<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
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
<form  onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar dropdownSearchBar">
		<table class="searchContent">
			<tr>
				<td>年份：</td>
				<td>
					<input type="text" id="year"  name="yearEquaTo" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})"" readonly="true" style="width: 100px"/>
				</td>					
				<td style="text-align: right;">部门：</td>
                <td>
                	<select id="dape" name="dape" onchange="getBigClassByAnYear()">
                		<option value="">--请选择--</option>
                    	<c:forEach items="${deparmentList}" var="o" varStatus="s">
                    	<%-- <option value="${o.departmentNumber}">${o.departmentName}</option> --%>
                    	<option value="${o.departmentName}" <c:if test="${vo.dape eq o.departmentName }">selected="selected"</c:if>>${o.departmentName}</option>
                    	</c:forEach>
                    </select>
                </td>  
                <td style="text-align: right;">绩效目标大类：</td>
                <td>
                	<select id="bigClass" name="bigClass" onchange="getIndexContentByAnYear()">
                		<option value="">--请选择--</option>
                    </select>
                </td> 
                <td style="text-align: right;">绩效目标衡量指标内容：</td>
                <td>
                    <select id="indexContent" name="indexContent">
                		<option value="">--请选择--</option>
                    </select>
                </td> 
                <td style="text-align: right;">统计类型：</td>
                <td>
                    <select id="type" name="type">
                		<option value="">所有</option>
                		<option value="月度实际绩效值">月度实际绩效值</option>
                		<option value="月度累计实际绩效值">月度累计实际绩效值</option>
                    </select>
                </td> 
                <td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="getChartsDataByAnYear()">查找</button></div></div>
				</td>					
			 </tr>	
		</table>
	</div>
</form>
<div class="pageContent"   style="height: 600px;overflow: auto; ">
	<div class="pageContent"   style="height: 550px;overflow: auto; ">
		<div id="chartByYear" style="width: 95%;height: 500px;"></div>
	</div>
</div>
 <script type="text/javascript">
 $(function(){
	 getBigClassByAnYear()
 })
 
 function getBigClassByAnYear(bigClass){
	 var dape=$("#dape",navTab.getCurrentPanel()).find("option:selected").val(); 
	 $.ajax({
     	type: "POST",
     	url: "<c:url value='/per/chartsDate/getBigClass.do'/>",   
     	data: {
     		"dape":dape
     	},
     	success: function(data){
     		$("#bigClass",navTab.getCurrentPanel()).children("option").remove(); 
     		var json=data.rows;
     		$("#bigClass",navTab.getCurrentPanel()).append("<option value=''>--请选择--</option>");
     		for (var i = 0; i < json.length; i++) {
     			$("#bigClass",navTab.getCurrentPanel()).append("<option value='"+json[i]+"'>"+json[i]+"</option>");
			}
     	}
     });
	 getIndexContentByAnYear();
 }
 function getIndexContentByAnYear(){
	 var dape=$("#dape",navTab.getCurrentPanel()).find("option:selected").val();  
	 var bigClass=$("#bigClass").find("option:selected").val();  
	 $.ajax({
     	type: "POST",
     	url: "<c:url value='/per/chartsDate/getIndexContent.do'/>",   
     	data: {
     		"dape":dape,
     		"bigClass":bigClass
     	},
     	success: function(data){
     		$("#indexContent",navTab.getCurrentPanel()).children("option").remove(); 
     		var json=data.rows;
     		$("#indexContent",navTab.getCurrentPanel()).append("<option value=''>--请选择--</option>");
     		for (var i = 0; i < json.length; i++) {
     			$("#indexContent",navTab.getCurrentPanel()).append("<option value='"+json[i]+"'>"+json[i]+"</option>");
			} 
     	}
     });
 }
 
 function getChartsDataByAnYear(){
	 var year=$("#year",navTab.getCurrentPanel()).val();
	 var dape=$("#dape",navTab.getCurrentPanel()).find("option:selected").val();  
	 var bigClass=$("#bigClass",navTab.getCurrentPanel()).find("option:selected").val(); 
	 var indexContent=$("#indexContent",navTab.getCurrentPanel()).find("option:selected").val(); 
	 var type=$("#type",navTab.getCurrentPanel()).find("option:selected").val(); 
	 if(year==""){
		 alert("请输入年份！")
		 return;
	 }
/* 	 if(dape==""){
		 alert("请选择部门！")
		 return;
	 }
	 if(bigClass==""){
		 alert("请选择绩效目标大类！")
		 return;
	 }
	 if(indexContent==""){
		 alert("请选择绩效目标衡量指标内容！")
		 return;
	 } */
	 $.ajax({
     	type: "POST",
     	url: "<c:url value='/per/chartsDate/loadChartsByAnYear.do'/>",   
     	data: {
     		"yearEquaTo":year,
     		"dape":dape,
     		"bigClass":bigClass,
     		"indexContent":indexContent,
     		"type":type
     	},
     	success: function(data){
     		loadChartByYear(data.vo);
     	},
     	error: function(XMLHttpRequest,textStatus,errorThrown) {
            alert("查询出错！");
        },
     });
 }
 function loadChartByYear(vo){
	 	var title=vo.title;
	 	var legendData=vo.legendData;
	 	var xAxisData=vo.xAxisData;
	 	var seriesData=vo.list[0];
	 	var seriesData2=vo.list[1];
	 	var seriesData3=vo.list[2];
		var echart;
		var option;
		option = {
			title: {
				text: title,
			    //subtext: '纯属虚构'
				x:'center',
			},
			tooltip: {
			    trigger: 'axis'
			},
			legend: {
				bottom : 'bottom',
			    data:legendData,
			},
			toolbox: {
			    show: true,
			    feature: {
				    dataZoom: {
				    	yAxisIndex: 'none'
				    },
		            dataView: {readOnly: false},
		            magicType: {type: ['line', 'line','line']},
		            restore: {},
		            saveAsImage: {}
		        }
			},
		    xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data: xAxisData
		    },
		    yAxis: {
		        type: 'value',
		        axisLabel: {
		            formatter: '{value}'
		        }
		    },
		    series: [
		        {
		            name:legendData[0],
		            type:'line',
		            data:seriesData,
		            itemStyle : { normal: {label : {show: true}}}
		        },
		        {
		            name:legendData[1],
		            type:'line',
		            data:seriesData2,
		            itemStyle : { normal: {label : {show: true}}}
		        },
		        {
		            name:legendData[2],
		            type:'line',
		            data:seriesData3,
		            itemStyle : { normal: {label : {show: true}}}
		        }
		    ]
		}; 
		echart = echarts.init(document.getElementById("chartByYear",navTab.getCurrentPanel()));
		echart.setOption(option);	
 }		
 </script>
