<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>

<script>
	$(function() {
		var url = "<c:url value='highcharts/exemple/getInfo_singleList.do'/>";
		$.post(url, {}, function(data) {
	
			if (data.result == 0) {
				setData(data.chartsInfo);
			} else {
	
				alertMsg.error(result.message);
	
			}
		});
	
	});

	function setData(data) {
		var xCategories = new Array();
		myChart("container1", data, function(event) {
	
		});	
		singleList(data);
		/*根据情况调用
		singleList(data);
		doubleList(data);
		*/
	};
	
	//单个list例子
	function singleList(data) {
		var a = new Array();
		var xCategories = new Array();
		myChart("container1", data, function(event) {
	
		});
		if (data.chartDatas != null && data.chartDatas.length > 0) {
	
			for (i = 0; i < data.chartDatas.length; i++) {
				a.push(data.chartDatas[i].y_coordinate);
				xCategories.push(data.chartDatas[i].x_coordinate)
			}
		}
		chart.xAxis[0].setCategories(xCategories);
		chart.series[0].setData(a);
		var list = chart.series
		for ( var y = 0; y < list.length; y++) {
			list[y].update( {
				name : data.seriesName[y]
			});
		}
	
		chart.series[0].name = data.seriesName[0]
	
	}
	//两个list例子
	function dobuleList(data) {
	
		var a = new Array();
		var b = new Array();
		var xCategories = new Array();
		myChart("container1", data, function(event) {
	
		});
	
		if (data.twoGenlist.ob1 != null && data.twoGenlist.ob1.length > 0) {
	
			for ( var i = 0; i < data.twoGenlist.ob1.length; i++) {
	
				a.push(data.twoGenlist.ob1[i].y_coordinate);
				xCategories.push(data.twoGenlist.ob1[i].x_coordinate)
			}
		}
	
		if (data.twoGenlist.ob2 != null && data.twoGenlist.ob2.length > 0) {
	
			for ( var x = 0; x < data.twoGenlist.ob2.length; x++) {
				b.push(data.twoGenlist.ob2[x].yseries);
			}
		}
	
		chart.xAxis[0].setCategories(xCategories);
		chart.series[0].setData(a);
		chart.series[1].setData(b);
		var list = chart.series
		for ( var y = 0; y < list.length; y++) {
			list[y].update( {
	
				name : data.seriesName[y]
	
			});
		}
	
		chart.series[0].name = data.seriesName[0]
		chart.series[1].name = data.seriesName[1]
	}
</script>
<body>

	<div id="container1"
		style="width: 800px; height: 400px; margin: 0 auto"></div>

</body>