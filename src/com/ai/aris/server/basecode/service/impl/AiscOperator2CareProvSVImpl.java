package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.ServiceManager;
import com.ai.aris.server.basecode.bean.AiscOperator2CareProvBean;
import com.ai.aris.server.basecode.bean.AiscOperator2CareProvEngine;
import com.ai.aris.server.basecode.bean.AiscQueryOperator2CareProvBean;
import com.ai.aris.server.basecode.bean.AiscQueryOperator2CareProvEngine;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvBean;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvEngine;
import com.ai.aris.server.basecode.model.AiscOperator2CareProvModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscOperator2CareProvSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscOperator2CareProvSVImpl implements IAiscOperator2CareProvSV {

	@Override
	public ResultDTO queryPageList(AiscOperator2CareProvModel model,
			ResultDTO resultDTO) throws Exception {
		
		  StringBuffer condition = new StringBuffer();
	        condition.append(" 1=1");
	        
	        if (isNotBlank(model.getOperatorCode())) {
	            condition.append(" and operator_code like '%"+model.getOperatorCode()+"%' ");
	        }
	        if (isNotBlank(model.getCareprovName())) {
	            condition.append(" and careprov_name like '%"+model.getCareprovName()+"%' ");
	        }
	        
	        if (isNotBlank(model.getOrgId())) {
	            condition.append(" and org_id ='"+model.getOrgId()+"' ");
	        }
	    

	        int total = AiscQueryOperator2CareProvEngine.getBeansCount(condition.toString(), null);
	        AiscQueryOperator2CareProvBean[] beans = null;

	        if (total > 0) {
	            condition.append(" ORDER BY USER2CAREPROV_ID desc");
	            beans = AiscQueryOperator2CareProvEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
	        }
	        
//	        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
//	        dicts.put("locType", new DictTranslator("locType","LOC_TYPE","locTypeDesc"));
	        
	        resultDTO.setRows(BeanUtils.beanToList(beans,AiscOperator2CareProvModel.class,null));
	        resultDTO.setRecords(1);
	        return resultDTO;
	}

	

	@Override
	public boolean deleteOperator2CareProv(long id) throws Exception {
		
		 AiscOperator2CareProvBean bean = getAiscOperator2CareProv(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscOperator2CareProvEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
	}



	@Override
	public AiscOperator2CareProvBean getAiscOperator2CareProv(long id)
			throws Exception {
		return AiscOperator2CareProvEngine.getBean(id);
	}


	@Override
	public void saveAiscOperator2CareProv(AiscOperator2CareProvBean bean)
			throws Exception {
		if (bean.getUser2careprovId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQ_USER2CAREPROV_ID");
	    	bean.setUser2careprovId(id);
	    	AiscOperator2CareProvEngine.save(bean);
    	}else {
    		AiscOperator2CareProvBean oldBean = AiscOperator2CareProvEngine.getBean(bean.getUser2careprovId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscOperator2CareProvEngine.save(oldBean);
        }
	}
	
	@Override
    public List<AiscOperator2CareProvModel> getNotJoinCare(long operator_id,String orgId) throws Exception{
    	Statement stmt = null;
    	ResultSet rs = null;
    	List<AiscOperator2CareProvModel> list = new ArrayList();
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = "  select careprov_id,careprov_name from  aisc_careprov where careprov_id not in " +
	    			"( select careprov_id from  aisc_user2careprov t where operator_id="+operator_id+") and org_id='"+orgId+"' ";
	    	rs = stmt.executeQuery(sql);
	    	AiscOperator2CareProvModel careprov = null;
			while(rs.next()){
				careprov = new AiscOperator2CareProvModel();
				careprov.setCareprovName(rs.getString("CAREPROV_NAME")==null?"":rs.getString("CAREPROV_NAME"));
				careprov.setCareprovId(rs.getLong("CAREPROV_ID"));
				list.add(careprov);
			}
    	} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.release(rs, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
		return list;
    }
    
    @Override
    public List<AiscOperator2CareProvModel> getNewCareList(long operator_id,String orgId) throws Exception{
    	Statement stmt = null;
    	ResultSet rs = null;
    	List<AiscOperator2CareProvModel> list = new ArrayList();
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = "  select careprov_id,careprov_name from  aisc_careprov where careprov_id in " +
			"( select careprov_id from  aisc_user2careprov t where operator_id="+operator_id+" and org_id='"+orgId+"' )";
	    	rs = stmt.executeQuery(sql);
	    	AiscOperator2CareProvModel careprov = null;
			while(rs.next()){
				careprov = new AiscOperator2CareProvModel();
				careprov.setCareprovName(rs.getString("CAREPROV_NAME")==null?"":rs.getString("CAREPROV_NAME"));
				careprov.setCareprovId(rs.getLong("CAREPROV_ID"));
				list.add(careprov);
			}
    	} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.release(rs, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
		return list;
    }
    
    @Override
    public void deleUser2careprov(String operId) throws Exception{
    	Statement stmt = null;
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = " delete from aisc_user2careprov where operator_id="+operId;
	    	stmt.executeUpdate(sql);
    	} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.release(null, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
    }
    
    @Override
    public void saveUser2careprov(String operId,String operCode,String careprovId) throws Exception{
    	long id = ServiceUtil.getSequence("seq_user2careprov_id");
    	boolean flag = getHaveUser2care(Long.parseLong(careprovId),Long.parseLong(operId),operCode);
    	if(flag){
    		AiscUser2CareProvBean bean = new AiscUser2CareProvBean();
    		bean.setCareprovId(Long.parseLong(careprovId));
    		bean.setOperatorId(Long.parseLong(operId));
    		bean.setOperatorCode(operCode);
    		bean.setUser2careprovId(id);
	    	AiscUser2CareProvEngine.save(bean);
    	}
    }
    
    public boolean getHaveUser2care(long careprovId,long operId,String operCode) throws Exception{
    	Statement stmt = null;
    	ResultSet rs = null;
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = "   select count(*) COUNT from aisc_user2careprov where operator_id="+operId+" and operator_code='"+operCode+"' and careprov_id="+careprovId;
	    	rs = stmt.executeQuery(sql);
	    	int total = 0 ;
	    	if(rs.next()){
	        	total = rs.getInt("COUNT");
	        }
	    	if(total>0){
	    		return false;
	    	}
    	} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.release(rs, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
		return true;
    }
}

