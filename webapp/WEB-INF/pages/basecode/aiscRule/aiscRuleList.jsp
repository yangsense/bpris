<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>规则生成器设置</title>
    <script type="text/javascript">
        var resources = eval("("+'${resources}'+")");
    </script>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscrule/aiscrulelist.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/selectTree.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
</head>
<body>
<div class="wrap100 quickrecharge">
	<!--导航start-->
	<div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb" style="margin-top: 8px;">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="javascript:void(0)">科室管理</a>
            </li>
            <li class="active">规则生成器设置</li>
        </ul>
    </div>
	<!--导航end-->
	<div class="q_r_list">
	
	<!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                	<tr>
                      <th class="r" width="13%">机构名称：</th>
                      <td width="18%">
                        	<select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                                <option value="-1">请选择机构</option>
                            </select>
                      </td> 
                      <th class="r" width="13%">科室名称 ：</th>
                      <td width="18%" >
                            <select id="locId" name="locId" class="inputW160">
                                <option value="-1">--请选择--</option>
                            </select>
                      </td>   
                      <th class="r" width="13%">生成方式：</th>
                        <td width="18%">
                            <select id="ruleType" name="ruleType" class="inputW160">
                            	 <option value="-1">请选择方式</option>
                            </select> 
                        </td>    
                    </tr>
                    <tr>
                        <th class="r" width="13%">规则ID ：</th>
                        <td width="18%">
                        	<input id="ruleId" name="ruleId" class="inputW160"/>
                        </td>                         
                        <th width="13%" class="r">规则前缀：</th>
                        <td width="18%" colspan="3">
                            <input name="rulePrix" id="rulePrix" class="inputW160"/>
                        </td> 
                    </tr>
                    
                </table>
            </form>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="r n_b_b">&nbsp;</th>
                    <td class="n_b_b">&nbsp;</td>
                    <th class="r n_b_b">&nbsp;</th>
                    <th colspan="3" class="r n_b_b">
                    	<a href="javascript:void(0)" onclick="view(0,'add')" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-plus-sign"></span>新 增</a>
                        <a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                    </th>
                </tr>
            </table>
        </div>
        <!--查询条件 end-->
		<div>
			<table id="aiscrulelist" width="100%" border="0" cellspacing="0" cellpadding="0">
		   
			</table>
			<div id="aiscrule_pager"></div>
		</div>
    </div>
</div>

</body>
</html>
