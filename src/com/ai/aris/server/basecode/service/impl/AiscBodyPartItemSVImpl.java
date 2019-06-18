package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.ServiceManager;
import com.ai.aris.server.basecode.bean.AiscBodyPart2ItemBean;
import com.ai.aris.server.basecode.bean.AiscBodyPart2ItemEngine;
import com.ai.aris.server.basecode.bean.AiscBodyPartBean;
import com.ai.aris.server.basecode.bean.AiscBodyPartEngine;
import com.ai.aris.server.basecode.bean.AiscItemMastBean;
import com.ai.aris.server.basecode.bean.AiscItemMastEngine;
import com.ai.aris.server.basecode.bean.AiscLocStudyStaBean;
import com.ai.aris.server.basecode.bean.AiscLocStudyStaEngine;
import com.ai.aris.server.basecode.bean.QryBodyPart2ItemBean;
import com.ai.aris.server.basecode.bean.QryBodyPart2ItemEngine;
import com.ai.aris.server.basecode.bean.QryBodyPartBean;
import com.ai.aris.server.basecode.bean.QryBodyPartEngine;
import com.ai.aris.server.basecode.model.AiscBodyPartItem;
import com.ai.aris.server.basecode.service.interfaces.IAiscBodyPartItemSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscBodyPartItemSVImpl implements IAiscBodyPartItemSV {

    @Override
    public ResultDTO queryPageList(AiscBodyPartItem model,ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
//        if(isNotBlank(String.valueOf(model.getBodypartCode()))) {
//            condition.append(" and BodyPart_Code like '%"+model.getBodypartCode()+"%' ");
//        }
//        if (model.getItemmastId()!=null&&!String.valueOf(model.getItemmastId()).equals("")) {
//            condition.append(" and ItemMast_ID like '%"+model.getItemmastId()+"%' ");
//        }
        if(model.getOrdcategoryId()>0&&!"-1".equals(model.getOrdcategoryId())&&model.getOrdsubcategoryId()==-1){
                condition.append(" and ItemMast_ID in ("+getItemMastIdBycate(model.getOrdcategoryId())+") ");

        }
        if(model.getOrdsubcategoryId()>0&&!"-1".equals(model.getOrdsubcategoryId())){
            condition.append(" and ItemMast_ID like '%"+getItemMastIdBySubcate(model.getOrdsubcategoryId())+"%' ");
        }
        
        if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())){
            condition.append(" and ORG_ID like '%"+model.getOrgId()+"%' ");
        }
        if(model.getBodypartPid()!=0&&model.getBodypartPid()!=-1){
            condition.append(" and bodypart_pid = "+model.getBodypartPid());
        }
        if(isNotBlank(model.getBodypartType())&&!"-1".equals(model.getBodypartType())){
            condition.append(" and bodypart_type = '"+model.getBodypartType()+"' ");
        }
        
        if(isNotBlank(model.getItemmastDesc())){
            condition.append(" and itemmast_desc like '%"+model.getItemmastDesc()+"%' ");
        }

        int total = QryBodyPart2ItemEngine.getBeansCount(condition.toString(), null);
        QryBodyPart2ItemBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY BodyPart2_ID desc");
            beans = QryBodyPart2ItemEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscBodyPartItem.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
  
    public String getItemMastIdBycate(long id) throws Exception {
    	String ids ="" ;
    	AiscItemMastBean[] beans =AiscItemMastEngine.getBeans( " ORDCATEGORY_ID = "+id, null) ;
    	if (beans.length>0) {
    		for (int i = 0; i < beans.length; i++) {
				ids += ids==""?String.valueOf(beans[i].getItemmastId()):","+String.valueOf(beans[i].getItemmastId()) ;
			}
		}
        return ids;
    }
    public long getItemMastIdBySubcate(long id) throws Exception {
    	AiscItemMastBean[] beans =AiscItemMastEngine.getBeans( " ORDSUBCATEGORY_ID = "+id, null) ;
    	
    	if (beans.length>0) {
            return beans[0].getItemmastId() ;
		}
        return 0;
        }
    @Override
    public AiscBodyPart2ItemBean getAiscBodyPartItem(long id) throws Exception {
        return AiscBodyPart2ItemEngine.getBean(id);
    }
    @Override
    public boolean deleteAiscBodyPartItem(long id) throws Exception{
    	 AiscBodyPart2ItemBean bean = getAiscBodyPartItem(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscBodyPart2ItemEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscBodyPartItem(long itemmastId,String orgId,String bodypartCode) throws Exception{
    	long id = ServiceUtil.getSequence("SEQBODYPARTITEMID");
        boolean flag = getBodyPartItem(itemmastId, orgId, bodypartCode);
        AiscBodyPartBean partBean = AiscBodyPartEngine.getBean(bodypartCode);
        if(partBean!=null&&partBean.getBodypartPid()!=0){
        	//验证部位大类在关系表中是否存在
        	boolean flag1 = getBodyPartItem(itemmastId, orgId,String.valueOf(partBean.getBodypartPid()));
        	if (flag1) {
        		long id1 = ServiceUtil.getSequence("SEQBODYPARTITEMID");
        		AiscBodyPart2ItemBean bean = new AiscBodyPart2ItemBean();
                bean.setBodypart2Id(id1);
                bean.setBodypartCode(String.valueOf(partBean.getBodypartPid()));
                bean.setItemmastId(itemmastId);
                bean.setOrgId(orgId);
                AiscBodyPart2ItemEngine.save(bean);
        	}
        }
        if (flag) {
        	AiscBodyPart2ItemBean bean = new AiscBodyPart2ItemBean();
            bean.setBodypart2Id(id);
            bean.setBodypartCode(bodypartCode);
            bean.setItemmastId(itemmastId);
            bean.setOrgId(orgId);
            AiscBodyPart2ItemEngine.save(bean);
        }
    }
    
    private boolean getBodyPartItem(long itemmastId,String orgId,String bodypartCode) throws Exception {
    	Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "   select count(*) COUNT from aisc_bodypart2item where itemmast_id=" + itemmastId + " and org_id='" + orgId + "' and bodypart_code='" + bodypartCode + "'";
            rs = stmt.executeQuery(sql);
            int total = 0;
            if (rs.next()) {
                total = rs.getInt("COUNT");
            }
            if (total > 0) {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return true;
    }
    
    public void deleAiscBodyPartItem(String orgId,long itemmastId) throws Exception{
    	Statement stmt = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "   delete from aisc_bodypart2item where itemmast_Id=" + itemmastId + " and org_Id='" + orgId + "'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DBUtil.release(null, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
    }
    
    @Override
	public List<Map> getAiscBodyPartUnSelect(List<Map> itemMastList,
			long itemmastId,String orgId,String bodypartType,long bodypartPid) throws Exception {
		Map map = new HashMap();
		StringBuffer condition = new StringBuffer();
		String itemmastIds = "";
		condition.append(" 1=1");
		if (itemmastId > 0) {
			condition.append(" and bodypart_code not in (" +
					" select t.bodypart_code from aisc_bodypart2item t ,aisc_bodypart b ,aisc_itemmast m ,sys_org s" +
					" where t.bodypart_code=b.bodypart_code and t.itemmast_id=m.itemmast_id and m.org_id=s.org_id "+
					" and t.itemmast_id ='"+itemmastId+"' and t.org_id ='"+orgId+"' ) and org_id='"+orgId+"'") ;
			
		} else if (itemMastList!=null) {
			if (itemMastList.size()>0) {
				
			String ss = itemMastList.toArray().toString();
			for (int i = 0; i < itemMastList.size(); i++) {
				Long id = (Long) (((Map) itemMastList.get(i)).get("itemmastid"));
				itemmastIds += itemmastIds == "" ? id : "," + id;
			}
			condition.append(" and bodypart_code not in (" +
					" select t.bodypart_code from aisc_bodypart2item t ,aisc_bodypart b ,aisc_itemmast m ,sys_org s" +
					" where t.bodypart_code=b.bodypart_code and t.itemmast_id=m.itemmast_id and m.org_id=s.org_id "+
					" and t.itemmast_id in ("+itemmastIds+") and t.org_id ='"+orgId+"' ) and org_id='"+orgId+"'") ;
			
		}
		}else {
			condition.append(" and bodypart_code not in (" +
					" select t.bodypart_code from aisc_bodypart2item t where t.org_id='"+orgId+"') and org_id='"+orgId+"'") ;
		}
		if(bodypartPid!=0&&bodypartPid!=-1){
			condition.append(" and bodypart_pid="+bodypartPid+"");
		}
		condition.append(" and bodypart_type='"+bodypartType+"'");
		//condition.append(" ORDER BY BODYPART_DESC ASC ");
		AiscBodyPartBean[] beans = AiscBodyPartEngine.getBeans(
				condition.toString(), map);
		List<Map> list = new ArrayList();
		for (AiscBodyPartBean bodyPartBean : beans) {
			list.add(ServiceUtil.transerBeanToMap(bodyPartBean));
		}
		return list;
	}
    @Override
	public List<Map> getAiscBodyPartSelected(List<Map> itemMastList,
			long itemmastId,String orgId,String bodypartType,long bodypartPid) throws Exception {
		Map map = new HashMap();
		StringBuffer condition = new StringBuffer();
		String itemmastIds = "";
		condition.append(" 1=1");
		if (itemmastId > 0) {
			condition.append(" and bodypart_code  in (" +
					" select t.bodypart_code from aisc_bodypart2item t ,aisc_bodypart b ,aisc_itemmast m ,sys_org s" +
					" where t.bodypart_code=b.bodypart_code and t.itemmast_id=m.itemmast_id and m.org_id=s.org_id "+
					"  and t.itemmast_id ='"+itemmastId+"' and t.org_id ='"+orgId+"' ) and org_id='"+orgId+"'") ;
			
		} else if (itemMastList!=null) {
			if (itemMastList.size()>0) {
			String ss = itemMastList.toArray().toString();
			for (int i = 0; i < itemMastList.size(); i++) {
				Long id = (Long) (((Map) itemMastList.get(i)).get("itemmastid"));
				itemmastIds += itemmastIds == "" ? id : "," + id;
			}
			condition.append(" and bodypart_code  in (" +
					" select t.bodypart_code from aisc_bodypart2item t ,aisc_bodypart b ,aisc_itemmast m ,sys_org s" +
					" where t.itemmast_id in ("+itemmastIds+") and t.org_id ='"+orgId+"' ) and org_id='"+orgId+"'") ;
			}
		}else {
				condition.append(" and bodypart_code in (" +
						" select t.bodypart_code from aisc_bodypart2item t  where t.org_id='"+orgId+"') and org_id='"+orgId+"' ") ;
		}
		condition.append(" and bodypart_type='"+bodypartType+"'");
		if(bodypartPid!=0&&bodypartPid!=-1){
			//condition.append(" and bodypart_pid="+bodypartPid+"");
		}
		condition.append(" ORDER BY BODYPART_DESC ASC ");
		AiscBodyPartBean[] beans = AiscBodyPartEngine.getBeans(
				condition.toString(), map);
		List<Map> list = new ArrayList();
		for (AiscBodyPartBean bodyPartBean : beans) {
			list.add(ServiceUtil.transerBeanToMap(bodyPartBean));
		}
		return list;
	}
    

    
}

