package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AisReportFilesBean extends DataContainer implements DataContainerInterface,IAisReportFilesValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AisReportFiles";



  public final static  String S_FileId = "FILE_ID";
  public final static  String S_Status = "STATUS";
  public final static  String S_ReportfileId = "REPORTFILE_ID";
  public final static  String S_ReportId = "REPORT_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisReportFilesBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initFileId(long value){
     this.initProperty(S_FileId,new Long(value));
  }
  public  void setFileId(long value){
     this.set(S_FileId,new Long(value));
  }
  public  void setFileIdNull(){
     this.set(S_FileId,null);
  }

  public long getFileId(){
        return DataType.getAsLong(this.get(S_FileId));
  
  }
  public long getFileIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_FileId));
      }

  public void initStatus(String value){
     this.initProperty(S_Status,value);
  }
  public  void setStatus(String value){
     this.set(S_Status,value);
  }
  public  void setStatusNull(){
     this.set(S_Status,null);
  }

  public String getStatus(){
       return DataType.getAsString(this.get(S_Status));
  
  }
  public String getStatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Status));
      }

  public void initReportfileId(long value){
     this.initProperty(S_ReportfileId,new Long(value));
  }
  public  void setReportfileId(long value){
     this.set(S_ReportfileId,new Long(value));
  }
  public  void setReportfileIdNull(){
     this.set(S_ReportfileId,null);
  }

  public long getReportfileId(){
        return DataType.getAsLong(this.get(S_ReportfileId));
  
  }
  public long getReportfileIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportfileId));
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


 
 }

