var resultData = []; //结果集
var averageData = []; //平均值
var maxReapir = 0; //地图最大维修数
var minRepair = 0; //地图最小维修数
var statisType; //排序方式
var xTitle;
var yTitle;

function setScatterData(data, chartType) { //设置散点图数据
	if (chartType == "0") {
		xTitle = "发货数";
	} else if (chartType == "repairCount") {
		xTitle = "安装数";
		yTitle = "维修数";
	} else if (chartType == "repairRate") {
		xTitle = "安装数";
		yTitle = "维修率";
		var recPercentlist = data.yValues[2];
	}
	var queryTypeList = data.xValue; //查询条件
	var repairlist = data.yValues[0]; //维修数
	var repairArr = data.yValues[1]; //维修率
	var shipCountList = data.tipValues;
	var maxShipCount = 0; //最大发货数
	var minShipCount = 0; //最小发货数
	var maxRepairCount = 0; //最大维修数
	var minRepairCount = 0; //最小维修数
	resultData = [];
	for (var i = 0; i < queryTypeList.length; i++) {
		var shipCount = Math.round(shipCountList[i]);
		var repairCount = Math.round(repairlist[i]);
		var repair = repairArr[i];
		var queryType = queryTypeList[i];
		if (chartType == "repairRate") {
			var recPercent = recPercentlist[i];
//			var list = [shipCount, repairCount, repair ,queryType, recPercent];
			var list = [shipCount, recPercent, repair ,queryType, repairCount];
			if (recPercent > maxRepairCount) {
				maxRepairCount = recPercent;
			}
			if (minRepairCount == 0 || recPercent < minRepairCount) {
				minRepairCount = recPercent;
			}
		} else {
			var list = [shipCount, repairCount, repair ,queryType];
			if (repairCount > maxRepairCount) {
				maxRepairCount = repairCount;
			}
			if (minRepairCount == 0 || repairCount < minRepairCount) {
				minRepairCount = repairCount;
			}
		}
		if (shipCount > maxShipCount) {
			maxShipCount = shipCount;
		}
		if (minShipCount == 0 || shipCount < minShipCount) {
			minShipCount = shipCount;
		}
		resultData.push(list);
	}
	getAverageLine(maxShipCount, minShipCount, maxRepairCount, minRepairCount);
}

function getAverageLine(maxShipCount, minShipCount, maxRepairCount, minRepairCount) { //计算散点图中间线坐标
	var shipCountAverage = Math.round((maxShipCount + minShipCount)/2); //发货数平均值
	var repairCountAverage = Math.round((maxRepairCount + minRepairCount)/2); //维修数平均值
	var coord1 = [shipCountAverage, 0]; //发货数中间线坐标
	var coord2 = [shipCountAverage, maxRepairCount]; //发货数中间线坐标
	var coord3 = [0, repairCountAverage]; //维修数中间线坐标
	var coord4 = [maxShipCount, repairCountAverage]; //维修数中间线坐标
	
	var shipCountLine = [];
	var repairCountLine = [];
	var coordMap1 = {};
	var coordMap2 = {};
	var coordMap3 = {};
	var coordMap4 = {};
	coordMap1['name'] = '发货数中间线';
	coordMap1['value'] = shipCountAverage;
	coordMap1['coord'] = coord1;
	coordMap2['coord'] = coord2;
	shipCountLine.push(coordMap1);
	shipCountLine.push(coordMap2);
	
	coordMap3['name'] = '维修数中间线';
	coordMap3['value'] = repairCountAverage;
	coordMap3['coord'] = coord3;
	coordMap4['coord'] = coord4;
	repairCountLine.push(coordMap3);
	repairCountLine.push(coordMap4);
	
	averageData = [];
	averageData.push(shipCountLine);
	averageData.push(repairCountLine);
}

