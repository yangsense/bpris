package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscMediumInfoBean extends DataContainer implements DataContainerInterface,IAiscMediumInfoValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscMediumInfo";



  public final static  String S_MediumType = "MEDIUM_TYPE";
  public final static  String S_MediumEnabled = "MEDIUM_ENABLED";
  public final static  String S_MediumPath = "MEDIUM_PATH";
  public final static  String S_MediumIsonline = "MEDIUM_ISONLINE";
  public final static  String S_MediumId = "MEDIUM_ID";
  public final static  String S_MediumIsfull = "MEDIUM_ISFULL";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_MediumSize = "MEDIUM_SIZE";
  public final static  String S_MediumName = "MEDIUM_NAME";
  public final static  String S_MediumReminesize = "MEDIUM_REMINESIZE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscMediumInfoBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initMediumType(int value){
     this.initProperty(S_MediumType,new Integer(value));
  }
  public  void setMediumType(int value){
     this.set(S_MediumType,new Integer(value));
  }
  public  void setMediumTypeNull(){
     this.set(S_MediumType,null);
  }

  public int getMediumType(){
        return DataType.getAsInt(this.get(S_MediumType));
  
  }
  public int getMediumTypeInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_MediumType));
      }

  public void initMediumEnabled(int value){
     this.initProperty(S_MediumEnabled,new Integer(value));
  }
  public  void setMediumEnabled(int value){
     this.set(S_MediumEnabled,new Integer(value));
  }
  public  void setMediumEnabledNull(){
     this.set(S_MediumEnabled,null);
  }

  public int getMediumEnabled(){
        return DataType.getAsInt(this.get(S_MediumEnabled));
  
  }
  public int getMediumEnabledInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_MediumEnabled));
      }

  public void initMediumPath(String value){
     this.initProperty(S_MediumPath,value);
  }
  public  void setMediumPath(String value){
     this.set(S_MediumPath,value);
  }
  public  void setMediumPathNull(){
     this.set(S_MediumPath,null);
  }

  public String getMediumPath(){
       return DataType.getAsString(this.get(S_MediumPath));
  
  }
  public String getMediumPathInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MediumPath));
      }

  public void initMediumIsonline(int value){
     this.initProperty(S_MediumIsonline,new Integer(value));
  }
  public  void setMediumIsonline(int value){
     this.set(S_MediumIsonline,new Integer(value));
  }
  public  void setMediumIsonlineNull(){
     this.set(S_MediumIsonline,null);
  }

  public int getMediumIsonline(){
        return DataType.getAsInt(this.get(S_MediumIsonline));
  
  }
  public int getMediumIsonlineInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_MediumIsonline));
      }

  public void initMediumId(long value){
     this.initProperty(S_MediumId,new Long(value));
  }
  public  void setMediumId(long value){
     this.set(S_MediumId,new Long(value));
  }
  public  void setMediumIdNull(){
     this.set(S_MediumId,null);
  }

  public long getMediumId(){
        return DataType.getAsLong(this.get(S_MediumId));
  
  }
  public long getMediumIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumId));
      }

  public void initMediumIsfull(int value){
     this.initProperty(S_MediumIsfull,new Integer(value));
  }
  public  void setMediumIsfull(int value){
     this.set(S_MediumIsfull,new Integer(value));
  }
  public  void setMediumIsfullNull(){
     this.set(S_MediumIsfull,null);
  }

  public int getMediumIsfull(){
        return DataType.getAsInt(this.get(S_MediumIsfull));
  
  }
  public int getMediumIsfullInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_MediumIsfull));
      }

  public void initServerId(long value){
     this.initProperty(S_ServerId,new Long(value));
  }
  public  void setServerId(long value){
     this.set(S_ServerId,new Long(value));
  }
  public  void setServerIdNull(){
     this.set(S_ServerId,null);
  }

  public long getServerId(){
        return DataType.getAsLong(this.get(S_ServerId));
  
  }
  public long getServerIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ServerId));
      }

  public void initMediumSize(long value){
     this.initProperty(S_MediumSize,new Long(value));
  }
  public  void setMediumSize(long value){
     this.set(S_MediumSize,new Long(value));
  }
  public  void setMediumSizeNull(){
     this.set(S_MediumSize,null);
  }

  public long getMediumSize(){
        return DataType.getAsLong(this.get(S_MediumSize));
  
  }
  public long getMediumSizeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumSize));
      }

  public void initMediumName(String value){
     this.initProperty(S_MediumName,value);
  }
  public  void setMediumName(String value){
     this.set(S_MediumName,value);
  }
  public  void setMediumNameNull(){
     this.set(S_MediumName,null);
  }

  public String getMediumName(){
       return DataType.getAsString(this.get(S_MediumName));
  
  }
  public String getMediumNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MediumName));
      }

  public void initMediumReminesize(long value){
     this.initProperty(S_MediumReminesize,new Long(value));
  }
  public  void setMediumReminesize(long value){
     this.set(S_MediumReminesize,new Long(value));
  }
  public  void setMediumReminesizeNull(){
     this.set(S_MediumReminesize,null);
  }

  public long getMediumReminesize(){
        return DataType.getAsLong(this.get(S_MediumReminesize));
  
  }
  public long getMediumReminesizeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumReminesize));
      }


 
 }

