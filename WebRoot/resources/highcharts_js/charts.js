var chart;
function noFuncName()
{
	alert('没有对应的函数');
}
function showChart(id, data,callback){
	var chartIndex = data.index == undefined ? -1 : data.index;
	var detailType = data.type== undefined ? -1 : data.type;
	
	var chartType = data.chartType;//类型
	var chartWidth = data.chartWidth;//宽
	var chartHight = data.chartHight;//高
	var title = data.title;//图标名称
	var subtitle = data.subtitle;//副标题
	var xTitle = data.xTitle;//横坐标名称
	var yLeftTitle = data.yLeftTitle;//纵坐标左标题
	var yRightTitle = data.yRightTitle;//纵坐标右标题
	var tipUnit = data.tipUnit;//提示单位
	var yLeftUnit = data.yLeftUnit;//左单位
	var yRightUnit = data.yRightUnit;//右单位
	var defaultValue = data.defaultValue;
	var xValue = data.xValue;
	var contxtTitle = "打印或导出";
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;
	var creditsEnable = data.index == undefined ? false : true;
	//alert(chartType);
	var containerId = id;
	//alert("type:" + chartType);
	for(var i=0;i<xValue.length;i++){ 
		if(xValue[i] == null || xValue[i] ==""){
			xValue[i] = " ";
		}
	}
	var tipValues = data.tipValues;//获取焦点提示值
	var tipText = data.tipText;//获取焦点提示语
	var tipValues1 = data.tipValues1;//获取焦点提示值
	var tipText1 = data.tipText1;//获取焦点提示语
	var tipValues2 = data.tipValues2;//获取焦点提示值
	var tipText2 = data.tipText2;//获取焦点提示语
	var creditsInfo = {
		enabled:creditsEnable,
		text: '详情',
        href: 'javascript:javascript:'+funcName+'('+chartIndex+','+detailType +')',
		style: {
			cursor: 'pointer',
			color: 'blue',
			fontSize: '16px'
		},
        position: {
            align: 'right',
            verticalAlign:'bottom',
            x: -10,y:-10
        }
    };
	var calculateInfo = {
		enabled:true,
		text: '计算',
        href: 'javascript:javascript:'+funcName+'(this)',
		style: {
			cursor: 'pointer',
			color: 'blue',
			fontSize: '14px'
		},
        position: {
            align: 'right',
            verticalAlign:'top',
            x: -50,y:26
        }
    };
	var creditName = data.creditName;
	var creditObj = creditsInfo;
	if(creditName == "calculate"){
		creditObj = calculateInfo;
	}
	/**
	 * 多折线图
	 */
	if (chartType == "lines") {
		chart = new Highcharts.Chart({
			chart : {
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
				type : 'line',
				borderColor: '#6195CA',
				borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1
			},
			
			lang:{ 
				contextButtonTitle: contxtTitle,
				exportButtonTitle: '导出',
				printChart: '打印',
				downloadJPEG:"下载JPEG 图片",
				downloadPDF: "下载PDF文档",
				downloadPNG: "下载PNG 图片",
				downloadSVG: "下载SVG 矢量图"
			},
			credits: creditObj,
			title : {
				text : title
			},
			subtitle: {
	            text: subtitle
		    },
	        exporting: {
	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,

				labels : {
					align : 'left',
					formatter : function() {
						return this.value;
//						var x = this.value;
//						var y;
//						
//						if(x.length>7){
//							y = x.substring(0,7)+"...";
//						}else{
//							y = this.value;
//						}
//						
//						return y
					},	
					rotation : 20,
					overflow: 'justify',
					staggerLines : 1,
//					style: { 
//                        writingMode:'tb-rl',   //文字竖排样式,
//                    },
//					y:5
				},
				
				//tickInterval : 1,
				title : {
					text : xTitle,
					align: "low",
					x: -16,
					y: -20
					//style: {
					//	fontWeight: 'bold', //刻度字体加粗
					//}
				}

			},
			yAxis : {
				//tickInterval : 3,
				offset: 0,
				title : {
					align: "high",
					rotation: 0,
					offset: 0,
					y: -20,
					text : yLeftTitle,

				},
				min:0,
				labels : {
					formatter : function() {
						return this.value+yLeftUnit;
					},
	                align: "right",
					x: -15,
					y: 0
				},

				/*plotLines:[{
	                color:'red',            //线的颜色，定义为红色
	                dashStyle:'LongDash',     //默认是值，这里定义为长虚线
	                value: defaultValue,              //定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
	                width:2,               //标示线的宽度，2px
	                label:{
	                    text:'',     //标签的内容
	                    align:'left',                //标签的水平位置，水平居左,默认是水平居中center
	                    x:10,                         //标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
	                    style:{
	                        fontSize:'14px',
	                        fontWeight:'bold'
	                    },
						formatter : function() {
							return this.value;
						}
	                }
	            }]*/
			},
			//是否有竖线
			 tooltip: {
				 formatter: function (formatObj) {
		                s = this.x + "<br/>"+this.series.name+":" + +this.y;
		                var len = tipValues.length;
		                for(var i=0;i<len;i++){ 
		            		if(xValue[i] == this.x){
		            			tipValue = tipValues[i];
		            			tipValue1 = tipValues1[i];
		            			tipValue2 = tipValues2[i];
		            			 s += '<br/>'+tipText+': '+tipValue;
		            			 s += '<br/>'+tipText1+': '+tipValue1;
		            			 s += '<br/>'+tipText2+': '+tipValue2;
		            			break;
		            		}
		            	} 
		                return s;
		            },
		            shared: false
			 },
			plotOptions : {
				series : {
					cursor : 'pointer',
					pointWidth : 30,
					lineWidth: 1
				},
				line : {
					// 允许线性图上的数据点进行点击
					allowPointSelect : true,
					// 是否在图注中显示。
					showInLegend : true,
					// 调整图像顺序关系
					zIndex : 3
				}
			},
			series : [ {
	            name: data.seriesNames[0],
	            data: data.yValues[0],
	            dashStyle: 'dash',
	            width: 0.2
	        }, {
	            name: data.seriesNames[1],
	            data: data.yValues[1]
	        }, {
	            name: data.seriesNames[2],
	            data: data.yValues[2]
	        }]
		});
	}
	//纵向柱状图
	else if(chartType == "column") { 
		chart = new Highcharts.Chart( {
			chart : {
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
				type : chartType,
	            borderColor: '#6195CA',
            	borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1
			},
			lang : {
				contextButtonTitle: contxtTitle,
				exportButtonTitle : '导出',
				printChart : '打印',
				downloadJPEG : "下载JPEG 图片",
				downloadPDF : "下载PDF文档",
				downloadPNG : "下载PNG 图片",
				downloadSVG : "下载SVG 矢量图",
				noData: "没有符合条件的数据"
			},
        	credits: creditObj,	
			title : {
				text : title
			},
			subtitle: {
	            text: subtitle
		    },
	        exporting: {
	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,
				labels : {
					align : 'left',
					formatter : function() {
//						var x = this.value;
//						var y;
//						if(x.length>7){
//							y = x.substring(0,7)+"...";
//						}else{
//							y = this.value;
//						}
						return this.value;
					},
	                rotation: 20,
					staggerLines : 1,
					overflow: 'justify',
//					style: { 
//                        writingMode:'tb-rl',   //文字竖排样式,
//                    },
//					y:5

				},
				//tickInterval : 1,
				title : {
					text : xTitle,
					align: "low",
					x: -16,
					y: -30
				}
			},
			yAxis : {
				labels : {

					formatter : function() {
						return this.value+yLeftUnit;
					},
					style: {
	                    color: '#058DC7'
	                },
					rotation: 0,
					offset: 0,
	                align: "right",
					x: -12,
					y: 2
				},
				//tickInterval : 3,
				title : {
					text : yLeftTitle,
					style: {
	                    color: '#058DC7'
	                }
				}
			},
			plotOptions : {
				series : {
					cursor : 'pointer',

					pointWidth : 30
				},
				dataLabels : {
					enabled : true
				},
				column : {
					// 允许线性图上的数据点进行点击
					allowPointSelect : true,
					// 是否在图注中显示。
					showInLegend : true,
					events : {
						click : function (event) {
							var _this = this;
							if(callback)callback(event,_this);
						}
					},
					// 调整图像顺序关系
					zIndex : 3
				}
			},
			series : [ {
				name : data.seriesNames[0],
				data : data.yValues[0],
				color: '#058DC7',
   				dataLabels: {
	                enabled: true,
	                rotation: 0,
	                color: '#058DC7',
	                align: 'left',
	                x: 3,
	                y: 4,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
			} ]
		});
	}
	//柱状折线图、曲线图
	else if (chartType == "column_line" || chartType == "column_spline") {
		var line_type = "line";
		if(chartType == "column_spline"){
			line_type = "spline";
		}
		chart = new Highcharts.Chart( {
			chart : {
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
	            borderColor: '#6195CA',
        		borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1
			},
			lang : {
				contextButtonTitle: contxtTitle,
				exportButtonTitle : '导出',
				printChart : '打印',
				downloadJPEG : "下载JPEG 图片",
				downloadPDF : "下载PDF文档",
				downloadPNG : "下载PNG 图片",
				downloadSVG : "下载SVG 矢量图",
			},
			credits: creditObj,
			title : {
				text : title
			},
			subtitle: {
	            text: subtitle
		    },
	        exporting: {
	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,
				title : {
					text : xTitle,
					align: "low",
					x: -26,
					y: -30
				},
				labels: {
	                overflow: 'justify',
	                rotation: 45
	                
	            }
				
			},
			yAxis: [{ // Primary yAxis
	            title: {
	                text: yLeftTitle,
	                style: {
	                    color: '#058DC7'
	                }
	            },
	            min:0,
	            labels: {
	            	
	                format: '{value}'+yLeftUnit,
	                style: {
	                    color: '#058DC7'
	                },
					rotation: 0,
					offset: 0,
	                align: "right",
					x: -12,
					y: 2
	            }
	        }, { // Secondary yAxis
	            title: {
	                text: yRightTitle,
	                style: {
	                    color: '#ED561B'
	                }
	            },
	            min:0,
	            labels: {
	                format: '{value}'+yRightUnit,
	                style: {
	                    color: '#ED561B'
	                }
	            },
	            opposite: true
	        }],
	        tooltip: {
	        	formatter: function (formatObj) {
	                var s = '<b>' + this.x + '</b>';
					var poUnit = yLeftUnit;
	                $.each(this.points, function (poIndex) {
	                	if(poIndex==0){
	                		poUnit = yLeftUnit;
	                	}else if(poIndex ==1){
	                		poUnit = yRightUnit;
	                	}
	                    s += '<br/>' + this.series.name + ': ' +
	                        this.y + poUnit;
	                });
	                var tipValue = 0;
	                var len = tipValues.length;
	                for(var i=0;i<len;i++){ 
	            		if(xValue[i] == this.x){
	            			tipValue = tipValues[i];
	            			 s += '<br/>'+tipText+': '+tipValue;
	            			break;
	            		}
	            	} 
	                return s;
	            },
    			shared: true
			},
			plotOptions : {
				series : {
					cursor : 'pointer',
					pointWidth : 30
				}
			},
			series: [{
	            name: data.seriesNames[0],
	            type: 'column',
	            color: '#058DC7',
	            data: data.yValues[0],	
	            tooltip: {
        			valueSuffix: yLeftUnit
   				},
   				dataLabels: {
	                enabled: true,
	                rotation: 0,
	                color: '#058DC7',
	                align: 'left',
	                x: 3,
	                y: 4,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
	        }, {
	            name: data.seriesNames[1],
	            yAxis: 1,
	            type: line_type,
	            data: data.yValues[1],
	            color: '#ED561B',
	            tooltip: {
        			valueSuffix: yRightUnit
   				},
   				 dataLabels: {
	                enabled: true,
	                color: '#ED561B',
	                align: 'right',
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
	        }]
		});
	}
	
	/**
	 * 折线图
	 */
	else if (chartType == "line") {
		chart = new Highcharts.Chart({
			chart : {
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
				type : chartType,
	            borderColor: '#6195CA',
            borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1
			},
			lang:{ 
				contextButtonTitle: contxtTitle,
				exportButtonTitle: '导出',
				printChart: '打印',
				downloadJPEG:"下载JPEG 图片",
				downloadPDF: "下载PDF文档",
				downloadPNG: "下载PNG 图片",
				downloadSVG: "下载SVG 矢量图"
			},
			credits: creditObj,	
			title : {
				text : title
			},
			subtitle: {
	            text: subtitle
		    },
	        exporting: {
	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,
				labels : {
					align : 'left',
					formatter : function() {
						return this.value;
					},
					rotation : 20,
					staggerLines : 1,
					overflow: 'justify',
//					style: { 
//                        writingMode:'tb-rl',   //文字竖排样式,
//                    },
//					y:5
				},
				//tickInterval : 1,
				title : {
					text : xTitle,
					align: "low",
					x: -16,
					y: -30
				}
			},
			yAxis : {
				labels : {
					formatter : function() {
						return this.value+yLeftUnit;
					},
	                align: "right",
					x: -15,
					y: 2
				},
				//tickInterval : 3,
				title : {
					text : yLeftTitle
				},
				offset: 0
			},
			//是否有竖线
			 tooltip: {
				 formatter: function () {
					 var s = '<b>' + this.x + '</b>';
		                $.each(this.points, function (poIndex) {
		                    s += '<br/>' + this.series.name + ': ' +
		                        this.y+tipUnit;
		                });
		                var tip = 0;
		                var tip1 = 0;
		                var tip2 = 0;
		                var len = tipValues.length;
		                for(var i=0;i<len;i++){ 
		            		if(xValue[i] == this.x){
		            			tip = tipValues[i];
		            			tip1 = tipValues1[i];
		            			tip2 = tipValues2[i]
		            			 s += '<br/>'+tipText+': '+tip;
		            			 s += '<br/>'+tipText1+': '+tip1;
		            			 s += '<br/>'+tipText2+': '+tip2;
		            			break;
		            		}
		            	} 
		                return s;
		            },
				
                shared: true
			 },
			plotOptions : {
				series : {
					cursor : 'pointer',
					pointWidth : 30
				},
				line : {
					// 允许线性图上的数据点进行点击
					allowPointSelect : true,
					// 是否在图注中显示。
					showInLegend : true,
					// 调整图像顺序关系
					zIndex : 3
				}
			},
			series : [ {
				type : chartType,
				name : data.seriesNames[0],
				data : data.yValues[0]
			} ]
		});
	}
	
	/**
	 * 折线、散点图
	 */
	else if (chartType == "scatter_line") {
		Highcharts.setOptions({
			lang: {
		        resetZoom: '重置',
		        resetZoomTitle:"重置缩放比例"
			}
		});

		chart = new Highcharts.Chart({
			chart : {
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
				zoomType:"xy",
	            borderColor: '#6195CA',
            	borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1
			},
			lang:{ 
				contextButtonTitle: contxtTitle,
				exportButtonTitle: '导出',
				printChart: '打印',
				downloadJPEG:"下载JPEG 图片",
				downloadPDF: "下载PDF文档",
				downloadPNG: "下载PNG 图片",
				downloadSVG: "下载SVG 矢量图"
			},
			credits: creditObj,	
			title : {
				text : title
			},
			subtitle: {
	            text: subtitle
		    },
	        exporting: {
	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,
				labels : {
					align : 'center',
					formatter : function() {
						return (this.value.replace(',','<br/>'));
					},
					rotation : 0,
					staggerLines : 1,
					overflow: 'justify',

						
				},
				//tickInterval : 1,
				title : {
					text : xTitle,
					align: "low",
					x: -30,
					y: -20
				}
			},
			yAxis : {
				labels : {
					formatter : function() {
						return this.value+yLeftUnit;
					},
	                align: "right",
					x: -15,
					y: 2
				},
				min:0,
				//tickInterval : 3,
				title : {
					text : yLeftTitle
				},
				offset: 0
			},
			tooltip: {
	            formatter: function () {
	                return this.x +'<br/>' +this.series.name+': '+this.y;
	            }
		    },
			series : [ {
	            type: 'scatter',
	            color:'black', 
	            name: data.seriesNames[0],
	            data: data.yValues[0],
	            marker: {
	                radius: 4
	            }
	        },{
				type : 'line',
				color:'#F78223', 
				name : data.seriesNames[1],
				data : data.yValues[1]
			},{
				type : 'line',
				color:'blue', 
				name : data.seriesNames[2],
				data : data.yValues[2]
			} ]
		});
	}
			
}

function myChart(containerId, obj, funcCode) {

	this.containerId = containerId; //div的id
	var chartWidth = obj.chartWidth; //图表宽度
	var chartHight = obj.chartHight; //图表高度
	var chartText = obj.chartText; //图表名称
	var xText = obj.xText; //X轴名称
	var yText_left = obj.yText_left; //Y轴名称(左边Y轴)
	var yFormat_left = obj.yFormat_left;//Y轴format格式(左边Y轴)
	var yText_right = obj.yText_right; //Y轴名称(右边Y轴)
	var yFormat_right = obj.yFormat_right;//Y轴format格式(右边Y轴)
	var seriesName = obj.seriesName; //系列名称
	var chartType = obj.chartType; //图表类型
	var chartType1 = obj.chartType1; //图表类型
	var chartType2 = obj.chartType2; //图表类型
	var functionCode = funcCode;
	

	/**
	 * 折线图
	 */
if (chartType == "column_line") {
		$(function() {
			$(document).ready(function() {
				chart = new Highcharts.Chart( {
					chart : {
						renderTo : containerId,
						width : chartWidth,
						hight : chartHight
					},
					lang : {
						contextButtonTitle: contxtTitle,
						printChart : '打印',
						downloadJPEG : "下载JPEG 图片",
						downloadPDF : "下载PDF文档",
						downloadPNG : "下载PNG 图片",
						downloadSVG : "下载SVG 矢量图"
					},
					credits : {
						enabled : false
					},
					title : {
						text : chartText
					},
					xAxis : {
						categories :[],
						title : {
							text : xText
						},
						labels: {
			                overflow: 'justify',
			                rotation: 90,
			                overflow: 'justify'
			            }
						
					},
					yAxis: [{ // Primary yAxis
			            labels: {
			                format: '{value}'+yFormat_left,
			                style: {
			                    color: '#058DC7'
			                }
			            },
			            title: {
			                text: yText_left,
			                style: {
			                    color: Highcharts.getOptions().colors[0]
			                }
			            }
			        }, { // Secondary yAxis
			            title: {
			                text: yText_right,
			                style: {
			                    color: Highcharts.getOptions().colors[1]
			                }
			            },
			            labels: {
			                format: '{value}'+yFormat_right,
			                style: {
			                    color: '#ED561B'
			                }
			            },
			            opposite: true
			        }],
			        tooltip: {
            			shared: true
        			},
					plotOptions : {
						series : {
							cursor : 'pointer',
							pointWidth : 30
						}
					},
					series: [{
			            name: '',
			            type: chartType1,
			            color: '#058DC7',
			            data: [],	
			            tooltip: {
                			valueSuffix: yFormat_left
           				},
           				dataLabels: {
			                enabled: true,
			                rotation: 0,
			                color: '#058DC7',
			                align: 'right',
			                x: 3,
			                y: 4,
			                style: {
			                    fontSize: '13px',
			                    fontFamily: 'Verdana, sans-serif'
			                }
			            }
			        }, {
			            name: '',
			            yAxis: 1,
			            type: chartType2,
			            data: [],
			            color: '#ED561B',
			            tooltip: {
                			valueSuffix: yFormat_right
           				},
           				 dataLabels: {
			                enabled: true,
			                color: '#ED561B',
			                style: {
			                    fontSize: '13px',
			                    fontFamily: 'Verdana, sans-serif'
			                }
			            }
			        }]
				});

			});
		});
	};
	
	/**
	 * 折线图
	 */
	if (chartType == "line") {
		$(function() {
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart : {
						renderTo : containerId,
						width : chartWidth,
						hight : chartHight,
						type : chartType
					},
					lang:{ 
						contextButtonTitle: contxtTitle,
						exportButtonTitle: '导出',
						printChart: '打印',
						downloadJPEG:"下载JPEG 图片",
						downloadPDF: "下载PDF文档",
						downloadPNG: "下载PNG 图片",
						downloadSVG: "下载SVG 矢量图"
					},
					credits : {
						enabled : false
					},
					title : {
						text : chartText
					},
					xAxis : {
						categories : [],
						labels : {
							align : 'center',
							formatter : function() {
								return this.value;
							},
							rotation : 10,
							staggerLines : 1,
							overflow: 'justify'
						},
						//tickInterval : 1,
						title : {
							text : xText
						}
					},
					yAxis : {
						labels : {
							align : 'right',
							formatter : function() {
								return this.value;
							},
			                align: "right",
							x: -6,
							y: -2
						},
						//tickInterval : 3,
						title : {
							text : yText_left
						}
					},
					//是否有竖线
					/* tooltip: {
		                   crosshairs: true
					 },*/
					plotOptions : {
						series : {
							cursor : 'pointer',
							pointWidth : 30
						},
						line : {
							// 允许线性图上的数据点进行点击
							allowPointSelect : true,
							// 数据点的点击事件
							events : {
								click : functionCode
							},
							// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
							point : {
								events : {
									click : functionCode
								}
							},
							// 是否在图注中显示。
							showInLegend : true,
							// 调整图像顺序关系
							zIndex : 3
						}
					},
					series : [ {
						type : chartType,
						name : seriesName,
						data : []
					} ]
				});

			});
		});
	};

	/**
	 * 纵向柱形图
	 */
	if (chartType == "column") {
		$(function() {
			$(document).ready(function() {
				chart = new Highcharts.Chart( {
					chart : {
						renderTo : containerId,
						width : chartWidth,
						hight : chartHight,
						type : chartType
					},
					lang : {
						exportButtonTitle : '导出',
						printChart : '打印',
						downloadJPEG : "下载JPEG 图片",
						downloadPDF : "下载PDF文档",
						downloadPNG : "下载PNG 图片",
						downloadSVG : "下载SVG 矢量图"
					},
					credits : {
						enabled : false
					},
					title : {
						text : chartText
					},
					xAxis : {
						categories : [],
						labels : {
							align : 'center',
							formatter : function() {
								return this.value;
							},
							rotation : 10,
							staggerLines : 1,
							overflow: 'justify'
						},
						//tickInterval : 1,
						title : {
							text : xText
						}
					},
					yAxis : {
						labels : {
							align : 'right',
							formatter : function() {
								return this.value+yFormat_left;
							}
						},
						//tickInterval : 3,
						title : {
							text : yText_left
						}
					},
					plotOptions : {
						series : {
							cursor : 'pointer',

							pointWidth : 30
						},
						dataLabels : {
							enabled : true
						},
						column : {
							// 允许线性图上的数据点进行点击
							allowPointSelect : true,
							// 数据点的点击事件
							events : {
								click : functionCode
							},
							// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
							point : {
								events : {
									click : functionCode
								}
							},
							// 是否在图注中显示。
							showInLegend : true,
							// 调整图像顺序关系
							zIndex : 3
						}
					},
					series : [ {
						type : chartType,
						name : seriesName,
						data : []
					} ]
				});
			});
		});
	}

	/**
	 * 曲线图
	 */
	if (chartType == "spline") {
		$(function() {
			$(document).ready(function() {
				chart = new Highcharts.Chart( {
					chart : {
						renderTo : containerId,
						width : chartWidth,
						hight : chartHight,
						type : chartType
					},
					lang : {
						exportButtonTitle : '导出',
						printChart : '打印',
						downloadJPEG : "下载JPEG 图片",
						downloadPDF : "下载PDF文档",
						downloadPNG : "下载PNG 图片",
						downloadSVG : "下载SVG 矢量图"
					},
					credits : {
						enabled : false
					},
					title : {
						text : chartText
					},
					xAxis : {
						categories : xCategories,
						labels : {
							align : 'center',
							formatter : function() {
								return this.value;
							},
							rotation : 10,
							staggerLines : 1,
							overflow: 'justify'
						},
						//tickInterval : 1,
						title : {
							text : xText
						}
					},
					yAxis : {
						labels : {
							align : 'right',
							formatter : function() {
								return this.value;
							}
						},
						//tickInterval : 3,
						title : {
							text : yText
						}
					},
					plotOptions : {

						series : {
							cursor : 'pointer',
							events : {
								legendItemClick : false
							},
							pointWidth : 30
						},
						spline : {
							// 允许线性图上的数据点进行点击
							allowPointSelect : true,
							// 数据点的点击事件
							events : {
								click : functionCode
							},
							// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
							point : {
								events : {
									click : functionCode
								}
							},
							// 是否在图注中显示。
							showInLegend : true,
							// 调整图像顺序关系
							zIndex : 3
						}

					},
					series : [ {
						type : chartType,
						name : seriesName,
						data : []
					} ]
				});

			});
		});
	}

	/**
	 * 饼型图
	 */
	if (chartType == "pie") {
		$(function() {
			$(document).ready(
					function() {
						chart = new Highcharts.Chart( {
							chart : {
								renderTo : containerId,
								width : chartWidth,
								hight : chartHight,
								type : chartType
							},
							lang : {
								exportButtonTitle : '导出',
								printChart : '打印',
								downloadJPEG : "下载JPEG 图片",
								downloadPDF : "下载PDF文档",
								downloadPNG : "下载PNG 图片",
								downloadSVG : "下载SVG 矢量图"
							},
							credits : {
								enabled : false
							},
							title : {
								text : chartText
							},
							xAxis : {
								categories : xCategories,
								labels : {
									align : 'center',
									formatter : function() {
										return this.value;
									},
									rotation : 10,
									staggerLines : 1
								},
								//tickInterval : 1,
								title : {
									text : xText
								}
							},
							yAxis : {
								labels : {
									align : 'right',
									formatter : function() {
										return this.value;
									}
								},
								//tickInterval : 3,
								title : {
									text : yText
								}
							},
							tooltip : {
								formatter : function() {
									return '<b>'
											+ this.point.name
											+ '</b>: '
											+ Highcharts.numberFormat(
													this.percentage, 1)
											+ '% ('
											+ Highcharts.numberFormat(this.y,
													0, ',') + yText + ' )';
								},
								useHTML : true
							},
							plotOptions : {
								dataLabels : {
									enabled : true,
									color : '#000000',
									connectorColor : '#000000',
									formatter : function() {
										return '<b>' + this.point.name
												+ '</b>: ' + this.percentage
												+ ' %';
									}
								},
								pie : {
									// 是否允许扇区点击
									allowPointSelect : true,
									// 点击后，滑开的距离
									slicedOffset : 5,
									// 饼图的中心坐标
									// 饼图的大小
									// 数据标签
									dataLabels : {
										// 是否允许标签
										enabled : true,
										// 标签与图像元素之间的间距
										distance : 10
									},
									// 数据点的点击事件
									events : {
										click : functionCode
									},
									// 是否忽略隐藏的项
									ignoreHiddenPoint : true,
									// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
									point : {
										events : {
											click : functionCode
										}
									},
									// 是否在图注中显示。
									showInLegend : true,
									// 调整图像顺序关系
									zIndex : 0
								}
							},
							series : [ {
								type : chartType,
								name : chartText,
								data : []
							} ]
						});

					});
		});
	}

	/**
	 * 散播型
	 */
	if (chartType == "scatter") {
		$(function() {
			$(document).ready(function() {

				chart = new Highcharts.Chart( {

					chart : {
						renderTo : containerId,
						width : chartWidth,
						hight : chartHight,
						type : chartType
					},
					lang : {
						exportButtonTitle : '导出',
						printChart : '打印',
						downloadJPEG : "下载JPEG 图片",
						downloadPDF : "下载PDF文档",
						downloadPNG : "下载PNG 图片",
						downloadSVG : "下载SVG 矢量图"
					},
					credits : {
						enabled : false
					},

					title : {
						text : chartText
					},

					xAxis : {
						categories : xCategories,
						labels : {
							align : 'center',
							formatter : function() {
								return this.value;
							},
							rotation : 10,
							staggerLines : 1,
							overflow: 'justify'
						},
						//tickInterval : 1,
						title : {
							text : xText
						}
					},

					yAxis : {
						labels : {
							align : 'right',
							formatter : function() {
								return this.value;
							}
						},
						//tickInterval : 3,
						title : {
							text : yText
						}
					},

					plotOptions : {

						series : {
							cursor : 'pointer',
							events : {
								legendItemClick : false
							},
							pointWidth : 30
						},

						scatter : {
							// 允许线性图上的数据点进行点击
							allowPointSelect : true,
							// 数据点的点击事件
							events : {
								click : functionCode
							},
							// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
							point : {
								events : {
									click : functionCode
								}
							},
							// 是否在图注中显示。
							showInLegend : true,
							// 调整图像顺序关系
							zIndex : 3
						}

					},

					series : [ {
						type : chartType,
						name : seriesName,
						data : []
					} ]
				});

			});
		});
	}

};

