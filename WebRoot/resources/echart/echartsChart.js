var echart;
var option;
function noFuncName()
{
	alert('没有对应的函数');
}
function showEChart(id, data){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	var colorList = data.colors;                             //颜色
	
	var itemStyle;
	var label ={
     	 normal : {
     		 show:true,
     		 position:'top'
     	 }
     };   
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		var obj = new Object();
		if(colorList !='' && colorList!=null){
			itemStyle={
					normal: {
						 color: colorList[i]
					}
			      };
			obj.itemStyle = itemStyle;
		}
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.data = yValues[i];
		obj.label = label;
		array.push(obj);
	}
	var dataArr = array ;
	

	
	/**
	 * 折线图
	 */
	
	if (chartType == "lines") {
		
		option = {
			    title: {
			        text: title,
			        left:'center'
			    },
			    tooltip: {
			        trigger: 'axis',
			        formatter: function(params) {
			            return params[0].name + '<br/>'
			                   + params[0].seriesName + ' : ' + params[0].value + ' ('+yLeftUnit+')<br/>'
			                   + params[1].seriesName + ' : ' + params[1].value + ' ('+yLeftUnit+')';
			        },
			    },
			    legend: {
			    	bottom:'3',
			        data:seriesNames
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '10%',
			        containLabel: true
			    },
			    toolbox: {
			    	show : toolboxShow,
			        feature: {
			            dataView: {readOnly: toolboxFeatureDataViewShow},
			            magicType: {
			            	show :toolboxFeatureMagicTypeShow,
			            	type: toolboxFeatureMagicTypeType
			            	},
			            restore: {show :toolboxFeatureRestoreShow},
			            saveAsImage: {show :toolboxFeatureSaveAsImageShow}
			        }
			    },
			    xAxis: {
			        type: 'category',
			        axisLabel: {
			        	show:true,
			        	interval: 0,
			        	},
			        boundaryGap: false,
			        data: xValue
			    },
			    yAxis: {
			        type: 'value',
			        axisLabel: {
			            formatter: yAxisAxisLabelFormatter
			        }
			    },
			    series : dataArr
			}
	}
	//纵向柱状图
	else if(chartType == "bar") { 
		option = {
				 title:{
				        text:title,
				        left:"center",
				    },
			    tooltip : {
			        trigger: 'axis',
			        formatter: function(params) {
			            return params[0].name + '<br/>'
			                   + params[0].seriesName + ' : ' + params[0].value + ' ('+yLeftUnit+')<br/>'
			                   + params[1].seriesName + ' : ' + params[1].value + ' ('+yLeftUnit+')';
			        },
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			        
			    },
			    legend: {
			    	bottom:'3',
			        data:seriesNames
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '10%',
			        containLabel: true
			    },
			    toolbox: {
			    	show : toolboxShow,
			        feature: {
			            dataView: {readOnly: toolboxFeatureDataViewShow},
			            magicType: {
			            	show :toolboxFeatureMagicTypeShow,
			            	type: toolboxFeatureMagicTypeType
			            	},
			            restore: {show :toolboxFeatureRestoreShow},
			            saveAsImage: {show :toolboxFeatureSaveAsImageShow}
			        }
			    },
			    xAxis : [
			        {   axisLabel: {
			        	show:true,
			        	interval: 0,
			        	rotate: 30,
			        	},
			            type : 'category',
			            data : xValue
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
		                axisLabel: {
					            formatter: yAxisAxisLabelFormatter
					        }
			        }
			    ],
			    series :dataArr
			}
	}
//饼图	
if (chartType == "pie") {
		
	 option = {
			    title: {
			        text: title,
			        left:'center'
			    },
			    tooltip: {
			    	trigger: 'item',
			    	formatter: "{b} : {c}"+yLeftUnit+" ({d}%)"
			    },
			    legend: {
			    	show:true,
			        orient: 'vertical',
			        left: '30',
			        data:seriesNames
			    },
			    toolbox: {
			    	show : toolboxShow,
			        feature: {
			            dataView: {readOnly: true},
			            magicType: {
			            	show :false,
			            	},
			            restore: {show :true},
			            saveAsImage: {show :true}
			        }
			    },
			    calculable : true,
			    series : [
		    		        {
		    		            name:'',
		    		            type:'pie',
		    		            radius : '60%',
		    					selectedMode:'single',
		    		            center: ['50%', '50%'],
		    					itemStyle : {
		    		                normal : {
		    		                    label : {
		    		                        show : true
		    		                    },
		    		                    labelLine : {
		    		                        show : true
		    		                    },
		    		                   
		    		                },
		    		                emphasis : {
		    		                    label : {
		    		                        show : false
		    		                    },
		    		                    labelLine : {
		    		                        show : false
		    		                    }
		    		                }
		    		            },
		    		            data:dataMap
		    		        }
		    		    ]
			}
	}
	
	echart = echarts.init(document.getElementById(id));
	echart.setOption(option);		
}




