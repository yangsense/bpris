$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    });
    
	var type = $("#aiscPeriodForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscReportForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#showText").text("保 存");
	}else{
		$("#showText").text("保 存");
	}

	$("#aiscPeriodForm").validate({

		// 非空校验
		rules : {
			periodDesc : {
				required:true
			},
			periodStarttime:{
				required:true
			},
			periodEndtime:{
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
		$("#aiscPeriodForm").submit();
	});
});

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
	    var data = $("#aiscPeriodForm").serializeObject();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscPeriod.ajax",
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
