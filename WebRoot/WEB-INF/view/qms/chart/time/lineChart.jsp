<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadLineChart();  
});

function loadLineChart() {

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
	
	var url = "<c:url value='timeChart/getLineInfo.do'/>";
	$.post(url, {productType:productType,queryMonth:queryMonth}, function(data) {

		if (data.result == 0) {
			showChart("lineChart", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}			                                            				
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
                <th>机型类别：</th>
                <td>
					<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }">${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 			
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${queryMonth }" readonly="readonly"/>
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadLineChart();">查找</button></div></div>
				</td>				
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
</div>	<!-- id每个页面要不一样 -->
		<div id="lineChart" class="singleChartDiv">
</div>