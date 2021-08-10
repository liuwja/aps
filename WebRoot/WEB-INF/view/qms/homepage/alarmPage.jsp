<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<style>
 .my-uploadify-button {
    background:none;
    border: none;
    text-shadow: none;
    border-radius:0;
}

.uploadify:hover .my-uploadify-button {
    background:none;
    border: none;
}

.fileQueue {
    width: 400px;
    height: 150px;
    overflow: auto;
    border: 1px solid #E5E5E5;
    margin-bottom: 10px;
}

.one{  
    width:99%; float:left;height:220px;margin-left: 2px;margin-top: 2px;
}
.two{
    width:25%; float:left;height:200px;margin-left: 10px;margin-top: 10px;border:2px solid #E5E5E5;
}

#tr1{
     width:100%;height:20px;backGround:#99CCFF;
}
#tr1 td{
     border:1px solid  #E5E5E5; text-align: center; font-size: 8px; font-weight: bold;
}

#tr2 {
    width:100%;height:20px;
}

#tr2 td{
     padding-top:3px; padding-left:2px;padding-right:2px; padding-bottom:2px;
     border:1px solid  #E5E5E5; text-align: center; font-size: 5px; color:#686868;font-weight: bold;
     
}

#tr3{
     height:28px;border:1px solid #E5E5E5;line-height:26px;background:#99CCFF;
}
#tr3 td{
   font-size: 10px; font-weight: bold; padding-left: 5px;
}

#tr4 td{
   font-size: 8px; font-weight: bold; padding-left: 5px;
}

#otable1 tbody tr{
   height:2px;border: 1px solid #E5E5E5;
}
#otable1 tbody tr td{
   padding-top:3px; padding-left:2px;padding-right:2px; padding-bottom:2px;border: 1px solid #E5E5E5;
   text-align: center;font-size: 4px;color:#484848;
}

.dot1{
   width:5%;
}


#stable1 tr td{    
    height:15px;  padding-left:5px;font-size: 5px;
}

#stable2 tr td{    
    height:15px; padding-left:5px;font-size: 5px;
}

#stable3 tr td{    
    height:15px;  padding-left:5px;font-size: 5px;
}

#span1{
   color:#058DC7;
}
#span2{
   color:#058DC7;
}
#span3{
   color:#058DC7;
}

 #firstbox{margin-bottom:20px;}
 #firstbox .two .mestext{height:25px;background:#99CCFF;}
 #firstbox .two .mestext .lefmes{display:inline-block;height:30px;line-height:30px;padding:0px 2px;font-size:8px;font-weight: bold;}  
 #firstbox .two .mestext .rigmes{display:inline-block;height:30px;line-height:30px;float:right;padding-right:10px;font-size:8px;font-weight: bold;}
 #twob{
     color:#CC3333;
 }
 
</style>