/**联动图-饼图柱状图*/
var chart1;
var chart2;
var option1;
var option2;
function showEchartPieBarChar(pieId,barId,data){
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
		var xValue = data.xValue;           //x轴坐标值
		var seriesNames = data.seriesNames; //系列名
		var yValues = data.yValues;
		var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
		var creditsEnable = data.index == undefined ? false : true;
		var dataMap = data.childList;                            //联动图
		var childSeriesName = data.childSeriesName;              //联动图子图系列名称
		var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
		
		//组件
		var axisPointerType = data.axisPointerType;//坐标轴指示器
		var tooltipTrigger = data.tooltipTrigger; //触发类型
		var toolboxShow = data.toolboxShow;// 工具配置项
		var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
		var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
		var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
		var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
		var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
		var xAxisType = data.xAxisType;//x坐标轴类型
		var yAxisType = data.yAxisType;//y坐标轴类型
		var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
		var seriesType = data.seriesType; //类型 
		var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
		var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
		var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
		var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
		
		var colorList = [
		   			  '#C1232B','#333366','#27727B','#CC6633','#99CC99',
		   			   '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
		   			   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
		   			   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
		   			];
			 option1 = {
					    title: {
					        text: title,
					        left:'center'
					    },
					    tooltip: {
					    	trigger: 'item',
					    	formatter: "{b} : {c} ({d}%)"
					    },
					    legend: {
					    	show:true,
					    	orient: 'vertical',
						    left:'30',
						    data:seriesNames
					    },
					    toolbox: {
					    	show : toolboxShow,
					        feature: {
					            dataView: {readOnly: true},
					            magicType: {
					            	show :false,
					            	},
					            restore: {show :true},
					            saveAsImage: {show :true}
					        }
					    },
					    calculable : true,
					    series : [
				    		        {
				    		            name:'',
				    		            type:'pie',
				    		            radius : '60%',
				    					selectedMode:'single',
				    		            center: ['50%', '50%'],
				    					itemStyle : {
				    		                normal : {
				    		                    label : {
				    		                        show : true
				    		                    },
				    		                    labelLine : {
				    		                        show : true
				    		                    },
				    		                    color: function(params) {
				    								return colorList[params.dataIndex]
				    							},
				    		                },
				    		                emphasis : {
				    		                    label : {
				    		                        show : false
				    		                    },
				    		                    labelLine : {
				    		                        show : false
				    		                    }
				    		                }
				    		            },
				    		            data:[ ]
				    		        }
				    		    ]
					};
			option2 = {
						 title:{
							    text:'',
						        left:"center",
						    },
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    legend: {
					    	show:false,
					    	data:[],
					    	bottom:'3',
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '10%',
					        containLabel: true
					    },
					    toolbox: {
					    	show : toolboxShow,
					        feature: {
					            dataView: {readOnly: true},
					            magicType: {
					            	show :true,
					            	type: ['line', 'bar']
					            	},
					            restore: {show :true},
					            saveAsImage: {show :true}
					        }
					    },
					    xAxis : [
					        {   axisLabel: {
					        	show:true,
					        	interval: 0,
					        	},
					            type : 'category',
					            data:[]
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value',
				                axisLabel: {
							            formatter: yAxisAxisLabelFormatter
							        }
					        }
					    ],
					    series :[
								{   
									name:childSeriesName,
								    type:'bar',
								    barWidth : barWidth,
								    data:[],
								}   
					       ]
					}

     var h1 = new Array();
     function initData(){
  	
 		for(var i=0; i<dataMap.length;i++){
 			var h2 = new HashMap();
 			h2.put(dataMap[i].name,dataMap[i].value);	
 			h1.push(h2);
 		}
 		
 	}
    
     function loadAll(){
  		chart2.clear();
  	    var option1Arr = new Array();
    		for(var i=0;i<seriesNames.length;i++){
 			var obj = new Object();
 			obj.name = seriesNames[i];
 			obj.value = sumArr(h1[i].get(seriesNames[i]));
 			option1Arr.push(obj);
 		    
 		}
    		option1.series[0].data = option1Arr;  
    		chart1.setOption(option1);
    		
 		  option2.xAxis[0].data = xValue;
 		  var option2Arr = new Array();
 		  for(var i=0; i<xValue.length; i++){
 			option2Arr.push(h1[0].get(seriesNames[0])[i]);
 		  }
 	      option2.series[0].data = option2Arr; 
 	      option2.title.text = seriesNames[0]; 
 	      chart2.setOption(option2);	
     }
     
     function sumArr(arr){
 		var sum=0;
 		for(var i=0; i<arr.length;i++){
 			sum+=arr[i];
 		}
 		return sum;
 	}
     
     function click1(e){
  	   chart2.setOption({
 			title:{
 			    text:seriesNames[e.dataIndex],
 		        left:"center",
 		    },
 		    series :[
 					{
 						name:childSeriesName,
 					    type:'bar',
 					    itemStyle: {
							normal: {
								color: colorList[e.dataIndex]
							}
 					      },
 					    barWidth : barWidth,
 					    data:h1[e.dataIndex].get(seriesNames[e.dataIndex]),
 					}   
 		       ]
 	        });
 		}
         chart1 = echarts.init(document.getElementById(pieId));
         chart2 = echarts.init(document.getElementById(barId));
         initData();
         loadAll();
         chart1.on('mouseover', function (e) {
      	   click1(e);
      	});
     
}    

/**柱状图柱状图*/
function showEchartBarBarChar(pieId,barId,data){
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
		var xValue = data.xValue;           //x轴坐标值
		var seriesNames = data.seriesNames; //系列名
		var yValues = data.yValues;
		var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
		var creditsEnable = data.index == undefined ? false : true;
		var dataMap = data.childList;                            //联动图
		var childSeriesName = data.childSeriesName;              //联动图子图系列名称
		var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
		
		//组件
		var axisPointerType = data.axisPointerType;//坐标轴指示器
		var tooltipTrigger = data.tooltipTrigger; //触发类型
		var toolboxShow = data.toolboxShow;// 工具配置项
		var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
		var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
		var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
		var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
		var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
		var xAxisType = data.xAxisType;//x坐标轴类型
		var yAxisType = data.yAxisType;//y坐标轴类型
		var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
		var seriesType = data.seriesType; //类型 
		var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
		var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
		var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
		var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
		
		var colorList = [
		   			  '#C1232B','#333366','#27727B','#CC6633','#99CC99',
		   			   '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
		   			   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
		   			   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
		   			];
		option1 = {
			 title:{
				    text:title,
			        left:"center",
			    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		    	show:false,
		    	data:[],
		    	bottom:'3',
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '10%',
		        containLabel: true
		    },
		    toolbox: {
		    	show : toolboxShow,
		        feature: {
		            dataView: {readOnly: true},
		            magicType: {
		            	show :true,
		            	type: ['line', 'bar']
		            	},
		            restore: {show :true},
		            saveAsImage: {show :true}
		        }
		    },
		    xAxis : [
		        {   axisLabel: {
		        	show:true,
		        	interval: 0,
		        	},
		            type : 'category',
		            data:seriesNames
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
	                axisLabel: {
				            formatter: yAxisAxisLabelFormatter
				        }
		        }
		    ],
		    series :[
					{   
						name:childSeriesName,
					    type:'bar',
					    barWidth : barWidth,
					    itemStyle: {
								normal: {
									 color: function(params) {
	    								return colorList[params.dataIndex]
	    							},
								}
	 					      },
					    data:[],
					}   
		       ]
		}
			option2 = {
						 title:{
							    text:'',
						        left:"center",
						    },
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    legend: {
					    	show:false,
					    	data:[],
					    	bottom:'3',
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '10%',
					        containLabel: true
					    },
					    toolbox: {
					    	show : toolboxShow,
					        feature: {
					            dataView: {readOnly: true},
					            magicType: {
					            	show :true,
					            	type: ['line', 'bar']
					            	},
					            restore: {show :true},
					            saveAsImage: {show :true}
					        }
					    },
					    xAxis : [
					        {   axisLabel: {
					        	show:true,
					        	interval: 0,
					        	},
					            type : 'category',
					            data:[]
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value',
				                axisLabel: {
							            formatter: yAxisAxisLabelFormatter
							        }
					        }
					    ],
					    series :[
								{   
									name:childSeriesName,
								    type:'bar',
								    barWidth : barWidth,
								    data:[],
								}   
					       ]
					}

  var h1 = new Array();
  function initData(){
	
		for(var i=0; i<dataMap.length;i++){
			var h2 = new HashMap();
			h2.put(dataMap[i].name,dataMap[i].value);	
			h1.push(h2);
		}
		
	}
 
  function loadAll(){
		chart2.clear();
	    var option1Arr = new Array();
 		for(var i=0;i<seriesNames.length;i++){
			var value = sumArr(h1[i].get(seriesNames[i]));
			option1Arr.push(value);
		    
		}
 		option1.series[0].data = option1Arr;  
 		chart1.setOption(option1);
 		
		  option2.xAxis[0].data = xValue;
		  var option2Arr = new Array();
		  for(var i=0; i<xValue.length; i++){
			option2Arr.push(h1[0].get(seriesNames[0])[i]);
		  }
	      option2.series[0].data = option2Arr; 
	      option2.title.text = seriesNames[0]; 
	      chart2.setOption(option2);	
  }
  
  function sumArr(arr){
		var sum=0;
		for(var i=0; i<arr.length;i++){
			sum+=arr[i];
		}
		return sum;
	}
  
  function click1(e){
	   chart2.setOption({
			title:{
			    text:seriesNames[e.dataIndex],
		        left:"center",
		    },
		    series :[
					{
						name:childSeriesName,
					    type:'bar',
					    itemStyle: {
							normal: {
								color: colorList[e.dataIndex]
							}
					      },
					    barWidth : barWidth,
					    data:h1[e.dataIndex].get(seriesNames[e.dataIndex]),
					}   
		       ]
	        });
		}
      chart1 = echarts.init(document.getElementById(pieId));
      chart2 = echarts.init(document.getElementById(barId));
      initData();
      loadAll();
      chart1.on('mouseover', function (e) {
   	   click1(e);
   	});
  
}  

