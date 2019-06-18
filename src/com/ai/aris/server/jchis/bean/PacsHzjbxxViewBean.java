package com.ai.aris.server.jchis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.jchis.bean.*;

public class PacsHzjbxxViewBean extends DataContainer implements DataContainerInterface,IPacsHzjbxxViewValue{

  private static String  m_boName = "com.ai.aris.server.jchis.bean.PacsHzjbxxView";



  public final static  String S_Sqys = "SQYS";
  public final static  String S_Jbmc = "JBMC";
  public final static  String S_Zxsj = "ZXSJ";
  public final static  String S_Zje = "ZJE";
  public final static  String S_Lxdh = "LXDH";
  public final static  String S_Zxksdm = "ZXKSDM";
  public final static  String S_Sjh = "SJH";
  public final static  String S_Zyh = "ZYH";
  public final static  String S_Jgdm = "JGDM";
  public final static  String S_Dz = "DZ";
  public final static  String S_Xb = "XB";
  public final static  String S_Sqksdm = "SQKSDM";
  public final static  String S_Brlx = "BRLX";
  public final static  String S_Sfzh = "SFZH";
  public final static  String S_Kh = "KH";
  public final static  String S_Bq = "BQ";
  public final static  String S_Djxh = "DJXH";
  public final static  String S_Brxm = "BRXM";
  public final static  String S_Yzmc = "YZMC";
  public final static  String S_Sqks = "SQKS";
  public final static  String S_Birth = "BIRTH";
  public final static  String S_Hissqh = "HISSQH";
  public final static  String S_Yzdm = "YZDM";
  public final static  String S_Ch = "CH";
  public final static  String S_Mzh = "MZH";
  public final static  String S_Sqysdm = "SQYSDM";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public PacsHzjbxxViewBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initSqys(String value){
     this.initProperty(S_Sqys,value);
  }
  public  void setSqys(String value){
     this.set(S_Sqys,value);
  }
  public  void setSqysNull(){
     this.set(S_Sqys,null);
  }

