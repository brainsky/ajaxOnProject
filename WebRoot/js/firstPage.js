function sendID(){
	if($("#editInput").css("display") == "none"){
		$("#editInput").css("display","block");
		return false;
	}
	$("input[name='customerID']").focus(function(){
		removePLabelError();
	});
	var inputID = $("input[name='customerID']").val();
	//用户输入时提交ajax请求
	var matchInt = /^[0-9]{1,4}$/;
	if(!matchInt.test(inputID)){
		errorInfo = addErrorMessageOnPLable("ID为整数且现在仅支持4位数哦");
		$("#showMessage").append(errorInfo);
		return false;
	}		
	//发送id到后台
	
	$.post("/mycrud/findByID",
			{"id":inputID},
			function(data){
				if(data.code == "404"){
					errorInfo = addErrorMessageOnPLable("该ID不存在，换一个试试");
					$("#showMessage").append(errorInfo);
				}else{
					//显示form表，添加返回的元素进去
					console.log(data);
					$("#findTable").css("display","block");
					html = "<td>"+data.name+"</td>"+"<td>"+data.level+"</td>";
					$("#searchResultTable").find(" tr:eq(1)").append(html);
				}
			}
			);	
}


$(function(){
//	var availableTags = [
//	                     "ActionScript",
//	                     "AppleScript",
//	                     "Asp",
//	                     "BASIC",]

//	$("input[name='customerID']").autocomplete({
//		source:availableTags
//	});
	//获取input框输入值
	
	//验证inputID
	removePLabelError();
	$("input[name='customerID']").focus(function(){
		removePLabelError();
		if($("#fillTrData").find("td:eq(0)") != undefined){
			$("#fillTrData").empty();	
		}
	});
});
