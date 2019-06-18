<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>机构会诊信息综合统计</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
         
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/statanalysis/consultationMultiple.js"></script>
    
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
                <a href="javaScript:void(0);">统计分析</a>
            </li>
            <li class="active">机构会诊信息综合统计</li>
        </ul>
    </div>
	<!--导航end-->
	
	<div class="q_r_list">
	<input type="hidden" id="currentLocId" value="${all_cookie_locId }"/>
	<!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">                     
                   <%--  <tr>   
                    	<th width="13%" class="r">地市：</th>
                      	<td width="13%">
                         <select id="cityCode" name="cityCode" style="width:160px;" onchange="getCountySelect()">  
                             <option value="-1">--请选择--</option>                      
                         </select> 
                        </td>
                        <th class="r" width="13%">区县：</th>
                        <td width="13%">
                            <select id="countyCode" name="countyCode" style="width:160px;" onchange="getOrgSelects()">
                                <option value="-1">--请选择--</option>       
                            </select> 
                        </td> 
                        <th class="r" width="13%">机构 ：</th>
                        <td width="13%">
                            <select id="orgId" name="orgId" style="width:160px;" >
		                         <option value="-1">请选择机构</option>
		                    </select> 
                        </td>                
                    </tr>
                    <tr>
                      	<th width="13%" class="r">开始日期：</th>
                        <td width="13%">
                            <input id="studyStarttime" name="studyStarttime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                            
                        </td> 
                        <th width="13%" class="r">结束日期：</th>
                           <td>                            
                           <input id="studyEndtime" name="studyEndtime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        </td>    
                        <th  class="r">
                        <a href="javascript:void(0)" onclick="reloadGrid()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span class="icon-search"></span>统计</a>
                        
                       <button type="button" onclick="reloadGrid();" class="btn btn-primary">统计</button>
                    </th>   
                    <td width="13%">
                    	<a href="javascript:void(0)" onclick="reset()" class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span class="icon-retweet"></span>重置</a>
                    	<a href="javascript:void(0)" onclick="exportHzMultiple()" 
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-arrow-up"></span>导 出</a>
                    </td>
                    </tr> --%>
                    
                    <tr>   
                      	<td width="13%">地市：
                         <select id="cityCode" name="cityCode" style="width:160px;" onchange="getCountySelect()">  
                             <option value="-1">--请选择--</option>                      
                         </select> 
                        </td>
                        <td width="13%">区县
                            <select id="countyCode" name="countyCode" style="width:160px;" onchange="getOrgSelects()">
                                <option value="-1">--请选择--</option>       
                            </select> 
                        </td> 
                        <td width="13%">机构 
                            <select id="orgId" name="orgId" style="width:160px;" >
		                         <option value="-1">请选择机构</option>
		                    </select> 
                        </td>                
                    </tr>
                    <tr>
                        <td width="13%">开始日期:
                            <input id="studyStarttime" name="studyStarttime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                            
                        </td> 
                           <td> 结束日期：                           
                           <input id="studyEndtime" name="studyEndtime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        </td>    
                    </td>
                    </tr>
                    
                </table>
            </form>
            
        <div>
        </div>
   		<div>
           	<span>
           	<a href="javascript:void(0)" onclick="exportHzMultiple()" 
                   class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                        class="icon-arrow-up"></span>导 出</a>
           		  <!--  <button type="button" onclick="reloadGrid();" class="btn btn-primary">统计</button> -->
                
           	</span>
           	<span style="float:right;">
            	<a href="javascript:void(0)" onclick="reloadGrid()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span class="icon-search"></span>查询</a>
           		<a href="javascript:void(0)" onclick="reset()" class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span class="icon-retweet"></span>重置</a>
           	</span>
        </div>
        
        <!--查询条件 end-->
         
      </div>
      	<div id="firstDiv">
        	<div id="divTotalInfo" style="font-size: 16px"></div>
			<div style="width:100%;margin-top:10px;">
	           	<table id="conlist" width="100%" border="0" cellspacing="0" cellpadding="0">
	              
	           	</table>
	           	<div id="conlist_pager"></div>
	       	</div>
	    </div>   	
	    <div id="detail_div"  style="display: none">
	    	<div id="retLastBtn" style="font-size: 16px"></div>
            <table id="detaillist" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="detail_pager"></div>
        </div>  			
         </div>
</div>

<!-- 书写报告 begin -->
 
<!-- 书写报告 end -->
 

</body>
</html>
