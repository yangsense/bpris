package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class SysRole2menuModel {

    private  long roleId;
    private  long menuId;

//自定义字典

    public long getRoleId(){
        return roleId;
    }
    public long getMenuId(){
        return menuId;
    }

    public  void setRoleId(long roleId){
        this.roleId = roleId;
    }
    public  void setMenuId(long menuId){
        this.menuId = menuId;
    }

//自定义字典
}
