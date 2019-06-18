package com.ai.aris.server.sysmanage.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.*;

import java.sql.Timestamp;

public class SysOperatorBean extends DataContainer implements DataContainerInterface,ISysOperatorValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.SysOperator";



  public final static  String S_Birthday = "BIRTHDAY";
  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_Remarks = "REMARKS";
  public final static  String S_TelNo = "TEL_NO";
  public final static  String S_CertNo = "CERT_NO";
  public final static  String S_PositionCode = "POSITION_CODE";
  public final static  String S_ChangePswDate = "CHANGE_PSW_DATE";
  public final static  String S_EmpPhoto = "EMP_PHOTO";
  public final static  String S_AddressVillageCode = "ADDRESS_VILLAGE_CODE";
  public final static  String S_OperatorName = "OPERATOR_NAME";
  public final static  String S_PositionLevelCode = "POSITION_LEVEL_CODE";
  public final static  String S_AddressTownCode = "ADDRESS_TOWN_CODE";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_AddressDetail = "ADDRESS_DETAIL";
  public final static  String S_SexCode = "SEX_CODE";
  public final static  String S_AddressAreaCode = "ADDRESS_AREA_CODE";
  public final static  String S_OperatorPsw = "OPERATOR_PSW";
  public final static  String S_AddressProvCode = "ADDRESS_PROV_CODE";
  public final static  String S_AddressCityCode = "ADDRESS_CITY_CODE";
  public final static  String S_Email = "EMAIL";
  public final static  String S_CreateDate = "CREATE_DATE";
  public final static  String S_OperatorState = "OPERATOR_STATE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public SysOperatorBean() throws AIException {
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException {
   return S_TYPE;
 }

 @Override
