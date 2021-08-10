<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css" media="screen">
	${demo.css}
	
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
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	$("#detailPartTimeDiv").hide();
	setTimeout("loadDetailPartTime()",50);
});

function loadDetailPartTime() {
	var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
	var typeNum = '${vo.typeNum}';
	var queryMonth = '${vo.queryMonth}';
	var productType = '${vo.productType}';
	var partType = '${vo.partType}';
	var region = '${vo.region}';
	var productLineNumber = '${vo.productLineNumber}';
	var faultReasonName = '${vo.faultReasonName}';
	var jsonVar = {productType:productType,queryMonth:queryMonth,partType:partType,region:region,productLineNumber:productLineNumber,faultReasonName:faultReasonName,xCount:(xCount-1)};
	if(productLineNumber == ""){
		 delete jsonVar["productLineNumber"]; 
	}
	if(faultReasonName == ""){
		delete jsonVar["faultReasonName"]; 
	}
	if(partType == ""){
		delete jsonVar["partType"]; 
	}
	if(region == ""){
		delete jsonVar["region"]; 
	}
	var url = "";
	if(typeNum=='0'){//时序图
		$("#detailPartTimeDiv").hide();
		url = "<c:url value='timeChart/getTimeInfo.do'/>";
	}else if(typeNum=='1'){//P空图
		$("#detailPartTimeDiv").hide();
		url = "<c:url value='timeMatrixTable/getTimeTotalInfo.do'/>";
	}else if(typeNum=='2'){//故障小类图累计
		$("#detailPartTimeDiv").show();
		url = "<c:url value='faultReasonChart/getFaultReasonInfo.do'/>";
	}else if(typeNum=='3'){//故障小类图单月
		$("#detailPartTimeDiv").show();
		jsonVar = {productType:productType,queryMonth:queryMonth,chartType:'column',xCount:(xCount-1)};
		url = "<c:url value='faultReason2Chart/getFaultReasonInfo.do'/>";
	}
	$.post(url, jsonVar, function(data) {

		if (data.result == 0) {
			showChart("detailChart", data.chartsInfo);
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
<div class="pageHeader" id="detailPartTimeDiv">
	<form onsubmit="return false;" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr> 
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="10" name="xCount" id="xCount" value="${vo.xCount+1 }"/>
    			</td>			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadDetailChart();">查找</button></div></div>
				</td>				
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
		<div id="detailChart" class="singleChartDiv"></div>
</div>