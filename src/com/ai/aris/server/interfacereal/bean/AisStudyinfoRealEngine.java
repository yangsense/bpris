package com.ai.aris.server.interfacereal.bean;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Connection;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.util.criteria.Criteria;
import com.ai.appframe2.bo.DataContainerFactory;

import com.ai.aris.server.interfacereal.bean.*;

public class AisStudyinfoRealEngine {

  public static AisStudyinfoRealBean[] getBeans(DataContainerInterface dc) throws
      Exception {
    Map ps = dc.getProperties();
    StringBuffer buffer = new StringBuffer();
    Map pList = new HashMap();
    for (java.util.Iterator cc = ps.entrySet().iterator(); cc.hasNext(); ) {
      Map.Entry e = (Map.Entry) cc.next();
      if(buffer.length() >0)
         buffer.append(" and ");
      buffer.append(e.getKey().toString() + " = :p_" + e.getKey().toString());
      pList.put("p_" + e.getKey().toString(),e.getValue());
    }
    Connection conn = ServiceManager.getSession().getConnection();
    try {
      return getBeans(buffer.toString(), pList);
    }finally{
      if (conn != null)
        conn.close();
    }
  }

    public static AisStudyinfoRealBean getBean(long _StudyinfoRealId) throws Exception{
            /**new create*/
    String condition = "STUDYINFO_REAL_ID = :S_STUDYINFO_REAL_ID";
      Map map = new HashMap();
      map.put("S_STUDYINFO_REAL_ID",new Long(_StudyinfoRealId));
;
      AisStudyinfoRealBean[] beans = getBeans(condition,map);
      if(beans!=null && beans.length==1)
              return beans[0];
      else if(beans!=null && beans.length>1){
              throw new Exception("[错误]根据主键查询出现一条以上记录");
      }else{
              AisStudyinfoRealBean bean = new AisStudyinfoRealBean();
                            bean.setStudyinfoRealId(_StudyinfoRealId);
                            return bean;
      }
 }

  public static  AisStudyinfoRealBean[] getBeans(Criteria sql) throws Exception{
   return getBeans(sql,-1,-1,false);
  }
 public static  AisStudyinfoRealBean[] getBeans(Criteria sql,int startNum,int endNum,boolean isShowFK) throws Exception{
    String[] cols = null;
    String condition = "";
    if(sql != null){
      cols = (String[])sql.getSelectColumns().toArray(new String[0]);
      condition = sql.toString();
    }
    return (AisStudyinfoRealBean[])getBeans(cols,condition,sql.getParameters(),startNum,endNum,isShowFK);
  }




  public static  AisStudyinfoRealBean[] getBeans(String condition,Map parameter) throws Exception{
	return getBeans(null,condition,parameter,-1,-1,false);
  }

  public static  AisStudyinfoRealBean[] getBeans(String[] cols,String condition,Map parameter,
           int startNum,int endNum,boolean isShowFK) throws Exception{
        Connection conn = null;
        try {
                conn = ServiceManager.getSession().getConnection();
                return (AisStudyinfoRealBean[])ServiceManager.getDataStore().retrieve(conn,AisStudyinfoRealBean.class,AisStudyinfoRealBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,null);
        }catch(Exception e){
                throw e;
        }finally{
           if (conn != null)
              conn.close();
        }
  }

   public static  AisStudyinfoRealBean[] getBeans(String[] cols,String condition,Map parameter,
          int startNum,int endNum,boolean isShowFK,String[] extendBOAttrs) throws Exception{
          Connection conn = null;
          try {
                  conn = ServiceManager.getSession().getConnection();
                  return (AisStudyinfoRealBean[])ServiceManager.getDataStore().retrieve(conn,AisStudyinfoRealBean.class,AisStudyinfoRealBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,extendBOAttrs);
          }catch(Exception e){
                  throw e;
          }finally{
        	if (conn != null)
                  conn.close();
          }
   }


