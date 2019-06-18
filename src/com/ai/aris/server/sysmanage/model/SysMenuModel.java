package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class SysMenuModel {

    private  String menuImage;
    private  long menuType;
    private  Timestamp createDate;
    private  String menuDesc;
    private  long parentMenuId;
    private  String menuUrl;
    private  String state;
    private  long menuId;
    private  String menuName;
    private  long menuOrder;
    private  long sysType;
    private  String groupId;

//自定义字典

    public String getMenuImage(){
        return menuImage;
    }
    public long getMenuType(){
        return menuType;
    }
    public Timestamp getCreateDate(){
        return createDate;
    }
    public String getMenuDesc(){
        return menuDesc;
    }
    public long getParentMenuId(){
        return parentMenuId;
    }
    public String getMenuUrl(){
        return menuUrl;
    }
    public String getState(){
        return state;
    }
    public long getMenuId(){
        return menuId;
    }
    public String getMenuName(){
        return menuName;
    }
    public long getMenuOrder(){
        return menuOrder;
    }
    public long getSysType(){
        return sysType;
    }
    public String getGroupId(){
        return groupId;
    }

    public  void setMenuImage(String menuImage){
        this.menuImage = menuImage;
    }
    public  void setMenuType(long menuType){
        this.menuType = menuType;
    }
    public  void setCreateDate(Timestamp createDate){
        this.createDate = createDate;
    }
    public  void setMenuDesc(String menuDesc){
        this.menuDesc = menuDesc;
    }
    public  void setParentMenuId(long parentMenuId){
        this.parentMenuId = parentMenuId;
    }
    public  void setMenuUrl(String menuUrl){
        this.menuUrl = menuUrl;
    }
    public  void setState(String state){
        this.state = state;
    }
    public  void setMenuId(long menuId){
        this.menuId = menuId;
    }
    public  void setMenuName(String menuName){
        this.menuName = menuName;
    }
    public  void setMenuOrder(long menuOrder){
        this.menuOrder = menuOrder;
    }
    public  void setSysType(long sysType){
        this.sysType = sysType;
    }
    public  void setGroupId(String groupId){
        this.groupId = groupId;
    }

//自定义字典
}
