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
	var firstPress = "${firstPress}";
	if(firstPress == "firstPress"){
		var shiftGroup = "";
		var region = "";
		var factory = "电器一厂";
		var area = "";
		var queryMonth = "${vo.queryMonth }";
		var url = "<c:url value='groupPerformanceChart/getPerformanceSingleChar.do'/>";
		$.post(url, {shiftGroupTxt:shiftGroup,queryMonth:queryMonth,region:region,factory:factory,area:area }, function(data) {

			if (data.result == 0) {
				groupShowChart("performanceChart", data.chartsInfo,queryMonth);
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
		});
		
	}
	
});

function loadPerformanceChart() {

	var shiftGroup = $("#shiftGroup", navTab.getCurrentPanel()).val();
	var region = $("#region",navTab.getCurrentPanel()).val();
	var factory = $("#baseFactory_${id_end}",navTab.getCurrentPanel()).val();
	var area = $("#baseArea_${id_end}",navTab.getCurrentPanel()).val();
    
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if(queryMonth == ''){
		alert('请选择月份');
		return false;
	}
	if(factory!="" || area!=""){
		if(shiftGroup !=""){
			factory ="";
			area ="";
		}
	}

	var url = "<c:url value='groupPerformanceChart/getPerformanceSingleChar.do'/>";
	$.post(url, {shiftGroupTxt:shiftGroup,queryMonth:queryMonth,region:region,factory:factory,area:area }, function(data) {

		if (data.result == 0) {
			groupShowChart("performanceChart", data.chartsInfo,queryMonth);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}


function loadShiftGroup(){
	var factory = $("#baseFactory_${id_end}",navTab.getCurrentPanel()).val();
	var area = $("#baseArea_${id_end}",navTab.getCurrentPanel()).val();
	var url = "<c:url value='groupPerformanceChart/loadShiftGroup.do'/>";
	$("#shiftGroupLoad", navTab.getCurrentPanel()).load(url,{factory:factory,area:area});
}

</script>

	<form  onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
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
    					    			
				<th>月份：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
					<input type="hidden" id="region" value="（单月）">
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" id="sigbutton" onclick="loadPerformanceChart();">查找</button></div></div>
				</td>				
			</tr>

		</table>
	</div>
	</form>
<div id="shiftGroupLoad">
<script type="text/javascript">
$(function(){	
	$('#shiftGroupList', navTab.getCurrentPanel()).dropdownlist({
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
		<div id="performanceChart" class="singleChartDiv"></div>
</div>