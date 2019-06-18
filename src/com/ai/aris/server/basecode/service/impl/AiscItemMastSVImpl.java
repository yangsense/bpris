package com.ai.aris.server.basecode.service.impl;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscItemMastBean;
import com.ai.aris.server.basecode.bean.AiscItemMastEngine;
import com.ai.aris.server.basecode.bean.QryAiscItemMastBean;
import com.ai.aris.server.basecode.bean.QryAiscItemMastEngine;
import com.ai.aris.server.basecode.model.AiscItemMastModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscItemMastSV;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.web.controller.basecode.AiscLocController;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.PyFirstLetter;
import com.ai.common.util.ServiceUtil;

import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.apache.commons.lang.StringUtils.isNotBlank;
/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/10/16
 * Time: 15:54
 * Email:zhangfz3@asiainfo.com
 */
public class AiscItemMastSVImpl  implements IAiscItemMastSV{
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
	 private Log logger = LogFactory.getLog(AiscLocController.class);
    @Override
    public ResultDTO queryPageList(AiscItemMastModel model, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(model.getItemmastCode())) {
            condition.append(" and ItemMast_Code like '%"+model.getItemmastCode()+"%' ");
        }
        if(isNotBlank(model.getItemmastDesc())) {
            condition.append(" and ItemMast_Desc like '%"+model.getItemmastDesc()+"%' ");
        }
        if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())) {
            condition.append(" and org_id = '"+model.getOrgId()+"' ");
        }
        if (isNotBlank(model.getItemmastEndesc())) {
            condition.append(" and ItemMast_EnDesc like '%"+model.getItemmastEndesc()+"%' ");
        }
        if(model.getItemmastSevice()>0){
            condition.append(" and ITEMMAST_SEVICE ="+model.getItemmastSevice());
        }
//        if(model.getItemmastIsenhanced()>=0){
//            condition.append(" and ItemMast_IsEnhanced ="+model.getItemmastIsenhanced());
//        }
        if(model.getOrdcategoryId()>0&&!"-1".equals(model.getOrdcategoryId())){
            condition.append(" and Ordcategory_Id = '"+model.getOrdcategoryId()+"' ");
        }
        if(model.getOrdsubcategoryId()>0&&!"-1".equals(model.getOrdsubcategoryId())){
            condition.append(" and OrdSubCategory_Id = '"+model.getOrdsubcategoryId()+"' ");
        }

        int total = QryAiscItemMastEngine.getBeansCount(condition.toString(), null);
        QryAiscItemMastBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY itemmast_Id desc");
            beans = QryAiscItemMastEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
