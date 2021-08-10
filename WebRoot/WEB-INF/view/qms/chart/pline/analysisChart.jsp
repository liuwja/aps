<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
.divline
{
width:99%;
border:1px solid #6495ED; 
height:15px;
float:left;
margin-bottom:3px;
margin-top:3px;
margin-left:2px;
background:#FFF68F;
padding: 3px;
font-weight: bolder;
}	
.div_border{
	border-right:1px solid #6495ED;
	border-top:1px solid #6495ED;
	border-bottom:1px solid #6495ED;
	width:100%;
	margin-right:-1px; 
	margin-bottom:-1px;
}

.div_right_border{
	margin-top: 2px;
    margin-left: 2px;
}
</style>

<div class="pageHeader" style="overflow: visible;">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
                <th>机型类别：</th>
                <td>
					<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" <c:if test="${o.machineType==vo.productType}">selected</c:if>>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 
    			 <th>工厂：</th>
                <td>
					<select name="factory" id="factory" onchange="loadPlAnalyCondition();">
							<option value="">请选择</option>
							<c:forEach items="${factorys}" var="o">
							<option value="${o.factory }" <c:if test="${vo.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
							</c:forEach>
					</select>
    			</td>
    			<th>产线：</th>                       
                <td>
                	<div id="plineAnalyList" class="dropdownlist"></div>
                </td>	
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="10" name="xCount" id="xCount" value="${vo.xCount+1 }"/>
    			</td> 			
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadPlineAnalysisChart();">查找</button></div></div>
				</td>				
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent div_border" style="height: 500px;overflow: auto;" id="pline_out_div">
		<!-- id每个页面要不一样 -->
		<div id="div_pline_1" style="width:99%; float:left;height:300px;margin-left: 2px;margin-top: 2px"></div>
		<div class="divline" id="pline0">&nbsp;</div>
		<div style="width:100%; float:left;">
			<div>
			    <div class="div_right_border" id="div_pline_sx_a_0" style="width:50%; float:left;"></div>
				
			</div>
			<div style="width:100%; float:left;">
				<div class="div_right_border" id="div_pline_sx_0" style="width:50%; float:left;height:200px;"></div>
				<div id="div_pline_sxp_0" style="width:49%; float:left;height:200px;"></div>
			</div>
			<div >
				<div class="div_right_border" id="div_pline_p_0" style="width:33%; float:left;height:250px;"></div>
				<div class="div_right_border" id="div_pline_r_0" style="width:33%; float:left;height:250px;"></div>
				<div id="div_pline_g_0" style="width:33%; float:left;height:250px;"></div>
				
			</div>
			
		</div>
		<div class="divline" id="pline1">&nbsp;</div>
		<div id="plChart1" style="width:100%; float:left;">
			<div>
				
				<div class="div_right_border" id="div_pline_sx_a_1" style="width:50%; float:left;"></div>
			</div>
			<div style="width:100%; float:left;">
				<div class="div_right_border" id="div_pline_sx_1" style="width:50%; float:left;height:200px;"></div>
				<div id="div_pline_sxp_1" style="width:49%; float:left;height:200px;"></div>
			</div>
			<div >
				<div class="div_right_border" id="div_pline_p_1" style="width:33%; float:left;height:250px;"></div>
				<div class="div_right_border" id="div_pline_r_1" style="width:33%; float:left;height:250px;"></div>
				<div id="div_pline_g_1" style="width:33%; float:left;height:250px;"></div>
			</div>
			
		</div>
		<div class="divline" id="pline2">&nbsp;</div>
		<div id="plChart2" style="width:100%; float:left;">
			<div>
				
				<div class="div_right_border" id="div_pline_sx_a_2" style="width:50%; float:left;"></div>
			</div>
			<div style="width:100%; float:left;">
				<div class="div_right_border" id="div_pline_sx_2" style="width:50%; float:left;height:200px;"></div>
				<div id="div_pline_sxp_2" style="width:49%; float:left;height:200px;"></div>
			</div>
			<div >
				<div class="div_right_border" id="div_pline_p_2" style="width:33%; float:left;height:250px;"></div>
				<div class="div_right_border" id="div_pline_r_2" style="width:33%; float:left;height:250px;"></div>
				<div id="div_pline_g_2" style="width:33%; float:left;height:250px;"></div>
			</div>
			
		</div>  

