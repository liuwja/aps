<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
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
//型号
function loadBetCommonChart(index) {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
	var insStartTime = $("#insStartTime", navTab.getCurrentPanel()).val();
	var insEndTime = $("#insEndTime", navTab.getCurrentPanel()).val();
	var dwStartTime = $("#dwStartTime", navTab.getCurrentPanel()).val();
	var dwEndTime = $("#dwEndTime", navTab.getCurrentPanel()).val();
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
	
    if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
	var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	if(!checkPass(xCount)){
		return false;
	}
	//工厂，产线，区域，故障小类
	var partTypeListTxt = $("#partType_partType", navTab.getCurrentPanel()).val();
    var plineListTxt = $("#plineListTxt", navTab.getCurrentPanel()).val();
    var regionListTxt = $("#regionListTxt", navTab.getCurrentPanel()).val();
    var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val();
    var isOver = $("#isOver", navTab.getCurrentPanel()).val();
    
    var faultReasonName = $("#COMCB_name", navTab.getCurrentPanel()).val();
    var faultReasonCode =  $("#COMCB_code", navTab.getCurrentPanel()).val();
    var faultTypeTxt  = $("#COMMONB_FT_name", navTab.getCurrentPanel()).val();
    var faultTypeCode =  $("#COMMONB_FT_code", navTab.getCurrentPanel()).val();
    var statisType =$("#statisType", navTab.getCurrentPanel()).find("option:selected").val();
    var faultReasonValid =  $("#faultReasonValid", navTab.getCurrentPanel()).val();
    var startI = $("#betStartI", navTab.getCurrentPanel()).val();
    var endI = $("#betEndI", navTab.getCurrentPanel()).val();
    var insStartI = $("#betInsStartI", navTab.getCurrentPanel()).val();
    var insEndI = $("#betInsEndI", navTab.getCurrentPanel()).val();
    var isConsumedPart = $("#isConsumedPart", navTab.getCurrentPanel()).val();
    
	var url = "";
	if(index == 1){
		url = "<c:url value='productFamilyChart/getBetweenProdFamilyInfo.do'/>";
	}else if(index ==2){
		url = "<c:url value='partTypeChart/getBetweenPartTypeInfo.do'/>";
	}else if(index ==3){
		url = "<c:url value='regionChart/getBetweenRegionInfo.do'/>";
	}else if(index ==4){
		url = "<c:url value='plineChart/getBetweenPlineInfo.do'/>";
	}else if(index ==5){
		url = "<c:url value='faultReasonChart/getFaultReasonBetweenInfo.do'/>";
	}else if(index ==6){
		url = "<c:url value='faultTypeChart/getBetweenFaultTypeInfo.do'/>";
	}
	var jsonData = {productType:productType, xCount:(xCount-1), insStartTime:insStartTime, insEndTime:insEndTime, faultReasonName:faultReasonName, dwStartTime:dwStartTime,dwEndTime:dwEndTime,
			startTime:startTime, endTime:endTime, faultReasonCode:faultReasonCode, faultTypeTxt:faultTypeTxt, partTypeListTxt:partTypeListTxt,faultReasonValid:faultReasonValid,
			regionListTxt:regionListTxt, plineListTxt:plineListTxt, statisType:statisType, productFamilyTxt:productFamilyTxt, faultTypeCode:faultTypeCode, isOver:isOver,
			startI:startI, endI:endI, insStartI:insStartI, insEndI:insEndI,isConsumedPart:isConsumedPart };
	
	$.post(url,jsonData, function(data) {
		if (data.result == 0) {
			showChart("betweenComChart", data.chartsInfo,function(event){
				var category = event.point.category;
				loadURL(jsonData,category,index);
			});
		} else {
			alertMsg.infoMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

//前往crm维修表
function loadURL(jsonData,category,index){
	console.log("category = " + category);
	if(index == 1){
		jsonData.productFamilyTxt = category;
	}else if(index ==2){
		jsonData.partTypeListTxt = category;
	}else if(index ==3){
		if(category.indexOf("服务中心")==-1){
			category = category+"服务中心";
		}
		jsonData.regionListTxt = category;
	}else if(index ==4){
		jsonData.plineListTxt = category;
	}else if(index ==5){
		jsonData.faultReasonTxt = category;
		jsonData.meshFaultReasonName = category;
	}else if(index ==6){
		jsonData.faultTypeTxt = category;
		jsonData.faultTypeName = category;
	}
	jsonData.isOver ="否";
	jsonData.faultReasonValid = "是";
	jsonData.title="CRM市场维修记录";
	jsonData.fresh = false;
	var url = "base/repairRecord/repairRecord.do";
	navTab.closeTab("repairRecordNav");
	navTab.openTab("repairRecordNav", url, { title:"CRM市场维修记录", fresh:true, data:jsonData});
}

function checkPass(num){
	var reg = new RegExp("^-?\\d+$");
	if(num.match(reg)==null){
		alertMsg.info("X轴数量请输入整数!");
        return false;
    }
    if(num<5){
    	alertMsg.info("X轴数量不得小于5!");
        return false;
    }
    return true;
}

function loadBetComChartCondition(loadType) {
	var url = "<c:url value='qms/common/partTypeLineOps.do' />";
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var factory = $("#factory", navTab.getCurrentPanel()).val();
	var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val();
	var plineListTxt = $("#plineListTxt", navTab.getCurrentPanel()).val();
	var jsonVar = {productType:productType,factory:factory,plineListTxt:plineListTxt,productFamilyTxt:productFamilyTxt, title: "\"生产及维修日期排列分析\"",productFamilyDocId:"prodFamilyBetweenComList",plineDocId:"plineBetweenComList"};
	  
	if(loadType == 0){
		delete jsonVar["productFamilyTxt"];
	}
	if(loadType == 1){
		delete jsonVar["plineListTxt"];
	}
	$("#betweenComchartTd",navTab.getCurrentPanel()).load(url,jsonVar);
}

function betcom_fr_clearAll(){
	$("#COMCB_id", navTab.getCurrentPanel()).val("");
	$("#COMCB_name", navTab.getCurrentPanel()).val("");
	$("#COMCB_code", navTab.getCurrentPanel()).val("");
}

function betcom_ft_clearAll()
{
    $("#COMMONB_FT_id", navTab.getCurrentPanel()).val("");
    $("#COMMONB_FT_code", navTab.getCurrentPanel()).val("");
    $("#COMMONB_FT_name", navTab.getCurrentPanel()).val("");
}
function COMCB_FR_Sel(obj){
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultReasonSelect.do?groupName=COMCB";
	$(obj).attr("href",href+"&productType="+productType);
}
//机型类别选择
function betcomftSel(obj){
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultTypeSelect.do?groupName=COMMONB_FT";
	$(obj).attr("href",href+"&productType="+productType);
}
//机型选择
function partTypeSel(obj){
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var partTypeId = $("#partType_id",navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/partTypeSelect.do?groupName=partType";
	$(obj).attr("href",href+"&productType="+productType+"&partTypeId="+partTypeId);
}

function partType_clearAll(){
	 $("#partType_id", navTab.getCurrentPanel()).val("");
	 $("#partType_partType", navTab.getCurrentPanel()).val("");
}

function BC_REGIONSel(obj) {
	var strarr = $("#BC_REGION_id", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/findRegionSelect.do?strarr="+strarr;
	$(obj).attr("href",href);
}

function BC_REGION_clearAll(){
	$("#BC_REGION_id", navTab.getCurrentPanel()).val("");
	$("#BC_REGION_code", navTab.getCurrentPanel()).val("");
	$("#BC_REGION_name", navTab.getCurrentPanel()).val("");
}
function checkValue(k){
	if($(k).val()!="" && $(k).val()<=0){
		alert("不能输入小于等于0的数!");
		$(k).val("");
	}
}
</script>
<div class="pageHeader" style="position:static">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar" style="">
		<table class="searchContent">
			<tr>
				<td>机型类别：</td>
                <td>
					<select name="productType" onchange="loadBetComChartCondition(0);">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td>
    			<td>产品系列：</td>          
                <td>
                	<div id="prodFamilyBetweenComList" class="dropdownlist"></div>
                </td>
                <td>生产日期：</td>
                <td>
                    <input type="text" id="dwStartTime" name="dwStartTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.dwStartTime}" readonly="readonly" size="8"/>
                    	至
                    <input type="text" id="dwEndTime" name="dwEndTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.dwEndTime}" readonly="readonly" size="8"/>
                </td>
                <td>
                	生产后第X月-第X月的维修数:
                </td>
                <td>
                    <input type="text" id="betStartI" name="startI" onchange="checkValue(this)"  value="${vo.startI}" size="5"/>
                    	-
                    <input type="text" id="betEndI" name="endI" onchange="checkValue(this)"  value="${vo.endI}" size="5"/>
                </td>
                <td>故障小类：</td>
    			<td>
    				<input type="hidden" name="faultReasonID" id="COMCB_id" value="${vo.faultReasonID}"/>
    				<input type="hidden" name="faultReasonCode" id="COMCB_code" value="${vo.faultReasonCode}"/>
    				<input type="text" name="faultReasonTxt" id="COMCB_name" size="20" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="COMCB_FR_Sel(this)" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?groupName=COMCB" width=950 height=500 lookupGroup="COMCB">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="betcom_fr_clearAll()" title="清空"></a> 
    			</td>
              </tr>
             <tr>
                <td style="text-align: center;">工厂：</td>
                <td>
					<select name="factory" id="factory" onchange="loadBetComChartCondition(1);">
							<option value="">请选择</option>
							<c:forEach items="${factorys}" var="o">
							<option value="${o.factory }" <c:if test="${vo.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
							</c:forEach>
					</select>
    			</td>
    			<td style="text-align: center;">型号：</td>                       
                <td>
                	<input type="hidden" id="partType_id" value="">
    				<input type="text" name="partTypeName" id="partType_partType" size="12" readonly="true" value="" style="float: left;"/>    				
    				<a id="btn" onclick="partTypeSel(this)"  class="btnLook btn" href="qms/commonSelect/partTypeSelect.do?groupName=partType" width=850 height=500 lookupGroup="partType">型号选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="partType_clearAll()"  title="清空"></a> 
                </td>
                <td style="text-align: center;">安装日期：</td>
                <td>
                	<input type="text" id="insStartTime" name="insStartTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.insStartTime}" readonly="readonly" size="8"/>
                   	至
                    <input type="text" id="insEndTime" name="insEndTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.insEndTime}" readonly="readonly" size="8"/>
                </td>
                <td>
                	安装后第X月-第X月的维修数:
                </td>
                <td>
                    <input type="text" id="betInsStartI" name="insStartI" onchange="checkValue(this)" value="${vo.insStartI}" size="5"/>
                    	-
                    <input type="text" id="betInsEndI" name="insEndI" onchange="checkValue(this)" value="${vo.insEndI}" size="5"/>
                </td>
                
                
                <td style="padding-right:10px;">故障大类：</td>
                <td>
                    <input type="hidden" name="faultTypeID" id="COMMONB_FT_id" size="15" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="COMMONB_FT_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="COMMONB_FT_name" size="20" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a onclick="betcomftSel(this);" id="btn" class="btnLook btn" href="qms/commonSelect/faultTypeSelect.do?groupName=COMMONB_FT" width=950 height=500 lookupGroup="COMMONB_FT">故障大类选择</a>
                    <a class="btnClear" href="javascript:void(0);" onclick="betcom_ft_clearAll()" title="清空"></a> 
                </td>
			</tr>
			<tr>
				<td  style="text-align: center;">产线：</td>                       
                <td>
                	<div id="plineBetweenComList" class="dropdownlist"></div>
                </td>
                <td style="text-align: center;">区域：</td>                       
                <td>
                	<div id="regionBetweenComList" class="dropdownlist"></div>
                </td>
                <td style="text-align: center;">维修日期：</td>
                <td>
                    <input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime}" readonly="readonly" size="8"/>
                    	至
                    <input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime}" readonly="readonly" size="8"/>
                </td>
				<td colspan="2">
					<span>百台内：</span>
					<select name="isOver" id="isOver">
						<option value="">全选</option>
						<option <c:if test="${vo.isOver=='否'}">selected</c:if> value="否">是</option>
						<option <c:if test="${vo.isOver=='是'}">selected</c:if> value="是">否</option>
					</select>
					<span style="margin-left: 15px">X轴数量：</span>
					<input type="text" size="2" name="xCount" id="xCount" value="${vo.xCount+1 }" />
					<span>故障小类是否有效：</span>
					<select id="faultReasonValid" name="faultReasonValid">
						<option value="">全选</option>
						<option value="是" ${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid eq '是' ? 'selected':''}>是</option>
						<option value="否" ${vo.faultReasonValid eq '否' ? 'selected':''}>否</option>
					</select>
				</td>
				<td>是否消耗配件：</td>
				<td>
					<select id = "isConsumedPart" name="isConsumedPart">
						<option value="">全选</option>
						<option value="是" ${vo.isConsumedPart eq '是' ? 'selected':''}>是</option>
						<option value="否" ${vo.isConsumedPart eq '否' ? 'selected':''}>否</option>
					</select>
				</td>
			</tr>
			<tr>                  
                <td colspan="6"  id="commonTd">
                	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadBetCommonChart(1);">产品系列排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadBetCommonChart(2);">型号排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadBetCommonChart(3);">区域排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadBetCommonChart(4);">产线排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadBetCommonChart(5);">故障小类排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadBetCommonChart(6);">故障大类排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '生产及维修日期排列分析', '排列图');">数据来源</button></div></div>
                </td>               			
			</tr>
		</table>
	</div>
	</form>
</div>
	 <span id="betweenComchartTd">
                    <script type="text/javascript">
                    $(function(){
                        $("#plineBetweenComList").dropdownlist({
                            id:'plineListTxt',
                            columns:3,
                            selectedtext:'',
                            listboxwidth:450,//下拉框宽
                            maxchecked:100,
                            checkbox:true,
                            listboxmaxheight:400,
                            width:120,
                            requiredvalue:[],
                            selected:[${vo.plineListTxt}],
                            data:${jsonLines},//数据，格式：{value:name}
                            onchange:function(text,value){
                            }
                        });

                        $("#prodFamilyBetweenComList").dropdownlist({
        				    id:'productFamilyTxt',
        				    columns:3,
        				    selectedtext:'',
        				    listboxwidth:500,//下拉框宽
        				    maxchecked:100,
        				    checkbox:true,
        				    listboxmaxheight:400,
        				    width:125,
        				    requiredvalue:[],
        				    selected:[${vo.productFamilyTxt}],
        				    data:${jsonProFamily},//数据，格式：{value:name}
        				    onchange:function(text,value){
        				    }
        				});
                        
                        $("#regionBetweenComList").dropdownlist({
                            id:'regionListTxt',
                            columns:3,
                            selectedtext:'',
                            listboxwidth:700,//下拉框宽
                            maxchecked:100,
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
<div class="pageContent">
		<div id="betweenComChart" class="singleChartDiv" style="height: 450px"></div>
</div>
