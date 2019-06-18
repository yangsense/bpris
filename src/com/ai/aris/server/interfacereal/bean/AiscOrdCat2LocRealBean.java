package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscOrdCat2LocRealBean extends DataContainer implements DataContainerInterface,IAiscOrdCat2LocRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscOrdCat2LocReal";



  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OldOrdcat2locId = "OLD_ORDCAT2LOC_ID";
  public final static  String S_Ordcat2locId = "ORDCAT2LOC_ID";
  public final static  String S_Ordcat2locRealId = "ORDCAT2LOC_REAL_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscOrdCat2LocRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initCreateTime(Timestamp value){
     this.initProperty(S_CreateTime,value);
  }
  public  void setCreateTime(Timestamp value){
     this.set(S_CreateTime,value);
  }
  public  void setCreateTimeNull(){
     this.set(S_CreateTime,null);
  }

  public Timestamp getCreateTime(){
        return DataType.getAsDateTime(this.get(S_CreateTime));
  
  }
  public Timestamp getCreateTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CreateTime));
      }

  public void initOldOrdcat2locId(long value){
     this.initProperty(S_OldOrdcat2locId,new Long(value));
  }
  public  void setOldOrdcat2locId(long value){
     this.set(S_OldOrdcat2locId,new Long(value));
  }
  public  void setOldOrdcat2locIdNull(){
     this.set(S_OldOrdcat2locId,null);
  }

  public long getOldOrdcat2locId(){
        return DataType.getAsLong(this.get(S_OldOrdcat2locId));
  
  }
  public long getOldOrdcat2locIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldOrdcat2locId));
      }

  public void initOrdcat2locId(long value){
     this.initProperty(S_Ordcat2locId,new Long(value));
  }
  public  void setOrdcat2locId(long value){
     this.set(S_Ordcat2locId,new Long(value));
  }
  public  void setOrdcat2locIdNull(){
     this.set(S_Ordcat2locId,null);
  }

  public long getOrdcat2locId(){
        return DataType.getAsLong(this.get(S_Ordcat2locId));
  
  }
  public long getOrdcat2locIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Ordcat2locId));
      }

  public void initOrdcat2locRealId(long value){
     this.initProperty(S_Ordcat2locRealId,new Long(value));
  }
  public  void setOrdcat2locRealId(long value){
     this.set(S_Ordcat2locRealId,new Long(value));
  }
  public  void setOrdcat2locRealIdNull(){
     this.set(S_Ordcat2locRealId,null);
  }

  public long getOrdcat2locRealId(){
        return DataType.getAsLong(this.get(S_Ordcat2locRealId));
  
  }
  public long getOrdcat2locRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Ordcat2locRealId));
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

