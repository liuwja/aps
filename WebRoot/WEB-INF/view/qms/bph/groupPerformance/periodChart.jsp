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
	$("#baseFactory_${id_end}",navTab.getCurrentPanel()).change(function(){
		loadShiftGroup();
	})
	$("#baseArea_${id_end}",navTab.getCurrentPanel()).change(function(){
		loadShiftGroup();
	})		
});

function loadPeriodChart() {

	var shiftGroup = $("#shiftGroup", navTab.getCurrentPanel()).val();
	var region = $("#region",navTab.getCurrentPanel()).val();
	var factory =  $("#baseFactory_${id_end}",navTab.getCurrentPanel()).val();
	var area = $("#baseArea_${id_end}",navTab.getCurrentPanel()).val();
    
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	if(startTime == ''){
		alert('请选择开始月份');
		return false;
	}
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
	if(endTime == ''){
		alert('请选择结束月份');
		return false;
	}
	if(factory!="" || area!=""){
		if(shiftGroup !=""){
			factory ="";
			area ="";
		}
	}
	
	var url = "<c:url value='shiftGroupPerformanceChart/getPerformancePeriodChart.do'/>";
	$.post(url, {shiftGroupTxt:shiftGroup,startTime:startTime,endTime:endTime,region:region ,factory:factory,area:area}, function(data) {

		if (data.result == 0) {
			showChart("periodPerformanceChart", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function loadShiftGroup(){
	var factory = $("#baseFactory_${id_end}",navTab.getCurrentPanel()).val();
	var area = $("#baseArea_${id_end}",navTab.getCurrentPanel()).val();
	var url = "<c:url value='shiftGroupPerformanceChart/loadShiftGroup.do'/>";
	$("#shiftGroupLoad", navTab.getCurrentPanel()).load(url,{factory:factory,area:area});
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
					<jsp:param value="0" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>
                <th>班组名称：</th>
                <td>
					<div id="shiftGroupList" class="dropdownlist"></div>
					
    			</td>
    					    			
				<th>期间：</th>
                <td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime }" readonly="readonly"/>&nbsp;至&nbsp;
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime }" readonly="readonly"/>
					<input type="hidden" id="region" value="（期间）">
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadPeriodChart();">查找</button></div></div>
				</td>				
			</tr>

		</table>
	</div>
	</form>
<div id="shiftGroupLoad">
<script type="text/javascript">
$(function(){	
$('#shiftGroupList',navTab.getCurrentPanel()).dropdownlist({
							    id:'shiftGroup',
							    columns:3,
							    selectedtext:'',
							    listboxwidth:450,//下拉框宽
							    maxchecked:100,
							    checkbox:true,
							    listboxmaxheight:400,
							    width:150,
							    requiredvalue:[],
							    selected:[${vo.baseGroup}],
							    data:${jsonParts},//数据，格式：{value:name}
							    onchange:function(text,value){
							    }
							});
							
						});
</script>
</div>	

<div class="pageContent">
		<!-- id每个页面要不一样 -->
		<div id="periodPerformanceChart" class="singleChartDiv"></div>
</div>