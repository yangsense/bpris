package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryBigBodypartBean extends DataContainer implements DataContainerInterface,IQryBigBodypartValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryBigBodypart";



  public final static  String S_BodypartEndesc = "BODYPART_ENDESC";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_BodypartPid = "BODYPART_PID";
  public final static  String S_BodypartType = "BODYPART_TYPE";
  public final static  String S_BodypartOrder = "BODYPART_ORDER";
  public final static  String S_BodypartDesc = "BODYPART_DESC";
  public final static  String S_BodypartCode = "BODYPART_CODE";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryBigBodypartBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initBodypartEndesc(String value){
     this.initProperty(S_BodypartEndesc,value);
  }
  public  void setBodypartEndesc(String value){
     this.set(S_BodypartEndesc,value);
  }
  public  void setBodypartEndescNull(){
     this.set(S_BodypartEndesc,null);
  }

  public String getBodypartEndesc(){
       return DataType.getAsString(this.get(S_BodypartEndesc));
  
  }
  public String getBodypartEndescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BodypartEndesc));
      }

  public void initItemmastId(long value){
     this.initProperty(S_ItemmastId,new Long(value));
  }
  public  void setItemmastId(long value){
     this.set(S_ItemmastId,new Long(value));
  }
  public  void setItemmastIdNull(){
     this.set(S_ItemmastId,null);
  }

  public long getItemmastId(){
        return DataType.getAsLong(this.get(S_ItemmastId));
  
  }
  public long getItemmastIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemmastId));
      }

  public void initBodypartPid(long value){
     this.initProperty(S_BodypartPid,new Long(value));
  }
  public  void setBodypartPid(long value){
     this.set(S_BodypartPid,new Long(value));
  }
  public  void setBodypartPidNull(){
     this.set(S_BodypartPid,null);
  }

  public long getBodypartPid(){
        return DataType.getAsLong(this.get(S_BodypartPid));
  
  }
  public long getBodypartPidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_BodypartPid));
      }

  public void initBodypartType(String value){
     this.initProperty(S_BodypartType,value);
  }
  public  void setBodypartType(String value){
     this.set(S_BodypartType,value);
  }
  public  void setBodypartTypeNull(){
     this.set(S_BodypartType,null);
  }

  public String getBodypartType(){
       return DataType.getAsString(this.get(S_BodypartType));
  
  }
  public String getBodypartTypeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BodypartType));
      }

  public void initBodypartOrder(long value){
     this.initProperty(S_BodypartOrder,new Long(value));
  }
  public  void setBodypartOrder(long value){
     this.set(S_BodypartOrder,new Long(value));
  }
  public  void setBodypartOrderNull(){
     this.set(S_BodypartOrder,null);
  }

  public long getBodypartOrder(){
        return DataType.getAsLong(this.get(S_BodypartOrder));
  
  }
  public long getBodypartOrderInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_BodypartOrder));
      }

  public void initBodypartDesc(String value){
     this.initProperty(S_BodypartDesc,value);
  }
  public  void setBodypartDesc(String value){
     this.set(S_BodypartDesc,value);
  }
  public  void setBodypartDescNull(){
     this.set(S_BodypartDesc,null);
  }

  public String getBodypartDesc(){
       return DataType.getAsString(this.get(S_BodypartDesc));
  
  }
  public String getBodypartDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BodypartDesc));
      }

  public void initBodypartCode(String value){
     this.initProperty(S_BodypartCode,value);
  }
  public  void setBodypartCode(String value){
     this.set(S_BodypartCode,value);
  }
  public  void setBodypartCodeNull(){
     this.set(S_BodypartCode,null);
  }

  public String getBodypartCode(){
       return DataType.getAsString(this.get(S_BodypartCode));
  
  }
  public String getBodypartCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BodypartCode));
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


 
 }

