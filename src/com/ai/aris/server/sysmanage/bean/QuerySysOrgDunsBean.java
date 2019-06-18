package com.ai.aris.server.sysmanage.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.sysmanage.bean.*;

public class QuerySysOrgDunsBean extends DataContainer implements DataContainerInterface,IQuerySysOrgDunsValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.QuerySysOrgDuns";



  public final static  String S_Duns = "DUNS";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QuerySysOrgDunsBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initDuns(String value){
     this.initProperty(S_Duns,value);
  }
  public  void setDuns(String value){
     this.set(S_Duns,value);
  }
  public  void setDunsNull(){
     this.set(S_Duns,null);
  }

  public String getDuns(){
       return DataType.getAsString(this.get(S_Duns));
  
  }
  public String getDunsInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Duns));
      }


 
 }

