package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQuerySysOrgDunsValue extends DataStructInterface{

  public final static  String S_Duns = "DUNS";


public String getDuns();


public  void setDuns(String value);
}
