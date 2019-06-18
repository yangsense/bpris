package com.ai.aris.server.basecode.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.DataType;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.ServiceManager;

public class AiscTemplatedirBean extends DataContainer implements DataContainerInterface,IAiscTemplatedirValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscTemplatedir";



  public final static  String S_TemplatedirOrder = "TEMPLATEDIR_ORDER";
  public final static  String S_TemplatedirEnabled = "TEMPLATEDIR_ENABLED";
  public final static  String S_TemplatedirId = "TEMPLATEDIR_ID";
  public final static  String S_TemplatedirPdirid = "TEMPLATEDIR_PDIRID";
  public final static  String S_TemplatedirDesc = "TEMPLATEDIR_DESC";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscTemplatedirBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }

  public void initTemplatedirOrder(int value){
     this.initProperty(S_TemplatedirOrder,new Integer(value));
  }
  public  void setTemplatedirOrder(int value){
     this.set(S_TemplatedirOrder,new Integer(value));
  }
  public  void setTemplatedirOrderNull(){
     this.set(S_TemplatedirOrder,null);
  }

  public int getTemplatedirOrder(){
        return DataType.getAsInt(this.get(S_TemplatedirOrder));
  
  }
  public int getTemplatedirOrderInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_TemplatedirOrder));
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


 
 }

