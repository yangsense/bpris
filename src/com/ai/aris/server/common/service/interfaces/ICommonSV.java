package com.ai.aris.server.common.service.interfaces;

import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.util.criteria.Criteria;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/12/31
 * Time: 10:53
 * Email:zhangfz3@asiainfo.com
 */
public interface ICommonSV {
    Timestamp getSysDateFromDB() throws Exception;

    String getCurrentDate(String format) throws Exception;

    <T extends DataContainerInterface> void saveBatch(T[] aBeans) throws Exception;

    void callProc(String procName, String... params) throws Exception;

    <T extends DataContainerInterface> T[] getBeansFromSql(String sql, Map parameter, Class<T> clz) throws Exception;

    void execute(String sql, Map parameter) throws Exception;

    <T> T getObjFromSql(String sql, Map parameter, Class<T> clz) throws Exception;

    <T> T callFunc(String procName, Class<T> clz, String... params) throws Exception;

    long getSequence(String sequence) throws Exception;

    String getStringSequence(String sequence) throws Exception;

    /**
     * 公共分页方法
     *
     * @param conditionCount  统计总数SQL
     * @param conditionRecord 查询记录集SQL
     * @param resultDTO       分页参数
     * @param dicts           字典参数
     * @param modelClz        翻译model类参数
     * @param clz             bean类参数
     */
    <S,T extends DataContainerInterface> ResultDTO<T> queryPageList(String conditionCount, String conditionRecord, ResultDTO resultDTO, Map<String, DictTranslator> dicts, Class<S> modelClz, Class<T> clz) throws Exception;

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
    <S,T extends DataContainerInterface> ResultDTO<T> queryPageList(Criteria conditionCount, Criteria conditionRecord, ResultDTO resultDTO, Map<String, DictTranslator> dicts, Class<S> modelClz, Class<T> clz) throws Exception;


    /**
     * 公共保存方法
     *
     * @param aBean 新增或修改一个bean，只考虑一个PK的情况
     */
    <T extends DataContainerInterface> void save(T aBean) throws Exception;

    /**
     * 公共保存方法
     *
     * @param aBean 新增一个bean且前台设定PK的情况
     */
    <T extends DataContainerInterface> void insert(T aBean) throws Exception;
    /**
     * 公共查询单个bean对象
     *
     * @param condition 查询条件
     * @param parameter 查询参数
     * @param clz       bean类参数
     */
    <T extends DataContainerInterface> T getBean(String condition, Map parameter, Class<T> clz) throws Exception;

    /**
     * 公共查询bean集合
     *
     * @param condition 查询条件
     * @param parameter 查询参数
     * @param clz       bean类参数
     */
    <T extends DataContainerInterface> T[] getBeans(String condition, Map parameter, Class<T> clz) throws Exception;

    /**
     * 通过PK公共查询单个bean对象
     *
     * @param id  查主键
     * @param clz bean类参数
     */
    <T extends DataContainerInterface> T getBean(String id, Class<T> clz) throws Exception;

    /**
     * 通过PK公共查询单个bean对象
     *
     * @param id  查主键
     * @param clz bean类参数
     */
    <T extends DataContainerInterface> T getBean(long id, Class<T> clz) throws Exception;
    /**
     * 公共删除方法
     * @param aBean bean对象
     */
    <T extends DataContainerInterface> void delete(T aBean) throws Exception;
    /**
     * 公共批量删除方法
     * @param ids 主键集合
     */
    <T extends DataContainerInterface> void deleteBatch(String ids,Class<T> clz) throws Exception;
}
