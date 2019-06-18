package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AisKnowledgebaseBean extends DataContainer implements DataContainerInterface,IAisKnowledgebaseValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AisKnowledgebase";



  public final static  String S_CollectorCode = "COLLECTOR_CODE";
  public final static  String S_OperateDatetime = "OPERATE_DATETIME";
  public final static  String S_Keydesc = "KEYDESC";
  public final static  String S_Casegroupdesc = "CASEGROUPDESC";
  public final static  String S_ReportId = "REPORT_ID";
  public final static  String S_Knowledgebaseid = "KNOWLEDGEBASEID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisKnowledgebaseBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initCollectorCode(String value){
     this.initProperty(S_CollectorCode,value);
  }
  public  void setCollectorCode(String value){
     this.set(S_CollectorCode,value);
  }
  public  void setCollectorCodeNull(){
     this.set(S_CollectorCode,null);
  }

  public String getCollectorCode(){
       return DataType.getAsString(this.get(S_CollectorCode));
  
  }
  public String getCollectorCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CollectorCode));
      }

  public void initOperateDatetime(Timestamp value){
     this.initProperty(S_OperateDatetime,value);
  }
  public  void setOperateDatetime(Timestamp value){
     this.set(S_OperateDatetime,value);
  }
  public  void setOperateDatetimeNull(){
     this.set(S_OperateDatetime,null);
  }

  public Timestamp getOperateDatetime(){
        return DataType.getAsDateTime(this.get(S_OperateDatetime));
  
  }
  public Timestamp getOperateDatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_OperateDatetime));
      }

  public void initKeydesc(String value){
     this.initProperty(S_Keydesc,value);
  }
  public  void setKeydesc(String value){
     this.set(S_Keydesc,value);
  }
  public  void setKeydescNull(){
     this.set(S_Keydesc,null);
  }

  public String getKeydesc(){
       return DataType.getAsString(this.get(S_Keydesc));
  
  }
  public String getKeydescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Keydesc));
      }

  public void initCasegroupdesc(String value){
     this.initProperty(S_Casegroupdesc,value);
  }
  public  void setCasegroupdesc(String value){
     this.set(S_Casegroupdesc,value);
  }
  public  void setCasegroupdescNull(){
     this.set(S_Casegroupdesc,null);
  }

  public String getCasegroupdesc(){
       return DataType.getAsString(this.get(S_Casegroupdesc));
  
  }
  public String getCasegroupdescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Casegroupdesc));
      }

  public void initReportId(long value){
     this.initProperty(S_ReportId,new Long(value));
  }
  public  void setReportId(long value){
     this.set(S_ReportId,new Long(value));
  }
  public  void setReportIdNull(){
     this.set(S_ReportId,null);
  }

  public long getReportId(){
        return DataType.getAsLong(this.get(S_ReportId));
  
  }
  public long getReportIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportId));
      }

  public void initKnowledgebaseid(long value){
     this.initProperty(S_Knowledgebaseid,new Long(value));
  }
  public  void setKnowledgebaseid(long value){
     this.set(S_Knowledgebaseid,new Long(value));
  }
  public  void setKnowledgebaseidNull(){
     this.set(S_Knowledgebaseid,null);
  }

  public long getKnowledgebaseid(){
        return DataType.getAsLong(this.get(S_Knowledgebaseid));
  
  }
  public long getKnowledgebaseidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Knowledgebaseid));
      }


 
 }

