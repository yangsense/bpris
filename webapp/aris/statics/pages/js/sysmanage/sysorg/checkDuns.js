
function checkDunsUID(type){
		var duns =  $("#duns").val();
   		 $.ajax({
			url : GLOBAL.WEBROOT + "/sysOrg/checkDunsUID.ajax?duns="+duns+"&type="+type,
			type : "post",
			success : function(data) {

				if(true==data.result){
					
					$("#duns").val("");
					alert("机构唯一码已存在，请重新输入!");

				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				 $("#duns").val("");
			}
		});
}
