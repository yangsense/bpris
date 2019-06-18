<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>数据字典维护</title>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/common/dictItem/list.js"></script>
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
                <a href="javascript:void(0)">数据字典维护</a>
            </li>
            <li class="active">数据字典查询</li>
        </ul>
        <!-- .breadcrumb -->
    </div>
    <div class="q_r_list">
        <!--交易记录查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <!-- <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <th class="r" width="13%">字典值 ：</th>
                        <td width="18%">
                            <input name="itemName" id="itemName" class="inputW160"/>

                        </td>
                        <th width="13%" class="r">字典备注：</th>
                        <td width="18%">
                            <input name="itemDesc" id="itemDesc" class="inputW160"/>
                        </td>
                        <th class="r" width="13%">字典状态：</th>
                        <td width="18%"><select id="itemState" name="itemState" class="inputW160">
                            <option  value="1">有效</option>
                            <option value="0">无效</option>
                        </select>
                        </td>
                    </tr>
                </table> -->
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                	<tr>
                		<td>
                			字典值：
                			<input name="itemName" id="itemName" class="inputW160"/>
                		</td>
                		<td>
                			字典备注：
                			<input name="itemDesc" id="itemDesc" class="inputW160"/>
                		</td>
                		<td>
                			字典状态：
                			<select id="itemState" name="itemState" class="inputW160">
                            	<option  value="1">有效</option>
                            	<option value="0">无效</option>
                        	</select>
                		</td>
                </table>
                <div>
                   	<span>
                   		<a href="javascript:void(0)" onclick="edit()" id="fbox_grid-table_search"
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
            <!-- <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="r n_b_b">&nbsp;</th>
                    <td class="n_b_b">&nbsp;</td>
                    <th class="r n_b_b">&nbsp;</th>
                    <th colspan="3" class="r n_b_b">
                        <a href="javascript:void(0)" onclick="edit()" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-plus-sign"></span>新 增</a>
                        <a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="reset();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                    </th>
                </tr>
            </table> -->
        </div>
        <!--交易记录查询条件 end-->
        <div>
            <table id="dictItem" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="dictItem_pager"></div>
        </div>
    </div>
</div>
</body>
</html>
