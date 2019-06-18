$(function () {

	$("#closeBtn").click(function(){
       closeDiv();
    });
	$("#saveBtn").click(function(){
		save();
	});

	//$("#saveBtn").hide();
//	var type = $("#aiscBodyPartItemForm").attr("dataType");
//	if(type=="query"){
//		com.ai.bdx.util.disableAll("aiscBodyPartItemForm");
//		$("#saveBtn").hide();
//	}else if(type=="update"){
//		$("#showText").text("修 改");
//	}else{
//		com.ai.bdx.util.reset("aiscBodyPartItemForm");
//		$("#showText").text("新 增");
//	}


	initCate() ; //初始化检查大类
	initSubCate() ;  //初始化检查子类
	postloaddata("selectItem") ; //初始化部位列表
	//loadBodypart("selectItem",0);
});

function initCate() {

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
	        orgSelect.val($("#searchForm").attr("old_orgId")==""?parent.parent.$("#currentOrgId").val():$("#searchForm").attr("old_orgId"));
	        orgSelect.select2();  
		}
	}); 
    
    
  //部位大类加载
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getBodyparType.ajax?orgId='+$("#orgId").val(),
		dataType:'json',
		success:function(data){
			var bodyparType = eval(data.BodyparType);
	        var pidSelect = $("#bodypartPid");
	        pidSelect.multiselect2side('destroy');
	        pidSelect.empty();
	        pidSelect.append("<option value='-1'>--请选择--</option>");
	        for (var i = 0; i < bodyparType.length; i++) {
	        	pidSelect.append("<option value='" + bodyparType[i].bodypartcode + "'>" + bodyparType[i].bodypartdesc + "</option>");
	        }
	        pidSelect.select2();
		}
	}); 
