<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
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
    
    $("#selectDate",navTab.getCurrentPanel()).change(function(){
    	var dateT= $("#dateTd",navTab.getCurrentPanel());
    	//alert(dateT.onclick);
    	dateT.empty();
    	var selectDate = $("#selectDate",navTab.getCurrentPanel()).val();
    	if(selectDate =='天'){
    		dateT.append("<input type='text' size='10' id='dateT' name='dateT' placeholder='请输入日期' onclick='laydate()' readonly='true'/>");
    	}else if(selectDate =='周'){
    		dateT.append("<input type='text'  size='10' id='dateT'  name='dateT' placeholder='请输入日期' onclick='laydate()' readonly='true'/>");
    	}else if(selectDate =='月'){
    		dateT.append("<input type='text'  size='10' id='dateT' name='dateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY-MM\"})' readonly='true'/>");
    	}else if(selectDate =='年'){
    		dateT.append("<input type='text'  size='10' id='dateT'  name='dateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY\"})' readonly='true'/>");
    	}
    });
    
    function loadTestInstanceChart(){
    	var productType = $("#productType",navTab.getCurrentPanel()).val();
    	var supplier = $("#supplier",navTab.getCurrentPanel()).val();
    	var partType = $("#supplierList",navTab.getCurrentPanel()).val();
    	var partName = $("#partName",navTab.getCurrentPanel()).val();
    	var dateType = $("#selectDate",navTab.getCurrentPanel()).val();
    	var dateT = $("#dateT",navTab.getCurrentPanel()).val();
    	var isNew = $("#isNew",navTab.getCurrentPanel()).val();
    	var lotName = $("#lotName",navTab.getCurrentPanel()).val();
    	
    	if(dateT=='' || dateT ==null){
    		alert("请选择时间维度！");
    		return;
    	}
    	var url = "<c:url value='quality/testInstance/lodeSupplierDefectRate.do' />";
    	$.post(url,{productType:productType,supplier:supplier,partType:partType,partName:partName,dateType:dateType,dateT:dateT,isNew:isNew,lotName:lotName},function(data){
    		if(data.result==1){
 //   			loadChartOne_one(data.resultMap);
    		}
    		if(data.result==-1){
    			alert("查询出错，请联系管理员！");
    		}
    	});
    }
</script>

	<form  onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar dropdownSearchBar">
		<table class="searchContent">
			<tr>
				<td>
					机型类别：
				</td>
				<td>
					<select id="productType" name="productType">
					   <option>请选择</option>
					   <c:forEach items="${proList }" var="o">
					      <option value="${o.productType }">${o.productType }</option>
					   </c:forEach>
					</select>
				</td>	
				<td>
					供应商：
				</td>
                <td>
                    <div id="supplierList" class="dropdownlist"></div>
                </td>  
                <td>
					物料类别：
				</td>
                <td>
                     <div id="partTypeList" class="dropdownlist"></div>
                </td> 
                 <td>
					物料名称：
				</td>
                <td>
                    <input type="text" name="partName" value="${param.partName}" />
                </td> 				
		 </tr>	
		 <tr>	
		        <td>
					时间维度：
				</td>
                <td >
                    <select id="selectDate" >
                       <option >请选择</option>
                       <option value="天">天</option>
                       <option value="周">周</option>
                       <option value="月">月</option>
                       <option value="年">年</option>
                     </select>
                     <span id="dateTd"></span>
                </td>
               
                <td>
					新品：
				</td>
                <td>
                    <input type="text" name="isNew" value="${param.isNew}" />
                </td>  
                 <td>
					供应商批次：
				</td>
                <td>
                    <input type="text" name="lotName" value="${param.lotName}" />
                </td>	
				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				<div class="buttonActive"><div class="buttonContent"><button type="button" id="resetBtn">重置</button></div></div>
				</td>	
			</tr>
		</table>
	</div>
	</form>

