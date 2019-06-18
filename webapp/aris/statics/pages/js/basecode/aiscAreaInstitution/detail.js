$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    });
    
	// initDict();
	
	var type = $("#aiscServerForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscServerForm");
		$("#serverEnabled").val($("#aiscServerForm").attr("old_serverEnabled"));
		$("#serverEnabled").select2();
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#serverId").attr("readonly","true");
		$("#serverEnabled").val($("#aiscServerForm").attr("old_serverEnabled"));
		$("#serverEnabled").select2();
		$("#showText").text("保 存");
	}else{
		com.ai.bdx.util.reset("aiscServerForm");
		$("#serverId").attr("disabled","disabled");
		$("#serverId").val("系统自动生成");
		$("#serverEnabled").val("1");
		$("#serverEnabled").select2();
		$("#showText").text("保 存");
	}

	$("#aiscServerForm").validate({

		// 非空校验
		rules : {
			// serverName : {
			// 	required : true
			// },
			// serverIp:{
			// 	required:true
			// },
			// serverPort:{
			// 	required : true,
			// 	digits:true
			// },
			// serverType:{
			// 	required : true,
			// 	isSelect:true
			// }

		},
		errorElement:'em',
		messages: {
		/*	locCode: '非空',
			locDesc:'非空',
			serverPort:'必须输入数字'*/
		},
		submitHandler : save
	});

	$("#saveBtn").click(function(){
		$("#aiscServerForm").submit();
    });
});

//initial query dict
function initDict() {
	
    //服务器类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscServerType.ajax",
        success: function (data) {
            var jsonAiscServerType = eval(data.AiscServerType);
            var Server_TypeSelect = $("#serverType");
            Server_TypeSelect.empty();
            Server_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscServerType.length; i++) {
                Server_TypeSelect.append("<option value='" + jsonAiscServerType[i].itemno + "'>" + jsonAiscServerType[i].itemname + "</option>");
            }
            Server_TypeSelect.val($("#aiscServerForm").attr("old_server_type")==""?"-1":$("#aiscServerForm").attr("old_server_type"));
            Server_TypeSelect.select2();
        }
    });
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
		//验证
	
		//提交
	    var data = $("#aiscServerForm").serializeObject();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/areaInstitution/saveBeanInfo",
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
