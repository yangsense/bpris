package com.ai.aris.server.statanalysis.service.impl;


import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.bean.CommonEngine;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.statanalysis.bean.AiscPatientCheckCountyrpBean;
import com.ai.aris.server.statanalysis.bean.AiscPatientCheckLocrpBean;
import com.ai.aris.server.statanalysis.bean.AiscPatientCheckRpBean;
import com.ai.aris.server.statanalysis.bean.QryHzCountBean;
import com.ai.aris.server.statanalysis.bean.QryHzMultipleListBean;
import com.ai.aris.server.statanalysis.bean.QryHzMultipleListEngine;
import com.ai.aris.server.statanalysis.bean.QryHzOrgBean;
import com.ai.aris.server.statanalysis.bean.QryHzOrgEngine;
import com.ai.aris.server.statanalysis.bean.QryHzRecordListBean;
import com.ai.aris.server.statanalysis.bean.QryHzRecordListEngine;
import com.ai.aris.server.statanalysis.bean.QryMedicalCountDetailBean;
import com.ai.aris.server.statanalysis.bean.QryMedicalCountDetailEngine;
import com.ai.aris.server.statanalysis.bean.QrySQLocBean;
import com.ai.aris.server.statanalysis.bean.QrySQLocEngine;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckCountyrpModel;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckLocrpModel;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckRpModel;
import com.ai.aris.server.statanalysis.model.QryHzMultipleListModel;
import com.ai.aris.server.statanalysis.model.QryHzRecordListModel;
import com.ai.aris.server.statanalysis.service.interfaces.IPatientWorkloadListSV;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;

