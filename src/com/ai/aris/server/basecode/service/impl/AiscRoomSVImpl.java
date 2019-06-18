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
import com.ai.aris.server.basecode.bean.AiscRoomBean;
import com.ai.aris.server.basecode.bean.AiscRoomEngine;
import com.ai.aris.server.basecode.model.AiscRoom;
import com.ai.aris.server.basecode.service.interfaces.IAiscRoomSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscRoomSVImpl implements IAiscRoomSV {

    @Override
    public ResultDTO queryPageList(AiscRoom model,ResultDTO resultDTO) throws Exception {
    	Statement stmt = null;
    	ResultSet rs = null;
    	ResultSet rsCount =null;
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = "select * from (select B.*,rownum as row_index from ( select A.ROOM_ID,A.LOC_ID,A.ROOM_DESC,A.ROOM_ENDESC,A.ROOM_ENABLED,A.ORG_ID,B.LOC_DESC,C.ORG_NAME " +
	    			" from aisc_room a,aisc_loc b,sys_org c where a.loc_id=b.loc_id and a.org_id=c.org_id ";
	    	String sqlParam ="";
	    	if(model.getLocId()!=-1&&isNotBlank(String.valueOf(model.getLocId()))) {
	    		sqlParam+=" and a.loc_id = '"+model.getLocId()+"' ";
	        }
	        if (isNotBlank(String.valueOf(model.getRoomDesc()))) {
	        	sqlParam+=" and a.room_desc like '%"+model.getRoomDesc()+"%' ";
	        }
	        if(model.getRoomEnabled()!=-1&&isNotBlank(String.valueOf(model.getRoomEnabled()))){
	        	sqlParam+=" and a.room_enabled = '"+model.getRoomEnabled()+"' ";
	        }
	        if(model.getOrgId()!=-1&&isNotBlank(String.valueOf(model.getOrgId()))){
	        	sqlParam+=" and c.org_id = '"+model.getOrgId()+"' ";
	        }
	        int number = resultDTO.getLimit()*resultDTO.getPage();
	        int rowindex = 1+(number-resultDTO.getLimit());
	        String sqlOrder ="ORDER BY room_id asc ) B where rownum<="+number+") where  row_index >="+rowindex+" and row_index <= "+number+" ";
	    	rs = stmt.executeQuery(sql+sqlParam+sqlOrder);
	        List<AiscRoom> list = new ArrayList();
	        AiscRoom aiscRoom = null;
			while(rs.next()){
				aiscRoom = new AiscRoom();
				aiscRoom.setLocId(rs.getLong("LOC_ID"));
				aiscRoom.setRoomId(rs.getLong("ROOM_ID"));
				aiscRoom.setRoomEnabled(rs.getLong("ROOM_ENABLED"));
				aiscRoom.setRoomDesc(rs.getString("ROOM_DESC")==null?"":rs.getString("ROOM_DESC"));
				aiscRoom.setRoomEndesc(rs.getString("ROOM_ENDESC")==null?"":rs.getString("ROOM_ENDESC"));
				aiscRoom.setOrgName(rs.getString("ORG_NAME")==null?"":rs.getString("ORG_NAME"));
				aiscRoom.setLocName(rs.getString("LOC_DESC")==null?"":rs.getString("LOC_DESC"));
				list.add(aiscRoom);
			}		
			String sqlCount = "select count(*) COUNT from aisc_room a,aisc_loc b,sys_org c where a.loc_id=b.loc_id and a.org_id=c.org_id"+sqlParam;
	        int total = 0;
	        rsCount = stmt.executeQuery(sqlCount);
	        if(rsCount.next()){
	        	total = rsCount.getInt("COUNT");
	        }
	        
	        AiscRoom [] loclist = new AiscRoom[list.size()];
	        if(list!=null&&list.size()>0){
	        	for(int i=0;i<list.size();i++){
	        		loclist[i] = list.get(i);
	        	}
	        }
	        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
	        resultDTO.setRows(BeanUtils.beanToList(loclist,AiscRoom.class,dicts));
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
    public AiscRoomBean getAiscRoom(long id) throws Exception {
        return AiscRoomEngine.getBean(id);
    }
    @Override
    public boolean deleteAiscRoom(long id) throws Exception{
    	 AiscRoomBean bean = getAiscRoom(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscRoomEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscRoom(AiscRoomBean bean) throws Exception{
    	if (bean.getRoomId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQROOMID");
	    	bean.setRoomId(id);
	    	AiscRoomEngine.save(bean);
    	}else {
    		AiscRoomBean oldBean = AiscRoomEngine.getBean(bean.getRoomId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscRoomEngine.save(oldBean);
        }
    }
    
    @Override
	public List<Map> getRoomSelect(String orgId,long locId) throws Exception {
		Map map = new HashMap();
		map.put("orgId",orgId);
		map.put("locId",locId);
        AiscRoomBean[] beans = AiscRoomEngine.getBeans(" org_id=:orgId and loc_id=:locId ORDER BY room_desc ASC", map);
        List<Map> list = new ArrayList();
        for(AiscRoomBean aiscroomBean:beans){
            list.add(ServiceUtil.transerBeanToMap(aiscroomBean));
        }
        return list;
	}
}

