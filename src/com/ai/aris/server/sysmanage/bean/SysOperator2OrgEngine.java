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

public class SysOperator2OrgEngine {

  public static SysOperator2OrgBean[] getBeans(DataContainerInterface dc) throws
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

    public static SysOperator2OrgBean getBean(String _OperatorCode,String _StationCode,String _OrgId) throws Exception{
            /**new create*/
    String condition = "OPERATOR_CODE = :S_OPERATOR_CODE and STATION_CODE = :S_STATION_CODE and ORG_ID = :S_ORG_ID";
      Map map = new HashMap();
      map.put("S_OPERATOR_CODE",_OperatorCode);
      map.put("S_STATION_CODE",_StationCode);
      map.put("S_ORG_ID",_OrgId);
;
      SysOperator2OrgBean[] beans = getBeans(condition,map);
      if(beans!=null && beans.length==1)
              return beans[0];
      else if(beans!=null && beans.length>1){
              throw new Exception("[错误]根据主键查询出现一条以上记录");
      }else{
              SysOperator2OrgBean bean = new SysOperator2OrgBean();
                            bean.setOperatorCode(_OperatorCode);
                            bean.setStationCode(_StationCode);
                            bean.setOrgId(_OrgId);
                            return bean;
      }
 }

  public static  SysOperator2OrgBean[] getBeans(Criteria sql) throws Exception{
   return getBeans(sql,-1,-1,false);
  }
 public static  SysOperator2OrgBean[] getBeans(Criteria sql, int startNum, int endNum, boolean isShowFK) throws Exception{
    String[] cols = null;
    String condition = "";
    if(sql != null){
      cols = (String[])sql.getSelectColumns().toArray(new String[0]);
      condition = sql.toString();
    }
    return getBeans(cols,condition,sql.getParameters(),startNum,endNum,isShowFK);
  }




  public static  SysOperator2OrgBean[] getBeans(String condition,Map parameter) throws Exception{
	return getBeans(null,condition,parameter,-1,-1,false);
  }

  public static  SysOperator2OrgBean[] getBeans(String[] cols,String condition,Map parameter,
           int startNum,int endNum,boolean isShowFK) throws Exception{
        Connection conn = null;
        try {
                conn = ServiceManager.getSession().getConnection();
                return (SysOperator2OrgBean[]) ServiceManager.getDataStore().retrieve(conn,SysOperator2OrgBean.class,SysOperator2OrgBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,null);
        }catch(Exception e){
                throw e;
        }finally{
           if (conn != null)
              conn.close();
        }
  }

   public static  SysOperator2OrgBean[] getBeans(String[] cols,String condition,Map parameter,
          int startNum,int endNum,boolean isShowFK,String[] extendBOAttrs) throws Exception{
          Connection conn = null;
          try {
                  conn = ServiceManager.getSession().getConnection();
                  return (SysOperator2OrgBean[]) ServiceManager.getDataStore().retrieve(conn,SysOperator2OrgBean.class,SysOperator2OrgBean.getObjectTypeStatic(),cols,condition,parameter,startNum,endNum,isShowFK,false,extendBOAttrs);
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
              return ServiceManager.getDataStore().retrieveCount(conn,SysOperator2OrgBean.getObjectTypeStatic(),condition,parameter,null);
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
                return ServiceManager.getDataStore().retrieveCount(conn,SysOperator2OrgBean.getObjectTypeStatic(),condition,parameter,extendBOAttrs);
        }catch(Exception e){
                throw e;
        }finally{
          if (conn != null)
              conn.close();
        }
   }

  public static void save( SysOperator2OrgBean aBean) throws Exception
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

   public static  void save( SysOperator2OrgBean[] aBeans) throws Exception{
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

   public static  void saveBatch( SysOperator2OrgBean[] aBeans) throws Exception{
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


  public static  SysOperator2OrgBean[] getBeansFromQueryBO(String soureBO,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              String sql = ServiceManager.getObjectTypeFactory().getInstance(soureBO).getMapingEnty();
              resultset = ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (SysOperator2OrgBean[]) ServiceManager.getDataStore().crateDtaContainerFromResultSet(SysOperator2OrgBean.class,SysOperator2OrgBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

     public static  SysOperator2OrgBean[] getBeansFromSql(String sql,Map parameter) throws Exception{
      Connection conn = null;
      ResultSet resultset = null;
      try {
              conn = ServiceManager.getSession().getConnection();
              resultset = ServiceManager.getDataStore().retrieve(conn,sql,parameter);
              return (SysOperator2OrgBean[]) ServiceManager.getDataStore().crateDtaContainerFromResultSet(SysOperator2OrgBean.class,SysOperator2OrgBean.getObjectTypeStatic(),resultset,null,true);

      }catch(Exception e){
              throw e;
      }finally{
          if(resultset!=null)resultset.close();
          if (conn != null)
              conn.close();
      }
   }

   public static java.math.BigDecimal getNewId() throws Exception{
       return ServiceManager.getIdGenerator().getNewId(SysOperator2OrgBean.getObjectTypeStatic());
   }

/*
   public static java.sql.Timestamp getSysDate() throws Exception{
       return ServiceManager.getIdGenerator().getSysDate(SysOperator2OrgBean.getObjectTypeStatic());
   }
*/

   public static SysOperator2OrgBean wrap(DataContainerInterface source, Map colMatch, boolean canModify){
     try{
       return (SysOperator2OrgBean) DataContainerFactory.wrap(source,SysOperator2OrgBean.class,colMatch,canModify);
     }catch(Exception e){
       if(e.getCause()!=null)
         throw new RuntimeException(e.getCause());
       else
         throw new RuntimeException(e);
     }
   }
   public static SysOperator2OrgBean copy(DataContainerInterface source, Map colMatch, boolean canModify){
     try {
       SysOperator2OrgBean result = new SysOperator2OrgBean();
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

   public static SysOperator2OrgBean transfer(ISysOperator2OrgValue value) {
   	if(value==null)
   		return null;
	try {
		if(value instanceof SysOperator2OrgBean){
			return (SysOperator2OrgBean)value;
		}
		SysOperator2OrgBean newBean = new SysOperator2OrgBean();

		DataContainerFactory.transfer(value ,newBean);
		return newBean;
	}catch (Exception ex) {
		if(ex.getCause()!=null)
			throw new RuntimeException(ex.getCause());
		else
			throw new RuntimeException(ex);
	}
   }

   public static SysOperator2OrgBean[] transfer(ISysOperator2OrgValue[] value) {
   	if(value==null || value.length==0)
   		return null;

	try {
		if(value instanceof SysOperator2OrgBean[]){
			return (SysOperator2OrgBean[])value;
		}
		SysOperator2OrgBean[] newBeans = new SysOperator2OrgBean[value.length];
   		for(int i=0;i<newBeans.length;i++){
   			newBeans[i] = new SysOperator2OrgBean();
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
  public static void save(ISysOperator2OrgValue aValue) throws Exception
  {
     save(transfer(aValue));
  }

   public static  void save( ISysOperator2OrgValue[] aValues) throws Exception{
          save(transfer(aValues));
   }

   public static  void saveBatch( ISysOperator2OrgValue[] aValues) throws Exception{
     saveBatch(transfer(aValues));
   }
}
