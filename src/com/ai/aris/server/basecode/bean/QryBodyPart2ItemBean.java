package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class QryBodyPart2ItemBean extends DataContainer implements DataContainerInterface,IQryBodyPart2ItemValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.QryBodyPart2Item";



  public final static  String S_ItemmastDesc = "ITEMMAST_DESC";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_BodypartTypename = "BODYPART_TYPENAME";
  public final static  String S_BodypartPid = "BODYPART_PID";
  public final static  String S_BodypartType = "BODYPART_TYPE";
  public final static  String S_Bodypart2Id = "BODYPART2_ID";
  public final static  String S_BodypartPname = "BODYPART_PNAME";
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
  public QryBodyPart2ItemBean() throws AIException{
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

  public void initBodypartTypename(String value){
     this.initProperty(S_BodypartTypename,value);
  }
  public  void setBodypartTypename(String value){
     this.set(S_BodypartTypename,value);
  }
  public  void setBodypartTypenameNull(){
     this.set(S_BodypartTypename,null);
  }

  public String getBodypartTypename(){
       return DataType.getAsString(this.get(S_BodypartTypename));
  
  }
  public String getBodypartTypenameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BodypartTypename));
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

  public void initBodypartPname(String value){
     this.initProperty(S_BodypartPname,value);
  }
  public  void setBodypartPname(String value){
     this.set(S_BodypartPname,value);
  }
  public  void setBodypartPnameNull(){
     this.set(S_BodypartPname,null);
  }

  public String getBodypartPname(){
       return DataType.getAsString(this.get(S_BodypartPname));
  
  }
  public String getBodypartPnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BodypartPname));
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

