package com.zlei.gp.mapper;

import com.zlei.gp.entity.Manager;
import com.zlei.gp.entity.ManagerNameInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ManagerMapper {

    @Select("select managerId, managerName, password, managerUuid from manager_info where managerName = #{managerName}")
    public Manager getManagerInfo(@Param("managerName") String managerName);

    @Select("select managerName from manger_info")
    public List<ManagerNameInfo> getAllManagerName();

}
