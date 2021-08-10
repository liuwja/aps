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
	//loadPartTypeChart();  
});

function loadPartTypeChart() {

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

	var partTypeListTxt = $('#partTypeListTxt', navTab.getCurrentPanel()).val();
	
	var url = "<c:url value='partTypeChart/getPartTypeInfo.do'/>";
	$.post(url, {productType:productType,queryMonth:queryMonth, xCount:(xCount-1), partTypeListTxt:partTypeListTxt}, function(data) {

		if (data.result == 0) {
			showChart("partTypeChart", data.chartsInfo);
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

function loadPartTypeCondition()
{
	var url = "<c:url value='qms/common/partTypeLineOps.do' />";
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
    if(productType==""){
        alert("请选择机型类别");
        return false;
    }
	$("#partTypeTd").load(url,{productType:productType,partTypeDocId:"partTypeList"});
}

</script>
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar dropdownSearchBar" >
		<table class="searchContent">
			<tr style="height: 35px;line-height: 28px">
                <th>机型类别：</th>
                <td>
					<select name="productType" onchange="loadPartTypeCondition();">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td>
    			<th>型号：</th>                       
                <td>
                	<div id="partTypeList" class="dropdownlist"></div>
                </td>
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="10" name="xCount" id="xCount" value="${vo.xCount+1 }"/>
    			</td> 			
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 			
				<td id="partTypeTd">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadPartTypeChart();">查找</button></div></div>
					<script type="text/javascript">
						$(function(){
							$('#partTypeList').dropdownlist({
							    id:'partTypeListTxt',
							    columns:3,
							    selectedtext:'',
							    listboxwidth:450,//下拉框宽
							    maxchecked:100,
							    checkbox:true,
							    listboxmaxheight:400,
							    width:120,
							    requiredvalue:[],
							    selected:[],
							    data:${jsonParts},//数据，格式：{value:name}
							    onchange:function(text,value){
							    }
							});
							
						});
					</script>
				</td>				
			</tr>
		</table>
	</div>
	</form>
<div class="pageContent">
		<div id="partTypeChart" class="singleChartDiv"></div>
</div>