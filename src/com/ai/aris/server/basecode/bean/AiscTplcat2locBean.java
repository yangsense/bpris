package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscTplcat2locBean extends DataContainer implements DataContainerInterface,IAiscTplcat2locValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscTplcat2loc";



  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_TemplatecatId = "TEMPLATECAT_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_Tplcat2locId = "TPLCAT2LOC_ID";
  public final static  String S_ModalityId = "MODALITY_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscTplcat2locBean() throws AIException{
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

  public void initOrgId(long value){
     this.initProperty(S_OrgId,new Long(value));
  }
  public  void setOrgId(long value){
     this.set(S_OrgId,new Long(value));
  }
  public  void setOrgIdNull(){
     this.set(S_OrgId,null);
  }

  public long getOrgId(){
        return DataType.getAsLong(this.get(S_OrgId));
  
  }
  public long getOrgIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrgId));
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

  public void initTplcat2locId(long value){
     this.initProperty(S_Tplcat2locId,new Long(value));
  }
  public  void setTplcat2locId(long value){
     this.set(S_Tplcat2locId,new Long(value));
  }
  public  void setTplcat2locIdNull(){
     this.set(S_Tplcat2locId,null);
  }

  public long getTplcat2locId(){
        return DataType.getAsLong(this.get(S_Tplcat2locId));
  
  }
  public long getTplcat2locIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Tplcat2locId));
      }

  public void initModalityId(long value){
     this.initProperty(S_ModalityId,new Long(value));
  }
  public  void setModalityId(long value){
     this.set(S_ModalityId,new Long(value));
  }
  public  void setModalityIdNull(){
     this.set(S_ModalityId,null);
  }

  public long getModalityId(){
        return DataType.getAsLong(this.get(S_ModalityId));
  
  }
  public long getModalityIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ModalityId));
      }


 
 }

