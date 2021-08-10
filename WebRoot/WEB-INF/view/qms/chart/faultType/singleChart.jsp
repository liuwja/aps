<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
	/*
	.btn{position:absolute;top: 6px;right: 499px;display: block;}*/
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadfaultTypeChart();
	var queryMonth = '${vo.queryMonth}';
	var productType = '${vo.productType}';
	if(queryMonth!="" && productType!="" ){
	  loadFaultTypeAuto(queryMonth,productType);
	}  
});

function loadFaultTypeAuto(queryMonth,productType){
   var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
   var chartType = $('select[name="chartType"]', navTab.getCurrentPanel()).val();
   var url_1 = "<c:url value='faultTypeChart/getFaultTypeInfo.do'/>";
	$.post(url_1, {productType:productType,queryMonth:queryMonth,chartType:'column',width:1000,hight:200, xCount:(xCount-1)}, function(data) {

		if (data.result == 0) {
			//alert(data.chartsInfo.chartType);
			showChart("faultTypeChart", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function loadFaultTypeChart() {

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
	
	var faultTypeTxt = $("#FT_name", navTab.getCurrentPanel()).val();

	var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
	
	var chartType = $('select[name="chartType"]', navTab.getCurrentPanel()).val();
	var url = "<c:url value='faultTypeChart/getFaultTypeInfo.do'/>";
	$.post(url, {productType:productType,queryMonth:queryMonth,faultTypeTxt:faultTypeTxt,chartType:chartType, xCount:(xCount-1)}, function(data) {

		if (data.result == 0) {
			showChart("faultTypeChart", data.chartsInfo);
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
function clearAll(){
	$("#FT_name",navTab.getCurrentPanel()).val("");
	$("#FT_id",navTab.getCurrentPanel()).val("");
	$("#FT_code",navTab.getCurrentPanel()).val("");
}
function singleftSel(obj){
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultTypeSelect.do?groupName=FT";
	$(obj).attr("href",href+"&productType="+productType);
}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<th>图形：</th>
                <td>
                	<select name="chartType" onchange="loadFaultTypeChart()">
						<option value="column">柱状图</option>
						<option value="line">折线图</option>
					</select>
    			</td>
                <th>机型类别：</th>
                <td>
					<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" <c:if test="${o.machineType==vo.productType}">selected</c:if>>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td>
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="2" name="xCount" id="xCount" value="${vo.xCount+1 }" maxlength="2"/>
    			</td> 		
    			<th>故障大类：</th>
    			<td>
    				<input type="hidden" name="faultTypeID" id="FT_id" size="15" readonly="true" value="${o.id}"/>  
    				<input type="hidden" name="code" id="FT_code" size="15" readonly="true" value="${o.code}"/>  
    				<input type="text" name="faultTypeTxt" id="FT_name" size="20" readonly="true" value="${o.name}" style="float: left;"/>    				
    				<a onclick="singleftSel(this);" id="btn" class="btnLook btn" href="qms/commonSelect/faultTypeSelect.do?groupName=FT" width=950 height=500 lookupGroup="FT">故障大类选择</a>
    				<a class="btnClear" href="javascript:void(0);" onclick="clearAll()" ></a> 
					
    			</td>	
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" size="8" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadFaultTypeChart();">查找</button></div></div>
				</td>				
			</tr>

		</table>
	</div>
	</form>
</div>
<div class="pageContent">
		<!-- id每个页面要不一样 -->
		<div id="faultTypeChart" class="singleChartDiv"></div>
</div>