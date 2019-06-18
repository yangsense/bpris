<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="cache-control" content="no-cache">
	<%@ include file="/common/meta.jsp" %>
	<title>岗位管理</title>
	<script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysoperator/operator2org-manage-init.js?_dc=${staticVersion}"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>

	<script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
	<link type="text/css" rel="stylesheet"
		  href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>

	<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
</head>
<body>
	<div class="q_r_list">
		<!--交易记录查询条件 begin-->
		<div class="listTable">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th colspan="3" class="r n_b_b">
						<a href="javascript:void(0)" onclick="addOperator2Org()" id="fbox_grid-table_search"
						   class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
								class="icon-credit-card"></span>新 增</a>
						<a href="javascript:void(0)" onclick="deleteOperator2org()" id="fbox_grid-table_delete"
						   class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-danger"><span
								class="icon-remove"></span>删 除</a>
					</th>
				</tr>
			</table>
		</div>
		<!--交易记录查询条件 end-->

		<!--交易记录查询列表 begin-->
		<div>
			<table id="operator2org" width="100%" border="0" cellspacing="0" cellpadding="0">

			</table>
			<div id="operator2org_pager"></div>
		</div>
		<!--交易记录查询列表 end-->
	</div>
<input type="hidden" name="operatorCode" value="${id}" id="operatorCode"/>
</body>
</html>