function showScatterChart(id, data, chartType) { //显示散点图
	option = {
		title : {
			text : data.title,
			subtext : data.subtitle,
			left : 'center'
		},
		grid : {
			left: '3%',
	        right: '7%',
	        bottom: '3%',
	        containLabel: true
		},
		tooltip : {
			trigger : 'item',
			formatter : function(params) {
				var name = params.name;
				if (name != null && name != '' && name != undefined) {
					var value = params.value;
					resultData = [];
					return name + ' ： ' + value;
				}
				var queryType = params.value[3];
				var shipCount = params.value[0];
				var repairCount = params.value[1];
				var repair = params.value[2];
				
				if (chartType == "repairRate") {
					var queryType = params.value[3];
					var shipCount = params.value[0];
					var recPercent = params.value[1];
					var repairCount = params.value[4];
					var repair = params.value[2];
					return queryType + '<br />' + xTitle + "：" + shipCount + '<br />维修数：' + repairCount + '<br />维修率：' + recPercent + '';
				} else {
					var queryType = params.value[3];
					var shipCount = params.value[0];
					var repairCount = params.value[1];
					var repair = params.value[2];
					var recPercent = params.value[4];
					return queryType + '<br />' + xTitle + "：" + shipCount + '<br />维修数：' + repairCount + '<br />维修率：' + repair + '%';
				}
			}
		},
		toolbox: {
			feature: {
				dataZoom: {}
			}
		},
	    xAxis : [{
	    	type : 'value',
	    	name : xTitle,
	    	scale : true,
	    	axisLabel : {
                formatter: '{value} 个'
            },
            splitLine: {
                show: false
            }
	    }],
	    yAxis : [{
	    	type : 'value',
	    	name : yTitle,
	    	scale : true,
	    	axisLabel : {
                formatter: '{value} 个'
//	    		formatter : function(a, b, c) {
//	    			console.log("a = " + a);
//	    			console.log("b = " + b);
//	    			console.log("c = " + c);
//                	if (yTitle == '维修率') {
//                		return a + "ppm";
//                	} else {
//                		return a + "个";
//                	}
//				}
            },
            splitLine: {
                show: false
            }
	    }],
	    series : [{
	    	name : '维修数',
	    	type : 'scatter',
	    	data : resultData,
	    	itemStyle: {
	            normal: {
	                shadowBlur: 10,
	                shadowColor: 'rgba(120, 36, 50, 0.5)',
	                shadowOffsetY: 5,
	                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
	                    offset: 0,
	                    color: 'rgb(251, 118, 123)'
	                }, {
	                    offset: 1,
	                    color: 'rgb(204, 46, 72)'
	                }])
	            }
	        },
	        markArea: {
                silent: true,
                itemStyle: {
                    normal: {
                    	borderColor : '#121212',
                        color: 'transparent',
                        borderWidth: 1,
                        borderType: 'dashed'
                    }
                },
                data: [[{
                    name: '分布区间',
                    xAxis: 'min',
                    yAxis: 'min'
                }, {
                    xAxis: 'max',
                    yAxis: 'max'
                }]]
            },
		    markPoint : { //max值
	            data : [
	                {type : 'max', name: '最大维修数'},
	                {type : 'min', name: '最小维修数'}
	            ]
	        },
		    markLine : { //中间线
                lineStyle: {
                    normal: {
                        type: 'solid',
                        color : '#121212'
                    }
                },
                data : averageData
            }
	    }],
	};
	var myChart = echarts.init(document.getElementById(id));
	myChart.clear();
	myChart.setOption(option);
}

function setMapData(data, type) { //设置地图数据
	var provincelist = data.xValue; //省份
	var repairlist = data.yValues[0]; //维修数
	var repairs = data.yValues[1]; //维修率
	maxReapir = 0;
	minRepair = 0;
	resultData = [];
	statisType = type;
	if (type == "维修数") {
		for (var i = 0; i < provincelist.length; i++) {
			var dataMap = {};
			var count = repairs[i] * 0.01; //维修率
			count = repairlist[i] + count; //维修数加维修率，方便传值
			dataMap['name'] = provincelist[i];
			dataMap['value'] = count;
			resultData.push(dataMap);
			if (repairlist[i] > maxReapir) {
				maxReapir = repairlist[i];
			}
			if (minRepair == 0 || repairlist[i] < minRepair) {
				minRepair = repairlist[i];
			}
		}
	} else {
		for(var j = 0; j < provincelist.length; j++) {
			var map = {};
			var repair = repairs[j];
			var repairCount = repairlist[j];
			map['name'] = provincelist[j];
			map['value'] = repair;
			map['repairCount'] = repairCount;
			resultData.push(map);
			if (repair > maxReapir) {
				maxReapir = repair;
			}
		}
	}
}

