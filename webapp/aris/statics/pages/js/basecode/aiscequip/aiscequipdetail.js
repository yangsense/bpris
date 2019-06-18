$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    });
	initDict();
	initSubCate();
    var id = $("#equipmentId").val()==''?0:$("#equipmentId").val();
    
	var type = $("#aiscEquipForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscEquipForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#equipmentId").attr("readonly","true");
		$("#showText").text("保 存");
	}else{
		$("#equipmentId").attr("disabled","disabled");
		$("#equipmentId").val("系统自动生成");
		$("#showText").text("保 存");
	}
	
	
	$("#modalityEnabled").val($("#aiscEquipForm").attr("param1")==""?"1":$("#aiscEquipForm").attr("param1"));
	$("#modalityEnabled").select2();
	$("#modalityWorklist").val($("#aiscEquipForm").attr("param2")==""?"1":$("#aiscEquipForm").attr("param2"));
	$("#modalityWorklist").select2();
	$("#modalitySupportchinese").val($("#aiscEquipForm").attr("param3")==""?"1":$("#aiscEquipForm").attr("param3"));
	$("#modalitySupportchinese").select2();
	$("#modalitySupportsexage").val($("#aiscEquipForm").attr("param4")==""?"1":$("#aiscEquipForm").attr("param4"));
	$("#modalitySupportsexage").select2();
	$("#modalitySupportid").val($("#aiscEquipForm").attr("param5")==""?"1":$("#aiscEquipForm").attr("param5"));
	$("#modalitySupportid").select2();
	
	$("#aiscEquipForm").validate({

		// 非空校验
		rules : {
			locId:{
				isSelect:true
			},
			modalityId:{
				isSelect:true
			},
			roomId:{
				isSelect:true
			},
			ordsubcategoryId:{
				isSelect:true
			},
			equipmentCode : {
				required : true,
				remote:GLOBAL.WEBROOT + '/basecode/checkEquipmentCode.ajax?equipmentId='+id+"&orgId="+$("#orgId").val()
			},
			equipmentStartdate:{
				required : true
			},
			equipmentDesc:{
				required : true
			}

		},
		errorElement:'em',
		messages: {
			equipmentCode:{
        		required:'必填字段',
        		remote:'已存在'
        	}
		},
		submitHandler : save
	});
	$("#saveBtn").click(function(){
		$("#aiscEquipForm").submit();
	});


});

function initSubCate() {
    //检查子类下拉
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdSubCate/getAiscOrdSubCategory.ajax?orgId="+$("#orgId").val() ,
        success: function (data) {
            var jsonOrdSubCategory = eval(data.AiscOrdSubCategory);
            var ordSubCategorySelect = $("#ordsubcategoryId");
            ordSubCategorySelect.empty();
            ordSubCategorySelect.append("<option value='-1'>请选择子类</option>");
            for (var i = 0; i < jsonOrdSubCategory.length; i++) {
            	ordSubCategorySelect.append("<option value='" + jsonOrdSubCategory[i].ordsubcategoryid + "'>" + jsonOrdSubCategory[i].ordsubcategorydesc + "</option>");
            }
            ordSubCategorySelect.val($("#aiscEquipForm").attr("old_sub_cate")==""?"-1":$("#aiscEquipForm").attr("old_sub_cate"));
            ordSubCategorySelect.select2();
        }
    });
}

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
            Equip_TypeSelect.val($("#aiscEquipForm").attr("old_sb_type")==""?"-1":$("#aiscEquipForm").attr("old_sb_type"));
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
	        if(sysOrg.length>0){
	       		orgSelect.val($("#aiscEquipForm").attr("old_org_id")==""?parent.parent.$("#currentOrgId").val():$("#aiscEquipForm").attr("old_org_id"));
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
            	loc_nameSelect.val($("#aiscEquipForm").attr("old_loc_id")==""?"-1":$("#aiscEquipForm").attr("old_loc_id"));
            }
            loc_nameSelect.select2();
  			loadRoom();
        }
    });
    
}

function loadRoom(){
	var mark = false;
	//房间列表加载
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getRoomSelect.ajax?orgId="+$("#orgId").val()+"&locId="+$("#locId").val(),
        success: function (data) {
            var jsonRoomData = eval(data.RoomName);
            var roomIdSelect = $("#roomId");
            roomIdSelect.empty();
            roomIdSelect.append("<option value='-1'>请选择房间</option>");
            for (var i = 0; i < jsonRoomData.length; i++) {
                roomIdSelect.append("<option value='" + jsonRoomData[i].roomid + "'>" + jsonRoomData[i].roomdesc + "</option>");
                if(jsonRoomData[i].roomid==$("#aiscEquipForm").attr("old_room_id")){
                	mark = true;
                }
            }
            if(jsonRoomData.length>0){
            	if(mark){
            		roomIdSelect.val($("#aiscEquipForm").attr("old_room_id")==""?"-1":$("#aiscEquipForm").attr("old_room_id"));
            	}
            }
            roomIdSelect.select2();  
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
	    var data = $("#aiscEquipForm").serializeObject();
//	    data.equipmentId= $("#equipmentId").val();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscEquip",
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
