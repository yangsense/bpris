package com.ai.aris.server.sysmanage.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.sysmanage.bean.*;

public class QuerySysRoleBean extends DataContainer implements DataContainerInterface,IQuerySysRoleValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.QuerySysRole";



  public final static  String S_CreateDate = "CREATE_DATE";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_CreateUser = "CREATE_USER";
  public final static  String S_UpdateDate = "UPDATE_DATE";
  public final static  String S_RoleState = "ROLE_STATE";
  public final static  String S_UpdateUser = "UPDATE_USER";
  public final static  String S_RoleId = "ROLE_ID";
  public final static  String S_RoleName = "ROLE_NAME";
  public final static  String S_MenuName = "MENU_NAME";
  public final static  String S_RoleDes = "ROLE_DES";
  public final static  String S_SysType = "SYS_TYPE";
  public final static  String S_FmtCreateDate = "FMT_CREATE_DATE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QuerySysRoleBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initCreateDate(Timestamp value){
     this.initProperty(S_CreateDate,value);
  }
  public  void setCreateDate(Timestamp value){
     this.set(S_CreateDate,value);
  }
  public  void setCreateDateNull(){
     this.set(S_CreateDate,null);
  }

  public Timestamp getCreateDate(){
        return DataType.getAsDateTime(this.get(S_CreateDate));
  
  }
  public Timestamp getCreateDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CreateDate));
      }

  public void initOrgId(String value){
     this.initProperty(S_OrgId,value);
  }
  public  void setOrgId(String value){
     this.set(S_OrgId,value);
  }
  public  void setOrgIdNull(){
     this.set(S_OrgId,null);
  }

  public String getOrgId(){
       return DataType.getAsString(this.get(S_OrgId));
  
  }
  public String getOrgIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgId));
      }

  public void initCreateUser(String value){
     this.initProperty(S_CreateUser,value);
  }
  public  void setCreateUser(String value){
     this.set(S_CreateUser,value);
  }
  public  void setCreateUserNull(){
     this.set(S_CreateUser,null);
  }

  public String getCreateUser(){
       return DataType.getAsString(this.get(S_CreateUser));
  
  }
  public String getCreateUserInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CreateUser));
      }

  public void initUpdateDate(Timestamp value){
     this.initProperty(S_UpdateDate,value);
  }
  public  void setUpdateDate(Timestamp value){
     this.set(S_UpdateDate,value);
  }
  public  void setUpdateDateNull(){
     this.set(S_UpdateDate,null);
  }

  public Timestamp getUpdateDate(){
        return DataType.getAsDateTime(this.get(S_UpdateDate));
  
  }
  public Timestamp getUpdateDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_UpdateDate));
      }

  public void initRoleState(long value){
     this.initProperty(S_RoleState,new Long(value));
  }
  public  void setRoleState(long value){
     this.set(S_RoleState,new Long(value));
  }
  public  void setRoleStateNull(){
     this.set(S_RoleState,null);
  }

  public long getRoleState(){
        return DataType.getAsLong(this.get(S_RoleState));
  
  }
  public long getRoleStateInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RoleState));
      }

  public void initUpdateUser(String value){
     this.initProperty(S_UpdateUser,value);
  }
  public  void setUpdateUser(String value){
     this.set(S_UpdateUser,value);
  }
  public  void setUpdateUserNull(){
     this.set(S_UpdateUser,null);
  }

  public String getUpdateUser(){
       return DataType.getAsString(this.get(S_UpdateUser));
  
  }
  public String getUpdateUserInitialValue(){
        return DataType.getAsString(this.getOldObj(S_UpdateUser));
      }

  public void initRoleId(long value){
     this.initProperty(S_RoleId,new Long(value));
  }
  public  void setRoleId(long value){
     this.set(S_RoleId,new Long(value));
  }
  public  void setRoleIdNull(){
     this.set(S_RoleId,null);
  }

  public long getRoleId(){
        return DataType.getAsLong(this.get(S_RoleId));
  
  }
  public long getRoleIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RoleId));
      }

  public void initRoleName(String value){
     this.initProperty(S_RoleName,value);
  }
  public  void setRoleName(String value){
     this.set(S_RoleName,value);
  }
  public  void setRoleNameNull(){
     this.set(S_RoleName,null);
  }

  public String getRoleName(){
       return DataType.getAsString(this.get(S_RoleName));
  
  }
  public String getRoleNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RoleName));
      }

  public void initMenuName(String value){
     this.initProperty(S_MenuName,value);
  }
  public  void setMenuName(String value){
     this.set(S_MenuName,value);
  }
  public  void setMenuNameNull(){
     this.set(S_MenuName,null);
  }

  public String getMenuName(){
       return DataType.getAsString(this.get(S_MenuName));
  
  }
  public String getMenuNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MenuName));
      }

  public void initRoleDes(String value){
     this.initProperty(S_RoleDes,value);
  }
  public  void setRoleDes(String value){
     this.set(S_RoleDes,value);
  }
  public  void setRoleDesNull(){
     this.set(S_RoleDes,null);
  }

  public String getRoleDes(){
       return DataType.getAsString(this.get(S_RoleDes));
  
  }
  public String getRoleDesInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RoleDes));
      }

  public void initSysType(long value){
     this.initProperty(S_SysType,new Long(value));
  }
  public  void setSysType(long value){
     this.set(S_SysType,new Long(value));
  }
  public  void setSysTypeNull(){
     this.set(S_SysType,null);
  }

  public long getSysType(){
        return DataType.getAsLong(this.get(S_SysType));
  
  }
  public long getSysTypeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_SysType));
      }

  public void initFmtCreateDate(String value){
     this.initProperty(S_FmtCreateDate,value);
  }
  public  void setFmtCreateDate(String value){
     this.set(S_FmtCreateDate,value);
  }
  public  void setFmtCreateDateNull(){
     this.set(S_FmtCreateDate,null);
  }

  public String getFmtCreateDate(){
       return DataType.getAsString(this.get(S_FmtCreateDate));
  
  }
  public String getFmtCreateDateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_FmtCreateDate));
      }


 
 }

