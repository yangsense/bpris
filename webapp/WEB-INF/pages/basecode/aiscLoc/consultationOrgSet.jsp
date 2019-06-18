<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/messages_cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscloc/aiscConsult.js"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscConsultForm">
                 <input name="orgId" id="orgId" type="hidden" value="${QueryOrgLocBean.orgId}"/>
                 <input name="locId" id="locId" type="hidden" value="${QueryOrgLocBean.locId}"/>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 当前机构： </label>
                        <div class="col-xs-3"  style="width: 35%"  >
                            ${QueryOrgLocBean.orgName}
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 当前科室： </label>
                        <div class="col-xs-3"  style="width: 35%" >
                           ${QueryOrgLocBean.locDesc}
                        </div>
                    </div>
                     <hr/>
					<div>
						<table id="aiscConsultlist" width="100%" border="0" cellspacing="0" cellpadding="0">
					   
						</table>
						<div id="aiscConsult_pager"></div>
					</div>
					<hr/>
					<div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 会诊机构名称： </label>
                        <div class="col-xs-3"  style="width: 35%"  >
                            <div  name="orgName" style="width:240px;" onclick="changeOrgLoc(this)">
			                </div>
			                <input type="hidden" name="conorgId" id="conorgId"/>		 
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 会诊科室： </label>
                        <div class="col-xs-3"  style="width: 35%" >
                           <select id="conlocId" name="conlocId" style="width:240px;" >   
                            <option value='-1'>请选择会诊科室</option>
			                </select>
                        </div>
                    </div>
					
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-info" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                	<span id="showText">保 存</span>
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
