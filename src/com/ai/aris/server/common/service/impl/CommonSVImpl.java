package com.ai.aris.server.common.service.impl;

import java.sql.Timestamp;
import java.util.Map;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.util.criteria.Criteria;
import com.ai.aris.server.common.bean.CommonEngine;
import com.ai.aris.server.common.service.interfaces.ICommonSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class CommonSVImpl implements ICommonSV {
    public <T extends DataContainerInterface> T[] getBeans(String condition, Map parameter, Class<T> clz) throws Exception{
        return CommonEngine.getBeans(condition,parameter,clz);
    }
    public <T extends DataContainerInterface> T getBean(String condition, Map parameter, Class<T> clz) throws Exception{
        return CommonEngine.getBean(condition, parameter, clz);
    }

    public Timestamp getSysDateFromDB() throws Exception{
        return CommonEngine.getSysDateFromDB();
    }
    public String getCurrentDate(String  format) throws Exception{
        return CommonEngine.getCurrentDate(format);
    }

    public <T extends DataContainerInterface> void saveBatch(T[] aBeans) throws Exception{
        CommonEngine.saveBatch(aBeans);
    }

    public void callProc(String procName,String...params) throws Exception{
        CommonEngine.callProc(procName,params);
    }
    public <T extends DataContainerInterface> T[] getBeansFromSql(String sql, Map parameter, Class<T> clz) throws  Exception{
       return CommonEngine.getBeansFromSql(sql,parameter,clz);
    }
    public void execute(String sql,Map parameter) throws Exception{
        CommonEngine.execute(sql,parameter);
    }

    public <T> T getObjFromSql(String sql,Map parameter,Class<T> clz) throws Exception{
        return CommonEngine.getObjFromSql(sql,parameter,clz);
    }

    public <T>T callFunc(String procName, Class<T> clz, String...params) throws Exception{
       return CommonEngine.callFunc(procName,clz,params);
    }
    public  long getSequence(String sequence) throws Exception {
        return ServiceUtil.getSequence(sequence);
    }
    public  String getStringSequence(String sequence) throws Exception {
        return ServiceUtil.getStringSequence(sequence);
    }

    @Override
    /**
     * 公共分页方法
     * @param conditionCount 统计总数SQL
     * @param conditionRecord 查询记录集SQL
     * @param resultDTO 分页参数
     * @param dicts 字典参数
     * @param modelClz 翻译model类参数
     * @param clz bean类参数
     * */
    public <S,T extends DataContainerInterface>ResultDTO<T> queryPageList(String conditionCount, String conditionRecord, ResultDTO resultDTO, Map<String, DictTranslator> dicts, Class<S> modelClz, Class<T> clz) throws Exception {
        int total = CommonEngine.getBeansCount(conditionCount, null,clz);

        T[] beans = null;
        if (total > 0) {
            beans = CommonEngine.getBeans(null, conditionRecord, null, resultDTO.getStart(), resultDTO.getEnd(), false,clz);
        }
        if(dicts==null)
            resultDTO.setRows(BeanUtils.beanToList(beans, modelClz));
        else
            resultDTO.setRows(BeanUtils.beanToList(beans, modelClz,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    /**
     * 公共分页方法
     *
     * @param conditionCount  统计总数Criteria
     * @param conditionRecord 查询记录集Criteria
     * @param resultDTO       分页参数
     * @param dicts           字典参数
     * @param modelClz        翻译model类参数
     * @param clz             bean类参数
     */
    public <S,T extends DataContainerInterface> ResultDTO<T> queryPageList(Criteria conditionCount, Criteria conditionRecord, ResultDTO resultDTO, Map<String, DictTranslator> dicts, Class<S> modelClz, Class<T> clz) throws Exception{
        int total = CommonEngine.getBeansCount(conditionCount.toString(), conditionCount.getParameters(),clz);

        T[] beans = null;
        if (total > 0) {
            beans = CommonEngine.getBeans(conditionRecord, resultDTO.getStart(), resultDTO.getEnd(), false,clz);
        }
        if(dicts==null)
            resultDTO.setRows(BeanUtils.beanToList(beans, modelClz));
        else
            resultDTO.setRows(BeanUtils.beanToList(beans, modelClz,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    /**
     * 公共保存方法
     * @param aBean 新增或修改一个bean，只考虑一个PK的情况
     * */
    public <T extends DataContainerInterface> void save(T aBean) throws Exception{
        Map<String,Object> pks = (Map<String,Object>)aBean.getKeyProperties();
        Map.Entry<String,Object> pk = pks.entrySet().iterator().next();

        if(pk.getValue()==null){ //新增
           String javaType = aBean.getObjectType().getProperty(pk.getKey()).getJavaDataType();
           if("Long".equals(javaType)){
               long id = getSequence("SEQ_"+aBean.getTableName());
               aBean.set(pk.getKey(),id);
           }else {
               String id = getStringSequence("SEQ_" + aBean.getTableName());
               aBean.set(pk.getKey(),id);
           }
           CommonEngine.save(aBean);
        }else{//修改
            T oldBean = CommonEngine.getBean(pk.getValue().toString(),(Class<T>)aBean.getClass());
            DataContainerFactory.copyNoClearData(aBean, oldBean);
            CommonEngine.save(oldBean);
        }
    }
    /**
     * 公共保存方法
     *
     * @param aBean 新增一个bean且前台设定PK的情况
     */
    public <T extends DataContainerInterface> void insert(T aBean) throws Exception{
        CommonEngine.save(aBean);
    }
    /**
     * 通过PK公共查询单个bean对象
     * */
    public <T extends DataContainerInterface> T getBean(String id,Class<T> clz) throws Exception{
        return CommonEngine.getBean(id,clz);
    }
    /**
     * 通过PK公共查询单个bean对象
     * */
    public <T extends DataContainerInterface> T getBean(long id,Class<T> clz) throws Exception{
        return CommonEngine.getBean(id,clz);
    }

    @Override
    /**
     * 公共删除方法
     * @param aBean bean对象
     */
    public  <T extends DataContainerInterface> void  delete(T aBean) throws Exception {
        aBean.setStsToOld();
        aBean.delete();
        CommonEngine.save(aBean);
    }
    public  <T extends DataContainerInterface> void deleteBatch(String ids,Class<T> clz) throws Exception{
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            // 基本信息
            T aBean = CommonEngine.getBean(id,clz);
            aBean.setStsToOld();
            aBean.delete();
            CommonEngine.save(aBean);
        }
    }
}
