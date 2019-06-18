$(function () {
	init();
    
	$("#closeBtn").click(function(){
       closeDiv();
    });
    
	var type = $("#aiscBodyPartForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscBodyPartForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#bodypartCode").attr("readonly","true");
		$("#showText").text("保 存");
	}else{
		$("#bodypartCode").attr("disabled","disabled");
		$("#bodypartCode").val("系统自动生成");
		$("#showText").text("保 存");
	}
	$("#bodypartType").select2();
	$("#aiscBodyPartForm").validate({

		// 非空校验
		rules : {
			bodypartDesc : {
				required : true
			},
			orgId:{
				checkSelectVal:true
			}
		},
		errorElement:'em',
		messages: {
			bodypartDesc:{
        		required:'必填字段',
        	}
		},
		submitHandler : save
	});


	$("#saveBtn").click(function(){
		$("#aiscBodyPartForm").submit();

	});

});

function init(){
	$("#bodypartType").val($("#aiscBodyPartForm").attr("old_part_type")==""?"2":$("#aiscBodyPartForm").attr("old_part_type"));
	//部位大类加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getBodyparType.ajax?orgId='+parent.parent.$("#currentOrgId").val(),
		dataType:'json',
		success:function(data){
			var bodyparType = eval(data.BodyparType);
	        var pidSelect = $("#bodypartPid");
	        pidSelect.empty(); 
	        pidSelect.append("<option value='-1'>请选择上挂大部位</option>");
	        for (var i = 0; i < bodyparType.length; i++) {
	        	pidSelect.append("<option value='" + bodyparType[i].bodypartcode + "'>" + bodyparType[i].bodypartdesc + "</option>");
	        }
	        pidSelect.val($("#aiscBodyPartForm").attr("old_bodypartPid")=="0"?"-1":$("#aiscBodyPartForm").attr("old_bodypartPid"));
	        pidSelect.select2();  
//	        if(pidSelect.val()=="-1"){
//	        	$("#bodypartPid").attr("readonly","true");
//	        }
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
	        orgSelect.val($("#aiscBodyPartForm").attr("loc_old_orgId")==""?parent.parent.$("#currentOrgId").val():$("#aiscBodyPartForm").attr("loc_old_orgId"));
	        orgSelect.select2();  
		}
	}); 
}

function changeLable(obj){
//	if($(obj).val()=="1"){
//		$("#lastspan").hide();
////		$("#bodypartPid").attr("readonly","true");
//	}else{
//		$("#lastspan").show();
////		$("#bodypartPid").attr("readonly",false);
//	}
}

function checkAdd() {
	//校验新增数据是否重复
	var  flag= false ;
	var bodypartDesc = $("#bodypartDesc").val();
	var orgId = $("#orgId").val();
	var bodypartCode= $("#bodypartCode").val();
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/checkAiscBodyPart.ajax?bodypartDesc='+bodypartDesc+"&orgId="+orgId+"&bodypartCode="+bodypartCode+"&bodypartType="+$("#bodypartType").val(),
		dataType:'json',
		success:function(data){
			if(data.result=="true"){
				flag=true ;
			}
		}
	});
	return flag ;
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
		//验证
		if (checkAdd()){
			layer.msg('机构与部位名称必须唯一，请重新选择！', {icon: 2});
			return ;
		}
//		if ($("#bodypartType").val()=="2"){
//			if($("#bodypartPid").val()=="-1"){
//				layer.msg('子类部位必须选择上挂部位大部位', {icon: 2});
//				return ;
//			}
//		}
		//提交
	    var data = $("#aiscBodyPartForm").serializeObject();
	    data.orgId = $("#orgId").val();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscBodyPart",
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