function showMapCharts(id, data) { //显示地图
	option = {
		backgroundColor: '#404a59',
	 	title: {
	    	text: data.title,
	    	textStyle : {
	        	color : '#FFFFFF'
	        },
	        subtext: data.subtitle,
	        left: 'center'
	    },
	    tooltip: {
	        trigger: 'item',
			formatter: function (params) {
				var provinceName = params.name;
				var repairCount; //维修数
				var repair; //维修率
				var shipCount; //发货数
				
				if (statisType == "维修率") {
					var dataIndex = params.dataIndex;
					repairCount = resultData[dataIndex]['repairCount'];
					repair = params.value;
					if (repair > 0 && repairCount > 0) {
						shipCount = Math.round(repairCount / (repair * 0.01));
					} else {
						shipCount = 0;
					}
				} else {
					var count = params.value.toString().split(".");
					repairCount = count[0];
					repair = "0." + parseInt(count[1]); 
					repair = (parseFloat(repair) * 10).toFixed(repair);
					shipCount = 0; 
					if (repair > 0) {
						shipCount = Math.round(repairCount / (repair * 0.01));
					} 
				}
				return provinceName + '<br />维修数：' + repairCount + '个<br />维修率：' + repair + '%<br />发货数：' + shipCount + '个';
	        }
	    },
	    visualMap: {
	        min: minRepair,
	        max: maxReapir,
	        left: 'left',
	        top: 'bottom',
	        text: ['高','低'],
	        textStyle : {
	        	color : '#FFFFFF'
	        },
	        calculable: true
	    },
	    toolbox: {
	        show: true,
	        orient: 'vertical',
	        left: 'right',
	        top: 'center',
	        feature: {
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    series : [
	    	{
		    	name: '维修量',
	            type: 'map',
	            mapType: 'china',
	            roam: false,
	            label: {
	                normal: {
	                    show: true
	                },
	                emphasis: {
	                    show: true
	                }
	            },
	            data : resultData
	    	}
	    ]
	};
	var myChart = echarts.init(document.getElementById(id));
	myChart.clear();
	myChart.setOption(option);
}

var xData = []; //x轴标题

var yData1 = [];
var yData2 = [];
var shipCountArr = []; //发货数
var yTitle1 = "";
var yTitle2 = "";
var topCtrLineTitle = "上控制线";
var lowCtrLineTitle = "下控制线";
var maxY1 = 0;
var maxY2 = 0;
var splitData1 = 0;
var splitData2 = 0;
var splitData3 = 0;
var topLineList = [];
var lowLineList = [];

function setMarketDefectData(index,isConsumed,selectDate,data) {
	xData = [];
	yData1 = [];
	yData2 = [];
	shipCountArr = [];
	maxY1 = 0;
	maxY2 = 0;
	splitData1 = 0;
	splitData2 = 0;
	topLineList = [];
	lowLineList = [];
	if (isConsumed != null && isConsumed != '' && isConsumed != undefined) {
		if (isConsumed == "2" && (index == 1 || index == 3)) {
			xData = data.xValue;
			if (index == 1) {
				yData1 = data.yValues[2]; //入库数
				yTitle1 = "入库数";
			} else if (index == 3) {
				yData1 = data.yValues[0]; //不良数
				yTitle1 = "不良数";
			}
			for (var i = 0; i < xData.length; i++) {
				var x = xData[i];
				var y1 = yData1[i];
				if(i < 1 || y1 > maxY1) {
					maxY1 = y1;
				}
				if (i < 1 || y2 > maxY2) {
					maxY2 = y2
				}
			}
		} else if (isConsumed == "1" && index == 6) {
			xData = data.xValue;
			yData1 = data.yValues[0]; //不良数
			shipCountArr = data.yValues[2]; //发货数或入库数
			for (var i = 0; i < xData.length; i++) {
				var x = xData[i];
				var y1 = yData1[i];
				if(i < 1 || y1 > maxY1) {
					maxY1 = y1;
				}
			}
			yTitle1 = "不良数";
		} else {
			xData = data.xValue;
			yData1 = data.yValues[0]; //不良数
			yData2 = data.yValues[1]; //不良率
			shipCountArr = data.yValues[2]; //发货数或入库数
			for (var i = 0; i < xData.length; i++) {
				var x = xData[i];
				var y1 = yData1[i];
				var y2 = yData2[i];
				if(i < 1 || y1 > maxY1) {
					maxY1 = y1;
				}
				if (i < 1 || y2 > maxY2) {
					maxY2 = y2
				}
			}
			yTitle1 = "不良数";
			yTitle2 = "不良率";
		}
	} else {
		xData = data.xValue;
		yData1 = data.yValues[0]; //不良数
		for (var i = 0; i < xData.length; i++) {
			var x = xData[i];
			var y1 = yData1[i];
			if(i < 1 || y1 > maxY1) {
				maxY1 = y1;
			}
		}
		yTitle1 = "不良数";
	}
	splitData1 = Math.round(maxY1/10);
	splitData2 = Math.round(maxY2/10);
}

function showMarketDefectCharts(id, data, index, isConsumed) { //物料市场不良排列图显示
	option = {
		title: {
			text : data.title,
			x:'center'
		},
		grid: {
	        left: '8%',
	        right: '4%',
	        bottom: '20%',
	        top : '25%',
	        containLabel: true
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            saveAsImage : {show: true}
	        }
	    },
		tooltip: {
	        formatter: function(params) {
	        	var dataIndex = params.dataIndex;
	        	var value_1 = xData[dataIndex];
	        	var value_2 = yData1[dataIndex];
	        	var value_3 = yData2[dataIndex];
	        	var value_4 = shipCountArr[dataIndex];
	        	if (value_2 == null || value_2 == "" || value_2 == undefined) {
	        		value_2 = 0;
	        	}
	        	if (value_3 == null || value_3 == "" || value_3 == undefined) {
	        		value_3 = 0;
	        	}
	        	if (value_4 == null || value_4 == "" || value_4 == undefined) {
	        		value_4 = 0;
	        	}
	        	if (isConsumed != null && isConsumed != '' && isConsumed != undefined) {
	        		if (isConsumed == 1 && index == 6) {
	        			return value_1 + '<br />维修数：' + value_2 + '个<br />发货数：' + value_4 + '个';
	        		} else if (isConsumed == 1) {
	        			return value_1 + '<br />维修数：' + value_2 + '个<br />维修率：' + value_3 + '%<br />发货数：' + value_4 + '个';
	        		} else if (isConsumed == 2 && index == 1) {
	        			return value_1 + '<br />入库数：' + value_2 + '个';
	        		} else if (isConsumed == 2 && index == 3) {
	        			return value_1 + '<br />维修数：' + value_2 + '个'
	        		} else {
	        			return value_1 + '<br />维修数：' + value_2 + '个<br />维修率：' + value_3 + '%<br />入库数：' + value_4 + '个';
	        		}
	        	} else {
	        		return value_1 + '<br />维修数：' + value_2 + '个';
	        	}
	        }
	    },
	    legend: {
	        data:[yTitle1,yTitle2],
	        align : 'left',
	        left : 20,
	        top : 20
	    },
	    xAxis: [
            {
            	axisLabel: {
            		show : true,
            		interval:0,
            		rotate:25
            	},
                type: 'category',
                data: xData
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: yTitle1,
                min: 0,
                max: maxY1,
                interval: splitData1,
                splitLine: false,
                axisLabel: {
                    formatter: '{value} 个'
                }
            },
            {
                type: 'value',
                name: yTitle2,
                min: 0,
                max: maxY2,
                interval: splitData2,
                splitLine: false,
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        series: [
            {
            	name:yTitle1,
            	type:'bar',
            	data: yData1,
            	label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                itemStyle : {
                	normal : {
                		color : '#3399FF'
                	}
                },
                barWidth : 50
            },
            {
            	name:yTitle2,
            	type:'line',
            	yAxisIndex: 1,
            	data: yData2,
            	markLine : {
            		lineStyle: {
            			normal: {
            				type : 'solid'
            			}
            		}
            	},
            	label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter : function(a, b, c) {
                        	if (yTitle2 == '不良率') {
                        		return a.value + "%";
                        	} else {
                        		return a.value + "个";
                        	}
        				}
                    }
                }
            }
        ]
	}
	var myChart = echarts.init(document.getElementById(id));
	myChart.clear();
	myChart.setOption(option);
	myChart.on('click', function (params) {
	    var i = params.dataIndex;
	    var s = data.tipValues[i];
	    var n = data.xValue[i];
	    var t = data.title;
	    if (isConsumed != null && isConsumed != "" && isConsumed == 2 && index == 1) {
	    	navTab.openTab("incomingPartDataTab", "quality/marketPart/incomingPartData.do", 
			{
				title:"明细-来料入库",fresh:true,
				data:{title : index, commonName : n, commonNumber : s}
			});
	    } else {
	    	navTab.openTab("marketPartDataTab", "quality/marketPart/marketPartData.do", 
			{
				title:"明细-部件不良", fresh:true,
				data:{title: index, commonName : n, commonNumber : s}
			});
	    }
	});
	$("<div style='z-index:900;position: absolute;top:5px;left:85%;'><button type='button' style='background: transparent;color: #A0A0A0;border: 0px;cursor:pointer;' onclick='getDataSourceByMenuName(&quot;物料质量统计分析&quot;, &quot;市场部分&quot;, &quot;市场不良排列图&quot;, &quot;排列图&quot;);'>数据来源</button></div>").appendTo($("#marketDefectTdChart",navTab.getCurrentPanel()));
}

var trend_xData = [];
var trend_yData1 = [];
var trend_yData2 = [];
var trend_shipCountArr = [];
var trend_maxY1 = 0;
var trend_maxY2 = 0;
var trend_splitData1 = 0;
var trend_splitData2 = 0;
var trend_topLineList = 0;
var trend_lowLineList = 0;

function setMarketDefectTrendData(isConsumed, selectDate, data) { //设置不良趋势图数据
	trend_xData = []; //时间
	trend_yData1 = []; //不良数
	trend_yData2 = []; //不良率
	trend_shipCountArr = [];
	trend_maxY1 = 0;
	trend_maxY2 = 0;
	trend_splitData1 = 0;
	trend_splitData2 = 0;
	trend_topLineList = [];
	trend_lowLineList = [];
	if (isConsumed != null && isConsumed != '' && isConsumed != undefined) {
		trend_xData = data.xValue;
		trend_yData1 = data.yValues[0]; //不良数
		trend_yData2 = data.yValues[1]; //不良率
		trend_shipCountArr = data.yValues[2]; //发货数或入库数
		trend_topLineList = data.yValues[3]; //上控制线
		trend_lowLineList = data.yValues[4]; //下控制线
		for (var i = 0; i < trend_xData.length; i++) {
			var x = trend_xData[i];
			var y1 = trend_yData1[i];
			var y2 = trend_yData2[i];
			if(i < 1 || y1 > trend_maxY1) {
				trend_maxY1 = y1;
			}
			if (i < 1 || y2 > trend_maxY2) {
				trend_maxY2 = y2
			}
		}
		yTitle1 = "不良数";
		yTitle2 = "不良率";
	} else {
		trend_xData = data.xValue;
		trend_yData1 = data.yValues[0]; //不良数
		for (var i = 0; i < trend_xData.length; i++) {
			var x = trend_xData[i];
			var y1 = trend_yData1[i];
			if(i < 1 || y1 > trend_maxY1) {
				trend_maxY1 = y1;
			}
		}
		yTitle1 = "不良数";
	}
	trend_splitData1 = Math.round(trend_maxY1/10);
	trend_splitData2 = Math.round(trend_maxY2/10);
}

function showMarketDefectTrendCharts(isConsumed, selectDate, id, data, index) { //物料市场不良趋势图显示
	option = {
		title: {
			text : data.title,
			x:'center'
		},
		grid: {
			left: '8%',
	        right: '4%',
	        bottom: '20%',
	        top : '25%',
	        containLabel: true
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            saveAsImage : {show: true}
	        }
	    },
		tooltip: {
			formatter: function(params) {
	        	var dataIndex = params.dataIndex;
	        	var value_1 = trend_xData[dataIndex];
	        	var value_2 = trend_yData1[dataIndex];
	        	var value_3 = trend_yData2[dataIndex];
	        	var value_4 = trend_shipCountArr[dataIndex];
	        	var value_5 = trend_topLineList[dataIndex];
	        	var value_6 = trend_lowLineList[dataIndex];
	        	if (value_2 == null || value_2 == "" || value_2 == undefined) {
	        		value_2 = 0;
	        	}
	        	if (value_3 == null || value_3 == "" || value_3 == undefined) {
	        		value_3 = 0;
	        	}
	        	if (value_4 == null || value_4 == "" || value_4 == undefined) {
	        		value_4 = 0;
	        	}
	        	if (value_5 == null || value_5 == "" || value_5 == undefined) {
	        		value_5 = 0;
	        	}
	        	if (value_6 == null || value_6 == "" || value_6 == undefined) {
	        		value_6 = 0;
	        	}
	        	if (isConsumed != null && isConsumed != '' && isConsumed != undefined && selectDate != null) {
	        		if (selectDate == "month") {
	        			if (isConsumed == 1) {
	        				return trend_xData[dataIndex] + '<br />维修数：' + value_2 + '个<br />维修率：' + value_3 + '%<br />发货数：' + value_4 + '个'
		        			+ "<br />上控制线：" + value_5 + "<br />下控制线：" + value_6;
	        			} else {
	        				return trend_xData[dataIndex] + '<br />维修数：' + value_2 + '个<br />维修率：' + value_3 + '%<br />入库数：' + value_4 + '个'
		        			+ "<br />上控制线：" + value_5 + "<br />下控制线：" + value_6;
	        			}
	        		} else {
	        			if (selectDate == "month") {
	        				return trend_xData[dataIndex] + '<br />维修数：' + value_2 + '个<br />维修率：' + value_3 + '%<br />发货数：' + value_4 + '个';
	        			} else {
	        				return trend_xData[dataIndex] + '<br />维修数：' + value_2 + '个<br />维修率：' + value_3 + '%<br />入库数：' + value_4 + '个';
	        			}
	        		}
	        	} else {
	        		return trend_xData[dataIndex] + '<br />维修数：' + trend_yData1[dataIndex] + '个';
	        	}
	        }
	    },
	    legend: {
	        data:[yTitle1,yTitle2,topCtrLineTitle,lowCtrLineTitle],
	        align : 'left',
	        left : 20,
	        top : 20
	    },
	    xAxis: [
            {
            	axisLabel: {
            		show : true,
            		interval:0,
            		rotate:25
            	},
                type: 'category',
                data: trend_xData
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: yTitle1,
                min: 0,
                max: trend_maxY1,
                interval: trend_splitData1,
                splitLine: false,
                axisLabel: {
                    formatter: '{value} 个'
                }
            },
            {
                type: 'value',
                name: yTitle2,
                min: 0,
                splitLine: false,
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        series: [
            {
            	name:yTitle1,
            	type:'bar',
            	data: trend_yData1,
            	label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                itemStyle : {
                	normal : {
                		color : '#3399FF'
                	}
                },
                barWidth : 50
            },
            {
            	name:yTitle2,
            	type:'line',
            	yAxisIndex: 1,
            	data: trend_yData2,
            	markLine : {
            		lineStyle: {
            			normal: {
            				type : 'solid'
            			}
            		}
            	},
            	label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter : function(a, b, c) {
        					return a.value + "%";
        				}
                    }
                }
            },
            {
            	name : topCtrLineTitle,
            	type : 'line',
            	step : 'middle',
            	yAxisIndex : 1,
            	data : trend_topLineList,
            	markLine : {
            		ineStyle: {
            			normal: {
            				type: 'solid'
            			}
            		}
            	}
            },
            {
            	name : lowCtrLineTitle,
            	type : 'line',
            	step : 'middle',
            	yAxisIndex : 1,
            	data : trend_lowLineList,
            	markLine : {
            		ineStyle: {
            			normal: {
            				type: 'solid'
            			}
            		}
            	}
            }
        ]
	}
	var myChart = echarts.init(document.getElementById(id));
	myChart.clear();
	myChart.setOption(option);
	myChart.on('click', function (params) {
	    var i = params.dataIndex;
	    var s = data.tipValues[i];
	    var n = data.xValue[i];
	    var t = data.title;
	    navTab.openTab("marketPartDataTab", "quality/marketPart/marketPartData.do", 
			{
				title: "明细-部件不良", fresh:true,
				data:{title: index, commonName : n, commonNumber : s}
			});
	});
	$("<div style='z-index:900;position: absolute;top:5px;left:85%;'><button type='button' style='background: transparent;color: #A0A0A0;border: 0px;cursor:pointer;' onclick='getDataSourceByMenuName(&quot;物料质量统计分析&quot;, &quot;市场部分&quot;, &quot;市场不良趋势图&quot;, &quot;趋势图&quot;);'>数据来源</button></div>").appendTo($("#marketDefectTrendTdChart",navTab.getCurrentPanel()));
}

