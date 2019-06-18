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
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentEngine;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.basecode.model.AiscEquipment;
import com.ai.aris.server.basecode.service.interfaces.IAiscEquipSV;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.bean.DictItemEngine;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscEquipSVImpl implements IAiscEquipSV {


    @Override
    public ResultDTO queryPageList(AiscEquipment model,ResultDTO resultDTO) throws Exception {
    	Statement stmt = null;
    	ResultSet rs = null;
    	ResultSet rsCount =null;
    	try {
    		stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = " select * from (select B.*,rownum as row_index from (select A.EQUIPMENT_ID,A.LOC_ID,A.EQUIPMENT_CODE,A.MODALITY_ID,A.EQUIPMENT_DESC,A.EQUIPMENT_ENDESC," +
	    			"A.EQUIPMENT_AE,A.EQUIPMENT_IP,A.EQUIPMENT_PORT,to_char(A.EQUIPMENT_STARTDATE,'yyyy-mm-dd hh24:mi:ss') EQUIPMENT_STARTDATE,A.EQUIPMENT_MANUFACTURER,A.EQUIPMENT_PHONE," +
	    			"A.ROOM_ID,A.MODALITY_ENABLED,A.MODALITY_WORKLIST,A.MODALITY_SUPPORTCHINESE,A.MODALITY_SUPPORTSEXAGE,A.MODALITY_SUPPORTID,A.ORG_ID," +
	    			"(select item_name from sys_dictitem where dict_name='AISC_MODALITY' and item_no=a.MODALITY_ID) MODALITY_TYPE,b.room_desc,c.org_name,d.loc_desc,(select ordsubcategory_desc from aisc_ordsubcategory where ordsubcategory_id=a.ordsubcategory_id) ordsubcategory_desc " +
	    			" from aisc_equipment a,aisc_room b,sys_org c,aisc_loc d where a.loc_id=d.loc_id and a.org_id=c.org_id and a.room_id=b.room_id ";
	    	String sqlParam ="";
	    	if(isNotBlank(String.valueOf(model.getEquipmentCode()))) {
	    		sqlParam+=" and a.Equipment_Code like '%"+model.getEquipmentCode()+"%' ";
	        }
	        if (isNotBlank(String.valueOf(model.getEquipmentDesc()))) {
	        	sqlParam+=" and a.Equipment_Desc like '%"+model.getEquipmentDesc()+"%' ";
	        }
	        if(model.getModalityId()!=-1&&isNotBlank(String.valueOf(model.getModalityId()))){
	        	sqlParam+=" and a.Modality_ID = '"+model.getModalityId()+"' ";
	        }
	        if(!model.getOrgId().equals("-1")&&isNotBlank(model.getOrgId())){
	        	sqlParam+=" and c.org_id = '"+model.getOrgId()+"' ";
	        }
	        if(model.getLocId()!=-1&&isNotBlank(String.valueOf(model.getLocId()))){
	        	sqlParam+=" and d.loc_id = '"+model.getLocId()+"' ";
	        }
	        int number = resultDTO.getLimit()*resultDTO.getPage();
	        int rowindex = 1+(number-resultDTO.getLimit());
	        String sqlOrder ="ORDER BY a.equipment_startdate desc) B where rownum<="+number+") where  row_index >="+rowindex+" and row_index <= "+number+" ";
	    	rs = stmt.executeQuery(sql+sqlParam+sqlOrder);
	        List<AiscEquipment> list = new ArrayList();
	        AiscEquipment aiscLoc = null;
			while(rs.next()){
				aiscLoc = new AiscEquipment();
				aiscLoc.setLocId(rs.getLong("LOC_ID"));
				aiscLoc.setEquipmentId(rs.getLong("EQUIPMENT_ID"));
				aiscLoc.setEquipmentCode(rs.getString("EQUIPMENT_CODE")==null?"":rs.getString("EQUIPMENT_CODE"));
				aiscLoc.setEquipmentDesc(rs.getString("EQUIPMENT_DESC")==null?"":rs.getString("EQUIPMENT_DESC"));
				aiscLoc.setLocName(rs.getString("LOC_DESC")==null?"":rs.getString("LOC_DESC"));
				aiscLoc.setRoomName(rs.getString("ROOM_DESC")==null?"":rs.getString("ROOM_DESC"));
				aiscLoc.setEquipmentEndesc(rs.getString("EQUIPMENT_ENDESC")==null?"":rs.getString("EQUIPMENT_ENDESC"));
				aiscLoc.setEquipmentAe(rs.getString("EQUIPMENT_AE")==null?"":rs.getString("EQUIPMENT_AE"));
				aiscLoc.setEquipmentIp(rs.getString("EQUIPMENT_IP")==null?"":rs.getString("EQUIPMENT_IP"));
				aiscLoc.setEquipmentPort(rs.getLong("EQUIPMENT_PORT"));
				aiscLoc.setEquipmentStartdate(rs.getString("EQUIPMENT_STARTDATE"));
				aiscLoc.setEquipmentManufacturer(rs.getString("EQUIPMENT_MANUFACTURER")==null?"":rs.getString("EQUIPMENT_MANUFACTURER"));
				aiscLoc.setEquipmentPhone(rs.getString("EQUIPMENT_PHONE")==null?"":rs.getString("EQUIPMENT_PHONE"));
				aiscLoc.setModalityEnabled(rs.getLong("MODALITY_ENABLED"));
				aiscLoc.setModalityWorklist(rs.getLong("MODALITY_WORKLIST"));
				aiscLoc.setModalitySupportchinese(rs.getLong("MODALITY_SUPPORTCHINESE"));
				aiscLoc.setModalitySupportsexage(rs.getLong("MODALITY_SUPPORTSEXAGE"));
				aiscLoc.setModalitySupportid(rs.getLong("MODALITY_SUPPORTID"));
				aiscLoc.setOrgName(rs.getString("ORG_NAME")==null?"":rs.getString("ORG_NAME"));
				aiscLoc.setModalityType(rs.getString("MODALITY_TYPE")==null?"":rs.getString("MODALITY_TYPE"));
				aiscLoc.setOrdsubcategoryDesc(rs.getString("ORDSUBCATEGORY_DESC")==null?"":rs.getString("ORDSUBCATEGORY_DESC"));
				list.add(aiscLoc);
			}		
			String sqlCount = "select count(*) COUNT from aisc_equipment a,aisc_room b,sys_org c,aisc_loc d,aisc_ordsubcategory e where a.loc_id=d.loc_id and a.org_id=c.org_id and a.room_id=b.room_id and e.ordsubcategory_id=a.ordsubcategory_id "+sqlParam;
	        int total = 0;
	        rsCount = stmt.executeQuery(sqlCount);
	        if(rsCount.next()){
	        	total = rsCount.getInt("COUNT");
	        }
	        
	        AiscEquipment [] loclist = new AiscEquipment[list.size()];
	        if(list!=null&&list.size()>0){
	        	for(int i=0;i<list.size();i++){
	        		loclist[i] = list.get(i);
	        	}
	        }
	        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
	        resultDTO.setRows(BeanUtils.beanToList(loclist,AiscEquipment.class,dicts));
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
    public List<Map> queryDict(String dictName) throws Exception {
        Map map = new HashMap();
        map.put("dictName", dictName);
        DictItemBean[] dictItemBeans = DictItemEngine.getBeans("DICT_NAME=:dictName", map);
        List<Map> list = new ArrayList();
        for(DictItemBean dictItemBean:dictItemBeans){
            list.add(ServiceUtil.transerBeanToMap(dictItemBean));
        }
        return list;
    }
    
    @Override
    public AiscEquipmentBean getAiscEquipBean(long id) throws Exception {
        return AiscEquipmentEngine.getBean(id);
    }
    @Override
    public boolean deleteAiscEquip(long id) throws Exception{
    	 AiscEquipmentBean bean = getAiscEquipBean(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscEquipmentEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscEquip(AiscEquipmentBean bean) throws Exception{
    	if (bean.getEquipmentId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQEQUIPMENTID");
	    	bean.setEquipmentId(id);
	    	AiscEquipmentEngine.save(bean);
    	}else {
    		AiscEquipmentBean oldBean = AiscEquipmentEngine.getBean(bean.getEquipmentId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscEquipmentEngine.save(oldBean);
        }
    }

	@Override
	public boolean checkEquipmentCode(long equipmentId,String equipmentCode,String orgId) throws Exception {
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(equipmentId!=0){
        	condition.append(" and equipment_id<>"+equipmentId);
        }
        if(isNotBlank(equipmentCode)) {
            condition.append(" and equipment_code = '"+equipmentCode+"' ");
        }
        if(isNotBlank(orgId)) {
            condition.append(" and org_id = '"+orgId+"' ");
        }
        return AiscEquipmentEngine.getBeansCount(condition.toString(), null)==0;
	}
}

