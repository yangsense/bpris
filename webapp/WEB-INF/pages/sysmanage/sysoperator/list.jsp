<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/meta.jsp" %>
    <title>操作员管理</title>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysoperator/list.js?_dc=${staticVersion}"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>

    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet"
          href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>

    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
</head>
<body>
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
            <li class="active">操作员管理</li>
        </ul>
        <!-- .breadcrumb -->
    </div>
    <div class="q_r_list">
        <!--交易记录查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <!--   <tr>
                        <th width="13%" class="r">工号：</th>
                        <td width="25%">
                            <input name="operatorCode" id="operatorCode" class="inputW160"/>
                        </td>
                        <th class="r">姓名：</th>
                        <td><input name="operatorName" id="operatorName" class="inputW160"/>
                        </td>
                        <th class="r">所属机构：</th>
                        <td><input name="orgName" id="orgName" class="inputW160"/>
                        </td>
                    </tr>
                    <tr>
                        <th width="13%" class="r">创建时间：</th>
                        <td >
                            <input id="operateTimeStart" name="operateTimeStart" class="inputW80" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                            --
                            <input id="operateTimeEnd" name="operateTimeEnd" class="inputW80" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                        <th class="r">备注：</th>
                        <td><input name="remarks" id="remarks" class="inputW160"/>
                        </td>
                    </tr> -->
                    
                     <tr>
                <td>工号管理：
                	<input name="operatorCode" id="operatorCode" class="inputW160"/>
                </td>
                <td>
                	姓名：
                	<input name="operatorName" id="operatorName" class="inputW160"/>
                	
                </td>
                	
                <td>
                	所属机构：
                	<input name="orgName" id="orgName" class="inputW160"/>
                </td>
                </tr>
                  <tr>
	                <td>
                	创建时间：
                	 <input id="operateTimeStart" name="operateTimeStart" class="inputW80" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                            --
                     <input id="operateTimeEnd" name="operateTimeEnd" class="inputW80" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
	                <td>备注：
	                <input name="remarks" id="remarks" class="inputW160"/>
	                </td>
                </tr>
                    
                    
                    
                </table>
            </form>
             <!--<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="r n_b_b">&nbsp;</th>
                    <td class="n_b_b">&nbsp;</td>
                    <th class="r n_b_b">&nbsp;</th>
                    <th colspan="3" class="r n_b_b">
                        <a href="javascript:void(0)" onclick="openEdit()" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-credit-card"></span>新 增</a>
                        <a href="javascript:void(0)" onclick="deleteOperators()" id="fbox_grid-table_delete"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-danger"><span
                                class="icon-remove"></span>禁 用</a>
                        <a href="javascript:void(0)" id="queryBtn" onclick="reloadGrid()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                    </th>
                </tr> 
                </table>
                -->
                
               <div>
                   	<span>
                   		 <a href="javascript:void(0)" onclick="openEdit()" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-credit-card"></span>新 增</a>
                        <a href="javascript:void(0)" onclick="deleteOperators()" id="fbox_grid-table_delete"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-danger"><span
                                class="icon-remove"></span>禁 用</a>
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
            <table id="sysOperator" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="sysOperator_pager"></div>
        </div>
        <!--交易记录查询列表 end-->
    </div>
</div>
</body>
</html>