</div>
<span id="plineAnalyTd">
      <script type="text/javascript">
          $(function(){
        	  $('#plineAnalyList').dropdownlist({
                  id:'plineListTxt',
                  columns:3,
                  selectedtext:'',
                  listboxwidth:450,//下拉框宽
                  maxchecked:100,
                  checkbox:true,
                  listboxmaxheight:400,
                  width:120,
                  requiredvalue:[],
                  selected:[${vo.plineListTxt}],
                  data:${jsonLines},//数据，格式：{value:name}
                  onchange:function(text,value){
                  }
              });
              
          });
      </script>
  </span> 	
<script type="text/javascript">
//加载产线
function loadPlAnalyCondition()
{
	var url = "<c:url value='qms/common/productLineOps.do' />";
	var factory = $('#factory', navTab.getCurrentPanel()).val();
	var plineListTxt = $('#plineListTxt', navTab.getCurrentPanel()).val();
	var jsonVar = {factory:factory,plineListTxt:plineListTxt,plineDocId:"plineAnalyList"};
	delete jsonVar["plineListTxt"];
	$("#plineAnalyTd", navTab.getCurrentPanel()).load(url,jsonVar);
}

var width = 0;
var width1 = 0;
var width2 = 0;
var detailFunctionName = 'openPlineTab';
jQuery(document).ready(function(){
	//loadPlineAnalysisChart();  
	width = $(".pageContent",navTab.getCurrentPanel()).width()*0.95;
	width1 = parseInt(width/2);
	width2 = parseInt(width/3);
	$("#pline_out_div",navTab.getCurrentPanel()).hide(); 
	var queryMonth = '${vo.queryMonth}';
	var productType = '${vo.productType}';
	if(queryMonth!="" && productType!="" ){
	    loadPlineAuto(queryMonth,productType);
	}
});
    
