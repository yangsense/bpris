package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class QryAiscBodyPartBean extends DataContainer implements DataContainerInterface,IQryAiscBodyPartValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.QryAiscBodyPart";



  public final static  String S_BodypartEndesc = "BODYPART_ENDESC";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LastBodypart = "LAST_BODYPART";
  public final static  String S_BodypartPid = "BODYPART_PID";
  public final static  String S_BodypartType = "BODYPART_TYPE";
  public final static  String S_BodypartOrder = "BODYPART_ORDER";
  public final static  String S_BodypartTypedesc = "BODYPART_TYPEDESC";
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
  public QryAiscBodyPartBean() throws AIException{
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

  public void initOrgName(String value){
     this.initProperty(S_OrgName,value);
  }
  public  void setOrgName(String value){
     this.set(S_OrgName,value);
  }
  public  void setOrgNameNull(){
     this.set(S_OrgName,null);
  }

  public String getOrgName(){
       return DataType.getAsString(this.get(S_OrgName));
  
  }
  public String getOrgNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgName));
      }

  public void initLastBodypart(String value){
     this.initProperty(S_LastBodypart,value);
  }
  public  void setLastBodypart(String value){
     this.set(S_LastBodypart,value);
  }
  public  void setLastBodypartNull(){
     this.set(S_LastBodypart,null);
  }

  public String getLastBodypart(){
       return DataType.getAsString(this.get(S_LastBodypart));
  
  }
  public String getLastBodypartInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LastBodypart));
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

  public void initBodypartTypedesc(String value){
     this.initProperty(S_BodypartTypedesc,value);
  }
  public  void setBodypartTypedesc(String value){
     this.set(S_BodypartTypedesc,value);
  }
  public  void setBodypartTypedescNull(){
     this.set(S_BodypartTypedesc,null);
  }

  public String getBodypartTypedesc(){
       return DataType.getAsString(this.get(S_BodypartTypedesc));
  
  }
  public String getBodypartTypedescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BodypartTypedesc));
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

