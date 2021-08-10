<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}

.content { width:100%;
		   height:100%;
		   border:0px;
}
.div_td { 
			width:49%;
		  height:45%;
		  float:left;
		  color:#FF0000;
		  text-align:center;
		  line-height:250px;
}

</style>
<script type="text/javascript">
$(function () {
    $("#classjx_topl").highcharts({
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
    
    $("#classjx_topr").highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: 'Monthly Average Temperature'
        },
        subtitle: {
            text: 'Source: WorldClimate.com'
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        },
        yAxis: {
            title: {
                text: 'Temperature (°C)'
            },
            plotLines:[{
                color:'red',            //线的颜色，定义为红色
                dashStyle:'LongDash',     //默认是值，这里定义为长虚线
                value:20,              //定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
                width:2,               //标示线的宽度，2px
                label:{
                    text:'',     //标签的内容
                    align:'left',                //标签的水平位置，水平居左,默认是水平居中center
                    x:10,                         //标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
                    style:{
                        fontSize:'14px',
                        fontWeight:'bold'
                    }
                },
                events:{
                    click:function(e){
                        alert("yAxis plotLine Clicked!");
                        //当标示线被单击时，触发的事件
                    },
                    mouseover:function(){
                        console.log("yAxis plotLine Hovered!");
                        //当标示线被鼠标悬停时，触发的事件
                    },
                
                    mouseout:function(){
                        //当标示线被鼠标移出时，触发的事件
                    },
                    
                    mousemove:function(){
                        //当标示线被鼠标移动(时，触发的事件
                    }
                }
            }]
        },
        tooltip: {
            enabled: false,
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: 'Tokyo',
            data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
        }, {
            name: 'London',
            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
        }]
    });
        
    var i = 0;
    $(".add").click(function(){
        i++;
        chart = $('#container').highcharts();
        chart.xAxis[0].addPlotLine({             //在x轴上增加
            value:2*i,                           //在值为2的地方
            width:2,                             //标示线的宽度为2px
            color: '#000',                       //标示线的颜色
            id: 'plot-line-'+i+''                //标示线的id，在删除该标示线的时候需要该id标示
        });
   });
        
   $(".remove").click(function(){
        if(i>0) {
            chart.xAxis[0].removePlotLine("plot-line-"+i+'');
            i--;
        }
    });
   $("#classjx_downl").highcharts({
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
   $("#classjx_downr").highcharts({
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
                <th>班组：</th>
                <td>
					<input type="radio" id="type" name="type" value="班组1"/>班组1
					<input type="radio" id="type" name="type" value="班组2"/>班组2
					<input type="radio" id="type" name="type" value="班组3"/>班组3
					<input type="radio" id="type" name="type" value="班组4"/>班组4

    			</td> 			
				<th>期间：</th>
                <td>
					<input type="text" id="type" name="type" placeholder="请输入日期" onclick="laydate()" value=""/>
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

	<div class="content">
	<div class="div_td" id="classjx_topl" ></div>
	<div class="div_td" id="classjx_topr"></div>
	<div class="div_td" id="classjx_downl" ></div>
	<div class="div_td" id="classjx_downr"></div>            
</div>
</div>