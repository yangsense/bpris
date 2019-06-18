package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscOrdSubCategoryBean extends DataContainer implements DataContainerInterface,IAiscOrdSubCategoryValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscOrdSubCategory";



  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_OrdsubcategoryCode = "ORDSUBCATEGORY_CODE";
  public final static  String S_OrdsubcategoryEndesc = "ORDSUBCATEGORY_ENDESC";
  public final static  String S_OrdsubcategoryDesc = "ORDSUBCATEGORY_DESC";
  public final static  String S_OrdsubcategoryId = "ORDSUBCATEGORY_ID";
  public final static  String S_OrdsubcategoryNote = "ORDSUBCATEGORY_NOTE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscOrdSubCategoryBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOrdcategoryId(long value){
     this.initProperty(S_OrdcategoryId,new Long(value));
  }
  public  void setOrdcategoryId(long value){
     this.set(S_OrdcategoryId,new Long(value));
  }
  public  void setOrdcategoryIdNull(){
     this.set(S_OrdcategoryId,null);
  }

  public long getOrdcategoryId(){
        return DataType.getAsLong(this.get(S_OrdcategoryId));
  
  }
  public long getOrdcategoryIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrdcategoryId));
      }

  public void initOrdsubcategoryCode(String value){
     this.initProperty(S_OrdsubcategoryCode,value);
  }
  public  void setOrdsubcategoryCode(String value){
     this.set(S_OrdsubcategoryCode,value);
  }
  public  void setOrdsubcategoryCodeNull(){
     this.set(S_OrdsubcategoryCode,null);
  }

  public String getOrdsubcategoryCode(){
       return DataType.getAsString(this.get(S_OrdsubcategoryCode));
  
  }
  public String getOrdsubcategoryCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrdsubcategoryCode));
      }

  public void initOrdsubcategoryEndesc(String value){
     this.initProperty(S_OrdsubcategoryEndesc,value);
  }
  public  void setOrdsubcategoryEndesc(String value){
     this.set(S_OrdsubcategoryEndesc,value);
  }
  public  void setOrdsubcategoryEndescNull(){
     this.set(S_OrdsubcategoryEndesc,null);
  }

  public String getOrdsubcategoryEndesc(){
       return DataType.getAsString(this.get(S_OrdsubcategoryEndesc));
  
  }
  public String getOrdsubcategoryEndescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrdsubcategoryEndesc));
      }

  public void initOrdsubcategoryDesc(String value){
     this.initProperty(S_OrdsubcategoryDesc,value);
  }
  public  void setOrdsubcategoryDesc(String value){
     this.set(S_OrdsubcategoryDesc,value);
  }
  public  void setOrdsubcategoryDescNull(){
     this.set(S_OrdsubcategoryDesc,null);
  }

  public String getOrdsubcategoryDesc(){
       return DataType.getAsString(this.get(S_OrdsubcategoryDesc));
  
  }
  public String getOrdsubcategoryDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrdsubcategoryDesc));
      }

  public void initOrdsubcategoryId(long value){
     this.initProperty(S_OrdsubcategoryId,new Long(value));
  }
  public  void setOrdsubcategoryId(long value){
     this.set(S_OrdsubcategoryId,new Long(value));
  }
  public  void setOrdsubcategoryIdNull(){
     this.set(S_OrdsubcategoryId,null);
  }

  public long getOrdsubcategoryId(){
        return DataType.getAsLong(this.get(S_OrdsubcategoryId));
  
  }
  public long getOrdsubcategoryIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrdsubcategoryId));
      }

  public void initOrdsubcategoryNote(String value){
     this.initProperty(S_OrdsubcategoryNote,value);
  }
  public  void setOrdsubcategoryNote(String value){
     this.set(S_OrdsubcategoryNote,value);
  }
  public  void setOrdsubcategoryNoteNull(){
     this.set(S_OrdsubcategoryNote,null);
  }

  public String getOrdsubcategoryNote(){
       return DataType.getAsString(this.get(S_OrdsubcategoryNote));
  
  }
  public String getOrdsubcategoryNoteInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrdsubcategoryNote));
      }


 
 }

