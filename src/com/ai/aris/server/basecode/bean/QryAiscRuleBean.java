package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class QryAiscRuleBean extends DataContainer implements DataContainerInterface,IQryAiscRuleValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.QryAiscRule";



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

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryAiscRuleBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initRulePrix(String value){
     this.initProperty(S_RulePrix,value);
  }
  public  void setRulePrix(String value){
     this.set(S_RulePrix,value);
  }
  public  void setRulePrixNull(){
     this.set(S_RulePrix,null);
  }

  public String getRulePrix(){
       return DataType.getAsString(this.get(S_RulePrix));
  
  }
  public String getRulePrixInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RulePrix));
      }

  public void initRuleId(long value){
     this.initProperty(S_RuleId,new Long(value));
  }
  public  void setRuleId(long value){
     this.set(S_RuleId,new Long(value));
  }
  public  void setRuleIdNull(){
     this.set(S_RuleId,null);
  }

  public long getRuleId(){
        return DataType.getAsLong(this.get(S_RuleId));
  
  }
  public long getRuleIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RuleId));
      }

  public void initRuleField(String value){
     this.initProperty(S_RuleField,value);
  }
  public  void setRuleField(String value){
     this.set(S_RuleField,value);
  }
  public  void setRuleFieldNull(){
     this.set(S_RuleField,null);
  }

  public String getRuleField(){
       return DataType.getAsString(this.get(S_RuleField));
  
  }
  public String getRuleFieldInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RuleField));
      }

  public void initRuleType(long value){
     this.initProperty(S_RuleType,new Long(value));
  }
  public  void setRuleType(long value){
     this.set(S_RuleType,new Long(value));
  }
  public  void setRuleTypeNull(){
     this.set(S_RuleType,null);
  }

  public long getRuleType(){
        return DataType.getAsLong(this.get(S_RuleType));
  
  }
  public long getRuleTypeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RuleType));
      }

  public void initRuleLen(long value){
     this.initProperty(S_RuleLen,new Long(value));
  }
  public  void setRuleLen(long value){
     this.set(S_RuleLen,new Long(value));
  }
  public  void setRuleLenNull(){
     this.set(S_RuleLen,null);
  }

  public long getRuleLen(){
        return DataType.getAsLong(this.get(S_RuleLen));
  
  }
  public long getRuleLenInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RuleLen));
      }

  public void initOrgName(String value){
     this.initProperty(S_OrgName,value);
  }
  public  void setOrgName(String value){
     this.set(S_OrgName,value);
  }
  public  void setOrgNameNull(){
     this.set(S_OrgName,null);
  }

  public String getOrgName(){
       return DataType.getAsString(this.get(S_OrgName));
  
  }
  public String getOrgNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgName));
      }

  public void initLocDesc(String value){
     this.initProperty(S_LocDesc,value);
  }
  public  void setLocDesc(String value){
     this.set(S_LocDesc,value);
  }
  public  void setLocDescNull(){
     this.set(S_LocDesc,null);
  }

  public String getLocDesc(){
       return DataType.getAsString(this.get(S_LocDesc));
  
  }
  public String getLocDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocDesc));
      }

  public void initLocId(long value){
     this.initProperty(S_LocId,new Long(value));
  }
  public  void setLocId(long value){
     this.set(S_LocId,new Long(value));
  }
  public  void setLocIdNull(){
     this.set(S_LocId,null);
  }

  public long getLocId(){
        return DataType.getAsLong(this.get(S_LocId));
  
  }
  public long getLocIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_LocId));
      }

  public void initRuleStartindex(long value){
     this.initProperty(S_RuleStartindex,new Long(value));
  }
  public  void setRuleStartindex(long value){
     this.set(S_RuleStartindex,new Long(value));
  }
  public  void setRuleStartindexNull(){
     this.set(S_RuleStartindex,null);
  }

  public long getRuleStartindex(){
        return DataType.getAsLong(this.get(S_RuleStartindex));
  
  }
  public long getRuleStartindexInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RuleStartindex));
      }

  public void initOrgId(String value){
     this.initProperty(S_OrgId,value);
  }
  public  void setOrgId(String value){
     this.set(S_OrgId,value);
  }
  public  void setOrgIdNull(){
     this.set(S_OrgId,null);
  }

  public String getOrgId(){
       return DataType.getAsString(this.get(S_OrgId));
  
  }
  public String getOrgIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgId));
      }


 
 }