   public static int getBeansCount(String condition,Map parameter) throws Exception{
     Connection conn = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              return ServiceManager.getDataStore().retrieveCount(conn,AisStudyinfoRealBean.getObjectTypeStatic(),condition,parameter,null);
      }catch(Exception e){
              throw e;
      }finally{
          if (conn != null)
              conn.close();
      }
   }

   public static int getBeansCount(String condition,Map parameter,String[] extendBOAttrs) throws Exception{
      	Connection conn = null;
        try {
                conn = ServiceManager.getSession().getConnection();
                return ServiceManager.getDataStore().retrieveCount(conn,AisStudyinfoRealBean.getObjectTypeStatic(),condition,parameter,extendBOAttrs);
        }catch(Exception e){
                throw e;
        }finally{
          if (conn != null)
              conn.close();
        }
   }

  public static void save( AisStudyinfoRealBean aBean) throws Exception
  {
  	Connection conn = null;
	try {
		conn = ServiceManager.getSession().getConnection();
   		ServiceManager.getDataStore().save(conn,aBean);
   	}catch(Exception e){
		throw e;
	}finally{
		conn.close();
	}
  }

   public static  void save( AisStudyinfoRealBean[] aBeans) throws Exception{
     	Connection conn = null;
        try {
                conn = ServiceManager.getSession().getConnection();
                ServiceManager.getDataStore().save(conn,aBeans);
        }catch(Exception e){
                throw e;
        }finally{
          if (conn != null)
              conn.close();
        }
   }

   public static  void saveBatch( AisStudyinfoRealBean[] aBeans) throws Exception{
     	Connection conn = null;
        try {
                conn = ServiceManager.getSession().getConnection();
                ServiceManager.getDataStore().saveBatch(conn,aBeans);
        }catch(Exception e){
                throw e;
        }finally{
          if (conn != null)
              conn.close();
        }
   }


  public static  AisStudyinfoRealBean[] getBeansFromQueryBO(String soureBO,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              String sql = ServiceManager.getObjectTypeFactory().getInstance(soureBO).getMapingEnty();
              resultset =ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (AisStudyinfoRealBean[])ServiceManager.getDataStore().crateDtaContainerFromResultSet(AisStudyinfoRealBean.class,AisStudyinfoRealBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

     public static  AisStudyinfoRealBean[] getBeansFromSql(String sql,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              resultset =ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (AisStudyinfoRealBean[])ServiceManager.getDataStore().crateDtaContainerFromResultSet(AisStudyinfoRealBean.class,AisStudyinfoRealBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

   public static java.math.BigDecimal getNewId() throws Exception{
       return ServiceManager.getIdGenerator().getNewId(AisStudyinfoRealBean.getObjectTypeStatic());
   }

/*
   public static java.sql.Timestamp getSysDate() throws Exception{
       return ServiceManager.getIdGenerator().getSysDate(AisStudyinfoRealBean.getObjectTypeStatic());
   }
*/

   public static AisStudyinfoRealBean wrap(DataContainerInterface source,Map colMatch,boolean canModify){
     try{
       return (AisStudyinfoRealBean)DataContainerFactory.wrap(source,AisStudyinfoRealBean.class,colMatch,canModify);
     }catch(Exception e){
       if(e.getCause()!=null)
         throw new RuntimeException(e.getCause());
       else
         throw new RuntimeException(e);
     }
   }
   public static AisStudyinfoRealBean copy(DataContainerInterface source,Map colMatch,boolean canModify){
     try {
       AisStudyinfoRealBean result = new AisStudyinfoRealBean();
       DataContainerFactory.copy(source, result, colMatch);
       return result;
     }
     catch (AIException ex) {
       if(ex.getCause()!=null)
         throw new RuntimeException(ex.getCause());
       else
         throw new RuntimeException(ex);
     }
    }

   public static AisStudyinfoRealBean transfer(IAisStudyinfoRealValue value) {
   	if(value==null)
   		return null;
	try {
		if(value instanceof AisStudyinfoRealBean){
			return (AisStudyinfoRealBean)value;
		}
		AisStudyinfoRealBean newBean = new AisStudyinfoRealBean();

		DataContainerFactory.transfer(value ,newBean);
		return newBean;
	}catch (Exception ex) {
		if(ex.getCause()!=null)
			throw new RuntimeException(ex.getCause());
		else
			throw new RuntimeException(ex);
	}
   }

   public static AisStudyinfoRealBean[] transfer(IAisStudyinfoRealValue[] value) {
   	if(value==null || value.length==0)
   		return null;

	try {
		if(value instanceof AisStudyinfoRealBean[]){
			return (AisStudyinfoRealBean[])value;
		}
		AisStudyinfoRealBean[] newBeans = new AisStudyinfoRealBean[value.length];
   		for(int i=0;i<newBeans.length;i++){
   			newBeans[i] = new AisStudyinfoRealBean();
			DataContainerFactory.transfer(value[i] ,newBeans[i]);
		}
		return newBeans;
	}catch (Exception ex) {
		if(ex.getCause()!=null)
			throw new RuntimeException(ex.getCause());
		else
			throw new RuntimeException(ex);
	}
   }
  public static void save(IAisStudyinfoRealValue aValue) throws Exception
  {
     save(transfer(aValue));
  }

   public static  void save( IAisStudyinfoRealValue[] aValues) throws Exception{
          save(transfer(aValues));
   }

   public static  void saveBatch( IAisStudyinfoRealValue[] aValues) throws Exception{
     saveBatch(transfer(aValues));
   }
}
