package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AisStudyItemInfoBean extends DataContainer implements DataContainerInterface,IAisStudyItemInfoValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AisStudyItemInfo";



  public final static  String S_StudyitemStatus = "STUDYITEM_STATUS";
  public final static  String S_StudyitemBodypart = "STUDYITEM_BODYPART";
  public final static  String S_StudyitemDesc = "STUDYITEM_DESC";
  public final static  String S_StudyitemNumber = "STUDYITEM_NUMBER";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudyitemHisid = "STUDYITEM_HISID";
  public final static  String S_StudyitemId = "STUDYITEM_ID";
  public final static  String S_StudyitemBodyinfo = "STUDYITEM_BODYINFO";
  public final static  String S_StudyitemEndesc = "STUDYITEM_ENDESC";
  public final static  String S_StudyitemCode = "STUDYITEM_CODE";
  public final static  String S_StudyitemPrice = "STUDYITEM_PRICE";
  public final static  String S_StudyitemItemno = "STUDYITEM_ITEMNO";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisStudyItemInfoBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStudyitemStatus(String value){
     this.initProperty(S_StudyitemStatus,value);
  }
  public  void setStudyitemStatus(String value){
     this.set(S_StudyitemStatus,value);
  }
  public  void setStudyitemStatusNull(){
     this.set(S_StudyitemStatus,null);
  }

  public String getStudyitemStatus(){
       return DataType.getAsString(this.get(S_StudyitemStatus));
  
  }
  public String getStudyitemStatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemStatus));
      }

  public void initStudyitemBodypart(String value){
     this.initProperty(S_StudyitemBodypart,value);
  }
  public  void setStudyitemBodypart(String value){
     this.set(S_StudyitemBodypart,value);
  }
  public  void setStudyitemBodypartNull(){
     this.set(S_StudyitemBodypart,null);
  }

  public String getStudyitemBodypart(){
       return DataType.getAsString(this.get(S_StudyitemBodypart));
  
  }
  public String getStudyitemBodypartInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemBodypart));
      }

  public void initStudyitemDesc(String value){
     this.initProperty(S_StudyitemDesc,value);
  }
  public  void setStudyitemDesc(String value){
     this.set(S_StudyitemDesc,value);
  }
  public  void setStudyitemDescNull(){
     this.set(S_StudyitemDesc,null);
  }

  public String getStudyitemDesc(){
       return DataType.getAsString(this.get(S_StudyitemDesc));
  
  }
  public String getStudyitemDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemDesc));
      }

  public void initStudyitemNumber(int value){
     this.initProperty(S_StudyitemNumber,new Integer(value));
  }
  public  void setStudyitemNumber(int value){
     this.set(S_StudyitemNumber,new Integer(value));
  }
  public  void setStudyitemNumberNull(){
     this.set(S_StudyitemNumber,null);
  }

  public int getStudyitemNumber(){
        return DataType.getAsInt(this.get(S_StudyitemNumber));
  
  }
  public int getStudyitemNumberInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_StudyitemNumber));
      }

  public void initStudyinfoId(long value){
     this.initProperty(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoId(long value){
     this.set(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoIdNull(){
     this.set(S_StudyinfoId,null);
  }

  public long getStudyinfoId(){
        return DataType.getAsLong(this.get(S_StudyinfoId));
  
  }
  public long getStudyinfoIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyinfoId));
      }

  public void initStudyitemHisid(String value){
     this.initProperty(S_StudyitemHisid,value);
  }
  public  void setStudyitemHisid(String value){
     this.set(S_StudyitemHisid,value);
  }
  public  void setStudyitemHisidNull(){
     this.set(S_StudyitemHisid,null);
  }

  public String getStudyitemHisid(){
       return DataType.getAsString(this.get(S_StudyitemHisid));
  
  }
  public String getStudyitemHisidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemHisid));
      }

  public void initStudyitemId(long value){
     this.initProperty(S_StudyitemId,new Long(value));
  }
  public  void setStudyitemId(long value){
     this.set(S_StudyitemId,new Long(value));
  }
  public  void setStudyitemIdNull(){
     this.set(S_StudyitemId,null);
  }

  public long getStudyitemId(){
        return DataType.getAsLong(this.get(S_StudyitemId));
  
  }
  public long getStudyitemIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyitemId));
      }

  public void initStudyitemBodyinfo(String value){
     this.initProperty(S_StudyitemBodyinfo,value);
  }
  public  void setStudyitemBodyinfo(String value){
     this.set(S_StudyitemBodyinfo,value);
  }
  public  void setStudyitemBodyinfoNull(){
     this.set(S_StudyitemBodyinfo,null);
  }

  public String getStudyitemBodyinfo(){
       return DataType.getAsString(this.get(S_StudyitemBodyinfo));
  
  }
  public String getStudyitemBodyinfoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemBodyinfo));
      }

  public void initStudyitemEndesc(String value){
     this.initProperty(S_StudyitemEndesc,value);
  }
  public  void setStudyitemEndesc(String value){
     this.set(S_StudyitemEndesc,value);
  }
  public  void setStudyitemEndescNull(){
     this.set(S_StudyitemEndesc,null);
  }

  public String getStudyitemEndesc(){
       return DataType.getAsString(this.get(S_StudyitemEndesc));
  
  }
  public String getStudyitemEndescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemEndesc));
      }

  public void initStudyitemCode(String value){
     this.initProperty(S_StudyitemCode,value);
  }
  public  void setStudyitemCode(String value){
     this.set(S_StudyitemCode,value);
  }
  public  void setStudyitemCodeNull(){
     this.set(S_StudyitemCode,null);
  }

  public String getStudyitemCode(){
       return DataType.getAsString(this.get(S_StudyitemCode));
  
  }
  public String getStudyitemCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemCode));
      }

  public void initStudyitemPrice(float value){
     this.initProperty(S_StudyitemPrice,new Float(value));
  }
  public  void setStudyitemPrice(float value){
     this.set(S_StudyitemPrice,new Float(value));
  }
  public  void setStudyitemPriceNull(){
     this.set(S_StudyitemPrice,null);
  }

  public float getStudyitemPrice(){
        return DataType.getAsFloat(this.get(S_StudyitemPrice));
  
  }
  public float getStudyitemPriceInitialValue(){
        return DataType.getAsFloat(this.getOldObj(S_StudyitemPrice));
      }

  public void initStudyitemItemno(String value){
     this.initProperty(S_StudyitemItemno,value);
  }
  public  void setStudyitemItemno(String value){
     this.set(S_StudyitemItemno,value);
  }
  public  void setStudyitemItemnoNull(){
     this.set(S_StudyitemItemno,null);
  }

  public String getStudyitemItemno(){
       return DataType.getAsString(this.get(S_StudyitemItemno));
  
  }
  public String getStudyitemItemnoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemItemno));
      }


 
 }

