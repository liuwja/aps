//高亮索引
var highlightindex=-1;

//设置文本框的内容
function setContent(obj,con,index){
	var context=con.eq(index).text();
	$(obj).val(context);
}

//设置背景颜色
function setBkColor(con,index,color){
	con.eq(index).css("background-color",color);
}

$(document).ready(
	function(){
		//获得输入框节点
		var autonode=$("#auto");


		//当键盘抬起时触发事件执行访问服务器业务
		$("input[name='oriSerialNum']").live("keyup",
			function(event){
				var myevent=event||window.event;
				var mykeyCode=myevent.keyCode;
				var inputItem=$(this);
				
				//字母，退格，删除，空格
				if(mykeyCode>=65&&mykeyCode<=90||mykeyCode==8||mykeyCode==46||mykeyCode==32){
					//清除上一次的内容
					autonode.html(" ");
					//获得文本框内容
					var word = $.trim(inputItem.val());
					var timeDelay;
					
					if(word!=""){
						//取消上次提交
						window.clearTimeout(timeDelay);
						//延迟提交，这边设置的为400ms
						//partNameArr = ["非关键件","bbb","阀门","物料"];
						var newArr = new Array();
						
						$.each(partNameArr,
							function(i,value){
								if(value.indexOf(word,0) != -1)
								{
									newArr.push(value);
								}
							}
						)
						timeDelay=window.setTimeout(
							//将文本框的内容发到服务器
							function(){
								//将返回数据转换为JQuery对象
								var jqObj=$(newArr);
								//找到所有的word节点
								$.each(jqObj,
									function(i,value){
										//获得返回的单词内容
										var newNode=$("<div>").html(value).attr("id",i).addClass("pro");
										//将返回内容附加到页面
										newNode.appendTo(autonode);
										//处理鼠标事件
										var con=$("#auto").children("div");
										//鼠标经过
										newNode.mouseover(
											function(){
												if(highlightindex!=-1){
													setBkColor(con,highlightindex,"white");
												}
												highlightindex=$(this).attr("id");
												$(this).css("background-color","#ADD8E6");
												setContent(inputItem,con,highlightindex);
											}	
										);
										//鼠标离开
										newNode.mouseout(
											function(){
												$(this).css("background-color","white");
											}	
										);
										//鼠标点击
										newNode.click(
											function(){
												setContent(inputItem,con,highlightindex);
												highlightindex=-1;
												autonode.hide();
											}
										);
									}	
								);    //each

								//当返回的数据长度大于0才显示
								if(newArr.length>0){
		
		var inputOffset=inputItem.offset();
		//设置提示框隐藏
		autonode.hide().css("border","1px black solid").css("position","absolute").css("background-color","white")
		.css("top",inputOffset.top+inputItem.height()+5+"px")
		.css("left",inputOffset.left+"px").width(inputItem.width()+"px");	
									autonode.show();
								}else{
									autonode.hide();
								}
							}
							,200);    //settimeout
					}else{
						autonode.hide();
						highlightindex=-1;
					}
				}else{
					//获得返回框中的值
					var rvalue=$("#auto").children("div");
					//上下键
					if(mykeyCode==38||mykeyCode==40){
						//向上
						if(mykeyCode==38){
							if(highlightindex!=-1){
								setBkColor(rvalue,highlightindex,"white");
								highlightindex--;
							}
							if(highlightindex==-1){
								setBkColor(rvalue,highlightindex,"white");
								highlightindex=rvalue.length-1;
							}
							setBkColor(rvalue,highlightindex,"#ADD8E6");
							setContent(inputItem,rvalue,highlightindex);
						}
						//向下
						if(mykeyCode==40){
							if(highlightindex!=rvalue.length){
								setBkColor(rvalue,highlightindex,"white");
								highlightindex++;
							}
							if(highlightindex==rvalue.length){
								setBkColor(rvalue,highlightindex,"white");
								highlightindex=0;
							}
							setBkColor(rvalue,highlightindex,"#ADD8E6");
							setContent(inputItem,rvalue,highlightindex);
						}
					}
					//回车键
					if(mykeyCode==13){
						if(highlightindex!=-1){
							setContent(inputItem,rvalue,highlightindex);
							highlightindex=-1;
							autonode.hide();
						}else{
							alert(inputItem.val());
						}
					}
				}
			}
		);//键盘抬起

		//当文本框失去焦点时的做法
		$("input[name='oriSerialNum']").live("focusout",
		//$("#partNameID").focusout(
			function(){
				//隐藏提示框
				autonode.hide();
			}	
		);
	}
);//reday