/**折叠图*/
function showEChartStack(id, data){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	//蓝色，灰色，红色
	var colorList = [
		   			  '#19B955','#C23531','#02467D','#CC6633','#99CC99'
		   			];
	var itemStyle;
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		itemStyle={
				normal: {
					 color: colorList[i]
				}
		      };
		
		var obj = new Object();
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.data = yValues[i];
		obj.stack = stacks[i];
		obj.yAxisIndex = yAxisIndex[i];
		if(seriesType[i] == 'bar' ){
			var lobj = new Object();
			lobj.show ='true';
			lobj.position ='inside';
			var nobj = new Object();
			nobj.normal = lobj;
			obj.label = nobj;
			
			obj.itemStyle =  itemStyle;
		}
		if(seriesType[i] == 'line' ){
			var lobj = new Object();
			lobj.show ='true';
			lobj.position ='top';
			var nobj = new Object();
			nobj.normal = lobj;
			obj.label = nobj;
			obj.itemStyle =  itemStyle;
		}
		
		array.push(obj);
	}
	var dataArr = array;
	
	//纵向柱状图
   if(chartType == "stackBar") { 
		option = {
				 title:{
				        text:title,
				        left:"center",
				    },
			    tooltip : {
			        trigger: 'axis',
			        formatter: function(params) {
			            return params[0].name + '<br/>'
			                   + params[0].seriesName + ' : ' + params[0].value + ' ('+yLeftUnit+')<br/>'
			                   + params[1].seriesName + ' : ' + params[1].value + ' ('+yLeftUnit+')<br/>'
			                   + params[2].seriesName + ' : ' + params[2].value + ' ('+yRightUnit+')';
			        },
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			    	bottom:'3',
			        data:seriesNames
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '13%',
			        containLabel: true
			    },
			    toolbox: {
			    	show : toolboxShow,
			        feature: {
			            dataView: {readOnly: toolboxFeatureDataViewShow},
			            magicType: {
			            	show :toolboxFeatureMagicTypeShow,
			            	type: toolboxFeatureMagicTypeType
			            	},
			            restore: {show :toolboxFeatureRestoreShow},
			            saveAsImage: {show :toolboxFeatureSaveAsImageShow}
			        }
			    },
			    xAxis : [
			        {   axisLabel: {
			        	show:true,
			        	interval: 0,
			        	rotate: 30,
			        	},
			            type : 'category',
			            data : xValue
			        }
			    ],
			    yAxis :yaxiss,
			    series :dataArr
			}
	}
	echart = echarts.init(document.getElementById(id));
	echart.setOption(option);		
}
/**
 * 柱状图和饼图联动
 */
function showEchartBarPieChar(pieId,barId,data){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var subtext = data.subtext;                             //图表副标题
	
	var colorList = [
	   			  '#C1232B','#333366','#27727B','#CC6633','#99CC99',
	   			   '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
	   			   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
	   			   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
	   			];
	option1 = {
			 title:{
				    text:title,
				    subtext:subtext,
			        left:"center",
			    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		    	show:false,
		    	data:seriesNames,
		    	bottom:'3',
		    },
		    grid: {
		        left: '6%',
		        right: '4%',
		        bottom: '22%',
		        containLabel: true
		    },
		    toolbox: {
		    	show : toolboxShow,
		        feature: {
		            dataView: {readOnly: true},
		            magicType: {
		            	show :true,
		            	type: ['line', 'bar']
		            	},
		            restore: {show :true},
		            saveAsImage: {show :true}
		        }
		    },
		    xAxis : [
		        {  axisLabel: {
		        	show:true,
		        	interval: 0,
		        	rotate: 30,
		        	},
		            type : 'category',
		            data:seriesNames
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
	                axisLabel: {
				            formatter: yAxisAxisLabelFormatter
				        }
		        }
		    ],
		    series :[
					{   
						name:childSeriesName,
					    type:'bar',
					    barWidth : barWidth,
					    itemStyle : {
    		                normal : {
    		                    color: function(params) {
    								return colorList[params.dataIndex]
    							},
    		                }
    		                },
    		            label :{
    		            	 normal : {
    		            		 show:true,
    		            		 position:'top'
    		            	 }
    		            },   
					    data:[],
					}   
		       ]
		}
		 option2 = {
				    title: {
				        text: '',
				        left:'center'
				    },
				    tooltip: {
				    	trigger: 'item',
				    	formatter: "{b} : {c} ({d}%)"
				    },
				    legend: {
				    	show:true,
				    	orient: 'vertical',
					    left:'30',
					    data:xValue
				    },
				    toolbox: {
				    	show : toolboxShow,
				        feature: {
				            dataView: {readOnly: true},
				            magicType: {
				            	show :false,
				            	},
				            restore: {show :true},
				            saveAsImage: {show :true}
				        }
				    },
				    calculable : true,
				    series : [
			    		        {
			    		            name:'',
			    		            type:'pie',
			    		            radius : '60%',
			    					selectedMode:'single',
			    		            center: ['50%', '50%'],
			    					itemStyle : {
			    		                normal : {
			    		                    label : {
			    		                        show : true
			    		                    },
			    		                    labelLine : {
			    		                        show : true
			    		                    },
			    		                },
			    		                emphasis : {
			    		                    label : {
			    		                        show : false
			    		                    },
			    		                    labelLine : {
			    		                        show : false
			    		                    }
			    		                }
			    		            },
			    		            label :{
			    		            	 normal : {
			    		            		 show:true,
			    		            		 formatter: '{b}: {c}'
			    		            	 }
			    		            }, 
			    		            data:[ ]
			    		        }
			    		    ]
				};
		

var h1 = new Array();
function initData(){

	for(var i=0; i<dataMap.length;i++){
		var h2 = new HashMap();
		h2.put(dataMap[i].name,dataMap[i].value);	
		h1.push(h2);
	}
	
}

function loadAll(){
	chart2.clear();
    var option1Arr = new Array();
		for(var i=0;i<seriesNames.length;i++){
		var value = sumArr(h1[i].get(seriesNames[i]));
		option1Arr.push(value);
	}
		option1.series[0].data = option1Arr;  
		chart1.setOption(option1);
		
	  var option2Arr = new Array();
	  for(var i=0; i<xValue.length; i++){
		  var obj = new Object();
		  obj.name = xValue[i];
		  obj.value = h1[0].get(seriesNames[0])[i];
		  option2Arr.push(obj);
	  }
      option2.series[0].data = option2Arr; 
      option2.title.text = seriesNames[0]; 
      chart2.setOption(option2);	
}

function sumArr(arr){
	var sum=0;
	for(var i=0; i<arr.length;i++){
		sum+=arr[i];
	}
	return sum;
}

function click1(e){
  var dataList = new Array();
  for(var i=0; i<xValue.length; i++){
	  var obj = new Object();
	  obj.name = xValue[i];
	  obj.value = h1[e.dataIndex].get(seriesNames[e.dataIndex])[i];
	  dataList.push(obj);
  }
   chart2.setOption({
		title:{
		    text:seriesNames[e.dataIndex],
	        left:"center",
	    },
	    series :[
				{
					name:childSeriesName,
				    type:'pie',
				    data:dataList,
				}   
	       ]
        });
	}
  chart1 = echarts.init(document.getElementById(pieId));
  chart2 = echarts.init(document.getElementById(barId));
  initData();
  loadAll();
  chart1.on('mouseover', function (e) {
	   click1(e);
	});
}  

