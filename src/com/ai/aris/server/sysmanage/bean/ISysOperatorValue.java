package com.ai.aris.server.sysmanage.bean;

import com.ai.appframe2.common.DataStructInterface;

import java.sql.Timestamp;

public interface ISysOperatorValue extends DataStructInterface {

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


public Timestamp getBirthday();

public long getOperatorId();

public String getRemarks();

public String getTelNo();

public String getCertNo();

public String getPositionCode();

public Timestamp getChangePswDate();

public String getEmpPhoto();

public String getAddressVillageCode();

public String getOperatorName();

public String getPositionLevelCode();

public String getAddressTownCode();

public String getOrgId();

public String getOperatorCode();

public String getAddressDetail();

public String getSexCode();

public String getAddressAreaCode();

public String getOperatorPsw();

public String getAddressProvCode();

public String getAddressCityCode();

public String getEmail();

public Timestamp getCreateDate();

public long getOperatorState();


public  void setBirthday(Timestamp value);

public  void setOperatorId(long value);

public  void setRemarks(String value);

public  void setTelNo(String value);

public  void setCertNo(String value);

public  void setPositionCode(String value);

public  void setChangePswDate(Timestamp value);

public  void setEmpPhoto(String value);

public  void setAddressVillageCode(String value);

public  void setOperatorName(String value);

public  void setPositionLevelCode(String value);

public  void setAddressTownCode(String value);

public  void setOrgId(String value);

public  void setOperatorCode(String value);

public  void setAddressDetail(String value);

public  void setSexCode(String value);

public  void setAddressAreaCode(String value);

public  void setOperatorPsw(String value);

public  void setAddressProvCode(String value);

public  void setAddressCityCode(String value);

public  void setEmail(String value);

public  void setCreateDate(Timestamp value);

public  void setOperatorState(long value);
}
