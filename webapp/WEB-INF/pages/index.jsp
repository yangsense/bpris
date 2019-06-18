<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/meta.jsp" %>
<title>影像云平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
<meta http-equiv="X-UA-Compatible" content="IE=edge" > 

<link href="${ctx}/aris/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css">
<link href="${ctx}/aris/statics/pages/css/index/css/detail.css" rel="stylesheet" type="text/css">


	<link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css">
    <script language="javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/bt.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/menuManage/menuInit.js"></script>

 <script src="${ctx}/aris/statics/pages/index.js"></script>
 <script type="text/javascript">
     var GLOBAL = {WEBROOT:"${ctx}"};
 </script>
<!-- 取session中信息 -->
 <input type="hidden" id="isWarn" value='${isWarn}'/>
 <input type="hidden" id="residueTime" value='${residueTime}'/>
 <input type="hidden" id="org" value='${_USER_ORG}'/>
<input type="hidden" id="user" value='${_USER_INFO_MAP_}'/>
<input type="hidden" id="userStation" value='${_OPERATOR_STATION}'/>
 <input type="hidden" id="userTJStation" value='${_OPERATOR_TJ_STATION}'/>
 <input type="hidden" id="sysOrg" value='${_SYS_ORG}'/>

 <style>
  body .layui-layer-setmybg .layui-layer-content{background-color:  #c00; color: #fff;}
 </style>

<script>
 
    /**  
     * layout方法扩展  
     * @param {Object} jq  
     * @param {Object} region  
     */  
    $.extend($.fn.layout.methods, {   
        /**  
         * 面板是否存在和可见  
         * @param {Object} jq  
         * @param {Object} params  
         */  
        isVisible: function(jq, params) {   
            var panels = $.data(jq[0], 'layout').panels;   
            var pp = panels[params];   
            if(!pp) {   
                return false;   
            }   
            if(pp.length) {   
                return pp.panel('panel').is(':visible');   
            } else {   
                return false;   
            }   
        },   
        /**  
         * 隐藏除某个region，center除外。  
         * @param {Object} jq  
         * @param {Object} params  
         */  
        hidden: function(jq, params) {   
            return jq.each(function() {   
                var opts = $.data(this, 'layout').options;   
                var panels = $.data(this, 'layout').panels;   
                if(!opts.regionState){   
                    opts.regionState = {};   
                }   
                var region = params;   
                function hide(dom,region,doResize){   
                    var first = region.substring(0,1);   
                    var others = region.substring(1);   
                    var expand = 'expand' + first.toUpperCase() + others;   
                    if(panels[expand]) {   
                        if($(dom).layout('isVisible', expand)) {   
                            opts.regionState[region] = 1;   
                            panels[expand].panel('close');   
                        } else if($(dom).layout('isVisible', region)) {   
                            opts.regionState[region] = 0;   
                            panels[region].panel('close');   
                        }   
                    } else {   
                        panels[region].panel('close');   
                    }   
                    if(doResize){   
                        $(dom).layout('resize');   
                    }   
                };   
                if(region.toLowerCase() == 'all'){   
                    hide(this,'east',false);   
                    hide(this,'north',false);   
                    hide(this,'west',false);   
                    hide(this,'south',true);   
                }else{   
                    hide(this,region,true);   
                }   
            });   
        },   
        /**  
         * 显示某个region，center除外。  
         * @param {Object} jq  
         * @param {Object} params  
         */  
        show: function(jq, params) {   
            return jq.each(function() {   
                var opts = $.data(this, 'layout').options;   
                var panels = $.data(this, 'layout').panels;   
                var region = params;   
      
                function show(dom,region,doResize){   
                    var first = region.substring(0,1);   
                    var others = region.substring(1);   
                    var expand = 'expand' + first.toUpperCase() + others;   
                    if(panels[expand]) {   
                        if(!$(dom).layout('isVisible', expand)) {   
                            if(!$(dom).layout('isVisible', region)) {   
                                if(opts.regionState[region] == 1) {   
                                    panels[expand].panel('open');   
                                } else {   
                                    panels[region].panel('open');   
                                }   
                            }   
                        }   
                    } else {   
                        panels[region].panel('open');   
                    }   
                    if(doResize){   
                        $(dom).layout('resize');   
                    }   
                };   
                if(region.toLowerCase() == 'all'){   
                    show(this,'east',false);   
                    show(this,'north',false);   
                    show(this,'west',false);   
                    show(this,'south',true);   
                }else{   
                    show(this,region,true);   
                }   
            });   
        }   
    });  
</script>
</head>
<body >
<!--top beign-->
<div class="header clearfix b-blue">
<!-- 导航栏 -->
<div class="head-nav" id="menuHeader"></div>
 <h1>
 	<a href="javascript:void(0);"> 
 		<img src="${ctx}/aris/statics/pages/css/index/img/logo.png" alt="影像云平台">
 	</a>
 </h1>
 
 <div class="head-mem">
  <input type="hidden" id="currentOrgId" value="${currentOrgId }"/>
  
  <select id="orgId" name="orgId"  onchange="getLocInfo();" style="width:120px;">  
        <option value="-1" selected>请选择</option>     
  </select> 
  <span class="head-right">
  	<%-- <img src="${ctx}/aris/statics/pages/css/index/images/icon_1.png" ></img> --%>
    <a id="userinfo" href="javaScript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
       <span class="caret"></span>   
    </a>
   
  	<a href="javaScript:void(0)" onclick="logout()">&nbsp;&nbsp;&nbsp;退出</a>
  </span>
  
 </div>
 
</div>
<!--top end-->

<div class="easyui-layout" style="height:98%" >
		<%--浅蓝:<!-- background-color: #d1eeee  边框颜色: #d2e0f2-->--%>
		
		<div region="west" split="true" title="&nbsp;" maximizable="false"
	      style="width:230px;padding:0px;padding-top:5px;height: 100%;overflow: auto; background-color:#cfdcd9;">
	      
	      <div class="wrap100 fM_content">
			 <div class="fl left" id="menuTree1" style="width: 100%;"></div>
		  </div>
	  	</div>
	     
	     <div region="center" split="true" maximizable="true" style="width:500px;padding:5px;overflow: auto;">
	     	<!--右侧内容 begin-->
			<div class="fl right" style="height:100%;width:100%;" id="left_content">
				<iframe id="content" name="view_frame" frameborder="0" height="100%" width="100%" scrolling="auto"></iframe>
			</div>
			<!--右侧内容 end -->
	     
	     
	     </div>
		
	

	<!--左侧导航 start-->
	 <%--<div class="fl left" id="menuTree"></div>
	 
	--%>
	<%--<div class="fl l_close" id="left_scroll"> 
		<a href="javascript:void(0);">
			<img src="${ctx}/aris/statics/pages/css/index/images/fM-left.png" id="makeleft" onclick="switchBarl()" />
		</a> 
	</div>
	--%>
	<!--左侧导航 over-->
	
</div>




</body>
</html>