function columnlinechar(id, data,hid){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	var colorList = data.colors;                             //颜色
	
	var itemStyle;
	var label ={
     	 normal : {
     		 show:true,
     		 position:'top'
     	 }
     };   
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		var obj = new Object();
		if(colorList !='' && colorList!=null){
			itemStyle={
					normal: {
						 color: colorList[i]
					}
			      };
			obj.itemStyle = itemStyle;
		}
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.barWidth=barWidth;
		obj.data = yValues[i];
		obj.label = label;
		array.push(obj);
	}
	var dataArr = array ;
	option = {
			title: {
		        text: title,
		        x: 'center'
		    },
		    tooltip: {
		        trigger: 'axis',
		        formatter: function(params) {
		        	return params[0].name + '<br/>'
	                   + params[0].seriesName + ' : ' + params[0].value + '<br/>'
	                   + params[1].seriesName + ' : ' + params[1].value + ' ('+yLeftUnit+')';
		        },
		    },
		    toolbox: {
		        feature: {
		            dataView: {show: true, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar']},
		            restore: {show: true},
		            saveAsImage: {show: true}
		        }
		    },
		    legend: {
		    	data:seriesNames,
		    	bottom :20
		    },
		    grid: { left: '6%', right: '3%', bottom: '18%',top: '20%',  containLabel: true },
		    xAxis: [
		        {
		            type: 'category',
		            axisLabel: { show:true, interval: 0, rotate: 30 , textStyle: {fontWeight: 'lighter', fontSize: 8}},
		            data:xValue
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '不良数',
		            axisLabel: {
		                formatter: '{value}个'
		            }
		        },
		        {
		            type: 'value',
		            name: '不良率',
		            axisLabel: {
		                formatter: '{value} %'
		            }
		        }
		    ],
		    series:dataArr
	}
	option.series[1].yAxisIndex=1;
	echart = echarts.init(document.getElementById(id));
	echart.setOption(option);
	echart.on('click', function (params) {
		if(params.seriesIndex==0){
			if(hid=='1'){//生产退次表查询
				var xv1=xValue[params.dataIndex].trim();
				$("#bad_Reason","#assembly").val(xv1);
				//arrange();
			}
		}
	});
}

function columnline(id, data,bs){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	var colorList = data.colors;                             //颜色
	
	var itemStyle;
	var label ={
     	 normal : {
     		 show:true,
     		 position:'top'
     	 }
     };   
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		var obj = new Object();
		if(colorList !='' && colorList!=null){
			itemStyle={
					normal: {
						 color: colorList[i]
					}
			      };
			obj.itemStyle = itemStyle;
		}
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.barWidth=barWidth;
		obj.data = yValues[i];
		obj.label = label;
		array.push(obj);
	}
	var dataArr = array ;
	
	option = {
		    title: {
		        text: title,
		        left:'center'
		    },
		    tooltip: {
		        trigger: 'axis',
		        formatter: function(params) {
		            return params[0].name + '<br/>'
		                   + params[0].seriesName + ' : ' + params[0].value + '<br/>'
		                   + params[1].seriesName + ' : ' + params[1].value + '<br/>'
		                   + params[2].seriesName + ' : ' + params[2].value 
		        },
		    },
		    legend: {
		    	bottom:'3',
		        data:seriesNames
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '10%',
		        containLabel: true
		    },
		    toolbox: {
		    	show : toolboxShow,
		        feature: {
		            dataView: {readOnly: toolboxFeatureDataViewShow},
		            magicType: {
		            	show :toolboxFeatureMagicTypeShow,
		            	type: toolboxFeatureMagicTypeType
		            	},
		            restore: {show :toolboxFeatureRestoreShow},
		            saveAsImage: {show :toolboxFeatureSaveAsImageShow}
		        }
		    },
		    xAxis: {
		        type: 'category',
		        axisLabel: {
		        	show:true,
		        	interval: 0,
		        	},
		        boundaryGap: false,
		        data: xValue
		    },
		    yAxis: {
		        type: 'value',
		        axisLabel: {
		            formatter: yAxisAxisLabelFormatter
		        }
		    },
		    series : dataArr
		}

	var chart = echarts.init(document.getElementById(id));
	chart.setOption(option);	
	chart.on('click', function (params) {
		if(params.seriesIndex==0){//获取series的序号
			//var index=params.value;//y轴值
			//var x=xValue[params.dataIndex];//x轴值
			var x = xValue[params.dataIndex];
			var date=x+"-25";//大
			var date2=datetime(x);//小
			$("#startTime").val(date2);
			$("#endTime").val(date);
			if(bs=="zz"){
				$("#assembly").submit();
			}if(bs=="pt"){//喷涂退次
				$("#paintingProductReturn").submit();
			}if(bs=="cy"){
				$("#stampingDaliy").submit();
			}if(bs=="pty"){//喷涂一次通过率
				$("#paintingDaily").submit();
			}
		}
	});
}

function datetime(time){
	var strs= new Array(); //定义一数组
	strs=time.split("-"); //字符分割 
	var y=parseInt(strs[0]);
	var m=parseInt(strs[1]);
	if(1<m && m<=12){
		if(m<=9){
			m=m-1;
			m="0"+m;
		}else{
			m=m-1;
		}
	}if(m==1){
		y=y-1;
		m=12;
	}
	var y2=y+"-"+m+"-"+"26"
	return y2;
}


