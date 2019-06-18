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
		<link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css">
		<script language="javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>

		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/additional-methods.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/messages_cn.js"></script>

		<link rel="stylesheet" href="${ctx}/aris/statics/common/js/flexigrid/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysoperator/operatorinfo-station.js?_dc=${staticVersion}"></script>

		<style type="text/css">
        .div-center-left {
            float: left;
            width: 50%
        }
        tr{
          height: 40px;         
        } 
        input,select{
         height: 20px;   
        } 
        .h25{
          height: 25px;   
        }
    </style>
	</head>
	<body>
		
	<div class="tanchu-form">
	   <div class="easyui-tabs" style="width: 480px ">
	   <div title="机构信息" style="padding: 10px;">
	    <form id="inputForm" name="inputForm" action="sysmanage/sysoperator/saveOperator2Org" method='post'>
		 <input type="hidden" name="operatorCode" value="${id}" id="operatorCode"/>
		 <input type="hidden" name="orgIds" id="orgIds"/>
			<div>岗位选择：<select id="station" name="stationCode" readonly="readonly">
				</select>
			 </div>
</br>
			机构名称:&nbsp;
			<input type="text" id="orgName" name="orgName" style="width:190px;height: 25px"/>
			&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" id="btnQuery"
			   class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset  btn-sm btn-success"><span
					class="icon-search"></span>查 询</a>
			<a href="javascript:void(0)" id="btnReset"
			   class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search  btn-sm btn-gray"><span
					class="icon-retweet"></span>重 置</a>

			<fieldset id="queryBox">			        
					<legend class="fswitch">机构信息</legend>
					<div id="roleTree" class="ztree" style="height: 280px;overflow: auto;">
					</div>
					
			</fieldset>		
		    <div class="op" style="clear:both;margin-top:20px;">
				<button class="btn btn-primary" type="submit" id="saveBtn">
					<i class="ace-icon fa fa-check bigger-110 icon-save"></i>
					确 认
				</button>
				<button class="btn btn-gray" type="button" id="closeBt">
					<i class="ace-icon fa fa-check bigger-110 icon-remove"></i>
					关 闭
				</button>
		    </div>

         </form>
      </div>
	</div>
	</div>
	</body>

</html>
