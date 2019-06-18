package com.ai.aris.server.jchis.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.jchis.bean.PacsHzjbxxViewBean;
import com.ai.aris.server.jchis.model.HisStudyInfoModel;
import com.ai.aris.server.jchis.model.PacsHzjbxxViewModel;
import com.ai.aris.server.jchis.service.interfaces.IPacsHzjbxxViewSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;

public class PacsHzjbxxViewSVImpl implements IPacsHzjbxxViewSV {
	private Log logger = LogFactory.getLog(PacsHzjbxxViewSVImpl.class);
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
//	public int getHisStudyInfoCount(PacsHzjbxxViewModel searchModel) throws Exception {		
//		StringBuffer condition = new StringBuffer();
//        condition.append(" 1=1");
//         
//        if (isNotBlank(searchModel.getOrgId()) ) {
//            //condition.append(" and jgdm = '"+searchModel.getOrgId()+"'");
//            condition.append(" and jgdm = '610402681564783'");
//        } 
//        if (isNotBlank(searchModel.queryKey)) {
//			if ("0".equals(searchModel.queryKey)) {
//				condition.append( " AND MZH = '" + searchModel.queryValue +"'");
//			} else if ("1".equals(searchModel.queryKey)) {
//				condition.append( " AND ZYH = '" + searchModel.queryValue +"'");
//			} else if ("2".equals(searchModel.queryKey)) {
//				condition.append( " AND SJH = '" + searchModel.queryValue +"'");
//			} else if ("3".equals(searchModel.queryKey)) {
//				// sql +=" AND SQDH = " + searchModel.queryValue ;
//			}
//		}	
//
//        int total =  PacsHzjbxxViewEngine.getBeansCount(condition.toString(), null);
//		return total;
//	}
	
