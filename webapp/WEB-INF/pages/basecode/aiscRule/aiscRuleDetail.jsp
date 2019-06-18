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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscrule/aiscruledetail.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/selectTree.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscRuleForm" dataType="${dataType}" old_rule_type="${aiscRuleBean.ruleType}"
                old_loc_id="${aiscRuleBean.locId}" old_org_id="${aiscRuleBean.orgId}" old_rule_Field="${aiscRuleBean.ruleField}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"> 规则ID： </label>
                        <div class="col-xs-3"  style="width:35%">
                            <input  class="inputW160" type="text" id="ruleId" name="ruleId" value="${aiscRuleBean.ruleId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"> 机构名称： </label>
                        <div class="col-xs-3"  style="width:35%">
                        	<select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                                <option value="-1">请选择机构</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"> 科室名称： </label>
                        <div class="col-xs-3"  style="width:35%">
                        	<select id="locId" name="locId" class="inputW160">
                                <option value="-1">请选择科室</option>
                            </select>
                        </div>
                        
                         <label class="col-xs-2 control-label no-padding-right" style="width:15%"> 前缀： </label>
                        <div class="col-xs-3"  style="width:35%">
                           <input  class="inputW160" type="text" id="rulePrix" name="rulePrix" value="${aiscRuleBean.rulePrix}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"><span style="color:red;">*</span> 长度： </label>
                        <div class="col-xs-3"  style="width:35%">
                        	<input  class="inputW160" type="text" id="ruleLen" name="ruleLen" value="${aiscRuleBean.ruleLen}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"><span style="color:red;">*</span> 开始序号： </label>
                        <div class="col-xs-3"  style="width:35%">
                           <input  class="inputW160" type="text" id="ruleStartindex" name="ruleStartindex" value="${aiscRuleBean.ruleStartindex}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width:15%"> 规则字段： </label>
                        <div class="col-xs-3"  style="width:35%">
                        	<%--<input  class="inputW160" type="text" id="ruleField" name="ruleField" value="${aiscRuleBean.ruleField}"/>
                      --%>
                      		<select id="ruleField" name="ruleField" class="inputW160">
                                <option value="-1">请选择方式</option>
                      	 	</select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width:15%"><span style="color:red;">*</span> 生成方式： </label>
                        <div class="col-xs-3"  style="width:35%">
                        	<select id="ruleType" name="ruleType" class="inputW160">
                                <option value="-1">请选择方式</option>
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
