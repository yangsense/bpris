package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class QryOrdCat2LocBean extends DataContainer implements DataContainerInterface,IQryOrdCat2LocValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.QryOrdCat2Loc";



  public final static  String S_OrdcategoryCode = "ORDCATEGORY_CODE";
  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_OrdcatId = "ORDCAT_ID";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_OrgCode = "ORG_CODE";
  public final static  String S_Ordcat2locId = "ORDCAT2LOC_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OrdcategoryDesc = "ORDCATEGORY_DESC";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryOrdCat2LocBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOrdcategoryCode(String value){
     this.initProperty(S_OrdcategoryCode,value);
  }
  public  void setOrdcategoryCode(String value){
     this.set(S_OrdcategoryCode,value);
  }
  public  void setOrdcategoryCodeNull(){
     this.set(S_OrdcategoryCode,null);
  }

  public String getOrdcategoryCode(){
       return DataType.getAsString(this.get(S_OrdcategoryCode));
  
  }
  public String getOrdcategoryCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrdcategoryCode));
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

  public void initOrdcatId(long value){
     this.initProperty(S_OrdcatId,new Long(value));
  }
  public  void setOrdcatId(long value){
     this.set(S_OrdcatId,new Long(value));
  }
  public  void setOrdcatIdNull(){
     this.set(S_OrdcatId,null);
  }

  public long getOrdcatId(){
        return DataType.getAsLong(this.get(S_OrdcatId));
  
  }
  public long getOrdcatIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrdcatId));
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

  public void initOrgCode(String value){
     this.initProperty(S_OrgCode,value);
  }
  public  void setOrgCode(String value){
     this.set(S_OrgCode,value);
  }
  public  void setOrgCodeNull(){
     this.set(S_OrgCode,null);
  }

  public String getOrgCode(){
       return DataType.getAsString(this.get(S_OrgCode));
  
  }
  public String getOrgCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgCode));
      }

  public void initOrdcat2locId(long value){
     this.initProperty(S_Ordcat2locId,new Long(value));
  }
  public  void setOrdcat2locId(long value){
     this.set(S_Ordcat2locId,new Long(value));
  }
  public  void setOrdcat2locIdNull(){
     this.set(S_Ordcat2locId,null);
  }

  public long getOrdcat2locId(){
        return DataType.getAsLong(this.get(S_Ordcat2locId));
  
  }
  public long getOrdcat2locIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Ordcat2locId));
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

  public void initOrdcategoryDesc(String value){
     this.initProperty(S_OrdcategoryDesc,value);
  }
  public  void setOrdcategoryDesc(String value){
     this.set(S_OrdcategoryDesc,value);
  }
  public  void setOrdcategoryDescNull(){
     this.set(S_OrdcategoryDesc,null);
  }

  public String getOrdcategoryDesc(){
       return DataType.getAsString(this.get(S_OrdcategoryDesc));
  
  }
  public String getOrdcategoryDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrdcategoryDesc));
      }


 
 }

