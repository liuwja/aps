<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}

.div_border{
	border-right:1px solid #6495ED;
	border-top:1px solid #6495ED;
	border-bottom:1px solid #6495ED;
	width:100%;
	margin-right:-1px; 
	margin-bottom:-1px;
}
.div_topdown_border{
	border-bottom:1px solid #6495ED;
}
.div_right_border{
	border-right:1px solid #6495ED;
}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	$("#sumpartType_out_div",navTab.getCurrentPanel()).hide(); 
	$('select[name="productType"]', navTab.getCurrentPanel()).poshytip({
		showOn: 'none',
		alignTo: 'target',
		alignX: 'center',
		alignY: 'top',
		showTimeout:0,
		offsetX:20,
		offsetY:5
	}); 
});

function openSumPartTypeTab(num, type){
	var paraValues = $("#sumAnaPartType_"+num, navTab.getCurrentPanel()).val();
	var arrys = paraValues.split(",");
	navTab.closeTab('sumAnaChartTab');
	navTab.openTab('sumAnaChartTab', "partTypeChart/detailPartTime.do", { title:'排列图-详细信息', fresh:false, data:{productType:arrys[0],queryMonth:arrys[1],typeNum:type}});
}

function openAnalysisTypeChar(){
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
	navTab.openTab('partTypeAnalysisChart', "partTypeChart/analysisChart.do", { title:'型号排列图', fresh:false, data:{productType:productType,queryMonth:queryMonth,chartType:'column',xCount:(xCount-1)}});
}

function openAnalysisRegionChar(){
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
	navTab.openTab('regionAnalysisChart', "regionChart/analysisChart.do", { title:'区域排列图', fresh:false, data:{productType:productType,queryMonth:queryMonth,xCount:(xCount-1)}});
}

function openAnalysisPlineChar(){
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
	navTab.openTab('plineAnalysisChart', "plineChart/analysisChart.do", { title:'产线排列图', fresh:false, data:{productType:productType,queryMonth:queryMonth,chartType:'column',xCount:(xCount-1)}});
}

function openAnalysisFaultTypeChar(){
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
	navTab.openTab('faultTypeCharts', "faultTypeChart/analysisChart.do", { title:'故障大类排列图', fresh:false, data:{productType:productType,queryMonth:queryMonth,chartType:'column',xCount:(xCount-1)}});
}

function openAnalysisFaultResonChar(){
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
	navTab.openTab('faultReasonAnalysisChart', "faultReasonChart/analysisChart.do", { title:'故障小类排列图', fresh:false, data:{productType:productType,queryMonth:queryMonth,chartType:'column',xCount:(xCount-1)}});
}

