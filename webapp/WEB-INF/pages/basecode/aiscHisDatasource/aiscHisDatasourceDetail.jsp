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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscHisDatasource/aiscHisDatasourceDetail.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/config.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" id="aiscPeriodForm" dataType="${dataType}"  role="form">
                	<input type="hidden" name="sourceId" value="${bean.sourceId }"/>
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 机构名称： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<input id="orgIdHidden" type="hidden" value="${bean.orgId }"/>
                        	<select id="orgId" name="orgId" class="inputW160">
                                <option value="-1">请选择机构</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%">类型： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<input id="typecodeHidden" type="hidden" value="${bean.patientTypecode }"/>
                        	<select id="patientTypecode" name="patientTypecode" class="inputW160">
                                <option value="-1">请选择机构</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 用户名： </label>
                        <div class="col-xs-3"  style="width: 30%" >
                        	<input type="text" name="sourceUser" value="${bean.sourceUser }" class="inputW160"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 密码： </label>
                        <div class="col-xs-3"  style="width: 40%" >
                        	<input type="text" name="sourcePassword" value="${bean.sourcePassword }" class="inputW160"/>
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 数据源地址： </label>
                        <div class="col-xs-3"  style="width: 70%"  >
                        	<input style="width:100%" type="text" name="sourceUrl" value="${bean.sourceUrl }"/>
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
                </form>
			</div>
		</div>	
	</div>
</div>
 </body>
</html>
