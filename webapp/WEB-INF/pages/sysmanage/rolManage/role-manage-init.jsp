<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>人员信息</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/roleManage/role-manage-init.js"></script>
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
                <a href="javascript:void(0)">用户管理配置</a>
            </li>
            <li class="active">角色管理</li>
        </ul>
    </div>
    <!--导航end-->
    <div class="q_r_list">

        <!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                   <%--  <tr>
                        <th class="r" width="13%">角色名称：</th>
                        <td width="18%">
                            <input name="roleName" id="roleName" class="inputW160"/>
                        </td>
                        <th class="r" width="13%">归属系统：</th>
                        <td width="18%" >
                            <select id="sysType" name="sysType" class="inputW160">
                                <option value="-1">--请选择--</option>
                                <c:forEach var="sysMenuBeans" items="${sysMenuBeans}">
                                    <option value="${sysMenuBeans.sysType}" selected>${sysMenuBeans.menuName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th class="r" width="13%">创建时间：</th>
                        <td width="18%">
                            <input class="inputW160" type="text" id="createStart" name="createStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px"/>
                            &nbsp;-&nbsp;
                            <input class="inputW160" type="text" id="createEnd" name="createEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px"/>
                        </td>
                    </tr> --%>
                    
                    
                    <tr>
                    	<td>
                    		角色名称：
                    		 <input name="roleName" id="roleName" class="inputW160"/>
                    	</td>
                    	<td>
                    		归属系统：
                    		<select id="sysType" name="sysType" class="inputW160">
                                <option value="-1">--请选择--</option>
                                <c:forEach var="sysMenuBeans" items="${sysMenuBeans}">
                                    <option value="${sysMenuBeans.sysType}" selected>${sysMenuBeans.menuName}</option>
                                </c:forEach>
                            </select>
                    	</td>
                    	<td>
                    		创建时间：
                    		<input class="inputW160" type="text" id="createStart" name="createStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px"/>
                            &nbsp;-&nbsp;
                            <input class="inputW160" type="text" id="createEnd" name="createEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px"/>
                    	</td>
                    </tr>
                  
                    
                    
                </table>
                
                  <div>
                   	<span>
                   		<a href="javascript:void(0)" onclick="view(0,'add')" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-plus-sign"></span>新 增</a>
                   	</span>
                   	<span style="float:right;">
                   		<a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');initData()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                   	</span>
                </div>
                
                
            </form>
            <!-- <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
                        <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');initData()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                    </th>
                </tr>
            </table> -->
        </div>
        <!--查询条件 end-->
        <div>
            <table id="aisccareprovlist" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="aisccareprov_pager"></div>
        </div>
    </div>
</div>

</body>
</html>
