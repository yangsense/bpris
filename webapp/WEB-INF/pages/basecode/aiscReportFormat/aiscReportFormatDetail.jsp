<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/messages_cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscreportformat/aiscreportformatdetail.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/config.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscReportForm" dataType="${dataType}" old_loc_id="${aiscReportBean.locId}"
                 old_org_id="${aiscReportBean.orgId}" old_modalityId="${aiscReportBean.modalityId}" old_modelType="${aiscReportBean.modelType}">
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 机构名称： </label>
                        <div class="col-xs-3"  style="width: 35%">
                        	<select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                                <option value="-1">请选择机构</option>
                            </select> 
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 科室名称： </label>
                        <div class="col-xs-3"  style="width: 35%"  >
                        	<select id="locId" name="locId" class="inputW160">
                                <option value="-1">--请选择--</option>
                            </select> 
                        </div>
                        
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 设备大类： </label>
                        <div class="col-xs-3"  style="width: 35%" >
                        	<select id="modalityId" name="modalityId" class="inputW160">
                                <option value="-1">请选择类型</option>
                            </select> 
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 模板名称： </label>
                        <div class="col-xs-3"  style="width: 35%">
                           <input  class="inputW160" type="text" id="formatName" name="formatName" value="${aiscReportBean.formatName}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 模板类型： </label>
                        <div class="col-xs-3"  style="width: 35%" >
                        	<select id="modelType" name="modelType" class="inputW160">
                                <option value="-1">请选择类型</option>
                            </select> 
                        </div>
                        
                    </div>
                    <label class="col-sm-9 control-label no-padding-right" style="width: 15%"> 模板内容： </label>
                    <div class="form-group">
                        <div class="col-xs-12"  style="width: 100%;height:400px" align="center">
                        	<textarea id="reportFormat" cols="5" rows="30" isRich="true" name="reportFormat" class="ckeditor">
                       		${aiscReportBean.reportFormat}</textarea>
                        </div>
                        
                    </div>
                    
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-info" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                	<span id="showText">新 增</span>
                            </button>
                            <button class="btn btn-info" type="button" id="closeBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                             	   取 消
                            </button>
                        </div>
                    </div>
                     <input type="hidden" name="format2locId" id="format2locId" value="${aiscReportBean.format2locId}"/>
                     <input type="hidden" name="formatId" id="formatId" value="${aiscReportBean.formatId}"/>
                </form>
			</div>
		</div>	
	</div>
</div>
 </body>
</html>
