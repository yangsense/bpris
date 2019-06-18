$(function () {	
	$("#closeBtn").click(function(){
       closeDiv();
    });
	initDict();
	
	var id = $("#locId").val()==''?0:$("#locId").val();
	
	var type = $("#aiscLocForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscLocForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#locId").attr("readonly","true");
		$("#showText").text("保 存");
	}else{
		$("#locId").attr("disabled","disabled");
		$("#locId").val("系统自动生成");
		$("#showText").text("保 存");
	}
	$("#aiscLocForm").validate({

		// 非空校验
		rules : {
			locCode : {
				required : true,
//				remote:GLOBAL.WEBROOT + '/basecode/checkLocCode.ajax?locId='+id)
			},
			locDesc:{
				required:true
			},
			locType:{
				isSelect:true
			},
			orgId:{
				isSelect:true
			},
			locType:{
				checkSelectVal:true
			},
//			serverId:{
//				checkSelectVal:true
//			},
			orgId:{
				checkSelectVal:true
			}
			
//			,
//			serverId:{
//				isSelect:true
//			}
		},
		errorElement:'em',
		messages: {
			locCode:{
        		required:'必填字段',
//        		remote:'已存在'
        	}
		},
		submitHandler : save
	});

	$("#saveBtn").click(function(){
		$("#aiscLocForm").submit();
	});
});

function initDict() {
    //科室类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscLocType.ajax",
        success: function (data) {
            var jsonAiscLocType = eval(data.AiscLocType);
            var loc_TypeSelect = $("#locType");
            loc_TypeSelect.empty();
            loc_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscLocType.length; i++) {
                loc_TypeSelect.append("<option value='" + jsonAiscLocType[i].itemno + "'>" + jsonAiscLocType[i].itemname + "</option>");
            }
            loc_TypeSelect.val($("#aiscLocForm").attr("loc_old_type")==""?"-1":$("#aiscLocForm").attr("loc_old_type"));
            loc_TypeSelect.select2();  
        }
    });
    //服务器加载
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscServer.ajax",
        success: function (data) {
            var jsonAiscServer = eval(data.AiscServer);
            var serverSelect = $("#serverId");
            serverSelect.empty();
            serverSelect.append("<option value='-1'>请选择服务器</option>");
            for (var i = 0; i < jsonAiscServer.length; i++) {
                serverSelect.append("<option value='" + jsonAiscServer[i].serverid + "'>" + jsonAiscServer[i].servername + "</option>");
            }
            serverSelect.val($("#aiscLocForm").attr("loc_old_server")==""?"-1":$("#aiscLocForm").attr("loc_old_server"));
            serverSelect.select2();  
        }
    });
    //机构列表加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/bpris/getOrgs.ajax?operatorCode='+parent.parent.user.operatorCode+"&sysType=25",
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data);
	        var orgSelect = $("#orgId");
	        orgSelect.empty(); 
	        orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        orgSelect.val($("#aiscLocForm").attr("loc_old_orgId")==""?parent.parent.$("#currentOrgId").val():$("#aiscLocForm").attr("loc_old_orgId"));
	        orgSelect.select2();  
		}
	}); 
  
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
		//验证
		if (checkAdd()){
			layer.msg('机构与科室代码必须唯一，请重新选择！', {icon: 2});
			return ;
		}

		//提交
	    var data = $("#aiscLocForm").serializeObject();
	    data.orgId = $("#orgId").val();
	    data.serverId = $("#serverId").val()==undefined?"-1":$("#serverId").val();
	    data = $.toJSON(data);
//	    alert(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscLoc",
	        dataType: 'json',
	        contentType: 'application/json',
	        data: data,
	        success: function (data) {
	            if (data.ERRCODE == "0") {
	            	layer.msg('操作成功', {icon: 1});
	                parent.reloadGrid();
	                setTimeout(closeDiv,1500);
	            }
	            else {
	                layer.msg('新增失败', {icon: 1});
	            }
	        }
	    });
}

function checkAdd() {
	//校验新增数据是否重复
	var  flag= false ;
	var orgId = $("#orgId").val();
	var locCode = $("#locCode").val();
	var locId = $("#locId").val();
	if($("#aiscLocForm").attr("dataType")=="add"){
		locId = 0;
	}
	
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/checkAiscLoc.ajax?orgId='+orgId+"&locCode="+locCode+"&locId="+locId,
		dataType:'json',
		success:function(data){
			if(data.result=="true"){
				flag=true ;
			}
		}
	});
	return flag ;
}