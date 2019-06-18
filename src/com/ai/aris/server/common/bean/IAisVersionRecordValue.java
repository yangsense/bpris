package com.ai.aris.server.common.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisVersionRecordValue extends DataStructInterface{

  public final static  String S_VersionBelongOrgcode = "VERSION_BELONG_ORGCODE";
  public final static  String S_UpdateDate = "UPDATE_DATE";
  public final static  String S_VersionBelongOrgname = "VERSION_BELONG_ORGNAME";
  public final static  String S_Id = "ID";
  public final static  String S_VersionNo = "VERSION_NO";
  public final static  String S_VersionRemark = "VERSION_REMARK";


public String getVersionBelongOrgcode();

public Timestamp getUpdateDate();

public String getVersionBelongOrgname();

public String getId();

public String getVersionNo();

public String getVersionRemark();


public  void setVersionBelongOrgcode(String value);

public  void setUpdateDate(Timestamp value);

public  void setVersionBelongOrgname(String value);

public  void setId(String value);

public  void setVersionNo(String value);

public  void setVersionRemark(String value);
}
