
$(function(){

    var option = {
        dataStructure:{
            idKey: "orgId",
            pIdKey: "parentOrgId",
            rootPId: 10840,
            name:"orgName"
        },
        data:{
            url:"/cashbox/getOrgs",    //获取数据的URL
            param:["orgId"]  //获取数据的参数
        },
        rootNode:[{orgId: '10840',orgName: '请选择', open : true,isParent: true ,nocheck:true}]

    };

    com.ai.bdx.util.ztreeComp("treeselect2",false,option,callFunc);
    com.ai.bdx.util.ztreeComp("treeselect1",true,option,callFuncForMulti);



});

function callFunc(treeNode){
    $("#treeselect2").val(treeNode.orgName);
}


function callFuncForMulti(zTree){
    var nodes = zTree.getCheckedNodes(true);
    var v = "";
    var n = "";
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].orgCode + ",";
        n += nodes[i].orgName +",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    if (n.length > 0 ) n = n.substring(0, n.length-1);
    $('#treeselect1').val(n);
}


