package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AisAiDiagnosisDetailBean extends DataContainer implements DataContainerInterface,IAisAiDiagnosisDetailValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AisAiDiagnosisDetail";



  public final static  String S_StudyDetailId = "STUDY_DETAIL_ID";
  public final static  String S_Marktype = "MARKTYPE";
  public final static  String S_Shapedesc = "SHAPEDESC";
  public final static  String S_Seriesuid = "SERIESUID";
  public final static  String S_Description = "DESCRIPTION";
  public final static  String S_GlobalStudyId = "GLOBAL_STUDY_ID";
  public final static  String S_Shape = "SHAPE";
  public final static  String S_Dcmnolist = "DCMNOLIST";
  public final static  String S_Seriesno = "SERIESNO";
  public final static  String S_No = "NO";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisAiDiagnosisDetailBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStudyDetailId(long value){
     this.initProperty(S_StudyDetailId,new Long(value));
  }
  public  void setStudyDetailId(long value){
     this.set(S_StudyDetailId,new Long(value));
  }
  public  void setStudyDetailIdNull(){
     this.set(S_StudyDetailId,null);
  }

  public long getStudyDetailId(){
        return DataType.getAsLong(this.get(S_StudyDetailId));
  
  }
  public long getStudyDetailIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyDetailId));
      }

  public void initMarktype(long value){
     this.initProperty(S_Marktype,new Long(value));
  }
  public  void setMarktype(long value){
     this.set(S_Marktype,new Long(value));
  }
  public  void setMarktypeNull(){
     this.set(S_Marktype,null);
  }

  public long getMarktype(){
        return DataType.getAsLong(this.get(S_Marktype));
  
  }
  public long getMarktypeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Marktype));
      }

  public void initShapedesc(String value){
     this.initProperty(S_Shapedesc,value);
  }
  public  void setShapedesc(String value){
     this.set(S_Shapedesc,value);
  }
  public  void setShapedescNull(){
     this.set(S_Shapedesc,null);
  }

  public String getShapedesc(){
       return DataType.getAsString(this.get(S_Shapedesc));
  
  }
  public String getShapedescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Shapedesc));
      }

  public void initSeriesuid(String value){
     this.initProperty(S_Seriesuid,value);
  }
  public  void setSeriesuid(String value){
     this.set(S_Seriesuid,value);
  }
  public  void setSeriesuidNull(){
     this.set(S_Seriesuid,null);
  }

  public String getSeriesuid(){
       return DataType.getAsString(this.get(S_Seriesuid));
  
  }
  public String getSeriesuidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Seriesuid));
      }

  public void initDescription(String value){
     this.initProperty(S_Description,value);
  }
  public  void setDescription(String value){
     this.set(S_Description,value);
  }
  public  void setDescriptionNull(){
     this.set(S_Description,null);
  }

  public String getDescription(){
       return DataType.getAsString(this.get(S_Description));
  
  }
  public String getDescriptionInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Description));
      }

  public void initGlobalStudyId(String value){
     this.initProperty(S_GlobalStudyId,value);
  }
  public  void setGlobalStudyId(String value){
     this.set(S_GlobalStudyId,value);
  }
  public  void setGlobalStudyIdNull(){
     this.set(S_GlobalStudyId,null);
  }

  public String getGlobalStudyId(){
       return DataType.getAsString(this.get(S_GlobalStudyId));
  
  }
  public String getGlobalStudyIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_GlobalStudyId));
      }

  public void initShape(long value){
     this.initProperty(S_Shape,new Long(value));
  }
  public  void setShape(long value){
     this.set(S_Shape,new Long(value));
  }
  public  void setShapeNull(){
     this.set(S_Shape,null);
  }

  public long getShape(){
        return DataType.getAsLong(this.get(S_Shape));
  
  }
  public long getShapeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Shape));
      }

  public void initDcmnolist(String value){
     this.initProperty(S_Dcmnolist,value);
  }
  public  void setDcmnolist(String value){
     this.set(S_Dcmnolist,value);
  }
  public  void setDcmnolistNull(){
     this.set(S_Dcmnolist,null);
  }

  public String getDcmnolist(){
       return DataType.getAsString(this.get(S_Dcmnolist));
  
  }
  public String getDcmnolistInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Dcmnolist));
      }

  public void initSeriesno(long value){
     this.initProperty(S_Seriesno,new Long(value));
  }
  public  void setSeriesno(long value){
     this.set(S_Seriesno,new Long(value));
  }
  public  void setSeriesnoNull(){
     this.set(S_Seriesno,null);
  }

  public long getSeriesno(){
        return DataType.getAsLong(this.get(S_Seriesno));
  
  }
  public long getSeriesnoInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Seriesno));
      }

  public void initNo(String value){
     this.initProperty(S_No,value);
  }
  public  void setNo(String value){
     this.set(S_No,value);
  }
  public  void setNoNull(){
     this.set(S_No,null);
  }

  public String getNo(){
       return DataType.getAsString(this.get(S_No));
  
  }
  public String getNoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_No));
      }


 
 }

