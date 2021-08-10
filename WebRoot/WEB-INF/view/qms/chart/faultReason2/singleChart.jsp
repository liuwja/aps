<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
	/*
	.btn{position:absolute;top: 6px;right: 499px;display: block;}*/
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadFaultReason2Chart();  
});

function loadFaultReason2Chart() {

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
	
	var faultReasonTxt = $("#FR1_code", navTab.getCurrentPanel()).val();
	
	var chartType = $('select[name="chartType"]', navTab.getCurrentPanel()).val();
	var url = "<c:url value='faultReason2Chart/getFaultReasonInfo.do'/>";
	$.post(url, {productType:productType,queryMonth:queryMonth,chartType:chartType, xCount:(xCount-1),faultReasonTxt:faultReasonTxt}, function(data) {

		if (data.result == 0) {
			showChart("faultReason2Chart", data.chartsInfo);
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
function clearAll_two(){
	$("#FR1_meshname").val("");
	$("#FR1_id").val("");
	$("#FR1_code").val("");
}
function sigleRerfSel(obj){
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultReasonSelect.do?groupName=FR1";
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
                	<select name="chartType" onchange="loadFaultReason2Chart();">
						<option value="column">柱状图</option>
						<option value="line">折线图</option>
					</select>
    			</td>
                <th>机型类别：</th>
                <td>
					<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }">${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 	
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="10" name="xCount" id="xCount" value="${vo.xCount+1 }"/>
    			</td>
    			<th>故障小类：</th>
    			<td>
    				<input type="hidden" name="faultTypeID" id="FR1_id" size="15" readonly="true" value=""/>  
    				<input type="hidden" name="code" id="FR1_code" size="15" readonly="true" value=""/>  
    				<input type="text" name="faultReasonTxt" id="FR1_meshname" size="20" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a onclick="sigleRerfSel(this);" id="btn" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?groupName=FR1" width=950 height=500 lookupGroup="FR1">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearAll_two()" ></a> 
    			</td>		
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadFaultReason2Chart();">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
		<!-- id每个页面要不一样 -->
		<div id="faultReason2Chart" class="singleChartDiv"></div>
</div>