function groupShowChart(id, data, startTime){
	var chartIndex = data.index == undefined ? -1 : data.index;
	var detailType = data.type== undefined ? -1 : data.type;
	
	var chartType = data.chartType;//类型
	var chartWidth = data.chartWidth;//宽
	var chartHight = data.chartHight;//高
	var title = data.title;//图标名称
	var xTitle = data.xTitle;//横坐标名称
	var yLeftTitle = data.yLeftTitle;//纵坐标左标题
	var yRightTitle = data.yRightTitle;//纵坐标右标题
	var yLeftUnit = data.yLeftUnit;//左单位
	var yRightUnit = data.yRightUnit;//右单位
	var defaultValue = data.defaultValue;
	var xValue = data.xValue;
	var contxtTitle = "打印或导出";
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;
	var creditsEnable = data.index == undefined ? false : true;
	//alert(chartType);
	var containerId = id;
	var date = startTime;
	//alert("type:" + chartType);
	for(var i=0;i<xValue.length;i++){ 
		if(xValue[i] == null || xValue[i] ==""){
			xValue[i] = " ";
		}
	} 
	var creditsInfo = {
			enabled:creditsEnable,
			text: '详情',
            href: 'javascript:javascript:'+funcName+'('+chartIndex+','+detailType +')',
			style: {
				cursor: 'pointer',
				color: 'blue',
				fontSize: '16px'
			},
            position: {
                align: 'right',
                verticalAlign:'bottom',
                x: -10,y:-10
            }
        };

	//纵向柱状图
 if(chartType == "column") { 
		chart = new Highcharts.Chart( {
			chart : {
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
				type : chartType,
	            borderColor: '#6195CA',
            	borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1
			},
			lang : {
				contextButtonTitle: contxtTitle,
				exportButtonTitle : '导出',
				printChart : '打印',
				downloadJPEG : "下载JPEG 图片",
				downloadPDF : "下载PDF文档",
				downloadPNG : "下载PNG 图片",
				downloadSVG : "下载SVG 矢量图",
				noData: "没有符合条件的数据"
			},
        	credits: creditsInfo,	
			title : {
				text : title
			},
	        exporting: {
	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,
				labels : {
					align : 'left',
					formatter : function() {
//						var x = this.value;
//						var y;
//						if(x.length>7){
//							y = x.substring(0,7)+"...";
//						}else{
//							y = this.value;
//						}
						return this.value;
					},
	                rotation: 20,
					staggerLines : 1,
					overflow: 'justify',
//					style: { 
//                        writingMode:'tb-rl',   //文字竖排样式,
//                    },
//					y:5

				},
				//tickInterval : 1,
				title : {
					//text : xTitle
				}
			},
			yAxis : {
				labels : {

					formatter : function() {
						return this.value+yLeftUnit;
					},
					style: {
	                    color: '#058DC7'
	                },
					rotation: 0,
					offset: 0,
	                align: "right",
					x: -5,
					y: 2
				},
				//tickInterval : 3,
				title : {
					text : yLeftTitle,
					style: {
	                    color: '#058DC7'
	                }
				}
			},
			plotOptions : {
				series : {
					cursor : 'pointer',
					
					pointWidth : 30
				},
				dataLabels : {
					enabled : true
				},

				column : {
					// 允许线性图上的数据点进行点击
					allowPointSelect : true,
					events: { 
		                click: function(e) { 
		                	var tabId = "performanceDetailListNav";
		                	var title = "月度班组绩效明细查询";
		                	var group = e.point.category;
		                	var startTime = date;
		                	var url = "groupPerformanceChart/performanceDetailListMonth.do?date="+startTime+"&group="+group;
		                	navTab.openTab(tabId, url, {title:title, fresh:false});
//		                    alert(e.point.category); 
		                } 
		            } ,
					// 是否在图注中显示。					
					showInLegend : true,
					// 调整图像顺序关系
					zIndex : 3
				}
			},
			
			series : [ {
				name : data.seriesNames[0],
				data : data.yValues[0],
				color: '#058DC7',
   				dataLabels: {
	                enabled: true,
	                rotation: 0,
	                color: '#058DC7',
	                align: 'left',
	                x: 3,
	                y: 4,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
			} ]
		});
	}
			
}
function onegroupShowChart(id, data, startTime, shiftGroup){
	var chartIndex = data.index == undefined ? -1 : data.index;
	var detailType = data.type== undefined ? -1 : data.type;
	
	var chartType = data.chartType;//类型
	var chartWidth = data.chartWidth;//宽
	var chartHight = data.chartHight;//高
	var title = data.title;//图标名称
	var xTitle = data.xTitle;//横坐标名称
	var yLeftTitle = data.yLeftTitle;//纵坐标左标题
	var yRightTitle = data.yRightTitle;//纵坐标右标题
	var yLeftUnit = data.yLeftUnit;//左单位
	var yRightUnit = data.yRightUnit;//右单位
	var defaultValue = data.defaultValue;
	var xValue = data.xValue;
	var contxtTitle = "打印或导出";
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	//alert(chartType);
	var containerId = id;
	var date = startTime;
	//alert("type:" + chartType);
	for(var i=0;i<xValue.length;i++){ 
		if(xValue[i] == null || xValue[i] ==""){
			xValue[i] = " ";
		}
	} 
	var creditsInfo = {
			enabled:creditsEnable,
			text: '详情',
            href: 'javascript:javascript:'+funcName+'('+chartIndex+','+detailType +')',
			style: {
				cursor: 'pointer',
				color: 'blue',
				fontSize: '16px'
			},
            position: {
                align: 'right',
                verticalAlign:'bottom',
                x: -10,y:-10
            }
        };

	//纵向柱状图
 if(chartType == "column") { 
		chart = new Highcharts.Chart( {
			chart : {
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
				type : chartType,
	            borderColor: '#6195CA',
            	borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1
			},
			lang : {
				contextButtonTitle: contxtTitle,
				exportButtonTitle : '导出',
				printChart : '打印',
				downloadJPEG : "下载JPEG 图片",
				downloadPDF : "下载PDF文档",
				downloadPNG : "下载PNG 图片",
				downloadSVG : "下载SVG 矢量图",
				noData: "没有符合条件的数据"
			},
        	credits: creditsInfo,	
			title : {
				text : title
			},
	        exporting: {
	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,
				labels : {
					align : 'left',
					formatter : function() {
//						var x = this.value;
//						var y;
//						if(x.length>7){
//							y = x.substring(0,7)+"...";
//						}else{
//							y = this.value;
//						}
						return this.value;
					},
	                rotation: 20,
					staggerLines : 1,
					overflow: 'justify',
//					style: { 
//                        writingMode:'tb-rl',   //文字竖排样式,
//                    },
//					y:5

				},
				//tickInterval : 1,
				title : {
					//text : xTitle
				}
			},
			yAxis : {
				labels : {

					formatter : function() {
						return this.value+yLeftUnit;
					},
					style: {
	                    color: '#058DC7'
	                },
					rotation: 0,
					offset: 0,
	                align: "right",
					x: -5,
					y: 2
				},
				//tickInterval : 3,
				title : {
					text : yLeftTitle,
					style: {
	                    color: '#058DC7'
	                }
				}
			},
			plotOptions : {
				series : {
					cursor : 'pointer',
					
					pointWidth : 30
				},
				dataLabels : {
					enabled : true
				},

				column : {
					// 允许线性图上的数据点进行点击
					allowPointSelect : true,
					events: { 
		                click: function(e) { 
		                	var tabId = "performanceDetailListNav";
		                	var title = "月度班组绩效明细查询";
		                	var group = shiftGroup;
		                	var startTime = e.point.category;
		                	var url = "shiftGroupPerformanceChart/performanceDetailListMonthNew.do?date="+startTime+"&group="+group;
		                	navTab.openTab(tabId, url, {title:title, fresh:false});
//		                    alert(e.point.category); 
		                } 
		            } ,
					// 是否在图注中显示。					
					showInLegend : true,
					// 调整图像顺序关系
					zIndex : 3
				}
			},
			
			series : [ {
				name : data.seriesNames[0],
				data : data.yValues[0],
				color: '#058DC7',
   				dataLabels: {
	                enabled: true,
	                rotation: 0,
	                color: '#058DC7',
	                align: 'left',
	                x: 3,
	                y: 4,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
			} ]
		});
	}
			
}

function ftjshowChart(id, data){
	var chartIndex = data.index == undefined ? -1 : data.index;
	var detailType = data.type== undefined ? -1 : data.type;
	
	var chartType = data.chartType;//类型
	var chartWidth = data.chartWidth;//宽
	var chartHight = data.chartHight;//高
	var title = data.title;//图标名称
	var subtitle = data.subtitle;//副标题
	var xTitle = data.xTitle;//横坐标名称
	var yLeftTitle = data.yLeftTitle;//纵坐标左标题
	var yRightTitle = data.yRightTitle;//纵坐标右标题
	var tipUnit = data.tipUnit;//提示单位
	var yLeftUnit = data.yLeftUnit;//左单位
	var yRightUnit = data.yRightUnit;//右单位
	var defaultValue = data.defaultValue;
	var xValue = data.xValue;
	var contxtTitle = "打印或导出";
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;
	var creditsEnable = data.index == undefined ? false : true;
	//alert(chartType);
	var containerId = id;
	//alert("type:" + chartType);
	for(var i=0;i<xValue.length;i++){ 
		if(xValue[i] == null || xValue[i] ==""){
			xValue[i] = " ";
		}
	} 
	var tipValues = data.tipValues;//获取焦点提示值
	var tipText = data.tipText;//获取焦点提示语
	var tipValues1 = data.tipValues1;//获取焦点提示值
	var tipText1 = data.tipText1;//获取焦点提示语
	var tipValues2 = data.tipValues2;//获取焦点提示值
	var tipText2 = data.tipText2;//获取焦点提示语
	var creditsInfo = {
			enabled:creditsEnable,
			text: '详情',
            href: 'javascript:javascript:'+funcName+'('+chartIndex+','+detailType +')',
			style: {
				cursor: 'pointer',
				color: 'blue',
				fontSize: '16px'
			},
            position: {
                align: 'right',
                verticalAlign:'bottom',
                x: -10,y:-10
            }
        };
	
	var calculateInfo = {
			enabled:true,
			text: '计算',
            href: 'javascript:javascript:'+funcName+'(this)',
			style: {
				cursor: 'pointer',
				color: 'blue',
				fontSize: '14px'
			},
            position: {
                align: 'right',
                verticalAlign:'top',
                x: -50,y:26
            }
        };
	var creditName = data.creditName;
	var creditObj = creditsInfo;
	if(creditName == "calculate"){
		creditObj = calculateInfo;
	}
	/**
	 * 多折线图
	 */
	
	if (chartType == "lines") {
		chart = new Highcharts.Chart({
			chart : {
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
				type : 'line',
				borderColor: '#6195CA',
				borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1
			},
			
			lang:{ 
				contextButtonTitle: contxtTitle,
				exportButtonTitle: '导出',
				printChart: '打印',
				downloadJPEG:"下载JPEG 图片",
				downloadPDF: "下载PDF文档",
				downloadPNG: "下载PNG 图片",
				downloadSVG: "下载SVG 矢量图"
			},
			credits: creditObj,
			title : {
				text : title
			},
			subtitle: {
	            text: subtitle
		    },
	        exporting: {
	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,

				labels : {
					align : 'left',
					formatter : function() {
						return this.value;
//						var x = this.value;
//						var y;
//						
//						if(x.length>7){
//							y = x.substring(0,7)+"...";
//						}else{
//							y = this.value;
//						}
//						
//						return y
					},	
					rotation : 20,
					overflow: 'justify',
					staggerLines : 1,
//					style: { 
//                        writingMode:'tb-rl',   //文字竖排样式,
//                    },
//					y:5
				},
				
				//tickInterval : 1,
				title : {
					text : xTitle,
					align: "low",
					x: -16,
					y: -20
					//style: {
					//	fontWeight: 'bold', //刻度字体加粗
					//}
				}

			},
			yAxis : {
				//tickInterval : 3,
				offset: 0,
				title : {
					align: "high",
					rotation: 0,
					offset: 0,
					y: -20,
					text : yLeftTitle,

				},
				min:0,
				labels : {
					formatter : function() {
						return this.value+yLeftUnit;
					},
	                align: "right",
					x: -15,
					y: 0
				},

				/*plotLines:[{
	                color:'red',            //线的颜色，定义为红色
	                dashStyle:'LongDash',     //默认是值，这里定义为长虚线
	                value: defaultValue,              //定义在那个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
	                width:2,               //标示线的宽度，2px
	                label:{
	                    text:'',     //标签的内容
	                    align:'left',                //标签的水平位置，水平居左,默认是水平居中center
	                    x:10,                         //标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
	                    style:{
	                        fontSize:'14px',
	                        fontWeight:'bold'
	                    },
						formatter : function() {
							return this.value;
						}
	                }
	            }]*/
			},
			//是否有竖线
			 tooltip: {
				 formatter: function (formatObj) {
		                s = this.x + "<br/>"+this.series.name+":" + +this.y;
		                var len = tipValues.length;
		                for(var i=0;i<len;i++){ 
		            		if(xValue[i] == this.x){
		            			tipValue = tipValues[i];
		            			tipValue1 = tipValues1[i];
		            			tipValue2 = tipValues2[i];
		            			 s += '<br/>'+tipText+': '+tipValue;
		            			 s += '<br/>'+tipText1+': '+tipValue1;
		            			 s += '<br/>'+tipText2+': '+tipValue2;
		            			break;
		            		}
		            	} 
		                return s;
		            },
		            shared: false
			 },
			plotOptions : {
				series : {
					cursor : 'pointer',
					pointWidth : 30,
					
				},
				line : {
					// 允许线性图上的数据点进行点击
					allowPointSelect : true,
					// 是否在图注中显示。
					showInLegend : true,
					// 调整图像顺序关系
					zIndex : 3
				}
			},
			series : [ {
	            name: data.seriesNames[0],
	            data: data.yValues[0],
	            dashStyle: 'dash',
	            width: 0.2
	        }, {
	            name: data.seriesNames[1],
	            data: data.yValues[1]
	        }]
		});
	}/**
	 * 折线图
	 */
	else if (chartType == "line") {
		chart = new Highcharts.Chart({
			chart : {
				renderTo : containerId,
				width : chartWidth,
				hight : chartHight,
				type : chartType,
	            borderColor: '#6195CA',
            borderWidth: 1,
	            plotBorderColor: '#C0C0C8',
	            plotBorderWidth: 1
			},
			lang:{ 
				contextButtonTitle: contxtTitle,
				exportButtonTitle: '导出',
				printChart: '打印',
				downloadJPEG:"下载JPEG 图片",
				downloadPDF: "下载PDF文档",
				downloadPNG: "下载PNG 图片",
				downloadSVG: "下载SVG 矢量图"
			},
			credits: creditObj,	
			title : {
				text : title
			},
			subtitle: {
	            text: subtitle
		    },
	        exporting: {
	            filename: 'custom-file-name'
	        },
			xAxis : {
				categories : xValue,
				labels : {
					align : 'left',
					formatter : function() {
						return this.value;
					},
					rotation : 20,
					staggerLines : 1,
					overflow: 'justify',
//					style: { 
//                        writingMode:'tb-rl',   //文字竖排样式,
//                    },
//					y:5
				},
				//tickInterval : 1,
				title : {
					text : xTitle,
					align: "low",
					x: -16,
					y: -30
				}
			},
			yAxis : {
				labels : {
					formatter : function() {
						return this.value+yLeftUnit;
					},
	                align: "right",
					x: -15,
					y: 2
				},
				//tickInterval : 3,
				title : {
					text : yLeftTitle
				},
				offset: 0
			},
			//是否有竖线
			 tooltip: {
				 formatter: function () {
					 var s = '<b>' + this.x + '</b>';
		                $.each(this.points, function (poIndex) {
		                    s += '<br/>' + this.series.name + ': ' +
		                        this.y+tipUnit;
		                });
		                var tip = 0;
		                var tip1 = 0;
		                var tip2 = 0;
		                var len = tipValues.length;
		                for(var i=0;i<len;i++){
		            		if(xValue[i] == this.x){
		            			tip = tipValues[i];
		            			tip1 = tipValues1[i];
		            			tip2 = tipValues2[i];
		            			 s += '<br/>'+tipText+': '+tip;
		            			 s += '<br/>'+tipText1+': '+tip1;
		            			 s += '<br/>'+tipText2+': '+tip2;
		            			break;
		            		}
		            	} 
		                return s;
		            },
				
                shared: true
			 },
			plotOptions : {
				series : {
					cursor : 'pointer',
					pointWidth : 30
				},
				line : {
					// 允许线性图上的数据点进行点击
					allowPointSelect : true,
					// 是否在图注中显示。
					showInLegend : true,
					events: { 
		                click:function(e) { 
		                	var tabId = "newperformanceDetailListNav";
		                	var title = "月度班组绩效明细查询";
		                	var group = data.shiftGroup;
		                	var startTime = e.point.category;
		                	var factory = data.factory;
		                	var url = "groupPerformanceChart/performanceDetailListMonth.do?date="+startTime+"&group="+group+"&factory="+factory;
		                	navTab.openTab(tabId, url, {title:title, fresh:true});
		                }, 
					},
					// 调整图像顺序关系
					zIndex : 3
				}
			},
			series : [ {
				type : chartType,
				name : data.seriesNames[0],
				data : data.yValues[0]
			} ]
		});
	}
		
}

function showUnderWarrantyChar(id, data,callback) {
	var chartIndex = data.index == undefined ? -1 : data.index;
	var detailType = data.type== undefined ? -1 : data.type;
	
	var chartType = data.chartType;//类型
	var chartWidth = data.chartWidth;//宽
	var chartHight = data.chartHight;//高
	var title = data.title;//图标名称
	var subtitle = data.subtitle;//副标题
	var xTitle = data.xTitle;//横坐标名称
	var yLeftTitle = data.yLeftTitle;//纵坐标左标题
	var yRightTitle = data.yRightTitle;//纵坐标右标题
	var tipUnit = data.tipUnit;//提示单位
	var yLeftUnit = data.yLeftUnit;//左单位
	var yRightUnit = data.yRightUnit;//右单位
	var defaultValue = data.defaultValue;
	var xValue = data.xValue;
	var contxtTitle = "打印或导出";
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;
	var creditsEnable = data.index == undefined ? false : true;
	//alert(chartType);
	var containerId = id;
	//alert("type:" + chartType);
	for(var i=0;i<xValue.length;i++){ 
		if(xValue[i] == null || xValue[i] ==""){
			xValue[i] = " ";
		}
	}
	var tipValues = data.tipValues;//获取焦点提示值
	var tipText = data.tipText;//获取焦点提示语
	var tipValues1 = data.tipValues1;//获取焦点提示值
	var tipText1 = data.tipText1;//获取焦点提示语
	var tipValues2 = data.tipValues2;//获取焦点提示值
	var tipText2 = data.tipText2;//获取焦点提示语
	var creditsInfo = {
			enabled:creditsEnable,
			text: '详情',
            href: 'javascript:javascript:'+funcName+'('+chartIndex+','+detailType +')',
			style: {
				cursor: 'pointer',
				color: 'blue',
				fontSize: '16px'
			},
            position: {
                align: 'right',
                verticalAlign:'bottom',
                x: -10,y:-10
            }
        };
	
	var calculateInfo = {
			enabled:true,
			text: '计算',
            href: 'javascript:javascript:'+funcName+'(this)',
			style: {
				cursor: 'pointer',
				color: 'blue',
				fontSize: '14px'
			},
            position: {
                align: 'right',
                verticalAlign:'top',
                x: -50,y:26
            }
        };
	var creditName = data.creditName;
	var creditObj = creditsInfo;
	if(creditName == "calculate"){
		creditObj = calculateInfo;
	}
	chart = new Highcharts.Chart({
		chart : {
			renderTo : containerId,
			width : chartWidth,
			hight : chartHight,
			type : 'line',
			borderColor: '#6195CA',
			borderWidth: 1,
            plotBorderColor: '#C0C0C8',
            plotBorderWidth: 1
		},
		
		lang:{ 
			contextButtonTitle: contxtTitle,
			exportButtonTitle: '导出',
			printChart: '打印',
			downloadJPEG:"下载JPEG 图片",
			downloadPDF: "下载PDF文档",
			downloadPNG: "下载PNG 图片",
			downloadSVG: "下载SVG 矢量图"
		},
		credits: creditObj,
		title : {
			text : title
		},
		subtitle: {
            text: subtitle
	    },
        exporting: {
            filename: 'custom-file-name'
        },
		xAxis : {
			categories : xValue,

			labels : {
				align : 'left',
				formatter : function() {
					return this.value;
				},	
				rotation : 20,
				overflow: 'justify',
				staggerLines : 1,
			},
			title : {
				text : xTitle,
				align: "low",
				x: -16,
				y: -20
			}

		},
		yAxis : {
			//tickInterval : 3,
			offset: 0,
			title : {
				align: "high",
				rotation: 0,
				offset: 0,
				y: -20,
				text : yLeftTitle,

			},
			min:0,
			labels : {
				formatter : function() {
					return this.value+yLeftUnit;
				},
                align: "right",
				x: -15,
				y: 0
			},
		},
		//是否有竖线
		 tooltip: {
			 formatter: function (formatObj) {
	                s = this.x + "<br/>"+this.series.name+":" + +this.y;
	                var len = tipValues.length;
	                for(var i=0;i<len;i++){ 
	            		if(xValue[i] == this.x){
	            			tipValue = tipValues[i];
	            			tipValue1 = tipValues1[i];
	            			tipValue2 = tipValues2[i];
	            			 s += '<br/>'+tipText+': '+tipValue;
	            			 s += '<br/>'+tipText1+': '+tipValue1;
	            			 s += '<br/>'+tipText2+': '+tipValue2;
	            			break;
	            		}
	            	} 
	                return s;
	            },
	            shared: false
		 },
		plotOptions : {
			series : {
				cursor : 'pointer',
				pointWidth : 30,
				lineWidth: 1
			},
			line : {
				// 允许线性图上的数据点进行点击
				allowPointSelect : true,
				// 是否在图注中显示。
				showInLegend : true,
				// 调整图像顺序关系
				zIndex : 3
			}
		},
		series : [{
            name: data.seriesNames[0],
            data: data.yValues[1]
        }]
	});
}