function showDashboard(id, data) { //显示仪表盘
	var productType = data.productType;
	var hundredRepairRate = data.hundredRepairRate * 100;
	var referenctRepairRate = data.referenctRepairRate * 100;
	var targetRepairRate = data.targetRepairRate * 100;
	
	var maxValue = referenctRepairRate*2;
	var hm = (hundredRepairRate/maxValue).toFixed(2);
	var tm = (targetRepairRate/maxValue).toFixed(2);

//	console.log("productType = " + productType);
//	console.log("hundredRepairRate = " + hundredRepairRate);
//	console.log("referenctRepairRate = " + referenctRepairRate);
//	console.log("targetRepairRate = " + targetRepairRate);
	
	var color = [];
	if (tm >= hm) {
		var tempList1 = [tm, '#00BFFF'];
		var tempList2 = [hm, '#00EE00'];
		var tempList3 = [1, '#00BFFF'];
		color.push(tempList1);
		color.push(tempList2);
		color.push(tempList3);
	} else {
		var tempList1 = [hm, '#00BFFF'];
		var tempList2 = [tm, 'red'];
		var tempList3 = [1, '#00BFFF'];
		color.push(tempList1);
		color.push(tempList2);
		color.push(tempList3);
	}
	
	var option = {
		title: {text: productType, x: "center", textStyle: {fontSize : 10}},
		tooltip: {
	    	formatter: function(params) {
	    		return "基准：" + referenctRepairRate.toFixed(1) + "%<br />目标：" + targetRepairRate.toFixed(1) + "%<br />现状：" + hundredRepairRate.toFixed(1) + "%";
	    	}
		},
		series: [{
			type: "gauge", min: 0, max: referenctRepairRate * 2, splitNumber: 12, startAngle: -90, endAngle: 269.99999,
			radius: '70px', //仪表盘半径
			axisLine: { //仪表盘轴线样式 
				lineStyle: {
					width: 5,
//					color: [
//					    [second, '#00BFFF'],
//					    [first, 'red'],
//					    [1, '#00BFFF']
//			        ], 
					color: color
		        }
	        },
	        splitLine: { //分割线样式 
	            length: 10,
	            lineStyle: {width: 2}
	        },
	        axisTick: { //仪表盘刻度样式
	            show: true,
	            splitNumber: 5, //分隔线之间分割的刻度数
	            length: 5, //刻度线长
	            lineStyle: {color: ['#ffffff']}
	        },
	        pointer: {
	            width: "5%", //指针大小
	        },
	        itemStyle: {
	            normal: {
	                color: 'red',
	                shadowColor: 'rgba(0, 0, 0, 0.5)',
	                shadowBlur: 10,
	                shadowOffsetX: 2,
	                shadowOffsetY: 2
	            }
	        },
        	axisLabel: { //刻度标签
        		show: 1,
	            distance: 0, //标签与刻度线的距离
	            textStyle: {
//	                fontWeight: 'bold',
	                fontSize: 5,
	            },
	            formatter: function(t) {
	                if (t === 0) {
	                    return '';
	                }
	                if (t === referenctRepairRate * 2) {
	                	return 0;
	                }
//	               return t.toFixed(1) + '%';
	                return t.toFixed(1);
	            }
	        },
	        detail: {
//        		formatter: '{value}%',
	        	formatter : function(a) {
					return a.toFixed(1) + "%";
				},
        		textStyle : {
        			fontSize : 10,
        			fontWeight: 'bold',
        			color : 'black'
        		}
        	},
	        data: [{
	            value: hundredRepairRate,
	        }, {
	        	value: referenctRepairRate,
	        }, {
	        	value: targetRepairRate,
	        }]
	    }]
	};
	var myChart = echarts.init(document.getElementById(id));
	myChart.clear();
	myChart.setOption(option);
}

