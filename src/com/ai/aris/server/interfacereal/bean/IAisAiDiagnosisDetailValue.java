package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisAiDiagnosisDetailValue extends DataStructInterface{

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


public long getStudyDetailId();

public long getMarktype();

public String getShapedesc();

public String getSeriesuid();

public String getDescription();

public String getGlobalStudyId();

public long getShape();

public String getDcmnolist();

public long getSeriesno();

public String getNo();


public  void setStudyDetailId(long value);

public  void setMarktype(long value);

public  void setShapedesc(String value);

public  void setSeriesuid(String value);

public  void setDescription(String value);

public  void setGlobalStudyId(String value);

public  void setShape(long value);

public  void setDcmnolist(String value);

public  void setSeriesno(long value);

public  void setNo(String value);
}