public class PatientWorkloadListSVImpl implements IPatientWorkloadListSV{
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);


	/**
	 * 获取市级级检查统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryCityPageList(AiscPatientCheckCountyrpModel model, ResultDTO resultDTO) throws Exception
	{
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  C.CITY_CODE, --城市代码 \n" +
				"                  C.CITY_DESC, --城市名称\n" +
				"                  COUNT(1) AS REGIST_NUM, --登记数量\n" +
				"                  SUM(A.STUDY_HAVEIMAGE) AS COLLECT_NUM, --采集数量\n" +
				"                  SUM(decode(A.Study_Imagecount,null,0,A.Study_Imagecount)) AS IMAGE_NUM, --图像数量\n" +
				"                  SUM(A.STUDY_HAVEREPORT) AS REPORT_NUM, --报告数量\n" +
				"                  SUM(DECODE(A.STUDYSTATUS_CODE, 'VERIFY', 1, 0)) AS CHECKED_NUM --审核数量\n" +
				"  FROM AIS_STUDYINFO A, AIS_PATIENTINFO B,AISC_AREAINSTITUTION C "+
				" WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID and C.org_id=A.org_id ");
		if(!"-1".equals(model.getCityCode())){
			condition.append(" and C.CITY_CODE = '"+model.getCityCode()+ "'");
		}
		if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append(" \n" +
				"           GROUP BY C.CITY_CODE,\n" +
				"                     C.CITY_DESC");
		int total = CommonEngine.getCountFromSql("select count(1) from ("+condition.toString() +")",null);
		AiscPatientCheckCountyrpBean[] beans = null;
		if (total > 0) {
			beans = CommonEngine.getBeansFromSql(condition.toString(),null,resultDTO.getStart(), resultDTO.getEnd(),AiscPatientCheckCountyrpBean.class) ;
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		resultDTO.setRows(BeanUtils.beanToList(beans,AiscPatientCheckCountyrpModel.class,dicts));
		resultDTO.setRecords(total);
		return resultDTO;
	}

	/**
	 * 获取区县级检查统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryCountyPageList(AiscPatientCheckCountyrpModel model, ResultDTO resultDTO) throws Exception
	 {
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  C.CITY_CODE, --城市代码 \n" +
				"                  C.CITY_DESC, --城市名称\n" +
				"                  C.COUNTY_CODE, --城市名称\n" +
				"                  C.COUNTY_DESC, --城市名称\n" +
				"                  COUNT(1) AS REGIST_NUM, --登记数量\n" +
				"                  SUM(A.STUDY_HAVEIMAGE) AS COLLECT_NUM, --采集数量\n" +
				"                  SUM(decode(A.Study_Imagecount,null,0,A.Study_Imagecount)) AS IMAGE_NUM, --图像数量\n" +
				"                  SUM(A.STUDY_HAVEREPORT) AS REPORT_NUM, --报告数量\n" +
				"                  SUM(DECODE(A.STUDYSTATUS_CODE, 'VERIFY', 1, 0)) AS CHECKED_NUM --审核数量\n" +
				"  FROM AIS_STUDYINFO A, AIS_PATIENTINFO B,AISC_AREAINSTITUTION C "+
				" WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID and C.org_id=A.org_id ");
		if(!"-1".equals(model.getCountyCode())){
			 condition.append(" and C.COUNTY_CODE = '"+model.getCountyCode()+ "'");
		}
		if(!"-1".equals(model.getCityCode())){
			condition.append(" and C.CITY_CODE = '"+model.getCityCode()+ "'");
		}
		if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		 condition.append("  GROUP BY  C.CITY_CODE, \n" +
				 "       C.CITY_DESC, \n" +
				 "       C.COUNTY_CODE, \n" +
				 "       C.COUNTY_DESC ");
		 int total = CommonEngine.getCountFromSql("select count(1) from ("+condition.toString() +")",null);
		 AiscPatientCheckCountyrpBean[] beans = null;
		 if (total > 0) {
			 beans = CommonEngine.getBeansFromSql(condition.toString(),null,resultDTO.getStart(), resultDTO.getEnd(),AiscPatientCheckCountyrpBean.class) ;
		 }
		 Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		 resultDTO.setRows(BeanUtils.beanToList(beans,AiscPatientCheckCountyrpModel.class,dicts));
		 resultDTO.setRecords(total);
        return resultDTO;
    }

	/**
	 * 获取科室级检查统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryLocPageList(AiscPatientCheckLocrpModel model, ResultDTO resultDTO) throws Exception{
		StringBuffer condition = new StringBuffer("SELECT C.CITY_CODE, --城市代码 \n" +
				"       C.CITY_DESC, --城市名称\n" +
				"       C.COUNTY_CODE, --区县代码 \n" +
				"       C.COUNTY_DESC, --区县名称\n" +
				"       A.ORG_ID, --机构ID \n" +
				"       C.Org_Name ORG_DESC, --机构名称\n" +
				"       D.LOC_CODE, --科室代码\n" +
				"       D.LOC_DESC, --科室名称\n" +
				"                  COUNT(1) AS REGIST_NUM, --登记数量\n" +
				"                  SUM(A.STUDY_HAVEIMAGE) AS COLLECT_NUM, --采集数量\n" +
				"                  SUM(decode(A.Study_Imagecount,null,0,A.Study_Imagecount)) AS IMAGE_NUM, --图像数量\n" +
				"                  SUM(A.STUDY_HAVEREPORT) AS REPORT_NUM, --报告数量\n" +
				"                  SUM(DECODE(A.STUDYSTATUS_CODE, 'VERIFY', 1, 0)) AS CHECKED_NUM --审核数量\n" +
				"  FROM AIS_STUDYINFO A, AIS_PATIENTINFO B,AISC_AREAINSTITUTION C,aisc_loc D "+
				" WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID and C.org_id=A.org_id and d.loc_id=a.loc_id");

		if(!"-1".equals(model.getCountyCode())){
			 condition.append(" and C.COUNTY_CODE = '"+model.getCountyCode()+ "'");
		}
		if(!"-1".equals(model.getCityCode())){
			condition.append(" and C.CITY_CODE = '"+model.getCityCode()+ "'");
		}
		if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append("  GROUP BY  C.CITY_CODE, \n" +
				"       C.CITY_DESC, \n" +
				"       C.COUNTY_CODE, \n" +
				"       C.COUNTY_DESC,\n" +
				"       A.ORG_ID, \n" +
				"       C.Org_Name, \n" +
				"       D.LOC_CODE, \n" +
				"       D.LOC_DESC ");
		int total = CommonEngine.getCountFromSql("select count(1) from ("+condition.toString() +")",null);
		AiscPatientCheckLocrpBean[] beans = null;
		if (total > 0) {
			beans = CommonEngine.getBeansFromSql(condition.toString(),null,resultDTO.getStart(), resultDTO.getEnd(),AiscPatientCheckLocrpBean.class) ;
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		resultDTO.setRows(BeanUtils.beanToList(beans,AiscPatientCheckLocrpModel.class,dicts));
		resultDTO.setRecords(total);
        return resultDTO;
	}


	/**
	 * 获取患者检查信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryPatientPageList(AiscPatientCheckRpModel model, ResultDTO resultDTO) throws Exception{
		StringBuffer condition = new StringBuffer("SELECT C.CITY_CODE, --城市代码 \n" +
				"       C.CITY_DESC, --城市名称\n" +
				"       C.COUNTY_CODE, --区县代码 \n" +
				"       C.COUNTY_DESC, --区县名称\n" +
				"       A.ORG_ID, --机构ID \n" +
				"       C.Org_Name ORG_DESC, --机构名称\n" +
				"       D.LOC_CODE, --科室代码\n" +
				"       D.LOC_DESC, --科室名称\n" +
				"       b.patient_id REGIST_NO,\n" +
				"       b.patient_name,\n" +
				"       a.patient_age,\n" +
				"       decode(b.patient_sex,'1','男','2','女','未知') patient_sex,\n" +
				"       a.study_itemdesc check_item,\n" +
				"       a.study_imagecount IMAGE_NUM,\n" +
				"       (select careprov_name from aisc_careprov where careprov_id=f.report_doctorid) report_doc,\n" +   
				"       (select careprov_name from aisc_careprov where careprov_id=f.report_finaldoctorid) check_doc\n" +   
				"  FROM AIS_STUDYINFO A, AIS_PATIENTINFO B,AISC_AREAINSTITUTION C,aisc_loc D ,ais_studyreport f "+
				" WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID and C.org_id=A.org_id and d.loc_id=a.loc_id and f.studyinfo_id(+)=a.studyinfo_id ");
		
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		if(!"-1".equals(model.getCityCode())){
			condition.append(" and C.CITY_CODE = '"+model.getCityCode()+ "'");
		}
		if(!"-1".equals(model.getCountyCode())){
			condition.append(" and C.COUNTY_CODE = '"+model.getCountyCode()+"'");
		}
		if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getOrgId()+ "'");
		}
		if(isNotBlank(model.getLocCode())){
			condition.append(" and D.LOC_CODE = '"+model.getLocCode()+"'");
		}
		
		int total = CommonEngine.getCountFromSql("select count(1) from ("+condition.toString() +")",null);
		AiscPatientCheckRpBean[] beans = null;
		if (total > 0) {
			beans = CommonEngine.getBeansFromSql(condition.toString(), null,resultDTO.getStart(), resultDTO.getEnd(),AiscPatientCheckRpBean.class) ;
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		resultDTO.setRows(BeanUtils.beanToList(beans,AiscPatientCheckRpModel.class,dicts));
		resultDTO.setRecords(total);
		return resultDTO;
	}

	public QrySQLocBean[] getSQLoc(String orgId)throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId)) {
            condition.append(" and org_id = "+orgId+" ");
        } 	
//	    if (isNotBlank(orgId)) {
//            condition.append(" and LOC_ID = "+locId+" ");
//        }
	    condition.append(" group by loc_id,loc_name,org_id ");
	    QrySQLocBean[] beans = QrySQLocEngine.getBeans(condition.toString(),null) ;
	    return beans; 
	}
	
	public QryHzOrgBean[] getHzOrg(String orgId,String locId,String conorgId)throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId)) {
            condition.append(" and org_id = "+orgId+" ");
        } 	
	    if (isNotBlank(locId)&&!"-1".equals(locId)) {
            condition.append(" and LOC_ID = "+locId+" ");
        }
	    if (isNotBlank(conorgId)&&!"-1".equals(conorgId)) {
            condition.append(" and conorg_Id = "+conorgId+" ");
        }
	    QryHzOrgBean[] beans = QryHzOrgEngine.getBeans(condition.toString(),null) ;
	    return beans; 
	}
	
	//查询会诊统计记录
    public ResultDTO queryHzRecordList(QryHzRecordListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (!"-1".equals(model.getOrgId())  && isNotBlank(model.getOrgId())) {
            condition.append(" and org_id = '" + model.getOrgId() +"'");
        }
        if (model.getLocId()!=-1&& isNotBlank(String.valueOf(model.getLocId()))) {
            condition.append(" and loc_id = '" + model.getLocId() +"'");
        }
        if (!"-1".equals(model.getStudyConsultorg())  && isNotBlank(model.getStudyConsultorg())) {
            condition.append(" and study_consultorg = '" + model.getStudyConsultorg() +"'");
        }
        if (model.getStudyConsultloc()!=-1 && isNotBlank(String.valueOf(model.getStudyConsultloc()))) {
            condition.append(" and study_consultloc = '" + model.getStudyConsultloc() +"'");
        }
        if(isNotBlank(model.getStudyStarttime())){ 
			condition.append(" and study_datetime >= to_date('"+model.getStudyStarttime()+" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
        if(isNotBlank(model.getStudyEndtime())){
			condition.append(" and study_datetime <= to_date('"+model.getStudyEndtime()+" 00:00:00' ,'yyyy-mm-dd hh24:mi:ss')");
		} 
        
        int total = QryHzRecordListEngine .getBeansCount(condition.toString(), null);
        QryHzRecordListBean[] beans = null;

        if (total > 0) {
        	//排序处理
        	if(!"".equals(sidx)){
        		condition.append(" ORDER BY "+sidx+" "+sord+" ");
        	}else{
        		condition.append(" ORDER BY study_datetime desc");
        	}
            
            beans = QryHzRecordListEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        } 
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QryHzRecordListModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    
    //查询会诊综合统计记录
    public ResultDTO queryHzRecordMutiList(QryHzMultipleListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (!"-1".equals(model.getOrgId())  && isNotBlank(model.getOrgId())) {
            condition.append(" and org_id = '" + model.getOrgId() +"'");
        }
        if (!"-1".equals(model.getCityCode())&& isNotBlank(model.getCityCode())) {
			condition.append(" and city_code = '"+model.getCityCode()+"'");
		}
		if (!"-1".equals(model.getCountyCode())&& isNotBlank(model.getCountyCode())) {
			condition.append(" and county_code ='"+model.getCountyCode()+"'");
		}
        if(isNotBlank(model.getStudyStarttime())){ 
			condition.append(" and to_date(study_datetime,'yyyy-mm-dd hh24:mi:ss') >= to_date('"+model.getStudyStarttime()+" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
        if(isNotBlank(model.getStudyEndtime())){
			condition.append(" and to_date(study_datetime,'yyyy-mm-dd hh24:mi:ss') <= to_date('"+model.getStudyEndtime()+" 00:00:00' ,'yyyy-mm-dd hh24:mi:ss')");
		} 
        
        int total = QryHzMultipleListEngine .getBeansCount(condition.toString(), null);
        QryHzMultipleListBean[] beans = null;

        if (total > 0) {
        	//排序处理
        	if(!"".equals(sidx)){
        		condition.append(" ORDER BY "+sidx+" "+sord+" ");
        	}else{
        		condition.append(" ORDER BY study_datetime desc");
        	}
            
            beans = QryHzMultipleListEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        } 
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans,QryHzMultipleListModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    
    public ResultDTO getDetailHzRecord(QryHzMultipleListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
		if (!"-1".equals(model.getCountyCode())&& isNotBlank(model.getCountyCode())) {
			condition.append(" and county_code ='"+model.getCountyCode()+"'");
		}
        if(isNotBlank(model.getCheckDate())){ 
			condition.append(" and to_date(study_datetime,'yyyy-mm-dd')= to_date('"+model.getCheckDate()+"','yyyy-mm-dd')");
		}
        
        int total = QryHzMultipleListEngine .getBeansCount(condition.toString(), null);
        QryHzMultipleListBean[] beans = null;

        if (total > 0) {
        	//排序处理
        	if(!"".equals(sidx)){
        		condition.append(" ORDER BY "+sidx+" "+sord+" ");
        	}else{
        		condition.append(" ORDER BY study_datetime desc");
        	}
            
            beans = QryHzMultipleListEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        } 
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans,QryHzMultipleListModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    
    public  Map<String, String> getTotalInfo(QryHzMultipleListModel model) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer condition = new StringBuffer();
		condition.append("   select sum(hzsqsl) hzsqsl,sum(hzsl) hzsl from (select b.city_code,b.county_code,b.county_desc,a.org_id,count(*) hzsqsl,to_char(a.study_datetime,'yyyy-mm-dd') study_datetime");
		condition.append("   ,count(c.report_id) hzsl from ");
		condition.append("   ais_studyinfo a ,AISC_AreaInstitution b,ais_studyreport c  where");
		condition.append("   a.org_id=b.org_id  and a.studyinfo_id=c.studyinfo_id(+) and study_type = '1' ");
		
		if (!"-1".equals(model.getOrgId())  && isNotBlank(model.getOrgId())) {
            condition.append(" and a.org_id = '" + model.getOrgId() +"'");
        }
        if (!"-1".equals(model.getCityCode())&& isNotBlank(model.getCityCode())) {
			condition.append(" and b.city_code = '"+model.getCityCode()+"'");
		}
		if (!"-1".equals(model.getCountyCode())&& isNotBlank(model.getCountyCode())) {
			condition.append(" and b.county_code ='"+model.getCountyCode()+"'");
		}
        if(isNotBlank(model.getStudyStarttime())){ 
			condition.append(" and a.study_datetime >= to_date('"+model.getStudyStarttime()+" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
        if(isNotBlank(model.getStudyEndtime())){
			condition.append(" and a.study_datetime <= to_date('"+model.getStudyEndtime()+" 00:00:00' ,'yyyy-mm-dd hh24:mi:ss')");
		} 
        condition.append(" group by b.city_code,b.county_code,b.county_desc,a.org_id,to_char(a.study_datetime,'yyyy-mm-dd')) ");
        QryHzMultipleListBean[] beans = QryHzMultipleListEngine.getBeansFromSql(condition.toString(), null);
        if (beans!=null && beans.length>0) {
			map.put("hzsqsl", beans[0].getHzsqsl()+"");
			map.put("hzsl", beans[0].getHzsl()+"");
		}
        return map;
	}
    
    public ResultDTO queryMedicalCountList(QryHzMultipleListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception{
    	Statement stmt = null;
    	ResultSet rsCount = null;
    	try{
	    	 stmt = ServiceManager.getSession().getConnection().createStatement();
	    	String sql = "  select * from (select B.*,rownum as row_index from ( select  d.city_code,d.county_code,d.county_desc,count(*) hzsl from ais_patientinfo a, AIS_StudyInfo b, "
	    			+ "AIS_StudyReport c ,AISC_AreaInstitution d   ";
	    	sql += " where a.patient_globalid = b.patient_globalid ";
	    	sql += " and d.org_id=b.org_id and b.studyinfo_id = c.studyinfo_id and studystatus_code not in ('Cancel')  ";
	        String sqlParam = "";
	        if (isNotBlank(String.valueOf(model.getStudyStarttime()))) {
	            sqlParam += " and  b.study_starttime >= to_date('"+model.getStudyStarttime()+"','yyyy-mm-dd hh24:mi:ss') ";
	        }
	        if (isNotBlank(String.valueOf(model.getStudyEndtime()))) {
	            sqlParam += " and  b.study_endtime<=to_date('"+model.getStudyEndtime()+"','yyyy-mm-dd hh24:mi:ss') ";
	        }
	        if (!"-1".equals(model.getCityCode())&& isNotBlank(model.getCityCode())) {
	        	sqlParam +=" and d.city_code = '"+model.getCityCode()+"'";
			}
			if (!"-1".equals(model.getCountyCode())&& isNotBlank(model.getCountyCode())) {
				sqlParam +=" and d.county_code ='"+model.getCountyCode()+"'";
			}
			if(isNotBlank(model.getCategories())) {
				sqlParam +=" and (c.REPORT_EXAM like '%" + model.getCategories() + "%' or c.REPORT_RESULT like '%" + model.getCategories() + "%' )";
	        }
			if(isNotBlank(model.getStartAge())){ 
				sqlParam +=" and substr(b.PATIENT_AGE,1,LENGTH(b.PATIENT_AGE) - 1) >= " + model.getStartAge();
			}
	        if(isNotBlank(model.getEndAge())){ 
	        	sqlParam +=" and substr(b.PATIENT_AGE,1,LENGTH(b.PATIENT_AGE) - 1) <= " + model.getEndAge();
			}
			
			sql+=sqlParam;
	        sql += " group by d.city_code,d.county_code,d.county_desc ";
	        int number = resultDTO.getLimit() * resultDTO.getPage();
	        int rowindex = 1 + (number - resultDTO.getLimit());
	        String sqlOrder = " ) B where rownum<=" + number + ") where  row_index >=" + rowindex + " and row_index <= " + number + " ";
	        ResultSet rs = stmt.executeQuery(sql + sqlOrder);
	        List<QryHzMultipleListModel> list = new ArrayList();
	        QryHzMultipleListModel hzModel = null;
	        while (rs.next()) {
	        	hzModel = new QryHzMultipleListModel();
	        	hzModel.setCountyCode(rs.getString("COUNTY_CODE")==null?"":rs.getString("COUNTY_CODE"));
	        	hzModel.setCountyDesc(rs.getString("COUNTY_DESC")==null?"":rs.getString("COUNTY_DESC"));
	        	hzModel.setHzsl(rs.getLong("hzsl"));
	            list.add(hzModel);
	        }
	        String sqlCount = "select count(*) COUNT from (select  d.city_code,d.county_code,d.county_desc,count(*) hzsl from ais_patientinfo a, AIS_StudyInfo b, "
	    			+ "AIS_StudyReport c ,AISC_AreaInstitution d   ";
	        sqlCount += " where a.patient_globalid = b.patient_globalid ";
	        sqlCount += " and d.org_id=b.org_id and b.studyinfo_id = c.studyinfo_id and studystatus_code not in ('Cancel')  ";
	        sqlCount += sqlParam;
	        sqlCount += " group by d.city_code,d.county_code,d.county_desc) ";
	        int total = 0;
	        rsCount = stmt.executeQuery(sqlCount);
	        if (rsCount.next()) {
	            total = rsCount.getInt("COUNT");
	        }
	        QryHzMultipleListModel[] loclist = new QryHzMultipleListModel[list.size()];
	        if (list != null && list.size() > 0) {
	            for (int i = 0; i < list.size(); i++) {
	                loclist[i] = list.get(i);
	            }
	        }
	        Map<String, DictTranslator> dicts = new HashMap<String, DictTranslator>();
	        resultDTO.setRows(BeanUtils.beanToList(loclist, QryHzMultipleListModel.class, dicts));
	//        dicts.put("locType", new DictTranslator("locType","LOC_TYPE","locTypeDesc"));
	        resultDTO.setRecords(total);
    	}catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.release(rsCount, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
        return resultDTO;
    }
    
    public ResultDTO queryMedicalCountDetailList(QryHzMultipleListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception{
    	StringBuffer condition = new StringBuffer();
        condition.append("1=1");
		 if(!"-1".equals(model.getCityCode())){
			 condition.append(" and CITY_CODE = '"+model.getCityCode()+ "'");
		 }
		 if(!"-1".equals(model.getCountyCode())){
			 condition.append(" and COUNTY_CODE = '"+model.getCountyCode()+"'");
		 }
		 if(isNotBlank(model.getStudyStarttime())){
			 condition.append(" and study_starttime >= to_date('"+model.getStudyStarttime()+"','yyyy-mm-dd hh24:mi:ss') ");
		 }
		 if(isNotBlank(model.getStudyEndtime())){
			 condition.append(" and study_endtime <= to_date('"+model.getStudyEndtime()+"','yyyy-mm-dd hh24:mi:ss') ");
		 }
        int total = QryMedicalCountDetailEngine.getBeansCount(condition.toString(), null);
        QryMedicalCountDetailBean[] beans = null;
        if (total > 0) {
            condition.append(" ORDER BY study_endtime DESC ");
            beans = QryMedicalCountDetailEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>(); 
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QryHzMultipleListModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    
    /**
	 * 获取会诊市级统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryHzCityPageList(QryHzMultipleListModel model, ResultDTO resultDTO) throws Exception
	{
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  B.CITY_CODE, --城市代码 \n" +
				"                  B.CITY_DESC, --城市名称\n" +
				"                  count(*) hz_count, \n" +
				"                  count(c.report_id) hz_resultcount\n" +
				"  from ais_studyinfo a, AISC_AreaInstitution b, ais_studyreport c "+
				" where a.org_id = b.org_id and a.studyinfo_id = c.studyinfo_id(+)  and study_type = '1' ");
		if(!"-1".equals(model.getCityCode())){
			condition.append(" and B.CITY_CODE = '"+model.getCityCode()+ "'");
		}
		if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append(" \n" +
				"           GROUP BY B.CITY_CODE,\n" +
				"                     B.CITY_DESC");
		int total = CommonEngine.getCountFromSql("select count(1) from ("+condition.toString() +")",null);
		QryHzCountBean[] beans = null;
		if (total > 0) {
			beans = CommonEngine.getBeansFromSql(condition.toString(),null,resultDTO.getStart(), resultDTO.getEnd(),QryHzCountBean.class) ;
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		resultDTO.setRows(BeanUtils.beanToList(beans,QryHzMultipleListModel.class,dicts));
		resultDTO.setRecords(total);
		return resultDTO;
	}
	
	/**
	 * 获取会诊区县统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryHzCountyPageList(QryHzMultipleListModel model, ResultDTO resultDTO) throws Exception
	{
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  B.CITY_CODE, --城市代码 \n" +
				"                  B.CITY_DESC, --城市名称\n" +
				"                  B.COUNTY_CODE, --\n" +
				"                  B.COUNTY_DESC, --\n" +
				"                  count(*) hz_count, \n" +
				"                  count(c.report_id) hz_resultcount\n" +
				"  from ais_studyinfo a, AISC_AreaInstitution b, ais_studyreport c "+
				" where a.org_id = b.org_id and a.studyinfo_id = c.studyinfo_id(+)  and study_type = '1' ");
		if(!"-1".equals(model.getCountyCode())){
			 condition.append(" and B.COUNTY_CODE = '"+model.getCountyCode()+ "'");
		}
		if(!"-1".equals(model.getCityCode())){
			condition.append(" and B.CITY_CODE = '"+model.getCityCode()+ "'");
		}
		if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append(" \n" +
				"           GROUP BY B.CITY_CODE,\n" +
				"                     B.CITY_DESC,B.COUNTY_CODE,B.COUNTY_DESC");
		int total = CommonEngine.getCountFromSql("select count(1) from ("+condition.toString() +")",null);
		QryHzCountBean[] beans = null;
		if (total > 0) {
			beans = CommonEngine.getBeansFromSql(condition.toString(),null,resultDTO.getStart(), resultDTO.getEnd(),QryHzCountBean.class) ;
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		resultDTO.setRows(BeanUtils.beanToList(beans,QryHzMultipleListModel.class,dicts));
		resultDTO.setRecords(total);
		return resultDTO;
	}
	
	/**
	 * 获取会诊科室统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryhZLocPageList(QryHzMultipleListModel model, ResultDTO resultDTO) throws Exception
	{
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  B.CITY_CODE, --城市代码 \n" +
				"                  B.CITY_DESC, --城市名称\n" +
				"                  B.COUNTY_CODE, --\n" +
				"                  B.COUNTY_DESC, --\n" +
				"				   A.Org_Id,\n" +
		        "				   B.org_name ,\n" +
		        "				   D.LOC_CODE,\n" +
		        "				   D.Loc_Desc,\n" +
				"                  count(*) hz_count, \n" +
				"                  count(c.report_id) hz_resultcount \n" +
				"  from ais_studyinfo a, AISC_AreaInstitution b, ais_studyreport c,aisc_loc D "+
				" where a.org_id = b.org_id and d.loc_id=a.loc_id and a.studyinfo_id = c.studyinfo_id(+)  and study_type = '1' ");
		if(!"-1".equals(model.getCountyCode())){
			 condition.append(" and B.COUNTY_CODE = '"+model.getCountyCode()+ "'");
		}
		if(!"-1".equals(model.getCityCode())){
			condition.append(" and B.CITY_CODE = '"+model.getCityCode()+ "'");
		}
		if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append(" \n" +
				"           GROUP BY B.CITY_CODE,\n" +
				"                     B.CITY_DESC,B.COUNTY_CODE,B.COUNTY_DESC,A.Org_Id,B.org_name,D.LOC_CODE,D.Loc_Desc");
		int total = CommonEngine.getCountFromSql("select count(1) from ("+condition.toString() +")",null);
		QryHzCountBean[] beans = null;
		if (total > 0) {
			beans = CommonEngine.getBeansFromSql(condition.toString(),null,resultDTO.getStart(), resultDTO.getEnd(),QryHzCountBean.class) ;
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		resultDTO.setRows(BeanUtils.beanToList(beans,QryHzMultipleListModel.class,dicts));
		resultDTO.setRecords(total);
		return resultDTO;
	}
	
	/**
	 * 获取会诊病人统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryhZPatientPageList(QryHzMultipleListModel model, ResultDTO resultDTO) throws Exception
	{
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"       B.CITY_CODE, --城市代码 \n" +
				"       B.CITY_DESC, --城市名称\n" +
				"       B.COUNTY_CODE, --\n" +
				"       B.COUNTY_DESC, --\n" +
				"       A.ORG_ID, --机构ID \n" +
				"       B.Org_Name, --机构名称\n" +
				"       D.LOC_CODE, --科室代码\n" +
				"       D.LOC_DESC, --科室名称\n" +
				"       E.patient_id, \n" +
				"       E.patient_name,\n" +
				"       A.patient_age,\n" +
				"       a.study_appdate,\n" +
				"       C.Report_Datetime,\n" +
				"       decode(E.patient_sex,'1','男','2','女','未知') patient_sex,\n" +
				"       a.study_itemdesc check_item,\n" +
				"       (select careprov_name from aisc_careprov where careprov_id=C.report_doctorid) report_doc,   \n" +
				"       (select careprov_name from aisc_careprov where careprov_id=C.report_finaldoctorid) check_doc,\n" +
				"       (select org_name from AISC_AreaInstitution s where s.org_id=a.study_consultorg) study_consultorgName,\n" +
				"       (select loc_desc from aisc_loc where loc_id = a.study_consultloc) study_consultlocName, \n" +
				"       (select operator_name from sys_operator where operator_id = a.study_operationid) CAREPROV_NAME\n" +
				"  from ais_studyinfo a, AISC_AreaInstitution b, ais_studyreport c,aisc_loc D,AIS_PATIENTINFO E "+
				" where a.org_id = b.org_id and d.loc_id=a.loc_id and e.patient_globalid=a.patient_globalid and a.studyinfo_id = c.studyinfo_id(+)and a.studyinfo_id = c.studyinfo_id(+)  and study_type = '1' ");
		if(!"-1".equals(model.getCountyCode())){
			 condition.append(" and B.COUNTY_CODE = '"+model.getCountyCode()+ "'");
		}
		if(!"-1".equals(model.getCityCode())){
			condition.append(" and B.CITY_CODE = '"+model.getCityCode()+ "'");
		}
		if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		int total = CommonEngine.getCountFromSql("select count(1) from ("+condition.toString() +")",null);
		QryHzCountBean[] beans = null;
		if (total > 0) {
			beans = CommonEngine.getBeansFromSql(condition.toString(),null,resultDTO.getStart(), resultDTO.getEnd(),QryHzCountBean.class) ;
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		resultDTO.setRows(BeanUtils.beanToList(beans,QryHzMultipleListModel.class,dicts));
		resultDTO.setRecords(total);
		return resultDTO;
	}
	
	
	public List<Map<String, Object>> getCityStudyinfoExcel(AiscPatientCheckCountyrpModel model) throws Exception{
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  C.CITY_CODE, --城市代码 \n" +
				"                  C.CITY_DESC, --城市名称\n" +
				"                  COUNT(1) AS REGIST_NUM, --登记数量\n" +
				"                  SUM(A.STUDY_HAVEIMAGE) AS COLLECT_NUM, --采集数量\n" +
				"                  SUM(decode(A.Study_Imagecount,null,0,A.Study_Imagecount)) AS IMAGE_NUM, --图像数量\n" +
				"                  SUM(A.STUDY_HAVEREPORT) AS REPORT_NUM, --报告数量\n" +
				"                  SUM(DECODE(A.STUDYSTATUS_CODE, 'VERIFY', 1, 0)) AS CHECKED_NUM --审核数量\n" +
				"  FROM AIS_STUDYINFO A, AIS_PATIENTINFO B,AISC_AREAINSTITUTION C "+
				" WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID and C.org_id=A.org_id ");
		if(!"-1".equals(model.getExcelCityCode())){
			condition.append(" and C.CITY_CODE = '"+model.getExcelCityCode()+ "'");
		}
		if(isNotBlank(model.getExcelOrgId())&&!"-1".equals(model.getExcelOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getExcelOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append(" \n" +
				"           GROUP BY C.CITY_CODE,\n" +
				"                     C.CITY_DESC");
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String CITY_DESC = result.getString("CITY_DESC");
	            String REGIST_NUM = result.getString("REGIST_NUM");
	            String COLLECT_NUM = result.getString("COLLECT_NUM");
	            String IMAGE_NUM = result.getString("IMAGE_NUM");
	            String REPORT_NUM = result.getString("REPORT_NUM");
	            String CHECKED_NUM = result.getString("CHECKED_NUM");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("CITY_DESC", CITY_DESC);
	            map.put("REGIST_NUM", REGIST_NUM);
	            map.put("COLLECT_NUM", COLLECT_NUM);
	            map.put("IMAGE_NUM", IMAGE_NUM);
	            map.put("REPORT_NUM", REPORT_NUM);
	            map.put("CHECKED_NUM", CHECKED_NUM);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
	}
	
	public List<Map<String, Object>> getCountyStudyinfoExcel(AiscPatientCheckCountyrpModel model) throws Exception{
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  C.CITY_CODE, --城市代码 \n" +
				"                  C.CITY_DESC, --城市名称\n" +
				"                  C.COUNTY_CODE, --城市名称\n" +
				"                  C.COUNTY_DESC, --城市名称\n" +
				"                  COUNT(1) AS REGIST_NUM, --登记数量\n" +
				"                  SUM(A.STUDY_HAVEIMAGE) AS COLLECT_NUM, --采集数量\n" +
				"                  SUM(decode(A.Study_Imagecount,null,0,A.Study_Imagecount)) AS IMAGE_NUM, --图像数量\n" +
				"                  SUM(A.STUDY_HAVEREPORT) AS REPORT_NUM, --报告数量\n" +
				"                  SUM(DECODE(A.STUDYSTATUS_CODE, 'VERIFY', 1, 0)) AS CHECKED_NUM --审核数量\n" +
				"  FROM AIS_STUDYINFO A, AIS_PATIENTINFO B,AISC_AREAINSTITUTION C "+
				" WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID and C.org_id=A.org_id ");
		if(isNotBlank(model.getExcelCounty())&&!"-1".equals(model.getExcelCounty())){
			 condition.append(" and C.COUNTY_CODE = '"+model.getExcelCounty()+ "'");
		}
		if(isNotBlank(model.getExcelCityCode())&&!"-1".equals(model.getExcelCityCode())){
			condition.append(" and C.CITY_CODE = '"+model.getExcelCityCode()+ "'");
		}
		if(isNotBlank(model.getExcelOrgId())&&!"-1".equals(model.getExcelOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getExcelOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		 condition.append("  GROUP BY  C.CITY_CODE, \n" +
				 "       C.CITY_DESC, \n" +
				 "       C.COUNTY_CODE, \n" +
				 "       C.COUNTY_DESC ");
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String COUNTY_DESC = result.getString("COUNTY_DESC");
	            String REGIST_NUM = result.getString("REGIST_NUM");
	            String COLLECT_NUM = result.getString("COLLECT_NUM");
	            String IMAGE_NUM = result.getString("IMAGE_NUM");
	            String REPORT_NUM = result.getString("REPORT_NUM");
	            String CHECKED_NUM = result.getString("CHECKED_NUM");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("COUNTY_DESC", COUNTY_DESC);
	            map.put("REGIST_NUM", REGIST_NUM);
	            map.put("COLLECT_NUM", COLLECT_NUM);
	            map.put("IMAGE_NUM", IMAGE_NUM);
	            map.put("REPORT_NUM", REPORT_NUM);
	            map.put("CHECKED_NUM", CHECKED_NUM);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
	}
	
	public List<Map<String, Object>> getLocStudyinfoExcel(AiscPatientCheckCountyrpModel model) throws Exception{
		StringBuffer condition = new StringBuffer("SELECT C.CITY_CODE, --城市代码 \n" +
				"       C.CITY_DESC, --城市名称\n" +
				"       C.COUNTY_CODE, --区县代码 \n" +
				"       C.COUNTY_DESC, --区县名称\n" +
				"       A.ORG_ID, --机构ID \n" +
				"       C.Org_Name ORG_DESC, --机构名称\n" +
				"       D.LOC_CODE, --科室代码\n" +
				"       D.LOC_DESC, --科室名称\n" +
				"                  COUNT(1) AS REGIST_NUM, --登记数量\n" +
				"                  SUM(A.STUDY_HAVEIMAGE) AS COLLECT_NUM, --采集数量\n" +
				"                  SUM(decode(A.Study_Imagecount,null,0,A.Study_Imagecount)) AS IMAGE_NUM, --图像数量\n" +
				"                  SUM(A.STUDY_HAVEREPORT) AS REPORT_NUM, --报告数量\n" +
				"                  SUM(DECODE(A.STUDYSTATUS_CODE, 'VERIFY', 1, 0)) AS CHECKED_NUM --审核数量\n" +
				"  FROM AIS_STUDYINFO A, AIS_PATIENTINFO B,AISC_AREAINSTITUTION C,aisc_loc D "+
				" WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID and C.org_id=A.org_id and d.loc_id=a.loc_id");
	
		if(isNotBlank(model.getExcelCounty())&&!"-1".equals(model.getExcelCounty())){
			 condition.append(" and C.COUNTY_CODE = '"+model.getExcelCounty()+ "'");
		}
		if(isNotBlank(model.getExcelCityCode())&&!"-1".equals(model.getExcelCityCode())){
			condition.append(" and C.CITY_CODE = '"+model.getExcelCityCode()+ "'");
		}
		if(isNotBlank(model.getExcelOrgId())&&!"-1".equals(model.getExcelOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getExcelOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append("  GROUP BY  C.CITY_CODE, \n" +
				"       C.CITY_DESC, \n" +
				"       C.COUNTY_CODE, \n" +
				"       C.COUNTY_DESC,\n" +
				"       A.ORG_ID, \n" +
				"       C.Org_Name, \n" +
				"       D.LOC_CODE, \n" +
				"       D.LOC_DESC ");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String COUNTY_DESC = result.getString("COUNTY_DESC");
	            String REGIST_NUM = result.getString("REGIST_NUM");
	            String COLLECT_NUM = result.getString("COLLECT_NUM");
	            String IMAGE_NUM = result.getString("IMAGE_NUM");
	            String REPORT_NUM = result.getString("REPORT_NUM");
	            String CHECKED_NUM = result.getString("CHECKED_NUM");
	            String ORG_ID = result.getString("ORG_ID");
	            String ORG_DESC = result.getString("ORG_DESC");
	            String LOC_DESC = result.getString("LOC_DESC");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("COUNTY_DESC", COUNTY_DESC);
	            map.put("REGIST_NUM", REGIST_NUM);
	            map.put("COLLECT_NUM", COLLECT_NUM);
	            map.put("IMAGE_NUM", IMAGE_NUM);
	            map.put("REPORT_NUM", REPORT_NUM);
	            map.put("CHECKED_NUM", CHECKED_NUM);
	            map.put("ORG_ID", ORG_ID);
	            map.put("ORG_DESC", ORG_DESC);
	            map.put("LOC_DESC", LOC_DESC);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
		
	}
	
	public List<Map<String, Object>> getPatientStudyinfoExcel(AiscPatientCheckCountyrpModel model) throws Exception{
		StringBuffer condition = new StringBuffer("SELECT C.CITY_CODE, --城市代码 \n" +
				"       C.CITY_DESC, --城市名称\n" +
				"       C.COUNTY_CODE, --区县代码 \n" +
				"       C.COUNTY_DESC, --区县名称\n" +
				"       A.ORG_ID, --机构ID \n" +
				"       C.Org_Name ORG_DESC, --机构名称\n" +
				"       D.LOC_CODE, --科室代码\n" +
				"       D.LOC_DESC, --科室名称\n" +
				"       b.patient_id,\n" +
				"       b.patient_name,\n" +
				"       A.patient_age,\n" +
				"       decode(b.patient_sex,'1','男','2','女','未知') patient_sex,\n" +
				"       a.study_itemdesc check_item,\n" +
				"       a.study_imagecount,\n" +
				"       (select careprov_name from aisc_careprov where careprov_id=f.report_doctorid) report_doc,\n" +   
				"       (select careprov_name from aisc_careprov where careprov_id=f.report_finaldoctorid) check_doc\n" +   
				"  FROM AIS_STUDYINFO A, AIS_PATIENTINFO B,AISC_AREAINSTITUTION C,aisc_loc D ,ais_studyreport f "+
				" WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID and C.org_id=A.org_id and d.loc_id=a.loc_id and f.studyinfo_id=a.studyinfo_id ");
		
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		if(isNotBlank(model.getExcelCityCode())&&!"-1".equals(model.getExcelCityCode())){
			condition.append(" and C.CITY_CODE = '"+model.getExcelCityCode()+ "'");
		}
		if(isNotBlank(model.getExcelCounty())&&!"-1".equals(model.getExcelCounty())){
			condition.append(" and C.COUNTY_CODE = '"+model.getExcelCounty()+"'");
		}
		if(isNotBlank(model.getExcelOrgId())&&!"-1".equals(model.getExcelOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getExcelOrgId()+ "'");
		}
		if(isNotBlank(model.getExcelLocCode())){
			condition.append(" and D.LOC_CODE = '"+model.getExcelLocCode()+"'");
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String COUNTY_DESC = result.getString("COUNTY_DESC");
	            String ORG_DESC = result.getString("ORG_DESC");
	            String LOC_DESC = result.getString("LOC_DESC");
	            String PATIENT_ID = result.getString("PATIENT_ID");
	            String PATIENT_NAME = result.getString("PATIENT_NAME");
	            String PATIENT_AGE = result.getString("PATIENT_AGE");
	            String PATIENT_SEX = result.getString("PATIENT_SEX");
	            String CHECK_ITEM = result.getString("CHECK_ITEM");
	            String STUDY_IMAGECOUNT = result.getString("STUDY_IMAGECOUNT");
	            String REPORT_DOC = result.getString("REPORT_DOC");
	            String CHECK_DOC = result.getString("CHECK_DOC");
	            
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("COUNTY_DESC", COUNTY_DESC);
	            map.put("ORG_DESC", ORG_DESC);
	            map.put("LOC_DESC", LOC_DESC);
	            map.put("PATIENT_ID", PATIENT_ID);
	            map.put("PATIENT_NAME", PATIENT_NAME);
	            map.put("PATIENT_AGE", PATIENT_AGE);
	            map.put("PATIENT_SEX", PATIENT_SEX);
	            map.put("CHECK_ITEM", CHECK_ITEM);
	            map.put("STUDY_IMAGECOUNT", STUDY_IMAGECOUNT);
	            map.put("REPORT_DOC", REPORT_DOC);
	            map.put("CHECK_DOC", CHECK_DOC);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
	}
	
	public List<Map<String, Object>> getHzCityStudyinfoExcel(QryHzMultipleListModel model) throws Exception{
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  B.CITY_CODE, --城市代码 \n" +
				"                  B.CITY_DESC, --城市名称\n" +
				"                  count(*) hz_count, \n" +
				"                  count(c.report_id) hz_resultcount\n" +
				"  from ais_studyinfo a, AISC_AreaInstitution b, ais_studyreport c "+
				" where a.org_id = b.org_id and a.studyinfo_id = c.studyinfo_id(+)  and study_type = '1' ");
		if(isNotBlank(model.getExcelCityCode())&&!"-1".equals(model.getExcelCityCode())){
			condition.append(" and B.CITY_CODE = '"+model.getExcelCityCode()+ "'");
		}
		if(isNotBlank(model.getExcelOrgId())&&!"-1".equals(model.getExcelOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getExcelOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append(" \n" +
				"           GROUP BY B.CITY_CODE,\n" +
				"                     B.CITY_DESC");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String CITY_DESC = result.getString("CITY_DESC");
	            String HZ_RESULTCOUNT = result.getString("HZ_RESULTCOUNT");
	            String HZ_COUNT = result.getString("HZ_COUNT");
	           
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("CITY_DESC", CITY_DESC);
	            map.put("HZ_RESULTCOUNT", HZ_RESULTCOUNT);
	            map.put("HZ_COUNT", HZ_COUNT);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
	}

	@Override
	public List<Map<String, Object>> getHzCountyStudyinfoExcel(QryHzMultipleListModel model) throws Exception {
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  B.CITY_CODE, --城市代码 \n" +
				"                  B.CITY_DESC, --城市名称\n" +
				"                  B.COUNTY_CODE, --\n" +
				"                  B.COUNTY_DESC, --\n" +
				"                  count(*) hz_count, \n" +
				"                  count(c.report_id) hz_resultcount\n" +
				"  from ais_studyinfo a, AISC_AreaInstitution b, ais_studyreport c "+
				" where a.org_id = b.org_id and a.studyinfo_id = c.studyinfo_id(+)  and study_type = '1' ");
		if(isNotBlank(model.getExcelCounty())&&!"-1".equals(model.getExcelCounty())){
			 condition.append(" and B.COUNTY_CODE = '"+model.getExcelCounty()+ "'");
		}
		if(isNotBlank(model.getExcelCityCode())&&!"-1".equals(model.getExcelCityCode())){
			condition.append(" and B.CITY_CODE = '"+model.getExcelCityCode()+ "'");
		}
		if(isNotBlank(model.getExcelOrgId())&&!"-1".equals(model.getExcelOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getExcelOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append(" \n" +
				"           GROUP BY B.CITY_CODE,\n" +
				"                     B.CITY_DESC,B.COUNTY_CODE,B.COUNTY_DESC");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String COUNTY_DESC = result.getString("COUNTY_DESC");
	            String HZ_RESULTCOUNT = result.getString("HZ_RESULTCOUNT");
	            String HZ_COUNT = result.getString("HZ_COUNT");
	           
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("COUNTY_DESC", COUNTY_DESC);
	            map.put("HZ_RESULTCOUNT", HZ_RESULTCOUNT);
	            map.put("HZ_COUNT", HZ_COUNT);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
	}

	@Override
	public List<Map<String, Object>> getHzLocStudyinfoExcel(
			QryHzMultipleListModel model) throws Exception {
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"                  B.CITY_CODE, --城市代码 \n" +
				"                  B.CITY_DESC, --城市名称\n" +
				"                  B.COUNTY_CODE, --\n" +
				"                  B.COUNTY_DESC, --\n" +
				"				   A.Org_Id,\n" +
		        "				   B.org_name ,\n" +
		        "				   D.LOC_CODE,\n" +
		        "				   D.Loc_Desc,\n" +
				"                  count(*) hz_count, \n" +
				"                  count(c.report_id) hz_resultcount \n" +
				"  from ais_studyinfo a, AISC_AreaInstitution b, ais_studyreport c,aisc_loc D "+
				" where a.org_id = b.org_id and d.loc_id=a.loc_id and a.studyinfo_id = c.studyinfo_id(+)  and study_type = '1' ");
		if(isNotBlank(model.getExcelCounty())&&!"-1".equals(model.getExcelCounty())){
			 condition.append(" and B.COUNTY_CODE = '"+model.getExcelCounty()+ "'");
		}
		if(isNotBlank(model.getExcelCityCode())&&!"-1".equals(model.getExcelCityCode())){
			condition.append(" and B.CITY_CODE = '"+model.getExcelCityCode()+ "'");
		}
		if(isNotBlank(model.getExcelOrgId())&&!"-1".equals(model.getExcelOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getExcelOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		condition.append(" \n" +
				"           GROUP BY B.CITY_CODE,\n" +
				"                     B.CITY_DESC,B.COUNTY_CODE,B.COUNTY_DESC,A.Org_Id,B.org_name,D.LOC_CODE,D.Loc_Desc");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String COUNTY_DESC = result.getString("COUNTY_DESC");
	            String ORG_ID = result.getString("ORG_ID");
	            String ORG_NAME = result.getString("ORG_NAME");
	            String LOC_DESC = result.getString("LOC_DESC");
	            String HZ_RESULTCOUNT = result.getString("HZ_RESULTCOUNT");
	            String HZ_COUNT = result.getString("HZ_COUNT");
	           
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("COUNTY_DESC", COUNTY_DESC);
	            map.put("ORG_ID", ORG_ID);
	            map.put("ORG_NAME", ORG_NAME);
	            map.put("LOC_DESC", LOC_DESC);
	            map.put("HZ_RESULTCOUNT", HZ_RESULTCOUNT);
	            map.put("HZ_COUNT", HZ_COUNT);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
	}

	@Override
	public List<Map<String, Object>> getHzPatientStudyinfoExcel(
			QryHzMultipleListModel model) throws Exception {
		StringBuffer condition = new StringBuffer(" SELECT     \n" +
				"       B.CITY_CODE, --城市代码 \n" +
				"       B.CITY_DESC, --城市名称\n" +
				"       B.COUNTY_CODE, --\n" +
				"       B.COUNTY_DESC, --\n" +
				"       A.ORG_ID, --机构ID \n" +
				"       B.Org_Name, --机构名称\n" +
				"       D.LOC_CODE, --科室代码\n" +
				"       D.LOC_DESC, --科室名称\n" +
				"       E.patient_id, \n" +
				"       E.patient_name,\n" +
				"       A.patient_age,\n" +
				"       to_char(a.study_appdate,'yyyy-mm-dd hh24:mi:ss') study_appdate,\n" +
				"       to_char(C.Report_Datetime,'yyyy-mm-dd hh24:mi:ss') Report_Datetime,\n" +
				"       decode(E.patient_sex,'1','男','2','女','未知') patient_sex,\n" +
				"       a.study_itemdesc check_item,\n" +
				"       (select careprov_name from aisc_careprov where careprov_id=C.report_doctorid) report_doc,   \n" +
				"       (select careprov_name from aisc_careprov where careprov_id=C.report_finaldoctorid) check_doc,\n" +
				"       (select org_name from AISC_AreaInstitution s where s.org_id=a.study_consultorg) study_consultorgName,\n" +
				"       (select loc_desc from aisc_loc where loc_id = a.study_consultloc) study_consultlocName, \n" +
				"       (select operator_name from sys_operator where operator_id = a.study_operationid) CAREPROV_NAME\n" +
				"  from ais_studyinfo a, AISC_AreaInstitution b, ais_studyreport c,aisc_loc D,AIS_PATIENTINFO E "+
				" where a.org_id = b.org_id and d.loc_id=a.loc_id and e.patient_globalid=a.patient_globalid and a.studyinfo_id = c.studyinfo_id(+)and a.studyinfo_id = c.studyinfo_id(+)  and study_type = '1' ");
		if(isNotBlank(model.getExcelCounty())&&!"-1".equals(model.getExcelCounty())){
			 condition.append(" and B.COUNTY_CODE = '"+model.getExcelCounty()+ "'");
		}
		if(isNotBlank(model.getExcelCityCode())&&!"-1".equals(model.getExcelCityCode())){
			condition.append(" and B.CITY_CODE = '"+model.getExcelCityCode()+ "'");
		}
		if(isNotBlank(model.getExcelOrgId())&&!"-1".equals(model.getExcelOrgId())){
			condition.append(" and A.ORG_ID = '"+model.getExcelOrgId()+ "'");
		}
		//年
		if(model.getTime_type().equals("5")){
			if(isNotBlank(model.getYearDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy'),'yyyy') = to_date('"+model.getYearDate()+ "','yyyy')");
			}
		}
		//季度
		if(model.getTime_type().equals("4")){
			if(isNotBlank(model.getYearDateOfJD())){
				if("1".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-01','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-03','yyyy-mm')");
				}else if("2".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-04','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-05','yyyy-mm')");
				}else if("3".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-07','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-09','yyyy-mm')");
				}else if("4".equals(model.getJd())){
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') >= to_date('"+model.getYearDateOfJD()+"-10','yyyy-mm')");
					condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') <= to_date('"+model.getYearDateOfJD()+ "-12','yyyy-mm')");
				}
			}
		}
		//月
		if(model.getTime_type().equals("3")){
			if(isNotBlank(model.getMonthDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm'),'yyyy-mm') = to_date('"+model.getMonthDate()+ "','yyyy-mm')");
			}
		}
		//日区间
		if(model.getTime_type().equals("2")){
			if(isNotBlank(model.getStartDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date('"+model.getStartDate()+ "','yyyy-mm-dd')");
			}
			if(isNotBlank(model.getEndDate())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date('"+model.getEndDate()+"','yyyy-mm-dd')");
			}
		}
		//日
		if(model.getTime_type().equals("1")){
			if(isNotBlank(model.getDateOfDay())){
				condition.append(" and to_date(to_char(A.study_appdate,'yyyy-mm-dd'),'yyyy-mm-dd') = to_date('"+model.getDateOfDay()+ "','yyyy-mm-dd')");
			}
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String COUNTY_DESC = result.getString("COUNTY_DESC");
	            String ORG_NAME = result.getString("ORG_NAME");
	            String LOC_DESC = result.getString("LOC_DESC");
	            String PATIENT_ID = result.getString("PATIENT_ID");
	            String PATIENT_NAME = result.getString("PATIENT_NAME");
	            String PATIENT_AGE = result.getString("PATIENT_AGE");
	            String PATIENT_SEX = result.getString("PATIENT_SEX");
	            String CHECK_ITEM = result.getString("CHECK_ITEM");
	            String REPORT_DOC = result.getString("REPORT_DOC");
	            String CHECK_DOC = result.getString("CHECK_DOC");
	            String STUDY_APPDATE = result.getString("STUDY_APPDATE");
	            String REPORT_DATETIME = result.getString("REPORT_DATETIME");
	            String STUDY_CONSULTORGNAME = result.getString("STUDY_CONSULTORGNAME");
	            String STUDY_CONSULTLOCNAME = result.getString("STUDY_CONSULTLOCNAME");
	            String CAREPROV_NAME = result.getString("CAREPROV_NAME");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("COUNTY_DESC", COUNTY_DESC);
	            map.put("ORG_NAME", ORG_NAME);
	            map.put("LOC_DESC", LOC_DESC);
	            map.put("PATIENT_ID", PATIENT_ID);
	            map.put("PATIENT_NAME", PATIENT_NAME);
	            map.put("PATIENT_AGE", PATIENT_AGE);
	            map.put("PATIENT_SEX", PATIENT_SEX);
	            map.put("CHECK_ITEM", CHECK_ITEM);
	            map.put("REPORT_DOC", REPORT_DOC);
	            map.put("CHECK_DOC", CHECK_DOC);
	            map.put("STUDY_APPDATE", STUDY_APPDATE);
	            map.put("REPORT_DATETIME", REPORT_DATETIME);
	            map.put("STUDY_CONSULTORGNAME", STUDY_CONSULTORGNAME);
	            map.put("STUDY_CONSULTLOCNAME", STUDY_CONSULTLOCNAME);
	            map.put("CAREPROV_NAME", CAREPROV_NAME);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
	}
	
	public List<Map<String, Object>> getExportHzInit(QryHzRecordListModel model) throws Exception {
    	StringBuffer condition = new StringBuffer(" select b.patient_name,\n"+
							"        decode(b.patient_sex,1,'男',2,'女','未知') patient_sex,\n"+
							"        a.patient_age,\n"+
							"        a.org_id,\n"+
							"        a.study_consultorg,\n"+
							"        a.loc_id,\n"+
							"        a.study_consultloc,\n"+
							"        (select org_name from sys_org s where a.org_id = s.org_id) org_name,\n"+
							"        (select loc_desc from aisc_loc where loc_id = a.loc_id) loc_desc,\n"+
							"        (select operator_name\n"+
							"           from sys_operator\n"+
							"          where operator_id = a.study_operationid) operator_name,\n"+
							"        to_char(c.report_datetime,'yyyy-mm-dd hh24:mi:ss') report_datetime,\n"+
							"        (select org_name from sys_org s where a.study_consultorg = s.org_id) study_consultorgName,\n"+
							"        (select loc_desc from aisc_loc where loc_id = a.study_consultloc) study_consultlocName,\n"+
							"        (select operator_name\n"+
							"           from sys_operator\n"+
							"          where operator_id = c.report_doctorid) CAREPROV_NAME,\n"+
							"        to_char(a.study_datetime,'yyyy-mm-dd hh24:mi:ss') study_datetime\n"+
							"   from ais_studyinfo a, ais_patientinfo b, ais_studyreport c\n"+
							"  where a.patient_globalid = b.patient_globalid\n"+
							"    and a.studyinfo_id = c.studyinfo_id\n"+
							"    and study_type = '1' ");
    	if (!"-1".equals(model.getOrgId())  && isNotBlank(model.getOrgId())) {
            condition.append(" and org_id = '" + model.getOrgId() +"'");
        }
        if (model.getLocId()!=-1&& isNotBlank(String.valueOf(model.getLocId()))) {
            condition.append(" and loc_id = '" + model.getLocId() +"'");
        }
        if (!"-1".equals(model.getStudyConsultorg())  && isNotBlank(model.getStudyConsultorg())) {
            condition.append(" and study_consultorg = '" + model.getStudyConsultorg() +"'");
        }
        if (model.getStudyConsultloc()!=-1 && isNotBlank(String.valueOf(model.getStudyConsultloc()))) {
            condition.append(" and study_consultloc = '" + model.getStudyConsultloc() +"'");
        }
        if(isNotBlank(model.getStudyStarttime())){ 
			condition.append(" and study_datetime >= to_date('"+model.getStudyStarttime()+" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
        if(isNotBlank(model.getStudyEndtime())){
			condition.append(" and study_datetime <= to_date('"+model.getStudyEndtime()+" 00:00:00' ,'yyyy-mm-dd hh24:mi:ss')");
		} 
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String PATIENT_NAME = result.getString("PATIENT_NAME");
	            String PATIENT_SEX = result.getString("PATIENT_SEX");
	            String PATIENT_AGE = result.getString("PATIENT_AGE");
	            String ORG_NAME = result.getString("ORG_NAME");
	            String LOC_DESC = result.getString("LOC_DESC");
	            String OPERATOR_NAME = result.getString("OPERATOR_NAME");
	            String STUDY_CONSULTORGNAME = result.getString("STUDY_CONSULTORGNAME");
	            String STUDY_DATETIME = result.getString("STUDY_DATETIME");
	            String STUDY_CONSULTLOCNAME = result.getString("STUDY_CONSULTLOCNAME");
	            String REPORT_DATETIME = result.getString("REPORT_DATETIME");
	            String CAREPROV_NAME = result.getString("CAREPROV_NAME");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("PATIENT_NAME", PATIENT_NAME);
	            map.put("PATIENT_SEX", PATIENT_SEX);
	            map.put("PATIENT_AGE", PATIENT_AGE);
	            map.put("ORG_NAME", ORG_NAME);
	            map.put("LOC_DESC", LOC_DESC);
	            map.put("STUDY_DATETIME", STUDY_DATETIME);
	            map.put("STUDY_CONSULTLOCNAME", STUDY_CONSULTLOCNAME);
	            map.put("STUDY_CONSULTORGNAME", STUDY_CONSULTORGNAME);
	            map.put("REPORT_DATETIME", REPORT_DATETIME);
	            map.put("CAREPROV_NAME", CAREPROV_NAME);
	            map.put("OPERATOR_NAME", OPERATOR_NAME);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
    }
	
	public List<Map<String, Object>> getExportHzMultiple(QryHzMultipleListModel model) throws Exception {
    	StringBuffer condition = new StringBuffer("select (select org_name from sys_org s where a.org_id = s.org_id) org_name,\n"+
											"       b.city_code,\n"+
											"       b.county_code,\n"+
											"       b.county_desc,\n"+
											"       a.org_id,\n"+
											"       count(*) hzsqsl,\n"+
											"       to_char(a.study_datetime, 'yyyy-mm-dd') study_datetime,\n"+
											"       count(c.report_id) hzsl\n"+
											"  from ais_studyinfo a, AISC_AreaInstitution b, ais_studyreport c\n"+
											" where a.org_id = b.org_id\n"+
											"   and a.studyinfo_id = c.studyinfo_id(+)\n"+
											"   and study_type = '1' ");
    	if (!"-1".equals(model.getOrgId())  && isNotBlank(model.getOrgId())) {
            condition.append(" and org_id = '" + model.getOrgId() +"'");
        }
        if (!"-1".equals(model.getCityCode())&& isNotBlank(model.getCityCode())) {
			condition.append(" and city_code = '"+model.getCityCode()+"'");
		}
		if (!"-1".equals(model.getCountyCode())&& isNotBlank(model.getCountyCode())) {
			condition.append(" and county_code ='"+model.getCountyCode()+"'");
		}
        if(isNotBlank(model.getStudyStarttime())){ 
			condition.append(" and to_date(to_char(study_datetime,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss') >= to_date('"+model.getStudyStarttime()+" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
        if(isNotBlank(model.getStudyEndtime())){
			condition.append(" and to_date(to_char(study_datetime,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss') <= to_date('"+model.getStudyEndtime()+" 00:00:00' ,'yyyy-mm-dd hh24:mi:ss')");
		} 
        condition.append(" group by b.city_code,b.county_code,b.county_desc,a.org_id,to_char(a.study_datetime, 'yyyy-mm-dd') ");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	        	String COUNTY_DESC = result.getString("COUNTY_DESC");
	            String HZSQSL = result.getString("HZSQSL");
	            String HZSL = result.getString("HZSL");
	            String STUDY_DATETIME = result.getString("STUDY_DATETIME");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("COUNTY_DESC", COUNTY_DESC);
	            map.put("HZSQSL", HZSQSL);
	            map.put("HZSL", HZSL);
	            map.put("STUDY_DATETIME", STUDY_DATETIME);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
    }
	
	public List<Map<String, Object>> getMeidcalCountExcel(QryHzMultipleListModel model) throws Exception{
		StringBuffer condition = new StringBuffer("select d.city_code, d.county_code, d.county_desc, count(*) hzsl \n"+
												"  from ais_patientinfo      a, \n"+
												"       AIS_StudyInfo        b, \n"+
												"       AIS_StudyReport      c, \n"+
												"       AISC_AreaInstitution d \n"+
												" where a.patient_globalid = b.patient_globalid \n"+
												"   and d.org_id = b.org_id \n"+
												"   and b.studyinfo_id = c.studyinfo_id \n"+
												"   and studystatus_code not in ('Cancel') \n");
		if (isNotBlank(String.valueOf(model.getStudyStarttime()))) {
			condition.append(" and  b.study_starttime >= to_date('"+model.getStudyStarttime()+"','yyyy-mm-dd hh24:mi:ss') ");
        }
        if (isNotBlank(String.valueOf(model.getStudyEndtime()))) {
        	condition.append(" and  b.study_endtime<=to_date('"+model.getStudyEndtime()+"','yyyy-mm-dd hh24:mi:ss') ");
        }
        if (!"-1".equals(model.getCityCode())&& isNotBlank(model.getCityCode())) {
        	condition.append(" and d.city_code = '"+model.getCityCode()+"'");
		}
		if (!"-1".equals(model.getCountyCode())&& isNotBlank(model.getCountyCode())) {
			condition.append(" and d.county_code ='"+model.getCountyCode()+"'");
		}
		if(isNotBlank(model.getCategories())) {
			condition.append(" and (c.REPORT_EXAM like '%" + model.getCategories() + "%' or c.REPORT_RESULT like '%" + model.getCategories() + "%' )");
        }
		if(isNotBlank(model.getStartAge())){ 
			condition.append(" and substr(b.PATIENT_AGE,1,LENGTH(b.PATIENT_AGE) - 1) >= " + model.getStartAge());
		}
        if(isNotBlank(model.getEndAge())){ 
        	condition.append(" and substr(b.PATIENT_AGE,1,LENGTH(b.PATIENT_AGE) - 1) <= " + model.getEndAge());
		}
		condition.append(" \n" +
				"           group by d.city_code,d.county_code,d.county_desc ");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String COUNTY_DESC = result.getString("COUNTY_DESC");
	            String HZSL = result.getString("HZSL");
	           
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("COUNTY_DESC", COUNTY_DESC);
	            map.put("START_TIME", model.getStudyStarttime());
	            map.put("END_TIME", model.getStudyEndtime());
	            map.put("HZSL", HZSL);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
	}
	
	public List<Map<String, Object>> getMeidcalCountDetailExcel(QryHzMultipleListModel model) throws Exception{
		StringBuffer condition = new StringBuffer("select d.county_desc,\n"+
												"        d.county_code,\n"+
												"        b.study_datetime Sdatetime,\n"+
												"        b.org_id,\n"+
												"        (select org_name from sys_org s where b.org_id = s.org_id) org_name,\n"+
												"        b.study_starttime,\n"+
												"        b.study_endtime,\n"+
												"        a.patient_name,\n"+
												"        b.studyinfo_id,\n"+
												"        b.patient_age,\n"+
												"        decode(a.patient_sex,'1','男',2,'女','未知') patient_sex,\n"+
												"        b.study_accnumber,\n"+
												"        to_char(regexp_replace(c.report_exam,'</?[^>]*>|nbsp;|&','')) report_exam, \n"+
												"        to_char(regexp_replace(c.report_result,'</?[^>]*>|nbsp;|&','')) report_result\n"+
												"   from ais_patientinfo      a,\n"+
												"        AIS_StudyInfo        b,\n"+
												"        Ais_Studyreport      c,\n"+
												"        AISC_AreaInstitution d\n"+
												"  where a.patient_globalid = b.patient_globalid\n"+
												"    and d.org_id = b.org_id\n"+
												"    and b.studyinfo_id = c.studyinfo_id\n"+
												"    and studystatus_code not in ('Cancel')\n");
		if(!"-1".equals(model.getCityCode())){
			 condition.append(" and CITY_CODE = '"+model.getCityCode()+ "'");
		 }
		 if(!"-1".equals(model.getExcelCounty())){
			 condition.append(" and COUNTY_CODE = '"+model.getExcelCounty()+"'");
		 }
		 if(isNotBlank(model.getStudyStarttime())){
			 condition.append(" and study_starttime >= to_date('"+model.getStudyStarttime()+"','yyyy-mm-dd hh24:mi:ss') ");
		 }
		 if(isNotBlank(model.getStudyEndtime())){
			 condition.append(" and study_endtime <= to_date('"+model.getStudyEndtime()+"','yyyy-mm-dd hh24:mi:ss') ");
		 }
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet result = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String COUNTY_DESC = result.getString("COUNTY_DESC");
	            String ORG_NAME = result.getString("ORG_NAME");
	            String SDATETIME = result.getString("SDATETIME");
	            String PATIENT_NAME = result.getString("PATIENT_NAME");
	            String PATIENT_AGE = result.getString("PATIENT_AGE");
	            String PATIENT_SEX = result.getString("PATIENT_SEX");
	            String STUDYINFO_ID = result.getString("STUDYINFO_ID");
	            String STUDY_ACCNUMBER = result.getString("STUDY_ACCNUMBER");
	            String REPORT_EXAM = result.getString("REPORT_EXAM");
	            String REPORT_RESULT = result.getString("REPORT_RESULT");
	           
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("COUNTY_DESC", COUNTY_DESC);
	            map.put("ORG_NAME", ORG_NAME);
	            map.put("SDATETIME", SDATETIME);
	            map.put("PATIENT_NAME", PATIENT_NAME);
	            map.put("PATIENT_AGE", PATIENT_AGE);
	            map.put("PATIENT_SEX", PATIENT_SEX);
	            map.put("START_TIME", model.getStudyStarttime());
	            map.put("END_TIME", model.getStudyEndtime());
	            map.put("STUDYINFO_ID", STUDYINFO_ID);
	            map.put("STUDY_ACCNUMBER", STUDY_ACCNUMBER);
	            map.put("REPORT_EXAM", REPORT_EXAM);
	            map.put("REPORT_RESULT", REPORT_RESULT);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(result, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
	}
}
