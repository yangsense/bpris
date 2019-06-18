$(function () {
	$("select").select2();
	
	$("#closeBtn").click(function(){
       closeDiv();
    });
    
	initDict();
	
	var type = $("#aiscRuleForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscRuleForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#ruleId").attr("readonly","true");
		$("#showText").text("保 存");
	}else{
		$("#ruleId").attr("disabled","disabled");
		$("#ruleId").val("系统自动生成");
		$("#showText").text("保 存");
	}
	$("#aiscRuleForm").validate({

		// 非空校验
		rules : {
			orgId : {
				required : true
			},
//			locId:{
//				required : true
//			},
			ruleStartindex:{
				required:true
			},
			ruleField:{
				required:true
			},
			ruleLen:{
				required:true,
				digits:true
			},
			ruleType:{
				isSelect:true
			},
			ruleField:{
				publicSelectVal:true
			},
//			locId:{
//				publicSelectVal:true
//			},


		},
		errorElement:'em',
		messages: {
			/*orgId: '非空',
			locDesc:'非空',
			ruleStartindex: '非空',
			ruleLen:'必须输入数字',*/
		},
		submitHandler : save
	});


	$("#saveBtn").click(function(){
		$("#aiscRuleForm").submit();
    });
});

//initial query dict
function initDict() {
	
    //生成方式
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscRuleType.ajax",
        success: function (data) {
            var jsonAiscRuleType = eval(data.AiscRuleType);
            var Rule_TypeSelect = $("#ruleType");
            Rule_TypeSelect.empty();
            Rule_TypeSelect.append("<option value='-1'>--请选择类型--</option>");
            for (var i = 0; i < jsonAiscRuleType.length; i++) {
                Rule_TypeSelect.append("<option value='" + jsonAiscRuleType[i].itemno + "'>" + jsonAiscRuleType[i].itemname + "</option>");
            }
            Rule_TypeSelect.val($("#aiscRuleForm").attr("old_rule_type")==""?"-1":$("#aiscRuleForm").attr("old_rule_type"));
            Rule_TypeSelect.select2();
        }
    });
    
    
    //规则字段
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscRuleField.ajax",
        success: function (data) {
            var jsonAiscRuleField = eval(data.AiscRuleField);
            var Rule_FieldSelect = $("#ruleField");
            Rule_FieldSelect.empty();
            Rule_FieldSelect.append("<option value='-1'>--请选择类型--</option>");
            for (var i = 0; i < jsonAiscRuleField.length; i++) {
                Rule_FieldSelect.append("<option value='" + jsonAiscRuleField[i].itemno + "'>" + jsonAiscRuleField[i].itemname + "</option>");
            }
            Rule_FieldSelect.val($("#aiscRuleForm").attr("old_rule_Field")==""?"-1":$("#aiscRuleForm").attr("old_rule_Field"));
            Rule_FieldSelect.select2();
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
	        	orgSelect.val($("#aiscRuleForm").attr("old_org_id")==""?parent.parent.$("#currentOrgId").val():$("#aiscRuleForm").attr("old_org_id"));
	        }
	        orgSelect.select2();  
	        loadLoc();
		}
	}); 
    
    
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
            	loc_nameSelect.val(($("#aiscRuleForm").attr("old_loc_id")=="0"||$("#aiscRuleForm").attr("old_loc_id")=="")?"-1":$("#aiscRuleForm").attr("old_loc_id"));	
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
		//验证
	
		//提交
	    var data = $("#aiscRuleForm").serializeObject();
	    data.locId = $("#locId").val();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscRule",
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
