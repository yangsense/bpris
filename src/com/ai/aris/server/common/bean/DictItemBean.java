package com.ai.aris.server.common.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.common.bean.*;

public class DictItemBean extends DataContainer implements DataContainerInterface,IDictItemValue{

  private static String  m_boName = "com.ai.aris.server.common.bean.DictItem";



  public final static  String S_ItemNamePy = "ITEM_NAME_PY";
  public final static  String S_ItemNo = "ITEM_NO";
  public final static  String S_CreateUser = "CREATE_USER";
  public final static  String S_ItemDesc = "ITEM_DESC";
  public final static  String S_MappingItemNo = "MAPPING_ITEM_NO";
  public final static  String S_ItemNamePycap = "ITEM_NAME_PYCAP";
  public final static  String S_ParentItemNo = "PARENT_ITEM_NO";
  public final static  String S_MappingDictName = "MAPPING_DICT_NAME";
  public final static  String S_ItemState = "ITEM_STATE";
  public final static  String S_DictName = "DICT_NAME";
  public final static  String S_ItemName = "ITEM_NAME";
  public final static  String S_ItemOrder = "ITEM_ORDER";
  public final static  String S_CreateDate = "CREATE_DATE";
  public final static  String S_ItemLevel = "ITEM_LEVEL";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public DictItemBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initItemNamePy(String value){
     this.initProperty(S_ItemNamePy,value);
  }
  public  void setItemNamePy(String value){
     this.set(S_ItemNamePy,value);
  }
  public  void setItemNamePyNull(){
     this.set(S_ItemNamePy,null);
  }

  public String getItemNamePy(){
       return DataType.getAsString(this.get(S_ItemNamePy));
  
  }
  public String getItemNamePyInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ItemNamePy));
      }

  public void initItemNo(String value){
     this.initProperty(S_ItemNo,value);
  }
  public  void setItemNo(String value){
     this.set(S_ItemNo,value);
  }
  public  void setItemNoNull(){
     this.set(S_ItemNo,null);
  }

  public String getItemNo(){
       return DataType.getAsString(this.get(S_ItemNo));
  
  }
  public String getItemNoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ItemNo));
      }

  public void initCreateUser(String value){
     this.initProperty(S_CreateUser,value);
  }
  public  void setCreateUser(String value){
     this.set(S_CreateUser,value);
  }
  public  void setCreateUserNull(){
     this.set(S_CreateUser,null);
  }

  public String getCreateUser(){
       return DataType.getAsString(this.get(S_CreateUser));
  
  }
  public String getCreateUserInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CreateUser));
      }

  public void initItemDesc(String value){
     this.initProperty(S_ItemDesc,value);
  }
  public  void setItemDesc(String value){
     this.set(S_ItemDesc,value);
  }
  public  void setItemDescNull(){
     this.set(S_ItemDesc,null);
  }

  public String getItemDesc(){
       return DataType.getAsString(this.get(S_ItemDesc));
  
  }
  public String getItemDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ItemDesc));
      }

  public void initMappingItemNo(String value){
     this.initProperty(S_MappingItemNo,value);
  }
  public  void setMappingItemNo(String value){
     this.set(S_MappingItemNo,value);
  }
  public  void setMappingItemNoNull(){
     this.set(S_MappingItemNo,null);
  }

  public String getMappingItemNo(){
       return DataType.getAsString(this.get(S_MappingItemNo));
  
  }
  public String getMappingItemNoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MappingItemNo));
      }

  public void initItemNamePycap(String value){
     this.initProperty(S_ItemNamePycap,value);
  }
  public  void setItemNamePycap(String value){
     this.set(S_ItemNamePycap,value);
  }
  public  void setItemNamePycapNull(){
     this.set(S_ItemNamePycap,null);
  }

  public String getItemNamePycap(){
       return DataType.getAsString(this.get(S_ItemNamePycap));
  
  }
  public String getItemNamePycapInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ItemNamePycap));
      }

  public void initParentItemNo(String value){
     this.initProperty(S_ParentItemNo,value);
  }
  public  void setParentItemNo(String value){
     this.set(S_ParentItemNo,value);
  }
  public  void setParentItemNoNull(){
     this.set(S_ParentItemNo,null);
  }

  public String getParentItemNo(){
       return DataType.getAsString(this.get(S_ParentItemNo));
  
  }
  public String getParentItemNoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ParentItemNo));
      }

  public void initMappingDictName(String value){
     this.initProperty(S_MappingDictName,value);
  }
  public  void setMappingDictName(String value){
     this.set(S_MappingDictName,value);
  }
  public  void setMappingDictNameNull(){
     this.set(S_MappingDictName,null);
  }

  public String getMappingDictName(){
       return DataType.getAsString(this.get(S_MappingDictName));
  
  }
  public String getMappingDictNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MappingDictName));
      }

  public void initItemState(String value){
     this.initProperty(S_ItemState,value);
  }
  public  void setItemState(String value){
     this.set(S_ItemState,value);
  }
  public  void setItemStateNull(){
     this.set(S_ItemState,null);
  }

  public String getItemState(){
       return DataType.getAsString(this.get(S_ItemState));
  
  }
  public String getItemStateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ItemState));
      }

  public void initDictName(String value){
     this.initProperty(S_DictName,value);
  }
  public  void setDictName(String value){
     this.set(S_DictName,value);
  }
  public  void setDictNameNull(){
     this.set(S_DictName,null);
  }

  public String getDictName(){
       return DataType.getAsString(this.get(S_DictName));
  
  }
  public String getDictNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_DictName));
      }

  public void initItemName(String value){
     this.initProperty(S_ItemName,value);
  }
  public  void setItemName(String value){
     this.set(S_ItemName,value);
  }
  public  void setItemNameNull(){
     this.set(S_ItemName,null);
  }

  public String getItemName(){
       return DataType.getAsString(this.get(S_ItemName));
  
  }
  public String getItemNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ItemName));
      }

  public void initItemOrder(long value){
     this.initProperty(S_ItemOrder,new Long(value));
  }
  public  void setItemOrder(long value){
     this.set(S_ItemOrder,new Long(value));
  }
  public  void setItemOrderNull(){
     this.set(S_ItemOrder,null);
  }

  public long getItemOrder(){
        return DataType.getAsLong(this.get(S_ItemOrder));
  
  }
  public long getItemOrderInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemOrder));
      }

  public void initCreateDate(Timestamp value){
     this.initProperty(S_CreateDate,value);
  }
  public  void setCreateDate(Timestamp value){
     this.set(S_CreateDate,value);
  }
  public  void setCreateDateNull(){
     this.set(S_CreateDate,null);
  }

  public Timestamp getCreateDate(){
        return DataType.getAsDateTime(this.get(S_CreateDate));
  
  }
  public Timestamp getCreateDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CreateDate));
      }

  public void initItemLevel(long value){
     this.initProperty(S_ItemLevel,new Long(value));
  }
  public  void setItemLevel(long value){
     this.set(S_ItemLevel,new Long(value));
  }
  public  void setItemLevelNull(){
     this.set(S_ItemLevel,null);
  }

  public long getItemLevel(){
        return DataType.getAsLong(this.get(S_ItemLevel));
  
  }
  public long getItemLevelInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemLevel));
      }


 
 }

