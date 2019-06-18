package com.ai.aris.server.workstation.bean;

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

import com.ai.aris.server.workstation.bean.*;

public class AisStudyItemInfoEngine {

  public static AisStudyItemInfoBean[] getBeans(DataContainerInterface dc) throws
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

    public static AisStudyItemInfoBean getBean(long _StudyitemId) throws Exception{
            /**new create*/
    String condition = "STUDYITEM_ID = :S_STUDYITEM_ID";
      Map map = new HashMap();
      map.put("S_STUDYITEM_ID",new Long(_StudyitemId));
;
      AisStudyItemInfoBean[] beans = getBeans(condition,map);
      if(beans!=null && beans.length==1)
              return beans[0];
      else if(beans!=null && beans.length>1){
              throw new Exception("[错误]根据主键查询出现一条以上记录");
      }else{
              AisStudyItemInfoBean bean = new AisStudyItemInfoBean();
                            bean.setStudyitemId(_StudyitemId);
                            return bean;
      }
 }

  public static  AisStudyItemInfoBean[] getBeans(Criteria sql) throws Exception{
   return getBeans(sql,-1,-1,false);
  }
 public static  AisStudyItemInfoBean[] getBeans(Criteria sql,int startNum,int endNum,boolean isShowFK) throws Exception{
    String[] cols = null;
    String condition = "";
    if(sql != null){
      cols = (String[])sql.getSelectColumns().toArray(new String[0]);
      condition = sql.toString();
    }
    return (AisStudyItemInfoBean[])getBeans(cols,condition,sql.getParameters(),startNum,endNum,isShowFK);
  }




  public static  AisStudyItemInfoBean[] getBeans(String condition,Map parameter) throws Exception{
	return getBeans(null,condition,parameter,-1,-1,false);
  }

  public static  AisStudyItemInfoBean[] getBeans(String[] cols,String condition,Map parameter,
           int startNum,int endNum,boolean isShowFK) throws Exception{
        Connection conn = null;
        try {
                conn = ServiceManager.getSession().getConnection();
                return (AisStudyItemInfoBean[])ServiceManager.getDataStore().retrieve(conn,AisStudyItemInfoBean.class,AisStudyItemInfoBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,null);
        }catch(Exception e){
                throw e;
        }finally{
           if (conn != null)
              conn.close();
        }
  }

   public static  AisStudyItemInfoBean[] getBeans(String[] cols,String condition,Map parameter,
          int startNum,int endNum,boolean isShowFK,String[] extendBOAttrs) throws Exception{
          Connection conn = null;
          try {
                  conn = ServiceManager.getSession().getConnection();
                  return (AisStudyItemInfoBean[])ServiceManager.getDataStore().retrieve(conn,AisStudyItemInfoBean.class,AisStudyItemInfoBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,extendBOAttrs);
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
              return ServiceManager.getDataStore().retrieveCount(conn,AisStudyItemInfoBean.getObjectTypeStatic(),condition,parameter,null);
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
                return ServiceManager.getDataStore().retrieveCount(conn,AisStudyItemInfoBean.getObjectTypeStatic(),condition,parameter,extendBOAttrs);
        }catch(Exception e){
                throw e;
        }finally{
          if (conn != null)
              conn.close();
        }
   }

  public static void save( AisStudyItemInfoBean aBean) throws Exception
  {
  	Connection conn = null;
	try {
		conn = ServiceManager.getSession().getConnection();
   		ServiceManager.getDataStore().save(conn,aBean);
   	}catch(Exception e){
		throw e;
	}finally{
            if (conn != null)
                conn.close();
	}
  }

   public static  void save( AisStudyItemInfoBean[] aBeans) throws Exception{
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

   public static  void saveBatch( AisStudyItemInfoBean[] aBeans) throws Exception{
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


  public static  AisStudyItemInfoBean[] getBeansFromQueryBO(String soureBO,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
          conn = ServiceManager.getSession().getConnection();
          String sql = ServiceManager.getObjectTypeFactory().getInstance(soureBO).getMapingEnty();
          resultset =ServiceManager.getDataStore().retrieve(conn,sql,parameter);
          return (AisStudyItemInfoBean[])ServiceManager.getDataStore().crateDtaContainerFromResultSet(AisStudyItemInfoBean.class,AisStudyItemInfoBean.getObjectTypeStatic(),resultset,null,true);
      }catch(Exception e){
          throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

     public static  AisStudyItemInfoBean[] getBeansFromSql(String sql,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              resultset =ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (AisStudyItemInfoBean[])ServiceManager.getDataStore().crateDtaContainerFromResultSet(AisStudyItemInfoBean.class,AisStudyItemInfoBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

   public static java.math.BigDecimal getNewId() throws Exception{
       return ServiceManager.getIdGenerator().getNewId(AisStudyItemInfoBean.getObjectTypeStatic());
   }

/*
   public static java.sql.Timestamp getSysDate() throws Exception{
       return ServiceManager.getIdGenerator().getSysDate(AisStudyItemInfoBean.getObjectTypeStatic());
   }
*/

   public static AisStudyItemInfoBean wrap(DataContainerInterface source,Map colMatch,boolean canModify){
     try{
       return (AisStudyItemInfoBean)DataContainerFactory.wrap(source,AisStudyItemInfoBean.class,colMatch,canModify);
     }catch(Exception e){
       if(e.getCause()!=null)
         throw new RuntimeException(e.getCause());
       else
         throw new RuntimeException(e);
     }
   }
   public static AisStudyItemInfoBean copy(DataContainerInterface source,Map colMatch,boolean canModify){
     try {
       AisStudyItemInfoBean result = new AisStudyItemInfoBean();
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

   public static AisStudyItemInfoBean transfer(IAisStudyItemInfoValue value) {
   	if(value==null)
   		return null;
	try {
		if(value instanceof AisStudyItemInfoBean){
			return (AisStudyItemInfoBean)value;
		}
		AisStudyItemInfoBean newBean = new AisStudyItemInfoBean();

		DataContainerFactory.transfer(value ,newBean);
		return newBean;
	}catch (Exception ex) {
		if(ex.getCause()!=null)
			throw new RuntimeException(ex.getCause());
		else
			throw new RuntimeException(ex);
	}
   }

   public static AisStudyItemInfoBean[] transfer(IAisStudyItemInfoValue[] value) {
   	if(value==null || value.length==0)
   		return null;

	try {
		if(value instanceof AisStudyItemInfoBean[]){
			return (AisStudyItemInfoBean[])value;
		}
		AisStudyItemInfoBean[] newBeans = new AisStudyItemInfoBean[value.length];
   		for(int i=0;i<newBeans.length;i++){
   			newBeans[i] = new AisStudyItemInfoBean();
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
  public static void save(IAisStudyItemInfoValue aValue) throws Exception
  {
     save(transfer(aValue));
  }

   public static  void save( IAisStudyItemInfoValue[] aValues) throws Exception{
          save(transfer(aValues));
   }

   public static  void saveBatch( IAisStudyItemInfoValue[] aValues) throws Exception{
     saveBatch(transfer(aValues));
   }
}
