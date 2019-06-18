$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    });
    initDict();
    
    var id = $("#format2locId").val()==''?0:$("#format2locId").val();
    
	var type = $("#aiscReportForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscReportForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#showText").text("保 存");
	}else{
		$("#showText").text("保 存");
	}

	$("#aiscReportForm").validate({

		// 非空校验
		rules : {
			locId : {
				isSelect:true
			},
			formatName:{
				required:true
			},
			orgId:{
				isSelect:true
			},
            modalityId:{
            	checkSelectVal:true
            },
            modelType:{
            	checkSelectVal:true
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
//		if($("#aiscReportForm").attr("dataType")=="add"){
//			
//				$("#aiscReportForm").submit();
//			}else {
//				layer.msg('机构、科室、设备大类和模板类型须保持唯一，请重新选择！', {icon: 2});
//
//			}
//		}else{
			$("#aiscReportForm").submit();
//		}
		

	});
});


function checkAdd() {
	//校验新增数据是否重复
	var  flag= false ;
	var orgId = $("#orgId").val();
	var locId = $("#locId").val();
	var modalityId = $("#modalityId").val();
	var modelType = $("#modelType").val();
	var format2locId = $("#format2locId").val();
	
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/checkAiscReportFormat2Loc.ajax?orgId='+orgId+"&locId="+locId+"&modalityId="+modalityId+"&modelType="+modelType+"&format2locId="+format2locId,
		dataType:'json',
		success:function(data){
			if(data.result=="true"){
				flag=true ;
			}
		}
	});
	return flag ;
}

//initial query dict
function initDict() {
	
    //设备类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscEquipType.ajax",
        success: function (data) {
            var jsonAiscEquipType = eval(data.AiscEquipType);
            var Equip_TypeSelect = $("#modalityId");
            Equip_TypeSelect.empty();
            Equip_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscEquipType.length; i++) {
                Equip_TypeSelect.append("<option value='" + jsonAiscEquipType[i].itemno + "'>" + jsonAiscEquipType[i].itemname + "</option>");
            }
            Equip_TypeSelect.val(($("#aiscReportForm").attr("old_modalityId")==0||$("#aiscReportForm").attr("old_modalityId")=="")?"-1":$("#aiscReportForm").attr("old_modalityId"));
            Equip_TypeSelect.select2();
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
	        if(sysOrg.length){
	        	orgSelect.val($("#aiscReportForm").attr("old_org_id")==""?parent.parent.$("#currentOrgId").val():$("#aiscReportForm").attr("old_org_id"));
	        }
	        orgSelect.select2();  
		}
	}); 
    
  //模板类型加载
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscPrintType.ajax",
        success: function (data) {
            var jsonAiscModelType = eval(data.AiscModelType);
            var model_TypeSelect = $("#modelType");
            model_TypeSelect.empty();
            model_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscModelType.length; i++) {
            	model_TypeSelect.append("<option value='" + jsonAiscModelType[i].itemno + "'>" + jsonAiscModelType[i].itemname + "</option>");
            }
            model_TypeSelect.val(($("#aiscReportForm").attr("old_modelType")==0||$("#aiscReportForm").attr("old_modelType")=="")?"-1":$("#aiscReportForm").attr("old_modelType"));
            model_TypeSelect.select2();
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
            if(jsonaiscLocName.length){
            	loc_nameSelect.val($("#aiscReportForm").attr("old_loc_id")==""?"-1":$("#aiscReportForm").attr("old_loc_id"));
            }
            loc_nameSelect.select2();
        }
    });
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
		if (checkAdd()){
			layer.msg('机构、科室、设备大类和模板类型须保持唯一，请重新选择！', {icon: 2});
			return ;
		}
	
		//提交
	    var eEditor = CKEDITOR.instances.reportFormat;   
	    if(eEditor.getData()==""){ 
	    	layer.msg('请填写模板内容', {icon: 5});  
	    	return false;
	    }    
	    
//	    var data = {"templatecatId":$("#templatecatId").val(),"templatedirId":$("#templatedirId").val(),"templateName":$("#templateName").val(),
//	    	templateMethod:$("#templateMethod").val(),"templateExam":eEditor.getData(),"templateResult":rEditor.getData(),templatecontentId:$("#templatecontentId").val()};
//	
	    var data = $("#aiscReportForm").serializeObject();
	    data.reportFormat=eEditor.getData();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscReport",
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
