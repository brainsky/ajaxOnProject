$().ready(
		function(){
			$("#enter_btn").on("click",toAddCustomer)
	}
);

//delete删除显示对话框
function delete_btn(obj){
	var id = $(obj).parent().parent().find("td:first").html();
	var status = confirm('确认删除吗？')
	if(!status){
		return false;
	} 
	$.post("/mycrud/deleteCustomer",
				{id:id},
				function(data){					
						href(data);
		});
}
//显示编辑框
function add(){
	$("#editform").css("display","block");	
}


function toAddCustomer(){
	var formData = getEditFormContent();
	postFormData("/mycrud/addCustomer",formData);
}


function returnbtn(){
	$("#editform").css("display","none");
}

function update_btn(obj){
	var id = $(obj).parent().parent().find("td:first").html();
	var name = $(obj).parent().parent().find("td:eq(1)").html();
	var level = $(obj).parent().parent().find("td:eq(2)").html();;
	//显示编辑框
	$("#editform").css("display","block");
	//输入值
	$("input[name='customerName']").val(name);
	$("input[name='level']").val(level);
	//修改button onclick属性
	$("#enter_btn").off("click",toAddCustomer);
	//绑定到update的地址
	//addID ={"id":id}
	$("#enter_btn").on("click",function(){
		var formData = getEditFormContent();
		formData.id = id;
		postFormData("/mycrud/updateCustomer",formData);
	});
}
function getEditFormContent(){
	//先判断是否有提示消息，有则清除提示。
	removePLabelError();
	$("input[name='customerName']").focus(function(){
		removePLabelError();
	});
	$("input[name='level']").focus(function(){
		removePLabelError();
	});
	//获取input框的值
	var name = $("input[name='customerName']").val();
	var level = $("input[name='level']").val();
	if(name==(""|undefined) || level==(""|undefined)){
		var text = addErrorMessageOnPLable("啊哦，你忘了输入姓名或者level!");
		$("#namelabel").append(text);
		return false;
	}
	//验证用户名长度	
	if(name.length >8){
		var text = addErrorMessageOnPLable("用户名长度小于8");
			$("#namelabel").append(text);
			return false;
	};
	//post提交到addCustomer.do
	//验证level是否是整数
	var matchInt = /^[0-9]{1,4}$/;
	if(!matchInt.test(level)){
		var text = addErrorMessageOnPLable("level必须是整数而且小于4位数哦!")
		$("#namelabel").append(text);
		return false;
	}
	var intLevel = +level;
	var json = {"name":name,"level":intLevel};
	
	return json;
}
//功能函数，获取后台返回url，前台ajax跳转
function href(data){
	
	data = JSON.parse(JSON.stringify(data))
	url = data.url;
	window.location.href = url;
}

function addErrorMessageOnPLable(message){
	var text = "<p class='text-danger' id='errorinfo'>" +message+"</p>"
	return text;
}

function removePLabelError(){
	if($("#errorinfo") != undefined){
		$("#errorinfo").remove();
	}
}

function postFormData(inputUrl,inputData){
	$.post(inputUrl,
			inputData,
			function(data,status){
					if(data.url != undefined){
						href(data);
					}else {
						resultData = data;
					}
				}
			);
}
