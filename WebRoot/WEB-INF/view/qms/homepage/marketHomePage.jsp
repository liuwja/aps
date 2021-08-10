<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<style>
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,<br>legend,input,textarea,p,blockquote,th,td,hr,button,article,aside,<br>details,figcaption,figure,footer,header,hgroup,menu,nav,section {
    margin:0;
    padding:0;
}
</style>
<script>
jQuery(document).ready(function(){
	selectHomePageData();
});

function selectHomePageData() {
	selectDashboardData();
	var month = $("input[name='month']", navTab.getCurrentPanel()).val();
	if (month == null || month == '' || month == undefined) {
		month = getDate();
	}
	loadChar("油烟机", month);
}

function selectDashboardData() {
	var url = "<c:url value='system/homePage/getDashboardData.do'/>"; //仪表盘
	var month = $("input[name='month']", navTab.getCurrentPanel()).val();
	if (month == null || month == '' || month == undefined) {
		month = getDate();
	}
	var jsonData = {month:month};
	$.post(url,jsonData, function(data) {
		if (data.result == 0) {
			var list = data.list;
			showDashboardTest("dashboardDiv1", list[0]);
			showDashboardTest("dashboardDiv2", list[1]);
			showDashboardTest("dashboardDiv3", list[2]);
			showDashboardTest("dashboardDiv4", list[3]);
			showDashboardTest("dashboardDiv5", list[4]);
			showDashboardTest("dashboardDiv6", list[5]);
			showDashboardTest("dashboardDiv7", list[6]);
			showDashboardTest("dashboardDiv8", list[7]);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function loadChar(productType, queryMonth) {
	var screenWidth = Math.round((window.screen.width - 80) / 3);
	var url_1 = "<c:url value='timeChart/getTimeInfo.do'/>"; //时间序列图
	var url_2 = "<c:url value='timeMatrixTable/getTimeTotalInfo.do'/>"; //P控制图
	var url_3 = "<c:url value='partTypeChart/getPartTypeInfo.do'/>"; //型号排列图
	var url_4 = "<c:url value='faultTypeChart/getFaultTypeInfo.do'/>"; //故障大类排列图
	var url_5 = "<c:url value='faultReasonChart/getFaultReasonInfo.do'/>"; //故障小类排列图
	var url_6 = "<c:url value='regionChart/getRegionInfo.do'/>"; //区域排列图
	$.post(url_1, {productType:productType,queryMonth:queryMonth,faultReasonValid:'是',chartType:'column',xCount: 9,width:screenWidth,height:200}, function(data) {
		if (data.result == 0) {
			showChart("temporalSeriesDiv", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	$.post(url_2, {productType:productType,queryMonth:queryMonth,isOver:"否",faultReasonValid:'是',width:screenWidth,height:200}, function(data) {
		if (data.result == 0) {
			showChart("pControlDiv", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	$.post(url_3, {productType:productType,queryMonth:queryMonth,isOver:"否",faultReasonValid:'是',xCount: 9,width:screenWidth,height:200}, function(data) {
		if (data.result == 0) {
			showChart("productTypeDiv", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	$.post(url_4, {productType:productType,queryMonth:queryMonth,isOver:"否",faultReasonValid:'是',xCount: 9,width:screenWidth,height:200}, function(data) {
		if (data.result == 0) {
			showChart("faultTypeDiv", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	$.post(url_5, {productType:productType,queryMonth:queryMonth,isOver:"否",faultReasonValid:'是',xCount: 9,width:screenWidth,height:200}, function(data) {
		if (data.result == 0) {
			showChart("faultReasonDiv", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	$.post(url_6, {productType:productType,queryMonth:queryMonth,isOver:"否",faultReasonValid:'是',xCount: 9,width:screenWidth,height:200}, function(data) {
		if (data.result == 0) {
			showChart("regionDiv", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function getDate() { //默认时间为当前时间往成推两个月
	var myDate = new Date();
	var year = myDate.getFullYear();
	var month = myDate.getMonth();
	if (month == "0" || month == 0) {
		year = year - 1;
		month = 12;
	} else if (month == "1" || month == 1) {
		month = 1;
	} else {
		month = month - 1;
	}
	return year + "-" + month;
}

function showSelectDate() {
	$("#spanSelectDate").css("visibility", "visible");
	$("#month").css("visibility", "visible");
	$("#buttonSelect").css("visibility", "visible");
}

function hiddenSelectDate() {
	$("#spanSelectDate").css("visibility", "hidden");
	$("#month").css("visibility", "hidden");
	$("#buttonSelect").css("visibility", "hidden");
}
</script>
<div class="pageHeader" style="position:static">
	<div id="selectDateDiv" style="width: 100%;height: 20px; text-align: center;" onmousemove="showSelectDate()" onmouseout="hiddenSelectDate()">
		<span>截止日期：</span>
		<input name="month" type="text" size="12" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" value="${queryMonth}" />
		<button onclick="selectHomePageData()">查询</button>
	</div>
	<div style="width: 1600px; height: 175px; padding: 0px">
		<div id="dashboardDiv1" style="width: 180px;height: 175px; float: left; padding-right: 20px; margin: 0px"></div>
		<div id="dashboardDiv2" style="width: 180px;height: 175px; float: left; padding-right: 20px; margin: 0px"></div>
		<div id="dashboardDiv3" style="width: 180px;height: 175px; float: left; padding-right: 20px; margin: 0px"></div>
		<div id="dashboardDiv4" style="width: 180px;height: 175px; float: left; padding-right: 20px; margin: 0px"></div>
		<div id="dashboardDiv5" style="width: 180px;height: 175px; float: left; padding-right: 20px; margin: 0px"></div>
		<div id="dashboardDiv6" style="width: 180px;height: 175px; float: left; padding-right: 20px; margin: 0px"></div>
		<div id="dashboardDiv7" style="width: 180px;height: 175px; float: left; padding-right: 20px; margin: 0px"></div>
		<div id="dashboardDiv8" style="width: 180px;height: 175px; float: left; margin: 0px"></div>
	</div>
</div>
<div id="homePageChartDiv" class="pageContent" style="text-align: center; width: 100%;">
	<table>
		<tr>
			<td>
				<div id="temporalSeriesDiv"></div>
			</td>
			<td>
				<div id="pControlDiv"></div>
			</td>
			<td>
				<div id="productTypeDiv"></div>
			</td>
		</tr>
		<tr>
			<td>
				<div id="faultTypeDiv"></div>
			</td>
			<td>
				<div id="faultReasonDiv"></div>
			</td>
			<td>
				<div id="regionDiv"></div>
			</td>
		</tr>
	</table>
</div>