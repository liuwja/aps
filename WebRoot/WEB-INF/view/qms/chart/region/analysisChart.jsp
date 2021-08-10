<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
.divline
{
width:99%;
border:1px solid #6495ED; 
height:15px;
float:left;
margin-bottom:3px;
margin-top:3px;
margin-left:2px;
background:#FFF68F;
padding: 3px;
font-weight: bolder;
}	
.div_border{
	border-right:1px solid #6495ED;
	border-top:1px solid #6495ED;
	border-bottom:1px solid #6495ED;
	width:100%;
	margin-right:-1px; 
	margin-bottom:-1px;
}
.div_topdown_border{

}
.div_right_border{
    margin-top: 2px;
    margin-left: 2px;
}
</style>

<div class="pageHeader" style="overflow: visible;">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
                <th>机型类别：</th>
                <td>
					<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" <c:if test="${o.machineType==vo.productType}">selected</c:if>>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 
    			<th>区域：</th>                       
                <td>
                    <div id="regionMtList" class="dropdownlist"></div>
                </td>	
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="10" name="xCount" id="xCount" value="${vo.xCount+1 }"/>
    			</td>			
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadRegionAnalysisChart();">查找</button></div></div>
				</td>				
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent div_border" style="height: 500px;overflow: auto;" id="region_out_div">
		<!-- id每个页面要不一样 -->
		<div class="div_topdown_border" id="div_region_1" style="width:99%; float:left;height:300px;margin-left: 2px;margin-top: 2px"></div>
		<div class="divline" id="regionline0">&nbsp;</div>
		<div class="div_topdown_border" style="width:100%; float:left;">
			<div>
				<div class="div_right_border" id="div_region_sx_a_0" style="width:50%; float:left;"></div>
			</div>
			<div class="div_topdown_border" style="width:100%; float:left;">
				<div class="div_right_border" id="div_region_sx_0" style="width:50%; float:left;height:200px;"></div>
				<div id="div_region_sxp_0" style="width:49%; float:left;height:200px;"></div>
			</div>
			<div >
				<div class="div_right_border" id="div_region_l_0" style="width:33%; float:left;height:250px;"></div>
				<div class="div_right_border" id="div_region_p_0" style="width:33%; float:left;height:250px;"></div>
				<div id="div_region_g_0" style="width:33%; float:left;height:250px;"></div>
			</div>
			
		</div>
		<div class="divline" id="regionline1">&nbsp;</div>
		<div id="regionChart1" class="div_topdown_border" style="width:100%; float:left;">
			<div>
				<div class="div_right_border" id="div_region_sx_a_1" style="width:50%; float:left;"></div>
			</div>
			<div class="div_topdown_border" style="width:100%; float:left;">
				<div class="div_right_border" id="div_region_sx_1" style="width:50%; float:left;height:200px;"></div>
				<div id="div_region_sxp_1" style="width:49%; float:left;height:200px;"></div>
			</div>
			<div >
				<div class="div_right_border" id="div_region_l_1" style="width:33%; float:left;height:250px;"></div>
				<div class="div_right_border" id="div_region_p_1" style="width:33%; float:left;height:250px;"></div>
				<div id="div_region_g_1" style="width:33%; float:left;height:250px;"></div>
			</div>
			
		</div>
		<div class="divline" id="regionline2">&nbsp;</div>
		<div id="regionChart2"  class="div_topdown_border" style="width:100%; float:left;">
			<div>
				<div class="div_right_border" id="div_region_sx_a_2" style="width:50%; float:left;"></div>
			</div>
			<div class="div_topdown_border" style="width:100%; float:left;">
				<div class="div_right_border" id="div_region_sx_2" style="width:50%; float:left;height:200px;"></div>
				<div id="div_region_sxp_2" style="width:49%; float:left;height:200px;"></div>
			</div>
			<div >
				<div class="div_right_border" id="div_region_l_2" style="width:33%; float:left;height:250px;"></div>
				<div class="div_right_border" id="div_region_p_2" style="width:33%; float:left;height:250px;"></div>
				<div id="div_region_g_2" style="width:33%; float:left;height:250px;"></div>
			</div>
			
		</div>

</div>
<span id="regionAnalyTd">
      <script type="text/javascript">
          $(function(){
        	  $('#regionMtList').dropdownlist({
                  id:'regionListTxt',
                  columns:3,
                  selectedtext:'',
                  listboxwidth:700,//下拉框宽
                  maxchecked:1,
                  checkbox:true,
                  listboxmaxheight:400,
                  width:120,
                  requiredvalue:[],
                  selected:[${vo.regionListTxt}],
                  data:${jsonRegions},//数据，格式：{value:name}
                  onchange:function(text,value){
                  }
              });
              
          });
      </script>
  </span> 	
<script type="text/javascript">
var width = 0;
var width1 = 0;
var width2 = 0; 
var detailFunctionName = 'openRegionTab';
jQuery(document).ready(function(){
	//loadRegionAnalysisChart();  
	width = $(".pageContent",navTab.getCurrentPanel()).width()*0.95;
	width1 = parseInt(width/2);
	width2 = parseInt(width/3); 
	$("#region_out_div",navTab.getCurrentPanel()).hide(); 
	var queryMonth = '${vo.queryMonth}';
	var productType = '${vo.productType}';	
	if(queryMonth!="" && productType!="" ){
	  loadRegionAuto(queryMonth,productType);
	}
});
    
