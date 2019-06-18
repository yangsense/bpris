var checkValue = false;
$(function () {	
	$("#closeBtn").click(function(){
       closeDiv();
    });
	
	$("#verifymode").val($("#aiscboardroomForm").attr("old_verifymode")==""?"-1":$("#aiscboardroomForm").attr("old_verifymode"));
	$("#enablechairpwd").val($("#aiscboardroomForm").attr("old_enablechairpwd")==""?"0":$("#aiscboardroomForm").attr("old_enablechairpwd"));
	$("select").select2();
	var type = $("#aiscboardroomForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscboardroomForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#boardroomId").attr("readonly","true");
		$("#showText").text("保 存");
	}else{
		$("#boardroomId").attr("disabled","disabled");
		$("#boardroomId").val("系统自动生成");
		$("#showText").text("保 存");
	}
	$("#aiscboardroomForm").validate({

		// 非空校验
		rules : {
			boardroomName : {
				required : true,
			},
			maxusercount : {
				required : true,
				number: true,
			},
			enablechairpwd : {
				isSelect : true,
			},
			verifymode:{
				checkSelectVal:true,
			}
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
		$("#aiscboardroomForm").submit();
	});
});
function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function changeType(obj){
	if($(obj).val()=="2"){
		$("#title1").show();
		 $("#password").rules("add", { required: true, messages: { required: "必填字段"} });
	}else{
		$("#title1").hide();
		$("#password").rules("add", { required: false, messages: { required: "必填字段"} });
	}
}

function changeType1(obj){
	if($(obj).val()=="1"){
		$("#title2").show();
		$("#chairpassword").rules("add", { required: true, messages: { required: "必填字段"} });
	}else{
		$("#title2").hide();
		$("#chairpassword").rules("add", { required: false, messages: { required: "必填字段"} });
	}
}

function save() {
	    $("#saveBtn").addClass('disabled');
		//提交
	    var data = $("#aiscboardroomForm").serializeObject();
	    data = $.toJSON(data);
//	    alert(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveBoardroom",
	        dataType: 'json',
	        contentType: 'application/json',
	        data: data,
	        success: function (data) {
	            if (data.roomId.split("||")[0] != "-1") {
	            	layer.msg('操作成功', {icon: 1});
	            	if(data.roomId.split("||")[1]=="0"){
	            		//同步用户数据
		            	sysBoartUser(data.roomId.split("||")[0]);
	            	}
	                parent.reloadGrid();
	                setTimeout(closeDiv,1500);
	            }else {
	                layer.msg('新增失败', {icon: 1});
	            }
	        }
	    });
}

function sysBoartUser(roomId){
	$.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/basecode/sysBoartUser?roomId="+roomId,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
           
        }
    });
}
