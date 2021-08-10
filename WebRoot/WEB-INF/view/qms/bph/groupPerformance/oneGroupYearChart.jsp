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
	var flag = "${vo.chartType}";
	if(flag=="char"){
		var shiftGroup = "${vo.baseGroup}";
		var queryMonth ="${vo.startTime}";
		var factory ="${vo.baseFactory}";
		var url = "<c:url value='groupPerformanceChart/getOneGroupPerfanceYear.do'/>";
		$.post(url, {shiftGroupTxt:shiftGroup,startTime:queryMonth}, function(data) {
            data.chartsInfo.shiftGroup = shiftGroup;
            data.chartsInfo.factory = factory;
			if (data.result == 0) {
				ftjshowChart("oneGroupYearChart", data.chartsInfo, queryMonth, shiftGroup);
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
		});
	}
});

function loadOneGroupYearChart() {

	var shiftGroup = $("#baseGroup_${id_end}", navTab.getCurrentPanel()).val();
	var region = $("#region",navTab.getCurrentPanel()).val();
	var factory = $("#baseFactory_${id_end}",navTab.getCurrentPanel()).val();
	var area = $("#baseArea_${id_end}",navTab.getCurrentPanel()).val();
    
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if(queryMonth == ''){
		alert('请选择年度');
		return false;
	}
	if(shiftGroup == ''){
		alert('请选择班组');
		return false;
	}
	var url = "<c:url value='groupPerformanceChart/getOneGroupPerfanceYear.do'/>";
	$.post(url, {shiftGroupTxt:shiftGroup,startTime:queryMonth}, function(data) {
		 data.chartsInfo.shiftGroup = shiftGroup;
		 data.chartsInfo.factory = factory;
		if (data.result == 0) {
			ftjshowChart("oneGroupYearChart", data.chartsInfo, queryMonth, shiftGroup);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

</script>

	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar dropdownSearchBar">
		<table class="searchContent" >
			<tr style="height: 35px;line-height: 28px">
			   <jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="1" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>
    					    			
				<th>月份：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime }" readonly="readonly"/>
					<input type="hidden" id="region" value="（单月）">
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" id="sigbutton" onclick="loadOneGroupYearChart();">查找</button></div></div>
				</td>				
			</tr>

		</table>
	</div>
	</form>

<div class="pageContent">
		<!-- id每个页面要不一样 -->
		<div id="oneGroupYearChart" class="singleChartDiv"></div>
</div>