<br>
<div class="page unitBox" style="overflow: auto;height: 100%">
<div id="homePageContent">
  	<div id="firstbox" class="one">
  	        <div  style="width: 100%;height: 20px; text-align: center;" ">
				<span>截止日期：</span>
				<input id="selectDate" type="text" size="12" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" value="${queryMonth }" class="textInput readonly">
				<button  onclick="reloadHomePge()" style="cursor:pointer;">查询</button>
	        </div>
			<div  style=" width:70%; float:left;height:200px;margin-left: 10px;margin-top: 10px;">
		         <table id="otable1" style="width:100%; hight:100%; border:2px solid #E5E5E5;border-collapse:collapse;">
		           <thead>
		            <tr id="tr1">
		               <td rowspan="2" style="border-right: 2px solid #E5E5E5;">指标</br>(${queryMonth })</td>
		               <td colspan="4" style="border-right: 2px solid #E5E5E5;">电器一厂</td>
		               <td colspan="4" style="border-right: 2px solid #E5E5E5;">燃气工厂</td>
		               <td colspan="4" >电器二厂</td>
		            </tr>
		            <tr id="tr2">
		               <td style="border:1px solid  #E5E5E5;">产品线</td>
		               <td style="border:1px solid  #E5E5E5;">月目标</td>
		               <td style="border:1px solid  #E5E5E5;">月实际</td>
		               <td style="border:1px solid  #E5E5E5;border-right: 2px solid #E5E5E5;">年实际</td>
		               
		               <td style="border:1px solid  #E5E5E5;">产品线</td>
		               <td style="border:1px solid  #E5E5E5;">月目标</td>
		               <td style="border:1px solid  #E5E5E5;">月实际</td>
		               <td style="border:1px solid  #E5E5E5;border-right: 2px solid #E5E5E5;">年实际</td>
		               
		               <td style="border:1px solid  #E5E5E5;">产品线</td>
		               <td style="border:1px solid  #E5E5E5;">月目标</td>
		               <td style="border:1px solid  #E5E5E5;">月实际</td>
		               <td style="border:1px solid  #E5E5E5;">年实际</td>
		            </tr>
		           </thead>
		         </table>
		   
		   
		    </div >
		    <div   class="two">
		    	<p class="mestext">
			         <span class="xtitle lefmes">信息公告栏</span>
			         <span class="rigmes">当月异常记录总数：<b id="twob">${num }</b></span>
		         </p>
		         <marquee scrollamount="1" behavior="scroll" direction="up"  height="150px" scrolldelay="100" hspace="5" onmouseout="this.start();" onmouseover="this.stop();" >
	               <ul class="shortInfo">
	              	<c:forEach items="${exList}" var="o">
	                <li class="stop">${o.exceptionName }</li>
	              	</c:forEach>
	               </ul>
	            </marquee>
		       
		    </div>
	</div>
	<div  id="charbox" style="height:200px;margin-left:10px;">
			<div  id="homePagePerforDqChart" style="width:32%; float:left;height:200px;">
		         
		    </div>	  
		    <div  id="homePagePerforRqChart" style="width:32%; float:left;height:200px;margin-left: 10px;">
		        
		    </div >
		    <div  id="homePagePerforDqeChart" style="width:32%; float:left;height:200px;">
		         
		    </div>
	</div>
	<div  id="charbox2" style="height:200px;margin-left:10px;">
			<div  id="homePagePerforDqChart2" style="width:32%; float:left;height:200px;">
		         
		    </div>  
		    <div  id="homePagePerforRqChart2" style="width:32%; float:left;height:200px;margin-left: 10px;">
		        
		    </div >
		     <div  id="homePagePerforDqeChart2" style="width:32%; float:left;height:200px;">
		         
		    </div>
	</div>
		
		
	<div style="margin-top: 10px;">
			<div style="width:96%; float:left;height:505px;margin-left: 10px;margin-top: 10px;">
		         <table style="width:100%; hight:100%; border-collapse:collapse;border:2px solid  #E5E5E5;">
		         <thead>
		          <tr id="tr3" >
	               	    <td colspan="11" style="border:1px solid #E5E5E5;padding-left:6px;">班组绩效(${queryMonth })</td>
	              </tr>	
	              <tr id="tr4" style="height:20px;bolder-bottom:1px solid #E5E5E5;line-height:26px;">
	               	   <th colspan="4" style="border:1px solid #E5E5E5;font-size: 6px;font-weight: bold;color:#686868">电器一厂</th>
	               	   <th colspan="4" style="border:1px solid #E5E5E5;font-size: 6px;font-weight: bold;color:#686868">燃气工厂</th>
	               	   <th colspan="4" style="border:1px solid #E5E5E5;font-size: 6px;font-weight: bold;color:#686868">电器二厂</th>
	              </tr>	
	              </thead>
	              <tbody>
	               <tr>
	                  <td colspan="4" style="width:32%; vertical-align:top;border-right:2px solid #E5E5E5 ">
	                      <table id="stable1"  style="width:100%; hight:100%;border-collapse:collapse;">
	                          <thead>
	                            <tr style="height:15px;line-height:15px;background:#CCFFFF;">
	                              <th style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">班组类别</th>
				                  <th style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">班组名称 </th>
				                  <th style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">得分</th>
				                  <th style="border-bottom:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">名次</th>
	                            </tr>
	                          </thead>
	                          <tbody>
	                            <c:forEach items="${category }" var="cat">    
	                               <c:forEach items="${map['电器一厂'][cat.category] }" var="vo" >	                     
	                               <tr style="border-bottom:1px solid #E5E5E5;">
	                                <td style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;padding-top: 1px;padding-bottom: 1px;color:#686868;font-weight: bold;">${vo.category }</td>
	                                <td style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;padding-top: 1px;padding-bottom: 1px;"><a href="javascript:void(0)" onclick="getGroupPerfermance('${vo.factory }','${vo.area }','${vo.shiftGroupTxt}','${queryMonth }','${vo.shiftGroupScore }','${vo.id  }')"><span id="span1">${vo.shiftGroupTxt }</span></a></td>
	                                <td style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;padding-top: 1px;padding-bottom: 1px;">${vo.shiftGroupScore =='-1' ?'-' : vo.shiftGroupScore}</td>
	                                <td style="padding-top: 1px;padding-bottom: 1px;">${vo.id =='' ?'-' : vo.id}</td>
	                               </tr> 
	                               </c:forEach>	                             
	                            </c:forEach>
	                          </tbody>    
	                      </table>
	                  </td>
	                  <td colspan="4" style="border:1px solid #E5E5E5;width:32%; vertical-align:top;border-right:2px solid #E5E5E5;">
	                      <table id="stable2" style="width:100%; hight:100%;position:top;border-collapse:collapse;">
	                         <thead>
	                            <tr style="height:15px;;line-height:15px;background:#CCFFFF ">
	                              <th style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">班组类别</th>
				                  <th style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">班组名称 </th>
				                  <th style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">得分</th>
				                  <th style="border-bottom:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">名次</th>
	                            </tr>
	                          </thead>  
	                           <tbody>
	                            <c:forEach items="${category }" var="cat">    
	                               <c:forEach items="${map['燃气工厂'][cat.category] }" var="vo" >	                     
	                               <tr style="border-bottom:1px solid #E5E5E5;">
	                                <td style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;padding-top: 1px;padding-bottom: 1px;color:#686868;font-weight: bold;">${vo.category }</td>
	                                <td style="border:1px solid #E5E5E5;padding-top: 1px;padding-bottom: 1px;"><a href="javascript:void(0)" onclick="getGroupPerfermance('${vo.factory }','${vo.area }','${vo.shiftGroupTxt}','${queryMonth }','${vo.shiftGroupScore }','${vo.id  }')"><span id="span2">${vo.shiftGroupTxt }</span></a></td>
	                                <td style="border:1px solid #E5E5E5;padding-top: 1px;padding-bottom: 1px;">${vo.shiftGroupScore=='-1' ?'-' : vo.shiftGroupScore}</td>
	                                <td style="padding-top: 1px;padding-bottom: 1px;">${vo.id =='' ?'-' : vo.id}</td>
	                               </tr> 
	                               </c:forEach>	                             
	                            </c:forEach>
	                          </tbody>    
	                      </table>
	                  </td>
	                  <td colspan="4" style="border:1px solid #E5E5E5;width:32%; vertical-align:top">
	                      <table id="stable3" style="width:100%; hight:100%;border-collapse:collapse;">
	                           <thead>
	                            <tr style="height:15px;;line-height:15px;background:#CCFFFF">
	                              <th style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">班组类别</th>
				                  <th style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">班组名称 </th>
				                  <th style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">得分</th>
				                  <th style="border:1px solid #E5E5E5;font-size: 5px;font-weight: bold;color:#686868">名次</th>
	                            </tr>
	                           </thead> 
	                           <tbody>
	                            <c:forEach items="${category }" var="cat">    
	                               <c:forEach items="${map['电器二厂'][cat.category] }" var="vo" >	                     
	                               <tr style="border-bottom:1px solid #E5E5E5;">
	                                <td style="border-bottom:1px solid #E5E5E5;border-right:1px solid #E5E5E5;padding-top: 1px;padding-bottom: 1px;color:#686868;font-weight: bold;">${vo.category }</td>
	                                <td style="border:1px solid #E5E5E5;padding-top: 1px;padding-bottom: 1px;"><a   href="javascript:void(0)"  onclick="getGroupPerfermance('${vo.factory }','${vo.area }','${vo.shiftGroupTxt}','${queryMonth }','${vo.shiftGroupScore }','${vo.id  }')"><span id="span3">${vo.shiftGroupTxt }</span></a></td>
	                                <td style="border:1px solid #E5E5E5;padding-top: 1px;padding-bottom: 1px;">${vo.shiftGroupScore =='-1' ?'-' : vo.shiftGroupScore}</td>

	                                <td style="padding-top: 1px;padding-bottom: 1px;">${vo.id =='' ?'-' : vo.id}</td>
	                               </tr> 
	                               </c:forEach>	                             
	                            </c:forEach>
	                          </tbody>    
	                      </table>
	                  </td>
	                
	               </tr>
	               </tbody>
		         </table>
		    </div>

	</div>

	
