package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscUserPhraseBean extends DataContainer implements DataContainerInterface,IAiscUserPhraseValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscUserPhrase";



  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_PhraseId = "PHRASE_ID";
  public final static  String S_PhraseContent = "PHRASE_CONTENT";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscUserPhraseBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOperatorId(long value){
     this.initProperty(S_OperatorId,new Long(value));
  }
  public  void setOperatorId(long value){
     this.set(S_OperatorId,new Long(value));
  }
  public  void setOperatorIdNull(){
     this.set(S_OperatorId,null);
  }

  public long getOperatorId(){
        return DataType.getAsLong(this.get(S_OperatorId));
  
  }
  public long getOperatorIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OperatorId));
      }

  public void initPhraseId(long value){
     this.initProperty(S_PhraseId,new Long(value));
  }
  public  void setPhraseId(long value){
     this.set(S_PhraseId,new Long(value));
  }
  public  void setPhraseIdNull(){
     this.set(S_PhraseId,null);
  }

  public long getPhraseId(){
        return DataType.getAsLong(this.get(S_PhraseId));
  
  }
  public long getPhraseIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_PhraseId));
      }

  public void initPhraseContent(String value){
     this.initProperty(S_PhraseContent,value);
  }
  public  void setPhraseContent(String value){
     this.set(S_PhraseContent,value);
  }
  public  void setPhraseContentNull(){
     this.set(S_PhraseContent,null);
  }

  public String getPhraseContent(){
       return DataType.getAsString(this.get(S_PhraseContent));
  
  }
  public String getPhraseContentInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PhraseContent));
      }


 
 }

