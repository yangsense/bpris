package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QuerySysRoleModel {

    private  Timestamp createDate;
    private  String orgId;
    private  String createUser;
    private  Timestamp updateDate;
    private  long roleState;
    private  String updateUser;
    private  long roleId;
    private  String roleName;
    private  String menuName;
    private  String roleDes;
    private  long sysType;
    private  String fmtCreateDate;

//自定义字典

    public Timestamp getCreateDate(){
        return createDate;
    }
    public String getOrgId(){
        return orgId;
    }
    public String getCreateUser(){
        return createUser;
    }
    public Timestamp getUpdateDate(){
        return updateDate;
    }
    public long getRoleState(){
        return roleState;
    }
    public String getUpdateUser(){
        return updateUser;
    }
    public long getRoleId(){
        return roleId;
    }
    public String getRoleName(){
        return roleName;
    }
    public String getMenuName(){
        return menuName;
    }
    public String getRoleDes(){
        return roleDes;
    }
    public long getSysType(){
        return sysType;
    }
    public String getFmtCreateDate(){
        return fmtCreateDate;
    }

    public  void setCreateDate(Timestamp createDate){
        this.createDate = createDate;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setCreateUser(String createUser){
        this.createUser = createUser;
    }
    public  void setUpdateDate(Timestamp updateDate){
        this.updateDate = updateDate;
    }
    public  void setRoleState(long roleState){
        this.roleState = roleState;
    }
    public  void setUpdateUser(String updateUser){
        this.updateUser = updateUser;
    }
    public  void setRoleId(long roleId){
        this.roleId = roleId;
    }
    public  void setRoleName(String roleName){
        this.roleName = roleName;
    }
    public  void setMenuName(String menuName){
        this.menuName = menuName;
    }
    public  void setRoleDes(String roleDes){
        this.roleDes = roleDes;
    }
    public  void setSysType(long sysType){
        this.sysType = sysType;
    }
    public  void setFmtCreateDate(String fmtCreateDate){
        this.fmtCreateDate = fmtCreateDate;
    }

//自定义字典
}
