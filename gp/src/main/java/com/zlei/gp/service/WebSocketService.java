package com.zlei.gp.service;

import javax.websocket.Session;

public interface WebSocketService {

    public void onOpen(Session session);

    public void onClose();

    public void onMessage(String message, Session session);

    public void onError(String message, Throwable error);

    public void broadCast(String message);
}
