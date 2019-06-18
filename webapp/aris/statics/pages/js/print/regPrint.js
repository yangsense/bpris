$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    }); 
	
	$("#printBtn").click(function(){
		print();		
	}); 
      
});
 
function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function print(){
	$("#pInfo").jqprint();
	setTimeout(function(){ 
		parent.goBack();
    },1000);	
	
}
 