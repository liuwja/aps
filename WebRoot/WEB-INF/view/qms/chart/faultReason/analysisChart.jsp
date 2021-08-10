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

<div class="pageHeader">
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
    			<th>合并故障名称：</th>
    			<td>
    				<input type="text" name="meshFaultName" id="analymesfr_meshFaultName" size="15" readonly="true" value="" style="float: left;"/>    				
    				<a id="btn" class="btnLook btn" href="qms/commonSelect/meshFaultNameSelect.do" width=650 height=500 lookupGroup="analymesfr">合并故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="analymesfr_coclearAll()"  title="清空"></a> 
    			</td>
    			<th>故障名称：</th>
    			<td>
    				<input type="hidden" name="faultReasonID" id="analyfr_id"  value=""/>  
    				<input type="hidden" name="faultReasonCode" id="analyfr_code" value=""/>  
    				<input type="text"  id="analyfr_name" size="15" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="analyfrSel(this)" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?groupName=analyfr" width=950 height=500 lookupGroup="analyfr">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="analyfr_coclearAll()"  title="清空"></a> 
    			</td>
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="2" maxlength="2" name="xCount" id="xCount" value="${vo.xCount+1 }"/>
    			</td> 		
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" size="8" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadFualtReasonAnalysisChart();">查找</button></div></div>
				</td>				
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent div_border" style="height: 500px;overflow: auto;" id="fualtReason_out_div" my="11">
		<!-- id每个页面要不一样 -->
		<div class="div_topdown_border" id="div_fualtReason_1" style="width:99%; float:left;height:300px;margin-left: 2px;margin-top: 2px"></div>
		<div class="divline" id="faultline0">&nbsp;</div>
		<div class="div_topdown_border" style="width:100%; float:left;">
			<div>
				
				<div class="div_right_border" id="div_fualtReason_sx_a_0" style="width:50%; float:left;"></div>
			</div>
			<div class="div_topdown_border" style="width:100%; float:left;">
				<div class="div_right_border" id="div_fualtReason_sx_0" style="width:50%; float:left;height:200px;"></div>
				<div id="div_fualtReason_sxp_0" style="width:49%; float:left;height:200px;"></div>
			</div>
			<div >
				<div class="div_right_border" id="div_fualtReason_p_0" style="width:33%; float:left;height:250px;"></div>
				<div class="div_right_border" id="div_fualtReason_r_0" style="width:33%; float:left;height:250px;"></div>
				<div id="div_fualtReason_l_0" style="width:33%; float:left;height:250px;"></div>
			</div>
			
		</div>
		<div class="divline" id="faultline1">&nbsp;</div>
		<div id="faultChart1" class="div_topdown_border" style="width:100%; float:left;">
			<div>			
				<div class="div_right_border" id="div_fualtReason_sx_a_1" style="width:50%; float:left;"></div>
			</div>
			<div class="div_topdown_border" style="width:100%; float:left;">
				<div class="div_right_border" id="div_fualtReason_sx_1" style="width:50%; float:left;height:200px;"></div>
				<div id="div_fualtReason_sxp_1" style="width:49%; float:left;height:200px;"></div>
			</div>
			<div >
				<div class="div_right_border" id="div_fualtReason_p_1" style="width:33%; float:left;height:250px;"></div>
				<div class="div_right_border" id="div_fualtReason_r_1" style="width:33%; float:left;height:250px;"></div>
				<div id="div_fualtReason_l_1" style="width:33%; float:left;height:250px;"></div>
			</div>
			
		</div>
		<div class="divline" id="faultline2">&nbsp;</div>
		<div id="faultChart2" class="div_topdown_border" style="width:100%; float:left;">
			<div>
				
				<div class="div_right_border" id="div_fualtReason_sx_a_2" style="width:50%; float:left;"></div>
			</div>
			<div class="div_topdown_border" style="width:100%; float:left;">
				<div class="div_right_border" id="div_fualtReason_sx_2" style="width:50%; float:left;height:200px;"></div>
				<div id="div_fualtReason_sxp_2" style="width:49%; float:left;height:200px;"></div>
			</div>
			<div >
				<div class="div_right_border" id="div_fualtReason_p_2" style="width:33%; float:left;height:250px;"></div>
				<div class="div_right_border" id="div_fualtReason_r_2" style="width:33%; float:left;height:250px;"></div>
				<div id="div_fualtReason_l_2" style="width:33%; float:left;height:250px;"></div>
			</div>
		
		</div>   

