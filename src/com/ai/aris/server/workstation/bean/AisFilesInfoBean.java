package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AisFilesInfoBean extends DataContainer implements DataContainerInterface,IAisFilesInfoValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AisFilesInfo";



  public final static  String S_FilePrinted = "FILE_PRINTED";
  public final static  String S_FileDesc = "FILE_DESC";
  public final static  String S_FileType = "FILE_TYPE";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_MiId = "MI_ID";
  public final static  String S_FileId = "FILE_ID";
  public final static  String S_FileArchive = "FILE_ARCHIVE";
  public final static  String S_FileSize = "FILE_SIZE";
  public final static  String S_FilePath = "FILE_PATH";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisFilesInfoBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initFilePrinted(int value){
     this.initProperty(S_FilePrinted,new Integer(value));
  }
  public  void setFilePrinted(int value){
     this.set(S_FilePrinted,new Integer(value));
  }
  public  void setFilePrintedNull(){
     this.set(S_FilePrinted,null);
  }

  public int getFilePrinted(){
        return DataType.getAsInt(this.get(S_FilePrinted));
  
  }
  public int getFilePrintedInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_FilePrinted));
      }

  public void initFileDesc(String value){
     this.initProperty(S_FileDesc,value);
  }
  public  void setFileDesc(String value){
     this.set(S_FileDesc,value);
  }
  public  void setFileDescNull(){
     this.set(S_FileDesc,null);
  }

  public String getFileDesc(){
       return DataType.getAsString(this.get(S_FileDesc));
  
  }
  public String getFileDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_FileDesc));
      }

  public void initFileType(String value){
     this.initProperty(S_FileType,value);
  }
  public  void setFileType(String value){
     this.set(S_FileType,value);
  }
  public  void setFileTypeNull(){
     this.set(S_FileType,null);
  }

  public String getFileType(){
       return DataType.getAsString(this.get(S_FileType));
  
  }
  public String getFileTypeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_FileType));
      }

  public void initStudyinfoId(long value){
     this.initProperty(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoId(long value){
     this.set(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoIdNull(){
     this.set(S_StudyinfoId,null);
  }

  public long getStudyinfoId(){
        return DataType.getAsLong(this.get(S_StudyinfoId));
  
  }
  public long getStudyinfoIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyinfoId));
      }

  public void initMiId(long value){
     this.initProperty(S_MiId,new Long(value));
  }
  public  void setMiId(long value){
     this.set(S_MiId,new Long(value));
  }
  public  void setMiIdNull(){
     this.set(S_MiId,null);
  }

  public long getMiId(){
        return DataType.getAsLong(this.get(S_MiId));
  
  }
  public long getMiIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MiId));
      }

  public void initFileId(long value){
     this.initProperty(S_FileId,new Long(value));
  }
  public  void setFileId(long value){
     this.set(S_FileId,new Long(value));
  }
  public  void setFileIdNull(){
     this.set(S_FileId,null);
  }

  public long getFileId(){
        return DataType.getAsLong(this.get(S_FileId));
  
  }
  public long getFileIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_FileId));
      }

  public void initFileArchive(int value){
     this.initProperty(S_FileArchive,new Integer(value));
  }
  public  void setFileArchive(int value){
     this.set(S_FileArchive,new Integer(value));
  }
  public  void setFileArchiveNull(){
     this.set(S_FileArchive,null);
  }

  public int getFileArchive(){
        return DataType.getAsInt(this.get(S_FileArchive));
  
  }
  public int getFileArchiveInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_FileArchive));
      }

  public void initFileSize(float value){
     this.initProperty(S_FileSize,new Float(value));
  }
  public  void setFileSize(float value){
     this.set(S_FileSize,new Float(value));
  }
  public  void setFileSizeNull(){
     this.set(S_FileSize,null);
  }

  public float getFileSize(){
        return DataType.getAsFloat(this.get(S_FileSize));
  
  }
  public float getFileSizeInitialValue(){
        return DataType.getAsFloat(this.getOldObj(S_FileSize));
      }

  public void initFilePath(String value){
     this.initProperty(S_FilePath,value);
  }
  public  void setFilePath(String value){
     this.set(S_FilePath,value);
  }
  public  void setFilePathNull(){
     this.set(S_FilePath,null);
  }

  public String getFilePath(){
       return DataType.getAsString(this.get(S_FilePath));
  
  }
  public String getFilePathInitialValue(){
        return DataType.getAsString(this.getOldObj(S_FilePath));
      }


 
 }

