<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
	<head> 	<%@ include file="/common/taglibs.jsp"%>
		<title>角色授权</title>
		<%@ include file="/common/meta.jsp"%>

		<script language="javascript" src="${ctx}/aris/statics/common/js/frameajax.js"></script>
		<link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/css/frameajax.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/flexigrid/css/flexigrid.css"/>
		<script type="text/javascript" src="${ctx}/aris/statics/common/js/flexigrid/js/flexigrid.js"></script>

		<link href="${ctx}/aris/statics/common/js/flexigrid/css/base.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>

		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/additional-methods.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/messages_cn.js"></script>

		<link rel="stylesheet" href="${ctx}/aris/statics/common/js/flexigrid/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>

		<script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysoperator/operatorinfo-belong-org.js?_dc=${staticVersion}"></script>
 	</head>
	<body>
		
	<div class="tanchu-form">
	 <form action="" name="inputForm" id="inputForm">
	   <div title="机构信息" style="padding: 10px;">
  		 <input type="hidden" name="orgIds" value="${id}" id="orgIds"/>
		   机构名称:
		   <input type="text" id="orgName" name="orgName" style="width:100px"/>
		   <input type="button" class="d_button" id="btnQuery" value="查 询"/>
		   <input type="button" class="d_button" id="btnReset" value="重 置"/>
			<fieldset id="queryBox">
					<legend class="fswitch">机构信息</legend>
					<div id="roleTree" class="ztree" style="height: 280px;overflow: auto;">
					</div>
					 
			</fieldset>		
		    <div class="op" style="clear:both;">
		        <input type="submit" class="btn" value="确 定" />
		        <input type="button" class="btn" value="取 消" id="closeBt"/>
		    </div> 
      </div> 
    </form>
	</div>
	</body>

</html>
