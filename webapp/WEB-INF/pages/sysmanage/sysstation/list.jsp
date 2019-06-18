<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/meta.jsp" %>
    <title>岗位管理</title>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysstation/list.js?_dc=${staticVersion}"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>

    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet"
          href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>

    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
</head>

<body >

  	
<div class="wrap100 quickrecharge">

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
                用户配置管理
            </li>
            <li class="active">岗位管理</li>
        </ul>
        <!-- .breadcrumb -->
    </div>
    <div class="q_r_list">
        <!--交易记录查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                    	<td width="25%">岗位名称：
                    		<input name="stationName" id="stationName" class="inputW160"/>
                    	</td>
                    	<td >岗位编码：
                    		<input name="stationCode" id="stationCode" class="inputW160"/>
                    	</td>
                    </tr>
                </table>
            </form>
            
              <div>
                   	<span>
                   		  <a   href="javascript:void(0)" onclick="openEdit()" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-credit-card"></span>新 增</a>
                        <a href="javascript:void(0)" onclick="deleteStation()" id="fbox_grid-table_delete"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-remove"></span>删 除</a> 
                        
                   	</span>
                   	<span style="float:right;">
                   		 	<a href="javascript:void(0)" id="queryBtn" onclick="reloadGrid()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                   	</span>
                </div>
                
        </div>
        <!--交易记录查询条件 end-->

        <!--交易记录查询列表 begin-->
        <div>
            <table id="sysStation" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="sysStation_pager"></div>
        </div>
        <!--交易记录查询列表 end-->
    </div>
</div>
</body>
</html>
