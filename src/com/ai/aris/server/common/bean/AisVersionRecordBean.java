package com.ai.aris.server.common.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.common.bean.*;

public class AisVersionRecordBean extends DataContainer implements DataContainerInterface,IAisVersionRecordValue{

  private static String  m_boName = "com.ai.aris.server.common.bean.AisVersionRecord";



  public final static  String S_VersionBelongOrgcode = "VERSION_BELONG_ORGCODE";
  public final static  String S_UpdateDate = "UPDATE_DATE";
  public final static  String S_VersionBelongOrgname = "VERSION_BELONG_ORGNAME";
  public final static  String S_Id = "ID";
  public final static  String S_VersionNo = "VERSION_NO";
  public final static  String S_VersionRemark = "VERSION_REMARK";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisVersionRecordBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initVersionBelongOrgcode(String value){
     this.initProperty(S_VersionBelongOrgcode,value);
  }
  public  void setVersionBelongOrgcode(String value){
     this.set(S_VersionBelongOrgcode,value);
  }
  public  void setVersionBelongOrgcodeNull(){
     this.set(S_VersionBelongOrgcode,null);
  }

  public String getVersionBelongOrgcode(){
       return DataType.getAsString(this.get(S_VersionBelongOrgcode));
  
  }
  public String getVersionBelongOrgcodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_VersionBelongOrgcode));
      }

  public void initUpdateDate(Timestamp value){
     this.initProperty(S_UpdateDate,value);
  }
  public  void setUpdateDate(Timestamp value){
     this.set(S_UpdateDate,value);
  }
  public  void setUpdateDateNull(){
     this.set(S_UpdateDate,null);
  }

  public Timestamp getUpdateDate(){
        return DataType.getAsDateTime(this.get(S_UpdateDate));
  
  }
  public Timestamp getUpdateDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_UpdateDate));
      }

  public void initVersionBelongOrgname(String value){
     this.initProperty(S_VersionBelongOrgname,value);
  }
  public  void setVersionBelongOrgname(String value){
     this.set(S_VersionBelongOrgname,value);
  }
  public  void setVersionBelongOrgnameNull(){
     this.set(S_VersionBelongOrgname,null);
  }

  public String getVersionBelongOrgname(){
       return DataType.getAsString(this.get(S_VersionBelongOrgname));
  
  }
  public String getVersionBelongOrgnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_VersionBelongOrgname));
      }

  public void initId(String value){
     this.initProperty(S_Id,value);
  }
  public  void setId(String value){
     this.set(S_Id,value);
  }
  public  void setIdNull(){
     this.set(S_Id,null);
  }

  public String getId(){
       return DataType.getAsString(this.get(S_Id));
  
  }
  public String getIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Id));
      }

  public void initVersionNo(String value){
     this.initProperty(S_VersionNo,value);
  }
  public  void setVersionNo(String value){
     this.set(S_VersionNo,value);
  }
  public  void setVersionNoNull(){
     this.set(S_VersionNo,null);
  }

  public String getVersionNo(){
       return DataType.getAsString(this.get(S_VersionNo));
  
  }
  public String getVersionNoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_VersionNo));
      }

  public void initVersionRemark(String value){
     this.initProperty(S_VersionRemark,value);
  }
  public  void setVersionRemark(String value){
     this.set(S_VersionRemark,value);
  }
  public  void setVersionRemarkNull(){
     this.set(S_VersionRemark,null);
  }

  public String getVersionRemark(){
       return DataType.getAsString(this.get(S_VersionRemark));
  
  }
  public String getVersionRemarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_VersionRemark));
      }


 
 }

