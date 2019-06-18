package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.ServiceManager;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.basecode.bean.AiscLoginLocBean;
import com.ai.aris.server.basecode.bean.AiscLoginLocEngine;
import com.ai.aris.server.basecode.model.AiscLoc;
import com.ai.aris.server.basecode.model.AiscLoginLoc;
import com.ai.aris.server.basecode.service.interfaces.IAiscLoginLocSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscLoginLocSVImpl implements IAiscLoginLocSV {


    @Override
    public ResultDTO queryPageList(AiscLoginLoc model,ResultDTO resultDTO) throws Exception {
    	Statement stmt = null;
    	ResultSet rs = null;
    	ResultSet rsCount =null;
    	try {  
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = " select * from (select B.*,rownum as row_index from ( select a.loginloc_id," +
	    			"a.operator_id,b.loc_desc,c.org_name,(select operator_code from aisc_user2careprov where operator_id=a.operator_id and rownum=1) operator_code from aisc_loginloc a,aisc_loc b," +
	    			"sys_org c where a.loc_id=b.loc_id and a.org_id=c.org_id ";
	    	String sqlParam ="";
	        if(!String.valueOf(model.getOperatorId()).equals("null")&&isNotBlank(String.valueOf(model.getOperatorId()))){
	        	sqlParam+=" and a.operator_id like '%"+model.getOperatorId()+"%' ";
	        }
	        if(model.getLocId()!=-1&&isNotBlank(String.valueOf(model.getLocId()))){
	        	sqlParam+=" and a.loc_id = '"+model.getLocId()+"' ";
	        }
	        if(!model.getOrgId().equals("-1")&&isNotBlank(String.valueOf(model.getOrgId()))){
	        	sqlParam+=" and a.org_id = '"+model.getOrgId()+"' ";
	        }
	        int number = resultDTO.getLimit()*resultDTO.getPage();
	        int rowindex = 1+(number-resultDTO.getLimit());
	        String sqlOrder =" ORDER BY a.LoginLoc_ID asc ) B where rownum<="+number+") where  row_index >="+rowindex+" and row_index <= "+number+" ";
	    	rs = stmt.executeQuery(sql+sqlParam+sqlOrder);
	        List<AiscLoginLoc> list = new ArrayList();
	        AiscLoginLoc aiscCare = null;
			while(rs.next()){
				aiscCare = new AiscLoginLoc();
				aiscCare.setOrgName(rs.getString("ORG_NAME")==null?"":rs.getString("ORG_NAME"));
				aiscCare.setLocName(rs.getString("LOC_DESC")==null?"":rs.getString("LOC_DESC"));
				aiscCare.setLoginlocId(rs.getLong("LOGINLOC_ID"));
				aiscCare.setOperatorId(rs.getLong("OPERATOR_ID"));
				aiscCare.setOperatorCode(rs.getString("OPERATOR_CODE")==null?"":rs.getString("OPERATOR_CODE"));
				list.add(aiscCare);
			}		
			String sqlCount = "select count(*) COUNT from aisc_loginloc a,aisc_loc b," +
	    			"sys_org c where a.loc_id=b.loc_id and a.org_id=c.org_id "+sqlParam;
	        int total = 0;
	        rsCount = stmt.executeQuery(sqlCount);
	        if(rsCount.next()){
	        	total = rsCount.getInt("COUNT");
	        }
	        
	        AiscLoginLoc [] loclist = new AiscLoginLoc[list.size()];
	        if(list!=null&&list.size()>0){
	        	for(int i=0;i<list.size();i++){
	        		loclist[i] = list.get(i);
	        	}
	        }
	        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
	        resultDTO.setRows(BeanUtils.beanToList(loclist,AiscLoginLoc.class,dicts));
	//        dicts.put("locType", new DictTranslator("locType","LOC_TYPE","locTypeDesc"));
	        resultDTO.setRecords(total);
    	} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.release(rs, stmt, null);
			DBUtil.release(rsCount, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
        return resultDTO;
    }
    
    @Override
    public AiscLoginLocBean getAiscLoginLoc(long id) throws Exception {
        return AiscLoginLocEngine.getBean(id);
    }
    @Override
    public boolean deleteAiscLoginLoc(long id) throws Exception{
    	 AiscLoginLocBean bean = getAiscLoginLoc(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscLoginLocEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscLoginLoc(AiscLoginLocBean bean) throws Exception{
    	if (bean.getLoginlocId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQLOGINLOCID");
	    	bean.setLoginlocId(id);
	    	AiscLoginLocEngine.save(bean);
    	}else {
    		AiscLoginLocBean oldBean = AiscLoginLocEngine.getBean(bean.getLoginlocId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscLoginLocEngine.save(oldBean);
        }
    }
    
    @Override
	public List<Map> getLocNameSelect(AiscLoginLoc model) throws Exception {
		Map map = new HashMap();
		map.put("ORG_ID", model.getOrgId());
        AiscLocBean[] beans = AiscLocEngine.getBeans(" org_id=:ORG_ID ORDER BY loc_desc ASC", map);
        List<Map> list = new ArrayList();
        for(AiscLocBean aiscLocBean:beans){
            list.add(ServiceUtil.transerBeanToMap(aiscLocBean));
        }
        return list;
	}
    
    @Override
	public List<Map> getCareprovlist(AiscLoginLoc model) throws Exception {
		Map map = new HashMap();
		AiscCareProvBean[] beans ;
		if(model.getOrgId()!=null){
			map.put("ORG_ID", model.getOrgId());
			beans = AiscCareProvEngine.getBeans(" org_id=:ORG_ID ORDER BY careprov_name ASC", map);
		}else{
			beans = AiscCareProvEngine.getBeans(" ORDER BY careprov_name ASC", map);
		}
        List<Map> list = new ArrayList();
        for(AiscCareProvBean aiscCareBean:beans){
            list.add(ServiceUtil.transerBeanToMap(aiscCareBean));
        }
        return list;
	}
    @Override
    public List<AiscLoc> getNewLocList(String operator_id,String orgId) throws Exception{
    	Statement stmt = null;
    	ResultSet rs = null;
    	List<AiscLoc> list = new ArrayList();
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = "  select loc_id,loc_desc from aisc_loc where loc_id in (select loc_id from aisc_loginloc a " +
	    			" where a.operator_id='"+operator_id+"')  and org_id='"+orgId+"'";
	    	rs = stmt.executeQuery(sql);
	        AiscLoc aiscLoc = null;
			while(rs.next()){
				aiscLoc = new AiscLoc();
				aiscLoc.setLocDesc(rs.getString("LOC_DESC")==null?"":rs.getString("LOC_DESC"));
				aiscLoc.setLocId(rs.getLong("LOC_ID"));
				list.add(aiscLoc);
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
    public List<AiscLoc> getNotJoinLoc(String operator_id,String orgId) throws Exception{
    	Statement stmt = null;
    	ResultSet rs = null;
    	List<AiscLoc> list = new ArrayList();
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = "   select loc_id,loc_desc from aisc_loc where loc_id not in (select loc_id from aisc_loginloc a " +
	    			" where a.operator_id='"+operator_id+"') and org_id='"+orgId+"'";
	    	rs = stmt.executeQuery(sql);
	        AiscLoc aiscLoc = null;
			while(rs.next()){
				aiscLoc = new AiscLoc();
				aiscLoc.setLocDesc(rs.getString("LOC_DESC")==null?"":rs.getString("LOC_DESC"));
				aiscLoc.setLocId(rs.getLong("LOC_ID"));
				list.add(aiscLoc);
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
    public void deleLoginLoc(String operId,String orgId) throws Exception{
    	Statement stmt = null;
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = "   delete from aisc_loginloc where operator_id="+operId+" and org_Id='"+orgId+"'";
	    	stmt.executeUpdate(sql);
    	} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.release(null, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
    }
    
    @Override
    public void saveLoginLoc(String operId,String orgId,String locId) throws Exception{
    	long id = ServiceUtil.getSequence("SEQLOGINLOCID");
    	boolean flag = getHaveLoginLoc(Long.parseLong(operId),orgId,Long.parseLong(locId));
    	if(flag){
    		AiscLoginLocBean bean = new AiscLoginLocBean();
    		bean.setLocId(Long.parseLong(locId));
    		bean.setOperatorId(Long.parseLong(operId));
    		bean.setOrgId(orgId);
    		bean.setLoginlocId(id);
	    	AiscLoginLocEngine.save(bean);
    	}
    }
    
    public boolean getHaveLoginLoc(long operId,String orgId,long locId) throws Exception{
    	Statement stmt = null;
    	ResultSet rs = null;
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = "   select count(*) COUNT from aisc_loginloc where operator_id="+operId+" and org_id='"+orgId+"' and loc_id="+locId;
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

