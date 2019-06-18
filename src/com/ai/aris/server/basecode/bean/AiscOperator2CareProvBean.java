package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscOperator2CareProvBean extends DataContainer implements DataContainerInterface,IAiscOperator2CareProvValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscOperator2CareProv";



  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_User2careprovId = "USER2CAREPROV_ID";
  public final static  String S_CareprovId = "CAREPROV_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscOperator2CareProvBean() throws AIException{
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

  public void initUser2careprovId(long value){
     this.initProperty(S_User2careprovId,new Long(value));
  }
  public  void setUser2careprovId(long value){
     this.set(S_User2careprovId,new Long(value));
  }
  public  void setUser2careprovIdNull(){
     this.set(S_User2careprovId,null);
  }

  public long getUser2careprovId(){
        return DataType.getAsLong(this.get(S_User2careprovId));
  
  }
  public long getUser2careprovIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_User2careprovId));
      }

  public void initCareprovId(long value){
     this.initProperty(S_CareprovId,new Long(value));
  }
  public  void setCareprovId(long value){
     this.set(S_CareprovId,new Long(value));
  }
  public  void setCareprovIdNull(){
     this.set(S_CareprovId,null);
  }

  public long getCareprovId(){
        return DataType.getAsLong(this.get(S_CareprovId));
  
  }
  public long getCareprovIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_CareprovId));
      }


 
 }

