<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
function loadunderWarrantyChart() {
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
    var isConsumedPart = $("#isConsumedPart",navTab.getCurrentPanel()).val();
    var url = "<c:url value='timeMatrixTable/getUnderWarrantyChartData.do'/>";
    $.post(url, {productType:productType,queryMonth:queryMonth,isConsumedPart:isConsumedPart,chartType:'column',isOver:'否',faultReasonValid:'是',width:1000,hight:200}, function(data) {
    	if (data.result == 0) {
    		showUnderWarrantyChar("underWarrantyChart", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
    });
}
</script>
<div class="pageHeader" style="position:static">
	<form  onsubmit="return navTabSearch(this);" id="timeMatrixForSingle" rel="pagerForm" action="timeMatrixTable/underWarrantyChart.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<th>机型类别：</th>
	                <td>
						<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
						</select>
	    			</td>
	    			<th>维修日期：</th>
                	<td>
                    	<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly" size="8"/>
                	</td>
                	<th>是否消耗配件：：</th>
                	<td>
						<select id="isConsumedPart" name = "isConsumedPart">
							<option value ="">全选</option>
							<option value ="是" <c:if test="${vo.isConsumedPart=='是'}">selected="selected"</c:if> >是</option>
							<option value ="否" <c:if test="${vo.isConsumedPart=='否'}">selected="selected"</c:if> >否</option>
						</select>
                	</td>
                	<td>
                		<div class="buttonActive">
                			<div class="buttonContent"><button type="button" onclick="loadunderWarrantyChart();">查找</button></div>
                			<div class="buttonContent"><button type="button" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '保内时序图', '时间序列图');">数据来源</button></div>
                		</div>
                	</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div id="underWarrantyChart" class="singleChartDiv" style="height: 450px"></div>
</div>