function showDashboardTest(id, data) { //显示仪表盘
	var productType = data.productType;
	var hundredRepairRate = data.hundredRepairRate * 100;
	var referenctRepairRate = data.referenctRepairRate * 100;
	var targetRepairRate = data.targetRepairRate * 100;
	
	var maxValue = referenctRepairRate*2;
	var hm = (hundredRepairRate/maxValue).toFixed(2);
	var tm = (targetRepairRate/maxValue).toFixed(2);
	var color = [];
	if (tm >= hm) {
		var tempList1 = [tm, '#00BFFF'];
		var tempList2 = [hm, '#00EE00'];
		var tempList3 = [1, '#00BFFF'];
		color.push(tempList1);
		color.push(tempList2);
		color.push(tempList3);
	} else {
		var tempList1 = [hm, '#00BFFF'];
		var tempList2 = [tm, 'red'];
		var tempList3 = [1, '#00BFFF'];
		color.push(tempList1);
		color.push(tempList2);
		color.push(tempList3);
	}
	
	var option = {
		title: {text: productType, x: "center", textStyle: {fontSize : 10}},
		tooltip: {
	    	formatter: function(params) {
	    		return productType + "<br />基准：" + referenctRepairRate.toFixed(2) + "%<br />目标：" + targetRepairRate.toFixed(2) + "%<br />现状：" + hundredRepairRate.toFixed(2) + "%";
	    	}
		},
		series: [{
			type: "gauge", min: 0, max: referenctRepairRate * 2, splitNumber: 12, startAngle: -90, endAngle: 269.99999,
			radius: '70px', //仪表盘半径
			axisLine: { //仪表盘轴线样式 
				lineStyle: {
					width: 2,
					color: color
		        }
	        },
	        splitLine: { //分割线样式 
	            length: 10,
	            lineStyle: {width: 2}
	        },
	        axisTick: { //仪表盘刻度样式
	            show: true,
	            splitNumber: 5, //分隔线之间分割的刻度数
	            length: 5, //刻度线长
	            lineStyle: {color: ['#ffffff']}
	        },
	        pointer: {
	            width: "5%", //指针大小
	        },
	        itemStyle: {
	            normal: {
	                color: 'black',
	                shadowColor: 'rgba(0, 0, 0, 0.5)',
	                shadowBlur: 10,
	                shadowOffsetX: 2,
	                shadowOffsetY: 2
	            }
	        },
        	axisLabel: { //刻度标签
        		show: 1,
	            distance: 0, //标签与刻度线的距离
	            textStyle: {
	                fontSize: 5,
	            },
	            formatter: function(t) {
	                if (t === 0) {
	                    return '';
	                }
	                if (t === referenctRepairRate * 2) {
	                	return 0;
	                }
	                return t.toFixed(2);
	            }
	        },
	        detail: {
	        	formatter : function(a) {
					return a.toFixed(2) + "%";
				},
        		textStyle : {
        			fontSize : 10,
        			fontWeight: 'bold',
        			color : 'black'
        		}
        	},
	        data: [{
	            value: hundredRepairRate,
	        }]
	    },{
	    	type: "gauge", min: 0, max: referenctRepairRate * 2, splitNumber: 12, startAngle: -90, endAngle: 269.99999,
			radius: '70px', //仪表盘半径
			axisLine: { //仪表盘轴线样式 
				lineStyle: {
					width: 2,
					color: color
		        }
	        },
	        splitLine: { //分割线样式 
	            length: 10,
	            lineStyle: {width: 2}
	        },
	        axisTick: { //仪表盘刻度样式
	            show: true,
	            splitNumber: 5, //分隔线之间分割的刻度数
	            length: 5, //刻度线长
	            lineStyle: {color: ['#D15FEE']}
	        },
	        pointer: {
	            width: "5%", //指针大小
	        },
	        itemStyle: {
	            normal: {
	                color: 'red',
	                shadowColor: 'rgba(0, 0, 0, 0.5)',
	                shadowBlur: 10,
	                shadowOffsetX: 2,
	                shadowOffsetY: 2
	            }
	        },
        	axisLabel: { //刻度标签
        		show: 1,
	            distance: 0, //标签与刻度线的距离
	            textStyle: {
	                fontSize: 5,
	            },
	            formatter: function(t) {
	                if (t === 0) {
	                    return '';
	                }
	                if (t === referenctRepairRate * 2) {
	                	return 0;
	                }
	                return t.toFixed(2);
	            }
	        },
	        detail: {
	        	formatter : function(a) {
					return "";
				},
        		textStyle : {
        			fontSize : 10,
        			fontWeight: 'bold',
        			color : 'black'
        		}
        	},
	        data: [{
	            value: referenctRepairRate,
	        }]
	    },{
	    	type: "gauge", min: 0, max: referenctRepairRate * 2, splitNumber: 12, startAngle: -90, endAngle: 269.99999,
			radius: '70px', //仪表盘半径
			axisLine: { //仪表盘轴线样式 
				lineStyle: {
					width: 2,
					color: color
		        }
	        },
	        splitLine: { //分割线样式 
	            length: 10,
	            lineStyle: {width: 2}
	        },
	        axisTick: { //仪表盘刻度样式
	            show: true,
	            splitNumber: 5, //分隔线之间分割的刻度数
	            length: 5, //刻度线长
	            lineStyle: {color: ['#AEEEEE']}
	        },
	        pointer: {
	            width: "5%", //指针大小
	        },
	        itemStyle: {
	            normal: {
	                color: '#7CFC00',
	                shadowColor: 'rgba(0, 0, 0, 0.5)',
	                shadowBlur: 10,
	                shadowOffsetX: 2,
	                shadowOffsetY: 2
	            }
	        },
        	axisLabel: { //刻度标签
        		show: 1,
	            distance: 0, //标签与刻度线的距离
	            textStyle: {
	                fontSize: 5,
	            },
	            formatter: function(t) {
	                if (t === 0) {
	                    return '';
	                }
	                if (t === referenctRepairRate * 2) {
	                	return 0;
	                }
	                return t.toFixed(2);
	            }
	        },
	        detail: {
	        	formatter : function(a) {
					return "";
				},
        		textStyle : {
        			fontSize : 10,
        			fontWeight: 'bold',
        			color : 'black'
        		}
        	},
	        data: [{
	            value: targetRepairRate,
	        }]
	    }]
	};
	var myChart = echarts.init(document.getElementById(id));
	myChart.clear();
	myChart.setOption(option);
	myChart.on('click', function (params) {
		var month = $("#month", navTab.getCurrentPanel()).val();
		if (month == null || month == '' || month == undefined) {
			month = getDate();
		}
		loadChar(productType, month);
	})
}
var marketDefectTrendNewx = '';
var marketDefectTrendNewy1 = '';
var marketDefectTrendNewy2 = '';
var marketDefectTrendNewy3 = '';


