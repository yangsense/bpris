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
import com.ai.aris.server.basecode.bean.AiscMediumInfoBean;
import com.ai.aris.server.basecode.bean.AiscMediumInfoEngine;
import com.ai.aris.server.basecode.model.AiscMediumInfo;
import com.ai.aris.server.basecode.service.interfaces.IAiscMediumInfoSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscMediumInfoSVImpl implements IAiscMediumInfoSV {

    @Override
    public ResultDTO queryPageList(AiscMediumInfo model,ResultDTO resultDTO) throws Exception {
    	Statement stmt = null;
    	ResultSet rs = null;
    	ResultSet rsCount =null;
    	try {
    		stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = "select * from (select B.*,rownum as row_index from ( select a.medium_id,a.server_id,a.medium_name,a.medium_path,a.medium_isfull,a.medium_isonline," +
	    			"a.medium_enabled,(select item_name from sys_dictitem where dict_name='MEDIUM_TYPE' and item_no=a.medium_type) MEDIUM_TYPE_DESC" +
	    			",a.medium_size,a.medium_reminesize,b.server_name from aisc_mediuminfo a ,aisc_severinfo b " +
	    			" where a.server_id=b.server_id ";
	    	String sqlParam ="";
	    	if(model.getServerId()!=-1&&isNotBlank(String.valueOf(model.getServerId()))) {
	    		sqlParam+=" and a.server_id = '"+model.getServerId()+"' ";
	        }
	        if (isNotBlank(String.valueOf(model.getMediumName()))) {
	        	sqlParam+=" and a.medium_name like '%"+model.getMediumName()+"%'  ";
	        }
	        if(model.getMediumType()!=-1&&isNotBlank(String.valueOf(model.getMediumType()))){
	        	sqlParam+="  and a.medium_type = '"+model.getMediumType()+"' ";
	        }
	        int number = resultDTO.getLimit()*resultDTO.getPage();
	        int rowindex = 1+(number-resultDTO.getLimit());
	        String sqlOrder ="ORDER BY a.medium_id desc ) B where rownum<="+number+") where  row_index >="+rowindex+" and row_index <= "+number+" ";
	    	rs = stmt.executeQuery(sql+sqlParam+sqlOrder);
	        List<AiscMediumInfo> list = new ArrayList();
	        AiscMediumInfo aiscMedium = null;
			while(rs.next()){
				aiscMedium = new AiscMediumInfo();
				aiscMedium.setMediumId(rs.getLong("MEDIUM_ID"));
				aiscMedium.setMediumName(rs.getString("MEDIUM_NAME")==null?"":rs.getString("MEDIUM_NAME"));
				aiscMedium.setMediumPath(rs.getString("MEDIUM_PATH")==null?"":rs.getString("MEDIUM_PATH"));
				aiscMedium.setMediumIsfull(rs.getLong("MEDIUM_ISFULL"));
				aiscMedium.setMediumIsonline(rs.getLong("MEDIUM_ISONLINE"));
				aiscMedium.setMediumSize(rs.getLong("MEDIUM_SIZE"));
				aiscMedium.setMediumReminesize(rs.getLong("MEDIUM_REMINESIZE"));
				aiscMedium.setServerName(rs.getString("SERVER_NAME")==null?"":rs.getString("SERVER_NAME"));
				aiscMedium.setMediumEnabled(rs.getLong("MEDIUM_ENABLED"));
				aiscMedium.setMediumTypeDesc(rs.getString("MEDIUM_TYPE_DESC")==null?"":rs.getString("MEDIUM_TYPE_DESC"));
				list.add(aiscMedium);
			}		
			String sqlCount = "select count(*) COUNT from aisc_mediuminfo a ,aisc_severinfo b  where a.server_id=b.server_id"+sqlParam;
	        int total = 0;
	        rsCount = stmt.executeQuery(sqlCount);
	        if(rsCount.next()){
	        	total = rsCount.getInt("COUNT");
	        }
	        
	        AiscMediumInfo [] loclist = new AiscMediumInfo[list.size()];
	        if(list!=null&&list.size()>0){
	        	for(int i=0;i<list.size();i++){
	        		loclist[i] = list.get(i);
	        	}
	        }
	        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
	        resultDTO.setRows(BeanUtils.beanToList(loclist,AiscMediumInfo.class,dicts));
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
    public AiscMediumInfoBean getAiscMediumInfo(long id) throws Exception {
        return AiscMediumInfoEngine.getBean(id);
    }
    @Override
    public boolean deleteAiscMediumInfo(long id) throws Exception{
    	 AiscMediumInfoBean bean = getAiscMediumInfo(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscMediumInfoEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscMediumInfo(AiscMediumInfoBean bean) throws Exception{
    	if (bean.getMediumId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQMEDIUMINFOID");
	    	bean.setMediumId(id);
	    	AiscMediumInfoEngine.save(bean);
    	}else {
    		AiscMediumInfoBean oldBean = AiscMediumInfoEngine.getBean(bean.getMediumId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscMediumInfoEngine.save(oldBean);
        }
    }
}

