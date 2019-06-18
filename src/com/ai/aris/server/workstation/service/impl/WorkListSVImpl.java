package com.ai.aris.server.workstation.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.basecode.bean.AiscLocStudyStaBean;
import com.ai.aris.server.basecode.bean.AiscLocStudyStaEngine;
import com.ai.aris.server.basecode.bean.AiscLoginLocBean;
import com.ai.aris.server.basecode.bean.AiscLoginLocEngine;
import com.ai.aris.server.basecode.service.interfaces.IAiscCareProvSV;
import com.ai.aris.server.common.bean.CommonEngine;
import com.ai.aris.server.common.model.DictItemModel;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyReportBean;
import com.ai.aris.server.workstation.bean.AisStudyReportEngine;
import com.ai.aris.server.workstation.bean.QryPacsInfoBean;
import com.ai.aris.server.workstation.bean.QryPacsInfoEngine;
import com.ai.aris.server.workstation.bean.QryRisInfoBean;
import com.ai.aris.server.workstation.bean.QryRisInfoEngine;
import com.ai.aris.server.workstation.bean.QryStudyInfoListBean;
import com.ai.aris.server.workstation.bean.QryStudyInfoListEngine;
import com.ai.aris.server.workstation.bean.QryStudyInfoListPrintBean;
import com.ai.aris.server.workstation.bean.QryStudyInfoListPrintEngine;
import com.ai.aris.server.workstation.bean.QryStudyPatientBean;
import com.ai.aris.server.workstation.bean.QryStudyPatientEngine;
import com.ai.aris.server.workstation.model.QryPacsInfoModel;
import com.ai.aris.server.workstation.model.QryRisInfoModel;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.model.QryStudyPatientModel;
import com.ai.aris.server.workstation.service.interfaces.IWorkListSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;
 