<div  class="pageContent"   style="height: 500px;overflow: auto; ">
	<div class="pageContent"   style="height: 350px;overflow: auto; ">
		<div id="chartOne_one" style="width: 95%;height: 300px;"></div>
	</div>
	<div class="pageContent"   style="height: 350px;overflow: auto; ">
	    <div style="width: 95%;height: 300px;">
	       <div id="chartTwo_one" style="width: 50%;float:left;height: 300px;"></div>
	   
	       <div id="chartTwo_two" style="width: 50%;float:left;height: 300px;"></div>
	    </div>
	</div>
	<div class="pageContent"   style="height: 350px;overflow: auto; ">
	    <div style="width: 95%;height: 300px;">
	       <div id="chartThree_one" style="width: 50%;float:left;height: 300px;"></div>
	   
	       <div id="chartThree_two" style="width: 50%;float:left;height: 300px;"></div>
	    </div>
	</div>
	<div class="pageContent"   style="height: 350px;overflow: auto; ">
	    <div style="width: 95%;height: 300px;">
	       <div id="chartFour_one" style="width: 50%;float:left;height: 300px;"></div>
	   
	       <div id="chartFour_two" style="width: 50%;float:left;height: 300px;"></div>
	    </div>
	</div>
</div>
 <script type="text/javascript">
 $(function(){
	 loadChartOne_one();
 })
 function loadChartOne_one(){
		var echart1,echart2,echart3,echart4,echart5,echart6,echart7;
		var option1,option2,option3,option4,option5,option6,option7;
		option1 = {
				title : {
			        text: '供应商进料不良批次（率）、不良数（率）排列图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    legend: {
			    	bottom:'3',
			        data:['不良批数','不良率']
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['xx供应商','xx供应商','xx供应商','xx供应商','xx供应商','xx供应商','xx供应商','xx供应商','xx供应商','xx供应商']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            name : '数量',
			            axisLabel : {
			                formatter: '{value} 次'
			            }
			        },
			        {
			            type : 'value',
			            name : '不良率',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
			    ],
			    series : [

			        {
			            name:'不良批数',
			            type:'bar',
			            data:[2.0, 4, 7.0, 23, 25, 76, 135, 162, 32, 20,]
			        },
			        
			        {
			            name:'不良率',
			            type:'line',
			            yAxisIndex: 1,
			            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5]
			        }
			    ]
			};
		
		option2 = {
				title : {
			        text: '零部件进料不良批次（率）、\n不良数（率）排列图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    legend: {
			    	bottom:'3',
			        data:['不良批数','不良率']
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['xx零部件','xx零部件','xx零部件','xx零部件','xx零部件','xx零部件','xx零部件','xx零部件','xx零部件','xx零部件']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            name : '数量',
			            axisLabel : {
			                formatter: '{value} 次'
			            }
			        },
			        {
			            type : 'value',
			            name : '不良率',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
			    ],
			    series : [

			        {
			            name:'不良批数',
			            type:'bar',
			            data:[2.0, 4, 7.0, 23, 25, 76, 135, 162, 32, 20,]
			        },
			        
			        {
			            name:'不良率',
			            type:'line',
			            yAxisIndex: 1,
			            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5]
			        }
			    ]
			};
		
		option3 = {
			    title : {
			        text: '进料不良现象批次、数饼图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data:['性能类不良','尺寸类不良','外观类不良','其他类不良']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {
			                show: true, 
			                type: ['pie', 'funnel'],
			                option: {
			                    funnel: {
			                        x: '25%',
			                        width: '50%',
			                        funnelAlign: 'left',
			                        max: 1548
			                    }
			                }
			            },
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    series : [
			        {
			            name:'不良现象',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[
			                {value:335, name:'性能类不良'},
			                {value:310, name:'尺寸类不良'},
			                {value:234, name:'外观类不良'},
			                {value:135, name:'其他类不良'}
			            ]
			        }
			    ]
			};
		
		option4 = {
				title : {
			        text: '供应商不良数（率）趋势排列及控制图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    legend: {
			    	bottom:'3',
			        data:['不良批数','不良率']
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['第1周','第2周','第3周','第4周','第5周','第6周','第7周']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            name : '数量',
			            axisLabel : {
			                formatter: '{value} 次'
			            }
			        },
			        {
			            type : 'value',
			            name : '不良率',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
			    ],
			    series : [

			        {
			            name:'不良批数',
			            type:'bar',
			            data:[2.0, 4, 7.0, 23, 25, 76, 135]
			        },
			        
			        {
			            name:'不良率',
			            type:'line',
			            yAxisIndex: 1,
			            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3]
			        }
			    ]
			};
		option5 = {
				title : {
			        text: '零部件不良数（率）趋势排列及控制图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    legend: {
			    	bottom:'3',
			        data:['不良批数','不良率']
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['第1周','第2周','第3周','第4周','第5周','第6周','第7周']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            name : '数量',
			            axisLabel : {
			                formatter: '{value} 次'
			            }
			        },
			        {
			            type : 'value',
			            name : '不良率',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
			    ],
			    series : [

			        {
			            name:'不良批数',
			            type:'bar',
			            data:[2.0, 4, 7.0, 23, 25, 76, 135]
			        },
			        
			        {
			            name:'不良率',
			            type:'line',
			            yAxisIndex: 1,
			            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3]
			        }
			    ]
			};
		option6 = {
				title : {
			        text: '供应商不良批（数）\n（率）日期对比排列图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    legend: {
			    	bottom:'3',
			        data:['上月不良批','本月不良批','上月不良率','本月不良率']
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['A供应商','B供应商','C供应商','D供应商','E供应商']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            name : '数量',
			            axisLabel : {
			                formatter: '{value} 次'
			            }
			        },
			        {
			            type : 'value',
			            name : '不良率',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
			    ],
			    series : [

			        {
			            name:'上月不良批',
			            type:'bar',
			            data:[2, 4, 7, 23, 25]
			        },
			        {
			            name:'本月不良批',
			            type:'bar',
			            data:[2, 5, 9, 26, 28]
			        },
			        {
			            name:'上月不良率',
			            type:'line',
			            yAxisIndex: 1,
			            data:[2,1,2,3,4]
			        },
			        {
			            name:'本月不良率',
			            type:'line',
			            yAxisIndex: 1,
			            data:[3,3,5,2,6]
			        },
			    ]
			};
		
		option7 = {
				title : {
			        text: '零部件不良批（数）\n（率）日期对比排列图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    legend: {
			    	bottom:'3',
			        data:['上月不良批','本月不良批','上月不良率','本月不良率']
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['A零部件','B零部件','C零部件','D零部件','E零部件']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            name : '数量',
			            axisLabel : {
			                formatter: '{value} 次'
			            }
			        },
			        {
			            type : 'value',
			            name : '不良率',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
			    ],
			    series : [

			        {
			            name:'上月不良批',
			            type:'bar',
			            data:[2, 4, 7, 23, 25]
			        },
			        {
			            name:'本月不良批',
			            type:'bar',
			            data:[2, 5, 9, 26, 28]
			        },
			        {
			            name:'上月不良率',
			            type:'line',
			            yAxisIndex: 1,
			            data:[2,1,2,3,4]
			        },
			        {
			            name:'本月不良率',
			            type:'line',
			            yAxisIndex: 1,
			            data:[3,3,5,2,6]
			        },
			    ]
			};
		echart1 = echarts.init(document.getElementById('chartOne_one'));
		echart1.setOption(option1);	
		echart2 = echarts.init(document.getElementById('chartTwo_one'));
		echart2.setOption(option2);	
		echart3 = echarts.init(document.getElementById('chartTwo_two'));
		echart3.setOption(option3);	
		echart4 = echarts.init(document.getElementById('chartThree_one'));
		echart4.setOption(option4);	
		echart5 = echarts.init(document.getElementById('chartThree_two'));
		echart5.setOption(option5);	
		echart6 = echarts.init(document.getElementById('chartFour_one'));
		echart6.setOption(option6);	
		echart7 = echarts.init(document.getElementById('chartFour_two'));
		echart7.setOption(option7);	
 }		
 </script>
 
<div id="shiftGroupLoad">
<script type="text/javascript">
$(function(){	
	$('#supplierList', navTab.getCurrentPanel()).dropdownlist({
								    id:'supplier',
								    columns:3,
								    selectedtext:'',
								    listboxwidth:800,//下拉框宽
								    maxchecked:10,
								    checkbox:true,
								    listboxmaxheight:400,
								    width:150,
								    requiredvalue:[],
								    selected:[],
								    data:${supMap},//数据，格式：{value:name}
								    onchange:function(text,value){
								    }
								});
	$('#partTypeList', navTab.getCurrentPanel()).dropdownlist({
	    id:'partType',
	    columns:3,
	    selectedtext:'',
	    listboxwidth:400,//下拉框宽
	    maxchecked:100,
	    checkbox:true,
	    listboxmaxheight:400,
	    width:150,
	    requiredvalue:[],
	    selected:[],
	    data:${partMap},//数据，格式：{value:name}
	    onchange:function(text,value){
	    }
	});						
							});
							
							

</script>
</div>