  public String getSqys(){
       return DataType.getAsString(this.get(S_Sqys));
  
  }
  public String getSqysInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Sqys));
      }

  public void initJbmc(String value){
     this.initProperty(S_Jbmc,value);
  }
  public  void setJbmc(String value){
     this.set(S_Jbmc,value);
  }
  public  void setJbmcNull(){
     this.set(S_Jbmc,null);
  }

  public String getJbmc(){
       return DataType.getAsString(this.get(S_Jbmc));
  
  }
  public String getJbmcInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Jbmc));
      }

  public void initZxsj(Timestamp value){
     this.initProperty(S_Zxsj,value);
  }
  public  void setZxsj(Timestamp value){
     this.set(S_Zxsj,value);
  }
  public  void setZxsjNull(){
     this.set(S_Zxsj,null);
  }

  public Timestamp getZxsj(){
        return DataType.getAsDateTime(this.get(S_Zxsj));
  
  }
  public Timestamp getZxsjInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_Zxsj));
      }

  public void initZje(long value){
     this.initProperty(S_Zje,new Long(value));
  }
  public  void setZje(long value){
     this.set(S_Zje,new Long(value));
  }
  public  void setZjeNull(){
     this.set(S_Zje,null);
  }

  public long getZje(){
        return DataType.getAsLong(this.get(S_Zje));
  
  }
  public long getZjeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Zje));
      }

  public void initLxdh(String value){
     this.initProperty(S_Lxdh,value);
  }
  public  void setLxdh(String value){
     this.set(S_Lxdh,value);
  }
  public  void setLxdhNull(){
     this.set(S_Lxdh,null);
  }

  public String getLxdh(){
       return DataType.getAsString(this.get(S_Lxdh));
  
  }
  public String getLxdhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Lxdh));
      }

  public void initZxksdm(String value){
     this.initProperty(S_Zxksdm,value);
  }
  public  void setZxksdm(String value){
     this.set(S_Zxksdm,value);
  }
  public  void setZxksdmNull(){
     this.set(S_Zxksdm,null);
  }

  public String getZxksdm(){
       return DataType.getAsString(this.get(S_Zxksdm));
  
  }
  public String getZxksdmInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Zxksdm));
      }

  public void initSjh(String value){
     this.initProperty(S_Sjh,value);
  }
  public  void setSjh(String value){
     this.set(S_Sjh,value);
  }
  public  void setSjhNull(){
     this.set(S_Sjh,null);
  }

  public String getSjh(){
       return DataType.getAsString(this.get(S_Sjh));
  
  }
  public String getSjhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Sjh));
      }

  public void initZyh(String value){
     this.initProperty(S_Zyh,value);
  }
  public  void setZyh(String value){
     this.set(S_Zyh,value);
  }
  public  void setZyhNull(){
     this.set(S_Zyh,null);
  }

  public String getZyh(){
       return DataType.getAsString(this.get(S_Zyh));
  
  }
  public String getZyhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Zyh));
      }

  public void initJgdm(String value){
     this.initProperty(S_Jgdm,value);
  }
  public  void setJgdm(String value){
     this.set(S_Jgdm,value);
  }
  public  void setJgdmNull(){
     this.set(S_Jgdm,null);
  }

  public String getJgdm(){
       return DataType.getAsString(this.get(S_Jgdm));
  
  }
  public String getJgdmInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Jgdm));
      }

  public void initDz(String value){
     this.initProperty(S_Dz,value);
  }
  public  void setDz(String value){
     this.set(S_Dz,value);
  }
  public  void setDzNull(){
     this.set(S_Dz,null);
  }

  public String getDz(){
       return DataType.getAsString(this.get(S_Dz));
  
  }
  public String getDzInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Dz));
      }

  public void initXb(String value){
     this.initProperty(S_Xb,value);
  }
  public  void setXb(String value){
     this.set(S_Xb,value);
  }
  public  void setXbNull(){
     this.set(S_Xb,null);
  }

  public String getXb(){
       return DataType.getAsString(this.get(S_Xb));
  
  }
  public String getXbInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Xb));
      }

  public void initSqksdm(String value){
     this.initProperty(S_Sqksdm,value);
  }
  public  void setSqksdm(String value){
     this.set(S_Sqksdm,value);
  }
  public  void setSqksdmNull(){
     this.set(S_Sqksdm,null);
  }

  public String getSqksdm(){
       return DataType.getAsString(this.get(S_Sqksdm));
  
  }
  public String getSqksdmInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Sqksdm));
      }

  public void initBrlx(String value){
     this.initProperty(S_Brlx,value);
  }
  public  void setBrlx(String value){
     this.set(S_Brlx,value);
  }
  public  void setBrlxNull(){
     this.set(S_Brlx,null);
  }

  public String getBrlx(){
       return DataType.getAsString(this.get(S_Brlx));
  
  }
  public String getBrlxInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Brlx));
      }

  public void initSfzh(String value){
     this.initProperty(S_Sfzh,value);
  }
  public  void setSfzh(String value){
     this.set(S_Sfzh,value);
  }
  public  void setSfzhNull(){
     this.set(S_Sfzh,null);
  }

  public String getSfzh(){
       return DataType.getAsString(this.get(S_Sfzh));
  
  }
  public String getSfzhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Sfzh));
      }

  public void initKh(String value){
     this.initProperty(S_Kh,value);
  }
  public  void setKh(String value){
     this.set(S_Kh,value);
  }
  public  void setKhNull(){
     this.set(S_Kh,null);
  }

  public String getKh(){
       return DataType.getAsString(this.get(S_Kh));
  
  }
  public String getKhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Kh));
      }

  public void initBq(String value){
     this.initProperty(S_Bq,value);
  }
  public  void setBq(String value){
     this.set(S_Bq,value);
  }
  public  void setBqNull(){
     this.set(S_Bq,null);
  }

  public String getBq(){
       return DataType.getAsString(this.get(S_Bq));
  
  }
  public String getBqInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Bq));
      }

  public void initDjxh(String value){
     this.initProperty(S_Djxh,value);
  }
  public  void setDjxh(String value){
     this.set(S_Djxh,value);
  }
  public  void setDjxhNull(){
     this.set(S_Djxh,null);
  }

  public String getDjxh(){
       return DataType.getAsString(this.get(S_Djxh));
  
  }
  public String getDjxhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Djxh));
      }

  public void initBrxm(String value){
     this.initProperty(S_Brxm,value);
  }
  public  void setBrxm(String value){
     this.set(S_Brxm,value);
  }
  public  void setBrxmNull(){
     this.set(S_Brxm,null);
  }

  public String getBrxm(){
       return DataType.getAsString(this.get(S_Brxm));
  
  }
  public String getBrxmInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Brxm));
      }

  public void initYzmc(String value){
     this.initProperty(S_Yzmc,value);
  }
  public  void setYzmc(String value){
     this.set(S_Yzmc,value);
  }
  public  void setYzmcNull(){
     this.set(S_Yzmc,null);
  }

  public String getYzmc(){
       return DataType.getAsString(this.get(S_Yzmc));
  
  }
  public String getYzmcInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Yzmc));
      }

  public void initSqks(String value){
     this.initProperty(S_Sqks,value);
  }
  public  void setSqks(String value){
     this.set(S_Sqks,value);
  }
  public  void setSqksNull(){
     this.set(S_Sqks,null);
  }

  public String getSqks(){
       return DataType.getAsString(this.get(S_Sqks));
  
  }
  public String getSqksInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Sqks));
      }

  public void initBirth(String value){
     this.initProperty(S_Birth,value);
  }
  public  void setBirth(String value){
     this.set(S_Birth,value);
  }
  public  void setBirthNull(){
     this.set(S_Birth,null);
  }

  public String getBirth(){
       return DataType.getAsString(this.get(S_Birth));
  
  }
  public String getBirthInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Birth));
      }

  public void initHissqh(String value){
     this.initProperty(S_Hissqh,value);
  }
  public  void setHissqh(String value){
     this.set(S_Hissqh,value);
  }
  public  void setHissqhNull(){
     this.set(S_Hissqh,null);
  }

  public String getHissqh(){
       return DataType.getAsString(this.get(S_Hissqh));
  
  }
  public String getHissqhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Hissqh));
      }

  public void initYzdm(String value){
     this.initProperty(S_Yzdm,value);
  }
  public  void setYzdm(String value){
     this.set(S_Yzdm,value);
  }
  public  void setYzdmNull(){
     this.set(S_Yzdm,null);
  }

  public String getYzdm(){
       return DataType.getAsString(this.get(S_Yzdm));
  
  }
  public String getYzdmInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Yzdm));
      }

  public void initCh(String value){
     this.initProperty(S_Ch,value);
  }
  public  void setCh(String value){
     this.set(S_Ch,value);
  }
  public  void setChNull(){
     this.set(S_Ch,null);
  }

  public String getCh(){
       return DataType.getAsString(this.get(S_Ch));
  
  }
  public String getChInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Ch));
      }

  public void initMzh(String value){
     this.initProperty(S_Mzh,value);
  }
  public  void setMzh(String value){
     this.set(S_Mzh,value);
  }
  public  void setMzhNull(){
     this.set(S_Mzh,null);
  }

  public String getMzh(){
       return DataType.getAsString(this.get(S_Mzh));
  
  }
  public String getMzhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Mzh));
      }

  public void initSqysdm(String value){
     this.initProperty(S_Sqysdm,value);
  }
  public  void setSqysdm(String value){
     this.set(S_Sqysdm,value);
  }
  public  void setSqysdmNull(){
     this.set(S_Sqysdm,null);
  }

  public String getSqysdm(){
       return DataType.getAsString(this.get(S_Sqysdm));
  
  }
  public String getSqysdmInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Sqysdm));
      }


 
 }