function showEChartTwo(id, data){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	var colorList = data.colors;                             //颜色
	
	var itemStyle;
	var label ={
     	 normal : {
     		 show:true,
     		 position:'top'
     	 }
     };   
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		var obj = new Object();
		if(colorList !='' && colorList!=null){
			itemStyle={
					normal: {
						 color: colorList[i]
					}
			      };
			obj.itemStyle = itemStyle;
		}
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.data = yValues[i];
		obj.label = label;
		array.push(obj);
	}
	var dataArr = array ;

	/**
	 * 折线图
	 */
	
	if (chartType == "lines") {
		option = {
			    title: {
			        text: title
			    },
			    tooltip: {
			    	 trigger: 'axis'
			    },
			    legend: {
			        data:seriesNames
			    },
			    toolbox: {
			        show: true,
			        feature: {
			            dataZoom: {
			                yAxisIndex: 'none'
			            },
			            dataView: {readOnly: false},
			            magicType: {type: ['line', 'line']},
			            restore: {},
			            saveAsImage: {}
			        }
			    },
			    xAxis:  {
			        type: 'category',
			        boundaryGap: false,
			        data: xValue
			    },
			    yAxis: {
			        type: 'value',
			        axisLabel: {
			            formatter: '{value}'
			        }
			    },
			    series: dataArr
			};

	}

	echart = echarts.init(document.getElementById(id));
	echart.setOption(option);		
}
/**
 * showlinecolumn 新老版本
 * showlinecolumn2 老版本
 * showlinecolumn3 老版本
 */
function showlinecolumn(id,data,hid,charid){//id:图放置id，data：图形数据，hid：图顺序，charid：页面特殊处理(1为onlineHomeChar页面)
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	var colorList = data.colors;                             //颜色
	var Values = data.value;                             //隐藏值
	var itemStyle;
	var online = data.online;                            //在线对象
	var onlineList = data.onlineList;                    //在线list
	var numberMap = data.numberMap;
	var label ={
     	 normal : {
     		 show:true,
     		 position:'inside',
     		 /*textStyle :{
     			color:'#FF8888'
     		 }*/
     	 }
     };   
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		var obj = new Object();
		if(colorList !='' && colorList!=null){
			itemStyle={
					normal: {
						 color: colorList[i]
					}
			      };
			obj.itemStyle = itemStyle;
		}
		obj.yAxisIndex=i;
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.data = yValues[i];
		if(i>0){
			obj.label ={
			     	 normal : {
			     		 show:true,
			     		 position:'top',
			     		 formatter: '{c}%'
			     	 }
			     };   
		}else{
			obj.label = label;
		}
		obj.barWidth=barWidth;
		array.push(obj);
	}
	var dataArr = array ;
	
	option = {
		title: {
			text: title,
			left:'center'
		},
	    tooltip: {
	        trigger: 'axis'
	    },
	    toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            restore: {show: true},
	            saveAsImage: {show: false}
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '20%',
	        top: '25%',
	        containLabel: true
	    },
	    legend: {
	        data:seriesNames,
//	        bottom :3
	        align: 'left',
	        left: 20, 
	        top: '20',
	    },
	    xAxis: [{
	            type: 'category',
	            data: xValue,
	            interval: 0,
	            axisLabel:{
	            	show:true,
		        	interval: 0,
	            	rotate: 30
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '数',
	            axisLabel: {
	                formatter: '{value}'
	            },
	            splitLine:{  
	            　　　　   show:false  
	            　　   }
	        },
	        {
	            type: 'value',
	            name: '率',
	            axisLabel: {
	                formatter: '{value}%'
	            },
	            splitLine:{  
	            　　　　   show:false  
	            　　   }
	        }
	    ],
	    series: dataArr
	};
	//自定义toolTip
	var toolTip_n = e_createTooltip(null,hid,numberMap);
	option.tooltip = toolTip_n;
	//自定义下载按钮
	var myTool = e_createTool(online,hid,onlineList);
	option.toolbox.feature.myTool=myTool;
	var chart = echarts.init(document.getElementById(id));
	chart.setOption(option);
	//alert(JSON.stringify(option))
	chart.on('click', function (params) {
		if(params.seriesIndex==0){
			$("#strone",navTab.getCurrentPanel()).val("");
			$("#strtwo",navTab.getCurrentPanel()).val("");
			$("#spareparts",navTab.getCurrentPanel()).val("");
			$("#badphenomenon",navTab.getCurrentPanel()).val("");
			if(charid==1){
				if(hid=="1"){
					var xv1=Values[params.dataIndex].trim();
					var xv2=xValue[params.dataIndex].trim();
					$("#strone",navTab.getCurrentPanel()).val(xv1);
					$("#strtwo",navTab.getCurrentPanel()).val(xv2);
					$("#hiddenId",navTab.getCurrentPanel()).val("1");//用于查明细时特殊处理
					$("#batchshow",navTab.getCurrentPanel()).click();
				}
				if(hid=="2"){
					var xv1=Values[params.dataIndex].trim();
					var xv2=xValue[params.dataIndex].trim();
					$("#spareparts",navTab.getCurrentPanel()).val(xv2);
					$("#spareparts2",navTab.getCurrentPanel()).val(xv1);
					$("#batchshow",navTab.getCurrentPanel()).click();
				}
				if(hid=="3"){
					var v=xValue[params.dataIndex].trim();
					$("#badphenomenon",navTab.getCurrentPanel()).val(v);
					$("#batchshow",navTab.getCurrentPanel()).click();
				}
				if(hid=="4"){
					var v=xValue[params.dataIndex].trim();
					$("#trendTime",navTab.getCurrentPanel()).val(v);
					$("#batchshow",navTab.getCurrentPanel()).click();
				}
				if(hid=="5"){
					$("#hiddenId",navTab.getCurrentPanel()).val("5");//用于查明细时特殊处理
					var v=xValue[params.dataIndex].trim();
					$("#trendTime",navTab.getCurrentPanel()).val(v);
					$("#batchshow",navTab.getCurrentPanel()).click();
				}
			}
		}
	});
}
function showlinecolumn2(id,data,hid){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	var colorList = data.colors;                             //颜色
	var Values = data.value;                             //隐藏值
	var itemStyle;
	var label ={
     	 normal : {
     		 show:true,
     		 position:'top'
     	 }
     };   
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		var obj = new Object();
		if(colorList !='' && colorList!=null){
			itemStyle={
					normal: {
						 color: colorList[i]
					}
			      };
			obj.itemStyle = itemStyle;
		}
		obj.yAxisIndex=i;
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.data = yValues[i];
		obj.label = label;
		obj.barWidth=barWidth;
		array.push(obj);
	}
	var dataArr = array ;
	option = {
		title: {
			text: title,
			left:'center'
		},
	    tooltip: {
	        trigger: 'axis'
	    },
	    toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            //magicType: {show: true, type: ['bar', 'line','line','line']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '20%',
	        top:'25%',
	        containLabel: true
	    },
	    legend: {
	        data:seriesNames,
//	        bottom :3
	        align: 'left',
	        left: 20, 
	        top: '20',
	    },
	    xAxis: [{
	            type: 'category',
	            data: xValue,
	            axisLabel:{
	            	show:true,
		        	interval: 0,
	            	rotate: 30
	            }
	        }
	    ],
	    yAxis: [
	            {
	                type: 'value',
	                name: '不良数',
	                axisLabel: {
	                    formatter: '{value}'
	                },
		            splitLine:{  
		            　　　　   show:false  
		            　　   }
	            },
	            {
	                type: 'value',
	                name: '不良率', 
	                axisLabel: {
	                    formatter: '{value}%'
	                },
		            splitLine:{  
		            　　　　   show:false  
		            　　   }
	            }
	    ],
	    series: dataArr
	};
	var chart = echarts.init(document.getElementById(id));
	chart.setOption(option);
	chart.on('click', function (params) {
		if(params.seriesIndex==0){
			if(hid=='6' || hid=='11'){
				var xv1=Values[params.dataIndex].trim();
				var xv2=xValue[params.dataIndex].trim();
				$("#strone","#onlinechardata").val(xv1);
				$("#strtwo","#onlinechardata").val(xv2);
				$("#onlineERP","#onlinechardata").click();
			}
			if(hid=='7' || hid=='12'){
				var xv1=Values[params.dataIndex].trim();
				var xv2=xValue[params.dataIndex].trim();
				$("#spareparts","#onlinechardata").val(xv2);
				$("#spareparts2","#onlinechardata").val(xv1);
				$("#onlineERP","#onlinechardata").click();
			}			
			if(hid=='8' || hid=='13'){
				var v=xValue[params.dataIndex].trim();
				$("#badphenomenon","#onlinechardata").val(v);
				$("#onlinenum","#onlinechardata").click();
			}
//			if(hid=='9'|| hid=='14'){
//				var v=xValue[params.dataIndex].trim();
//				$("#trendTime","#onlinechardata").val(v);
//				$("#onlineERP","#onlinechardata").click();
//			}if(hid=='10' || hid=='15'){
//				var v=xValue[params.dataIndex].trim();
//				$("#trendTime","#onlinechardata").val(v);
//				$("#onlineERP","#onlinechardata").click();
//			}
		}
	});
}

