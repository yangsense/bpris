/**
 * 区域下拉框
 * 
 * 注：以下为下拉框的selectType(下拉框类型)
 * 
 * 省 PROVINCE
 * 市 CITY
 * 县 COUNTY
 * 乡 TOWN
 * 村 VILLAGE
 * 
 * @author hejm
 * @since 2015-3-4
 * 
 */
//-------------------------------地址对象 (开始)----------------------------------------------//
var ADD = {
		initFlag:true,
		/**
		 * selectId : 当前下拉框
		 * nextSelectId ：下一个下拉框
		 * currentSelectType:下拉框类型 省 province
		 *  例如： 省 selectId = "province"
		 *       市 nextSelectId = "city"
		 */
		init:function(selectId,nextSelectId,currentSelectType,nextSelectType,fillData){                                                          //初始化
//			alert(fillData.provinceId);
			ADD.fillSelect("",currentSelectType,selectId,fillData);
			//ADD.change(selectId,nextSelectId,nextSelectType);
		},
		/**
		 * currentSelectValue:下拉框选中的值
		 * type: city 市， county 县， town 乡
		 * targetId:目标控件的id，如：city，town，county
		 */
		fillSelect:function(currentSelectValue,selectType,targetId,fillData){                                //填充下拉框
			
			$.ajax({
				  url:GLOBAL.WEBROOT+'/dictItem/getAreaInfo.ajax',
				  type:'post',
				  dataType:'json',
				  data:{"currentSelectValue":currentSelectValue,"type":selectType},
				  success:function(data){
					  if(data!=null){
						  ADD.clear(targetId);
						  for(var i= 0;i<data.length;i++)
						  {
						    $("#"+targetId+"").append("<option value=\""+data[i].itemNo+","+data[i].itemName+"\">"+data[i].itemName+"</option>");
						  } 
						  //-----------回填 （开始）----------------//
						  if(data.length>0 && ADD.initFlag == true){
							  if(data[0].itemLevel=="1" && fillData.provinceValue!=""){
								  $("select#"+fillData.provinceId+" option[value^='"+fillData.provinceValue+"']").prop("selected", 'selected');
                                  $("#"+fillData.provinceId).select2();
                                  ADD.change(fillData.provinceId,fillData.cityId,fillData.cityType,fillData);
							  }else if(fillData.provinceValue==""){
                                  $("#"+fillData.provinceId+" option:first").prop("selected", 'selected');
                                  //$("select#"+fillData.provinceId+" option[value^='"+"-1"+"']").prop("selected", 'selected');
                                  //$("#"+fillData.provinceId).select2();
                              }
							  if(data[0].itemLevel=="2" && fillData.cityValue!=""){
								  $("select#"+fillData.cityId+" option[value^='"+fillData.cityValue+"']").prop("selected", 'selected');
                                  $("#"+fillData.cityId).select2();
                                  ADD.change(fillData.cityId,fillData.countyId,fillData.countyType,fillData);
							  }else if(fillData.cityValue==""){
                                  $("#"+fillData.cityId+" option:first").prop("selected", 'selected');
                                 // $("select#"+fillData.cityId+" option[value^='"+"-1"+"']").prop("selected", 'selected');
                                  //$("#"+fillData.cityId).select2();
                              }
							  if(data[0].itemLevel=="3" && fillData.countyValue!=""){
								  $("select#"+fillData.countyId+" option[value^='"+fillData.countyValue+"']").prop("selected", 'selected');
                                  $("#"+fillData.countyId).select2();
                                  ADD.change(fillData.countyId,fillData.townId,fillData.townType,fillData);
							  }else if(fillData.countyValue==""){
                                  $("#"+fillData.countyId+" option:first").prop("selected", 'selected');
                                  //$("select#"+fillData.countyId+" option[value^='"+"-1"+"']").prop("selected", 'selected');
                                  //$("#"+fillData.countyId).select2();
                              }
							  if(data[0].itemLevel=="4" && fillData.townValue!=""){
								  $("select#"+fillData.townId+" option[value^='"+fillData.townValue+"']").prop("selected", 'selected');
                                  $("#"+fillData.townId).select2();
                                  ADD.change(fillData.townId,fillData.villageId,fillData.villageType,fillData);
							  }else if(fillData.townValue==""){
                                  $("#"+fillData.townId+" option:first").prop("selected", 'selected');
                                  //$("select#"+fillData.townId+" option[value^='"+"-1"+"']").prop("selected", 'selected');
                                  //$("#"+fillData.townId).select2();
                              }
							  if(data[0].itemLevel=="5" && fillData.villageValue!=""){
								  $("select#"+fillData.villageId+" option[value^='"+fillData.villageValue+"']").prop("selected", 'selected');
                                  $("#"+fillData.villageId).select2();
							  }else if(fillData.villageValue==""){
                                  $("#"+fillData.villageId+" option:first").prop("selected", 'selected');
                                  //$("select#"+fillData.villageId+" option[value^='"+"-1"+"']").prop("selected", 'selected');
                                  //$("#"+fillData.villageId).select2();
                              }
						    }else if(data.length==0){
						    }
						   //-----------回填 （结束）----------------//
						  
					  }
				  }
				});
		},       
		/**
		 * selectId : 当前下拉框
		 * nextSelectId ：下一个下拉框
		 * nextSelectType:下拉框类型 省 province
		 *  例如： 省 selectId = "province"
		 *       市 nextSelectId = "city"
		 */
		change:function(selectId,nextSelectId,nextSelectType,fillData){                                       //改变事件     
			var currentSelectValue = $("#"+selectId+"").val().split(",")[0];
			ADD.fillSelect(currentSelectValue,nextSelectType,nextSelectId,fillData);
		}, 
		/**
		 * id : 需要清除的下拉框的id
		 */
		clear:function(id){                                                          //清除下拉框
			var select = $("#"+id+""); 
			$("#"+id+"").empty();//清空下拉框
			$("#"+id+"").append("<option value=''>--请选择--</option>");	
		}
}
//-------------------------------地址对象 (结束)----------------------------------------------//



