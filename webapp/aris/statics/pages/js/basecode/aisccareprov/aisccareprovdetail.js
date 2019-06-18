$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    });
	initDict();
    
	var id = $("#careprovId").val()==''?0:$("#careprovId").val();
	
	var type = $("#aiscCareProvForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscCareProvForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#careprovId").attr("readonly","true");
		$("#showText").text("保 存");
	}else{
		$("#careprovId").attr("disabled","disabled");
		$("#careprovId").val("系统自动生成");
		$("#showText").text("保 存");
	}
	
	$("#careprovCode_label").hide();

	$("#aiscCareProvForm").validate({

		// 非空校验
		rules : {
			careprovCode : {
				required : true,
				remote:GLOBAL.WEBROOT + '/basecode/checkCareProvCode.ajax?careprovId='+id+"&orgId="+$("#orgId").val()
			},
			careprovTypeid:{
				isSelect : true
			},
			careprovName:{
				required:true
			},
			locId:{
				isSelect : true
			},
			orgId:{
				isSelect : true
			}

		},
		errorElement:'em',
		messages: {
			careprovCode:{
        		required:'必填字段',
        		remote:'已存在'
        	}
		},
		submitHandler : save
	});


	$("#saveBtn").click(function(){
		$("#aiscCareProvForm").submit();
	});
});






function initDict() {
    //人员类别
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscCareProvType.ajax",
        success: function (data) {
            var jsonAiscCareProvType = eval(data.AiscCareProvType);
            var careprov_TypeSelect = $("#careProvTypeid");
            careprov_TypeSelect.empty();
            careprov_TypeSelect.append("<option value='-1'>请选择类别</option>");
            for (var i = 0; i < jsonAiscCareProvType.length; i++) {
                careprov_TypeSelect.append("<option value='" + jsonAiscCareProvType[i].itemno + "'>" + jsonAiscCareProvType[i].itemname + "</option>");
            }
            careprov_TypeSelect.val($("#aiscCareProvForm").attr("careprov_old_type")==""?"-1":$("#aiscCareProvForm").attr("careprov_old_type"));
            careprov_TypeSelect.select2();
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
	        if(sysOrg.length>0){
	        	orgSelect.val($("#aiscCareProvForm").attr("old_org_id")==""?parent.parent.$("#currentOrgId").val():$("#aiscCareProvForm").attr("old_org_id"));
	        }
	        orgSelect.select2();  
		}
	}); 
    
    loadLoc();
    
}

function loadLoc(){
	//科室名称
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscLocName.ajax?orgId="+$("#orgId").val(),
        success: function (data) {
            var jsonaiscLocName = eval(data.AiscLocName);
            var loc_nameSelect = $("#locId");
            loc_nameSelect.empty();
            loc_nameSelect.append("<option value='-1'>--请选择--</option>");
            for (var i = 0; i < jsonaiscLocName.length; i++) {
                loc_nameSelect.append("<option value='" + jsonaiscLocName[i].locid + "'>" + jsonaiscLocName[i].locdesc + "</option>");
            }
            if(jsonaiscLocName.length>0){
            	loc_nameSelect.val($("#aiscCareProvForm").attr("old_loc_id")==""?"-1":$("#aiscCareProvForm").attr("old_loc_id"));	
            }
            loc_nameSelect.select2();
        }
    });
}



//检查当前的CODE码是否存在 itemmast_code  
function checkCareprovCode(){
	var careprovCode = $("#careprovCode").val();
	if(null!=careprovCode&&""!=careprovCode){
		$.ajax({
	        type: "POST",
	        async: false,
	        url: GLOBAL.WEBROOT + "/basecode/checkCareprovCode.ajax?careprovCode="+careprovCode,
	        dataType: 'json',
//	        data: ordcategoryCode,
	        success: function (data) {
	        	 if (data.ERRCODE == "0") {
	                   //输入合法
	        		  $("#careprovCode_label").hide();
	                }
	                else {
	                	//输入非法
	                   $("#careprovCode_label").show();
	                }
	        }
	    });
	}
}


function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
		//验证
	
		//提交
	    var data = $("#aiscCareProvForm").serializeObject();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscCareProv",
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
