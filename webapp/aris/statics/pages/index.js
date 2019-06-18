var menus ;
//var rootOrgId;
var operatorOrgList , org ,user ,userStation,userTJStation ,sysOrg ;
$(function () {
    org = eval("(" + $('#org').val() + ")");
    user = eval("(" + $('#user').val() + ")");
    userStation = $('#userStation').val();
    userTJStation = $('#userTJStation').val();
    sysOrg = $('#sysOrg').val()==''?'':eval("(" + $('#sysOrg').val() + ")");

    //隐藏左侧导航栏west
    $(".layout-button-left").live("click",function(){ 
    }); 
    $(".layout-button-left").click();

	$("select").select2();
	$("#userinfo").text(user.operatorName);
	if($("#isWarn").val()=="isWarn"){
		if($("#residueTime").val()){
            layer.alert("系统注册还有" +$("#residueTime").val()+"天到期，请您联系服务厂商",{icon: 5,time: 4000,skin: 'red',background: 'red'});
        }
	}
	//修改密码功能
	$("#userinfo").click(function(){
		console.log("修改密码用户信息" , user.operatorName, +", " + user.operatorCode);
		var index = layer.open({
	        type: 2,
	        title:"修改密码",
	        area: ['500px', '300px'],
	        fix: true, //不固定
	        maxmin: true,
	        content: GLOBAL.WEBROOT + "/basecode/changeUserPd.html?operatorName="+user.operatorName+"&operatorCode="+user.operatorCode ,
	    });
	});

    //初始化菜单
    initMeun();
	
	//查当前操作员组织机构
    getOrgs(user.operatorCode);
    
//    $.ajax({
//		type: "POST",
//		async: false,
//		url:GLOBAL.WEBROOT +  '/aris/getRootOrgId.ajax',
//		dataType:'json',
//		success:function(data){
//			rootOrgId = data;
//		}
//	});
    
    
});

//查当前操作员组织机构--逻辑有点乱，感觉需要调整
function getOrgs(operatorCode){
	$.ajax({
		type: "POST",
		async: false,
		url: GLOBAL.WEBROOT + '/bpris/getOrgs.ajax?operatorCode='+operatorCode+"&sysType=25",
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data);
	        var orgSelect = $("#orgId");
	        orgSelect.empty(); 
	        for (var i = 0; i < sysOrg.length; i++) { 
	        	operatorOrgList += operatorOrgList == "" ? sysOrg[i].orgId : "," + sysOrg[i].orgId ;
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	      //选中当前组织机构
	      selectCurrentOrg();
			if(sysOrg.length=="0"){
				layer.msg('请对该操作人员进行岗位授权,否则部分功能无法正常使用', {title:'提示',time:9000000000,btn:['退出'],shade: [0.5, '#f5f5f5'],icon: 2},function(){window.location.href = GLOBAL.WEBROOT+'/bpris/logout.html'; });

			}
		}
	}); 
}

//组织机构变动取科室--刷新页面--根据当前组织机构取科室
function getLocInfo(){
	//归属机构标识 
	var orgId = $("#orgId option:selected").val();
	//操作员ID
	var operatorId = user.operatorId;	
	window.location.href = GLOBAL.WEBROOT+'/bpris/index.html?orgId='+orgId;
}

//选中当前组织机构
function selectCurrentOrg(){
	var isy = false;
	var currentOrgId = $("#currentOrgId").val();  //cookie中的值
	if(operatorOrgList.indexOf(currentOrgId) >= 0){
		isy = true;
	}
	//有值选中当前值，无值选中第一条	
	if(currentOrgId != null && currentOrgId != "" && isy){
		$("#orgId").val(currentOrgId);
		$("#orgId").select2();  
	}else{
		$("#orgId option:first").prop("selected", 'selected');
		$("#currentOrgId").val($("#orgId").val());
		$("#orgId").select2();  
	}
	//$("#orgId option").eq(currentOrgId).attr("selected",true);	
}


