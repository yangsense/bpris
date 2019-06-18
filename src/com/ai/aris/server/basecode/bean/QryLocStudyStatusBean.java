package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class QryLocStudyStatusBean extends DataContainer implements DataContainerInterface,IQryLocStudyStatusValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.QryLocStudyStatus";



  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LocStudystaId = "LOC_STUDYSTA_ID";
  public final static  String S_LocEndesc = "LOC_ENDESC";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_StudyStatus = "STUDY_STATUS";
  public final static  String S_StatusName = "STATUS_NAME";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryLocStudyStatusBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initLocCode(String value){
     this.initProperty(S_LocCode,value);
  }
  public  void setLocCode(String value){
     this.set(S_LocCode,value);
  }
  public  void setLocCodeNull(){
     this.set(S_LocCode,null);
  }

  public String getLocCode(){
       return DataType.getAsString(this.get(S_LocCode));
  
  }
  public String getLocCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocCode));
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

  public void initLocStudystaId(long value){
     this.initProperty(S_LocStudystaId,new Long(value));
  }
  public  void setLocStudystaId(long value){
     this.set(S_LocStudystaId,new Long(value));
  }
  public  void setLocStudystaIdNull(){
     this.set(S_LocStudystaId,null);
  }

  public long getLocStudystaId(){
        return DataType.getAsLong(this.get(S_LocStudystaId));
  
  }
  public long getLocStudystaIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_LocStudystaId));
      }

  public void initLocEndesc(String value){
     this.initProperty(S_LocEndesc,value);
  }
  public  void setLocEndesc(String value){
     this.set(S_LocEndesc,value);
  }
  public  void setLocEndescNull(){
     this.set(S_LocEndesc,null);
  }

  public String getLocEndesc(){
       return DataType.getAsString(this.get(S_LocEndesc));
  
  }
  public String getLocEndescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocEndesc));
      }

  public void initLocDesc(String value){
     this.initProperty(S_LocDesc,value);
  }
  public  void setLocDesc(String value){
     this.set(S_LocDesc,value);
  }
  public  void setLocDescNull(){
     this.set(S_LocDesc,null);
  }

  public String getLocDesc(){
       return DataType.getAsString(this.get(S_LocDesc));
  
  }
  public String getLocDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocDesc));
      }

  public void initLocId(long value){
     this.initProperty(S_LocId,new Long(value));
  }
  public  void setLocId(long value){
     this.set(S_LocId,new Long(value));
  }
  public  void setLocIdNull(){
     this.set(S_LocId,null);
  }

  public long getLocId(){
        return DataType.getAsLong(this.get(S_LocId));
  
  }
  public long getLocIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_LocId));
      }

  public void initStudyStatus(String value){
     this.initProperty(S_StudyStatus,value);
  }
  public  void setStudyStatus(String value){
     this.set(S_StudyStatus,value);
  }
  public  void setStudyStatusNull(){
     this.set(S_StudyStatus,null);
  }

  public String getStudyStatus(){
       return DataType.getAsString(this.get(S_StudyStatus));
  
  }
  public String getStudyStatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyStatus));
      }

  public void initStatusName(String value){
     this.initProperty(S_StatusName,value);
  }
  public  void setStatusName(String value){
     this.set(S_StatusName,value);
  }
  public  void setStatusNameNull(){
     this.set(S_StatusName,null);
  }

  public String getStatusName(){
       return DataType.getAsString(this.get(S_StatusName));
  
  }
  public String getStatusNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StatusName));
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

