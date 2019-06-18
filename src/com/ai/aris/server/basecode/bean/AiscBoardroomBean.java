package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscBoardroomBean extends DataContainer implements DataContainerInterface,IAiscBoardroomValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscBoardroom";



  public final static  String S_Maxusercount = "MAXUSERCOUNT";
  public final static  String S_Verifymode = "VERIFYMODE";
  public final static  String S_Keycode = "KEYCODE";
  public final static  String S_BoardroomName = "BOARDROOM_NAME";
  public final static  String S_BoardroomId = "BOARDROOM_ID";
  public final static  String S_Password = "PASSWORD";
  public final static  String S_Enablechairpwd = "ENABLECHAIRPWD";
  public final static  String S_Chairpassword = "CHAIRPASSWORD";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscBoardroomBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initMaxusercount(long value){
     this.initProperty(S_Maxusercount,new Long(value));
  }
  public  void setMaxusercount(long value){
     this.set(S_Maxusercount,new Long(value));
  }
  public  void setMaxusercountNull(){
     this.set(S_Maxusercount,null);
  }

  public long getMaxusercount(){
        return DataType.getAsLong(this.get(S_Maxusercount));
  
  }
  public long getMaxusercountInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Maxusercount));
      }

  public void initVerifymode(String value){
     this.initProperty(S_Verifymode,value);
  }
  public  void setVerifymode(String value){
     this.set(S_Verifymode,value);
  }
  public  void setVerifymodeNull(){
     this.set(S_Verifymode,null);
  }

  public String getVerifymode(){
       return DataType.getAsString(this.get(S_Verifymode));
  
  }
  public String getVerifymodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Verifymode));
      }

  public void initKeycode(String value){
     this.initProperty(S_Keycode,value);
  }
  public  void setKeycode(String value){
     this.set(S_Keycode,value);
  }
  public  void setKeycodeNull(){
     this.set(S_Keycode,null);
  }

  public String getKeycode(){
       return DataType.getAsString(this.get(S_Keycode));
  
  }
  public String getKeycodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Keycode));
      }

  public void initBoardroomName(String value){
     this.initProperty(S_BoardroomName,value);
  }
  public  void setBoardroomName(String value){
     this.set(S_BoardroomName,value);
  }
  public  void setBoardroomNameNull(){
     this.set(S_BoardroomName,null);
  }

  public String getBoardroomName(){
       return DataType.getAsString(this.get(S_BoardroomName));
  
  }
  public String getBoardroomNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BoardroomName));
      }

  public void initBoardroomId(long value){
     this.initProperty(S_BoardroomId,new Long(value));
  }
  public  void setBoardroomId(long value){
     this.set(S_BoardroomId,new Long(value));
  }
  public  void setBoardroomIdNull(){
     this.set(S_BoardroomId,null);
  }

  public long getBoardroomId(){
        return DataType.getAsLong(this.get(S_BoardroomId));
  
  }
  public long getBoardroomIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_BoardroomId));
      }

  public void initPassword(String value){
     this.initProperty(S_Password,value);
  }
  public  void setPassword(String value){
     this.set(S_Password,value);
  }
  public  void setPasswordNull(){
     this.set(S_Password,null);
  }

  public String getPassword(){
       return DataType.getAsString(this.get(S_Password));
  
  }
  public String getPasswordInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Password));
      }

  public void initEnablechairpwd(String value){
     this.initProperty(S_Enablechairpwd,value);
  }
  public  void setEnablechairpwd(String value){
     this.set(S_Enablechairpwd,value);
  }
  public  void setEnablechairpwdNull(){
     this.set(S_Enablechairpwd,null);
  }

  public String getEnablechairpwd(){
       return DataType.getAsString(this.get(S_Enablechairpwd));
  
  }
  public String getEnablechairpwdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Enablechairpwd));
      }

  public void initChairpassword(String value){
     this.initProperty(S_Chairpassword,value);
  }
  public  void setChairpassword(String value){
     this.set(S_Chairpassword,value);
  }
  public  void setChairpasswordNull(){
     this.set(S_Chairpassword,null);
  }

  public String getChairpassword(){
       return DataType.getAsString(this.get(S_Chairpassword));
  
  }
  public String getChairpasswordInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Chairpassword));
      }


 
 }

