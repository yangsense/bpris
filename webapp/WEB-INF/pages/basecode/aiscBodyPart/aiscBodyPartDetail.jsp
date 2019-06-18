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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscbodypart/aiscbodypartdetail.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscBodyPartForm" dataType="${dataType}" loc_old_orgId="${aiscBodyPartBean.orgId}" old_part_type="${aiscBodyPartBean.bodypartType}" old_bodypartPid="${aiscBodyPartBean.bodypartPid}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"> 部位代码： </label>
                        <div class="col-xs-3"  style="width:35%">
                            <input  class="inputW160" type="text" id="bodypartCode" name="bodypartCode" value="${aiscBodyPartBean.bodypartCode}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"><span style="color:red;">*</span> 部位名称： </label>
                       <div class="col-xs-3"  style="width:35%">
                        	<input  class="inputW160" type="text" id="bodypartDesc" name="bodypartDesc" value="${aiscBodyPartBean.bodypartDesc}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"> 部位类型： </label>
                        <div class="col-xs-3"  style="width:35%">
                           <select id="bodypartType" name="bodypartType" class="inputW160" onchange="changeLable(this)">
                                <option value="2">子部位</option>
                                <option value="1">大部位</option>
                            </select> 
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%">上挂部位大类： </label>
                        <div class="col-xs-3"  style="width:35%">
                           <select id="bodypartPid" name="bodypartPid" class="inputW160">
                                <option value="-1">请选择上挂大类</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"> 英文描述： </label>
                        <div class="col-xs-3"  style="width:35%">
                           <input  class="inputW160" type="text" id="bodypartEndesc" name="bodypartEndesc" value="${aiscBodyPartBean.bodypartEndesc}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"> 部位排序： </label>
                        <div class="col-xs-3"  style="width:35%">
                           <input  class="inputW160" type="text" id="bodypartOrder" name="bodypartOrder" value="${aiscBodyPartBean.bodypartOrder}"/>
                        </div>
                    </div>
					<div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"><span style="color:red;">*</span> 所属机构： </label>
                        <div class="col-xs-3"  style="width:35%">
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