public void setObjectType(ObjectType value) throws AIException {
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initBirthday(Timestamp value){
     this.initProperty(S_Birthday,value);
  }
  public  void setBirthday(Timestamp value){
     this.set(S_Birthday,value);
  }
  public  void setBirthdayNull(){
     this.set(S_Birthday,null);
  }

  public Timestamp getBirthday(){
        return DataType.getAsDateTime(this.get(S_Birthday));
  
  }
  public Timestamp getBirthdayInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_Birthday));
      }

  public void initOperatorId(long value){
     this.initProperty(S_OperatorId,new Long(value));
  }
  public  void setOperatorId(long value){
     this.set(S_OperatorId,new Long(value));
  }
  public  void setOperatorIdNull(){
     this.set(S_OperatorId,null);
  }

  public long getOperatorId(){
        return DataType.getAsLong(this.get(S_OperatorId));
  
  }
  public long getOperatorIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OperatorId));
      }

  public void initRemarks(String value){
     this.initProperty(S_Remarks,value);
  }
  public  void setRemarks(String value){
     this.set(S_Remarks,value);
  }
  public  void setRemarksNull(){
     this.set(S_Remarks,null);
  }

  public String getRemarks(){
       return DataType.getAsString(this.get(S_Remarks));
  
  }
  public String getRemarksInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Remarks));
      }

  public void initTelNo(String value){
     this.initProperty(S_TelNo,value);
  }
  public  void setTelNo(String value){
     this.set(S_TelNo,value);
  }
  public  void setTelNoNull(){
     this.set(S_TelNo,null);
  }

  public String getTelNo(){
       return DataType.getAsString(this.get(S_TelNo));
  
  }
  public String getTelNoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_TelNo));
      }

  public void initCertNo(String value){
     this.initProperty(S_CertNo,value);
  }
  public  void setCertNo(String value){
     this.set(S_CertNo,value);
  }
  public  void setCertNoNull(){
     this.set(S_CertNo,null);
  }

  public String getCertNo(){
       return DataType.getAsString(this.get(S_CertNo));
  
  }
  public String getCertNoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CertNo));
      }

  public void initPositionCode(String value){
     this.initProperty(S_PositionCode,value);
  }
  public  void setPositionCode(String value){
     this.set(S_PositionCode,value);
  }
  public  void setPositionCodeNull(){
     this.set(S_PositionCode,null);
  }

  public String getPositionCode(){
       return DataType.getAsString(this.get(S_PositionCode));
  
  }
  public String getPositionCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PositionCode));
      }

  public void initChangePswDate(Timestamp value){
     this.initProperty(S_ChangePswDate,value);
  }
  public  void setChangePswDate(Timestamp value){
     this.set(S_ChangePswDate,value);
  }
  public  void setChangePswDateNull(){
     this.set(S_ChangePswDate,null);
  }

  public Timestamp getChangePswDate(){
        return DataType.getAsDateTime(this.get(S_ChangePswDate));
  
  }
  public Timestamp getChangePswDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ChangePswDate));
      }

  public void initEmpPhoto(String value){
     this.initProperty(S_EmpPhoto,value);
  }
  public  void setEmpPhoto(String value){
     this.set(S_EmpPhoto,value);
  }
  public  void setEmpPhotoNull(){
     this.set(S_EmpPhoto,null);
  }

  public String getEmpPhoto(){
       return DataType.getAsString(this.get(S_EmpPhoto));
  
  }
  public String getEmpPhotoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EmpPhoto));
      }

  public void initAddressVillageCode(String value){
     this.initProperty(S_AddressVillageCode,value);
  }
  public  void setAddressVillageCode(String value){
     this.set(S_AddressVillageCode,value);
  }
  public  void setAddressVillageCodeNull(){
     this.set(S_AddressVillageCode,null);
  }

  public String getAddressVillageCode(){
       return DataType.getAsString(this.get(S_AddressVillageCode));
  
  }
  public String getAddressVillageCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AddressVillageCode));
      }

  public void initOperatorName(String value){
     this.initProperty(S_OperatorName,value);
  }
  public  void setOperatorName(String value){
     this.set(S_OperatorName,value);
  }
  public  void setOperatorNameNull(){
     this.set(S_OperatorName,null);
  }

  public String getOperatorName(){
       return DataType.getAsString(this.get(S_OperatorName));
  
  }
  public String getOperatorNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorName));
      }

  public void initPositionLevelCode(String value){
     this.initProperty(S_PositionLevelCode,value);
  }
  public  void setPositionLevelCode(String value){
     this.set(S_PositionLevelCode,value);
  }
  public  void setPositionLevelCodeNull(){
     this.set(S_PositionLevelCode,null);
  }

  public String getPositionLevelCode(){
       return DataType.getAsString(this.get(S_PositionLevelCode));
  
  }
  public String getPositionLevelCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PositionLevelCode));
      }

  public void initAddressTownCode(String value){
     this.initProperty(S_AddressTownCode,value);
  }
  public  void setAddressTownCode(String value){
     this.set(S_AddressTownCode,value);
  }
  public  void setAddressTownCodeNull(){
     this.set(S_AddressTownCode,null);
  }

  public String getAddressTownCode(){
       return DataType.getAsString(this.get(S_AddressTownCode));
  
  }
  public String getAddressTownCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AddressTownCode));
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

  public void initOperatorCode(String value){
     this.initProperty(S_OperatorCode,value);
  }
  public  void setOperatorCode(String value){
     this.set(S_OperatorCode,value);
  }
  public  void setOperatorCodeNull(){
     this.set(S_OperatorCode,null);
  }

  public String getOperatorCode(){
       return DataType.getAsString(this.get(S_OperatorCode));
  
  }
  public String getOperatorCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorCode));
      }

  public void initAddressDetail(String value){
     this.initProperty(S_AddressDetail,value);
  }
  public  void setAddressDetail(String value){
     this.set(S_AddressDetail,value);
  }
  public  void setAddressDetailNull(){
     this.set(S_AddressDetail,null);
  }

  public String getAddressDetail(){
       return DataType.getAsString(this.get(S_AddressDetail));
  
  }
  public String getAddressDetailInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AddressDetail));
      }

  public void initSexCode(String value){
     this.initProperty(S_SexCode,value);
  }
  public  void setSexCode(String value){
     this.set(S_SexCode,value);
  }
  public  void setSexCodeNull(){
     this.set(S_SexCode,null);
  }

  public String getSexCode(){
       return DataType.getAsString(this.get(S_SexCode));
  
  }
  public String getSexCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SexCode));
      }

  public void initAddressAreaCode(String value){
     this.initProperty(S_AddressAreaCode,value);
  }
  public  void setAddressAreaCode(String value){
     this.set(S_AddressAreaCode,value);
  }
  public  void setAddressAreaCodeNull(){
     this.set(S_AddressAreaCode,null);
  }

  public String getAddressAreaCode(){
       return DataType.getAsString(this.get(S_AddressAreaCode));
  
  }
  public String getAddressAreaCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AddressAreaCode));
      }

  public void initOperatorPsw(String value){
     this.initProperty(S_OperatorPsw,value);
  }
  public  void setOperatorPsw(String value){
     this.set(S_OperatorPsw,value);
  }
  public  void setOperatorPswNull(){
     this.set(S_OperatorPsw,null);
  }

  public String getOperatorPsw(){
       return DataType.getAsString(this.get(S_OperatorPsw));
  
  }
  public String getOperatorPswInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorPsw));
      }

  public void initAddressProvCode(String value){
     this.initProperty(S_AddressProvCode,value);
  }
  public  void setAddressProvCode(String value){
     this.set(S_AddressProvCode,value);
  }
  public  void setAddressProvCodeNull(){
     this.set(S_AddressProvCode,null);
  }

  public String getAddressProvCode(){
       return DataType.getAsString(this.get(S_AddressProvCode));
  
  }
  public String getAddressProvCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AddressProvCode));
      }

  public void initAddressCityCode(String value){
     this.initProperty(S_AddressCityCode,value);
  }
  public  void setAddressCityCode(String value){
     this.set(S_AddressCityCode,value);
  }
  public  void setAddressCityCodeNull(){
     this.set(S_AddressCityCode,null);
  }

  public String getAddressCityCode(){
       return DataType.getAsString(this.get(S_AddressCityCode));
  
  }
  public String getAddressCityCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AddressCityCode));
      }

  public void initEmail(String value){
     this.initProperty(S_Email,value);
  }
  public  void setEmail(String value){
     this.set(S_Email,value);
  }
  public  void setEmailNull(){
     this.set(S_Email,null);
  }

  public String getEmail(){
       return DataType.getAsString(this.get(S_Email));
  
  }
  public String getEmailInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Email));
      }

  public void initCreateDate(Timestamp value){
     this.initProperty(S_CreateDate,value);
  }
  public  void setCreateDate(Timestamp value){
     this.set(S_CreateDate,value);
  }
  public  void setCreateDateNull(){
     this.set(S_CreateDate,null);
  }

  public Timestamp getCreateDate(){
        return DataType.getAsDateTime(this.get(S_CreateDate));
  
  }
  public Timestamp getCreateDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CreateDate));
      }

  public void initOperatorState(long value){
     this.initProperty(S_OperatorState,new Long(value));
  }
  public  void setOperatorState(long value){
     this.set(S_OperatorState,new Long(value));
  }
  public  void setOperatorStateNull(){
     this.set(S_OperatorState,null);
  }

  public long getOperatorState(){
        return DataType.getAsLong(this.get(S_OperatorState));
  
  }
  public long getOperatorStateInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OperatorState));
      }


 
 }

