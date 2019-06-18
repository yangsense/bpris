package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class queryAiscConorganizationBean extends DataContainer implements DataContainerInterface,IqueryAiscConorganizationValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.queryAiscConorganization";



  public final static  String S_LocId = "LOC_ID";
  public final static  String S_ConlocId = "CONLOC_ID";
  public final static  String S_ConorganizatId = "CONORGANIZAT_ID";
  public final static  String S_Hzks = "HZKS";
  public final static  String S_ConorgId = "CONORG_ID";
  public final static  String S_Hzjgmc = "HZJGMC";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public queryAiscConorganizationBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initConlocId(String value){
     this.initProperty(S_ConlocId,value);
  }
  public  void setConlocId(String value){
     this.set(S_ConlocId,value);
  }
  public  void setConlocIdNull(){
     this.set(S_ConlocId,null);
  }

  public String getConlocId(){
       return DataType.getAsString(this.get(S_ConlocId));
  
  }
  public String getConlocIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ConlocId));
      }

  public void initConorganizatId(long value){
     this.initProperty(S_ConorganizatId,new Long(value));
  }
  public  void setConorganizatId(long value){
     this.set(S_ConorganizatId,new Long(value));
  }
  public  void setConorganizatIdNull(){
     this.set(S_ConorganizatId,null);
  }

  public long getConorganizatId(){
        return DataType.getAsLong(this.get(S_ConorganizatId));
  
  }
  public long getConorganizatIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ConorganizatId));
      }

  public void initHzks(String value){
     this.initProperty(S_Hzks,value);
  }
  public  void setHzks(String value){
     this.set(S_Hzks,value);
  }
  public  void setHzksNull(){
     this.set(S_Hzks,null);
  }

  public String getHzks(){
       return DataType.getAsString(this.get(S_Hzks));
  
  }
  public String getHzksInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Hzks));
      }

  public void initConorgId(String value){
     this.initProperty(S_ConorgId,value);
  }
  public  void setConorgId(String value){
     this.set(S_ConorgId,value);
  }
  public  void setConorgIdNull(){
     this.set(S_ConorgId,null);
  }

  public String getConorgId(){
       return DataType.getAsString(this.get(S_ConorgId));
  
  }
  public String getConorgIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ConorgId));
      }

  public void initHzjgmc(String value){
     this.initProperty(S_Hzjgmc,value);
  }
  public  void setHzjgmc(String value){
     this.set(S_Hzjgmc,value);
  }
  public  void setHzjgmcNull(){
     this.set(S_Hzjgmc,null);
  }

  public String getHzjgmc(){
       return DataType.getAsString(this.get(S_Hzjgmc));
  
  }
  public String getHzjgmcInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Hzjgmc));
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

