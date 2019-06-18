package com.ai.aris.server.sysmanage.bean;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.util.criteria.Criteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class SysOperatorLogEngine {

  public static SysOperatorLogBean[] getBeans(DataContainerInterface dc) throws
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

    public static SysOperatorLogBean getBean(long _LogId) throws Exception{
            /**new create*/
    String condition = "LOG_ID = :S_LOG_ID";
      Map map = new HashMap();
      map.put("S_LOG_ID",new Long(_LogId));
;
      SysOperatorLogBean[] beans = getBeans(condition,map);
      if(beans!=null && beans.length==1)
              return beans[0];
      else if(beans!=null && beans.length>1){
              throw new Exception("[错误]根据主键查询出现一条以上记录");
      }else{
              SysOperatorLogBean bean = new SysOperatorLogBean();
                            bean.setLogId(_LogId);
                            return bean;
      }
 }

  public static  SysOperatorLogBean[] getBeans(Criteria sql) throws Exception{
   return getBeans(sql,-1,-1,false);
  }
 public static  SysOperatorLogBean[] getBeans(Criteria sql, int startNum, int endNum, boolean isShowFK) throws Exception{
    String[] cols = null;
    String condition = "";
    if(sql != null){
      cols = (String[])sql.getSelectColumns().toArray(new String[0]);
      condition = sql.toString();
    }
    return getBeans(cols,condition,sql.getParameters(),startNum,endNum,isShowFK);
  }




  public static  SysOperatorLogBean[] getBeans(String condition,Map parameter) throws Exception{
	return getBeans(null,condition,parameter,-1,-1,false);
  }

  public static  SysOperatorLogBean[] getBeans(String[] cols,String condition,Map parameter,
           int startNum,int endNum,boolean isShowFK) throws Exception{
        Connection conn = null;
        try {
                conn = ServiceManager.getSession().getConnection();
                return (SysOperatorLogBean[]) ServiceManager.getDataStore().retrieve(conn,SysOperatorLogBean.class,SysOperatorLogBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,null);
        }catch(Exception e){
                throw e;
        }finally{
           if (conn != null)
              conn.close();
        }
  }

   public static  SysOperatorLogBean[] getBeans(String[] cols,String condition,Map parameter,
          int startNum,int endNum,boolean isShowFK,String[] extendBOAttrs) throws Exception{
          Connection conn = null;
          try {
                  conn = ServiceManager.getSession().getConnection();
                  return (SysOperatorLogBean[]) ServiceManager.getDataStore().retrieve(conn,SysOperatorLogBean.class,SysOperatorLogBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,extendBOAttrs);
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
              return ServiceManager.getDataStore().retrieveCount(conn,SysOperatorLogBean.getObjectTypeStatic(),condition,parameter,null);
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
                return ServiceManager.getDataStore().retrieveCount(conn,SysOperatorLogBean.getObjectTypeStatic(),condition,parameter,extendBOAttrs);
        }catch(Exception e){
                throw e;
        }finally{
          if (conn != null)
              conn.close();
        }
   }

  public static void save( SysOperatorLogBean aBean) throws Exception
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

   public static  void save( SysOperatorLogBean[] aBeans) throws Exception{
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

   public static  void saveBatch( SysOperatorLogBean[] aBeans) throws Exception{
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


  public static  SysOperatorLogBean[] getBeansFromQueryBO(String soureBO,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              String sql = ServiceManager.getObjectTypeFactory().getInstance(soureBO).getMapingEnty();
              resultset = ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (SysOperatorLogBean[]) ServiceManager.getDataStore().crateDtaContainerFromResultSet(SysOperatorLogBean.class,SysOperatorLogBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

     public static  SysOperatorLogBean[] getBeansFromSql(String sql,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              resultset = ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (SysOperatorLogBean[]) ServiceManager.getDataStore().crateDtaContainerFromResultSet(SysOperatorLogBean.class,SysOperatorLogBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

   public static java.math.BigDecimal getNewId() throws Exception{
       return ServiceManager.getIdGenerator().getNewId(SysOperatorLogBean.getObjectTypeStatic());
   }

/*
   public static java.sql.Timestamp getSysDate() throws Exception{
       return ServiceManager.getIdGenerator().getSysDate(SysOperatorLogBean.getObjectTypeStatic());
   }
*/

   public static SysOperatorLogBean wrap(DataContainerInterface source, Map colMatch, boolean canModify){
     try{
       return (SysOperatorLogBean) DataContainerFactory.wrap(source,SysOperatorLogBean.class,colMatch,canModify);
     }catch(Exception e){
       if(e.getCause()!=null)
         throw new RuntimeException(e.getCause());
       else
         throw new RuntimeException(e);
     }
   }
   public static SysOperatorLogBean copy(DataContainerInterface source, Map colMatch, boolean canModify){
     try {
       SysOperatorLogBean result = new SysOperatorLogBean();
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

   public static SysOperatorLogBean transfer(ISysOperatorLogValue value) {
   	if(value==null)
   		return null;
	try {
		if(value instanceof SysOperatorLogBean){
			return (SysOperatorLogBean)value;
		}
		SysOperatorLogBean newBean = new SysOperatorLogBean();

		DataContainerFactory.transfer(value ,newBean);
		return newBean;
	}catch (Exception ex) {
		if(ex.getCause()!=null)
			throw new RuntimeException(ex.getCause());
		else
			throw new RuntimeException(ex);
	}
   }

   public static SysOperatorLogBean[] transfer(ISysOperatorLogValue[] value) {
   	if(value==null || value.length==0)
   		return null;

	try {
		if(value instanceof SysOperatorLogBean[]){
			return (SysOperatorLogBean[])value;
		}
		SysOperatorLogBean[] newBeans = new SysOperatorLogBean[value.length];
   		for(int i=0;i<newBeans.length;i++){
   			newBeans[i] = new SysOperatorLogBean();
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
  public static void save(ISysOperatorLogValue aValue) throws Exception
  {
     save(transfer(aValue));
  }

   public static  void save( ISysOperatorLogValue[] aValues) throws Exception{
          save(transfer(aValues));
   }

   public static  void saveBatch( ISysOperatorLogValue[] aValues) throws Exception{
     saveBatch(transfer(aValues));
   }
}
