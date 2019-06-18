package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryItemmastEquimentBean extends DataContainer implements DataContainerInterface,IQryItemmastEquimentValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryItemmastEquiment";



  public final static  String S_ItemmastPrice = "ITEMMAST_PRICE";
  public final static  String S_ItemmastDesc = "ITEMMAST_DESC";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_ItemmastCode = "ITEMMAST_CODE";
  public final static  String S_OrdsubcategoryId = "ORDSUBCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryItemmastEquimentBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initEquipmentId(long value){
     this.initProperty(S_EquipmentId,new Long(value));
  }
  public  void setEquipmentId(long value){
     this.set(S_EquipmentId,new Long(value));
  }
  public  void setEquipmentIdNull(){
     this.set(S_EquipmentId,null);
  }

  public long getEquipmentId(){
        return DataType.getAsLong(this.get(S_EquipmentId));
  
  }
  public long getEquipmentIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_EquipmentId));
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


 
 }