function showlinecolumn3(id,data,hid){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	var colorList = data.colors;                             //颜色
	var Values = data.value;                             //隐藏值
	var itemStyle;
	var label ={
     	 normal : {
     		 show:true,
     		 position:'top'
     	 }
     };   
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		var obj = new Object();
		if(colorList !='' && colorList!=null){
			itemStyle={
					normal: {
						 color: colorList[i]
					}
			      };
			obj.itemStyle = itemStyle;
		}
		obj.yAxisIndex=i;
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.data = yValues[i];
		obj.label = label;
		obj.barWidth=barWidth;
		array.push(obj);
	}
	var dataArr = array ;
	option = {
			title: {
				text: title,
				left:'center'
			},
		    tooltip: {
		        trigger: 'axis'
		    },
		    grid: {
		        left: '8%',
		        right: '4%',
		        bottom: '10%',
		        top:'25%',
		        containLabel: true
		    },
		    toolbox: {
		        feature: {
		            dataView: {show: true, readOnly: false},
		            //magicType: {show: true, type: ['bar', 'line','line','line']},
		            restore: {show: true},
		            saveAsImage: {show: true}
		        }
		    },
		    legend: {
		        data:seriesNames,
//		        bottom :3
		        align: 'left',
		        left: 20, 
		        top: '20',
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: xValue,
		            axisLabel:{
		            	show:true,
			        	interval: 0,
		            	rotate: 30
		            }
		        }
		    ],
		    yAxis: [
		        {
		        	type: 'value',
	                name: '不良数',
	                position: 'left',
	                axisLabel: {
	                    formatter: '{value}'
	                },
		            splitLine:{  
		            　　　　   show:false  
		            　　   }
		        },
		        {
	                type: 'value',
	                name: '不良率', 
	                position: 'right',
	                axisLabel: {
	                    formatter: '{value}%'
	                },
		            splitLine:{  
		            　　　　   show:false  
		            　　   }
	            },
	            {
	                type: 'value',
	                name: '上控制线', 
	                position: 'left',
	                offset: 90,
	                axisLabel: {
	                    formatter: '{value}'
	                },
		            splitLine:{  
		            　　　　   show:false  
		            　　   }
	            },
	            {
	                type: 'value',
	                name: '下控制线', 
	                position: 'right',
	                offset: 65,
	                axisLabel: {
	                    formatter: '{value}'
	                },
		            splitLine:{  
		            　　　　   show:false  
		            　　   }
	            },
		    ],
		    series: dataArr
		};
	var chart = echarts.init(document.getElementById(id));
	chart.setOption(option);
	chart.on('click', function (params) {
		if(params.seriesIndex==0){
			if(hid=='9'|| hid=='14'){
				var v=xValue[params.dataIndex].trim();
				$("#trendTime","#onlinechardata").val(v);
				$("#onlineERP","#onlinechardata").click();
			}if(hid=='10' || hid=='15'){
				var v=xValue[params.dataIndex].trim();
				$("#trendTime","#onlinechardata").val(v);
				$("#onlineERP","#onlinechardata").click();
			}
		}
	});
}

/**
 * showlinecolumn4是showlinecolumn2的新版本
 */
