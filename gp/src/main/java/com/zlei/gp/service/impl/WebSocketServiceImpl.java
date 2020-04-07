package com.zlei.gp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlei.gp.entity.SocketMsg;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName: WebSocketServiceImpl
 * @Description: WebSocket实现方法
 * @Date: 2020-03-23 15:51
 * @Author: ZhangLei
 * version: 1.0
 **/
@SuppressWarnings("all")
@ServerEndpoint("/websocket/{nickname}")
@Component
public class WebSocketServiceImpl {

    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<WebSocketServiceImpl>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 用户名称
    private String nickname;

    //用来记录sessionId和该session进行绑定
    private static Map<String,Session> map = new HashMap<String, Session>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("nickname") String nickname) {
        this.session = session;
        this.nickname = nickname;
        map.put(session.getId(), session);

        webSocketSet.add(this);     //加入set中
        System.out.println("有新连接加入！当前在线人数为" + webSocketSet.size());
        broadcast(this.nickname + "上线了----他的频道Id是" + session.getId());
//        this.session.getAsyncRemote().sendText(this.nickname + "上线了----他的频道Id是" + session.getId());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("nickname") String nickname) {
        System.out.println("来自客户端的消息:--->" + nickname + ":" + message);

        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;
        try{
            socketMsg = objectMapper.readValue(message, SocketMsg.class);
            if (socketMsg.getType() == 1) {
                // 单聊 需找到发送者和接收者
                socketMsg.setFromUser(session.getId());
                Session fromSession = map.get(socketMsg.getFromUser());
                Session toSession = map.get(socketMsg.getToUser());
                // 发送给接收者
                if (toSession != null) {
                    // 发送给发送者
                    fromSession.getAsyncRemote().sendText(nickname + ":" + socketMsg.getMsg());
                    toSession.getAsyncRemote().sendText(nickname + ":" + socketMsg.getMsg());
                }
                else {
                    fromSession.getAsyncRemote().sendText("系统消息:对方不在线或者您输入的频道Id不对");
                }
            }
            else {
                //群发消息
                broadcast(nickname + ":" + message);
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     * */
    public  void broadcast(String message){
        for (WebSocketServiceImpl item : webSocketSet) {
            //this.session.getBasicRemote().sendText(message);
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }
    }
}
