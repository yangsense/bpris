package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class QrybodypartListBean extends DataContainer implements DataContainerInterface,IQrybodypartListValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.QrybodypartList";



  public final static  String S_ItemmastDesc = "ITEMMAST_DESC";
  public final static  String S_BodypartEndesc = "BODYPART_ENDESC";
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
  public QrybodypartListBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initItemmastDesc(String value){
     this.initProperty(S_ItemmastDesc,value);
  }
  public  void setItemmastDesc(String value){
     this.set(S_ItemmastDesc,value);
  }
  public  void setItemmastDescNull(){
     this.set(S_ItemmastDesc,null);
  }

  public String getItemmastDesc(){
       return DataType.getAsString(this.get(S_ItemmastDesc));
  
  }
  public String getItemmastDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ItemmastDesc));
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

