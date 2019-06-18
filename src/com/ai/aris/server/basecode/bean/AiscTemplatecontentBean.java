package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscTemplatecontentBean extends DataContainer implements DataContainerInterface,IAiscTemplatecontentValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscTemplatecontent";



  public final static  String S_TemplateFq = "TEMPLATE_FQ";
  public final static  String S_TemplateName = "TEMPLATE_NAME";
  public final static  String S_TemplateResult = "TEMPLATE_RESULT";
  public final static  String S_TemplatecontentId = "TEMPLATECONTENT_ID";
  public final static  String S_TemplateMethod = "TEMPLATE_METHOD";
  public final static  String S_TemplateExam = "TEMPLATE_EXAM";
  public final static  String S_TemplatecatId = "TEMPLATECAT_ID";
  public final static  String S_TemplatedirId = "TEMPLATEDIR_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscTemplatecontentBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initTemplateFq(long value){
     this.initProperty(S_TemplateFq,new Long(value));
  }
  public  void setTemplateFq(long value){
     this.set(S_TemplateFq,new Long(value));
  }
  public  void setTemplateFqNull(){
     this.set(S_TemplateFq,null);
  }

  public long getTemplateFq(){
        return DataType.getAsLong(this.get(S_TemplateFq));
  
  }
  public long getTemplateFqInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_TemplateFq));
      }

  public void initTemplateName(String value){
     this.initProperty(S_TemplateName,value);
  }
  public  void setTemplateName(String value){
     this.set(S_TemplateName,value);
  }
  public  void setTemplateNameNull(){
     this.set(S_TemplateName,null);
  }

  public String getTemplateName(){
       return DataType.getAsString(this.get(S_TemplateName));
  
  }
  public String getTemplateNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_TemplateName));
      }

  public void initTemplateResult(String value){
     this.initProperty(S_TemplateResult,value);
  }
  public  void setTemplateResult(String value){
     this.set(S_TemplateResult,value);
  }
  public  void setTemplateResultNull(){
     this.set(S_TemplateResult,null);
  }

  public String getTemplateResult(){
       return DataType.getAsString(this.get(S_TemplateResult));
  
  }
  public String getTemplateResultInitialValue(){
        return DataType.getAsString(this.getOldObj(S_TemplateResult));
      }

  public void initTemplatecontentId(long value){
     this.initProperty(S_TemplatecontentId,new Long(value));
  }
  public  void setTemplatecontentId(long value){
     this.set(S_TemplatecontentId,new Long(value));
  }
  public  void setTemplatecontentIdNull(){
     this.set(S_TemplatecontentId,null);
  }

  public long getTemplatecontentId(){
        return DataType.getAsLong(this.get(S_TemplatecontentId));
  
  }
  public long getTemplatecontentIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_TemplatecontentId));
      }

  public void initTemplateMethod(String value){
     this.initProperty(S_TemplateMethod,value);
  }
  public  void setTemplateMethod(String value){
     this.set(S_TemplateMethod,value);
  }
  public  void setTemplateMethodNull(){
     this.set(S_TemplateMethod,null);
  }

  public String getTemplateMethod(){
       return DataType.getAsString(this.get(S_TemplateMethod));
  
  }
  public String getTemplateMethodInitialValue(){
        return DataType.getAsString(this.getOldObj(S_TemplateMethod));
      }

  public void initTemplateExam(String value){
     this.initProperty(S_TemplateExam,value);
  }
  public  void setTemplateExam(String value){
     this.set(S_TemplateExam,value);
  }
  public  void setTemplateExamNull(){
     this.set(S_TemplateExam,null);
  }

  public String getTemplateExam(){
       return DataType.getAsString(this.get(S_TemplateExam));
  
  }
  public String getTemplateExamInitialValue(){
        return DataType.getAsString(this.getOldObj(S_TemplateExam));
      }

  public void initTemplatecatId(long value){
     this.initProperty(S_TemplatecatId,new Long(value));
  }
  public  void setTemplatecatId(long value){
     this.set(S_TemplatecatId,new Long(value));
  }
  public  void setTemplatecatIdNull(){
     this.set(S_TemplatecatId,null);
  }

  public long getTemplatecatId(){
        return DataType.getAsLong(this.get(S_TemplatecatId));
  
  }
  public long getTemplatecatIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_TemplatecatId));
      }

  public void initTemplatedirId(long value){
     this.initProperty(S_TemplatedirId,new Long(value));
  }
  public  void setTemplatedirId(long value){
     this.set(S_TemplatedirId,new Long(value));
  }
  public  void setTemplatedirIdNull(){
     this.set(S_TemplatedirId,null);
  }

  public long getTemplatedirId(){
        return DataType.getAsLong(this.get(S_TemplatedirId));
  
  }
  public long getTemplatedirIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_TemplatedirId));
      }


 
 }