function exportExcelByMarketNew(){//导出不良趋势图数据 x: 时间  y1: 不良数  y2: 发货数 y3: 不良率
	var myForm = document.createElement("form");
	myForm.action= "quality/marketPart/excelOutput_marketDefectTrendNew.do";
	myForm.method="post"; 
	myForm.target="noexistForm";
	myInputx = document.createElement("input");
	myInputy1 = document.createElement("input");
	myInputy2 = document.createElement("input");
	myInputy3 = document.createElement("input");
	
	myInputx.setAttribute("name","x");
	myInputx.setAttribute("value",marketDefectTrendNewx);
	myForm.appendChild(myInputx);
	
	myInputy1.setAttribute("name","y1");
	myInputy1.setAttribute("value",marketDefectTrendNewy1);
	myForm.appendChild(myInputy1);
	
	myInputy2.setAttribute("name","y2");
	myInputy2.setAttribute("value",marketDefectTrendNewy2);
	myForm.appendChild(myInputy2);
	
	myInputy3.setAttribute("name","y3");
	myInputy3.setAttribute("value",marketDefectTrendNewy3);
	myForm.appendChild(myInputy3);
	document.body.appendChild(myForm);
	myForm.submit();
}

function showMarketDefectTrendChartsNew(id, data) { //物料市场不良趋势图显示
	option = {
		title: {
			text : data.title,
			x:'center'
		},
		grid: {
			left: '8%',
	        right: '4%',
	        bottom: '20%',
	        top : '25%',
	        containLabel: true
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            saveAsImage : {show: true},
	            dataView : {
	            	show: true,
	            	title: '数据查看与导出',
	            	readOnly:true,
	            	optionToContent: function(opt) {
	            		marketDefectTrendNewx = '';
	            		marketDefectTrendNewy1 = '';
	            		marketDefectTrendNewy2 = '';
	            		marketDefectTrendNewy3 = '';
	            		
	                            var table = '<table class="bordered" ><thead><tr>'
	                                + '<th>月份</th>'
	                                + '<th>不良数</th>'
	                                + '<th>发货数</th>'
	                                + '<th>不良率</th>';	
	                            table += '</tr></thead><tbody>';
	                             for (var i = 0; i < data.xValue.length; i++) {
	                            	 if(i==data.xValue.length-1){
	                            		 marketDefectTrendNewx+= data.xValue[i];
	                            		 marketDefectTrendNewy1+= data.yValues[0][i];
	                            		 marketDefectTrendNewy2+= data.yValues[2][i];
	                            		 marketDefectTrendNewy3+= data.yValues[1][i];
	                            	 }else{
	                            		 marketDefectTrendNewx+= data.xValue[i]+',';
	                            		 marketDefectTrendNewy1+= data.yValues[0][i]+',';
	                            		 marketDefectTrendNewy2+= data.yValues[2][i]+',';
	                            		 marketDefectTrendNewy3+= data.yValues[1][i]+',';
	                            	 }
	                            	 
	                                table += '<tr><td>' + data.xValue[i] + '</td>'
	                                		+'<td>' + data.yValues[0][i] + '</td>'
	                                		+'<td>' + data.yValues[2][i] + '</td>'
	                                		+'<td>' + data.yValues[1][i] + '</td>'
	                                		+'</tr>';
	                            }
	                            table += '</tbody></table>';
	                            table += '<button type="button" onclick="exportExcelByMarketNew()">导出</button>'
	                        return table;
	                    },
	            	contentToOption:{}
	            }
	        }
	        },
		tooltip: {
			formatter: function(params) {
				var dataIndex = params.dataIndex;
				return data.xValue[dataIndex] + '<br />维修数：' + data.yValues[0][dataIndex] + '个<br />维修率：' + data.yValues[1][dataIndex] + '<br />发货数：' + data.yValues[2][dataIndex] + '个';
	        }
	    },
	    legend: {
	        data:["不良数", "不良率"],
	        align : 'left',
	        left : 20,
	        top : 20
	    },
	    xAxis: [
            {
            	axisLabel: {
            		show : true,
            		interval:0,
            		rotate:25
            	},
                type: 'category',
                data: data.xValue
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: "不良数",
                min: 0,
                splitLine: false,
                axisLabel: {
                    formatter: '{value} 个'
                }
            },
            {
                type: 'value',
                name: "不良率",
                min: 0,
                splitLine: false,
                axisLabel: {
                    formatter: '{value} '
                }
            }
        ],
        series: [
            {
            	name:"不良数",
            	type:'bar',
            	data: data.yValues[0],
            	label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                itemStyle : {
                	normal : {
                		color : '#3399FF'
                	}
                },
                barWidth : 50
            },
            {
            	name:"不良率",
            	type:'line',
            	yAxisIndex: 1,
            	data: data.yValues[1],
            	markLine : {
            		lineStyle: {
            			normal: {
            				type : 'solid'
            			}
            		}
            	},
            	label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter : function(a, b, c) {
        					return a.value ;
        				}
                    }
                }
            }
        ]
	}
	var myChart = echarts.init(document.getElementById(id));
	myChart.clear();
	myChart.setOption(option);
}