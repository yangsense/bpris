package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisKnowledgebaseValue extends DataStructInterface{

  public final static  String S_CollectorCode = "COLLECTOR_CODE";
  public final static  String S_OperateDatetime = "OPERATE_DATETIME";
  public final static  String S_Keydesc = "KEYDESC";
  public final static  String S_Casegroupdesc = "CASEGROUPDESC";
  public final static  String S_ReportId = "REPORT_ID";
  public final static  String S_Knowledgebaseid = "KNOWLEDGEBASEID";


public String getCollectorCode();

public Timestamp getOperateDatetime();

public String getKeydesc();

public String getCasegroupdesc();

public long getReportId();

public long getKnowledgebaseid();


public  void setCollectorCode(String value);

public  void setOperateDatetime(Timestamp value);

public  void setKeydesc(String value);

public  void setCasegroupdesc(String value);

public  void setReportId(long value);

public  void setKnowledgebaseid(long value);
}