function loadPlineAuto(queryMonth,productType){
   var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
   var url_1 = "<c:url value='plineChart/getPlineInfo.do'/>";
   
	$.post(url_1, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否',faultReasonValid:'是',faultReasonValid:'是',width:parseInt(width),hight:200, xCount:(xCount-1)}, function(data) {

		if (data.result == 0) {
			//alert(data.chartsInfo.chartType);
			showChart("div_pline_1", data.chartsInfo);
			loadPlineChildChart(productType, queryMonth, data.chartsInfo, xCount,"auto");
			$("#pline_out_div",navTab.getCurrentPanel()).show();
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function openPlineTab(num, type){
	var paraValues = $("#anaPline_"+num, navTab.getCurrentPanel()).val();
	var arrys = paraValues.split(",");
	navTab.closeTab('anaChartTab');
	navTab.openTab('anaChartTab', "partTypeChart/detailInfo.do", { title:'排列图-详细信息', fresh:false, data:{productType:arrys[0],queryMonth:arrys[1],productLineNumber:arrys[2], typeNum:type} });
}

function loadPlineAnalysisChart() {

	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
    if(productType==""){
        alert("请选择机型类别");
        return false;
    }
    
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if(queryMonth == ''){
		alert('请选择维修截至月份');
		return false;
	}
	var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
	
	var url_1 = "<c:url value='plineChart/getPlineInfo.do'/>";
	$.post(url_1, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否',faultReasonValid:'是',width:parseInt(width),hight:200, xCount:(xCount-1)}, function(data) {

		if (data.result == 0) {
			showChart("div_pline_1", data.chartsInfo);
			loadPlineChildChart(productType, queryMonth, data.chartsInfo, xCount,"analysis");
			$("#pline_out_div",navTab.getCurrentPanel()).show();
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	
}


function loadPlineChildChart(productType, queryMonth, data, xCount,loadType){
	var xValue = data.xValue;
	$.ajaxSetup({ 
	    async : false 
	});
	var child_length = 0;
	for(;child_length<xValue.length;child_length++){ 
		//
		if(child_length==3){
			child_length = 0;
			break;
		}
		var tmpX = xValue[child_length];
		tmpX = tmpX.replace(/(^\s+)|(\s+$)/g,"");
		if (tmpX == null || tmpX == "" || tmpX == undefined) {
			var tempIndex = child_length + 1;
			tmpX = xValue[tempIndex];
		}
		//选择单个型号
		if(loadType=="analysis"){
			var selectedvalue = $("#plineAnalyList",navTab.getCurrentPanel()).find(".ddl-select").attr("selectedtext");
			if(selectedvalue!=null && selectedvalue!="" && selectedvalue!="请选择"){
				tmpX = selectedvalue;
				$("#pline1",navTab.getCurrentPanel()).hide();
				$("#plChart1",navTab.getCurrentPanel()).hide();
				$("#pline2",navTab.getCurrentPanel()).hide();
				$("#plChart2",navTab.getCurrentPanel()).hide();
				if(child_length>=1){
					break;
				}
			}else{
				$("#pline1",navTab.getCurrentPanel()).show();
				$("#plChart1",navTab.getCurrentPanel()).show();
				$("#pline2",navTab.getCurrentPanel()).show();
				$("#plChart2",navTab.getCurrentPanel()).show();
			}
		}
	    $("#pline"+child_length).html((child_length + 1) + '. ' + tmpX);
		//chart1
		var url_sx = "<c:url value='timeChart/getTimeOnlyTotalInfo.do'/>";
		$.post(url_sx, {productType:productType,queryMonth:queryMonth,isOver:'否',faultReasonValid:'是',productLineNumber:tmpX,width:width1,hight:100}, function(data) {
			//alert(child_length);
			if (data.result == 0) {
				var passData = productType+","+queryMonth+","+tmpX;
				data.chartsInfo.index = child_length;
				data.chartsInfo.type = 0;
				data.chartsInfo.funcName = detailFunctionName;
				showChart("div_pline_sx_"+child_length, data.chartsInfo);
				$("#div_pline_sx_a_"+child_length).empty();
				$("#div_pline_sx_a_"+child_length).append("<input type='hidden' id='anaPline_"+child_length+"' name='anaPline_"+child_length+"' value='"+passData+"'/>");
				
			} else {
				alertMsg.error("加载时序图出错，请联系管理员");
				return ;
			}
		});
		//chart2
		var url_sxp = "<c:url value='timeMatrixTable/getTimeTotalInfo.do'/>";
		$.post(url_sxp, {productType:productType,queryMonth:queryMonth,isOver:'否',faultReasonValid:'是',productLineNumber:tmpX,width:width1,hight:100}, function(data) {
			//alert(child_length);
			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 1;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_pline_sxp_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载P控制图出错，请联系管理员");
				return ;
			}
		});
		//chart3
		var url_r = "<c:url value='regionChart/getRegionInfo.do'/>";
		$.post(url_r, {productType:productType,queryMonth:queryMonth,chartType:'column',xCount:6,isOver:'否',faultReasonValid:'是',productLineNumber:tmpX,width:width2,hight:100}, function(data) {

			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 2;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_pline_r_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载区域排列图出错");
				return ;
			}
		});
		//chart4
		var url_p = "<c:url value='partTypeChart/getPartTypeInfo.do'/>";
		$.post(url_p, {productType:productType,queryMonth:queryMonth,chartType:'column',xCount:6,isOver:'否',faultReasonValid:'是',productLineNumber:tmpX,width:width2,hight:100}, function(data) {

			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 6;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_pline_p_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载型号排列图出错");
				return ;
			}
		});
		//chart5
		var url_g = "<c:url value='faultReasonChart/getFaultReasonInfo.do'/>";
		$.post(url_g, {productType:productType,queryMonth:queryMonth,chartType:'column',xCount:6,isOver:'否',faultReasonValid:'是',productLineNumber:tmpX,width:width2,hight:100}, function(data) {

			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 4;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_pline_g_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载故障小类排列图出错");
				return ;
			}
		});
	}
	$.ajaxSetup({ 
	    async : true 
	});
}
function checkPass(num){
	var reg = new RegExp("^-?\\d+$");
	if(num.match(reg)==null){
        alert("X轴数量请输入整数!");
        return false;
    }
    if(num<5){
    	alert("X轴数量不得小于5!");
        return false;
    }
    return true;
}				                                            				
</script>