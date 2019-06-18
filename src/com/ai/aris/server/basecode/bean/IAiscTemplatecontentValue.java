package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscTemplatecontentValue extends DataStructInterface{

  public final static  String S_TemplateFq = "TEMPLATE_FQ";
  public final static  String S_TemplateName = "TEMPLATE_NAME";
  public final static  String S_TemplateResult = "TEMPLATE_RESULT";
  public final static  String S_TemplatecontentId = "TEMPLATECONTENT_ID";
  public final static  String S_TemplateMethod = "TEMPLATE_METHOD";
  public final static  String S_TemplateExam = "TEMPLATE_EXAM";
  public final static  String S_TemplatecatId = "TEMPLATECAT_ID";
  public final static  String S_TemplatedirId = "TEMPLATEDIR_ID";


public long getTemplateFq();

public String getTemplateName();

public String getTemplateResult();

public long getTemplatecontentId();

public String getTemplateMethod();

public String getTemplateExam();

public long getTemplatecatId();

public long getTemplatedirId();


public  void setTemplateFq(long value);

public  void setTemplateName(String value);

public  void setTemplateResult(String value);

public  void setTemplatecontentId(long value);

public  void setTemplateMethod(String value);

public  void setTemplateExam(String value);

public  void setTemplatecatId(long value);

public  void setTemplatedirId(long value);
}
