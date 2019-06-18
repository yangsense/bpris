package com.ai.aris.server.cornerstone.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.cornerstone.bean.*;

public class QryImageBean extends DataContainer implements DataContainerInterface,IQryImageValue{

  private static String  m_boName = "com.ai.aris.server.cornerstone.bean.QryImage";



  public final static  String S_ImageId = "IMAGE_ID";
  public final static  String S_ImageUid = "IMAGE_UID";
  public final static  String S_ImageKey = "IMAGE_KEY";
  public final static  String S_SeriesKey = "SERIES_KEY";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryImageBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initImageId(String value){
     this.initProperty(S_ImageId,value);
  }
  public  void setImageId(String value){
     this.set(S_ImageId,value);
  }
  public  void setImageIdNull(){
     this.set(S_ImageId,null);
  }

  public String getImageId(){
       return DataType.getAsString(this.get(S_ImageId));
  
  }
  public String getImageIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ImageId));
      }

  public void initImageUid(String value){
     this.initProperty(S_ImageUid,value);
  }
  public  void setImageUid(String value){
     this.set(S_ImageUid,value);
  }
  public  void setImageUidNull(){
     this.set(S_ImageUid,null);
  }

  public String getImageUid(){
       return DataType.getAsString(this.get(S_ImageUid));
  
  }
  public String getImageUidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ImageUid));
      }

  public void initImageKey(long value){
     this.initProperty(S_ImageKey,new Long(value));
  }
  public  void setImageKey(long value){
     this.set(S_ImageKey,new Long(value));
  }
  public  void setImageKeyNull(){
     this.set(S_ImageKey,null);
  }

  public long getImageKey(){
        return DataType.getAsLong(this.get(S_ImageKey));
  
  }
  public long getImageKeyInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ImageKey));
      }

  public void initSeriesKey(long value){
     this.initProperty(S_SeriesKey,new Long(value));
  }
  public  void setSeriesKey(long value){
     this.set(S_SeriesKey,new Long(value));
  }
  public  void setSeriesKeyNull(){
     this.set(S_SeriesKey,null);
  }

  public long getSeriesKey(){
        return DataType.getAsLong(this.get(S_SeriesKey));
  
  }
  public long getSeriesKeyInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_SeriesKey));
      }


 
 }

