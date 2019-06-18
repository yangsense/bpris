package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class QryItemMastBean extends DataContainer implements DataContainerInterface,IQryItemMastValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.QryItemMast";



  public final static  String S_ItemmastDesc = "ITEMMAST_DESC";
  public final static  String S_ItemmastPrice = "ITEMMAST_PRICE";
  public final static  String S_PyInitial = "PY_INITIAL";
  public final static  String S_ItemmastIsenhanced = "ITEMMAST_ISENHANCED";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_ItemmastExposurecount = "ITEMMAST_EXPOSURECOUNT";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_ItemmastSevice = "ITEMMAST_SEVICE";
  public final static  String S_OrdsubcategoryId = "ORDSUBCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_ItemmastWeight = "ITEMMAST_WEIGHT";
  public final static  String S_ItemmastEndesc = "ITEMMAST_ENDESC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_ItemmastCode = "ITEMMAST_CODE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryItemMastBean() throws AIException{
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

  public void initItemmastPrice(long value){
     this.initProperty(S_ItemmastPrice,new Long(value));
  }
  public  void setItemmastPrice(long value){
     this.set(S_ItemmastPrice,new Long(value));
  }
  public  void setItemmastPriceNull(){
     this.set(S_ItemmastPrice,null);
  }

  public long getItemmastPrice(){
        return DataType.getAsLong(this.get(S_ItemmastPrice));
  
  }
  public long getItemmastPriceInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemmastPrice));
      }

  public void initPyInitial(String value){
     this.initProperty(S_PyInitial,value);
  }
  public  void setPyInitial(String value){
     this.set(S_PyInitial,value);
  }
  public  void setPyInitialNull(){
     this.set(S_PyInitial,null);
  }

  public String getPyInitial(){
       return DataType.getAsString(this.get(S_PyInitial));
  
  }
  public String getPyInitialInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PyInitial));
      }

  public void initItemmastIsenhanced(long value){
     this.initProperty(S_ItemmastIsenhanced,new Long(value));
  }
  public  void setItemmastIsenhanced(long value){
     this.set(S_ItemmastIsenhanced,new Long(value));
  }
  public  void setItemmastIsenhancedNull(){
     this.set(S_ItemmastIsenhanced,null);
  }

  public long getItemmastIsenhanced(){
        return DataType.getAsLong(this.get(S_ItemmastIsenhanced));
  
  }
  public long getItemmastIsenhancedInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemmastIsenhanced));
      }

  public void initOrdcategoryId(long value){
     this.initProperty(S_OrdcategoryId,new Long(value));
  }
  public  void setOrdcategoryId(long value){
     this.set(S_OrdcategoryId,new Long(value));
  }
  public  void setOrdcategoryIdNull(){
     this.set(S_OrdcategoryId,null);
  }

  public long getOrdcategoryId(){
        return DataType.getAsLong(this.get(S_OrdcategoryId));
  
  }
  public long getOrdcategoryIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrdcategoryId));
      }

  public void initItemmastExposurecount(long value){
     this.initProperty(S_ItemmastExposurecount,new Long(value));
  }
  public  void setItemmastExposurecount(long value){
     this.set(S_ItemmastExposurecount,new Long(value));
  }
  public  void setItemmastExposurecountNull(){
     this.set(S_ItemmastExposurecount,null);
  }

  public long getItemmastExposurecount(){
        return DataType.getAsLong(this.get(S_ItemmastExposurecount));
  
  }
  public long getItemmastExposurecountInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemmastExposurecount));
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

  public void initItemmastSevice(long value){
     this.initProperty(S_ItemmastSevice,new Long(value));
  }
  public  void setItemmastSevice(long value){
     this.set(S_ItemmastSevice,new Long(value));
  }
  public  void setItemmastSeviceNull(){
     this.set(S_ItemmastSevice,null);
  }

  public long getItemmastSevice(){
        return DataType.getAsLong(this.get(S_ItemmastSevice));
  
  }
  public long getItemmastSeviceInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemmastSevice));
      }

  public void initOrdsubcategoryId(long value){
     this.initProperty(S_OrdsubcategoryId,new Long(value));
  }
  public  void setOrdsubcategoryId(long value){
     this.set(S_OrdsubcategoryId,new Long(value));
  }
  public  void setOrdsubcategoryIdNull(){
     this.set(S_OrdsubcategoryId,null);
  }

  public long getOrdsubcategoryId(){
        return DataType.getAsLong(this.get(S_OrdsubcategoryId));
  
  }
  public long getOrdsubcategoryIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrdsubcategoryId));
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

  public void initItemmastWeight(long value){
     this.initProperty(S_ItemmastWeight,new Long(value));
  }
  public  void setItemmastWeight(long value){
     this.set(S_ItemmastWeight,new Long(value));
  }
  public  void setItemmastWeightNull(){
     this.set(S_ItemmastWeight,null);
  }

  public long getItemmastWeight(){
        return DataType.getAsLong(this.get(S_ItemmastWeight));
  
  }
  public long getItemmastWeightInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemmastWeight));
      }

  public void initItemmastEndesc(String value){
     this.initProperty(S_ItemmastEndesc,value);
  }
  public  void setItemmastEndesc(String value){
     this.set(S_ItemmastEndesc,value);
  }
  public  void setItemmastEndescNull(){
     this.set(S_ItemmastEndesc,null);
  }

  public String getItemmastEndesc(){
       return DataType.getAsString(this.get(S_ItemmastEndesc));
  
  }
  public String getItemmastEndescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ItemmastEndesc));
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

  public void initItemmastCode(String value){
     this.initProperty(S_ItemmastCode,value);
  }
  public  void setItemmastCode(String value){
     this.set(S_ItemmastCode,value);
  }
  public  void setItemmastCodeNull(){
     this.set(S_ItemmastCode,null);
  }

  public String getItemmastCode(){
       return DataType.getAsString(this.get(S_ItemmastCode));
  
  }
  public String getItemmastCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ItemmastCode));
      }


 
 }

