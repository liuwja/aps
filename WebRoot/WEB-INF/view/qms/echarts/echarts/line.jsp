<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
<!--

-->
</style>
<script type="text/javascript">
$(function(){
})
    //折线图
	function loadLineChart() {
	
	var url = "<c:url value='report/echart/totestLine.do'/>";
	$.post(url, function(data) {

		if (data.result == 0) {
			showEChart("echartTestline", data.info);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}
   function loadPieBarChar(){
	var url = "<c:url value='report/echart/totestPieBar.do'/>";
		$.post(url, function(data) {
			if (data.result == 0) {
				showEchartPieBarChar("echartTestline","echartTestbar", data.info);
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
		});

   }
   
   function loadPieChar(){
		var url = "<c:url value='report/echart/totestPie.do'/>";
			$.post(url, function(data) {
				if (data.result == 0) {
					showEChart("echartTestline", data.info);
				} else {
					alertMsg.error("查询出错，请联系管理员");
					return ;
				}
			});

	   }

   function loadBarBarChart(){
	   var url = "<c:url value='report/echart/totestBarBar.do'/>";
		$.post(url, function(data) {
			if (data.result == 0) {
				showEchartBarBarChar("echartTestline","echartTestbar", data.info);
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
		});
   }

   function loadBarPieChart(){
		var url = "<c:url value='report/echart/totestBarPie.do'/>";
			$.post(url, function(data) {
				if (data.result == 0) {
					showEchartBarPieChar("echartTestline","echartTestbar", data.info);
				} else {
					alertMsg.error("查询出错，请联系管理员");
					return ;
				}
			});

	   }
   function loadtest(){
	   navTab.openTab("newTestTab", "report/echart/test.do", "");
   }
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post" >
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			
				             				
				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadLineChart();">折线图</button></div></div>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadPieBarChar()">饼图 和柱状图联动</button></div></div>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadPieChar()">饼图 </button></div></div>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadBarBarChart()">柱状图和柱状图的联动 </button></div></div>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadBarPieChart()">柱状图和饼图的联动 </button></div></div>
				</td>	
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent"   style="height: 500px;overflow: auto; margin-top: 50px;">
	<div id="echartTestline" style="width: 53%;float:left;height: 300px;"></div>
   
    <div id="echartTestbar" style="width: 45%;float:left;height: 300px;"></div>
</div>
 <script type="text/javascript">
 </script>