	public int getHisStudyInfoCount(PacsHzjbxxViewModel searchModel) throws Exception {	
		int total = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		try {		
			//疑问? DictItem表示做什么的?
			DictItemBean[] dictBean = dictSV.queryDictItem("HIS_PREFIX");
//			String sql = " select count(1) total from nethis.pacs_hzjbxx_view where jgdm = '610402435660649' and zxksdm = '02' ";
			String sql = "";
			
			//疑问? pacs_hzjbxx_view 指的是谁?
			if("4".equals(searchModel.queryKey)){
				sql = " select count(1) total from pacs_hzjbxx_view where jgdm = '"+searchModel.getOrgCode()+"' and zxksdm = '"+searchModel.getLocCode()+"' ";
			}else{
				sql = " select count(1) total from "+dictBean[0].getItemNo()+"pacs_hzjbxx_view where jgdm = '"+searchModel.getOrgCode()+"' and zxksdm = '"+searchModel.getLocCode()+"' ";
			}
	        if (isNotBlank(searchModel.queryKey)) {
				if ("0".equals(searchModel.queryKey)) {
					sql += " AND MZH = '" + searchModel.queryValue +"'" ;
				} else if ("1".equals(searchModel.queryKey)) {
					sql += " AND ZYH = '" + searchModel.queryValue +"'" ;
				} else if ("2".equals(searchModel.queryKey)) {
					sql += " AND SJH = '" + searchModel.queryValue +"'" ;
				} else if ("3".equals(searchModel.queryKey)) {
					 sql +=" AND HISSQH = '" + searchModel.queryValue+"'" ;
				}else if ("4".equals(searchModel.queryKey)) {
					 sql +=" AND MZH = '" + searchModel.queryValue+"' and BRLX='HP' " ;
				}
			}		
	        logger.error("sql:"+sql);	        
			 if("4".equals(searchModel.queryKey)){
				 conn = com.ai.aris.util.DAOUtils.getSqlServerDBConnection(searchModel.getOrgId(),"HP");
			 }else{
				 conn = com.ai.aris.util.DAOUtils.getDBConnection(searchModel.getOrgId(),"");
			 }
 			 pstmt = conn.prepareStatement( sql );
             //pstmt.setString(1,conditionVal);  
			 result = pstmt.executeQuery();
			 Map<String, String> partyMap = new HashMap<String, String>();
			 while (result.next()) {				
				 total = result.getInt("total");
			 }			 
		} catch (SQLException se) {
			logger.info("PacsHzjbxxViewSVImpl exception while getting  getHisStudyInfoCount:"
							+ se.toString());
			throw new com.ai.aris.util.DAOException(se.getMessage(), se);
		} finally {
			com.ai.aris.util.DAOUtils.closeResultSet(result);
			com.ai.aris.util.DAOUtils.closeStatement(pstmt);
			com.ai.aris.util.DAOUtils.closeConnection(conn);
		} 
		
		return total;
	}
	
		
//	@Override
//	public ResultDTO getHisStudyInfo(PacsHzjbxxViewModel searchModel,ResultDTO resultDTO) throws Exception {
//		
//		StringBuffer condition = new StringBuffer();
//        condition.append(" 1=1");         
//        if (isNotBlank(searchModel.getOrgId()) ) {
//        	 //condition.append(" and jgdm = '"+searchModel.getOrgId()+"'");
//            condition.append(" and jgdm = '610402681564783'");
//        } 
//        if (isNotBlank(searchModel.queryKey)) {
//			if ("0".equals(searchModel.queryKey)) {
//				condition.append( " AND MZH = " + searchModel.queryValue );
//			} else if ("1".equals(searchModel.queryKey)) {
//				condition.append( " AND ZYH = " + searchModel.queryValue );
//			} else if ("2".equals(searchModel.queryKey)) {
//				condition.append( " AND SJH = " + searchModel.queryValue );
//			} else if ("3".equals(searchModel.queryKey)) {
//				// sql +=" AND SQDH = " + searchModel.queryValue ;
//			}
//		}	
//        int total =  PacsHzjbxxViewEngine.getBeansCount(condition.toString(), null);
//        PacsHzjbxxViewBean[] beans = null;
//        if (total > 0) { 
//            beans = PacsHzjbxxViewEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
//        }
//        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();  
//        resultDTO.setRows(BeanUtils.beanToList(beans,HisStudyInfoModel.class,dicts));
//        resultDTO.setRecords(total);
//        return resultDTO;	
//	}
	
	 
	public ResultDTO getHisStudyInfo(PacsHzjbxxViewModel searchModel,ResultDTO resultDTO) throws Exception {
				
		int total = 0 ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null; 
		String sql = "";
		String sql2 = "";
		DictItemBean[] dictBean = dictSV.queryDictItem("HIS_PREFIX");
		try {		 
//			sql = " select count(1) total from nethis.pacs_hzjbxx_view where jgdm = '610402435660649'";
//			sql2 = " select * from nethis.pacs_hzjbxx_view where jgdm = '610402435660649'"; 	
			if("4".equals(searchModel.queryKey)){
				sql = " select count(1) total from pacs_hzjbxx_view where jgdm = '"+searchModel.getOrgCode()+"' and zxksdm = '"+searchModel.getLocCode()+"' ";
				sql2 = " select top 8 * from ( select row_number() over(order by brxm) as rownumber,* from pacs_hzjbxx_view where jgdm = '"+searchModel.getOrgCode()+"' and zxksdm = '"+searchModel.getLocCode()+"' ";
			}else{
				sql = " select count(1) total from "+dictBean[0].getItemNo()+"pacs_hzjbxx_view where jgdm = '"+searchModel.getOrgCode()+"' and zxksdm = '"+searchModel.getLocCode()+"' ";
				sql2 = " select *  from "+dictBean[0].getItemNo()+"pacs_hzjbxx_view where jgdm = '"+searchModel.getOrgCode()+"' and zxksdm = '"+searchModel.getLocCode()+"' ";
			}
			
	        if (isNotBlank(searchModel.queryKey)) {
				if ("0".equals(searchModel.queryKey)) {
					sql += " AND MZH = '" + searchModel.queryValue +"'" ;
					sql2 += " AND MZH = '" + searchModel.queryValue +"'" ;
				} else if ("1".equals(searchModel.queryKey)) {
					sql += " AND ZYH = '" + searchModel.queryValue +"'" ;
					sql2 += " AND ZYH = '" + searchModel.queryValue +"'" ;
				} else if ("2".equals(searchModel.queryKey)) {
					sql += " AND SJH = '" + searchModel.queryValue +"'" ;
					sql2 += " AND SJH = '" + searchModel.queryValue +"'" ;
				} else if ("3".equals(searchModel.queryKey)) {
					 sql +=" AND HISSQH = '" + searchModel.queryValue+"'" ;
					 sql2 += " AND HISSQH = '" + searchModel.queryValue +"'" ;
				}else if ("4".equals(searchModel.queryKey)) {
					 sql +=" AND MZH = '" + searchModel.queryValue+"' and BRLX='HP' " ;
					 sql2 += " AND MZH = '" + searchModel.queryValue +"' and BRLX='HP' " ;
				}
			} 
	        
	        if("4".equals(searchModel.queryKey)){
	        	 sql2 += " ) A where rownumber >=1 ";
				 conn = com.ai.aris.util.DAOUtils.getSqlServerDBConnection(searchModel.getOrgId(),"HP");
			 }else{
				 sql2 += "and rownum <= "+resultDTO.getEnd()+" and rownum >= "+resultDTO.getStart()+"  ";
				 conn = com.ai.aris.util.DAOUtils.getDBConnection(searchModel.getOrgId(),"");
			 }
	        logger.error("sql:"+sql);	        
	        logger.error("sql2:"+sql2);	     
 			 pstmt = conn.prepareStatement( sql );            
			 result = pstmt.executeQuery(); 
			 while (result.next()) {				
				 total = result.getInt("total");
			 }			 
		} catch (SQLException se) {
			logger.info("PacsHzjbxxViewSVImpl exception while getting  getHisStudyInfoCount:"
							+ se.toString());
			
			throw new com.ai.aris.util.DAOException(se.getMessage(), se);
		} finally {
			com.ai.aris.util.DAOUtils.closeResultSet(result);
			com.ai.aris.util.DAOUtils.closeStatement(pstmt);
			com.ai.aris.util.DAOUtils.closeConnection(conn);
		} 
		List<PacsHzjbxxViewBean> studylist = new ArrayList<PacsHzjbxxViewBean>();
		PacsHzjbxxViewBean[] beans = null;
        if (total > 0) {        
        	if("4".equals(searchModel.queryKey)){
        		conn = com.ai.aris.util.DAOUtils.getSqlServerDBConnection(searchModel.getOrgId(),"HP");
        	}else{
        		conn = com.ai.aris.util.DAOUtils.getDBConnection(searchModel.getOrgId(),"");
			}
 			 pstmt = conn.prepareStatement( sql2 );            
			 result = pstmt.executeQuery(); 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");   
			 while (result.next()) {
				 PacsHzjbxxViewBean bean = new PacsHzjbxxViewBean();
				 bean.setDjxh(result.getString("djxh"));  //基层his  住院——首页序号  门诊——挂号流水号
				 bean.setBrxm(result.getString("brxm"));				 
				 bean.setXb(result.getString("xb"));
				 bean.setBirth(result.getString("birth"));
				 bean.setLxdh(result.getString("lxdh"));
				 bean.setZyh(result.getString("zyh"));
				 bean.setMzh(result.getString("mzh"));
				 bean.setBrlx(result.getString("brlx"));				 
				 bean.setSqksdm(result.getString("sqksdm"));
				 bean.setSqks(result.getString("sqks"));
				 bean.setSqys(result.getString("sqys"));
				 bean.setBq(result.getString("bq"));			 
				 bean.setCh(result.getString("ch"));
				 bean.setSfzh(result.getString("sfzh"));
				 bean.setDz(result.getString("dz"));
				 bean.setKh(result.getString("kh"));
				 bean.setYzdm(result.getString("yzdm"));
				 bean.setYzmc(result.getString("yzmc"));
				 bean.setZje(result.getLong("zje"));
				 bean.setSjh(result.getString("sjh"));
				 bean.setZxksdm(result.getString("zxksdm"));
				 bean.setJgdm(result.getString("jgdm"));
				 bean.setJbmc(result.getString("jbmc"));
				 bean.setHissqh(result.getString("hissqh"));
				 bean.setSqysdm(result.getString("sqysdm"));
				 bean.setZxsj(Timestamp.valueOf(result.getString("zxsj")));
				 studylist.add(bean);
			 }			 
              
			 int size = studylist.size();  
			 beans = (PacsHzjbxxViewBean[])studylist.toArray(new PacsHzjbxxViewBean[size]); 
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();  
        resultDTO.setRows(BeanUtils.beanToList(beans,HisStudyInfoModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;	
	}
	
}