</div>
</div>
<script type="text/javascript"> 
jQuery.fn.rowspan = function(colIdx) { //封装的一个JQuery小插件 
return this.each(function(){ 
var that; 
$('tr', this).each(function(row) { 
$('td:eq('+colIdx+')', this).filter(':visible').each(function(col) { 
if (that!=null && $(this).html() == $(that).html()) { 
rowspan = $(that).attr("rowSpan"); 
if (rowspan == undefined) { 
$(that).attr("rowSpan",1); 
rowspan = $(that).attr("rowSpan"); } 
rowspan = Number(rowspan)+1; 
$(that).attr("rowSpan",rowspan); 
$(this).hide(); 
} else { 
that = this; 
} 
}); 
}); 
}); 
}  
</script> 

<script type="text/javascript">
jQuery(document).ready(function(){
	loadPerformanceChart();
	$("#stable1").rowspan(0);//传入的参数是对应的列数从0开始，哪一列有相同的内容就输入对应的列数值
	$("#stable2").rowspan(0);
	$("#stable3").rowspan(0);
	
	var text = "${text}";
//	alert(text);
	$("#otable1",navTab.getCurrentPanel()).append(text);
	$("#otable1 tbody tr:odd",navTab.getCurrentPanel()).addClass("tableTrbg");
	$("#stable1 tbody tr:odd",navTab.getCurrentPanel()).addClass("tableTrbg");
	$("#stable2 tbody tr:odd",navTab.getCurrentPanel()).addClass("tableTrbg");
	$("#stable3 tbody tr:odd",navTab.getCurrentPanel()).addClass("tableTrbg");
	$(window).resize(function(){
		var width = $("#alarmInfoDiv").width();
		if(width<1000){
			width = 1000;
		}
		$("#homePageContent",navTab.getCurrentPanel()).width(width);
		loadPerformanceChart();
      }); 
	setTimeout(function(){
		var homeWidth = $("#alarmInfoDiv").width();
		if(homeWidth<1100){
			homeWidth = 1100;
	       $("#homePageContent",navTab.getCurrentPanel()).width(homeWidth);
		}
	},100);
});

