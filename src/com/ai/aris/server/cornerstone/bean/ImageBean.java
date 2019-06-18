package com.ai.aris.server.cornerstone.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.*;

public class ImageBean extends DataContainer implements DataContainerInterface,IImageValue{

  private static String  m_boName = "com.ai.aris.server.cornerstone.bean.Image";



  public final static  String S_Columns = "COLUMNS";
  public final static  String S_Graphattr = "GRAPHATTR";
  public final static  String S_Sopclass = "SOPCLASS";
  public final static  String S_Contenttime = "CONTENTTIME";
  public final static  String S_Transforms = "TRANSFORMS";
  public final static  String S_Archived = "ARCHIVED";
  public final static  String S_Bitsallocated = "BITSALLOCATED";
  public final static  String S_Filename = "FILENAME";
  public final static  String S_Imagenumber = "IMAGENUMBER";
  public final static  String S_Bitsstored = "BITSSTORED";
  public final static  String S_Contentdate = "CONTENTDATE";
  public final static  String S_Highbit = "HIGHBIT";
  public final static  String S_Rows = "ROWS";
  public final static  String S_Pixelrepresentation = "PIXELREPRESENTATION";
  public final static  String S_Numberofframes = "NUMBEROFFRAMES";
  public final static  String S_Photometricinterpretation = "PHOTOMETRICINTERPRETATION";
  public final static  String S_Disknumber = "DISKNUMBER";
  public final static  String S_Serieskey = "SERIESKEY";
  public final static  String S_Pixpm = "PIXPM";
  public final static  String S_Samplesperpixel = "SAMPLESPERPIXEL";
  public final static  String S_Imageuid = "IMAGEUID";
  public final static  String S_Imagekey = "IMAGEKEY";
  public final static  String S_Presence = "PRESENCE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public ImageBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initColumns(long value){
     this.initProperty(S_Columns,new Long(value));
  }
  public  void setColumns(long value){
     this.set(S_Columns,new Long(value));
  }
  public  void setColumnsNull(){
     this.set(S_Columns,null);
  }

  public long getColumns(){
        return DataType.getAsLong(this.get(S_Columns));
  
  }
  public long getColumnsInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Columns));
      }

  public void initGraphattr(String value){
     this.initProperty(S_Graphattr,value);
  }
  public  void setGraphattr(String value){
     this.set(S_Graphattr,value);
  }
  public  void setGraphattrNull(){
     this.set(S_Graphattr,null);
  }

  public String getGraphattr(){
       return DataType.getAsString(this.get(S_Graphattr));
  
  }
  public String getGraphattrInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Graphattr));
      }

  public void initSopclass(String value){
     this.initProperty(S_Sopclass,value);
  }
  public  void setSopclass(String value){
     this.set(S_Sopclass,value);
  }
  public  void setSopclassNull(){
     this.set(S_Sopclass,null);
  }

  public String getSopclass(){
       return DataType.getAsString(this.get(S_Sopclass));
  
  }
  public String getSopclassInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Sopclass));
      }

  public void initContenttime(String value){
     this.initProperty(S_Contenttime,value);
  }
  public  void setContenttime(String value){
     this.set(S_Contenttime,value);
  }
  public  void setContenttimeNull(){
     this.set(S_Contenttime,null);
  }

  public String getContenttime(){
       return DataType.getAsString(this.get(S_Contenttime));
  
  }
  public String getContenttimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Contenttime));
      }

  public void initTransforms(String value){
     this.initProperty(S_Transforms,value);
  }
  public  void setTransforms(String value){
     this.set(S_Transforms,value);
  }
  public  void setTransformsNull(){
     this.set(S_Transforms,null);
  }

  public String getTransforms(){
       return DataType.getAsString(this.get(S_Transforms));
  
  }
  public String getTransformsInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Transforms));
      }

  public void initArchived(String value){
     this.initProperty(S_Archived,value);
  }
  public  void setArchived(String value){
     this.set(S_Archived,value);
  }
  public  void setArchivedNull(){
     this.set(S_Archived,null);
  }

  public String getArchived(){
       return DataType.getAsString(this.get(S_Archived));
  
  }
  public String getArchivedInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Archived));
      }

  public void initBitsallocated(long value){
     this.initProperty(S_Bitsallocated,new Long(value));
  }
  public  void setBitsallocated(long value){
     this.set(S_Bitsallocated,new Long(value));
  }
  public  void setBitsallocatedNull(){
     this.set(S_Bitsallocated,null);
  }

  public long getBitsallocated(){
        return DataType.getAsLong(this.get(S_Bitsallocated));
  
  }
  public long getBitsallocatedInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Bitsallocated));
      }

  public void initFilename(String value){
     this.initProperty(S_Filename,value);
  }
  public  void setFilename(String value){
     this.set(S_Filename,value);
  }
  public  void setFilenameNull(){
     this.set(S_Filename,null);
  }

  public String getFilename(){
       return DataType.getAsString(this.get(S_Filename));
  
  }
  public String getFilenameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Filename));
      }

  public void initImagenumber(long value){
     this.initProperty(S_Imagenumber,new Long(value));
  }
  public  void setImagenumber(long value){
     this.set(S_Imagenumber,new Long(value));
  }
  public  void setImagenumberNull(){
     this.set(S_Imagenumber,null);
  }

  public long getImagenumber(){
        return DataType.getAsLong(this.get(S_Imagenumber));
  
  }
  public long getImagenumberInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Imagenumber));
      }

  public void initBitsstored(long value){
     this.initProperty(S_Bitsstored,new Long(value));
  }
  public  void setBitsstored(long value){
     this.set(S_Bitsstored,new Long(value));
  }
  public  void setBitsstoredNull(){
     this.set(S_Bitsstored,null);
  }

  public long getBitsstored(){
        return DataType.getAsLong(this.get(S_Bitsstored));
  
  }
  public long getBitsstoredInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Bitsstored));
      }

  public void initContentdate(String value){
     this.initProperty(S_Contentdate,value);
  }
  public  void setContentdate(String value){
     this.set(S_Contentdate,value);
  }
  public  void setContentdateNull(){
     this.set(S_Contentdate,null);
  }

  public String getContentdate(){
       return DataType.getAsString(this.get(S_Contentdate));
  
  }
  public String getContentdateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Contentdate));
      }

  public void initHighbit(long value){
     this.initProperty(S_Highbit,new Long(value));
  }
  public  void setHighbit(long value){
     this.set(S_Highbit,new Long(value));
  }
  public  void setHighbitNull(){
     this.set(S_Highbit,null);
  }

  public long getHighbit(){
        return DataType.getAsLong(this.get(S_Highbit));
  
  }
  public long getHighbitInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Highbit));
      }

  public void initRows(long value){
     this.initProperty(S_Rows,new Long(value));
  }
  public  void setRows(long value){
     this.set(S_Rows,new Long(value));
  }
  public  void setRowsNull(){
     this.set(S_Rows,null);
  }

  public long getRows(){
        return DataType.getAsLong(this.get(S_Rows));
  
  }
  public long getRowsInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Rows));
      }

  public void initPixelrepresentation(long value){
     this.initProperty(S_Pixelrepresentation,new Long(value));
  }
  public  void setPixelrepresentation(long value){
     this.set(S_Pixelrepresentation,new Long(value));
  }
  public  void setPixelrepresentationNull(){
     this.set(S_Pixelrepresentation,null);
  }

  public long getPixelrepresentation(){
        return DataType.getAsLong(this.get(S_Pixelrepresentation));
  
  }
  public long getPixelrepresentationInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Pixelrepresentation));
      }

  public void initNumberofframes(long value){
     this.initProperty(S_Numberofframes,new Long(value));
  }
  public  void setNumberofframes(long value){
     this.set(S_Numberofframes,new Long(value));
  }
  public  void setNumberofframesNull(){
     this.set(S_Numberofframes,null);
  }

  public long getNumberofframes(){
        return DataType.getAsLong(this.get(S_Numberofframes));
  
  }
  public long getNumberofframesInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Numberofframes));
      }

  public void initPhotometricinterpretation(String value){
     this.initProperty(S_Photometricinterpretation,value);
  }
  public  void setPhotometricinterpretation(String value){
     this.set(S_Photometricinterpretation,value);
  }
  public  void setPhotometricinterpretationNull(){
     this.set(S_Photometricinterpretation,null);
  }

  public String getPhotometricinterpretation(){
       return DataType.getAsString(this.get(S_Photometricinterpretation));
  
  }
  public String getPhotometricinterpretationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Photometricinterpretation));
      }

  public void initDisknumber(long value){
     this.initProperty(S_Disknumber,new Long(value));
  }
  public  void setDisknumber(long value){
     this.set(S_Disknumber,new Long(value));
  }
  public  void setDisknumberNull(){
     this.set(S_Disknumber,null);
  }

  public long getDisknumber(){
        return DataType.getAsLong(this.get(S_Disknumber));
  
  }
  public long getDisknumberInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Disknumber));
      }

  public void initSerieskey(long value){
     this.initProperty(S_Serieskey,new Long(value));
  }
  public  void setSerieskey(long value){
     this.set(S_Serieskey,new Long(value));
  }
  public  void setSerieskeyNull(){
     this.set(S_Serieskey,null);
  }

  public long getSerieskey(){
        return DataType.getAsLong(this.get(S_Serieskey));
  
  }
  public long getSerieskeyInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Serieskey));
      }

  public void initPixpm(String value){
     this.initProperty(S_Pixpm,value);
  }
  public  void setPixpm(String value){
     this.set(S_Pixpm,value);
  }
  public  void setPixpmNull(){
     this.set(S_Pixpm,null);
  }

  public String getPixpm(){
       return DataType.getAsString(this.get(S_Pixpm));
  
  }
  public String getPixpmInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Pixpm));
      }

  public void initSamplesperpixel(long value){
     this.initProperty(S_Samplesperpixel,new Long(value));
  }
  public  void setSamplesperpixel(long value){
     this.set(S_Samplesperpixel,new Long(value));
  }
  public  void setSamplesperpixelNull(){
     this.set(S_Samplesperpixel,null);
  }

  public long getSamplesperpixel(){
        return DataType.getAsLong(this.get(S_Samplesperpixel));
  
  }
  public long getSamplesperpixelInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Samplesperpixel));
      }

  public void initImageuid(String value){
     this.initProperty(S_Imageuid,value);
  }
  public  void setImageuid(String value){
     this.set(S_Imageuid,value);
  }
  public  void setImageuidNull(){
     this.set(S_Imageuid,null);
  }

  public String getImageuid(){
       return DataType.getAsString(this.get(S_Imageuid));
  
  }
  public String getImageuidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Imageuid));
      }

  public void initImagekey(long value){
     this.initProperty(S_Imagekey,new Long(value));
  }
  public  void setImagekey(long value){
     this.set(S_Imagekey,new Long(value));
  }
  public  void setImagekeyNull(){
     this.set(S_Imagekey,null);
  }

  public long getImagekey(){
        return DataType.getAsLong(this.get(S_Imagekey));
  
  }
  public long getImagekeyInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Imagekey));
      }

  public void initPresence(String value){
     this.initProperty(S_Presence,value);
  }
  public  void setPresence(String value){
     this.set(S_Presence,value);
  }
  public  void setPresenceNull(){
     this.set(S_Presence,null);
  }

  public String getPresence(){
       return DataType.getAsString(this.get(S_Presence));
  
  }
  public String getPresenceInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Presence));
      }


 
 }

