package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscTemplatecatBean extends DataContainer implements DataContainerInterface,IAiscTemplatecatValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscTemplatecat";



  public final static  String S_TemplatecatId = "TEMPLATECAT_ID";
  public final static  String S_TemplatecatDesc = "TEMPLATECAT_DESC";
  public final static  String S_TemplatecatType = "TEMPLATECAT_TYPE";
  public final static  String S_TemplatecatEnabled = "TEMPLATECAT_ENABLED";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscTemplatecatBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initTemplatecatDesc(String value){
     this.initProperty(S_TemplatecatDesc,value);
  }
  public  void setTemplatecatDesc(String value){
     this.set(S_TemplatecatDesc,value);
  }
  public  void setTemplatecatDescNull(){
     this.set(S_TemplatecatDesc,null);
  }

  public String getTemplatecatDesc(){
       return DataType.getAsString(this.get(S_TemplatecatDesc));
  
  }
  public String getTemplatecatDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_TemplatecatDesc));
      }

  public void initTemplatecatType(int value){
     this.initProperty(S_TemplatecatType,new Integer(value));
  }
  public  void setTemplatecatType(int value){
     this.set(S_TemplatecatType,new Integer(value));
  }
  public  void setTemplatecatTypeNull(){
     this.set(S_TemplatecatType,null);
  }

  public int getTemplatecatType(){
        return DataType.getAsInt(this.get(S_TemplatecatType));
  
  }
  public int getTemplatecatTypeInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_TemplatecatType));
      }

  public void initTemplatecatEnabled(int value){
     this.initProperty(S_TemplatecatEnabled,new Integer(value));
  }
  public  void setTemplatecatEnabled(int value){
     this.set(S_TemplatecatEnabled,new Integer(value));
  }
  public  void setTemplatecatEnabledNull(){
     this.set(S_TemplatecatEnabled,null);
  }

  public int getTemplatecatEnabled(){
        return DataType.getAsInt(this.get(S_TemplatecatEnabled));
  
  }
  public int getTemplatecatEnabledInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_TemplatecatEnabled));
      }


 
 }

