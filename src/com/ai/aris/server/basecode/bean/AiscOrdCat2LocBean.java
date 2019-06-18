package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscOrdCat2LocBean extends DataContainer implements DataContainerInterface,IAiscOrdCat2LocValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscOrdCat2Loc";



  public final static  String S_OrdcatId = "ORDCAT_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_Ordcat2locId = "ORDCAT2LOC_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscOrdCat2LocBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOrdcatId(long value){
     this.initProperty(S_OrdcatId,new Long(value));
  }
  public  void setOrdcatId(long value){
     this.set(S_OrdcatId,new Long(value));
  }
  public  void setOrdcatIdNull(){
     this.set(S_OrdcatId,null);
  }

  public long getOrdcatId(){
        return DataType.getAsLong(this.get(S_OrdcatId));
  
  }
  public long getOrdcatIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrdcatId));
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

