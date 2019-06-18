package com.ai.aris.server.publicsv.impl;


import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.bean.CommonEngine;
import com.ai.aris.server.publicsv.interfaces.ISysManagePublicSV;
import com.ai.aris.server.sysmanage.bean.*;
import com.ai.aris.util.cache.ObjectCacheWithFile;
import com.ai.common.domain.ResultDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类都是缓存类，缓存时间为5分钟，所以如果对应的数据修改后，需要5分钟后才会更新生效
 * @author Administrator
 *
 */
public class SysManagePublicSVImpl implements ISysManagePublicSV {
    private static final long PUB_SV_CACHE_EXPIRE_SEC = 60 *5;
    private static final String PUB_SV_CACHE_TYPE = "SYS_COMMONFRAME_CACHE_TYPE";
    public ResultDTO queryListPage(RoleManageSearchModel searchModel, ResultDTO resultDTO, String operatorCode, String orgIds, String stationCode) throws Exception{

        return resultDTO;
    }

    /**
     * 获取所有组织信息
     * @return
     * @throws Exception
     */
    public SysOrgBean[] getAllOrgBeans()throws Exception{
        //尝试从本地缓存取值
        String cacheName = "getAllOrgBeans" ;
        SysOrgBean[] orgBeans=(SysOrgBean[]) getSysObjectFromCache(cacheName);

        if(orgBeans  != null){
            return orgBeans;
        }


        orgBeans = SysOrgEngine.getBeans("", null);

        //存入本地缓存
       // saveSysObjectToCache(cacheName, orgBeans);

        return orgBeans;
    }

