<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
</style>
<script type="text/javascript">
﻿$(function () {
    $("#highchartsP3").highcharts({
        chart: {
        },
        xAxis: {
            min: 0,
            max: 5.5
        },
        yAxis: {
            min: 0
        },
        title: {
            text: 'Scatter plot with regression line'
        },
        series: [{
            type: 'scatter',
            name: 'Observations',
            data: [3, 2, 2.8, 3.5, 3.9, 4.2],
            marker: {
                radius: 4
            }
        },{
        	name: '测试1',
        	color:'red', 
            data: [1.1, 1.6, 2.4, 3.2, 1.9,3.9]
            	
        },{
        	name: '测试2',
        	color:'green', 
            data: [2, 3.2, 1.4, 1.2, 2.9,5.9]
        }]
    });
});				                                       				
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="care/outlay/showGrapP3.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
                <th>机型类别：</th>
                <td>
					<input type="radio" id="type" name="type" value="油烟机"/>油烟机
					<input type="radio" id="type" name="type" value="灶具"/>灶具
					<input type="radio" id="type" name="type" value="热水器"/>热水器
					<input type="radio" id="type" name="type" value="消毒柜"/>消毒柜
					<input type="radio" id="type" name="type" value="烤箱"/>烤箱
					<input type="radio" id="type" name="type" value="蒸箱"/>蒸箱
					<input type="radio" id="type" name="type" value="微波炉"/>微波炉
    			</td> 			
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="type" name="type" value=""/>
    			</td> 			
				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>				
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
		<div id="highchartsP3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</div>