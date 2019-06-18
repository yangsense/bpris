package com.ai.aris.server.sysmanage.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.sysmanage.bean.*;

public class QueryOrgTreeBean extends DataContainer implements DataContainerInterface,IQueryOrgTreeValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.QueryOrgTree";



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

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QueryOrgTreeBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initWebUrl(String value){
     this.initProperty(S_WebUrl,value);
  }
  public  void setWebUrl(String value){
     this.set(S_WebUrl,value);
  }
  public  void setWebUrlNull(){
     this.set(S_WebUrl,null);
  }

  public String getWebUrl(){
       return DataType.getAsString(this.get(S_WebUrl));
  
  }
  public String getWebUrlInitialValue(){
        return DataType.getAsString(this.getOldObj(S_WebUrl));
      }

  public void initState(String value){
     this.initProperty(S_State,value);
  }
  public  void setState(String value){
     this.set(S_State,value);
  }
  public  void setStateNull(){
     this.set(S_State,null);
  }

  public String getState(){
       return DataType.getAsString(this.get(S_State));
  
  }
  public String getStateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_State));
      }

  public void initSetupTime(String value){
     this.initProperty(S_SetupTime,value);
  }
  public  void setSetupTime(String value){
     this.set(S_SetupTime,value);
  }
  public  void setSetupTimeNull(){
     this.set(S_SetupTime,null);
  }

  public String getSetupTime(){
       return DataType.getAsString(this.get(S_SetupTime));
  
  }
  public String getSetupTimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SetupTime));
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

  public void initCreateTime(Timestamp value){
     this.initProperty(S_CreateTime,value);
  }
  public  void setCreateTime(Timestamp value){
     this.set(S_CreateTime,value);
  }
  public  void setCreateTimeNull(){
     this.set(S_CreateTime,null);
  }

  public Timestamp getCreateTime(){
        return DataType.getAsDateTime(this.get(S_CreateTime));
  
  }
  public Timestamp getCreateTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CreateTime));
      }

  public void initContactName(String value){
     this.initProperty(S_ContactName,value);
  }
  public  void setContactName(String value){
     this.set(S_ContactName,value);
  }
  public  void setContactNameNull(){
     this.set(S_ContactName,null);
  }

  public String getContactName(){
       return DataType.getAsString(this.get(S_ContactName));
  
  }
  public String getContactNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ContactName));
      }

  public void initVillageCode(String value){
     this.initProperty(S_VillageCode,value);
  }
  public  void setVillageCode(String value){
     this.set(S_VillageCode,value);
  }
  public  void setVillageCodeNull(){
     this.set(S_VillageCode,null);
  }

  public String getVillageCode(){
       return DataType.getAsString(this.get(S_VillageCode));
  
  }
  public String getVillageCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_VillageCode));
      }

  public void initOrgType(String value){
     this.initProperty(S_OrgType,value);
  }
  public  void setOrgType(String value){
     this.set(S_OrgType,value);
  }
  public  void setOrgTypeNull(){
     this.set(S_OrgType,null);
  }

  public String getOrgType(){
       return DataType.getAsString(this.get(S_OrgType));
  
  }
  public String getOrgTypeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgType));
      }

  public void initLawPersonTel(String value){
     this.initProperty(S_LawPersonTel,value);
  }
  public  void setLawPersonTel(String value){
     this.set(S_LawPersonTel,value);
  }
  public  void setLawPersonTelNull(){
     this.set(S_LawPersonTel,null);
  }

  public String getLawPersonTel(){
       return DataType.getAsString(this.get(S_LawPersonTel));
  
  }
  public String getLawPersonTelInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LawPersonTel));
      }

  public void initChildren(long value){
     this.initProperty(S_Children,new Long(value));
  }
  public  void setChildren(long value){
     this.set(S_Children,new Long(value));
  }
  public  void setChildrenNull(){
     this.set(S_Children,null);
  }

  public long getChildren(){
        return DataType.getAsLong(this.get(S_Children));
  
  }
  public long getChildrenInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Children));
      }

  public void initBranchMark(String value){
     this.initProperty(S_BranchMark,value);
  }
  public  void setBranchMark(String value){
     this.set(S_BranchMark,value);
  }
  public  void setBranchMarkNull(){
     this.set(S_BranchMark,null);
  }

  public String getBranchMark(){
       return DataType.getAsString(this.get(S_BranchMark));
  
  }
  public String getBranchMarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BranchMark));
      }

  public void initYbMark(String value){
     this.initProperty(S_YbMark,value);
  }
  public  void setYbMark(String value){
     this.set(S_YbMark,value);
  }
  public  void setYbMarkNull(){
     this.set(S_YbMark,null);
  }

  public String getYbMark(){
       return DataType.getAsString(this.get(S_YbMark));
  
  }
  public String getYbMarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_YbMark));
      }

  public void initPostcode(String value){
     this.initProperty(S_Postcode,value);
  }
  public  void setPostcode(String value){
     this.set(S_Postcode,value);
  }
  public  void setPostcodeNull(){
     this.set(S_Postcode,null);
  }

  public String getPostcode(){
       return DataType.getAsString(this.get(S_Postcode));
  
  }
  public String getPostcodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Postcode));
      }

  public void initEconomicTypeCode(String value){
     this.initProperty(S_EconomicTypeCode,value);
  }
  public  void setEconomicTypeCode(String value){
     this.set(S_EconomicTypeCode,value);
  }
  public  void setEconomicTypeCodeNull(){
     this.set(S_EconomicTypeCode,null);
  }

  public String getEconomicTypeCode(){
       return DataType.getAsString(this.get(S_EconomicTypeCode));
  
  }
  public String getEconomicTypeCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EconomicTypeCode));
      }

  public void initOrgClassLevel(String value){
     this.initProperty(S_OrgClassLevel,value);
  }
  public  void setOrgClassLevel(String value){
     this.set(S_OrgClassLevel,value);
  }
  public  void setOrgClassLevelNull(){
     this.set(S_OrgClassLevel,null);
  }

  public String getOrgClassLevel(){
       return DataType.getAsString(this.get(S_OrgClassLevel));
  
  }
  public String getOrgClassLevelInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgClassLevel));
      }

  public void initOrgLevel(long value){
     this.initProperty(S_OrgLevel,new Long(value));
  }
  public  void setOrgLevel(long value){
     this.set(S_OrgLevel,new Long(value));
  }
  public  void setOrgLevelNull(){
     this.set(S_OrgLevel,null);
  }

  public long getOrgLevel(){
        return DataType.getAsLong(this.get(S_OrgLevel));
  
  }
  public long getOrgLevelInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrgLevel));
      }

  public void initContactTel(String value){
     this.initProperty(S_ContactTel,value);
  }
  public  void setContactTel(String value){
     this.set(S_ContactTel,value);
  }
  public  void setContactTelNull(){
     this.set(S_ContactTel,null);
  }

  public String getContactTel(){
       return DataType.getAsString(this.get(S_ContactTel));
  
  }
  public String getContactTelInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ContactTel));
      }

  public void initXnhMark(String value){
     this.initProperty(S_XnhMark,value);
  }
  public  void setXnhMark(String value){
     this.set(S_XnhMark,value);
  }
  public  void setXnhMarkNull(){
     this.set(S_XnhMark,null);
  }

  public String getXnhMark(){
       return DataType.getAsString(this.get(S_XnhMark));
  
  }
  public String getXnhMarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_XnhMark));
      }

  public void initOrgAddr(String value){
     this.initProperty(S_OrgAddr,value);
  }
  public  void setOrgAddr(String value){
     this.set(S_OrgAddr,value);
  }
  public  void setOrgAddrNull(){
     this.set(S_OrgAddr,null);
  }

  public String getOrgAddr(){
       return DataType.getAsString(this.get(S_OrgAddr));
  
  }
  public String getOrgAddrInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgAddr));
      }

  public void initOrgTypeFirst(String value){
     this.initProperty(S_OrgTypeFirst,value);
  }
  public  void setOrgTypeFirst(String value){
     this.set(S_OrgTypeFirst,value);
  }
  public  void setOrgTypeFirstNull(){
     this.set(S_OrgTypeFirst,null);
  }

  public String getOrgTypeFirst(){
       return DataType.getAsString(this.get(S_OrgTypeFirst));
  
  }
  public String getOrgTypeFirstInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgTypeFirst));
      }

  public void initLawPersonName(String value){
     this.initProperty(S_LawPersonName,value);
  }
  public  void setLawPersonName(String value){
     this.set(S_LawPersonName,value);
  }
  public  void setLawPersonNameNull(){
     this.set(S_LawPersonName,null);
  }

  public String getLawPersonName(){
       return DataType.getAsString(this.get(S_LawPersonName));
  
  }
  public String getLawPersonNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LawPersonName));
      }

  public void initOrgHead(String value){
     this.initProperty(S_OrgHead,value);
  }
  public  void setOrgHead(String value){
     this.set(S_OrgHead,value);
  }
  public  void setOrgHeadNull(){
     this.set(S_OrgHead,null);
  }

  public String getOrgHead(){
       return DataType.getAsString(this.get(S_OrgHead));
  
  }
  public String getOrgHeadInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgHead));
      }

  public void initManagementCode(String value){
     this.initProperty(S_ManagementCode,value);
  }
  public  void setManagementCode(String value){
     this.set(S_ManagementCode,value);
  }
  public  void setManagementCodeNull(){
     this.set(S_ManagementCode,null);
  }

  public String getManagementCode(){
       return DataType.getAsString(this.get(S_ManagementCode));
  
  }
  public String getManagementCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ManagementCode));
      }

  public void initOrgIntroduct(String value){
     this.initProperty(S_OrgIntroduct,value);
  }
  public  void setOrgIntroduct(String value){
     this.set(S_OrgIntroduct,value);
  }
  public  void setOrgIntroductNull(){
     this.set(S_OrgIntroduct,null);
  }

  public String getOrgIntroduct(){
       return DataType.getAsString(this.get(S_OrgIntroduct));
  
  }
  public String getOrgIntroductInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgIntroduct));
      }

  public void initOrgTypeThird(String value){
     this.initProperty(S_OrgTypeThird,value);
  }
  public  void setOrgTypeThird(String value){
     this.set(S_OrgTypeThird,value);
  }
  public  void setOrgTypeThirdNull(){
     this.set(S_OrgTypeThird,null);
  }

  public String getOrgTypeThird(){
       return DataType.getAsString(this.get(S_OrgTypeThird));
  
  }
  public String getOrgTypeThirdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgTypeThird));
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

  public void initDistrictCode(String value){
     this.initProperty(S_DistrictCode,value);
  }
  public  void setDistrictCode(String value){
     this.set(S_DistrictCode,value);
  }
  public  void setDistrictCodeNull(){
     this.set(S_DistrictCode,null);
  }

  public String getDistrictCode(){
       return DataType.getAsString(this.get(S_DistrictCode));
  
  }
  public String getDistrictCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_DistrictCode));
      }

  public void initCreateOrgCode(String value){
     this.initProperty(S_CreateOrgCode,value);
  }
  public  void setCreateOrgCode(String value){
     this.set(S_CreateOrgCode,value);
  }
  public  void setCreateOrgCodeNull(){
     this.set(S_CreateOrgCode,null);
  }

  public String getCreateOrgCode(){
       return DataType.getAsString(this.get(S_CreateOrgCode));
  
  }
  public String getCreateOrgCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CreateOrgCode));
      }

  public void initProvinceCode(String value){
     this.initProperty(S_ProvinceCode,value);
  }
  public  void setProvinceCode(String value){
     this.set(S_ProvinceCode,value);
  }
  public  void setProvinceCodeNull(){
     this.set(S_ProvinceCode,null);
  }

  public String getProvinceCode(){
       return DataType.getAsString(this.get(S_ProvinceCode));
  
  }
  public String getProvinceCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ProvinceCode));
      }

  public void initOrgHeadTelephone(String value){
     this.initProperty(S_OrgHeadTelephone,value);
  }
  public  void setOrgHeadTelephone(String value){
     this.set(S_OrgHeadTelephone,value);
  }
  public  void setOrgHeadTelephoneNull(){
     this.set(S_OrgHeadTelephone,null);
  }

  public String getOrgHeadTelephone(){
       return DataType.getAsString(this.get(S_OrgHeadTelephone));
  
  }
  public String getOrgHeadTelephoneInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgHeadTelephone));
      }

  public void initCityCode(String value){
     this.initProperty(S_CityCode,value);
  }
  public  void setCityCode(String value){
     this.set(S_CityCode,value);
  }
  public  void setCityCodeNull(){
     this.set(S_CityCode,null);
  }

  public String getCityCode(){
       return DataType.getAsString(this.get(S_CityCode));
  
  }
  public String getCityCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CityCode));
      }

  public void initOrgClassGrade(String value){
     this.initProperty(S_OrgClassGrade,value);
  }
  public  void setOrgClassGrade(String value){
     this.set(S_OrgClassGrade,value);
  }
  public  void setOrgClassGradeNull(){
     this.set(S_OrgClassGrade,null);
  }

  public String getOrgClassGrade(){
       return DataType.getAsString(this.get(S_OrgClassGrade));
  
  }
  public String getOrgClassGradeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgClassGrade));
      }

  public void initStreetCode(String value){
     this.initProperty(S_StreetCode,value);
  }
  public  void setStreetCode(String value){
     this.set(S_StreetCode,value);
  }
  public  void setStreetCodeNull(){
     this.set(S_StreetCode,null);
  }

  public String getStreetCode(){
       return DataType.getAsString(this.get(S_StreetCode));
  
  }
  public String getStreetCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StreetCode));
      }

  public void initCreateUserId(String value){
     this.initProperty(S_CreateUserId,value);
  }
  public  void setCreateUserId(String value){
     this.set(S_CreateUserId,value);
  }
  public  void setCreateUserIdNull(){
     this.set(S_CreateUserId,null);
  }

  public String getCreateUserId(){
       return DataType.getAsString(this.get(S_CreateUserId));
  
  }
  public String getCreateUserIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CreateUserId));
      }

  public void initUpdateTime(Timestamp value){
     this.initProperty(S_UpdateTime,value);
  }
  public  void setUpdateTime(Timestamp value){
     this.set(S_UpdateTime,value);
  }
  public  void setUpdateTimeNull(){
     this.set(S_UpdateTime,null);
  }

  public Timestamp getUpdateTime(){
        return DataType.getAsDateTime(this.get(S_UpdateTime));
  
  }
  public Timestamp getUpdateTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_UpdateTime));
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

  public void initOrgTypeSecond(String value){
     this.initProperty(S_OrgTypeSecond,value);
  }
  public  void setOrgTypeSecond(String value){
     this.set(S_OrgTypeSecond,value);
  }
  public  void setOrgTypeSecondNull(){
     this.set(S_OrgTypeSecond,null);
  }

  public String getOrgTypeSecond(){
       return DataType.getAsString(this.get(S_OrgTypeSecond));
  
  }
  public String getOrgTypeSecondInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgTypeSecond));
      }

  public void initSubordinateDivisionCode(String value){
     this.initProperty(S_SubordinateDivisionCode,value);
  }
  public  void setSubordinateDivisionCode(String value){
     this.set(S_SubordinateDivisionCode,value);
  }
  public  void setSubordinateDivisionCodeNull(){
     this.set(S_SubordinateDivisionCode,null);
  }

  public String getSubordinateDivisionCode(){
       return DataType.getAsString(this.get(S_SubordinateDivisionCode));
  
  }
  public String getSubordinateDivisionCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SubordinateDivisionCode));
      }

  public void initSubordinateRelationshipCode(String value){
     this.initProperty(S_SubordinateRelationshipCode,value);
  }
  public  void setSubordinateRelationshipCode(String value){
     this.set(S_SubordinateRelationshipCode,value);
  }
  public  void setSubordinateRelationshipCodeNull(){
     this.set(S_SubordinateRelationshipCode,null);
  }

  public String getSubordinateRelationshipCode(){
       return DataType.getAsString(this.get(S_SubordinateRelationshipCode));
  
  }
  public String getSubordinateRelationshipCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SubordinateRelationshipCode));
      }

  public void initDuns(String value){
     this.initProperty(S_Duns,value);
  }
  public  void setDuns(String value){
     this.set(S_Duns,value);
  }
  public  void setDunsNull(){
     this.set(S_Duns,null);
  }

  public String getDuns(){
       return DataType.getAsString(this.get(S_Duns));
  
  }
  public String getDunsInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Duns));
      }

  public void initUpdateUserId(String value){
     this.initProperty(S_UpdateUserId,value);
  }
  public  void setUpdateUserId(String value){
     this.set(S_UpdateUserId,value);
  }
  public  void setUpdateUserIdNull(){
     this.set(S_UpdateUserId,null);
  }

  public String getUpdateUserId(){
       return DataType.getAsString(this.get(S_UpdateUserId));
  
  }
  public String getUpdateUserIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_UpdateUserId));
      }

  public void initFaxNo(String value){
     this.initProperty(S_FaxNo,value);
  }
  public  void setFaxNo(String value){
     this.set(S_FaxNo,value);
  }
  public  void setFaxNoNull(){
     this.set(S_FaxNo,null);
  }

  public String getFaxNo(){
       return DataType.getAsString(this.get(S_FaxNo));
  
  }
  public String getFaxNoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_FaxNo));
      }

  public void initOrganizerTypeCode(String value){
     this.initProperty(S_OrganizerTypeCode,value);
  }
  public  void setOrganizerTypeCode(String value){
     this.set(S_OrganizerTypeCode,value);
  }
  public  void setOrganizerTypeCodeNull(){
     this.set(S_OrganizerTypeCode,null);
  }

  public String getOrganizerTypeCode(){
       return DataType.getAsString(this.get(S_OrganizerTypeCode));
  
  }
  public String getOrganizerTypeCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrganizerTypeCode));
      }

  public void initParentOrgId(String value){
     this.initProperty(S_ParentOrgId,value);
  }
  public  void setParentOrgId(String value){
     this.set(S_ParentOrgId,value);
  }
  public  void setParentOrgIdNull(){
     this.set(S_ParentOrgId,null);
  }

  public String getParentOrgId(){
       return DataType.getAsString(this.get(S_ParentOrgId));
  
  }
  public String getParentOrgIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ParentOrgId));
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

  public void initOrgShort(String value){
     this.initProperty(S_OrgShort,value);
  }
  public  void setOrgShort(String value){
     this.set(S_OrgShort,value);
  }
  public  void setOrgShortNull(){
     this.set(S_OrgShort,null);
  }

  public String getOrgShort(){
       return DataType.getAsString(this.get(S_OrgShort));
  
  }
  public String getOrgShortInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgShort));
      }


 
 }

