<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<form id="voiceForm" onsubmit="return navTabSearch(this);"
	rel="pagerForm" method="post">
	<div class="searchContent dropdownSearchBar">
		<table class="searchContent">
			<tr>
				<td>机型类别：</td>
				<td colspan="2"><select name="productType" id="productType"
					onchange="loadProductData('#voiceCustomerTmList', '#voiceCustomerTypeList');">
						<option value="">-请选择-</option>
						<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }"
								${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
						</c:forEach>
				</select></td>
				<td>产品系列：</td>
				<td colspan="2">
					<div id="voiceCustomerTmList" class="dropdownlist"></div>
				</td>
				<td>型号：</td>
				<td>
					<div id="voiceCustomerTypeList" class="dropdownlist"></div>
				</td>
				<td>区域：</td>
				<td>
					<div id="voiceCustomerRegionList" class="dropdownlist"></div>
				</td>
			</tr>
			<tr>
				<td>时间:</td>
				<td><select id="timeType" name="timeType">
				<option value="year">年</option>
				<option value="month">月</option>
				<option value="date">日</option>
				</select></td>
				<td><input size="6" type="text" id="startTime" name="startTime"
					placeholder="请输入日期"
					onclick="laydate()"
					value="${vo.insStartTime }" readonly="readonly" /> 至 <input
					size="6" type="text" id="endTime" name="endTime"
					placeholder="请输入日期"
					onclick="laydate()"
					value="${vo.insEndTime }" readonly="readonly" /></td>
				<td>二级分类:</td>
				<td colspan="2"><div id="voiceCategoryList" class="dropdownlist"></div></td>
				<td>x轴数量</td>
				<td><input size="6" type="text" id="xCount" name="xCount"
					value="${vo.insStartTime == ''?vo.insStartTime:20 }" /></td>
				<td>是否消耗配件：</td>
				<td>
					<select id="isConsumedPart" name="isConsumedPart">
						<option value="">全选</option>
						<option value="是" <c:if test="${'vo.isConsumedPart' eq '是'}">selected="selected"</c:if>>是</option>
						<option value="否" <c:if test="${'vo.isConsumedPart' eq '否'}">selected="selected"</c:if>>否</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="DATE_TIME" onclick="showEcharts('DATE_TIME')">时间趋势图</button>
						</div>
					</div>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="type1" onclick="showEcharts('type1');">机型类别排列图</button>
						</div>
					</div> <!-- 产品系列这一字段尚不确定 先空着 -->
					<!-- <div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="" onclick="showEcharts();">产品系列排列图</button>
						</div>
					</div> -->
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="MODEL_PRODUCT" onclick="showEcharts('MODEL_PRODUCT');">产品型号排列图</button>
						</div>
					</div>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="SERVICE_CENTER" onclick="showEcharts('SERVICE_CENTER');">区域排列图</button>
						</div>
					</div>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="TYPE2" onclick="showEcharts('TYPE2');">分类排列图</button>
						</div>
					</div>
				</td>

			</tr>
		</table>
	</div>
</form>



<div style="height:100%;margin: 0">
	<div id="voiceChart" style="height: 400px"></div>
</div>
<script type="text/javascript" src="resources/echart/echarts.js"></script>
<script type="text/javascript" src="resources/echart/china.js"></script>
<script type="text/javascript">
function showEcharts(title){
	//机型类别
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
	 if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
	//产品系列
    var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val();
    //产品型号
    var partTypeListTxt = $("#partTypeListTxt", navTab.getCurrentPanel()).val();
    //区域
    var regionListTxt = $("#regionListTxt", navTab.getCurrentPanel()).val();
    //开始时间
    var startTime = $("#startTime", navTab.getCurrentPanel()).val();
    //结束时间
    var endTime = $("#endTime", navTab.getCurrentPanel()).val();
    //x轴数量
    var xCount = $("#xCount", navTab.getCurrentPanel()).val();
    //时间类型
    var timeType = $("#timeType",navTab.getCurrentPanel()).val();
    //二级分类
    var voiceCategoryTxt = $("#voiceCategoryTxt", navTab.getCurrentPanel()).val();
    var isConsumedPart = $("#isConsumedPart",navTab.getCurrentPanel()).val();
    var url = "${pageContext.request.contextPath}/voiceCustomerEchatrs/showCharts.do";
    var jsonData = {productType:productType,productFamilyTxt:productFamilyTxt,partTypeListTxt:partTypeListTxt,regionListTxt:regionListTxt,startTime:startTime,endTime:endTime,title:title,timeType:timeType,xCount:xCount,voiceCategoryTxt:voiceCategoryTxt,isConsumedPart:isConsumedPart};
    var type = "json";
    var callback = function (data){
    if(data.result == 0){
    	var chartsdata = data.chartsInfo;
    	
    
    	myCharts("voiceChart",data.chartsInfo)
    }else{
    	alert("程序出错"+data.msg)
    }
    }
    $.post(url,jsonData,callback,type);
}
function myCharts(id,data){
var myChart = echarts.init(document.getElementById(id));
var allTitle = $("#"+data.xTitle).html();
	var option ={
		title:{
			text:allTitle,
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
	            name:allTitle.substring(0,allTitle.length-3),
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
	            type:data.chartType,
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
<
<script type="text/javascript">
$(function(){
		loadProductFamilyData("#voiceCustomerTmList", "productFamilyTxt", [${vo.productFamilyTxt}], ${jsonProFamily});
    	loadProductTypeData("#voiceCustomerTypeList", "partTypeListTxt", [${vo.partTypeListTxt}], ${jsonParts});
     	loadRegionData("#voiceCustomerRegionList", "regionListTxt", [${vo.regionListTxt}], ${jsonRegions});
     	loadvoiceCategoryData("#voiceCategoryList","voiceCategoryTxt",[${vo.voiceCategoryTxt}],${jsonVoiceCategory});
     	
	});
	
	$("#productType").change(function(){
		var url = "qms/common/loadVoiceCategory.do"
		var data = {"productType" : $('select[name="productType"]', navTab.getCurrentPanel()).val()}
		var callback = function(data){
			loadvoiceCategoryData("#voiceCategoryList","voiceCategoryTxt",[${vo.voiceCategoryTxt}],data);
		}
		$.post(url,data,callback,"json");
	})
	
	function loadvoiceCategoryData(divId, dataId, voiceCategoryListTxt, jsonParts) { //初始化页面时，加客户之声二级分类
	$(divId, navTab.getCurrentPanel()).dropdownlist({
        id:dataId,
        columns:3,
        selectedtext:'',
        listboxwidth:450,//下拉框宽
        maxchecked:100,
        checkbox:true,
        listboxmaxheight:400,
        width:120,
        requiredvalue:[],
        selected:voiceCategoryListTxt,
        data:jsonParts,//数据，格式：{value:name}
        onchange:function(text,value){}
	});
}
</script>


