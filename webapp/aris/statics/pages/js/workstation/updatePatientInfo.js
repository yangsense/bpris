$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    }); 
    
	//只读
	$("#patientId").attr("readonly","true");
//	$("#patientNamepy").attr("readonly","true");
	$("#patientSex").select2();
	$("#patientAgeUnit").select2();
	
	$("#saveBtn").click(function(){
		save();
	})
	
	if($("#patientAgeOld").val().substr($("#patientAgeOld").val().length-2)=="小时"){
		$("#patientAgeUnit").val("4");
		$("#patientAgeUnit").select2();
		$("#patientAgeShow").val($("#patientAgeOld").val().substr(0,$("#patientAgeOld").val().length-2));
		$("#patientAge").val($("#patientAgeShow").val()+$('#patientAgeUnit option:selected').val());
	}else if($("#patientAgeOld").val().substr($("#patientAgeOld").val().length-1)=="天"){
		$("#patientAgeUnit").val("3");
		$("#patientAgeUnit").select2();
	}else if($("#patientAgeOld").val().substr($("#patientAgeOld").val().length-1)=="月"){
		$("#patientAgeUnit").val("2");
		$("#patientAgeUnit").select2();
	}else if($("#patientAgeOld").val().substr($("#patientAgeOld").val().length-1)=="岁"){
		$("#patientAgeUnit").val("1");
		$("#patientAgeUnit").select2();
	}
});


function save() {
	
	if($("#patientName").val()==""){
		layer.msg('姓名不能为空', {icon: 5});
	}else if($("#patientSex").val()=="-1"){
		layer.msg('请选择性别', {icon: 5});
	}else if($("#patientAgeShow").val()==""){
		layer.msg('年龄不能为空', {icon: 5});
	}else if($("#patientDob").val()==""){
		layer.msg('操请选择生日', {icon: 5});
	}else{
		//提交
		var data = {patientGlobalid:parent.$("#patientGlobalid").val(),patientName:$("#patientName").val(),patientSex:$("#patientSex").val(),patientAge:$("#patientAgeShow").val()+$("#patientAgeUnit").find("option:selected").text(),patientDob:$("#patientDob").val(),
				patientPhone:$("#patientPhone").val(),patientMobile:$("#patientMobile").val(),patientMedicareid:$("#patientMedicareid").val(),patientCardid:$("#patientCardid").val(),
				patientIdnumber:$("#patientIdnumber").val(),patientAddress:$("#patientAddress").val()}
		data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: false,
	        url: GLOBAL.WEBROOT + "/studyReport/updatePatient.ajax",
	        dataType: 'json',
	        contentType: 'application/json',
	        data: data,
	        success: function (data) {
	            if (data.ERRCODE == "0") {
	            	layer.msg('修改成功', {icon: 1});
	            	parent.location.reload();
	                setTimeout(closeDiv,1000);
	            }
	            else {
	                layer.msg('修改失败', {icon: 1});
	            }
	        }
	    });
	}
}

 
function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

 