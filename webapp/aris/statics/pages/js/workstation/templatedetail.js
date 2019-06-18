$(function () {
	
	$("#closeBtn").click(function(){
       closeDiv();
    });
	
	var type = $("#templateDetaiForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("templateDetaiForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#showText").text("修 改");
	}else{
		$("#showText").text("新 增");
	}

	$("#templateDetaiForm").validate({

		// 非空校验
		rules : {
			templatedirId : {
				required : true
			},
			templatecatId:{
				required:true
			}

		},
		errorElement:'em',
		messages: {
		/*	locCode: '非空',
			locDesc:'非空'*/
		},
		submitHandler : save
	});

	$("#saveBtn").click(function(){
		$("#templateDetaiForm").submit();
	});
	
});

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
		//提交
//	    var data = $("#templateDetaiForm").serializeObject();
//	    data = $.toJSON(data);
//	    alert(data);
	    var eEditor = CKEDITOR.instances.templateExam;   
	    if(eEditor.getData()==""){ 
	    	layer.msg('请填写检查所见', {icon: 5});  
	    	return false;
	    }    
	    var rEditor = CKEDITOR.instances.templateResult; 
	    if(rEditor.getData()==""){ 
	    	layer.msg('请填写诊断意见', {icon: 5});  
	    	return false;
	    }    
	    
	    var data = {"templatedirId":$("#templateDetaiForm").attr("templatedirId"),"templateName":$("#templateName").val(),
	    	templateMethod:$("#templateMethod").val(),"templateExam":eEditor.getData(),"templateResult":rEditor.getData(),templatecontentId:$("#templatecontentId").val()};
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/studyReport/saveTemplateDetail",
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
