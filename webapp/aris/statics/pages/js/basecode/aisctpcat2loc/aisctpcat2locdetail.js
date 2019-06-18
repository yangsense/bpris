$(function () {
	var option = {
        dataStructure: {
            idKey: "orgId",
            pIdKey: "parentOrgId",
            rootPId: parent.parent.rootOrgId,
            name: "orgName"
        },
        data: {
            url: GLOBAL.WEBROOT + "/basecode/getOrgs",    //获取数据的URL
            param: ["orgId"]  //获取数据的参数
        },
        rootNode: [{orgId: parent.parent.rootOrgId, orgName: '请选择', open: true, isParent: true, nocheck: true}]
    };
	$("select").select2();
	com.ai.bdx.util.ztreeComp("orgName", false, option, callFuncForOrgNames);
	
	$("#closeBtn").click(function(){
       closeDiv();
    });
    
	var type = $("#aiscTpcatLocForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscTpcatLocForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#tplcat2locId").attr("readonly","true");
		$("#showText").text("修 改");
	}else{
		com.ai.bdx.util.reset("aiscTpcatLocForm");
		$("#tplcat2locId").attr("disabled","disabled");
		$("#tplcat2locId").val("系统自动生成");
		$("#showText").text("新 增");
	}



	$("#aiscTpcatLocForm").validate({

		// 非空校验
		rules : {
			modalityId : {
				required : true,
				number:true
			},
			locId:{
				required : true,
				number:true
			},
			operatorId:{
				required:true,
				number:true
			},
			templatecatId:{
				required:true,
				number:true
			},
			orgId:{
				required:true
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
		$("#aiscTpcatLocForm").submit();
	});

});

function callFuncForOrgNames(zTree) {
    $('#orgId').val(zTree.orgId);
    $('#orgName').val(zTree.orgName);
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
		//验证
	
		//提交
	    var data = $("#aiscTpcatLocForm").serializeObject();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscTpcat2Loc",
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
