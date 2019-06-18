<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>his检查列表</title>
     
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
               
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/hisPatientlist.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
        
</head>
<body>
<div class="wrap100 quickrecharge">
    <input type="hidden" id="orgId" value="${orgId }"/>
    <input type="hidden" id="orgCode" value="${orgCode }"/>
    <input type="hidden" id="locId" value="${locId }"/>
    <input type="hidden" id="locCode" value="${locCode }"/>
    <input type="hidden" id="queryKey" value="${queryKey }"/>
    <input type="hidden" id="queryValue" value="${queryValue }"/>
	<div class="q_r_list">
			<div>
            	<table id="hisPatientlist" width="100%" border="0" cellspacing="0" cellpadding="0">
               
            	</table>
            	<div id="hisPatientlist_pager"></div>
        	</div>
    </div>
    <div class="clearfix form-actions">
        <div class="col-bt-center" style="text-align:center;">
            <button class="btn btn-info" type="button" id="saveBtn">
                <i class="ace-icon fa fa-check bigger-110"></i>
                	<span id="showText">确定</span>
            </button>
            <button class="btn btn-info" type="button" id="closeBtn">
                <i class="ace-icon fa fa-check bigger-110"></i>
             	   取 消
            </button>
        </div>
    </div>
</div> 

</body>
</html>
