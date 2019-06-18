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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aisccareprov/aisccareprovlist.js"></script>
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
                <a href="javascript:void(0)">人员管理</a>
            </li>
            <li class="active">人员维护</li>
        </ul>
    </div>
	<!--导航end-->
	<div class="q_r_list">
	
	<!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                	<tr>
                		<!-- <th class="r" width="13%">机构名称 ：</th>
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
                        <th class="r" width="13%">人员类别：</th>
                        <td width="18%">
                            <select id="careprovTypeid" name="careprovTypeid" class="inputW160">
                                <option value="-1">请选择类别</option>
                            </select> 
                        </td>                  
                    </tr>
                    <tr>
                        <th class="r" width="13%">人员代码 ：</th>
                        <td width="18%">
                            <input name="careprovCode" id="careprovCode" class="inputW160"/>
                        </td>                         
                        <th width="18%" class="r">人员名称：</th>
                        <td width="18%"   colspan='3'>
                            <input name="careprovName" id="careprovName" class="inputW160"/>
                        </td>  -->
                        
                    </tr>
                    <tr>
                    	<td width="2%">
                        	机构名称:
                        	<select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                                <option value="-1">请选择机构</option>
                            </select> 
                        </td>
                        <td width="2%">
                        	科室名称:
                        	<select id="locId" name="locId" class="inputW160">
                                <option value="-1">--请选择--</option>
                            </select>
                        </td>
                        <td width="2%">
                        	人员类别:
                        	<select id="careprovTypeid" name="careprovTypeid" class="inputW160">
                                <option value="-1">请选择类别</option>
                            </select>
                        </td>
                        
                    </tr>
                    <tr>
                    	<td width="2%">
                        	人员代码:
                        	<input name="careprovCode" id="careprovCode" class="inputW160"/>
                        </td>
                        <td width="2%">
                        	人员名称:
                        	<input name="careprovName" id="careprovName" class="inputW160"/>
                        </td>
                    </tr>
                </table>
            </form>
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
                     <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');"
                      class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                         class="icon-retweet"></span>重 置</a>
				</span>
            </div>
			
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
