package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AiscTemplatecatDirBean extends DataContainer implements DataContainerInterface,IAiscTemplatecatDirValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AiscTemplatecatDir";



  public final static  String S_TemplatedirPdirid = "TEMPLATEDIR_PDIRID";
  public final static  String S_TemplatedirFlag = "TEMPLATEDIR_FLAG";
  public final static  String S_TemplatedirOrder = "TEMPLATEDIR_ORDER";
  public final static  String S_IsDirectory = "IS_DIRECTORY";
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
  public AiscTemplatecatDirBean() throws AIException{
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

  public void initTemplatedirFlag(int value){
     this.initProperty(S_TemplatedirFlag,new Integer(value));
  }
  public  void setTemplatedirFlag(int value){
     this.set(S_TemplatedirFlag,new Integer(value));
  }
  public  void setTemplatedirFlagNull(){
     this.set(S_TemplatedirFlag,null);
  }

  public int getTemplatedirFlag(){
        return DataType.getAsInt(this.get(S_TemplatedirFlag));
  
  }
  public int getTemplatedirFlagInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_TemplatedirFlag));
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

  public void initIsDirectory(int value){
     this.initProperty(S_IsDirectory,new Integer(value));
  }
  public  void setIsDirectory(int value){
     this.set(S_IsDirectory,new Integer(value));
  }
  public  void setIsDirectoryNull(){
     this.set(S_IsDirectory,null);
  }

  public int getIsDirectory(){
        return DataType.getAsInt(this.get(S_IsDirectory));
  
  }
  public int getIsDirectoryInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_IsDirectory));
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

  public void initTemplatedirEnabled(int value){
     this.initProperty(S_TemplatedirEnabled,new Integer(value));
  }
  public  void setTemplatedirEnabled(int value){
     this.set(S_TemplatedirEnabled,new Integer(value));
  }
  public  void setTemplatedirEnabledNull(){
     this.set(S_TemplatedirEnabled,null);
  }

  public int getTemplatedirEnabled(){
        return DataType.getAsInt(this.get(S_TemplatedirEnabled));
  
  }
  public int getTemplatedirEnabledInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_TemplatedirEnabled));
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