//	    
    //检查大类下拉
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdCate/getAiscOrdCategory.ajax?orgId="+$("#orgId").val(),
        success: function (data) {
            var jsonOrdCategory = eval(data.AiscOrdCategoryName);
            var ordCategorySelect = $("#ordcategoryId");
            ordCategorySelect.empty();
            ordCategorySelect.append("<option value='-1'>请选择大类</option>");
            for (var i = 0; i < jsonOrdCategory.length; i++) {
            	ordCategorySelect.append("<option value='" + jsonOrdCategory[i].ordcategoryid + "'>" + jsonOrdCategory[i].ordcategorydesc + "</option>");
            }
            ordCategorySelect.select2();
        }
    });
}
function initSubCate() {
    //检查子类下拉
    var ordcategoryId = $("#ordcategoryId").val() ;
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdSubCate/getAiscOrdSubCategory.ajax?ordcategoryId="+ordcategoryId+"&orgId="+$("#orgId").val() ,
        success: function (data) {
            var jsonOrdSubCategory = eval(data.AiscOrdSubCategory);
            var ordSubCategorySelect = $("#ordsubcategoryId");
            ordSubCategorySelect.empty();
            ordSubCategorySelect.append("<option value='-1'>请选择子类</option>");
            for (var i = 0; i < jsonOrdSubCategory.length; i++) {
            	ordSubCategorySelect.append("<option value='" + jsonOrdSubCategory[i].ordsubcategoryid + "'>" + jsonOrdSubCategory[i].ordsubcategorydesc + "</option>");
            }
            ordSubCategorySelect.select2();
        }
    });
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function postloaddata(selectType){
	//医嘱大，小类刷选
	var ordcategoryId;
	var ordcategoryId;
	var itemmastId;
	if(selectType=="selectItem"){
		ordsubcategoryId = $("#ordsubcategoryId").val() ;
		ordcategoryId = $("#ordcategoryId").val() ;
        itemmastId = 0;
    //医嘱列表刷选
	}else{
		ordcategoryId = $("#ordcategoryId").val() ;
		ordsubcategoryId = $("#ordsubcategoryId").val() ;
        itemmastId = $("#itemMastList").val();
        //if(itemmastId)
	}
	var orgId = $("#orgId").val() ;
	$.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getBodyPartList.ajax?ordcategoryId="+ordcategoryId+"&ordsubcategoryId="+ordsubcategoryId+"&itemmastId="+itemmastId+"&orgId="+orgId+"&bodypartPid="+$("#bodypartPid").val(),
        success: function (data) {
			//医嘱大，小类刷选
			if(selectType=="selectItem"){
					var jsonItemMastlist = eval(data.itemMastList);
				    var operSelect = $("#itemMastList");
				    operSelect.multiselect2side('destroy');
				    operSelect.empty();
				    for (var i = 0; i < jsonItemMastlist.length; i++) {
				        operSelect.append("<option value='" + jsonItemMastlist[i].itemmastid+"' onclick=postloadBodypart()>" +jsonItemMastlist[i].itemmastdesc + "</option>");
				    }
			}
			
			//加载未选部位列表
//		    var jsonPartlist = eval(data.bodyPartUnSelect);
		    var partUnSelect = $("#bodyPartList");
		    partUnSelect.multiselect2side('destroy');
		    partUnSelect.empty();
//		    for (var j = 0; j < jsonPartlist.length; j++) {
//		    	var pidName = jsonPartlist[j].bodypartpidname==undefined?"":jsonPartlist[j].bodypartpidname;
//		    	partUnSelect.append("<option value='" + jsonPartlist[j].bodypartcode + "'>"+j+"||" +jsonPartlist[j].bodypartdesc + "——"+pidName+"</option>");
//		    }
		    $('#bodyPartList').multiselect2side({
			   //search: "<img src='img/search.gif' />" //Search: 
		    	
			});
		    //加载已选部位列表
//		    var jsonPartSelected = eval(data.bodyPartSelected);
		    var partSelected = $("#searchableJoinms2side__dx");
		    partSelected.multiselect2side('destroy');
		    partSelected.empty();
//		    for (var k = 0; k < jsonPartSelected.length; k++) {
//		    	var pidName = jsonPartSelected[k].bodypartpidname==undefined?"":jsonPartSelected[k].bodypartpidname;
//		    	partSelected.append("<option value='" + jsonPartSelected[k].bodypartcode + "'>"+k+"||" +jsonPartSelected[k].bodypartdesc + "——"+pidName+"</option>");
//		    }
			//加载未选大部位列表
//			var bodyparType = eval(data.bodyPartUnBigSelect);
//	        var pidSelect = $("#bigbodyList");
//	        pidSelect.multiselect2side('destroy');
//	        pidSelect.empty();
//	        for (var i = 0; i < bodyparType.length; i++) {
//	        	pidSelect.append("<option value='" + bodyparType[i].bodypartcode + "' >"+i+"||" + bodyparType[i].bodypartdesc + "</option>");
//	        }
//	        $('#bigbodyList').multiselect2side({
//				   //search: "<img src='img/search.gif' />" //Search: 
//			    	
//			});
//	      //加载已选大部位列表
//	        var jsonbigSelected = eval(data.bodyPartBigSelect);
//		    var pidUnSelect = $("#searchableBigBodyms2side__dx");
//		    pidUnSelect.multiselect2side('destroy');
//		    pidUnSelect.empty();
//		    for (var c = 0; c < jsonbigSelected.length; c++) {
//		    	pidUnSelect.append("<option value='" + jsonbigSelected[c].bodypartcode + "'>"+c+"||" +jsonbigSelected[c].bodypartdesc + "</option>");
//		    }
//	        
//		    $(".ms2side__header").eq(1).text("大类部位列表");
//			$(".ms2side__header").eq(2).html("已选大类部位列表");
        }
    });
}