    /**
     * 从文件缓存获取缓存数据
     * @param cacheName
     * @return
     * @throws Exception
     */
    private Object getSysObjectFromCache(String cacheName) throws Exception{
        //从文件缓存获取数据
        return ObjectCacheWithFile.getObjectFromFile(PUB_SV_CACHE_TYPE,cacheName ,PUB_SV_CACHE_EXPIRE_SEC);
    }
    /**
     * 将对象存入文件缓存
     * @param cacheName
     * @param obj
     * @throws Exception
     */
    private void saveSysObjectToCache(String cacheName ,Object obj)throws Exception{
        if(obj == null){
            return;
        }

        //存入文件缓存
        ObjectCacheWithFile.saveObjectToFile(PUB_SV_CACHE_TYPE, cacheName, obj);
    }
    //提供外部调用权限组织
    public SysOrgBean[] getOperatorRelateOrgInfoByOperatorCode(
            String operatorCode, String stationCode, boolean isInclueSubOrg)
            throws Exception {

        //尝试从本地缓存取值
//		String cacheName = "getOpeRelaOrgIByOper_" + operatorCode+"_" +
//			operatorCode +"_" + stationCode + "_" + isInclueSubOrg  ;
//		SysOrgBean[] orgBeans=(SysOrgBean[]) getSysObjectFromCache(cacheName);
//
//		if(orgBeans  != null){
//			return orgBeans;
//		}
        SysOrgBean[] orgBeans= null;
        String belongStationCode = ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG;
        String sql = "";
        if(isInclueSubOrg){
            sql = "SELECT DISTINCT * FROM SYS_ORG A WHERE STATE=1 START WITH ORG_ID IN (SELECT ORG_ID "
                    +"                FROM SYS_OPERATOR2ORG  "
                    +"               WHERE OPERATOR_CODE = '"+operatorCode+"' "
                    +"                 AND ORG_TYPE = 0 "
                    +"                 AND STATION_CODE in ('"+stationCode+"','"+belongStationCode+"')) "
                    +" CONNECT BY PRIOR ORG_ID = PARENT_ORG_ID ";
        }else{
            sql = "SELECT DISTINCT * FROM SYS_ORG A WHERE STATE=1 AND ORG_ID IN (SELECT ORG_ID "
                    +"                FROM SYS_OPERATOR2ORG  "
                    +"               WHERE OPERATOR_CODE = '"+operatorCode+"' "
                    +"                 AND ORG_TYPE = 0 "
                    +"                 AND STATION_CODE in ('"+stationCode+"','"+belongStationCode+"')) " ;
        }

        orgBeans = SysOrgEngine.getBeansFromSql(sql, null);

        //存入本地缓存
        //saveSysObjectToCache(cacheName, orgBeans);
        return orgBeans;
    }
    //提供外部调用权限组织
    public SysOrgBean[] getOperatorRelateOrgInfoByOperatorCode2(
            String operatorCode, String stationCode, boolean isInclueSubOrg)
            throws Exception {

        //尝试从本地缓存取值
        String cacheName = "getOpeRelaOrgIByOper_" + operatorCode+"_" +
                operatorCode +"_" + stationCode + "_" + isInclueSubOrg  ;
        SysOrgBean[] orgBeans=(SysOrgBean[]) getSysObjectFromCache(cacheName);

        if(orgBeans  != null){
//				return orgBeans;
        }

        String belongStationCode = ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG;
        String sql = "";
        if(isInclueSubOrg){
            sql = "SELECT DISTINCT * FROM SYS_ORG A WHERE STATE=1 START WITH ORG_ID IN (SELECT ORG_ID "
                    +"                FROM SYS_OPERATOR2ORG  "
                    +"               WHERE OPERATOR_CODE = '"+operatorCode+"' "
                    +"                 AND ORG_TYPE = 0 "
                    +"                 AND STATION_CODE in ('"+stationCode+"','"+belongStationCode+"')) "
                    +" CONNECT BY PRIOR ORG_ID = PARENT_ORG_ID ";
        }else{
            sql = "SELECT DISTINCT * FROM SYS_ORG A WHERE STATE=1 AND ORG_ID IN (SELECT ORG_ID "
                    +"                FROM SYS_OPERATOR2ORG  "
                    +"               WHERE OPERATOR_CODE = '"+operatorCode+"' "
                    +"                 AND ORG_TYPE = 0 "
                    +"                 AND STATION_CODE in ('"+stationCode+"','"+belongStationCode+"')) " ;
        }

        orgBeans = SysOrgEngine.getBeansFromSql(sql, null);

        //存入本地缓存
        saveSysObjectToCache(cacheName, orgBeans);
        return orgBeans;
    }
    @Override
    public SysOperator2OrgBean getSysOperator2org(String operatorCode,
                                                  String stationCode) throws Exception {
        Map map = new HashMap();
        String condition = " OPERATOR_CODE = :S_OPERATOR_CODE AND STATION_CODE = :S_STATION_CODE ";
        map.put("S_OPERATOR_CODE",operatorCode);
        map.put("S_STATION_CODE",stationCode);
        SysOperator2OrgBean[] beans = SysOperator2OrgEngine.getBeans(condition, map);
        if(beans.length>0){
            return beans[0];
        }else{
            return null;
        }

    }
    public String getOrgNameByOrgId(String orgId)throws Exception{
        //尝试从本地缓存取值
        final String cacheName = "getOrgNameByOrgId_" + orgId;
        String orgName=(String) getSysObjectFromCache(cacheName);
        if(orgName != null){
            return orgName;
        }

        String condition = " ORG_ID = :S_ORG_ID ";
        Map map = new HashMap();
        map.put("S_ORG_ID",orgId);
        SysOrgBean[] beans =CommonEngine.getBeans(condition, map,SysOrgBean.class);

        if(beans!=null && beans.length>0){
            orgName =  beans[0].getOrgName();
        }else{
            orgName = orgId;
        }

        //存入本地缓存
        saveSysObjectToCache(cacheName, orgName);

        return orgName;
    }
    public SysOrgBean getOperatorBelongOrg(String operatorCode)	throws Exception {
        try{
            String sql = "SELECT B.* FROM SYS_OPERATOR2ORG A, SYS_ORG B WHERE A.ORG_ID = B.ORG_ID " +
                    "AND STATION_CODE='STAFF_BELONG_ORG' AND OPERATOR_CODE = :S_OPERATOR_CODE  ";
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("S_OPERATOR_CODE",operatorCode);
            SysOrgBean[] orgBeans = SysOrgEngine.getBeansFromSql(sql, map);
            if(orgBeans.length>0){
                return orgBeans[0];
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
