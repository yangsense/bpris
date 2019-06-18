package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryAiscRuleValue extends DataStructInterface{

  public final static  String S_RulePrix = "RULE_PRIX";
  public final static  String S_RuleId = "RULE_ID";
  public final static  String S_RuleField = "RULE_FIELD";
  public final static  String S_RuleType = "RULE_TYPE";
  public final static  String S_RuleLen = "RULE_LEN";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_RuleStartindex = "RULE_STARTINDEX";
  public final static  String S_OrgId = "ORG_ID";


public String getRulePrix();

public long getRuleId();

public String getRuleField();

public long getRuleType();

public long getRuleLen();

public String getOrgName();

public String getLocDesc();

public long getLocId();

public long getRuleStartindex();

public String getOrgId();


public  void setRulePrix(String value);

public  void setRuleId(long value);

public  void setRuleField(String value);

public  void setRuleType(long value);

public  void setRuleLen(long value);

public  void setOrgName(String value);

public  void setLocDesc(String value);

public  void setLocId(long value);

public  void setRuleStartindex(long value);

public  void setOrgId(String value);
}
