package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscLocBean extends DataContainer implements DataContainerInterface,IAiscLocValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscLoc";



  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_LocPhone = "LOC_PHONE";
  public final static  String S_LocType = "LOC_TYPE";
  public final static  String S_LocAddress = "LOC_ADDRESS";
  public final static  String S_LocEndesc = "LOC_ENDESC";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_LocIndex = "LOC_INDEX";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscLocBean() throws AIException{
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

  public void initLocPhone(String value){
     this.initProperty(S_LocPhone,value);
  }
  public  void setLocPhone(String value){
     this.set(S_LocPhone,value);
  }
  public  void setLocPhoneNull(){
     this.set(S_LocPhone,null);
  }

  public String getLocPhone(){
       return DataType.getAsString(this.get(S_LocPhone));
  
  }
  public String getLocPhoneInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocPhone));
      }

  public void initLocType(String value){
     this.initProperty(S_LocType,value);
  }
  public  void setLocType(String value){
     this.set(S_LocType,value);
  }
  public  void setLocTypeNull(){
     this.set(S_LocType,null);
  }

  public String getLocType(){
       return DataType.getAsString(this.get(S_LocType));
  
  }
  public String getLocTypeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocType));
      }

  public void initLocAddress(String value){
     this.initProperty(S_LocAddress,value);
  }
  public  void setLocAddress(String value){
     this.set(S_LocAddress,value);
  }
  public  void setLocAddressNull(){
     this.set(S_LocAddress,null);
  }

  public String getLocAddress(){
       return DataType.getAsString(this.get(S_LocAddress));
  
  }
  public String getLocAddressInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocAddress));
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

  public void initServerId(long value){
     this.initProperty(S_ServerId,new Long(value));
  }
  public  void setServerId(long value){
     this.set(S_ServerId,new Long(value));
  }
  public  void setServerIdNull(){
     this.set(S_ServerId,null);
  }

  public long getServerId(){
        return DataType.getAsLong(this.get(S_ServerId));
  
  }
  public long getServerIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ServerId));
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

  public void initLocIndex(String value){
     this.initProperty(S_LocIndex,value);
  }
  public  void setLocIndex(String value){
     this.set(S_LocIndex,value);
  }
  public  void setLocIndexNull(){
     this.set(S_LocIndex,null);
  }

  public String getLocIndex(){
       return DataType.getAsString(this.get(S_LocIndex));
  
  }
  public String getLocIndexInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocIndex));
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

