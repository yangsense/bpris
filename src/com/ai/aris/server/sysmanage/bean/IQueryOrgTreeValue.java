package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQueryOrgTreeValue extends DataStructInterface{

  public final static  String S_WebUrl = "WEB_URL";
  public final static  String S_State = "STATE";
  public final static  String S_SetupTime = "SETUP_TIME";
  public final static  String S_Remarks = "REMARKS";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_ContactName = "CONTACT_NAME";
  public final static  String S_VillageCode = "VILLAGE_CODE";
  public final static  String S_OrgType = "ORG_TYPE";
  public final static  String S_LawPersonTel = "LAW_PERSON_TEL";
  public final static  String S_Children = "CHILDREN";
  public final static  String S_BranchMark = "BRANCH_MARK";
  public final static  String S_YbMark = "YB_MARK";
  public final static  String S_Postcode = "POSTCODE";
  public final static  String S_EconomicTypeCode = "ECONOMIC_TYPE_CODE";
  public final static  String S_OrgClassLevel = "ORG_CLASS_LEVEL";
  public final static  String S_OrgLevel = "ORG_LEVEL";
  public final static  String S_ContactTel = "CONTACT_TEL";
  public final static  String S_XnhMark = "XNH_MARK";
  public final static  String S_OrgAddr = "ORG_ADDR";
  public final static  String S_OrgTypeFirst = "ORG_TYPE_FIRST";
  public final static  String S_LawPersonName = "LAW_PERSON_NAME";
  public final static  String S_OrgHead = "ORG_HEAD";
  public final static  String S_ManagementCode = "MANAGEMENT_CODE";
  public final static  String S_OrgIntroduct = "ORG_INTRODUCT";
  public final static  String S_OrgTypeThird = "ORG_TYPE_THIRD";
  public final static  String S_Email = "EMAIL";
  public final static  String S_DistrictCode = "DISTRICT_CODE";
  public final static  String S_CreateOrgCode = "CREATE_ORG_CODE";
  public final static  String S_ProvinceCode = "PROVINCE_CODE";
  public final static  String S_OrgHeadTelephone = "ORG_HEAD_TELEPHONE";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_OrgClassGrade = "ORG_CLASS_GRADE";
  public final static  String S_StreetCode = "STREET_CODE";
  public final static  String S_CreateUserId = "CREATE_USER_ID";
  public final static  String S_UpdateTime = "UPDATE_TIME";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OrgTypeSecond = "ORG_TYPE_SECOND";
  public final static  String S_SubordinateDivisionCode = "SUBORDINATE_DIVISION_CODE";
  public final static  String S_SubordinateRelationshipCode = "SUBORDINATE_RELATIONSHIP_CODE";
  public final static  String S_Duns = "DUNS";
  public final static  String S_UpdateUserId = "UPDATE_USER_ID";
  public final static  String S_FaxNo = "FAX_NO";
  public final static  String S_OrganizerTypeCode = "ORGANIZER_TYPE_CODE";
  public final static  String S_ParentOrgId = "PARENT_ORG_ID";
  public final static  String S_OrgCode = "ORG_CODE";
  public final static  String S_OrgShort = "ORG_SHORT";


public String getWebUrl();

public String getState();

public String getSetupTime();

public String getRemarks();

public String getOrgName();

public Timestamp getCreateTime();

public String getContactName();

public String getVillageCode();

public String getOrgType();

public String getLawPersonTel();

public long getChildren();

public String getBranchMark();

public String getYbMark();

public String getPostcode();

public String getEconomicTypeCode();

public String getOrgClassLevel();

public long getOrgLevel();

public String getContactTel();

public String getXnhMark();

public String getOrgAddr();

public String getOrgTypeFirst();

public String getLawPersonName();

public String getOrgHead();

public String getManagementCode();

public String getOrgIntroduct();

public String getOrgTypeThird();

public String getEmail();

public String getDistrictCode();

public String getCreateOrgCode();

public String getProvinceCode();

public String getOrgHeadTelephone();

public String getCityCode();

public String getOrgClassGrade();

public String getStreetCode();

public String getCreateUserId();

public Timestamp getUpdateTime();

public String getOrgId();

public String getOrgTypeSecond();

public String getSubordinateDivisionCode();

public String getSubordinateRelationshipCode();

public String getDuns();

public String getUpdateUserId();

public String getFaxNo();

public String getOrganizerTypeCode();

public String getParentOrgId();

public String getOrgCode();

public String getOrgShort();


public  void setWebUrl(String value);

public  void setState(String value);

public  void setSetupTime(String value);

public  void setRemarks(String value);

public  void setOrgName(String value);

public  void setCreateTime(Timestamp value);

public  void setContactName(String value);

public  void setVillageCode(String value);

public  void setOrgType(String value);

public  void setLawPersonTel(String value);

public  void setChildren(long value);

public  void setBranchMark(String value);

public  void setYbMark(String value);

public  void setPostcode(String value);

public  void setEconomicTypeCode(String value);

public  void setOrgClassLevel(String value);

public  void setOrgLevel(long value);

public  void setContactTel(String value);

public  void setXnhMark(String value);

public  void setOrgAddr(String value);

public  void setOrgTypeFirst(String value);

public  void setLawPersonName(String value);

public  void setOrgHead(String value);

public  void setManagementCode(String value);

public  void setOrgIntroduct(String value);

public  void setOrgTypeThird(String value);

public  void setEmail(String value);

public  void setDistrictCode(String value);

public  void setCreateOrgCode(String value);

public  void setProvinceCode(String value);

public  void setOrgHeadTelephone(String value);

public  void setCityCode(String value);

public  void setOrgClassGrade(String value);

public  void setStreetCode(String value);

public  void setCreateUserId(String value);

public  void setUpdateTime(Timestamp value);

public  void setOrgId(String value);

public  void setOrgTypeSecond(String value);

public  void setSubordinateDivisionCode(String value);

public  void setSubordinateRelationshipCode(String value);

public  void setDuns(String value);

public  void setUpdateUserId(String value);

public  void setFaxNo(String value);

public  void setOrganizerTypeCode(String value);

public  void setParentOrgId(String value);

public  void setOrgCode(String value);

public  void setOrgShort(String value);
}
