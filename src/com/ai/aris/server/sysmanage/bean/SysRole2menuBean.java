package com.ai.aris.server.sysmanage.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

public class SysRole2menuBean extends DataContainer implements DataContainerInterface,ISysRole2menuValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.SysRole2menu";



  public final static  String S_MenuId = "MENU_ID";
  public final static  String S_RoleId = "ROLE_ID";
  public final static  String S_SysType = "SYS_TYPE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public SysRole2menuBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 @Override
public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initMenuId(long value){
     this.initProperty(S_MenuId,new Long(value));
  }
  public  void setMenuId(long value){
     this.set(S_MenuId,new Long(value));
  }
  public  void setMenuIdNull(){
     this.set(S_MenuId,null);
  }

  public long getMenuId(){
        return DataType.getAsLong(this.get(S_MenuId));
  
  }
  public long getMenuIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MenuId));
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


 
 }

