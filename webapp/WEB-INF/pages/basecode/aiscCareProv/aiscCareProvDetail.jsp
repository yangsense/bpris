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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aisccareprov/aisccareprovdetail.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscCareProvForm" dataType="${dataType}" careprov_old_type="${aiscCareProvBean.careprovTypeid}"
                old_org_id="${aiscCareProvBean.orgId}" old_loc_id="${aiscCareProvBean.locId}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 人员ID： </label>
                        <div class="col-xs-3"  style="width:35%">
                            <input  class="inputW120" type="text" id="careprovId" name="careprovId" value="${aiscCareProvBean.careprovId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 人员代码： </label>
                        <div class="col-xs-3"  style="width:35%">
                           <input  class="inputW120" type="text" id="careprovCode" name="careprovCode" value="${aiscCareProvBean.careprovCode}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 人员类别： </label>
                        <div class="col-xs-3"  style="width:35%">
                           <select id="careProvTypeid" name="careprovTypeid" class="inputW120">
                                <option value="-1">请选择类别</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 人员姓名： </label>
                        <div class="col-xs-3"  style="width:35%">
                           <input  class="inputW120" type="text" id="careprovName" name="careprovName" value="${aiscCareProvBean.careprovName}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 机构名称： </label>
                        <div class="col-xs-3"  style="width:35%">
                            <select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                                <option value="-1">请选择机构</option>
                            </select> 
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 所属科室： </label>
                        <div class="col-xs-3"  style="width:35%">
                        	<select id="locId" name="locId" class="inputW160">
                                <option value="-1">--请选择--</option>
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
