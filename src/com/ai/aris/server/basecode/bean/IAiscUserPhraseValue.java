package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscUserPhraseValue extends DataStructInterface{

  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_PhraseId = "PHRASE_ID";
  public final static  String S_PhraseContent = "PHRASE_CONTENT";


public long getOperatorId();

public long getPhraseId();

public String getPhraseContent();


public  void setOperatorId(long value);

public  void setPhraseId(long value);

public  void setPhraseContent(String value);
}