function loadRegionAuto(queryMonth,productType){
   var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
   var url_1 = "<c:url value='regionChart/getRegionInfo.do'/>";
	$.post(url_1, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否',faultReasonValid:'是',width:parseInt(width),hight:200, xCount:(xCount-1)}, function(data) {

		if (data.result == 0) {
			//alert(data.chartsInfo.chartType);
			showChart("div_region_1", data.chartsInfo);
			loadRegionChildChart(productType, queryMonth, data.chartsInfo, xCount,"auto");
			$("#region_out_div",navTab.getCurrentPanel()).show();
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function openRegionTab(num, type){
	var paraValues = $("#anaRegion_"+num, navTab.getCurrentPanel()).val();
	var arrys = paraValues.split(",");
	navTab.closeTab('anaChartTab');
	navTab.openTab('anaChartTab', "partTypeChart/detailInfo.do", { title:'排列图-详细信息', fresh:false, data:{productType:arrys[0],queryMonth:arrys[1],region:arrys[2], typeNum:type} });
}

function loadRegionAnalysisChart() {

	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
    if(productType==""){
        alert("请选择机型类别");
        return false;
    }
    
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if(queryMonth == ''){
		alert('请选择维修截至月份');
		return false;
	}
	var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
	
	var url_1 = "<c:url value='regionChart/getRegionInfo.do'/>";
	$.post(url_1, {productType:productType,queryMonth:queryMonth,chartType:'column',width:parseInt(width),hight:200, xCount:(xCount-1)}, function(data) {

		if (data.result == 0) {
			showChart("div_region_1", data.chartsInfo);
			loadRegionChildChart(productType, queryMonth, data.chartsInfo, xCount,"analysis");
			$("#region_out_div",navTab.getCurrentPanel()).show();
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	
}


function loadRegionChildChart(productType, queryMonth, data, xCount,loadType){
	var xValue = data.xValue;
	$.ajaxSetup({ 
		    async : false 
		});
	var child_length = 0;
	for(;child_length<xValue.length;child_length++){ 
		//
		if(child_length==3){
			child_length = 0;
			break;
		}
		var tmpX = xValue[child_length];
		tmpX = tmpX.replace(/(^\s+)|(\s+$)/g,"");
		if (tmpX == null || tmpX == "" || tmpX == undefined) {
			var tempIndex = child_length + 1;
			tmpX = xValue[tempIndex];
		}
		//选择单个型号
		if(loadType=="analysis"){
			var selectedvalue = $("#regionMtList",navTab.getCurrentPanel()).find(".ddl-select").attr("selectedvalue");
			if(selectedvalue!=null && selectedvalue!=""){
				tmpX = selectedvalue;
				$("#regionline1",navTab.getCurrentPanel()).hide();
				$("#regionChart1",navTab.getCurrentPanel()).hide();
				$("#regionline2",navTab.getCurrentPanel()).hide();
				$("#regionChart2",navTab.getCurrentPanel()).hide();
				if(child_length>=1){
					break;
				}
			}else{
				$("#regionline1",navTab.getCurrentPanel()).show();
				$("#regionChart1",navTab.getCurrentPanel()).show();
				$("#regionline2",navTab.getCurrentPanel()).show();
				$("#regionChart2",navTab.getCurrentPanel()).show();
			}
		}
        $("#regionline"+child_length).html((child_length + 1) + '. ' + tmpX);
		var url_sx = "<c:url value='timeChart/getTimeOnlyTotalInfo.do'/>";
		$.post(url_sx, {productType:productType,queryMonth:queryMonth,isOver:'否',faultReasonValid:'是',region:tmpX,width:width1,hight:100}, function(data) {
			//alert(child_length);
			if (data.result == 0) {
				var passData = productType+","+queryMonth+","+tmpX;
				data.chartsInfo.index = child_length;
				data.chartsInfo.type = 0;
				data.chartsInfo.funcName = detailFunctionName;
				showChart("div_region_sx_"+child_length, data.chartsInfo);
				$("#div_region_sx_a_"+child_length).empty();
				$("#div_region_sx_a_"+child_length).append("<input type='hidden' id='anaRegion_"+child_length+"' name='anaRegion_"+child_length+"' value='"+passData+"'/>");
			} else {
				alertMsg.error("加载时序图出错，请联系管理员");
				return ;
			}
		});
		var url_sxp = "<c:url value='timeMatrixTable/getTimeTotalInfo.do'/>";
		$.post(url_sxp, {productType:productType,queryMonth:queryMonth,isOver:'否',faultReasonValid:'是',region:tmpX,width:width1,hight:100}, function(data) {
			//alert(child_length);
			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 1;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_region_sxp_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载P控制图出错，请联系管理员");
				return ;
			}
		});

		var url_l = "<c:url value='plineChart/getPlineInfo.do'/>";
		$.post(url_l, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否',faultReasonValid:'是',xCount:6,region:tmpX,width:width2,hight:100}, function(data) {

			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type =3;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_region_l_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载产线排列图出错");
				return ;
			}
		});

		var url_p = "<c:url value='partTypeChart/getPartTypeInfo.do'/>";
		$.post(url_p, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否',faultReasonValid:'是',xCount:6,region:tmpX,width:width2,hight:100}, function(data) {

			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 6;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_region_p_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载型号排列图出错");
				return ;
			}
		});
		var url_g = "<c:url value='faultReasonChart/getFaultReasonInfo.do'/>";
		$.post(url_g, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否',faultReasonValid:'是',xCount:6,region:tmpX,width:width2,hight:100}, function(data) {

			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 4;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_region_g_"+child_length, data.chartsInfo);		
			} else {
				alertMsg.error("加载故障小类排列图出错");
				return ;
			}
		});	
	}
	$.ajaxSetup({ 
		    async : true 
		});
}
function checkPass(num){
	var reg = new RegExp("^-?\\d+$");
	if(num.match(reg)==null){
        alert("X轴数量请输入整数!");
        return false;
    }
    if(num<5){
    	alert("X轴数量不得小于5!");
        return false;
    }
    return true;
}					                                            				
</script>