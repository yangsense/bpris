package com.ai.aris.server.basecode.service.impl;

import com.ai.appframe2.common.ServiceManager;
import com.ai.aris.server.basecode.model.AiscReportFormat2Loc;
import com.ai.aris.server.basecode.service.interfaces.IAiscReportFormatSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;
import oracle.sql.CLOB;

import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isNotBlank;

public class AiscReportFormatSVImpl implements IAiscReportFormatSV {


	@Override
	public ResultDTO queryPageList(AiscReportFormat2Loc model, ResultDTO resultDTO) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rsCount = null;
		try {
			stmt = ServiceManager.getSession().getConnection().createStatement();
			String sql = "select * from (select B.*,rownum as row_index from ( select a.format_id,a.format2loc_id,a.org_id,a.loc_id," +
					"(select item_name from sys_dictitem where dict_name='AISC_MODALITY' and item_no=a.modality_id) MODALITY_TYPE," +
					"(select item_name from sys_dictitem where dict_name='AISC_MODELTYPE' and item_no=a.model_type) MODEL_NAME," +
					"b.format_name,b.report_format,c.loc_desc,d.org_name from AISC_ReportFormat2Loc a,AISC_ReportFormat b,aisc_loc c," +
					"sys_org d where a.format_id=b.format_id and a.loc_id=c.loc_id and d.org_id=a.org_id ";
			String sqlParam = "";
			if (model.getLocId() != -1 && isNotBlank(String.valueOf(model.getLocId()))) {
				sqlParam += " and c.Loc_id = '" + model.getLocId() + "' ";
			}
			if (model.getModalityId() != -1 && isNotBlank(String.valueOf(model.getModalityId()))) {
				sqlParam += " and a.modality_id = '" + model.getModalityId() + "' ";
			}
			if (model.getModelType() != -1 && isNotBlank(String.valueOf(model.getModelType()))) {
				sqlParam += " and a.model_type = '" + model.getModelType() + "' ";
			}
			if (!model.getOrgId().equals("-1") && isNotBlank(String.valueOf(model.getOrgId()))) {
				sqlParam += " and d.org_id = '" + model.getOrgId() + "' ";
			}
			int number = resultDTO.getLimit() * resultDTO.getPage();
			int rowindex = 1 + (number - resultDTO.getLimit());
			String sqlOrder = "ORDER BY a.modality_id desc ) B where rownum<=" + number + ") where  row_index >=" + rowindex + " and row_index <= " + number + " ";
			rs = stmt.executeQuery(sql + sqlParam + sqlOrder);
			List<AiscReportFormat2Loc> list = new ArrayList();
			AiscReportFormat2Loc report = null;
			while (rs.next()) {
				report = new AiscReportFormat2Loc();
				report.setFormat2locId(rs.getLong("FORMAT2LOC_ID"));
				report.setOrgId(rs.getString("ORG_ID") == null ? "" : rs.getString("ORG_ID"));
				report.setLocId(rs.getLong("LOC_ID"));
				report.setFormatName(rs.getString("FORMAT_NAME") == null ? "" : rs.getString("FORMAT_NAME"));
				report.setLocName(rs.getString("LOC_DESC") == null ? "" : rs.getString("LOC_DESC"));
				report.setModalityType(rs.getString("MODALITY_TYPE") == null ? "" : rs.getString("MODALITY_TYPE"));
				report.setOrgName(rs.getString("ORG_NAME") == null ? "" : rs.getString("ORG_NAME"));
				report.setModelName(rs.getString("MODEL_NAME") == null ? "" : rs.getString("MODEL_NAME"));
//				report.setModelType(rs.getLong("MODEL_TYPE"));
				Clob reportFormat = rs.getClob("REPORT_FORMAT");
				String format = "";
				if (reportFormat != null) {
					format = reportFormat.getSubString(1, (int) reportFormat.length());
				}
//				String str = format.replace("<","&lt;").replace(">","&gt;");
				report.setReportFormat(format);
				list.add(report);
			}
			String sqlCount = "select count(*) COUNT from AISC_ReportFormat2Loc a,AISC_ReportFormat b,aisc_loc c," +
					"sys_org d where a.format_id=b.format_id and a.loc_id=c.loc_id and d.org_id=a.org_id " + sqlParam;
			int total = 0;
			rsCount = stmt.executeQuery(sqlCount);
			if (rsCount.next()) {
				total = rsCount.getInt("COUNT");
			}

			AiscReportFormat2Loc[] formatlist = new AiscReportFormat2Loc[list.size()];
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					formatlist[i] = list.get(i);
				}
			}
			Map<String, DictTranslator> dicts = new HashMap<String, DictTranslator>();
			resultDTO.setRows(BeanUtils.beanToList(formatlist, AiscReportFormat2Loc.class, dicts));
			resultDTO.setRecords(total);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.release(rs, stmt, null);
			DBUtil.release(rsCount, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
		return resultDTO;
	}

	@Override
	public AiscReportFormat2Loc getAiscReport(long id) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		AiscReportFormat2Loc report = null;
		try {
			stmt = ServiceManager.getSession().getConnection().createStatement();
			String sql = " select * from AISC_ReportFormat2Loc a,AISC_ReportFormat b where a.format_id=b.format_id and format2loc_id=" + id + "";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				report = new AiscReportFormat2Loc();
				report.setFormat2locId(rs.getLong("FORMAT2LOC_ID"));
				report.setOrgId(rs.getString("ORG_ID") == null ? "" : rs.getString("ORG_ID"));
				report.setLocId(rs.getLong("LOC_ID"));
				report.setFormatId(rs.getLong("FORMAT_ID"));
				report.setModalityId(rs.getLong("MODALITY_ID"));
				report.setModelType(rs.getLong("MODEL_TYPE"));
				//report.setModelName(rs.getLong("MODEL_TYPE"));
				report.setFormatName(rs.getString("FORMAT_NAME") == null ? "" : rs.getString("FORMAT_NAME"));
				Clob reportFormat = rs.getClob("REPORT_FORMAT");
				String format = "";
				if (reportFormat != null) {
					format = reportFormat.getSubString(1, (int) reportFormat.length());
				}
//				String str = format.replace("<","&lt;").replace(">","&gt;").replace("\"", "&quot;");
				report.setReportFormat(format);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.release(rs, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
		return report;
	}

	@Override
	public boolean deleteAiscReport(long id) throws Exception {
		Statement stmt = null;
		try {
			stmt = ServiceManager.getSession().getConnection().createStatement();
			String sql2 = "delete from aisc_reportformat2loc where format2loc_id=" + id + "";
			stmt.executeUpdate(sql2);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			DBUtil.release(null, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
	}

	@Override
	public void saveAiscReport(AiscReportFormat2Loc bean) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement preStmt = null;
		PreparedStatement presta = null;
		if (bean.getFormat2locId() != null) {
			try {
				stmt = ServiceManager.getSession().getConnection().createStatement();
				String sqlUp = "update aisc_reportformat set report_format=' ' where format_id=" + bean.getFormatId() + "";
				stmt.executeUpdate(sqlUp);
				//clob字段更新
				preStmt = ServiceManager.getSession().getConnection().prepareStatement("select * from aisc_reportformat where format_id=" + bean.getFormatId() + " for update");
				rs = preStmt.executeQuery();
				if (rs.next()) {
					CLOB clob = (CLOB) rs.getClob("REPORT_FORMAT");
					Writer outStream = clob.getCharacterOutputStream();
					char[] c = bean.getReportFormat().toCharArray();
					outStream.write(c, 0, c.length);
					outStream.flush();
					outStream.close();
				}
				String sql = "update aisc_reportformat set format_name='" + bean.getFormatName() + "'" +
						" where format_id=" + bean.getFormatId() + "";
				preStmt.executeUpdate(sql);
				String sql2 = "update aisc_reportformat2loc set org_id='" + bean.getOrgId() + "',loc_id=" + bean.getLocId() + ",modality_id=" + bean.getModalityId() + ",model_type=" + bean.getModelType() + "" +
						" where format2loc_id=" + bean.getFormat2locId() + "";
				preStmt.executeUpdate(sql2);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.release(rs, stmt, null);
				preStmt.close();
				ServiceManager.getSession().getConnection().close();
			}
		} else {
			try {
				long formatid = ServiceUtil.getSequence("SEQFORMATID");
				stmt = ServiceManager.getSession().getConnection().createStatement();
				String sql = "insert into aisc_reportformat (format_id,format_name,report_format) values (" + formatid + ",'" + bean.getFormatName() + "',' ')";
				stmt.executeUpdate(sql);
				presta = ServiceManager.getSession().getConnection().prepareStatement("select * from aisc_reportformat where format_id=" + formatid + " for update");
				rs = presta.executeQuery();
				if (rs.next()) {
					CLOB clob = (CLOB) rs.getClob("REPORT_FORMAT");
					Writer outStream = clob.getCharacterOutputStream();
					char[] c = bean.getReportFormat().toCharArray();
					outStream.write(c, 0, c.length);
					outStream.flush();
					outStream.close();
				}
				long formatloc2id = ServiceUtil.getSequence("SEQFORMAT2LOCID");
				String sql2 = "insert into aisc_reportformat2loc (format2loc_id,org_id,loc_id,modality_id,format_id,model_type) values (" + formatloc2id + ",'" + bean.getOrgId() + "'," + bean.getLocId() + "," + bean.getModalityId() + "," + formatid + "," + bean.getModelType() + ")";
				presta.executeUpdate(sql2);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.release(rs, stmt, null);
				presta.close();
				ServiceManager.getSession().getConnection().close();
			}
		}
	}

	public Boolean checkIsExist(AiscReportFormat2Loc bean) throws Exception {
		Statement stmt = null;
		ResultSet rsCount = null;
		try {
			stmt = ServiceManager.getSession().getConnection().createStatement();
			String sqlParam = "";
			if (bean.getLocId() != -1 && isNotBlank(String.valueOf(bean.getLocId()))) {
				sqlParam += " and c.Loc_id = '" + bean.getLocId() + "' ";
			}
			if (bean.getModalityId() != -1 && isNotBlank(String.valueOf(bean.getModalityId()))) {
				sqlParam += " and a.modality_id = '" + bean.getModalityId() + "' ";
			}
			if (bean.getModelType() != -1 && isNotBlank(String.valueOf(bean.getModelType()))) {
				sqlParam += " and a.model_type = '" + bean.getModelType() + "' ";
			}
			if (!bean.getOrgId().equals("-1") && isNotBlank(String.valueOf(bean.getOrgId()))) {
				sqlParam += " and d.org_id = '" + bean.getOrgId() + "' ";
			}
			if(bean.getFormat2locId()!=null&&isNotBlank(String.valueOf(bean.getFormat2locId()))){
				sqlParam += " and a.format2loc_id <> " + bean.getFormat2locId();
			}
			
			String sqlCount = "select count(*) COUNT from AISC_ReportFormat2Loc a,AISC_ReportFormat b,aisc_loc c," +
					"sys_org d where a.format_id=b.format_id and a.loc_id=c.loc_id and d.org_id=a.org_id " + sqlParam;
			int total = 0;
			rsCount = stmt.executeQuery(sqlCount);
			if (rsCount.next()) {
				total = rsCount.getInt("COUNT");
			}
			if (total > 0) {
				return true;
			} else return false;
		}catch (Exception e){
			e.printStackTrace();
			return  false ;
		}finally {
			DBUtil.release(rsCount, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
	}
}