//        dicts.put("itemmastIsenhanced", new DictTranslator("itemmastIsenhanced","ITEMMAST_ISENHANCED","itemmastIsenhancedDesc"));
//        dicts.put("itemmastSevice", new DictTranslator("itemmastSevice","ITEMMAST_SERVICE","itemmastSeviceDesc"));
        resultDTO.setRows(BeanUtils.beanToList(beans, AiscItemMastModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    @Override
    public AiscItemMastModel getAiscItemMast(long id) throws Exception {
        return BeanUtils.beanToModel(AiscItemMastEngine.getBean(id), AiscItemMastModel.class);
    }

    @Override
    public boolean deleteAiscItemMast(long id) throws Exception {
        AiscItemMastBean bean = AiscItemMastEngine.getBean(id);
        bean.setStsToOld();
        bean.delete();
        try {
            AiscItemMastEngine.save(bean);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void saveAiscItemMast(AiscItemMastBean bean) throws Exception {
        if (bean.getItemmastId() == 0) {
            long id = ServiceUtil.getSequence("SEQ_ITEMMAST");
            bean.setItemmastId(id);
            AiscItemMastEngine.save(bean);
        }else {
            AiscItemMastBean oldBean = AiscItemMastEngine.getBean(bean.getItemmastId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscItemMastEngine.save(oldBean);
        }
    }

    public Boolean checkIsExist(AiscItemMastBean bean) throws Exception{
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(String.valueOf(bean.getItemmastId()))&&bean.getItemmastId()!=0) {
			condition.append(" and itemmast_id <> "+bean.getItemmastId());
		}
        if(isNotBlank(bean.getItemmastCode())) {
            condition.append(" and itemmast_code = '"+bean.getItemmastCode()+"' ");
        }
        if(isNotBlank(bean.getOrgId())) {
            condition.append(" and org_id = '"+bean.getOrgId()+"' ");
        }
        int count = AiscItemMastEngine.getBeansCount(condition.toString(), null);
        if (count>0){
            return  true ;
        }else return  false ;
    }
    @Override
    public List<Map> getAiscItemMastSelect(long ordcategoryId , long ordsubcategoryId,String orgId) throws Exception {
    	Map map = new HashMap();
    	  StringBuffer condition = new StringBuffer();
          condition.append(" 1=1");
          if(ordcategoryId>0&&ordsubcategoryId!=-1){
              condition.append(" and ORDCATEGORY_ID = "+ordcategoryId);
          }
          if(ordsubcategoryId>0){
              condition.append(" and ORDSUBCATEGORY_ID = "+ordsubcategoryId);
          }
          if(isNotBlank(orgId)) {
              condition.append(" and org_id = '"+orgId+"' ");
          }
        //condition.append(" ORDER BY ITEMMAST_DESC ASC ") ;
    	AiscItemMastBean[] beans = AiscItemMastEngine.getBeans(condition.toString(), map);
        List<Map> list = new ArrayList();
        for(AiscItemMastBean aiscItemMastBean:beans){
            list.add(ServiceUtil.transerBeanToMap(aiscItemMastBean));
        }
        return list;
    }

	@Override
	public boolean checkItemmastCode(long itemmastId,String itemmastCode,String orgId) throws Exception {
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(itemmastId!=0){
        	condition.append(" and itemmast_id<>"+itemmastId);
        }
        if(isNotBlank(itemmastCode)) {
            condition.append(" and itemmast_code = '"+itemmastCode+"' ");
        }
        if(isNotBlank(orgId)) {
            condition.append(" and org_id = '"+orgId+"' ");
        }
        return AiscItemMastEngine.getBeansCount(condition.toString(), null)==0;
	}
	public String getOrgDuns(String orgId) throws Exception {

		Statement stmt = null;
		ResultSet rs = null;
		String duns = "";
		try {
			stmt = ServiceManager.getSession().getConnection()
					.createStatement();
			rs = stmt.executeQuery("select * from sys_org where org_id = "
					+ orgId + "");
			if (rs.next()) {
				duns = rs.getString("DUNS");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			DBUtil.release(null, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
		return duns;
	}
	public AiscItemMastBean getAiscItem(String itemmastCode,String itemmastDesc) throws Exception {
		StringBuffer condition = new StringBuffer();
        condition.append(" Itemmast_Code = '"+itemmastCode+"' and itemmast_Desc='"+itemmastDesc+"'");
        AiscItemMastBean[] beans = AiscItemMastEngine.getBeans(condition.toString(), null);
        AiscItemMastBean bean;
        if (beans!=null && beans.length>0) {
			bean = beans[0];
		}else{
			bean = new AiscItemMastBean();
		}
        return bean;
    }
	/**
	 * 导入项目
	 */
	public String importItem(String orgId,long ordcategoryId,long ordsubcategoryId,String locCode) throws Exception{
		String result = "0";
		try {
			List<Map<String,Object>> list = getHisItem(orgId, ordcategoryId, ordsubcategoryId, locCode);
			if(list!=null&&list.size()>0){
				for (Map<String, Object> map : list) {
					String itemmastCode = map.get("itemmastCode").toString();
					String itemmastDesc = map.get("itemmastDesc").toString();
					AiscItemMastBean bean = getAiscItem(itemmastCode,itemmastDesc);
					bean.setOrdcategoryId(ordcategoryId);
					bean.setOrdsubcategoryId(ordsubcategoryId);
					bean.setItemmastCode(itemmastCode);
					bean.setItemmastDesc(itemmastDesc);
					bean.setPyInitial(ename(itemmastDesc));
					bean.setOrgId(orgId);
					saveAiscItemMast(bean);
				}
				result = "0";
            }else{
            	result = "-2";
            }
		}catch (Exception ex) {
			result = "-1";
			throw ex;
		}
		return result;
	}
	
	private String ename(String itemmastName){
		String pymName = "";
		try {
			PyFirstLetter py = new PyFirstLetter();
			String name = URLDecoder.decode(itemmastName,"UTF-8"); 
			pymName = py.getUpEname(name);
		} catch (Exception e) {
			pymName="";
		}
		return pymName;
	}
	/**
	 * 获取his项目
	 */
	public List<Map<String,Object>> getHisItem(String orgId,long ordcategoryId,long ordsubcategoryId,String locCode) throws Exception{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String sql = "";
		try {
			DictItemBean[] dictBean = dictSV.queryDictItem("HIS_PREFIX");
			String duns = getOrgDuns(orgId);
			sql = "select * from "+dictBean[0].getItemNo()+"pacs_sfmbxx_view where jgdm='" + duns + "' and zxksdm='"+locCode+"'";
			logger.debug("sql:"+sql);
			conn = com.ai.aris.util.DAOUtils.getDBConnection(orgId,"");
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeQuery();
			while (result.next()) {
				String itemmastCode = result.getString("mbdm");
				String itemmastDesc = result.getString("mbmc");
				String orgCode = result.getString("jgdm");
				String zxksdm = result.getString("zxksdm");
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("itemmastCode", itemmastCode);
				map.put("itemmastDesc", itemmastDesc);
				map.put("orgCode", orgCode);
				map.put("zxksdm", zxksdm);
				list.add(map);
			}
		} catch (SQLException se) {
			throw new com.ai.aris.util.DAOException(se.getMessage(), se);
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			com.ai.aris.util.DAOUtils.closeResultSet(result);
			com.ai.aris.util.DAOUtils.closeStatement(pstmt);
			com.ai.aris.util.DAOUtils.closeConnection(conn);
		}
		return list;
	}
}
