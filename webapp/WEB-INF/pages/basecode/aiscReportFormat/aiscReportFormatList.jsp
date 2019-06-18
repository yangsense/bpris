<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>打印模板维护</title>
    <%--
    	打印模板维护
    --%>
    <script type="text/javascript">
        var resources = eval("("+'${resources}'+")");
    </script>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscreportformat/aiscreportformatlist.js"></script>
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
                <a href="javascript:void(0)">配置管理</a>
            </li>
            <li class="active">打印模板维护</li>
        </ul>
    </div>
	<!--导航end-->
	<div class="q_r_list">
	
	<!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <%--<tr>
                        <th class="r" width="13%">机构名称：</th>
                        <td width="18%">
                           <select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                                <option value="-1">请选择机构</option>
                            </select>
                        </td>  
                        <th class="r" width="13%">科室名称：</th>
                        <td width="18%">
                            <select id="locId" name="locId" class="inputW160">
                                <option value="-1">--请选择--</option>
                            </select>
                        </td>                         
                    </tr>--%>
                    <%--<tr>
                    	<th width="13%" class="r">模板类型：</th>
                        <td width="18%">
                            <select id="modelType" name="modelType" class="inputW160">
                                <option value="-1">请选择类型</option>
                            </select>
                        </td> 
                   		<th width="13%" class="r">设备大类：</th>
                        <td width="18%">
                            <select id="modalityId" name="modalityId" class="inputW160">
                                <option value="-1">请选择类型</option>
                            </select>
                        </td> 
                    </tr>--%>    
                    <tr>
                    	<td>
                    		机构名称：
                    		<select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                                <option value="-1">请选择机构</option>
                            </select>
                    	</td>
                    	<td>
                    		科室名称：
                    		<select id="locId" name="locId" class="inputW160">
                                <option value="-1">--请选择--</option>
                            </select>
                    	</td>
                    	<td>
                    		模板类型：
                    		<select id="modelType" name="modelType" class="inputW160">
                                <option value="-1">请选择类型</option>
                            </select>
                    	</td>
                    	<td>
                    		设备大类：
                    		<select id="modalityId" name="modalityId" class="inputW160">
                                <option value="-1">请选择类型</option>
                            </select>
                    	</td>
                    </tr>
                </table>
                <div>
                   	<span>
                   		<a href="javascript:void(0)" onclick="view(0,'add')" id="fbox_grid-table_search"
                          class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                               class="icon-plus-sign"></span>新 增
                        </a>
                   	</span>
                   	
                   	<span style="float:right;">
                   		<a href="javascript:void(0)" onclick="reloadGrid();"
                          class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success">
                          <span class="icon-search"></span>查 询
                        </a>
                        <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');"
                          class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray">
                          <span class="icon-retweet"></span>重 置
                        </a>
                   	</span>
                </div>
            </form>
            <%--<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
            </table>--%>
        </div>
        <!--查询条件 end-->
		<div>
			<table id="aiscreportlist" width="100%" border="0" cellspacing="0" cellpadding="0" >
		   
			</table>
			<div id="aiscreport_pager"></div>
		</div>
    </div>
</div>

</body>
</html>
