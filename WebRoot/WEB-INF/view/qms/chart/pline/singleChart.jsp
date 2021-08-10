<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadPlienChart();  
});

function loadPlienChart() {

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

	var factory = $('#factory', navTab.getCurrentPanel()).val();
	var plineListTxt = $('#plineListTxt', navTab.getCurrentPanel()).val();
	
	var url = "<c:url value='plineChart/getPlineInfo.do'/>";
	$.post(url, {productType:productType,queryMonth:queryMonth, xCount:(xCount-1), plineListTxt:plineListTxt, factory:factory}, function(data) {

		if (data.result == 0) {
			showChart("plineChart", data.chartsInfo);
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

function loadPlineCondition()
{
	var url = "<c:url value='qms/common/productLineOps.do' />";
    var factory = $('#factory', navTab.getCurrentPanel()).val();
	$("#plineTd").load(url,{factory:factory,plineDocId:"plineList"});
}
</script>
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar dropdownSearchBar" > 
		<table class="searchContent">
			<tr style="height: 35px;line-height: 28px">
                <th>机型类别：</th>
                <td>
					<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 
    			<th>工厂：</th>
                <td>
					<select name="factory" id="factory" onchange="loadPlineCondition();">
							<option value="">请选择</option>
							<c:forEach items="${factorys}" var="o">
							<option value="${o.factory }" <c:if test="${vo.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
							</c:forEach>
					</select>
    			</td>
    			<th>产线：</th>                       
                <td>
                	<div id="plineList" class="dropdownlist"></div>
                </td>
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="10" name="xCount" id="xCount" value="${vo.xCount+1 }"/>
    			</td>			
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 			
				<td id="plineTd">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadPlienChart();">查找</button></div></div>
					<script type="text/javascript">
						$(function(){
			
							$('#plineList').dropdownlist({
							    id:'plineListTxt',
							    columns:3,
							    selectedtext:'',
							    listboxwidth:450,//下拉框宽
							    maxchecked:100,
							    checkbox:true,
							    listboxmaxheight:400,
							    width:120,
							    requiredvalue:[],
							    selected:[],
							    data:${jsonLines},//数据，格式：{value:name}
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
		<div id="plineChart" class="singleChartDiv"></div>
</div>