//初始化组织机构
function initOrgInfo(){
	 var option = {
		        dataStructure: {
		            idKey: "orgId",
		            pIdKey: "parentOrgId",
		            rootPId: 10840,
		            name: "orgName"
		        },
		        data: {
		            url: "/cashbox/getOrgs",    //获取数据的URL
		            param: ["orgId"]  //获取数据的参数
		        },
		        rootNode: [{orgId: '10840', orgName: '请选择', open: true, isParent: true, nocheck: true}]
		    };
		    $("select").select2();
		    com.ai.bdx.util.ztreeComp("orgNames", true, option, callFuncForOrgNames);
		    com.ai.bdx.util.ztreeComp("orgname", false, option, callFuncForOrgEdit);
}
function callFuncForOrgNames(zTree) {
    var nodes = zTree.getCheckedNodes(true);
    var v = "";
    var n = "";
    for (var i = 0, l = nodes.length; i < l; i++) {
        v += nodes[i].orgCode + ",";
        n += nodes[i].orgName + ",";
    }
    if (v.length > 0) v = v.substring(0, v.length - 1);
    if (n.length > 0) n = n.substring(0, n.length - 1);
    $('#orgCodes').val(v);
    $('#orgNames').val(n);
}
function callFuncForOrgEdit(treeNode) {
    $('#orgcode').val(treeNode.orgCode);
    $('#orgname').val(treeNode.orgName);
}



//初始化菜单
function initMeun(){
	$.ajax({
        type: "POST",
        async: false,
        url:GLOBAL.WEBROOT +  '/bpris/getMenus.ajax',
        dataType:'json',
        success:function(data){
//                var menus = eval($("#menus").val());
            	menus = eval(data.menus);
            	if (menus) {
		        var parentId;
		        var topId;
		        //find header topId;
		        for (var i = 0; i < menus.length; i++) {
		            parentId = menus[i].parentMenuId;
		            if (parentId == '0') {
		                topId = menus[i].menuId;
		                break;
		            }
		        }
		        var divMenuHeader = $("#menuHeader");
		        var j = 0;
		        for (var i = 0; i < menus.length; i++) {
		            parentId = menus[i].parentMenuId;
		            if (parentId == topId) {
		                divMenuHeader.append('<div class="nav-item dropdown"><ul><li><div class="nav01" id="pmenuId_'+menus[i].menuId+'"><a href="javascript:void(0)" onclick="queryMenuByParent('+parentId+','+menus[i].menuId+')">' + menus[i].menuName + '</a></div></li></ul></div>');
		                if (j == 0) {
		                    loadMenuTree(menus[i].menuId);
		                    $("#pmenuId_"+menus[i].menuId).attr("class","nav02"); 
		                    j++;
		                }
		            }
		        }
		    }
            	//menuTree
		    var target = $("#menuTree1").find("a")[0].target;
			if(target=="view_frame"){
				$("#menuTree1").find("a")[0].click();
			}else{
				$("#menuTree1").find("a")[1].click();
			}
        }
    });
}


function loadMenuTree(topId){
	//menuTree
    var menuTree = $("#menuTree1");
    for(var i=0;i<menus.length;i++){
        //直接子节点
        if(topId == menus[i].parentMenuId) {
            if (menus[i].menuType == 1) {
                menuTree.append("<dl><h5><a href='"+menus[i].menuUrl+"' target='view_frame'><i class='fM-l-icon2'>" + menus[i].menuName + "</i></a></h5><dl>");
            } else {
                menuTree.append("<dl id='dl" + menus[i].menuId + "'><h5><a href='javascript:void(0);' onclick='changeMenu("+menus[i].menuId+")'><i class='fM-l-icon2'>" + menus[i].menuName + "</i></a></h5><dl>");
            }
        }
    }
    
    var dl
    for(var i=0;i<menus.length;i++){
        var menuId = menus[i].parentMenuId;
        dl = $("#dl"+menuId);
        if(dl){
            dl.append('<dt><a href="'+ menus[i].menuUrl +'" target="view_frame">'+menus[i].menuName+'</a></dt>');
        }
    }

    //自动解锁书写报告
    autoUnLockStudyReport();
}