function reloadHomePge()
{
	var selectDate = $("#selectDate",navTab.getCurrentPanel()).val();
	if(selectDate == "")
	{
		alertMsg.info("请选择截止日期！");
		return;
	}
	var url = "system/homePage/alarmPage.do";
	navTab.reload(url, {navTabId : "alarmPageNav",data:{selectDate:selectDate}});
}

function getGroupPerfermance(factory,area,group,month,score,rank){
//	alert("ok");
	 var url ="groupPerformanceChart/performanceDetailListMonth.do";
	 navTab.closeTab('newperformanceDetailListNav');
	 navTab.openTab('performanceDetailListNav', url, { title:'班组绩效明细', fresh:true, data:{factory:factory,area:area,group:group,date:month,mykey:'home',homeScore:score,homeRank:rank} });
}

function openHomePageCharTab(num){	
    var queryMonth = "${q}";
    var factory= '';
    if(num == 0){
      factory ='电器一厂';
    }else if(num == 1){
       factory='燃气工厂';
    }else if(num == 2){
       factory='电器二厂';
    }
	navTab.closeTab('singlePerformanceCharNav');
	navTab.openTab('singlePerformanceCharNav', "system/homePage/detailInfo.do", { title:'班组绩效排名统计(单月)', fresh:true, data:{factory:factory,queryMonth:queryMonth} });
}
var detailFunctionName = 'openHomePageCharTab';

