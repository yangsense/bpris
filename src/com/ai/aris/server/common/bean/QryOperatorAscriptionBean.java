package com.ai.aris.server.common.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.common.bean.*;

public class QryOperatorAscriptionBean extends DataContainer implements DataContainerInterface,IQryOperatorAscriptionValue{

  private static String  m_boName = "com.ai.aris.server.common.bean.QryOperatorAscription";



  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_OperatorPsw = "OPERATOR_PSW";
  public final static  String S_OperatorName = "OPERATOR_NAME";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryOperatorAscriptionBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOperatorCode(String value){
     this.initProperty(S_OperatorCode,value);
  }
  public  void setOperatorCode(String value){
     this.set(S_OperatorCode,value);
  }
  public  void setOperatorCodeNull(){
     this.set(S_OperatorCode,null);
  }

  public String getOperatorCode(){
       return DataType.getAsString(this.get(S_OperatorCode));
  
  }
  public String getOperatorCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorCode));
      }

  public void initOperatorPsw(String value){
     this.initProperty(S_OperatorPsw,value);
  }
  public  void setOperatorPsw(String value){
     this.set(S_OperatorPsw,value);
  }
  public  void setOperatorPswNull(){
     this.set(S_OperatorPsw,null);
  }

  public String getOperatorPsw(){
       return DataType.getAsString(this.get(S_OperatorPsw));
  
  }
  public String getOperatorPswInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorPsw));
      }

  public void initOperatorName(String value){
     this.initProperty(S_OperatorName,value);
  }
  public  void setOperatorName(String value){
     this.set(S_OperatorName,value);
  }
  public  void setOperatorNameNull(){
     this.set(S_OperatorName,null);
  }

  public String getOperatorName(){
       return DataType.getAsString(this.get(S_OperatorName));
  
  }
  public String getOperatorNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorName));
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