function postloadBodypart(str){
	var ordcategoryId = $("#ordcategoryId").val() ;
	var ordsubcategoryId = $("#ordsubcategoryId").val() ;
	var itemmastId = str;
	var orgId = $("#orgId").val() ;
	$.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getBodyPartDetailList.ajax?ordcategoryId="+ordcategoryId+"&ordsubcategoryId="+ordsubcategoryId+"&itemmastId="+itemmastId+"&orgId="+orgId+"&bodypartPid="+$("#bodypartPid").val(),
        success: function (data) {
        	var jsonPartlist = eval(data.bodyPartUnSelect);
		    var partUnSelect = $("#bodyPartList");
		    partUnSelect.multiselect2side('destroy');
		    partUnSelect.empty();
		    for (var j = 0; j < jsonPartlist.length; j++) {
		    	var pidName = jsonPartlist[j].bodypartpidname==undefined?"":jsonPartlist[j].bodypartpidname;
		    	partUnSelect.append("<option value='" + jsonPartlist[j].bodypartcode + "'>"+j+"||" +jsonPartlist[j].bodypartdesc + "——"+pidName+"</option>");
		    }
		    $('#bodyPartList').multiselect2side({
			   //search: "<img src='img/search.gif' />" //Search: 
		    	
			});
		    //加载已选部位列表
		    var jsonPartSelected = eval(data.bodyPartSelected);
		    var partSelected = $("#searchableJoinms2side__dx");
		    partSelected.multiselect2side('destroy');
		    partSelected.empty();
		    for (var k = 0; k < jsonPartSelected.length; k++) {
		    	var pidName = jsonPartSelected[k].bodypartpidname==undefined?"":jsonPartSelected[k].bodypartpidname;
		    	partSelected.append("<option value='" + jsonPartSelected[k].bodypartcode + "'>"+k+"||" +jsonPartSelected[k].bodypartdesc + "——"+pidName+"</option>");
		    }
        }
        	
    });
}

//function loadBodypart(selectType,pid){
//	var orgId = $("#orgId").val() ;
//	var ordcategoryId;
//	var ordcategoryId;
//	var itemmastId;
//	if(selectType=="selectItem"){
//		ordsubcategoryId = $("#ordsubcategoryId").val() ;
//		ordcategoryId = $("#ordcategoryId").val() ;
//        itemmastId = 0;
//    //医嘱列表刷选
//	}else{
//		ordcategoryId = $("#ordcategoryId").val() ;
//		ordsubcategoryId = $("#ordsubcategoryId").val() ;
//        itemmastId = $("#itemMastList").val();
//        //if(itemmastId)
//	}
//	$.ajax({
//        type: "POST",
//        url: GLOBAL.WEBROOT +"/basecode/getBodyPartList.ajax?ordcategoryId="+ordcategoryId+"&ordsubcategoryId="+ordsubcategoryId+"&itemmastId="+itemmastId+"&orgId="+orgId+"&bodypartPid="+pid,
//        success: function (data) {
//			
//        }
//    });
//}


function save(){
	//验证
	var  itemmastId = $("#itemMastList").val() ;
    var selectOption = $("#searchableJoinms2side__dx option").map(function(){
    	return $(this).val();
	}).get().join(",");
    var array = selectOption.split(",");
//    var selectBigOption = $("#searchableBigBodyms2side__dx option").map(function(){
//    	return $(this).val();
//	}).get().join(",");
	var orgId = $("#orgId").val() ;		
if($("#itemMastList").val()==null){
		layer.alert('请选择医嘱信息！', {
	    skin: 'layui-layer-molv', //样式类名
	    closeBtn: 0,
	    icon: 5
		}); 
	}else if(selectOption==""||array.length<1){
		layer.alert('请选择部位！', {
	    skin: 'layui-layer-molv', //样式类名
	    closeBtn: 0,
	    icon: 5
		}); 
	}else if(orgId==null){
		layer.alert('请选择机构！', {
		    skin: 'layui-layer-molv', //样式类名
		    closeBtn: 0,
		    icon: 5
			}); 
	}else{
	 //提交
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/basecode/saveAiscBodyPartItem?orgId="+orgId+"&itemmastId="+itemmastId+"&selectOption="+selectOption,
        dataType: 'json',
        contentType: 'application/json',
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
}


