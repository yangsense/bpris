package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class CheckBodyPartBean extends DataContainer implements DataContainerInterface,ICheckBodyPartValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.CheckBodyPart";



  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_Bodypart2Id = "BODYPART2_ID";
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
  public CheckBodyPartBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initBodypart2Id(long value){
     this.initProperty(S_Bodypart2Id,new Long(value));
  }
  public  void setBodypart2Id(long value){
     this.set(S_Bodypart2Id,new Long(value));
  }
  public  void setBodypart2IdNull(){
     this.set(S_Bodypart2Id,null);
  }

  public long getBodypart2Id(){
        return DataType.getAsLong(this.get(S_Bodypart2Id));
  
  }
  public long getBodypart2IdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Bodypart2Id));
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

