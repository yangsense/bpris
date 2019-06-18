package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscEquipmentBean extends DataContainer implements DataContainerInterface,IAiscEquipmentValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscEquipment";



  public final static  String S_EquipmentCode = "EQUIPMENT_CODE";
  public final static  String S_EquipmentPhone = "EQUIPMENT_PHONE";
  public final static  String S_ModalityEnabled = "MODALITY_ENABLED";
  public final static  String S_EquipmentIp = "EQUIPMENT_IP";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_ModalitySupportchinese = "MODALITY_SUPPORTCHINESE";
  public final static  String S_ModalitySupportid = "MODALITY_SUPPORTID";
  public final static  String S_ModalityId = "MODALITY_ID";
  public final static  String S_ModalityWorklist = "MODALITY_WORKLIST";
  public final static  String S_EquipmentPort = "EQUIPMENT_PORT";
  public final static  String S_OrdsubcategoryId = "ORDSUBCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_EquipmentManufacturer = "EQUIPMENT_MANUFACTURER";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_EquipmentStartdate = "EQUIPMENT_STARTDATE";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_EquipmentEndesc = "EQUIPMENT_ENDESC";
  public final static  String S_EquipmentAe = "EQUIPMENT_AE";
  public final static  String S_ModalitySupportsexage = "MODALITY_SUPPORTSEXAGE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscEquipmentBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initEquipmentCode(String value){
     this.initProperty(S_EquipmentCode,value);
  }
  public  void setEquipmentCode(String value){
     this.set(S_EquipmentCode,value);
  }
  public  void setEquipmentCodeNull(){
     this.set(S_EquipmentCode,null);
  }

  public String getEquipmentCode(){
       return DataType.getAsString(this.get(S_EquipmentCode));
  
  }
  public String getEquipmentCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EquipmentCode));
      }

  public void initEquipmentPhone(String value){
     this.initProperty(S_EquipmentPhone,value);
  }
  public  void setEquipmentPhone(String value){
     this.set(S_EquipmentPhone,value);
  }
  public  void setEquipmentPhoneNull(){
     this.set(S_EquipmentPhone,null);
  }

  public String getEquipmentPhone(){
       return DataType.getAsString(this.get(S_EquipmentPhone));
  
  }
  public String getEquipmentPhoneInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EquipmentPhone));
      }

  public void initModalityEnabled(int value){
     this.initProperty(S_ModalityEnabled,new Integer(value));
  }
  public  void setModalityEnabled(int value){
     this.set(S_ModalityEnabled,new Integer(value));
  }
  public  void setModalityEnabledNull(){
     this.set(S_ModalityEnabled,null);
  }

  public int getModalityEnabled(){
        return DataType.getAsInt(this.get(S_ModalityEnabled));
  
  }
  public int getModalityEnabledInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_ModalityEnabled));
      }

  public void initEquipmentIp(String value){
     this.initProperty(S_EquipmentIp,value);
  }
  public  void setEquipmentIp(String value){
     this.set(S_EquipmentIp,value);
  }
  public  void setEquipmentIpNull(){
     this.set(S_EquipmentIp,null);
  }

  public String getEquipmentIp(){
       return DataType.getAsString(this.get(S_EquipmentIp));
  
  }
  public String getEquipmentIpInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EquipmentIp));
      }

  public void initRoomId(long value){
     this.initProperty(S_RoomId,new Long(value));
  }
  public  void setRoomId(long value){
     this.set(S_RoomId,new Long(value));
  }
  public  void setRoomIdNull(){
     this.set(S_RoomId,null);
  }

  public long getRoomId(){
        return DataType.getAsLong(this.get(S_RoomId));
  
  }
  public long getRoomIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RoomId));
      }

  public void initModalitySupportchinese(int value){
     this.initProperty(S_ModalitySupportchinese,new Integer(value));
  }
  public  void setModalitySupportchinese(int value){
     this.set(S_ModalitySupportchinese,new Integer(value));
  }
  public  void setModalitySupportchineseNull(){
     this.set(S_ModalitySupportchinese,null);
  }

  public int getModalitySupportchinese(){
        return DataType.getAsInt(this.get(S_ModalitySupportchinese));
  
  }
  public int getModalitySupportchineseInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_ModalitySupportchinese));
      }

  public void initModalitySupportid(int value){
     this.initProperty(S_ModalitySupportid,new Integer(value));
  }
  public  void setModalitySupportid(int value){
     this.set(S_ModalitySupportid,new Integer(value));
  }
  public  void setModalitySupportidNull(){
     this.set(S_ModalitySupportid,null);
  }

  public int getModalitySupportid(){
        return DataType.getAsInt(this.get(S_ModalitySupportid));
  
  }
  public int getModalitySupportidInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_ModalitySupportid));
      }

  public void initModalityId(long value){
     this.initProperty(S_ModalityId,new Long(value));
  }
  public  void setModalityId(long value){
     this.set(S_ModalityId,new Long(value));
  }
  public  void setModalityIdNull(){
     this.set(S_ModalityId,null);
  }

  public long getModalityId(){
        return DataType.getAsLong(this.get(S_ModalityId));
  
  }
  public long getModalityIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ModalityId));
      }

  public void initModalityWorklist(int value){
     this.initProperty(S_ModalityWorklist,new Integer(value));
  }
  public  void setModalityWorklist(int value){
     this.set(S_ModalityWorklist,new Integer(value));
  }
  public  void setModalityWorklistNull(){
     this.set(S_ModalityWorklist,null);
  }

  public int getModalityWorklist(){
        return DataType.getAsInt(this.get(S_ModalityWorklist));
  
  }
  public int getModalityWorklistInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_ModalityWorklist));
      }

  public void initEquipmentPort(long value){
     this.initProperty(S_EquipmentPort,new Long(value));
  }
  public  void setEquipmentPort(long value){
     this.set(S_EquipmentPort,new Long(value));
  }
  public  void setEquipmentPortNull(){
     this.set(S_EquipmentPort,null);
  }

  public long getEquipmentPort(){
        return DataType.getAsLong(this.get(S_EquipmentPort));
  
  }
  public long getEquipmentPortInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_EquipmentPort));
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

  public void initEquipmentManufacturer(String value){
     this.initProperty(S_EquipmentManufacturer,value);
  }
  public  void setEquipmentManufacturer(String value){
     this.set(S_EquipmentManufacturer,value);
  }
  public  void setEquipmentManufacturerNull(){
     this.set(S_EquipmentManufacturer,null);
  }

  public String getEquipmentManufacturer(){
       return DataType.getAsString(this.get(S_EquipmentManufacturer));
  
  }
  public String getEquipmentManufacturerInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EquipmentManufacturer));
      }

  public void initEquipmentDesc(String value){
     this.initProperty(S_EquipmentDesc,value);
  }
  public  void setEquipmentDesc(String value){
     this.set(S_EquipmentDesc,value);
  }
  public  void setEquipmentDescNull(){
     this.set(S_EquipmentDesc,null);
  }

  public String getEquipmentDesc(){
       return DataType.getAsString(this.get(S_EquipmentDesc));
  
  }
  public String getEquipmentDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EquipmentDesc));
      }

  public void initEquipmentStartdate(Timestamp value){
     this.initProperty(S_EquipmentStartdate,value);
  }
  public  void setEquipmentStartdate(Timestamp value){
     this.set(S_EquipmentStartdate,value);
  }
  public  void setEquipmentStartdateNull(){
     this.set(S_EquipmentStartdate,null);
  }

  public Timestamp getEquipmentStartdate(){
        return DataType.getAsDateTime(this.get(S_EquipmentStartdate));
  
  }
  public Timestamp getEquipmentStartdateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_EquipmentStartdate));
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

  public void initEquipmentEndesc(String value){
     this.initProperty(S_EquipmentEndesc,value);
  }
  public  void setEquipmentEndesc(String value){
     this.set(S_EquipmentEndesc,value);
  }
  public  void setEquipmentEndescNull(){
     this.set(S_EquipmentEndesc,null);
  }

  public String getEquipmentEndesc(){
       return DataType.getAsString(this.get(S_EquipmentEndesc));
  
  }
  public String getEquipmentEndescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EquipmentEndesc));
      }

  public void initEquipmentAe(String value){
     this.initProperty(S_EquipmentAe,value);
  }
  public  void setEquipmentAe(String value){
     this.set(S_EquipmentAe,value);
  }
  public  void setEquipmentAeNull(){
     this.set(S_EquipmentAe,null);
  }

  public String getEquipmentAe(){
       return DataType.getAsString(this.get(S_EquipmentAe));
  
  }
  public String getEquipmentAeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EquipmentAe));
      }

  public void initModalitySupportsexage(int value){
     this.initProperty(S_ModalitySupportsexage,new Integer(value));
  }
  public  void setModalitySupportsexage(int value){
     this.set(S_ModalitySupportsexage,new Integer(value));
  }
  public  void setModalitySupportsexageNull(){
     this.set(S_ModalitySupportsexage,null);
  }

  public int getModalitySupportsexage(){
        return DataType.getAsInt(this.get(S_ModalitySupportsexage));
  
  }
  public int getModalitySupportsexageInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_ModalitySupportsexage));
      }


 
 }