function showlinecolumn4(id,data,hid){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	var colorList = data.colors;                             //颜色
	var Values = data.value;                             //隐藏值
	var codeMap = data.map;                              
	var online = data.online;                            //在线对象
	var onlineList = data.onlineList;                    //在线list
	var numberMap = data.numberMap;
	var itemStyle;
	var label ={
     	 normal : {
     		 show:true,
     		 position:'inside'
     	 }
     };   
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		var obj = new Object();
		if(colorList !='' && colorList!=null){
			itemStyle={
					normal: {
						 color: colorList[i]
					}
			      };
			obj.itemStyle = itemStyle;
		}
		obj.yAxisIndex=yAxisIndex[i];
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.data = yValues[i];
		obj.label = label;
		obj.barWidth=barWidth;
		if(i!=0){
			obj.label={
					normal : {
			     		 show:true,
			     		 position:'top',
			     		 formatter: '{c}%'
			     	 }
			}
		}
		array.push(obj);
	}
	var dataArr = array ;
	option = {
		title: {
			text: title,
			left:'center'
		},
	    tooltip: {
	        trigger: 'axis'
	    },
	    toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            //magicType: {show: true, type: ['bar', 'line','line','line']},
	            restore: {show: true},
	            saveAsImage: {show: false}
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '25%',
	        top : '25%',
	        containLabel: true
	    },
	    legend: {
	        data:seriesNames,
//	        bottom :3,
	        align: 'left',
	        left: 20, 
	        top: '20',
	        selected: {
	            '不良率' : false
	        }
	    },
	    xAxis: [{
	            type: 'category',
	            data: xValue,
	            axisLabel:{
	            	show:true,
		        	interval: 0,
	            	rotate: 30
	            },
	            splitLine:{  
	            　　　　   show:false  
	            　　   }
	        }
	    ],
	    yAxis: [
	            {
	                type: 'value',
	                name: '数',
	                axisLabel: {
	                    formatter: '{value}'
	                },
		            splitLine:{  
		            　　　　   show:false  
		            　　   }
	            },
	            {
	                type: 'value',
	                name: '率', 
	                axisLabel: {
	                    formatter: '{value}%'
	                },
		            splitLine:{  
		            　　　　   show:false  
		            　　   }
	            }
	    ],
	    series: dataArr
	};
	//自定义toolTip
	var toolTip_n = e_createTooltip(codeMap,hid,numberMap);
	option.tooltip = toolTip_n;
	//自定义下载按钮
	var myTool = e_createTool(online,hid,onlineList);
	option.toolbox.feature.myTool=myTool;
	var chart = echarts.init(document.getElementById(id));
	chart.setOption(option);
	chart.on('click', function (params) {
		if(params.seriesIndex==0){
			if(hid=='6'){
				var xv1=Values[params.dataIndex].trim();
				var xv2=xValue[params.dataIndex].trim();
				$("#strone",navTab.getCurrentPanel()).val(xv1);
				$("#strtwo",navTab.getCurrentPanel()).val(xv2);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlinenum",navTab.getCurrentPanel()).click();
			}
			if(hid=='7'){
				var xv1=Values[params.dataIndex].trim();
				var xv2=xValue[params.dataIndex].trim();
				$("#spareparts",navTab.getCurrentPanel()).val(xv2);
				$("#spareparts2",navTab.getCurrentPanel()).val(xv1);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlinenum",navTab.getCurrentPanel()).click();
			}			
			if(hid=='8'){
				var v=xValue[params.dataIndex].trim();
				$("#badphenomenon",navTab.getCurrentPanel()).val(v);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlinenum",navTab.getCurrentPanel()).click();
			}
			if(hid=='11'){
				var xv1=Values[params.dataIndex].trim();
				var xv2=xValue[params.dataIndex].trim();
				$("#strone",navTab.getCurrentPanel()).val(xv1);
				$("#strtwo",navTab.getCurrentPanel()).val(xv2);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlineERP",navTab.getCurrentPanel()).click();
			}
			if(hid=='12'){
				var xv1=Values[params.dataIndex].trim();
				var xv2=xValue[params.dataIndex].trim();
				$("#spareparts",navTab.getCurrentPanel()).val(xv2);
				$("#spareparts2",navTab.getCurrentPanel()).val(xv1);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlineERP",navTab.getCurrentPanel()).click();
			}	
			if(hid=='13'){
				var v=xValue[params.dataIndex].trim();
				$("#badphenomenon",navTab.getCurrentPanel()).val(v);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlineMES",navTab.getCurrentPanel()).click();
			}
		}
	});
}
/**
 * showlinecolumn5是showlinecolumn3的新版本
 */
function showlinecolumn5(id,data,hid){
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
	var xValue = data.xValue;           //x轴坐标值
	var seriesNames = data.seriesNames; //系列名
	var yValues = data.yValues;
	var funcName = data.funcName == undefined ? 'noFuncName' : data.funcName;;
	var creditsEnable = data.index == undefined ? false : true;
	
	//组件
	var axisPointerType = data.axisPointerType;//坐标轴指示器
	var tooltipTrigger = data.tooltipTrigger; //触发类型
	var toolboxShow = data.toolboxShow;// 工具配置项
	var toolboxFeatureSaveAsImageShow = data.toolboxFeatureSaveAsImageShow;//工具配置项-保存为图片
	var toolboxFeatureRestoreShow = data.toolboxFeatureRestoreShow; //工具配置项-配置项还原
	var toolboxFeatureDataViewShow = data.toolboxFeatureDataViewShow;//工具配置项-数据视图工具
	var toolboxFeatureMagicTypeShow = data.toolboxFeatureMagicTypeShow;//工具配置项-动态类型切换
	var toolboxFeatureMagicTypeType = data.toolboxFeatureMagicTypeType;//工具配置项-动态类型切换 
	var xAxisType = data.xAxisType;//x坐标轴类型
	var yAxisType = data.yAxisType;//y坐标轴类型
	var yAxisAxisLabelFormatter = data.yAxisAxisLabelFormatter;//刻度标签的内容格式器
	var seriesType = data.seriesType; //类型 
	var seriesMarkPointDataType = data.seriesMarkPointDataType;//图表标注。
	var seriesMarkPointDataName = data.seriesMarkPointDataName;//图表标注名称
	var seriesMarkLineDataType = data.seriesMarkLineDataType;//图表标线。
	var seriesMarkLineDataName = data.seriesMarkLineDataName;//图表标线名称
	var dataMap = data.childList;                            //联动图
	var childSeriesName = data.childSeriesName;              //联动图子图系列名称
	var barWidth = data.barWidth;                            //联动柱状图柱子的宽度
	var stacks = data.stack;                                 //数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	var yaxiss = data.yAxiss;                                //y轴设置
	var yAxisIndex = data.yAxisIndex;                        //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用。
	var colorList = data.colors;                             //颜色
	var Values = data.value;                             //隐藏值
	var codeMap = data.map;
	var online = data.online;                            //在线对象
	var onlineList = data.onlineList;                    //在线list
	var itemStyle;
	var label ={
     	 normal : {
     		 show:true,
     		 position:'inside',
     		 textStyle :{
     			color:'#FF8888'
     		 }
     	 }
     };   
	
	var array = new Array();
	for(var i=0;i<seriesNames.length;i++){
		var obj = new Object();
		if(colorList !='' && colorList!=null){
			itemStyle={
					normal: {
						 color: colorList[i]
					}
			      };
			obj.itemStyle = itemStyle;
		}
		obj.yAxisIndex=yAxisIndex[i];
		obj.name = seriesNames[i];
		obj.type = seriesType[i];
		obj.data = yValues[i];
		if(i==2 || i==3){
			obj.step = 'middle';
		}
		obj.label = label;
		obj.barWidth=barWidth;
		if(i!=0){
			obj.label={
					normal : {
			     		 show:true,
			     		 position:'top',
			     		 formatter: '{c}%'
			     	 }
			}
		}
		array.push(obj);
	}
	var dataArr = array ;
	option = {
			title: {
				text: title,
				left:'center'
			},
		    tooltip: {
		        trigger: 'axis'
		    },
		    grid: {
		        left: '8%',
		        right: '4%',
		        bottom: '20%',
		        top: '25%',
		        containLabel: true
		    },
		    toolbox: {
		        feature: {
		            dataView: {show: true, readOnly: false},
		            restore: {show: true},
		            saveAsImage: {show: false}
		        }
		    },
		    legend: {
		        data:seriesNames,
//		        bottom :3,
		        align: 'left',
		        left: 20, 
		        top: '20',
		        selected: {
		            '上控制线' : false,
		            '下控制线' : false
		        }
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: xValue,
		            axisLabel:{
		            	show:true,
			        	interval: 0,
		            	rotate: 30
		            },
		            splitLine:{  
		            　　　　   show:false  
		            　　   }
		        }
		    ],
		    yAxis: [
		        {
		        	type: 'value',
	                name: '不良数',
	                position: 'left',
	                axisLabel: {
	                    formatter: '{value}'
	                },
	                splitLine:{  
		            　　　　   show:false  
		            　　   }
		        },
		        {
	                type: 'value',
	                name: '不良率', 
	                position: 'right',
	                axisLabel: {
	                    formatter: '{value}%'
	                },
	                splitLine:{  
		            　　　　   show:false  
		            　　   }
	            },
	        /*    {
	                type: 'value',
	                name: '上下控制线', 
	                position: 'right',
	                offset: 80,
	                axisLabel: {
	                    formatter: '{value}'
	                },
	                splitLine:{  
		            　　　　   show:false  
		            　　   }
	            }*/
		    ],
		    series: dataArr
		};
	//自定义工具栏
	var toolTip_n = e_createTooltip(codeMap,hid,null);
	option.tooltip = toolTip_n;
	//自定义下载按钮
	var myTool = e_createTool(online,hid,onlineList);
	option.toolbox.feature.myTool=myTool;
	var chart = echarts.init(document.getElementById(id));
	chart.setOption(option);
	chart.on('click', function (params) {
		if(params.seriesIndex==0){
			if(hid=='9'){
				var v=xValue[params.dataIndex].trim();
				$("#trendTime",navTab.getCurrentPanel()).val(v);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlinenum",navTab.getCurrentPanel()).click();
			}if(hid=='10'){
				var v=xValue[params.dataIndex].trim();
				$("#trendTime",navTab.getCurrentPanel()).val(v);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlinenum",navTab.getCurrentPanel()).click();
			}
			if(hid=='14'){
				var v=xValue[params.dataIndex].trim();
				$("#trendTime",navTab.getCurrentPanel()).val(v);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlineERP",navTab.getCurrentPanel()).click();
			}if(hid=='15'){
				var v=xValue[params.dataIndex].trim();
				$("#trendTime",navTab.getCurrentPanel()).val(v);
				$("#distinction",navTab.getCurrentPanel()).val('1');//自动跳转标记
				$("#onlineERP",navTab.getCurrentPanel()).click();
			}
		}
	});
}

