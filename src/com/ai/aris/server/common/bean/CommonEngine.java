package com.ai.aris.server.common.bean;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.*;
import com.ai.appframe2.util.StringUtils;
import com.ai.appframe2.util.criteria.Criteria;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/11/27
 * Time: 15:26
 * Email:zhangfz3@asiainfo.com
 */
public class CommonEngine {
    private static transient Log log = LogFactory.getLog(CommonEngine.class);
    public static <T extends DataContainerInterface> T[] getBeans(T dc) throws
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
            return getBeans(buffer.toString(), pList,(Class<T>)dc.getClass());
        }finally{
            if (conn != null)
                conn.close();
        }
    }

    public static <T extends DataContainerInterface> T getBean(long _Id, Class<T> clz) throws Exception{
        /**new create*/
        String condition = "ID = :S_ID";
        Map map = new HashMap();
        map.put("S_ID",new Long(_Id));
        ;
        T[] beans = getBeans(condition,map,clz);
        if(beans!=null && beans.length==1)
            return beans[0];
        else if(beans!=null && beans.length>1){
            throw new Exception("[错误]根据主键查询出现一条以上记录");
        }else{
            T bean = clz.newInstance();

            Method setMethod = clz.getMethod("setId");
            setMethod.invoke(bean,_Id);
            return bean;
        }
    }
    public static <T extends DataContainerInterface> T getBean(String _Id, Class<T> clz) throws Exception{
        /**new create*/
        T obj = clz.newInstance();
        String[] ids = obj.getKeyPropertyNames();
        String condition = ids[0]+" = :S_ID";
        Map map = new HashMap();
        map.put("S_ID",_Id);

        T[] beans = getBeans(condition,map,clz);
        if(beans!=null && beans.length==1)
            return beans[0];
        else if(beans!=null && beans.length>1){
            throw new Exception("[错误]根据主键查询出现一条以上记录");
        }else{
            T bean = clz.newInstance();
            bean.set(ids[0],_Id);
            return bean;
        }
    }

    public static  <T extends DataContainerInterface> T[] getBeans(Criteria sql, Class<T> clz) throws Exception{
        return getBeans(sql,-1,-1,false,clz);
    }
    public static  <T extends DataContainerInterface> T[] getBeans(Criteria sql, int startNum, int endNum, boolean isShowFK, Class<T> clz) throws Exception{
        String[] cols = null;
        String condition = "";
        if(sql != null){
            cols = (String[])sql.getSelectColumns().toArray(new String[0]);
            condition = sql.toString();
        }
        return getBeans(cols,condition,sql.getParameters(),startNum,endNum,isShowFK,clz);
    }




    public static  <T extends DataContainerInterface> T[] getBeans(String condition, Map parameter, Class<T> clz) throws Exception{
        return getBeans(null,condition,parameter,-1,-1,false,clz);
    }

    public static  <T extends DataContainerInterface> T[] getBeans(String[] cols, String condition, Map parameter,
                                                                   int startNum, int endNum, boolean isShowFK, Class<T> clz) throws Exception{
        Connection conn = null;
        try {
            conn = ServiceManager.getSession().getConnection();
            Method getMethod = clz.getMethod("getObjectTypeStatic");
            ObjectType type = (ObjectType)getMethod.invoke(clz.newInstance());
            return (T[]) ServiceManager.getDataStore().retrieve(conn,clz,type,cols,condition,parameter,startNum,endNum,isShowFK,false,null);
        }catch(Exception e){
            throw e;
        }finally{
            if (conn != null)
                conn.close();
        }
    }

    public static  <T extends DataContainerInterface> T[] getBeans(String[] cols, String condition, Map parameter,
                                                                   int startNum, int endNum, boolean isShowFK, String[] extendBOAttrs, Class<T> clz) throws Exception{
        Connection conn = null;
        try {
            conn = ServiceManager.getSession().getConnection();
            Method getMethod = clz.getMethod("getObjectTypeStatic");
            ObjectType type = (ObjectType)getMethod.invoke(clz.newInstance());
            return (T[]) ServiceManager.getDataStore().retrieve(conn,clz,type,cols,condition,parameter,startNum,endNum,isShowFK,false,extendBOAttrs);
        }catch(Exception e){
            throw e;
        }finally{
            if (conn != null)
                conn.close();
        }
    }


    public static <T extends DataContainerInterface> int getBeansCount(String condition, Map parameter, Class<T> clz) throws Exception{
        Connection conn = null;
        try {
            conn = ServiceManager.getSession().getConnection();
            Method getMethod = clz.getMethod("getObjectTypeStatic");
            ObjectType type = (ObjectType)getMethod.invoke(clz.newInstance());
            return ServiceManager.getDataStore().retrieveCount(conn,type,condition,parameter,null);
        }catch(Exception e){
            throw e;
        }finally{
            if (conn != null)
                conn.close();
        }
    }

    public static <T extends DataContainerInterface> int getBeansCount(String condition, Map parameter, String[] extendBOAttrs, Class<T> clz) throws Exception{
        Connection conn = null;
        try {
            conn = ServiceManager.getSession().getConnection();
            Method getMethod = clz.getMethod("getObjectTypeStatic");
            ObjectType type = (ObjectType)getMethod.invoke(clz.newInstance());
            return ServiceManager.getDataStore().retrieveCount(conn,type,condition,parameter,extendBOAttrs);
        }catch(Exception e){
            throw e;
        }finally{
            if (conn != null)
                conn.close();
        }
    }

    public static <T extends DataContainerInterface> void save(T aBean) throws Exception
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



    public static  <T extends DataContainerInterface> void saveBatch(T[] aBeans) throws Exception{
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


    public static  <T extends DataContainerInterface> T[] getBeansFromQueryBO(String soureBO, Map parameter, Class<T> clz) throws Exception{
        Connection conn = null;
        ResultSet resultset = null;
        try {
            conn = ServiceManager.getSession().getConnection();
            Method getMethod = clz.getMethod("getObjectTypeStatic");
            ObjectType type = (ObjectType)getMethod.invoke(clz.newInstance());
            String sql = ServiceManager.getObjectTypeFactory().getInstance(soureBO).getMapingEnty();
            resultset = ServiceManager.getDataStore().retrieve(conn,sql,parameter);
            return (T[]) ServiceManager.getDataStore().crateDtaContainerFromResultSet(clz,type,resultset,null,true);

        }catch(Exception e){
            throw e;
        }finally{
            if(resultset!=null)resultset.close();
            if (conn != null)
                conn.close();
        }
    }

    public static  <T extends DataContainerInterface> T[] getBeansFromSql(String sql, Map parameter, Class<T> clz) throws Exception{
        Connection conn = null;
        ResultSet resultset = null;
        try {
            conn = ServiceManager.getSession().getConnection();
            Method getMethod = clz.getMethod("getObjectTypeStatic");
            ObjectType type = (ObjectType)getMethod.invoke(clz.newInstance());
            resultset = ServiceManager.getDataStore().retrieve(conn,sql,parameter);
            return (T[]) ServiceManager.getDataStore().crateDtaContainerFromResultSet(clz,type,resultset,null,true);

        }catch(Exception e){
            throw e;
        }finally{
            if(resultset!=null)resultset.close();
            if (conn != null)
                conn.close();
        }
    }

    public static  <T extends DataContainerInterface> java.math.BigDecimal getNewId(Class<T> clz) throws Exception{
        Method getMethod = clz.getMethod("getObjectTypeStatic");
        ObjectType type = (ObjectType)getMethod.invoke(clz.newInstance());
        return ServiceManager.getIdGenerator().getNewId(type);
    }

    public static <T extends DataContainerInterface> T wrap(DataContainerInterface source, Map colMatch, boolean canModify, Class<T> clz){
        try{
            return (T) DataContainerFactory.wrap(source,clz,colMatch,canModify);
        }catch(Exception e){
            if(e.getCause()!=null)
                throw new RuntimeException(e.getCause());
            else
                throw new RuntimeException(e);
        }
    }
    public static <T extends DataContainerInterface> T copy(DataContainerInterface source, Map colMatch, boolean canModify, Class<T> clz){
        try {
            T result = clz.newInstance();
            DataContainerFactory.copy(source, result, colMatch);
            return result;
        }
        catch (AIException ex) {
            if(ex.getCause()!=null)
                throw new RuntimeException(ex.getCause());
            else
                throw new RuntimeException(ex);
        } catch (InstantiationException e) {
            if(e.getCause()!=null)
                throw new RuntimeException(e.getCause());
            else
                throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            if(e.getCause()!=null)
                throw new RuntimeException(e.getCause());
            else
                throw new RuntimeException(e);
        }
    }

    public static void execute(String sql, Map parameter) throws Exception {
        Connection conn = null;
        try {
            conn = ServiceManager.getSession().getConnection();
            ServiceManager.getDataStore().execute(conn, sql, parameter);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null)
                conn.close();
        }
    }

    public static  <T extends DataContainerInterface> T getBean(String condition, Map parameter, Class<T> clz) throws Exception{
        T[] beans = getBeans(null,condition,parameter,-1,-1,false,clz);
        if(beans != null&&beans.length>0){
            return (T)beans[0];
        }
        return null;
    }
    public static Timestamp getSysDateFromDB() throws Exception{
        Connection conn = null;
        try {
            conn =  ServiceManager.getSession().getConnection();
            Timestamp timestamp = ServiceManager.getDataStore().getSysDateFromDB();
            return timestamp;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null)
                conn.close();
        }
    }

    public static String getCurrentDate(String format) throws Exception{
        Timestamp time = getSysDateFromDB();
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return  sdf.format(time);
    }
    /**
     * select count(1) from ...
     * */
    public static int getCountFromSql(String sql,Map aParameterList) throws Exception {
        Connection conn = null;
        int result = -1;
        try {
            conn = ServiceManager.getSession().getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            String[] parameterNames = StringUtils.getParamFromString(sql, ":", " ");
            ArrayList tempList = new ArrayList();
            for(int tableParameterNames = 0; aParameterList != null && parameterNames != null && tableParameterNames < parameterNames.length; ++tableParameterNames) {
                if(aParameterList.containsKey(parameterNames[tableParameterNames])) {
                    tempList.add(parameterNames[tableParameterNames]);
                }
            }
            parameterNames = (String[])tempList.toArray(new String[0]);
            sql = StringUtils.replaceParamString(sql, parameterNames, "?", ":", " ");
            for(int var24 = 0; parameterNames != null && var24 < parameterNames.length; ++var24) {
                DataType.setPrepareStatementParameter(pst, var24 + 1, "Object", aParameterList.get(parameterNames[var24]));
            }
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null)
                conn.close();
        }
        return result;
    }

    public static int getCountFromBeanSql(String sql,Map aParameterList) throws Exception {
        Connection conn = null;
        int result = -1;
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append(" select count(1) from (").append(sql).append(" ) ");

            if(log.isDebugEnabled()) {
                log.debug(buffer.toString());
            }
            conn = ServiceManager.getSession().getConnection();
            PreparedStatement pst = conn.prepareStatement(buffer.toString());
            String[] parameterNames = StringUtils.getParamFromString(buffer.toString(), ":", " ");
            ArrayList tempList = new ArrayList();
            for(int tableParameterNames = 0; aParameterList != null && parameterNames != null && tableParameterNames < parameterNames.length; ++tableParameterNames) {
                if(aParameterList.containsKey(parameterNames[tableParameterNames])) {
                    tempList.add(parameterNames[tableParameterNames]);
                }
            }
            parameterNames = (String[])tempList.toArray(new String[0]);
            sql = StringUtils.replaceParamString(buffer.toString(), parameterNames, "?", ":", " ");
            for(int var24 = 0; parameterNames != null && var24 < parameterNames.length; ++var24) {
                DataType.setPrepareStatementParameter(pst, var24 + 1, "Object", aParameterList.get(parameterNames[var24]));
                if(log.isDebugEnabled()) {
                    log.debug(parameterNames[var24] + " = " + aParameterList.get(parameterNames[var24]));
                }
            }
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null)
                conn.close();
        }
        return result;
    }

    public static  <T extends DataContainerInterface> T[] getBeansFromSql(String sql, Map parameter, int startNum, int endNum, Class<T> clz) throws Exception{
        Connection conn = null;
        ResultSet resultset = null;
        try {
            StringBuffer buffer = new StringBuffer(sql);
            if(startNum >= 0 || endNum >= 0) {
                buffer.insert(0, "select * from (select B.*,rownum as row_index from ( ");
                if(endNum >= 0) {
                    buffer.append(" ) B where rownum<= " + endNum + " ) where ");
                } else {
                    buffer.append(" ) B   ) where ");
                }
                if(startNum >= 0) {
                    buffer.append(" row_index >=").append(startNum);
                }
            }
            if(log.isDebugEnabled()) {
                log.debug(buffer.toString());
            }

            conn = ServiceManager.getSession().getConnection();
            Method getMethod = clz.getMethod("getObjectTypeStatic");
            ObjectType type = (ObjectType)getMethod.invoke(clz.newInstance());
            resultset = ServiceManager.getDataStore().retrieve(conn,buffer.toString(),parameter);
            return (T[]) ServiceManager.getDataStore().crateDtaContainerFromResultSet(clz,type,resultset,null,true);

        }catch(Exception e){
            throw e;
        }finally{
            if(resultset!=null)resultset.close();
            if (conn != null)
                conn.close();
        }
    }

    //默认参数类型全部varchar2
    public static  void callProc(String procName,String...params) throws Exception{
        Connection conn = null;
        try {
            conn = ServiceManager.getSession().getConnection();
            StringBuffer sql= new StringBuffer("{call "+procName+"(");

            if(params!=null){
                for(int i=0;i<params.length;i++) {
                    if(i==params.length-1){
                        sql.append("?");
                        continue;
                    }
                    sql.append("?,");

                }
            }
            sql.append(")}");
            CallableStatement cst=conn.prepareCall(sql.toString());
            for(int i=0;i<params.length;i++) {
                cst.setString(1, params[i]);//设置in参数的值
            }
            cst.execute();

        }catch(Exception e){
            throw e;
        }finally{
            if (conn != null)
                conn.close();
        }
    }

    //默认参数类型全部varchar2
    public static  <T> T callFunc(String funcName,Class<T> clz,String...params) throws Exception {
            Connection conn = null;
            ResultSet rs = null;
            try {
                conn = ServiceManager.getSession().getConnection();
                StringBuffer sql= new StringBuffer("select "+funcName+"(");

                if(params!=null){
                    for(int i=0;i<params.length;i++) {
                        if(i==params.length-1){
                            sql.append("'"+params[i]+"'");
                            continue;
                        }
                        sql.append("'"+params[i]+"',");

                    }
                }
                sql.append(") FROM DUAL");
                PreparedStatement stmt =conn.prepareStatement(sql.toString());
                rs = stmt.executeQuery();
                if(rs.next()) {
                    if (clz == String.class) {
                        return (T) rs.getString(1);
                    } else if (clz == Float.class) {
                        return clz.cast(rs.getFloat(1));
                    } else if (clz == Integer.class) {
                        return clz.cast(rs.getInt(1));
                    } else {
                        throw new Exception("不支持的类型");
                    }
                }else{
                    return null;
                }
            }catch(Exception e){
                throw e;
            }finally{
                if(rs!=null)rs.close();
                if (conn != null)
                    conn.close();
            }
    }


    public static <T> T getObjFromSql(String sql,Map parameter,Class<T> clz) throws Exception{
        Connection conn = null;
        ResultSet resultset = null;
        try {
            if(log.isDebugEnabled()) {
                log.debug(sql);
            }

            conn = ServiceManager.getSession().getConnection();
            resultset = ServiceManager.getDataStore().retrieve(conn,sql,parameter);
            if(resultset.next()) {
                if (clz == String.class) {
                    return (T) resultset.getString(1);
                } else if (clz == Float.class) {
                    return clz.cast(resultset.getFloat(1));
                } else if (clz == Integer.class) {
                    return clz.cast(resultset.getInt(1));
                } else {
                    throw new Exception("不支持的类型");
                }
            }else{
                return null;
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(resultset!=null)resultset.close();
            if (conn != null)
                conn.close();
        }
    }
}
