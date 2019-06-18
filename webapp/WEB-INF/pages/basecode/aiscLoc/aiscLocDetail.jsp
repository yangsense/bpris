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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscloc/aisclocdetail.js"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscLocForm" dataType="${dataType}" loc_old_orgId="${aiscLocBean.orgId}" loc_old_server="${aiscLocBean.serverId}" loc_old_type="${aiscLocBean.locType}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 科室ID： </label>
                        <div class="col-xs-3"  style="width: 35%"  >
                            <input  class="inputW160" type="text" id="locId" name="locId" value="${aiscLocBean.locId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 科室代码： </label>
                        <div class="col-xs-3"  style="width: 35%" >
                           <input  class="inputW160"  type="text" id="locCode" name="locCode" value="${aiscLocBean.locCode}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 科室拼音检索： </label>
                        <div class="col-xs-3"  style="width: 35%">
                        	<input  class="inputW160" type="text" id="locIndex" name="locIndex" value="${aiscLocBean.locIndex}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 科室名称： </label>
                        <div class="col-xs-3"  style="width: 35%">
                           <input  class="inputW160" type="text" id="locDesc" name="locDesc" value="${aiscLocBean.locDesc}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 英文描述名称： </label>
                        <div class="col-xs-3"  style="width: 35%">
                       		<input  class="inputW160" type="text" id="locEndesc" name="locEndesc" value="${aiscLocBean.locEndesc}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 科室电话： </label>
                        <div class="col-xs-3"  style="width: 35%">
                           <input  class="inputW160" type="text" id="locPhone" name="locPhone" value="${aiscLocBean.locPhone}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 科室地址： </label>
                        <div class="col-xs-3"  style="width: 35%">
                        	<input  class="inputW160" type="text" id="locAddress" name="locAddress" value="${aiscLocBean.locAddress}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 科室类型： </label>
                        <div class="col-xs-3"  style="width: 35%">
                           <select id="locType" name="locType" class="inputW160">
                                <option value="-1">请选择类型</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 服务器： </label>
                        <div class="col-xs-3"  style="width: 35%">
                            <select id="serverId" name="serverId" class="inputW160">
                                <option value="-1">请选择服务器</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 所属机构： </label>
                        <div class="col-xs-3"  style="width: 35%">
		                	<select id="orgId" name="orgId" class="inputW160">
                                <option value="-1">请选择机构</option>
                            </select>
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
