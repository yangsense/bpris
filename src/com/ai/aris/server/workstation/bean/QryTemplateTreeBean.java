package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryTemplateTreeBean extends DataContainer implements DataContainerInterface,IQryTemplateTreeValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryTemplateTree";



  public final static  String S_TemplatedirPdirid = "TEMPLATEDIR_PDIRID";
  public final static  String S_TemplatedirFlag = "TEMPLATEDIR_FLAG";
  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_TemplatedirOrder = "TEMPLATEDIR_ORDER";
  public final static  String S_Children = "CHILDREN";
  public final static  String S_IsDirectory = "IS_DIRECTORY";
  public final static  String S_ModalityId = "MODALITY_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_TemplatedirId = "TEMPLATEDIR_ID";
  public final static  String S_TemplatedirEnabled = "TEMPLATEDIR_ENABLED";
  public final static  String S_TemplatedirDesc = "TEMPLATEDIR_DESC";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryTemplateTreeBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initTemplatedirPdirid(long value){
     this.initProperty(S_TemplatedirPdirid,new Long(value));
  }
  public  void setTemplatedirPdirid(long value){
     this.set(S_TemplatedirPdirid,new Long(value));
  }
  public  void setTemplatedirPdiridNull(){
     this.set(S_TemplatedirPdirid,null);
  }

  public long getTemplatedirPdirid(){
        return DataType.getAsLong(this.get(S_TemplatedirPdirid));
  
  }
  public long getTemplatedirPdiridInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_TemplatedirPdirid));
      }

  public void initTemplatedirFlag(long value){
     this.initProperty(S_TemplatedirFlag,new Long(value));
  }
  public  void setTemplatedirFlag(long value){
     this.set(S_TemplatedirFlag,new Long(value));
  }
  public  void setTemplatedirFlagNull(){
     this.set(S_TemplatedirFlag,null);
  }

  public long getTemplatedirFlag(){
        return DataType.getAsLong(this.get(S_TemplatedirFlag));
  
  }
  public long getTemplatedirFlagInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_TemplatedirFlag));
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

  public void initTemplatedirOrder(long value){
     this.initProperty(S_TemplatedirOrder,new Long(value));
  }
  public  void setTemplatedirOrder(long value){
     this.set(S_TemplatedirOrder,new Long(value));
  }
  public  void setTemplatedirOrderNull(){
     this.set(S_TemplatedirOrder,null);
  }

  public long getTemplatedirOrder(){
        return DataType.getAsLong(this.get(S_TemplatedirOrder));
  
  }
  public long getTemplatedirOrderInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_TemplatedirOrder));
      }

  public void initChildren(long value){
     this.initProperty(S_Children,new Long(value));
  }
  public  void setChildren(long value){
     this.set(S_Children,new Long(value));
  }
  public  void setChildrenNull(){
     this.set(S_Children,null);
  }

  public long getChildren(){
        return DataType.getAsLong(this.get(S_Children));
  
  }
  public long getChildrenInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Children));
      }

  public void initIsDirectory(long value){
     this.initProperty(S_IsDirectory,new Long(value));
  }
  public  void setIsDirectory(long value){
     this.set(S_IsDirectory,new Long(value));
  }
  public  void setIsDirectoryNull(){
     this.set(S_IsDirectory,null);
  }

  public long getIsDirectory(){
        return DataType.getAsLong(this.get(S_IsDirectory));
  
  }
  public long getIsDirectoryInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_IsDirectory));
      }

  public void initModalityId(String value){
     this.initProperty(S_ModalityId,value);
  }
  public  void setModalityId(String value){
     this.set(S_ModalityId,value);
  }
  public  void setModalityIdNull(){
     this.set(S_ModalityId,null);
  }

  public String getModalityId(){
       return DataType.getAsString(this.get(S_ModalityId));
  
  }
  public String getModalityIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ModalityId));
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

  public void initTemplatedirId(long value){
     this.initProperty(S_TemplatedirId,new Long(value));
  }
  public  void setTemplatedirId(long value){
     this.set(S_TemplatedirId,new Long(value));
  }
  public  void setTemplatedirIdNull(){
     this.set(S_TemplatedirId,null);
  }

  public long getTemplatedirId(){
        return DataType.getAsLong(this.get(S_TemplatedirId));
  
  }
  public long getTemplatedirIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_TemplatedirId));
      }

  public void initTemplatedirEnabled(long value){
     this.initProperty(S_TemplatedirEnabled,new Long(value));
  }
  public  void setTemplatedirEnabled(long value){
     this.set(S_TemplatedirEnabled,new Long(value));
  }
  public  void setTemplatedirEnabledNull(){
     this.set(S_TemplatedirEnabled,null);
  }

  public long getTemplatedirEnabled(){
        return DataType.getAsLong(this.get(S_TemplatedirEnabled));
  
  }
  public long getTemplatedirEnabledInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_TemplatedirEnabled));
      }

  public void initTemplatedirDesc(String value){
     this.initProperty(S_TemplatedirDesc,value);
  }
  public  void setTemplatedirDesc(String value){
     this.set(S_TemplatedirDesc,value);
  }
  public  void setTemplatedirDescNull(){
     this.set(S_TemplatedirDesc,null);
  }

  public String getTemplatedirDesc(){
       return DataType.getAsString(this.get(S_TemplatedirDesc));
  
  }
  public String getTemplatedirDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_TemplatedirDesc));
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