$(function(){
//	$.gridLoading({
//		message: "正在加载..."
//	});
	//-----------现在驻地（开始）--------------//
	var fillData1={
		provinceId:"province1",
		provinceValue:$("#provinceValue1").val(),
		provinceType:"PROVINCE",
		cityId:"city1",
		cityValue:$("#cityValue1").val(),
		cityType:"CITY",
		countyId:"county1",
		countyValue:$("#countyValue1").val(),
		countyType:"COUNTY",
		townId:"town1",
		townValue:$("#townValue1").val(),
		townType:"TOWN",
		villageId:"village1",
		villageValue:$("#villageValue1").val(),
		villageType:"VILLAGE"
	}
	//初始化地域
	if($("#"+fillData1.provinceId+"").length==1){
	   ADD.init(fillData1.provinceId,fillData1.cityId,fillData1.provinceType,fillData1.cityType,fillData1);
	}else{
		$.gridUnLoading();
	}
	 //省改变
	$("#"+fillData1.provinceId+"").change(function(){
		ADD.initFlag = false;
		ADD.change(fillData1.provinceId,fillData1.cityId,fillData1.cityType);
		ADD.clear(fillData1.countyId);
		ADD.clear(fillData1.townId);
		ADD.clear(fillData1.villageId);
	});
	//市改变
	$("#"+fillData1.cityId+"").change(function(){
		ADD.initFlag = false;
		ADD.change(fillData1.cityId,fillData1.countyId,fillData1.countyType);
		ADD.clear(fillData1.townId);
		ADD.clear(fillData1.villageId);
	});
	//县改变
	$("#"+fillData1.countyId+"").change(function(){
		ADD.initFlag = false;
		ADD.change(fillData1.countyId,fillData1.townId,fillData1.townType);
		ADD.clear(fillData1.villageId);
	});
	//镇改变
	$("#"+fillData1.townId+"").change(function(){
		ADD.initFlag = false;
		ADD.change(fillData1.townId,fillData1.villageId,fillData1.villageType);
	});
//-----------现在驻地（结束）--------------//
	
//-------------------------户口地址（开始）----------------------//
	var fillData2 = {
		provinceId:"province",
		provinceValue:$("#provinceValue").val(),
		provinceType:"PROVINCE",
		cityId:"city",
		cityValue:$("#cityValue").val(),
		cityType:"CITY",
		countyId:"county",
		countyValue:$("#countyValue").val(),
		countyType:"COUNTY",
		townId:"town",
		townValue:$("#townValue").val(),
		townType:"TOWN",
		villageId:"village",
		villageValue:$("#villageValue").val(),
		villageType:"VILLAGE"
	}
	//初始化地域
	if($("#"+fillData2.provinceId+"").length==1){
	  ADD.init(fillData2.provinceId,fillData2.cityId,fillData2.provinceType,fillData2.cityType,fillData2);
	}
	  //省改变
	$("#"+fillData2.provinceId+"").change(function(){
		ADD.initFlag = false;
		ADD.change(fillData2.provinceId,fillData2.cityId,fillData2.cityType);
		ADD.clear(fillData2.countyId);
		ADD.clear(fillData2.townId);
		ADD.clear(fillData2.villageId);
	});
	//市改变
	$("#"+fillData2.cityId+"").change(function(){
		ADD.initFlag = false;
		ADD.change(fillData2.cityId,fillData2.countyId,fillData2.countyType);
		ADD.clear(fillData2.townId);
		ADD.clear(fillData2.villageId);
	});
	//县改变
	$("#"+fillData2.countyId+"").change(function(){
		ADD.initFlag = false;
		ADD.change(fillData2.countyId,fillData2.townId,fillData2.townType);
		ADD.clear(fillData2.villageId);
	});
	//镇改变
	$("#"+fillData2.townId+"").change(function(){
		ADD.initFlag = false;
		ADD.change(fillData2.townId,fillData2.villageId,fillData2.villageType);
	});
	
//---------------------户口地址（结束）-----------------------------//
	


});
