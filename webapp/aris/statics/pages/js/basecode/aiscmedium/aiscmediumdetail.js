$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    });
    
	initDict();
	
	var type = $("#aiscMediumForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscMediumForm");
		$("#mediumEnabled").val($("#aiscMediumForm").attr("old_mediumEnabled"));
		$("#mediumIsfull").val($("#aiscMediumForm").attr("old_mediumIsfull"));
		$("#mediumIsonline").val($("#aiscMediumForm").attr("old_mediumIsonline"));
		$("#mediumEnabled").select2();
		$("#mediumIsfull").select2();
		$("#mediumIsonline").select2();
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#mediumId").attr("readonly","true");
		$("#mediumEnabled").val($("#aiscMediumForm").attr("old_mediumEnabled"));
		$("#mediumIsfull").val($("#aiscMediumForm").attr("old_mediumIsfull"));
		$("#mediumIsonline").val($("#aiscMediumForm").attr("old_mediumIsonline"));
		$("#mediumEnabled").select2();
		$("#mediumIsfull").select2();
		$("#mediumIsonline").select2()
		$("#showText").text("保 存");
	}else{
		com.ai.bdx.util.reset("aiscMediumForm");
		$("#mediumId").attr("disabled","disabled");
		$("#mediumId").val("系统自动生成");
		$("#mediumEnabled").val("1");
		$("#mediumIsfull").val("1");
		$("#mediumIsonline").val("1");
		$("#mediumEnabled").select2();
		$("#mediumIsfull").select2();
		$("#mediumIsonline").select2()
		$("#showText").text("保 存");
	}


	$("#aiscMediumForm").validate({

		// 非空校验
		rules : {
			mediumName : {
				required : true
			},
			mediumPath:{
				required:true
			},
			mediumSize:{
				required:true,
				digits:true
			},
			mediumReminesize:{
				required:true,
				digits:true
			},
			mediumType:{
				isSelect:true
			},
			serverId:{
				isSelect:true
			}


		},
		errorElement:'em',
		messages: {
		/*	orgId: '非空',
			locDesc:'非空',
			ruleStartindex: '非空',
			ruleLen:'必须输入数字',*/
		},
		submitHandler : save
	});


	$("#saveBtn").click(function(){
		$("#aiscMediumForm").submit();
	});

});

//initial query dict
function initDict() {
	
    //介质类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscMediumType.ajax",
        success: function (data) {
            var jsonAiscMediumType = eval(data.AiscMediumType);
            var Medium_TypeSelect = $("#mediumType");
            Medium_TypeSelect.empty();
            Medium_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscMediumType.length; i++) {
                Medium_TypeSelect.append("<option value='" + jsonAiscMediumType[i].itemno + "'>" + jsonAiscMediumType[i].itemname + "</option>");
            }
            Medium_TypeSelect.val($("#aiscMediumForm").attr("old_medium_type")==""?"-1":$("#aiscMediumForm").attr("old_medium_type"));
            Medium_TypeSelect.select2();
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
            serverSelect.val($("#aiscMediumForm").attr("old_server")==""?"-1":$("#aiscMediumForm").attr("old_server"));
            serverSelect.select2();  
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
	    var data = $("#aiscMediumForm").serializeObject();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscMediumInfo",
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