function loadPerformanceChart() {
	var char_width = document.body.clientWidth - $("#sidebar").width()-80;
	var queryMonth = "${queryMonth}";
	var url = "<c:url value='system/homePage/getPerformanceSingleChar.do'/>";
	$.post(url, {queryMonth:queryMonth,width:char_width}, function(data) {

		if (data.result == 0) {
		    data.dpositivechar.index = 0;
			data.dpositivechar.type = 0;
			data.dpositivechar.funcName = detailFunctionName;
			data.dreversechart.index = 0;
		    data.dreversechart.type = 1;
			data.dreversechart.funcName = detailFunctionName;
			data.rpositivechart.index = 1;
			data.rpositivechart.type = 0;
			data.rpositivechart.funcName = detailFunctionName;
			data.rreversechart.index = 1;
		    data.rreversechart.type = 1;
			data.rreversechart.funcName = detailFunctionName;
			data.depositivechart.index = 2;
			data.depositivechart.type = 0;
			data.depositivechart.funcName = detailFunctionName;
			data.dereversechart.index = 2;
		    data.dereversechart.type = 1;
			data.dereversechart.funcName = detailFunctionName;
			homePageChart("homePagePerforDqChart", data.dpositivechar,queryMonth);
			homePageChart("homePagePerforDqChart2", data.dreversechart,queryMonth);
			homePageChart("homePagePerforRqChart", data.rpositivechart,queryMonth);
			homePageChart("homePagePerforRqChart2", data.rreversechart,queryMonth);
			homePageChart("homePagePerforDqeChart", data.depositivechart,queryMonth);
			homePageChart("homePagePerforDqeChart2", data.dereversechart,queryMonth);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}


function homePageChart(id, data, startTime){
	var chartIndex = data.index == undefined ? -1 : data.index;
	var detailType = data.type== undefined ? -1 : data.type;
	
	var chartType = data.chartType;//类型
	var chartWidth = data.chartWidth;//宽
	var chartHight = data.chartHight;//高
	var title = data.title;//图标名称
	var xTitle = data.xTitle;//横坐标名称
	var yLeftTitle = data.yLeftTitle;//纵坐标左标题
	var yRightTitle = data.yRightTitle;//纵坐标右标题
	var yLeftUnit = data.yLeftUnit;//左单位
	var yRightUnit = data.yRightUnit;//右单位
	var defaultValue = data.defaultValue;
	var xValue = data.xValue;
	var contxtTitle = "打印或导出";
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;
	var creditsEnable = data.index == undefined ? false : true;
	//alert(chartType);
	var containerId = id;
	var date = startTime;
	var factory = data.subtitle;
	//alert("type:" + chartType);
	for(var i=0;i<xValue.length;i++){ 
		if(xValue[i] == null || xValue[i] ==""){
			xValue[i] = " ";
		}
	} 
	var colColor ;
	if(detailType == 0){
		colColor='#058DC7';
	}else{
		colColor='#FF6666';
	}
	var creditsInfo = {
			enabled:creditsEnable,
			text: '详情',
            href: 'javascript:javascript:'+funcName+'('+chartIndex+')',
			style: {
				cursor: 'pointer',
				color: 'blue',
				fontSize: '10px',
			},
            position: {
                align: 'right',
                verticalAlign:'bottom',
                x: -10,y:-10
            }
        };
	//纵向柱状图
 if(chartType == "column") { 
		chart = new Highcharts.Chart( {
			chart : {
				backgroundColor:'#FFFFFF',
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
				type : chartType,
	            borderColor: '#FFFFFF',
            	borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1,
	            options3d: {
	                enabled: true,
	                alpha: 15,
	                beta: 15,
	                depth: 50,
	                viewDistance: 25
	            }
			},
			title : {
				text : title,    
				style:{				  
	                  font: '15px',
	                  fontSize : '15px',
	                  fontFamily: 'Verdana, sans-serif',
	                  fontWeight:'bold'
				},    
			},
			lang : {
				
				contextButtonTitle: contxtTitle,
				exportButtonTitle : '导出',
				printChart : '打印',
				downloadJPEG : "下载JPEG 图片",
				downloadPDF : "下载PDF文档",
				downloadPNG : "下载PNG 图片",
				downloadSVG : "下载SVG 矢量图",
				noData: "没有符合条件的数据"
			},
        	credits: creditsInfo,
        			
	        exporting: {
	             enabled:false
//	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,
				labels : {
					align : 'left',
					formatter : function() {
//						var x = this.value;
//						var y;
//						if(x.length>7){
//							y = x.substring(0,7)+"...";
//						}else{
//							y = this.value;
//						}
						return this.value;
					},
	                rotation: 20,
					staggerLines : 1,
					overflow: 'justify',
//					style: { 
//                        writingMode:'tb-rl',   //文字竖排样式,                        
//                   },
                     x:-20,
 			         y:15
 			         

				},
				//tickInterval : 1,
				title : {
					text : xTitle,
					style:{
						color : '#058DC7',
						fontSize:'8px',
					},
					align:'low',x:-55,y:-35,
					
				}
			},
			yAxis : {
				labels : {

					formatter : function() {
						return this.value+yLeftUnit;
					},
					style: {
	                    color: '#058DC7'
	                },
					rotation: 0,
					offset: 0,
	                align: "right",
					x: -8,
					y: 0
				},
				//tickInterval : 3,
				title : {
					text : yLeftTitle,
					style: {
						fontSize: '8px',
	                    color: '#058DC7'
	                }
				},
				
			},
			plotOptions : {
				series : {
					cursor : 'pointer',
					
					pointWidth : 20
				},
				dataLabels : {
					enabled : true
				},

				column : {
					// 允许线性图上的数据点进行点击
					allowPointSelect : true,
					events: { 
		                click: function(e) { 
		                	var tabId = "newperformanceDetailListNav";
		                	var title = "月度班组绩效明细查询";
		                	var group = e.point.category;
		                	var startTime = date;
		                	var url = "groupPerformanceChart/performanceDetailListMonth.do?date="+startTime+"&group="+group+"&factory="+factory;
		                	navTab.openTab(tabId, url, {title:title, fresh:false});
//		                    alert(e.point.category); 
		                } 
		            } ,
					// 是否在图注中显示。					
					showInLegend : true,
					// 调整图像顺序关系
					zIndex : 3
				}
			},
			
			legend: {
	            enabled: false
	        },
		
			series : [ {
				
				name : data.seriesNames[0],
				data : data.yValues[0],
				color: colColor,
   				dataLabels: {
	                enabled: true,
	                rotation: 0,
	                color: '#058DC7',
	                align: 'center',
	                x: 2,
	                y: 4,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
			} ]
			
		});
	}
			
}
</script>
