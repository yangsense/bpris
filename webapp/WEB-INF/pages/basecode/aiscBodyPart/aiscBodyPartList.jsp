<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>检查部位设置</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscbodypart/aiscbodypartlist.js"></script>
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
                <a href="javascript:void(0)">检查项目</a>
            </li>
            <li class="active">检查部位设置</li>
        </ul>
    </div>
	<!--导航end-->
	<div class="q_r_list">
	
	<!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <!-- <tr>
                    	<th class="r" width="13%">机构名称 ：</th>
                        <td width="18%" >
                            <select id="orgId" name="orgId" class="inputW160">
                                <option value="-1">请选择机构</option>
                            </select> 
                        </td>  
                        <th class="r" width="13%">部位类型 ：</th>
                        <td width="18%">
                        	<select id="bodypartType" name="bodypartType" class="inputW160">
                        		<option value="-1">--请选择--</option>
                                <option value="2">子部位</option>
                                <option value="1">大部位</option>
                            </select> 
                        </td>   
                        <th class="r" width="13%">上级部位 ：</th>
                        <td width="18%">
                        	<select id="bodypartPid" name="bodypartPid" class="inputW160">
                        		<option value="-1">--请选择--</option>
                            </select> 
                        </td>                           
                    </tr>
                    <tr>	
                    	<th width="13%" class="r">部位名称：</th>
                        <td width="18%">
                            <input name="bodypartDesc" id="bodypartDesc" class="inputW160"/>
                        </td> 
                    	<th class="r" width="13%">部位代码 ：</th>
                        <td width="18%">
                        	<input id="bodypartCode" name="bodypartCode" class="inputW160"/>
                        </td>      
                    	<th class="r" width="13%">英文描述：</th>
                        <td width="18%">
                            <input name="bodypartEndesc" id="bodypartEndesc" class="inputW160"/>
                        </td>  
                    </tr>
                    <tr>
                    	<td colspan="6" align="right">
                    	<a href="javascript:void(0)" onclick="partimport()" id="partImport" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
                    		<span class="icon-arrow-down"></span>导 入</a>
                        <a href="javascript:void(0)" onclick="view(0,'add')" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-plus-sign"></span>新 增</a>
                        <a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                        </td>  
                    </tr> -->
	                <tr>
	                	<td>
	                		机构名称：
	                		<select id="orgId" name="orgId" class="inputW160">
	                        	<option value="-1">请选择机构</option>
	                        </select>
	                	</td>
	                	<td>
	                		部位类型：
	                		<select id="bodypartType" name="bodypartType" class="inputW160">
	                    		<option value="-1">--请选择--</option>
	                            <option value="2">子部位</option>
	                            <option value="1">大部位</option>
	                        </select>
	                	</td>
	                	<td>
	                		上级部位：
	                		<select id="bodypartPid" name="bodypartPid" class="inputW160">
	                    		<option value="-1">--请选择--</option>
	                        </select>
	                	</td>
	                </tr>
	                <tr>
	                	<td>
	                		部位名称：
	                		<input name="bodypartDesc" id="bodypartDesc" class="inputW160"/>
	                	</td>
	                	<td>
	                		部位代码：
	                		<input id="bodypartCode" name="bodypartCode" class="inputW160"/>
	                	</td>
	                	<td>
	                		英文描述：
	                		<input name="bodypartEndesc" id="bodypartEndesc" class="inputW160"/>
	                	</td>
	                </tr>
      			</table>
      			<div>
                	<span>
						<a href="javascript:void(0)" onclick="partimport()" id="partImport" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
                    		<span class="icon-arrow-down"></span>导 入
                    	</a>
                    	<a href="javascript:void(0)" onclick="view(0,'add')" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-plus-sign"></span>新 增
                        </a>
                   	</span>
          				         	
                   	<span style="float:right;">
						<a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询
                        </a>
                        <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置
                        </a>
                   	</span>
                </div>
            </form>
        </div>
        <!--查询条件 end-->
		<div>
			<table id="aiscbodypartlist" width="100%" border="0" cellspacing="0" cellpadding="0">
		   
			</table>
			<div id="aiscbodypart_pager"></div>
		</div>
    </div>
</div>

</body>
</html>
