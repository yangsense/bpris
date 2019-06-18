<%@ page contentType="text/html;charset=UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
	<head> 	<%@ include file="/common/taglibs.jsp"%>
		<title>菜单配置</title>
		<%@ include file="/common/meta.jsp"%>
		<script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/menuManage/json2.js"></script>
		<link href="${ctx}/aris/statics/pages/js/basecode/menuManage/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/menuManage/menuSort.js"></script>
		


	</head>
<body>

<div id="container">
<table width="100%" border="0">
  <tr>
    <td height="354px" valign="top" style="border:inset ;overflow: auto;width: 95%">
    	<div  style="height: 354px;">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="configTab">
	      <tr class="tabBg" >
	        <td align="center" valign="middle" class="bor" width="20%" nowrap="nowrap">菜单名称</td>
	        <td align="center" valign="middle" class="bor" nowrap="nowrap">菜单连接</td>
	        
	      </tr> 
	    </table>
    </div>
    </td>
    <td width="10px">
    	<input name="" type="button" value="↑" onclick="InfoBean.toTop();" title="向上移"/><br/>
    	<input name="" type="button" value="↓" onclick="InfoBean.toDown();" title="向下移"/>
    </td>
  </tr>
</table>
	<div class="clearfix form-actions">
		<div class="col-bt-center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn btn-info" type="button"  id="commitBt">
				<i class="ace-icon fa fa-check bigger-110"></i>
				<span id="showText">保 存</span>
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn btn-info" type="button" id="closeBt">
				<i class="ace-icon fa fa-check bigger-110"></i>
				取 消
			</button>
		</div>
	</div>
</div>
	
</body>


</html>
