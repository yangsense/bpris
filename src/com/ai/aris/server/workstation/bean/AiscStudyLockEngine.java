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

public class AiscStudyLockEngine {

  public static AiscStudyLockBean[] getBeans(DataContainerInterface dc) throws
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

    public static AiscStudyLockBean getBean(long _StudyinfoId) throws Exception{
            /**new create*/
    String condition = "STUDYINFO_ID = :S_STUDYINFO_ID";
      Map map = new HashMap();
      map.put("S_STUDYINFO_ID",new Long(_StudyinfoId));
;
      AiscStudyLockBean[] beans = getBeans(condition,map);
      if(beans!=null && beans.length==1)
              return beans[0];
      else if(beans!=null && beans.length>1){
              throw new Exception("[错误]根据主键查询出现一条以上记录");
      }else{
              AiscStudyLockBean bean = new AiscStudyLockBean();
                            bean.setStudyinfoId(_StudyinfoId);
                            return bean;
      }
 }

  public static  AiscStudyLockBean[] getBeans(Criteria sql) throws Exception{
   return getBeans(sql,-1,-1,false);
  }
 public static  AiscStudyLockBean[] getBeans(Criteria sql,int startNum,int endNum,boolean isShowFK) throws Exception{
    String[] cols = null;
    String condition = "";
    if(sql != null){
      cols = (String[])sql.getSelectColumns().toArray(new String[0]);
      condition = sql.toString();
    }
    return (AiscStudyLockBean[])getBeans(cols,condition,sql.getParameters(),startNum,endNum,isShowFK);
  }




  public static  AiscStudyLockBean[] getBeans(String condition,Map parameter) throws Exception{
	return getBeans(null,condition,parameter,-1,-1,false);
  }

  public static  AiscStudyLockBean[] getBeans(String[] cols,String condition,Map parameter,
           int startNum,int endNum,boolean isShowFK) throws Exception{
        Connection conn = null;
        try {
                conn = ServiceManager.getSession().getConnection();
                return (AiscStudyLockBean[])ServiceManager.getDataStore().retrieve(conn,AiscStudyLockBean.class,AiscStudyLockBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,null);
        }catch(Exception e){
                throw e;
        }finally{
           if (conn != null)
              conn.close();
        }
  }

   public static  AiscStudyLockBean[] getBeans(String[] cols,String condition,Map parameter,
          int startNum,int endNum,boolean isShowFK,String[] extendBOAttrs) throws Exception{
          Connection conn = null;
          try {
                  conn = ServiceManager.getSession().getConnection();
                  return (AiscStudyLockBean[])ServiceManager.getDataStore().retrieve(conn,AiscStudyLockBean.class,AiscStudyLockBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,extendBOAttrs);
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
              return ServiceManager.getDataStore().retrieveCount(conn,AiscStudyLockBean.getObjectTypeStatic(),condition,parameter,null);
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
                return ServiceManager.getDataStore().retrieveCount(conn,AiscStudyLockBean.getObjectTypeStatic(),condition,parameter,extendBOAttrs);
        }catch(Exception e){
                throw e;
        }finally{
          if (conn != null)
              conn.close();
        }
   }

  public static void save( AiscStudyLockBean aBean) throws Exception
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

   public static  void save( AiscStudyLockBean[] aBeans) throws Exception{
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

   public static  void saveBatch( AiscStudyLockBean[] aBeans) throws Exception{
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


  public static  AiscStudyLockBean[] getBeansFromQueryBO(String soureBO,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
          conn = ServiceManager.getSession().getConnection();
          String sql = ServiceManager.getObjectTypeFactory().getInstance(soureBO).getMapingEnty();
          resultset =ServiceManager.getDataStore().retrieve(conn,sql,parameter);
          return (AiscStudyLockBean[])ServiceManager.getDataStore().crateDtaContainerFromResultSet(AiscStudyLockBean.class,AiscStudyLockBean.getObjectTypeStatic(),resultset,null,true);
      }catch(Exception e){
          throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

     public static  AiscStudyLockBean[] getBeansFromSql(String sql,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              resultset =ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (AiscStudyLockBean[])ServiceManager.getDataStore().crateDtaContainerFromResultSet(AiscStudyLockBean.class,AiscStudyLockBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

   public static java.math.BigDecimal getNewId() throws Exception{
       return ServiceManager.getIdGenerator().getNewId(AiscStudyLockBean.getObjectTypeStatic());
   }

/*
   public static java.sql.Timestamp getSysDate() throws Exception{
       return ServiceManager.getIdGenerator().getSysDate(AiscStudyLockBean.getObjectTypeStatic());
   }
*/

   public static AiscStudyLockBean wrap(DataContainerInterface source,Map colMatch,boolean canModify){
     try{
       return (AiscStudyLockBean)DataContainerFactory.wrap(source,AiscStudyLockBean.class,colMatch,canModify);
     }catch(Exception e){
       if(e.getCause()!=null)
         throw new RuntimeException(e.getCause());
       else
         throw new RuntimeException(e);
     }
   }
   public static AiscStudyLockBean copy(DataContainerInterface source,Map colMatch,boolean canModify){
     try {
       AiscStudyLockBean result = new AiscStudyLockBean();
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

   public static AiscStudyLockBean transfer(IAiscStudyLockValue value) {
   	if(value==null)
   		return null;
	try {
		if(value instanceof AiscStudyLockBean){
			return (AiscStudyLockBean)value;
		}
		AiscStudyLockBean newBean = new AiscStudyLockBean();

		DataContainerFactory.transfer(value ,newBean);
		return newBean;
	}catch (Exception ex) {
		if(ex.getCause()!=null)
			throw new RuntimeException(ex.getCause());
		else
			throw new RuntimeException(ex);
	}
   }

   public static AiscStudyLockBean[] transfer(IAiscStudyLockValue[] value) {
   	if(value==null || value.length==0)
   		return null;

	try {
		if(value instanceof AiscStudyLockBean[]){
			return (AiscStudyLockBean[])value;
		}
		AiscStudyLockBean[] newBeans = new AiscStudyLockBean[value.length];
   		for(int i=0;i<newBeans.length;i++){
   			newBeans[i] = new AiscStudyLockBean();
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
  public static void save(IAiscStudyLockValue aValue) throws Exception
  {
     save(transfer(aValue));
  }

   public static  void save( IAiscStudyLockValue[] aValues) throws Exception{
          save(transfer(aValues));
   }

   public static  void saveBatch( IAiscStudyLockValue[] aValues) throws Exception{
     saveBatch(transfer(aValues));
   }
}
