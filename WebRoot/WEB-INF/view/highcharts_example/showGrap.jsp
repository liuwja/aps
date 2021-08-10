<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
</style>
<script type="text/javascript">
$(function () {
    $("#highcharts").highcharts({
        /*chart: {
            zoomType: 'xy'
        },*/
        title: {
            text: 'Average Monthly Temperature and Rainfall in Tokyo'
        },
        subtitle: {
            text: 'Source: WorldClimate.com'
        },
        xAxis: [{
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        }],
        yAxis: [{ // Primary yAxis
            labels: {
                format: '{value}°C',
                style: {
                    color: '#89A54E'
                }
            },
            title: {
                text: 'Temperature',
                style: {
                    color: '#89A54E'
                }
            }
        }, { // Secondary yAxis
            title: {
                text: 'Rainfall',
                style: {
                    color: '#4572A7'
                }
            },
            labels: {
                format: '{value} mm',
                style: {
                    color: '#4572A7'
                }
            },
            opposite: true
        }],
        tooltip: {
            shared: true
        },
        
        legend: {
            layout: 'vertical',
            align: 'left',
            x: 120,
            verticalAlign: 'top',
            y: 100,
            floating: true,
            backgroundColor: '#FFFFFF'
        },
        series: [{
            name: 'Rainfall',
            color: '#4572A7',
            type: 'column',
            yAxis: 1,
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4],
            tooltip: {
                valueSuffix: ' mm'
            },
            dataLabels: {
                enabled: true,
                rotation: 0,
                color: '#4572A7',
                align: 'right',
                x: 3,
                y: 4,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                }
            },
            enableMouseTracking: false

        }, {
            name: 'Temperature',
            color: '#89A54E',
            type: 'spline',
            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6],
            tooltip: {
                valueSuffix: '°C'
            },
            dataLabels: {
                enabled: true,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                }
            },
            enableMouseTracking: false
            
        }]
    });
});
				                                            				
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="care/outlay/showGrap.do" method="post">
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
		<div id="highcharts" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</div>