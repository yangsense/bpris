package com.ai.aris.server.sysmanage.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.*;

import java.sql.Timestamp;

public class SysMenuBean extends DataContainer implements DataContainerInterface,ISysMenuValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.SysMenu";



  public final static  String S_MenuName = "MENU_NAME";
  public final static  String S_MenuOrder = "MENU_ORDER";
  public final static  String S_State = "STATE";
  public final static  String S_ParentMenuId = "PARENT_MENU_ID";
  public final static  String S_GroupId = "GROUP_ID";
  public final static  String S_MenuUrl = "MENU_URL";
  public final static  String S_MenuType = "MENU_TYPE";
  public final static  String S_MenuId = "MENU_ID";
  public final static  String S_CreateDate = "CREATE_DATE";
  public final static  String S_MenuImage = "MENU_IMAGE";
  public final static  String S_SysType = "SYS_TYPE";
  public final static  String S_MenuDesc = "MENU_DESC";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public SysMenuBean() throws AIException {
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException {
   return S_TYPE;
 }

 @Override
public void setObjectType(ObjectType value) throws AIException {
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initMenuOrder(int value){
     this.initProperty(S_MenuOrder,new Integer(value));
  }
  public  void setMenuOrder(int value){
     this.set(S_MenuOrder,new Integer(value));
  }
  public  void setMenuOrderNull(){
     this.set(S_MenuOrder,null);
  }

  public int getMenuOrder(){
        return DataType.getAsInt(this.get(S_MenuOrder));
  
  }
  public int getMenuOrderInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_MenuOrder));
      }

  public void initState(String value){
     this.initProperty(S_State,value);
  }
  public  void setState(String value){
     this.set(S_State,value);
  }
  public  void setStateNull(){
     this.set(S_State,null);
  }

  public String getState(){
       return DataType.getAsString(this.get(S_State));
  
  }
  public String getStateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_State));
      }

  public void initParentMenuId(long value){
     this.initProperty(S_ParentMenuId,new Long(value));
  }
  public  void setParentMenuId(long value){
     this.set(S_ParentMenuId,new Long(value));
  }
  public  void setParentMenuIdNull(){
     this.set(S_ParentMenuId,null);
  }

  public long getParentMenuId(){
        return DataType.getAsLong(this.get(S_ParentMenuId));
  
  }
  public long getParentMenuIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ParentMenuId));
      }

  public void initGroupId(String value){
     this.initProperty(S_GroupId,value);
  }
  public  void setGroupId(String value){
     this.set(S_GroupId,value);
  }
  public  void setGroupIdNull(){
     this.set(S_GroupId,null);
  }

  public String getGroupId(){
       return DataType.getAsString(this.get(S_GroupId));
  
  }
  public String getGroupIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_GroupId));
      }

  public void initMenuUrl(String value){
     this.initProperty(S_MenuUrl,value);
  }
  public  void setMenuUrl(String value){
     this.set(S_MenuUrl,value);
  }
  public  void setMenuUrlNull(){
     this.set(S_MenuUrl,null);
  }

  public String getMenuUrl(){
       return DataType.getAsString(this.get(S_MenuUrl));
  
  }
  public String getMenuUrlInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MenuUrl));
      }

  public void initMenuType(int value){
     this.initProperty(S_MenuType,new Integer(value));
  }
  public  void setMenuType(int value){
     this.set(S_MenuType,new Integer(value));
  }
  public  void setMenuTypeNull(){
     this.set(S_MenuType,null);
  }

  public int getMenuType(){
        return DataType.getAsInt(this.get(S_MenuType));
  
  }
  public int getMenuTypeInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_MenuType));
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

  public void initMenuImage(String value){
     this.initProperty(S_MenuImage,value);
  }
  public  void setMenuImage(String value){
     this.set(S_MenuImage,value);
  }
  public  void setMenuImageNull(){
     this.set(S_MenuImage,null);
  }

  public String getMenuImage(){
       return DataType.getAsString(this.get(S_MenuImage));
  
  }
  public String getMenuImageInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MenuImage));
      }

  public void initSysType(int value){
     this.initProperty(S_SysType,new Integer(value));
  }
  public  void setSysType(int value){
     this.set(S_SysType,new Integer(value));
  }
  public  void setSysTypeNull(){
     this.set(S_SysType,null);
  }

  public int getSysType(){
        return DataType.getAsInt(this.get(S_SysType));
  
  }
  public int getSysTypeInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_SysType));
      }

  public void initMenuDesc(String value){
     this.initProperty(S_MenuDesc,value);
  }
  public  void setMenuDesc(String value){
     this.set(S_MenuDesc,value);
  }
  public  void setMenuDescNull(){
     this.set(S_MenuDesc,null);
  }

  public String getMenuDesc(){
       return DataType.getAsString(this.get(S_MenuDesc));
  
  }
  public String getMenuDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MenuDesc));
      }
 }

