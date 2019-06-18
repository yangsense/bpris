package com.ai.aris.server.basecode.bean;

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

import com.ai.aris.server.basecode.bean.*;

public class AiscTpcat2LocEngine {

  public static AiscTpcat2LocBean[] getBeans(DataContainerInterface dc) throws
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

    public static AiscTpcat2LocBean getBean(long _Tplcat2locId) throws Exception{
            /**new create*/
    String condition = "TPLCAT2LOC_ID = :S_TPLCAT2LOC_ID";
      Map map = new HashMap();
      map.put("S_TPLCAT2LOC_ID",new Long(_Tplcat2locId));
;
      AiscTpcat2LocBean[] beans = getBeans(condition,map);
      if(beans!=null && beans.length==1)
              return beans[0];
      else if(beans!=null && beans.length>1){
              throw new Exception("[错误]根据主键查询出现一条以上记录");
      }else{
              AiscTpcat2LocBean bean = new AiscTpcat2LocBean();
                            bean.setTplcat2locId(_Tplcat2locId);
                            return bean;
      }
 }

  public static  AiscTpcat2LocBean[] getBeans(Criteria sql) throws Exception{
   return getBeans(sql,-1,-1,false);
  }
 public static  AiscTpcat2LocBean[] getBeans(Criteria sql,int startNum,int endNum,boolean isShowFK) throws Exception{
    String[] cols = null;
    String condition = "";
    if(sql != null){
      cols = (String[])sql.getSelectColumns().toArray(new String[0]);
      condition = sql.toString();
    }
    return (AiscTpcat2LocBean[])getBeans(cols,condition,sql.getParameters(),startNum,endNum,isShowFK);
  }




  public static  AiscTpcat2LocBean[] getBeans(String condition,Map parameter) throws Exception{
	return getBeans(null,condition,parameter,-1,-1,false);
  }

  public static  AiscTpcat2LocBean[] getBeans(String[] cols,String condition,Map parameter,
           int startNum,int endNum,boolean isShowFK) throws Exception{
        Connection conn = null;
        try {
                conn = ServiceManager.getSession().getConnection();
                return (AiscTpcat2LocBean[])ServiceManager.getDataStore().retrieve(conn,AiscTpcat2LocBean.class,AiscTpcat2LocBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,null);
        }catch(Exception e){
                throw e;
        }finally{
           if (conn != null)
              conn.close();
        }
  }

   public static  AiscTpcat2LocBean[] getBeans(String[] cols,String condition,Map parameter,
          int startNum,int endNum,boolean isShowFK,String[] extendBOAttrs) throws Exception{
          Connection conn = null;
          try {
                  conn = ServiceManager.getSession().getConnection();
                  return (AiscTpcat2LocBean[])ServiceManager.getDataStore().retrieve(conn,AiscTpcat2LocBean.class,AiscTpcat2LocBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,extendBOAttrs);
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
              return ServiceManager.getDataStore().retrieveCount(conn,AiscTpcat2LocBean.getObjectTypeStatic(),condition,parameter,null);
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
                return ServiceManager.getDataStore().retrieveCount(conn,AiscTpcat2LocBean.getObjectTypeStatic(),condition,parameter,extendBOAttrs);
        }catch(Exception e){
                throw e;
        }finally{
          if (conn != null)
              conn.close();
        }
   }

  public static void save( AiscTpcat2LocBean aBean) throws Exception
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

   public static  void save( AiscTpcat2LocBean[] aBeans) throws Exception{
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

   public static  void saveBatch( AiscTpcat2LocBean[] aBeans) throws Exception{
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


  public static  AiscTpcat2LocBean[] getBeansFromQueryBO(String soureBO,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              String sql = ServiceManager.getObjectTypeFactory().getInstance(soureBO).getMapingEnty();
              resultset =ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (AiscTpcat2LocBean[])ServiceManager.getDataStore().crateDtaContainerFromResultSet(AiscTpcat2LocBean.class,AiscTpcat2LocBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

     public static  AiscTpcat2LocBean[] getBeansFromSql(String sql,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              resultset =ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (AiscTpcat2LocBean[])ServiceManager.getDataStore().crateDtaContainerFromResultSet(AiscTpcat2LocBean.class,AiscTpcat2LocBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

   public static java.math.BigDecimal getNewId() throws Exception{
       return ServiceManager.getIdGenerator().getNewId(AiscTpcat2LocBean.getObjectTypeStatic());
   }

/*
   public static java.sql.Timestamp getSysDate() throws Exception{
       return ServiceManager.getIdGenerator().getSysDate(AiscTpcat2LocBean.getObjectTypeStatic());
   }
*/

   public static AiscTpcat2LocBean wrap(DataContainerInterface source,Map colMatch,boolean canModify){
     try{
       return (AiscTpcat2LocBean)DataContainerFactory.wrap(source,AiscTpcat2LocBean.class,colMatch,canModify);
     }catch(Exception e){
       if(e.getCause()!=null)
         throw new RuntimeException(e.getCause());
       else
         throw new RuntimeException(e);
     }
   }
   public static AiscTpcat2LocBean copy(DataContainerInterface source,Map colMatch,boolean canModify){
     try {
       AiscTpcat2LocBean result = new AiscTpcat2LocBean();
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

   public static AiscTpcat2LocBean transfer(IAiscTpcat2LocValue value) {
   	if(value==null)
   		return null;
	try {
		if(value instanceof AiscTpcat2LocBean){
			return (AiscTpcat2LocBean)value;
		}
		AiscTpcat2LocBean newBean = new AiscTpcat2LocBean();

		DataContainerFactory.transfer(value ,newBean);
		return newBean;
	}catch (Exception ex) {
		if(ex.getCause()!=null)
			throw new RuntimeException(ex.getCause());
		else
			throw new RuntimeException(ex);
	}
   }

   public static AiscTpcat2LocBean[] transfer(IAiscTpcat2LocValue[] value) {
   	if(value==null || value.length==0)
   		return null;

	try {
		if(value instanceof AiscTpcat2LocBean[]){
			return (AiscTpcat2LocBean[])value;
		}
		AiscTpcat2LocBean[] newBeans = new AiscTpcat2LocBean[value.length];
   		for(int i=0;i<newBeans.length;i++){
   			newBeans[i] = new AiscTpcat2LocBean();
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
  public static void save(IAiscTpcat2LocValue aValue) throws Exception
  {
     save(transfer(aValue));
  }

   public static  void save( IAiscTpcat2LocValue[] aValues) throws Exception{
          save(transfer(aValues));
   }

   public static  void saveBatch( IAiscTpcat2LocValue[] aValues) throws Exception{
     saveBatch(transfer(aValues));
   }
}