</div>

<script type="text/javascript">
var width = 0;
var width1 = 0;
var width2 = 0;
var detailFunctionName = 'openFaultResonTab';
jQuery(document).ready(function(){
	//loadPlineAnalysisChart();  
	width = $(".pageContent",navTab.getCurrentPanel()).width()*0.95;
	width1 = parseInt(width/2);
	width2 = parseInt(width/3);
	$("#fualtReason_out_div",navTab.getCurrentPanel()).hide(); 
	var queryMonth = '${vo.queryMonth}';
	var productType = '${vo.productType}';
	if(queryMonth!="" && productType!="" ){
	 	loadFaultResonAuto(queryMonth,productType);
	} 
});
     
function loadFaultResonAuto(queryMonth,productType){
   var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
   var url_1 = "<c:url value='faultReasonChart/getFaultReasonInfo.do'/>";
	$.post(url_1, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否',faultReasonValid:'是',width:parseInt(width),hight:200, xCount:(xCount-1)}, function(data) {

		if (data.result == 0) {
			//alert(data.chartsInfo.chartType);
			showChart("div_fualtReason_1", data.chartsInfo);
			loadFualtReasonChildChart(productType, queryMonth, data.chartsInfo, xCount,"auto");
			$("#fualtReason_out_div",navTab.getCurrentPanel()).show();
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function openFaultResonTab(num, type){
	var paraValues = $("#anaReason_"+num, navTab.getCurrentPanel()).val();
	var arrys = eval("("+paraValues+")");
	arrys.typeNum = type;
	navTab.closeTab('anaChartTab');
	navTab.openTab('anaChartTab', "partTypeChart/detailInfo.do", { title:'排列图-详细信息', fresh:false, data:arrys});
}

function loadFualtReasonAnalysisChart() {

	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
    if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
    
    var analyId = $("#analyfr_code", navTab.getCurrentPanel()).val();
    var meshFaultName = $("#analymesfr_meshFaultName", navTab.getCurrentPanel()).val();
	if(analyId!="" && meshFaultName!=""){
		alertMsg.info("请选择合并故障小类或故障小类！");
	    return false;
	}
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if(queryMonth == ''){
		alertMsg.info('请选择维修截至月份');
		return false;
	}
	var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
	
	var url_1 = "<c:url value='faultReasonChart/getFaultReasonInfo.do'/>";
	$.post(url_1, {productType:productType,queryMonth:queryMonth,chartType:'column',isOver:'否',faultReasonValid:'是',width:parseInt(width),hight:200, xCount:(xCount-1)}, function(data) {

		if (data.result == 0) {
			showChart("div_fualtReason_1", data.chartsInfo);
			loadFualtReasonChildChart(productType, queryMonth, data.chartsInfo, xCount,"analysis");
			$("#fualtReason_out_div",navTab.getCurrentPanel()).show();
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
	
}

function loadFualtReasonChildChart(productType, queryMonth, data, xCount,loadType){
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
		var analyName = "";
		var analyId = "";
		//选择单个型号
		if(loadType=="analysis"){
			var meshFaultName = $("#analymesfr_meshFaultName", navTab.getCurrentPanel()).val();
			analyName = $("#analyfr_name", navTab.getCurrentPanel()).val();
			analyId = $("#analyfr_code", navTab.getCurrentPanel()).val();
			if((meshFaultName!=null && meshFaultName!="") || analyId != ""){
				tmpX = meshFaultName;
				$("#faultline1",navTab.getCurrentPanel()).hide();
				$("#faultChart1",navTab.getCurrentPanel()).hide();
				$("#faultline2",navTab.getCurrentPanel()).hide();
				$("#faultChart2",navTab.getCurrentPanel()).hide();
				if(child_length>=1){
					break;
				}
			}else{
				$("#faultline1",navTab.getCurrentPanel()).show();
				$("#faultChart1",navTab.getCurrentPanel()).show();
				$("#faultline2",navTab.getCurrentPanel()).show();
				$("#faultChart2",navTab.getCurrentPanel()).show();
			}
		}
	    $("#faultline"+child_length,navTab.getCurrentPanel()).html((child_length + 1) + '. ' + tmpX);

		var url_sx = "<c:url value='timeChart/getTimeOnlyTotalInfo.do'/>";
		$.post(url_sx, {productType:productType,queryMonth:queryMonth,faultReasonTxt:analyName,isOver:'否',faultReasonValid:'是',faultReasonCode:analyId,meshFaultName:tmpX,width:width1,hight:100}, function(data) {
			//alert(child_length);
			if (data.result == 0) {
				var passData = '{productType:"'+productType+'",queryMonth:"'+queryMonth+'",meshFaultName:"'+tmpX+'",faultReasonTxt:"'+analyName+'",faultReasonCode:"'+analyId+'"}';
				data.chartsInfo.index = child_length;
				data.chartsInfo.type = 0;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_fualtReason_sx_"+child_length, data.chartsInfo);
				$("#div_fualtReason_sx_a_"+child_length,navTab.getCurrentPanel()).empty();
				$("#div_fualtReason_sx_a_"+child_length,navTab.getCurrentPanel()).append("<input type='hidden' id='anaReason_"+child_length+"' name='anaReason_"+child_length+"' value='"+passData+"'/>");
			} else {
				alertMsg.error("加载时序图出错，请联系管理员");
				return ;
			}
		});
		var url_sxp = "<c:url value='timeMatrixTable/getTimeTotalInfo.do'/>";
		$.post(url_sxp, {productType:productType,queryMonth:queryMonth,faultReasonCode:analyId,isOver:'否',faultReasonValid:'是',faultReasonTxt:analyName,meshFaultName:tmpX,width:width1,hight:100}, function(data) {
			//alert(child_length);
			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 1;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_fualtReason_sxp_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载P控制图出错，请联系管理员");
				return ;
			}
		});

		var url_r = "<c:url value='regionChart/getRegionInfo.do'/>";
		$.post(url_r, {productType:productType,queryMonth:queryMonth,chartType:'column',xCount:6,isOver:'否',faultReasonValid:'是',faultReasonCode:analyId,faultReasonTxt:analyName,meshFaultName:tmpX,width:width2,hight:100}, function(data) {

			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 2;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_fualtReason_r_"+child_length, data.chartsInfo);
			} else {
				alertMsg.error("加载区域排列图出错");
				return ;
			}
		});

		var url_p = "<c:url value='partTypeChart/getPartTypeInfo.do'/>";
		$.post(url_p, {productType:productType,queryMonth:queryMonth,chartType:'column',xCount:6,isOver:'否',faultReasonValid:'是',faultReasonCode:analyId,faultReasonTxt:analyName,meshFaultName:tmpX,width:width2,hight:100}, function(data) {

			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 6;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_fualtReason_p_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载型号排列图出错");
				return ;
			}
		});
		var url_l = "<c:url value='plineChart/getPlineInfo.do'/>";
		$.post(url_l, {productType:productType,queryMonth:queryMonth,chartType:'column',xCount:6,isOver:'否',faultReasonValid:'是',faultReasonCode:analyId,faultReasonTxt:analyName,meshFaultName:tmpX,width:width2,hight:100}, function(data) {

			if (data.result == 0) {
			    data.chartsInfo.index = child_length;
                data.chartsInfo.type = 3;
                data.chartsInfo.funcName = detailFunctionName;
				showChart("div_fualtReason_l_"+child_length, data.chartsInfo);
				
			} else {
				alertMsg.error("加载产线排列图出错");
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
function analyfr_coclearAll(){
	$("#analyfr_name", navTab.getCurrentPanel()).val("");
	$("#analyfr_code", navTab.getCurrentPanel()).val("");
	$("#analyfr_id", navTab.getCurrentPanel()).val("");
}
function analymesfr_coclearAll(){
	$("#analymesfr_meshFaultName", navTab.getCurrentPanel()).val("");
}
//查询带回
function selectCallBack(obj){
	var faultReasonIds = $("#analyfr_id",navTab.getCurrentPanel()).val();
	var url = "qms/commonSelect/faultReasonSelect.do?groupName=analyfr&faultReasonIds="+faultReasonIds;
	$(obj).attr("href",url);
}
function analyfrSel(obj){
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultReasonSelect.do?groupName=analyfr";
	$(obj).attr("href",href+"&productType="+productType);
}
</script>