function changeMenu(id){
	var dts = $("#dl"+id).find("dt");
	var cls = $("#dl"+id).attr("class");
	if(cls=='hideDiv'){
		if(dts&&dts.length>0){
			$("#dl"+id).removeClass("hideDiv");
			$("#dl"+id).addClass("showDiv");
			$("#dl"+id).find("i").removeClass();
			$("#dl"+id).find("i").addClass("fM-l-icon2");
			for(var i=0;i<dts.length;i++){
				$(dts[i]).show();		
			}
		}
	}else{
		if(dts&&dts.length>0){
			$("#dl"+id).removeClass("showDiv");
			$("#dl"+id).addClass("hideDiv");
			$("#dl"+id).find("i").removeClass();
			$("#dl"+id).find("i").addClass("showicon2");
			for(var i=0;i<dts.length;i++){
				$(dts[i]).hide();		
			}
		}
	}
	
}

function openMenu(url,menuId,name,isClose){
    WEB.addTab({title: name, 'url': url,closable:isClose});
}

function queryMenuByParent(parentMenuId,menuId){
		//TAB.closeAll();
		var o_id = "pmenuId_"+menuId;
		var menus = $(".nav-item").find("div"); 
		if(menus&&menus.length>0){
			for(var i=0;i<menus.length;i++){
				var temp = $(menus[i]).attr("id"); 
				if(o_id==temp){
					$(menus[i]).attr("class","nav02");
					$("#menuTree1").html('');
					loadMenuTree(menuId);
					var target = $("#menuTree1").find("a")[0].target;
					if(target=="view_frame"){
						$("#menuTree1").find("a")[0].click();
					}else{
						$("#menuTree1").find("a")[1].click();
					}
				}else{
					$(menus[i]).attr("class","nav01"); 
				}
			} 
		}
}

/* worklist 75行  */
/*function switchBarl(){
	 var imgsrc=document.all("makeleft").src;
	 if (imgsrc.indexOf("fM-left.png")>1){  
		 document.all("makeleft").src=GLOBAL.WEBROOT+"/aris/statics/pages/css/index/images/fM-right.png";  
		 document.all("makeleft").title="隐藏左边的菜单";  
		 $("#left_content").removeClass();
		 $("#left_content").addClass("fl all_content");
//		 $("#left_scroll").removeClass();
//		 $("#left_scroll").addClass("fl l_left");
		 $("#left_scroll").css("left",0);
		 $("#menuTree").hide(); 
		 
	 }else{  
		 document.all("makeleft").src=GLOBAL.WEBROOT+"/aris/statics/pages/css/index/images/fM-left.png";  
		 document.all("makeleft").title="显示左边的菜单";  
		 $("#left_content").removeClass();
		 $("#left_content").addClass("fl right");
		 $("#left_scroll").css("left",240);
//		 $("#left_scroll").addClass("fl l_close");
		 $("#menuTree").show(); 
	 }
}*/
 
function logout(){
	layer.confirm('您确定要退出吗？', {
		  btn: ['是','否'] //按钮
		}, function(){
			window.location.href = GLOBAL.WEBROOT+'/bpris/logout.html'; 
		}, function(){
//		  layer.msg('也可以这样', {
//		    time: 20000, //20s后自动关闭
//		    btn: ['明白了', '知道了']
//		  });
		});
}

function autoUnLockStudyReport() {
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + '/studyReport/autoUnLockStudyReport.ajax',
        dataType: 'json',
        // data :setdata,
        success: function (data) {
            if (data.ERRCODE == '1') {
                return;
            }
        }
    });
}