var e_createTooltip = function(opts,hid,code){
	   var tooltip_r = {
	    		trigger: 'axis',
	    		formatter: function(params) {
			           // console.warn(params[0].dataIndex+1);
			            var sname = params[0].name ;
			            var svalue ="";
			            var lab = "";
			            if(hid ==1 || hid ==2 || hid ==3 ||hid ==4||hid ==5){
			            	lab ="(批)";
			            }else{
			            	lab ="(个)";
			            }
			            for(var i in params){
			            	  if(i == 0){
			            		  var pvalue = params[i].value ;
			            		  if(pvalue == null){
			            			  pvalue = 0;
			            		  }
			            		  svalue += params[i].seriesName + ' : ' + pvalue + lab + '<br/>';
			            		  if(opts != null && opts[sname]  !=null){
			            			  if(sname!= '其他'){
		   			            		  svalue += "到货总数："+ opts[sname] + lab + '<br/>';
		   			            		 /* if(code != null && code[sname] != null ){
		   			            			sname = code[sname]+"-" + sname;
		   			            		  }*/
				            		  }
			            		  }
			            		  if(sname!= '其他' && code != null && code[sname] != null ){
		   			            			sname = code[sname]+"-" + sname;
			            		  }
			            	  }else{
			            		  var pvalue = params[i].value ;
			            		  if(pvalue == null){
			            			  pvalue = 0;
			            		  }
		                          svalue += params[i].seriesName + ' : ' + pvalue+'%' + '<br/>';
			            	  }
			            	}
			        
			            return sname +' <br/>' + svalue ;
			        },
	      };
	   
		return  $.extend(tooltip_r,opts,{});
}

//导出工具栏
var e_createTool = function(opts,hid,onList){
	 var myTool = {
			    selType : 1 ,
		        show : true,
		        title : '导出',
		        icon : 'M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
		        onclick : function (ecModel, api){
		     	        var model = this.model;
		     	        var title = model.get('name') || ecModel.get('title.0.text') || 'echarts';
		     	        var $a = document.createElement('a');
		     	        var _type = model.get('type', true) || 'png';
		     	        $a.download = title + '.' + _type;
		     	        $a.target = '_blank';
		     	        var url = api.getConnectedDataURL({
		     	            type: _type,
		     	            backgroundColor: model.get('backgroundColor', true)
		     	                || ecModel.get('backgroundColor') || '#fff',
		     	            excludeComponents: model.get('excludeComponents'),
		     	            pixelRatio: model.get('pixelRatio')
		     	        });
		     	        $a.href = url;
		     	        // Chrome and Firefox
		     	         if (typeof MouseEvent === 'function') {
		     	            var evt = new MouseEvent('click', {
		     	                view: window,
		     	                bubbles: true,
		     	                cancelable: false
		     	            });
		     	            $a.dispatchEvent(evt);
		     	        } 
		     	        // IE
		     	         else {
		     	            var lang = model.get('lang');
		     	            var html = ''
		     	                + '<body style="margin:0;">'
		     	                + '<img src="' + url + '" style="max-width:100%;" title="' + ((lang && lang[0]) || '') + '" />'
		     	                + '</body>';
		     	            var tab = window.open();
		     	            tab.document.write(html);
		     	        } 
//		     	    	var url = "<c:url value='quality/testInstance/dowmLoadFeedDetail.do' />";
		     	    	var form = document.createElement("form");
		     	    	form.action = "base/online/chartDownload.do";
		     	    	form.method = "post";
		     	    	form.target = '_blank';
		     	    	for(var i in opts){
    	     	    		var sinput = document.createElement("input");
    	     	    		sinput.name = i;
    	     	    		sinput.value = opts[i];
    	     	    		form.appendChild(sinput);
    	     	    	}
		     	    /*	for(var k in onList){
		     	    			for(var j in onList[k]){
		     	    				if(j!="onlineList" && onList[k][j] !=''){
		     	    					var sinput = document.createElement("input");
					     	    		sinput.name = "onlineList["+k+"]."+j;
					     	    		sinput.value = onList[k][j];
					     	    		console.warn(k+":"+sinput.name);
					     	    		form.appendChild(sinput);
		     	    				}
			     	    		}
		     	    	}*/
		     	    	
		     	    	var sinput = document.createElement("input");
	     	    		sinput.name = "items";
	     	    		sinput.value = "{ onlineList :"+JSON.stringify(onList)+"}";
	     	    		form.appendChild(sinput);
		     	    	form.submit();
		        } 
		    };
	 return  $.extend(myTool,opts,{});
}
 