public class WorkListSVImpl implements IWorkListSV{
	
	
	private IAiscCareProvSV loginLocsv = (IAiscCareProvSV) ServiceFactory.getService(IAiscCareProvSV.class);
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
	//工作列表查询 
    public ResultDTO queryPageList(QryStudyInfoListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        //远程诊断
        if(1==model.getStudyType()){
//        	if(model.getMessageFlag().equals("5")){
//        		condition.append(" and  studyinfo_id in (select studyinfo_id from ais_studymessage where status_code='APP')");
//            }        
//            if(model.getMessageFlag().equals("3")){
//        		condition.append(" and  studyinfo_id in (select studyinfo_id from ais_studymessage where status_code='VERIFY')");
//            }
        	if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
                condition.append(" and ( study_consultorg = " + model.getOrgId() +" or org_id = " + model.getOrgId() +")");
  		      }
  		      if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
  		          condition.append(" and ( study_consultloc = " + model.getLocId() +" or loc_id = " + model.getLocId() +")");
  		      }  
            condition.append(" and Study_Type = 1 " ); 
            if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            if(isNotBlank(model.getStartTime())){ 
	            	condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	    		}
	            if(isNotBlank(model.getEndTime())){
	            	condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	    		}  
            }
        }
        else if(0==model.getStudyType()){
        	if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
                condition.append(" and org_id = " + model.getOrgId() );
            }
            if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
                condition.append(" and loc_id = " + model.getLocId() );
            }
            condition.append(" and Study_Type = 0 " );
            //用户选择的状态， 来决定选择用哪个时间: 1,如果只查询 ，登记 状态以后的病人用登记时间  。如果查询申请状态的  , 用申请时间 。查询所有状态的， 就用申请时间
            //申请时间
        	if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("APP")!=-1){
        		if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            //登记时间
            }else if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("REG")!=-1){
            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_DateTime >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_DateTime <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            //申请时间
            }else {
            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            }
        }else{
//        	if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
//                condition.append(" and org_id = " + model.getOrgId() );
//            }
//            if (model.getLocId() != -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
//                condition.append(" and loc_id = " + model.getLocId() );
//            }
        	  if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
              condition.append(" and ( study_consultorg = " + model.getOrgId() +" or org_id = " + model.getOrgId() +")");
		      }
		      if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
		          condition.append(" and ( study_consultloc = " + model.getLocId() +" or loc_id = " + model.getLocId() +")");
		      }                        
            
            //用户选择的状态， 来决定选择用哪个时间: 1,如果只查询 ，登记 状态以后的病人用登记时间  。如果查询申请状态的  , 用申请时间 。查询所有状态的， 就用申请时间
            //申请时间
        	if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("APP")!=-1){
        		if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            //登记时间
            }else if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("REG")!=-1){
            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_DateTime >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_DateTime <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            //申请时间
            }else {
            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            }
        	if(isNotBlank(model.getAllStudyType())){
        	    condition.append(" and Study_Type in( "+model.getAllStudyType()+") " );
        	}
        }       
        
        if (isNotBlank(model.getPatientId())) {
            condition.append(" and patient_id = " + model.getPatientId());
        }
        if(isNotBlank(model.getStudyAccnumber())){             
            condition.append(" and Study_Accnumber = '" + model.getStudyAccnumber()+ "'");
        }
        if ("1".equals(model.getqType()) && isNotBlank(model.getqValue())) {
            condition.append(" and patient_id = '" + model.getqValue()+"'");
        }else if("2".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_id = '" + model.getqValue()+"'");
        }else if("3".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_name like '%" + model.getqValue().trim()+"%'");
        }else if("4".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_outpatientid = '" + model.getqValue().trim()+"'");
        }else if("5".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_inpatientid = '" + model.getqValue().trim()+"'");
        }else if("6".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_idnumber like '%" + model.getqValue().trim()+"%'");
        }else if("7".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and old_patient_id = '" + model.getqValue().trim()+"'");
        }
        
        if (!"-1".equals(model.getStudystatusCode())  && isNotBlank(model.getStudystatusCode())) {
            condition.append(" and Studystatus_Code in (" + model.getStudystatusCode() +")");
        }
        int num = getIsFlagHZOrg(model.getOrgId(),String.valueOf(model.getLocId()));
        if(num>0){
        	condition.append(" and Studystatus_Code != 'AppSave' ");
        	condition.append(" and Studystatus_Code != 'STUDYED' ");
        }
        
        //已取消记录不展现
        condition.append(" and Studystatus_Code != 'Cancel' ");
        
        //选择【我的】时，只检查分配给我的会诊记录
        if("1".equals(model.getConsultFlag())){
        	condition.append(" and study_consultdoctorid = " + model.getDoctorId());
        	condition.append(" and studystatus_code = 'AppVerify' ");//审核通过
        }         
        
        //检查消息提示框中的检查记录
        if(null != model.getMessageFlag()){
        	if(model.getMessageFlag().equals("5") || model.getMessageFlag().equals("2")){
        		condition.append("  and  Studystatus_Code = 'APPConsult' ");
            }        
            if(model.getMessageFlag().equals("3") || model.getMessageFlag().equals("4")){
        		condition.append(" and  Studystatus_Code = 'VERIFY' ");
            }
        }
        if(model.getModalityId() != -1 && isNotBlank(String.valueOf(model.getModalityId()))){
        	 condition.append(" and modality_id = " + model.getModalityId() );
        }
        
        int total = QryStudyInfoListEngine .getBeansCount(condition.toString(), null);
        QryStudyInfoListBean[] beans = null;

        if (total > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            //排序处理
        	if(!"".equals(sidx)){
        		condition.append(" ORDER BY "+sidx+" "+sord+" ");
        	}else{
        		condition.append(" ORDER BY Study_createtime desc");
        	}
            beans = QryStudyInfoListEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
            for(QryStudyInfoListBean bean : beans){
            	//部位查询
            	if(StringUtils.isNotBlank(String.valueOf(bean.getStudyinfoId()))){
                    StringBuffer conditionBuffer = new StringBuffer("");
            		conditionBuffer.append(" studyinfo_id = " + bean.getStudyinfoId());
            		AisStudyItemInfoBean[] itemBeans = AisStudyItemInfoEngine.getBeans(conditionBuffer.toString(), null);
            		String bodyparts = "";
            		if(itemBeans!=null&&itemBeans.length>0){
	            		for(AisStudyItemInfoBean item :itemBeans){
	            			if(!"".equals(item.getStudyitemBodyinfo())){
	            				bodyparts+=(item.getStudyitemBodyinfo()==null?"":item.getStudyitemBodyinfo())+",";
	            			}
	            		}
            		}
            		if(!"".equals(bodyparts)){
            			bean.setBodyitem(bodyparts.substring(0,bodyparts.length()-1));
            		}
            	}
            	//有报告才显示报告时间及报告人,打印
//            	if(bean.getStudyHavereport() == 1){
//            		 StringBuffer condition2 = new StringBuffer();
//            	     condition2.append(" studyinfo_id = " + bean.getStudyinfoId());
//            	     condition2.append(" and rownum = 1 order by report_id desc " );            	     
//            	     AisStudyReportBean[] reportBeans = AisStudyReportEngine.getBeans(condition2.toString(), null) ; 
//            	     if(reportBeans.length>0){
//            	    	 bean.setReportIsprinted(reportBeans[0].getReportIsprinted());
//            	    	 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");             	    	 
//            	    	 bean.setReportDatetime(reportBeans[0].getReportDatetime()==null?"": sdf.format(reportBeans[0].getReportDatetime()));
//            	    	 bean.setReportDoctorname(reportBeans[0].getReportDoctorid()==null?"":getCareProvInfo(reportBeans[0].getReportDoctorid()));
//            	    	 bean.setReportVerifydoctorid(reportBeans[0].getReportVerifydoctorid()==null?"":reportBeans[0].getReportVerifydoctorid());
//            	    	 bean.setReportVerifydoctorname(reportBeans[0].getReportVerifydoctorid()==null?"":getCareProvInfo(reportBeans[0].getReportVerifydoctorid()));
//            	    	 bean.setReportFinaldoctorname(reportBeans[0].getReportFinaldoctorid()==null?"":getCareProvInfo(reportBeans[0].getReportFinaldoctorid()));
//            	    	 bean.setReportVerifytime(reportBeans[0].getReportVerifydatetime()==null?"":sdf.format(reportBeans[0].getReportVerifydatetime()));
//            	    	 bean.setReportExam(reportBeans[0].getReportExam()==null?"":delHTMLTag(reportBeans[0].getReportExam().replace("\"","'").replace("<br type=\"_moz\" />", "")));
//            	    	 bean.setReportResult(reportBeans[0].getReportResult()==null?"":delHTMLTag(reportBeans[0].getReportResult().replace("\"","'").replace("<br type=\"_moz\" />", "")));
//            	     }
//            	}
            	bean.setReportExam(bean.getReportExam()==null?"":delHTMLTag(bean.getReportExam().replace("\"","'").replace("<br type=\"_moz\" />", "")));
   	    	    bean.setReportResult(bean.getReportResult()==null?"":delHTMLTag(bean.getReportResult().replace("\"","'").replace("<br type=\"_moz\" />", "")));
            	//申请医生名称, 处理从his过来的数据存放的是医生名称的问题
            	if(bean.getStudyHisappid()!=null&&!"".equals(bean.getStudyHisappid())){
            		bean.setStudyAppdoc(bean.getStudyAppdoc());
            	}else{
            		bean.setStudyAppdoc(getCareProvInfo(bean.getStudyDoctorid()));
            	}            	
            }
            
        } 
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        dicts.put("studystatusCode", new DictTranslator("studystatusCode","STUDY_STATUS","studystatusCode"));
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
        dicts.put("patienttypeCode", new DictTranslator("patienttypeCode","PATIENT_TYPE","patienttypeCode"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QryStudyInfoListModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
   
    public int getIsFlagHZOrg(String orgId,String locId) throws Exception{
    	StringBuffer condition = new StringBuffer();
    	condition.append(" 1=1");
		if (isNotBlank(orgId)) {
			condition.append(" AND org_id = '" + orgId+"'");
		}
		if (isNotBlank(locId) && !"undefined".equals(locId)) {
			condition.append(" AND loc_id = " + locId);
		}
		condition.append(" and loc_type='C' ");
    	AiscLocBean[] beans = AiscLocEngine.getBeans(condition.toString(),null);
    	if(beans.length>0){
    		return beans.length;
    	}else{
    		return 0;
    	}
    }
    //查询列表打印记录
    public ResultDTO queryPagePrintList(QryStudyInfoListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        //远程诊断
        if(1==model.getStudyType()){
//        	if(model.getMessageFlag().equals("5") ){
//        		condition.append(" and  studyinfo_id in (select studyinfo_id from ais_studymessage where status_code='APP')");
//            }        
//            if(model.getMessageFlag().equals("3")){
//        		condition.append(" and  studyinfo_id in (select studyinfo_id from ais_studymessage where status_code='VERIFY')");
//            }
            condition.append(" and Study_Type = 1 " ); 
            if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            if(isNotBlank(model.getStartTime())){ 
	            	condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	    		}
	            if(isNotBlank(model.getEndTime())){
	            	condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	    		}  
            }
        }else if(0==model.getStudyType()){
        	if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
                condition.append(" and org_id = " + model.getOrgId() );
            }
            if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
                condition.append(" and loc_id = " + model.getLocId() );
            }
            condition.append(" and Study_Type = 0 " );
            //用户选择的状态， 来决定选择用哪个时间: 1,如果只查询 ，登记 状态以后的病人用登记时间  。如果查询申请状态的  , 用申请时间 。查询所有状态的， 就用申请时间
            //申请时间
        	if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("APP")!=-1){
        		if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            //登记时间
            }else if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("REG")!=-1){
            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_DateTime >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_DateTime <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            //申请时间
            }else {
            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            }
        }else{
//        	if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
//                condition.append(" and org_id = " + model.getOrgId() );
//            }
//            if (model.getLocId() != -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
//                condition.append(" and loc_id = " + model.getLocId() );
//            }
        	  if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
              condition.append(" and ( study_consultorg = " + model.getOrgId() +" or org_id = " + model.getOrgId() +")");
		      }
		      if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
		          condition.append(" and ( study_consultloc = " + model.getLocId() +" or loc_id = " + model.getLocId() +")");
		      }                        
            
            //用户选择的状态， 来决定选择用哪个时间: 1,如果只查询 ，登记 状态以后的病人用登记时间  。如果查询申请状态的  , 用申请时间 。查询所有状态的， 就用申请时间
            //申请时间
        	if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("APP")!=-1){
        		if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
                }
            //登记时间
            }else if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("REG")!=-1){
            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_DateTime >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_DateTime <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
            	}   
            //申请时间
            }else {
            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
	            	if(isNotBlank(model.getStartTime())){ 
	        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
	        		}
	                if(isNotBlank(model.getEndTime())){
	        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
	        		} 
            	}
            }
        	if(isNotBlank(model.getAllStudyType())){
        	    condition.append(" and Study_Type in( "+model.getAllStudyType()+") " );
        	}
        }       
        
        if (isNotBlank(model.getPatientId())) {
            condition.append(" and patient_id = " + model.getPatientId());
        }
        if(isNotBlank(model.getStudyAccnumber())){             
            condition.append(" and Study_Accnumber = '" + model.getStudyAccnumber()+ "'");
        }
        if ("1".equals(model.getqType()) && isNotBlank(model.getqValue())) {
            condition.append(" and patient_id = '" + model.getqValue()+"'");
        }else if("2".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_id = '" + model.getqValue()+"'");
        }else if("3".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_name = '" + model.getqValue()+"'");
        }else if("4".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_outpatientid = '" + model.getqValue().trim()+"'");
        }else if("5".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_inpatientid = '" + model.getqValue().trim()+"'");
        }else if("6".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and patient_idnumber like '%" + model.getqValue().trim()+"%'");
        }else if("7".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and old_patient_id = '" + model.getqValue().trim()+"'");
        }
        if (!"-1".equals(model.getStudystatusCode())  && isNotBlank(model.getStudystatusCode())) {
            condition.append(" and Studystatus_Code in (" + model.getStudystatusCode() +")");
        }
        System.out.println(model.getReportIsprinted());
        if(!"-1".equals(model.getReportIsprinted())){
        	condition.append(" and report_isprinted=" + model.getReportIsprinted());
        }
        int num = getIsFlagHZOrg(model.getOrgId(),String.valueOf(model.getLocId()));
        if(num>0){
        	condition.append(" and Studystatus_Code != 'AppSave' ");
        	condition.append(" and Studystatus_Code != 'STUDYED' ");
        }
        //已取消记录不展现
        condition.append(" and Studystatus_Code != 'Cancel' ");
        
        //选择【我的】时，只检查分配给我的会诊记录
        if("1".equals(model.getConsultFlag())){
        	condition.append(" and study_consultdoctorid = " + model.getDoctorId());
        	condition.append(" and studystatus_code = 'AppVerify' ");//审核通过
        }         
        
        //检查消息提示框中的检查记录
        if(null != model.getMessageFlag()){
        	if(model.getMessageFlag().equals("5") || model.getMessageFlag().equals("2")){
        		condition.append("  and  Studystatus_Code = 'APPConsult' ");
            }        
            if(model.getMessageFlag().equals("3") || model.getMessageFlag().equals("4")){
        		condition.append(" and  Studystatus_Code = 'VERIFY' ");
            }
        }
        
        int total = QryStudyInfoListPrintEngine.getBeansCount(condition.toString(), null);
        QryStudyInfoListPrintBean[] beans = null;

        if (total > 0) {
        	//排序处理
        	if(!"".equals(sidx)){
        		condition.append(" ORDER BY "+sidx+" "+sord+" ");
        	}else{
        		condition.append(" ORDER BY StudyInfo_ID desc");
        	}
            
            beans = QryStudyInfoListPrintEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
            for(QryStudyInfoListPrintBean bean : beans){
            	//有报告才显示报告时间及报告人,打印
            	if(bean.getStudyHavereport() == 1){
            		 StringBuffer condition2 = new StringBuffer();
            	     condition2.append(" studyinfo_id = '" + bean.getStudyinfoId() + "'" );
            	     condition2.append(" and rownum = 1 order by report_id desc " );            	     
            	     AisStudyReportBean[] reportBeans = AisStudyReportEngine.getBeans(condition2.toString(), null) ; 
            	     if(reportBeans.length>0){
            	    	 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");             	    	 
            	    	 bean.setReportDatetime(reportBeans[0].getReportDatetime()==null?"": sdf.format(reportBeans[0].getReportDatetime()));
            	    	 bean.setReportDoctorname(reportBeans[0].getReportDoctorid()==null?"":getCareProvInfo(reportBeans[0].getReportDoctorid()));
            	    	 bean.setReportVerifydoctorid(reportBeans[0].getReportVerifydoctorid()==null?"":reportBeans[0].getReportVerifydoctorid());
            	    	 bean.setReportVerifydoctorname(reportBeans[0].getReportVerifydoctorid()==null?"":getCareProvInfo(reportBeans[0].getReportVerifydoctorid()));
            	    	 bean.setReportFinaldoctorname(reportBeans[0].getReportFinaldoctorid()==null?"":getCareProvInfo(reportBeans[0].getReportFinaldoctorid()));
            	    	 bean.setReportVerifytime(reportBeans[0].getReportVerifydatetime()==null?"":sdf.format(reportBeans[0].getReportVerifydatetime()));
            	     }
            	}
            	//申请医生名称, 处理从his过来的数据存放的是医生名称的问题
            	if(bean.getStudyHisappid()!=null&&!"".equals(bean.getStudyHisappid())){
            		bean.setStudyAppdoc(bean.getStudyAppdoc());
            	}else{
            		bean.setStudyAppdoc(getCareProvInfo(bean.getStudyDoctorid()));
            	}            	
            }
        } 
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        dicts.put("studystatusCode", new DictTranslator("studystatusCode","STUDY_STATUS","studystatusCode"));
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
        dicts.put("patienttypeCode", new DictTranslator("patienttypeCode","PATIENT_TYPE","patienttypeCode"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QryStudyInfoListModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    
    /**
     * 统计记录数
     */
    public List<Map> queryRecords(QryStudyInfoListModel model) throws Exception {
    	StringBuffer condition =  new StringBuffer();
    	condition.append("select count(1) record_num,Studystatus_Code from AIS_StudyInfo a, AIS_PatientInfo b where a.patient_globalid = b.patient_globalid ");
    	Statement stmt = null;
    	ResultSet rs = null;
    	ResultSet rsCount =null;
    	List<Map> list = new ArrayList();
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	        //远程诊断
	        if(model.getStudyType()==1){
	        	if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
	                condition.append(" and ( study_consultorg = " + model.getOrgId() +" or org_id = " + model.getOrgId() +")");
	  		      }
	  		      if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
	  		          condition.append(" and ( study_consultloc = " + model.getLocId() +" or loc_id = " + model.getLocId() +")");
	  		      }  
	            condition.append(" and Study_Type = 1 " ); 
	            if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            if(isNotBlank(model.getStartTime())){ 
		            	condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		    		}
		            if(isNotBlank(model.getEndTime())){
		            	condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		    		}  
	            }
	        }else if(model.getStudyType()==0){
	        	if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
	                condition.append(" and a.org_id = " + model.getOrgId() );
	            }
	            if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
	                condition.append(" and a.loc_id = " + model.getLocId() );
	            }
	            condition.append(" and Study_Type = 0 " );
	            //用户选择的状态， 来决定选择用哪个时间: 1,如果只查询 ，登记 状态以后的病人用登记时间  。如果查询申请状态的  , 用申请时间 。查询所有状态的， 就用申请时间
	            //申请时间
	        	if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("APP")!=-1){
	        		if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	                }
	            //登记时间
	            }else if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("REG")!=-1){
	            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_DateTime >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_DateTime <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	                }
	            //申请时间
	            }else {
	            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	            	}
	            }
	        }else{
	        	  if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
	              condition.append(" and ( study_consultorg = " + model.getOrgId() +" or a.org_id = " + model.getOrgId() +")");
			      }
			      if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
			          condition.append(" and ( study_consultloc = " + model.getLocId() +" or a.loc_id = " + model.getLocId() +")");
			      }                        
	            
	            //用户选择的状态， 来决定选择用哪个时间: 1,如果只查询 ，登记 状态以后的病人用登记时间  。如果查询申请状态的  , 用申请时间 。查询所有状态的， 就用申请时间
	            //申请时间
	        	if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("APP")!=-1){
	        		if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	                }
	            //登记时间
	            }else if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("REG")!=-1){
	            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_DateTime >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_DateTime <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	                }
	            //申请时间
	            }else {
	            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	            	}
	            }
	        	if(isNotBlank(model.getAllStudyType())){
	        	    condition.append(" and Study_Type in( "+model.getAllStudyType()+") " );
	        	}
	        } 
	        if(model.getModalityId() != -1 && isNotBlank(String.valueOf(model.getModalityId()))){
	        	condition.append("and (select modality_id from aisc_equipment c where c.equipment_id=a.equipment_id)=" + model.getModalityId());
	        }
	        
	        if (isNotBlank(model.getPatientId())) {
	            condition.append(" and patient_id = " + model.getPatientId());
	        }
	        if(isNotBlank(model.getStudyAccnumber())){             
	            condition.append(" and Study_Accnumber = '" + model.getStudyAccnumber()+ "'");
	        }
	        if ("1".equals(model.getqType()) && isNotBlank(model.getqValue())) {
	            condition.append(" and patient_id = '" + model.getqValue()+"'");
	        }else if("2".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_id = '" + model.getqValue()+"'");
	        }else if("3".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_name = '" + model.getqValue()+"'");
	        }else if("4".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_outpatientid = '" + model.getqValue().trim()+"'");
	        }else if("5".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_inpatientid = '" + model.getqValue().trim()+"'");
	        }else if("6".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_idnumber like '%" + model.getqValue().trim()+"%'");
	        }else if("7".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and old_patient_id = '" + model.getqValue().trim()+"'");
	        }
	        if (!"-1".equals(model.getStudystatusCode())  && isNotBlank(model.getStudystatusCode())) {
	            condition.append(" and Studystatus_Code in (" + model.getStudystatusCode() +")");
	        }
	        int num = getIsFlagHZOrg(model.getOrgId(),String.valueOf(model.getLocId()));
	        if(num>0){
	        	condition.append(" and Studystatus_Code != 'AppSave' ");
	        	condition.append(" and Studystatus_Code != 'STUDYED' ");
	        }
	        
	        //已取消记录不展现
	        condition.append(" and Studystatus_Code != 'AppCancel' ");
	        
	        //选择【我的】时，只检查分配给我的会诊记录
	        if("1".equals(model.getConsultFlag())){
	        	condition.append(" and study_consultdoctorid = " + model.getDoctorId());
	        	condition.append(" and studystatus_code = 'AppVerify' ");//审核通过
	        }         
	        
	        //检查消息提示框中的检查记录
	        if(null != model.getMessageFlag()){
	        	if(model.getMessageFlag().equals("5") || model.getMessageFlag().equals("2")){
	        		condition.append("  and  Studystatus_Code = 'APPConsult' ");
	            }        
	            if(model.getMessageFlag().equals("3") || model.getMessageFlag().equals("4")){
	        		condition.append(" and  Studystatus_Code = 'VERIFY' ");
	            }
	        }
	        
	        condition.append(" group by Studystatus_Code");
	        
	        rs = stmt.executeQuery(condition.toString());
	        
			while(rs.next()){
				Map map = new HashMap();
				map.put("record_num", rs.getLong("record_num"));
				map.put("studystatus_code", rs.getString("studystatus_code"));
				list.add(map);
			}		
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.release(rs, stmt, null);
			DBUtil.release(rsCount, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
        return list;
    }
    
    /**
     * 统计打印记录数
     */
    public Map queryPrintRecords(QryStudyInfoListModel model) throws Exception {
    	StringBuffer condition =  new StringBuffer();
    	condition.append("select count(1) print_num from AIS_StudyInfo a, AIS_PatientInfo b,ais_studyreport c where a.patient_globalid = b.patient_globalid and a.studyinfo_id=c.studyinfo_id and c.report_isprinted=1");
    	Statement stmt = null;
    	ResultSet rs = null;
    	ResultSet rsCount =null;
    	Map map = new HashMap();
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	        //远程诊断
	        if(1==model.getStudyType()){
	        	if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
	        		condition.append(" and ( study_consultorg = " + model.getOrgId() +" or org_id = " + model.getOrgId() +")");
	  		    }
  		      	if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
  		           condition.append(" and ( study_consultloc = " + model.getLocId() +" or loc_id = " + model.getLocId() +")");
  		      	}  
	            condition.append(" and Study_Type = 1 " ); 
	            if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            if(isNotBlank(model.getStartTime())){ 
		            	condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		    		}
		            if(isNotBlank(model.getEndTime())){
		            	condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		    		}  
	            }
	        }else if(0==model.getStudyType()){
	        	if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
	                condition.append(" and a.org_id = " + model.getOrgId() );
	            }
	            if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
	                condition.append(" and a.loc_id = " + model.getLocId() );
	            }
	            condition.append(" and Study_Type = 0 " );
	            //用户选择的状态， 来决定选择用哪个时间: 1,如果只查询 ，登记 状态以后的病人用登记时间  。如果查询申请状态的  , 用申请时间 。查询所有状态的， 就用申请时间
	            //申请时间
	        	if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("APP")!=-1){
	        		if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	                }
	            //登记时间
	            }else if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("REG")!=-1){
	            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_DateTime >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_DateTime <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	            	}
	            //申请时间
	            }else {
	            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	            	}
	            }
	        }else{
	        	  if (isNotBlank(model.getOrgId()) && !"null".equals(model.getOrgId() )) {
	              condition.append(" and ( study_consultorg = " + model.getOrgId() +" or a.org_id = " + model.getOrgId() +")");
			      }
			      if (model.getLocId() != 0 && isNotBlank(String.valueOf(model.getLocId())) ) {
			          condition.append(" and ( study_consultloc = " + model.getLocId() +" or a.loc_id = " + model.getLocId() +")");
			      }                        
	            
	            //用户选择的状态， 来决定选择用哪个时间: 1,如果只查询 ，登记 状态以后的病人用登记时间  。如果查询申请状态的  , 用申请时间 。查询所有状态的， 就用申请时间
	            //申请时间
	        	if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("APP")!=-1){
	        		if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	                }
	            //登记时间
	            }else if(!"-1".equals(model.getStudystatusCode()) && model.getStudystatusCode() !=null && model.getStudystatusCode().indexOf("REG")!=-1){
	            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_DateTime >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_DateTime <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	                }
	            //申请时间
	            }else {
	            	if(isNotBlank(model.getIsDate()) && "1".equals(model.getIsDate())){
		            	if(isNotBlank(model.getStartTime())){ 
		        			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		        		}
		                if(isNotBlank(model.getEndTime())){
		        			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		        		} 
	            	}
	            }
	        	if(isNotBlank(model.getAllStudyType())){
	        	    condition.append(" and Study_Type in( "+model.getAllStudyType()+") " );
	        	}
	        }       
	        
	        if(model.getModalityId() != -1 && isNotBlank(String.valueOf(model.getModalityId()))){
	        	condition.append("and (select modality_id from aisc_equipment c where c.equipment_id=a.equipment_id)=" + model.getModalityId());
	        }
	        
	        if (isNotBlank(model.getPatientId())) {
	            condition.append(" and patient_id = " + model.getPatientId());
	        }
	        if(isNotBlank(model.getStudyAccnumber())){             
	            condition.append(" and Study_Accnumber = '" + model.getStudyAccnumber()+ "'");
	        }
	        if ("1".equals(model.getqType()) && isNotBlank(model.getqValue())) {
	            condition.append(" and patient_id = '" + model.getqValue()+"'");
	        }else if("2".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_id = '" + model.getqValue()+"'");
	        }else if("3".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_name = '" + model.getqValue()+"'");
	        }else if("4".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_outpatientid = '" + model.getqValue().trim()+"'");
	        }else if("5".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_inpatientid = '" + model.getqValue().trim()+"'");
	        }else if("6".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and patient_idnumber like '%" + model.getqValue().trim()+"%'");
	        }else if("7".equals(model.getqType()) && isNotBlank(model.getqValue())){
	        	condition.append(" and old_patient_id = '" + model.getqValue().trim()+"'");
	        }
	        if (!"-1".equals(model.getStudystatusCode())  && isNotBlank(model.getStudystatusCode())) {
	            condition.append(" and Studystatus_Code in (" + model.getStudystatusCode() +")");
	        }
	        
	        int num = getIsFlagHZOrg(model.getOrgId(),String.valueOf(model.getLocId()));
	        if(num>0){
	        	condition.append(" and Studystatus_Code != 'AppSave' ");
	        	condition.append(" and Studystatus_Code != 'STUDYED' ");
	        }
	        //已取消记录不展现
	        condition.append(" and Studystatus_Code != 'Cancel' ");
	        
	        //选择【我的】时，只检查分配给我的会诊记录
	        if("1".equals(model.getConsultFlag())){
	        	condition.append(" and study_consultdoctorid = " + model.getDoctorId());
	        	condition.append(" and studystatus_code = 'AppVerify' ");//审核通过
	        }         
	        
	        //检查消息提示框中的检查记录
	        if(null != model.getMessageFlag()){
	        	if(model.getMessageFlag().equals("1") || model.getMessageFlag().equals("2")){
	        		condition.append(" and  a.studyinfo_id in (select studyinfo_id from ais_studymessage where status_code='APP')");
	            }        
	            if(model.getMessageFlag().equals("3") || model.getMessageFlag().equals("4")){
	        		condition.append(" and  a.studyinfo_id in (select studyinfo_id from ais_studymessage where status_code='VERIFY')");
	            }
	        }
	        rs = stmt.executeQuery(condition.toString());
	        
			while(rs.next()){
				map.put("print_num", rs.getLong("print_num"));
			}		
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.release(rs, stmt, null);
			DBUtil.release(rsCount, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
        return map;
    }
    
	//科室下拉列表查询
	public List<Map> getLocInfo(String orgId,String operatorId) throws Exception {		
		String sql =  " select * "
					 +" from aisc_loc a "
					 +"	where a.loc_id in (select loc_id "
					 +" from aisc_loginloc b "
					 +" where b.org_id = "+orgId+" "
					 +" and b.operator_id = "+operatorId+" ) ";
		Map<String, Object> map = new HashMap<String, Object>();		 
		AiscLocBean[] locBeans = AiscLocEngine.getBeansFromSql(sql, map); 
        List<Map> list = new ArrayList();
        for(AiscLocBean locBean:locBeans){
            list.add(ServiceUtil.transerBeanToMap(locBean));
        }
        return list;
	}
	
	//检查状态列表查询
	public List<Map> getAiscLocStatus(String locId,String orgId) throws Exception {		
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId) && !"undefined".equals(orgId) ) {
            condition.append(" and org_id = " + orgId );
        }
	    if (isNotBlank(locId) && !"undefined".equals(locId) ) {
            condition.append(" and loc_id = " + locId );
        }
		AiscLocStudyStaBean[] beans = AiscLocStudyStaEngine.getBeans(condition.toString(), null);
        List<Map> list = new ArrayList();
        for(AiscLocStudyStaBean statusBean:beans){
            list.add(ServiceUtil.transerBeanToMap(statusBean));
        }
        return list;
	}
	
	//科室下拉列表查询
	public AiscLocBean[] getLocInfos(String orgId) throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId) && !"undefined".equals(orgId) ) {
            condition.append(" and org_id = " + orgId );
        }
		AiscLocBean[] locBeans = AiscLocEngine.getBeans(condition.toString(), null); 
         
        return locBeans;
	}
	
	//根据医生编码查医姓名
	public String getCareProvInfo(String careProvCode) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(careProvCode)) {
            condition.append(" and careprov_id = '" + careProvCode + "'");
        }else{
        	return "";
        }	    
	    AiscCareProvBean[] beans = AiscCareProvEngine.getBeans(condition.toString(),null) ;
	    if(beans.length>0){
	    	 return beans[0].getCareprovName();
	    }else{
	    	return "";
	    }
	   
	}

	//浏览
	public QryStudyInfoListBean getWorkListDetail(String stydyinfoId)
			throws Exception {
		StringBuffer condition = new StringBuffer();
		condition.append(" 1=1");

		if (isNotBlank(stydyinfoId)) {
			condition.append(" AND StudyInfo_ID = " + stydyinfoId);
		}
		QryStudyInfoListBean[] beans = QryStudyInfoListEngine.getBeans(condition.toString(), null);
		 
		if(beans.length>0){
//			beans[0].setStudyAppdoc(getCareProvInfo(beans[0].getStudyDoctorid()));
			             
			if(beans[0].getStudyHavereport() == 1){
       		 StringBuffer condition2 = new StringBuffer();
       	     condition2.append(" studyinfo_id = '" + beans[0].getStudyinfoId() + "'" );
       	     condition2.append(" and rownum = 1 order by report_id desc " );            	     
       	     AisStudyReportBean[] reportBeans = AisStudyReportEngine.getBeans(condition2.toString(), null) ; 
       	     if(reportBeans.length>0){
//       	    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//       	    	beans[0].setReportDatetime(reportBeans[0].getReportDatetime()==null?"": sdf.format(reportBeans[0].getReportDatetime()));
//       	    	beans[0].setReportDoctorname(reportBeans[0].getReportDoctorid()==null?"":getCareProvInfo(reportBeans[0].getReportDoctorid()));
//       	    	beans[0].setReportVerifydoctorname(reportBeans[0].getReportVerifydoctorid()==null?"":getCareProvInfo(reportBeans[0].getReportVerifydoctorid()));
//       	    	beans[0].setReportFinaldoctorname(reportBeans[0].getReportFinaldoctorid()==null?"":getCareProvInfo(reportBeans[0].getReportFinaldoctorid()));
       	     }
       	     
       	     
       	   }
			DictItemModel dict = dictSV.getDictItem("STUDY_STATUS",beans[0].getStudystatusCode());
      	    beans[0].setStudystatusCode(dict.getItemName());
			//申请医生名称, 处理从his过来的数据存放的是医生名称的问题
        	if(beans[0].getStudyHisappid()!=null&&!"".equals(beans[0].getStudyHisappid())){
        		beans[0].setStudyAppdoc(beans[0].getStudyAppdoc());
        	}else{
        		beans[0].setStudyAppdoc(beans[0].getStudyAppdocname());
        	}   
			 
			return beans[0];
		}else{
			return new QryStudyInfoListBean();
		}
		
	}
	
	public Map getMessage(String orgId,String locId,String userId) throws Exception{
		Map map = new HashMap();
		//远程诊断申请
		StringBuffer condition1 = new StringBuffer();
		condition1.append(" 1=1");
		if (isNotBlank(orgId)) {
			condition1.append(" AND study_consultorg = " + orgId);	
		}
		if (isNotBlank(locId) && !"undefined".equals(locId)) {
			condition1.append(" AND study_consultloc = " + locId);
		}
		condition1.append(" AND study_type = 1 ");
		condition1.append(" and Studystatus_Code = 'APPConsult' ");
		int diagnoseTotal = QryStudyInfoListEngine.getBeansCount(condition1.toString(), null);
		
		
		/*//远程会诊申请
		StringBuffer condition2 = new StringBuffer();
		condition2.append(" 1=1");
		if (isNotBlank(orgId)) {
			condition2.append(" AND org_id = " + orgId);
		}
		if (isNotBlank(locId) && !"undefined".equals(locId) ) {
			condition2.append(" AND loc_id = " + locId);
		}
		condition2.append(" AND study_type = 1 "); 
		condition1.append(" and  studyinfo_id in (select studyinfo_id from ais_studymessage where status_code='APP')");
		QryCareProvBean  careProv = loginLocsv.getCareprovByOperatorId(orgId, locId, userId);
		if(null != careProv){
			condition2.append(" AND Study_ConsultDoctorID = " + careProv.getCareprovId()); 
		}		
		int consultTotal = QryStudyInfoListEngine.getBeansCount(condition2.toString(), null);*/
		
		//远程诊断--已审核
		StringBuffer condition3 = new StringBuffer();
		condition3.append(" 1=1");
		if (isNotBlank(orgId)) {
			condition3.append(" AND study_consultorg = " + orgId);	
		}
		if (isNotBlank(locId) && !"undefined".equals(locId)) {
			condition3.append(" AND study_consultloc = " + locId);
		}
		condition3.append(" and Study_Type = 1 ");
		condition3.append(" and Studystatus_Code != 'Cancel' ");
		condition3.append(" and Studystatus_Code = 'VERIFY' ");
		int diagnoseVerifyTotal = QryStudyInfoListEngine.getBeansCount(condition3.toString(), null);
		
		
		/*//远程会诊申请--已审核
		StringBuffer condition4 = new StringBuffer();
		condition4.append(" 1=1");
		if (isNotBlank(orgId)) {
			condition4.append(" AND org_id = " + orgId);
		}
		if (isNotBlank(locId) && !"undefined".equals(locId)) {
			condition4.append(" AND loc_id = " + locId);
		}
		condition4.append(" AND study_type = 1 AND status_code='VERIFY' "); 
		QryCareProvBean  careProv2 = loginLocsv.getCareprovByOperatorId(orgId, locId, userId);
		if(null != careProv2 && careProv2.getCareprovId() != 0){
			condition4.append(" AND Study_ConsultDoctorID = " + careProv2.getCareprovId()); 
		}		
		int consultVerifyTotal = QryStudyMessageEngine.getBeansCount(condition4.toString(), null);*/
		
		map.put("diagnoseTotal", diagnoseTotal);
		//map.put("consultTotal", consultTotal);
		map.put("diagnoseVerifyTotal", diagnoseVerifyTotal);
		//map.put("consultVerifyTotal", consultVerifyTotal);
		
		return map;
	}
	public void saveReprtStatus(String orgId,String locId,String userId) throws Exception{
		Map map = new HashMap();
		//远程诊断申请
		StringBuffer condition1 = new StringBuffer();
		condition1.append(" 1=1");
		if (isNotBlank(orgId)) {
			condition1.append(" AND study_consultorg = " + orgId);
		}
		if (isNotBlank(locId) && !"undefined".equals(locId)) {
			condition1.append(" AND study_consultloc = " + locId);
		}
		condition1.append(" AND study_type = 1 ");
		condition1.append(" and  studyinfo_id in (select studyinfo_id from ais_studymessage where status_code='APP')");
		QryStudyInfoListBean [] qryStudyInfoListBeans = QryStudyInfoListEngine.getBeans(condition1.toString(), null);
		if(qryStudyInfoListBeans != null && qryStudyInfoListBeans.length>0){
			for(QryStudyInfoListBean qryStudyInfoListBean : qryStudyInfoListBeans){
				if(StringUtils.isNotBlank(String.valueOf(qryStudyInfoListBean.getStudyinfoId()))){
	                StringBuffer conditionBuffer = new StringBuffer("");
	        		conditionBuffer.append(" update ais_studymessage t  set t.status_code='READ' where t.studyinfo_id='"+qryStudyInfoListBean.getStudyinfoId()+"'");
	        		 ServiceManager.getDataStore().execute(
	                         ServiceManager.getSession().getConnection(),
	                         conditionBuffer.toString(), map);
	        	}
			}
		}
	}
	public void saveReportStutusVerrify(String orgId,String locId,String userId) throws Exception{
		//远程诊断--已审核
		Map map = new HashMap();
		StringBuffer condition3 = new StringBuffer();
		condition3.append(" 1=1");
		if (isNotBlank(orgId)) {
			condition3.append(" AND org_id = " + orgId);
		}
		if (isNotBlank(locId) && !"undefined".equals(locId)) {
			condition3.append(" AND loc_id = " + locId);
		} 
		condition3.append(" and Study_Type = 1 ");
		condition3.append(" and Studystatus_Code != 'Cancel' ");
		condition3.append(" and  studyinfo_id in (select studyinfo_id from ais_studymessage where status_code='VERIFY')");
		QryStudyInfoListBean [] qryStudyInfoListBeans = QryStudyInfoListEngine.getBeans(condition3.toString(), null);
		if(qryStudyInfoListBeans != null && qryStudyInfoListBeans.length>0){
			for(QryStudyInfoListBean qryStudyInfoListBean : qryStudyInfoListBeans){
				if(StringUtils.isNotBlank(String.valueOf(qryStudyInfoListBean.getStudyinfoId()))){
	                StringBuffer conditionBuffer = new StringBuffer("");
	        		conditionBuffer.append(" update ais_studymessage t  set t.status_code='READ' where t.studyinfo_id='"+qryStudyInfoListBean.getStudyinfoId()+"'");
	        		 ServiceManager.getDataStore().execute(
	                         ServiceManager.getSession().getConnection(),
	                         conditionBuffer.toString(), map);
	        	}
			}
		}
	}
	@Override
	public ResultDTO queryRisInfoList(QryRisInfoModel model, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        
        if(model.getLocId()!=-1&&isNotBlank(String.valueOf(model.getLocId()))) {
        	condition.append(" and loc_id = '"+model.getLocId()+"' ");
        }
        if(model.getStudyStarttime()!=null) {
        	Timestamp time = model.getStudyStarttime();
        	DateFormat  fmt = new SimpleDateFormat("yyyy-MM-dd");
        	condition.append(" and  to_char(Study_StartTime,'yyyy-mm-dd')='"+fmt.format(time)+"' ");
        }
        if (isNotBlank(model.getOrgId()) && !"undefined".equals(model.getOrgId()) ) {
            condition.append(" and org_id = " + model.getOrgId() );
        }
        int total = QryRisInfoEngine.getBeansCount(condition.toString(), null);
        QryRisInfoBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY patient_name desc");
            beans = QryRisInfoEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans,QryRisInfoModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
	
	@Override
	public ResultDTO queryPacsInfoList(QryPacsInfoModel model, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(model.getReceptiondate())) {
        	String time = model.getReceptiondate().replace("-","");
        	condition.append(" and receptiondate='"+time+"' ");
        }
        if (!"-1".equals(model.getModalitiesinstudy())&&isNotBlank(String.valueOf(model.getModalitiesinstudy()))) {
            condition.append(" and ModalitiesInStudy='"+model.getModalitiesinstudy()+"' ");
        }

        int total = QryPacsInfoEngine.getBeansCount(condition.toString(), null);
        QryPacsInfoBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY PatientName desc");
            beans = QryPacsInfoEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans,QryPacsInfoModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
	
	public void updateControlInfo(long  patientkey,String accessionnumber,String patientid) throws Exception{
		Statement stmt = null;
		try{
    		stmt = ServiceManager.getSession().getConnection().createStatement();
    		String sql = "update Patient set PatientId='"+patientid+"' where patientkey='"+patientkey+"'";
    		stmt.executeUpdate(sql);
	    	String sql2 = "update Study set AccessionNumber='"+accessionnumber+"' where patientkey='"+patientkey+"'";
	    	stmt.executeUpdate(sql2);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.release(null, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
		
	}
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
	public static String delHTMLTag(String htmlStr) {  
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 杩囨护script鏍囩  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 杩囨护style鏍囩  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // html  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 杩囨护绌烘牸鍥炶溅鏍囩  
        htmlStr = htmlStr.replaceAll("&nbsp;","");
        return htmlStr; // 杩斿洖鏂囨湰瀛楃涓� 
    }  
	
	/**
	 * 工作列表2----左下查询
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryStudyList(QryStudyPatientModel model, ResultDTO resultDTO) throws Exception
	{
		StringBuffer condition = new StringBuffer();
		condition.append(" 1=1 ");
		if ("1".equals(model.getqType()) && isNotBlank(model.getqValue())) {
            condition.append(" and patientid = '" + model.getqValue()+"'");
        }else if("2".equals(model.getqType()) && isNotBlank(model.getqValue())){
        	condition.append(" and accessionnumber = '"+model.getqValue()+ "'");
        }
		if(isNotBlank(model.getOrgId())){
			condition.append(" and institutionID = '"+model.getOrgId()+ "'");
		}
		if(!model.getModalitiesinstudy().equals("-1")&&isNotBlank(model.getModalitiesinstudy())){
			condition.append(" and modalitiesinstudy like '%"+model.getModalitiesinstudy()+ "%'");
		}
		if(isNotBlank(model.getStartDate())){
			condition.append(" and to_date(studydate,'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
		}
		if(isNotBlank(model.getEndDate())){
			condition.append(" and to_date(studydate,'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
		}
		int total = QryStudyPatientEngine.getBeansCount(condition.toString(), null);
		QryStudyPatientBean[] beans = null;
		if (total > 0) {
			beans = QryStudyPatientEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		resultDTO.setRows(BeanUtils.beanToList(beans,QryStudyPatientModel.class,dicts));
		resultDTO.setRecords(total);
		return resultDTO;
	}
	
	/**
	 * 工作列表2----右下查询
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryStudyRightDownList(QryStudyInfoListModel model, ResultDTO resultDTO) throws Exception
	{
		StringBuffer condition = new StringBuffer();
		if(isNotBlank(model.getStudyAccnumber())){
			condition.append(" 1=1 ");
			condition.append(" and study_accnumber = '"+model.getStudyAccnumber()+ "'");
		}else{
			condition.append(" 1<>1 ");
		}
		if(isNotBlank(model.getStudyAccnumber())){
			condition.append(" and org_id = '"+model.getOrgId()+ "'");
		}
		int total = QryStudyInfoListEngine .getBeansCount(condition.toString(), null);
		QryStudyInfoListBean[] beans = null;
		if (total > 0) {
			beans = QryStudyInfoListEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        dicts.put("studystatusCode", new DictTranslator("studystatusCode","STUDY_STATUS","studystatusCode"));
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
        dicts.put("patienttypeCode", new DictTranslator("patienttypeCode","PATIENT_TYPE","patienttypeCode"));
		resultDTO.setRows(BeanUtils.beanToList(beans,QryStudyInfoListModel.class,dicts));
		resultDTO.setRecords(total);
		return resultDTO;
	}
	
	public String getLocType(long locId) throws Exception{
		AiscLocBean bean = AiscLocEngine.getBean(locId);
		return bean==null?"":bean.getLocType();
	}
	
	public String getLoginloc(String operatorId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
		condition.append(" 1=1 ");
		if(isNotBlank(operatorId)){
			condition.append(" and operator_Id = "+operatorId);
		}
		if(isNotBlank(orgId)){
			condition.append(" and org_id = '"+orgId+ "'");
		}
		AiscLoginLocBean[] loginlocBean = AiscLoginLocEngine.getBeans(condition.toString(), null);
		if(loginlocBean!=null&&loginlocBean.length>0){
			return String.valueOf(loginlocBean[0].getLocId());
		}
		return "";
	}
	
}
