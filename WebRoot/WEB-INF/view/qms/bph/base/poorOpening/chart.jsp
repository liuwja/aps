<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	jQuery(document).ready(function(){
		$('#baseFactory_${id_end}').change(function(){
	
			//doOnlyLineSelectBase('${id_end}');
			doshiftGroupSelect('${id_end}');
			
		});
		loadShiftGroupMap('${id_end}');
	});
	
	function mkrcheckTime(){
		var starTime = $('#startTime',navTab.getCurrentPanel()).val();
		var endTime = $('#endTime',navTab.getCurrentPanel()).val();
		if(starTime>endTime){
			alert("开始时间不能大于结束时间");
			return false;
		}
		$('#market').submit();
	}
	
	
	function loadShiftGroupMap(idEnd){
		var txt = "";
		<c:forEach items="${shiftGroup_mapLines}" var="ao">
			var akey = '${ao.key}';
			txt = txt+akey+"@$";
			<c:forEach items="${ao.value}" var="shiftGroup">
				txt = txt+"<option value='${shiftGroup[1]}'>${shiftGroup[1]}</option>";
			</c:forEach>
			txt = txt+"#&";
		</c:forEach>
		$("#shiftGroupMap_"+idEnd).val(txt);
	}
	
	function doshiftGroupSelect(idEnd){
		//var idEnd = '${id_end}';
		var favalue = $("#baseFactory_"+idEnd).find("option:selected").text();
		var select = $("#shiftGroup_"+idEnd);
		select.empty();
		select.append("<option value=''>请选择</option>"); 
		if(favalue.indexOf("请选择")>-1){
	   		return ;
		}
		var map = $("#shiftGroupMap_"+idEnd).val();
	
		var arr1 = map.split("#&");
		for(i=0; i<arr1.length; i++){
			var arr2 = arr1[i].split("@$");
	
			if(arr2[0] == favalue){
				select.append(arr2[1]);
			}
		}
	}
</script>
<div class="pageHeader">
	<form id="poorOpen">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<jsp:include page="../../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="fgroup"/>
					<jsp:param value="0" name="area"/>
					<jsp:param value="0" name="pline"/>
					<jsp:param value="0" name="shift"/>
					<jsp:param value="0" name="factory_iskey"/>
					<jsp:param value="0" name="area_iskey"/>
					<jsp:param value="1" name="pline_iskey"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
				</jsp:include>
				
				<td>
				   不良来源
				</td>
				<td>
				   <select name="defectSourceS" id="mrdefectSource">
				      <option value="">所有</option>
				      <option value="开箱不良">开箱不良</option>
				      <option value="流行性不良">流行性不良</option>
				   </select>
				   <script type="text/javascript">
						$("#mrdefectSource").val("${vo.defectSourceS}");
					</script>
				</td>
				<td>
				   故障机型
				</td>
				<td>
				   <input name="defectTypeS" type="text"  value="${vo.defectTypeS}" size="10">
				</td>
				</tr>
				<tr>
				<td>
				   产品编号或范围
				</td>
				<td>
				   <input name="productNumberS" type="text"  value="${vo.productNumberS }" size="10">
				</td>
				<td>发生日期：</td>	
				<td colspan="3">
					<input name="startTime" id="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" type="text" size="10" readonly="true"  value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>" />&nbsp;至&nbsp;
					<input name="endTime" id="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" type="text" size="10" readonly="true"  value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>" />
				</td>	
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" id="mkrbtn1" onclick="showPoorChart()">查找</button></div></div>
				</td>
						
			</tr>
		</table>
	</div>
	</form>
</div>

<div style="height:100%;margin: 0">
	<div id="poorChart" style="height: 400px"></div>
</div>
<script type="text/javascript" src="resources/echart/echarts.js"></script>
<script type="text/javascript" src="resources/echart/china.js"></script>
<script type="text/javascript">
function showPoorChart(){
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
	var baseFactory = $("select[name=baseFactory]", navTab.getCurrentPanel()).val();
	
	if(startTime == null||startTime == ''){
		alertMsg.info("请输入开始时间");
		return false;
	}
	if(endTime == null||endTime == ''){
		alertMsg.info("请输入结束时间");
		return false;
	}
	
	var data = {startTimeS:startTime,endTimeS:endTime,factoryS:baseFactory};
	var url = "${pageContext.request.contextPath}/poorOpen/showAll.do";
	var callback = function(data){
		if(data.result == 0 ){
			myCharts("poorChart",data.chartsInfo);	
		}else{
			alert("程序出错"+data.msg)
		}
	}
	$.post(url,data,callback,"json");
	
}
function myCharts(id,data){
var myChart = echarts.init(document.getElementById(id));
	var option ={
		title:{
			text:'开箱不良趋势图',
			x: 'center'
		},
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '13%',
	        right: '14%',
	        bottom: '23%',
	        containLabel: true
	    },
	    toolbox: {
			　　show: true,
				right:'20%',
			　　feature: {
			　　　　saveAsImage: {
			　　　　show:true,
			　　　　excludeComponents :['toolbox'],
			　　　　pixelRatio: 2
			　　　　}
			　　}
			},
	    xAxis : [
	        {
	            type : 'category',
	            name:'月份',
	            data : data.xValue,
	            axisTick: {
	                alignWithLabel: true
	            },

          
          axisLabel: {  
			   interval:0,  
			   rotate:-45  
			}  
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	        }
	    ],
	    series : [
	        {
	            name:'数量',
	            type:'line',
	            barWidth: '40%',
	            data:data.yValue,
	            label:{
					normal:{
						show:true,            //显示数字
						position: 'top'        //这里可以自己选择位置
					}

				}
	        }
	    ]
	};
	myChart.setOption(option);
}


</script>