function loadPartTypeChart() {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
    if(productType==""){
        $('select[name="productType"]', navTab.getCurrentPanel()).poshytip("show");
        $('select[name="productType"]', navTab.getCurrentPanel()).poshytip('hideDelayed', 3000);
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
	var url_1 = "<c:url value='timeChart/getTimeInfo.do'/>";
	$.post(url_1, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否',faultReasonValid:'是',xCount:(xCount-1),width:500,hight:100}, function(data) {
		if (data.result == 0) {
		    var passData = productType+","+queryMonth;
		    data.chartsInfo.index = 1;
		    data.chartsInfo.type = 0;
		    data.chartsInfo.funcName = 'openSumPartTypeTab';
			showChart("sumPartTypeChart", data.chartsInfo);
			loadPartTypeChildChart(productType, queryMonth, data.chartsInfo, xCount);			
			$("#sumpartType_out_div",navTab.getCurrentPanel()).show();
			$("#sumPartType_child",navTab.getCurrentPanel()).empty();
		    $("#sumPartType_child",navTab.getCurrentPanel()).append("<input type='hidden' id='sumAnaPartType_"+1+"' name='sumAnaPartType_"+1+"' value='"+passData+"'/>");
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function loadPartTypeChildChart(productType, queryMonth, data, xCount){
	var faultReasonTxt = $("#FR_name", navTab.getCurrentPanel()).val();
	var url = "<c:url value='timeMatrixTable/getTimeTotalInfo.do'/>";
	$.post(url, {productType:productType,queryMonth:queryMonth,isOver:'否',faultReasonValid:'是',width:500,hight:100}, function(data) {
		if (data.result == 0) {
	    	var passData = productType+","+queryMonth;
		    data.chartsInfo.index = 2;
		    data.chartsInfo.type = 1;
		    data.chartsInfo.funcName = 'openSumPartTypeTab';
			showChart("sumTimeTotalPChart", data.chartsInfo);
			$("#sumTimeTotalPChart_child").empty();
			$("#sumTimeTotalPChart_child").append("<input type='hidden' id='sumAnaPartType_"+2+"' name='sumAnaPartType_"+2+"' value='"+passData+"'/>");
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	
	var url = "<c:url value='faultReasonChart/getFaultReasonInfo.do'/>";
	$.post(url, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否', faultReasonValid:'是', xCount:(xCount-1),faultReasonTxt:faultReasonTxt,width:500,hight:100}, function(data) {
		if (data.result == 0) {
            var passData = productType+","+queryMonth;
            data.chartsInfo.index = 3;
		    data.chartsInfo.type = 2;
		    data.chartsInfo.funcName = 'openSumPartTypeTab';
			showChart("sumFaultReasonChart", data.chartsInfo);
			$("#sumFaultReasonChart_child",navTab.getCurrentPanel()).empty();
			$("#sumFaultReasonChart_child",navTab.getCurrentPanel()).append("<input type='hidden' id='sumAnaPartType_"+3+"' name='sumAnaPartType_"+3+"' value='"+passData+"'/>");
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	
	var url = "<c:url value='faultReason2Chart/getFaultReasonInfo.do'/>";
	$.post(url, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否', faultReasonValid:'是', xCount:(xCount-1),width:500,hight:100}, function(data) {
		if (data.result == 0) {
		    var passData = productType+","+queryMonth;
		    data.chartsInfo.index = 4;
		    data.chartsInfo.type = 3;
		    data.chartsInfo.funcName = 'openSumPartTypeTab';
			showChart("sumFaultReasonByMonthChart", data.chartsInfo);
			$("#sumFaultReasonByMonthChart_child",navTab.getCurrentPanel()).empty();
			$("#sumFaultReasonByMonthChart_child",navTab.getCurrentPanel()).append("<input type='hidden' id='sumAnaPartType_"+4+"' name='sumAnaPartType_"+4+"' value='"+passData+"'/>");
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
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
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent nowrap">
			<tr>
                <th>机型类别：</th>
                <td>
					<select name="productType" title="请选择机型类别">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }">${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="2" maxlength="2" name="xCount" id="xCount" value="${vo.xCount+1 }" title="ssss"/>
    			</td> 			
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${laterSumtime}" readonly="readonly" size="8"/>
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadPartTypeChart();">查找</button></div></div>
				</td>	
				<td>
				    <div class="buttonActive"><div class="buttonContent"> <button type="button" onclick="openAnalysisTypeChar()">型号排列图</button> </div></div>
				    <div class="buttonActive"><div class="buttonContent"> <button type="button" onclick="openAnalysisRegionChar()">区域排列图</button></div></div>
				    <div class="buttonActive"><div class="buttonContent"> <button type="button" onclick="openAnalysisPlineChar()">产线排列图</button> </div></div>
			    	<div class="buttonActive"><div class="buttonContent"> <button type="button" onclick="openAnalysisFaultTypeChar()">故障大类排列图</button></div></div>
				    <div class="buttonActive"><div class="buttonContent"> <button type="button" onclick="openAnalysisFaultResonChar()">故障小类排列图</button></div></div>
				</td>				         		
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent div_border" style="height: 500px;overflow: auto;" id="sumpartType_out_div">
		<!-- id每个页面要不一样 -->
	<div class="div_topdown_border"  style="width:100%; height:300px;">
		<div class="div_topdown_border"  style="width:50%; float:left;height:300px;">
			<div id="sumPartType_child" style="width:49.5%; height:15px;"></div>
			<div id="sumPartTypeChart" style="width:49.5%; float:left;height:250px;"></div>
		</div>
	
		<div class="div_topdown_border"  style="width:50%; float:left;height:300px;">
			<div id="sumTimeTotalPChart_child" style="width:49.5%; height:15px;"></div>
			<div id="sumTimeTotalPChart"  style="width:49.5%; float:left;height:250px;"></div>
	    </div>
	</div>
	
	<div class="div_topdown_border"  style="width:100%; height:300px;">
		<div class="div_topdown_border" style="width:50%; float:left;height:300px;">
			<div id="sumFaultReasonChart_child" style="width:49.5%; height:15px;"></div>
			<div id="sumFaultReasonChart" style="width:49.5%; float:left;height:250px;"></div>
   		</div>
		<div class="div_topdown_border"  style="width:50%; float:left;height:300px;">
			<div id="sumFaultReasonByMonthChart_child" style="width:49.5%; height:15px;"></div>
			<div id="sumFaultReasonByMonthChart" style="width:49.5%; float:left;height:250px;"></div>
		</div>
	</div>
</div>