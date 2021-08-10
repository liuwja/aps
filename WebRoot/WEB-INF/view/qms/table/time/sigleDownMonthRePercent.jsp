<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
	#showColor{position:fixed;bottom:16px;left:0px;width:100%;height:20px;}
	#showColor .col_ul{text-align:center;margin-left:200px;background:#666;}
	#showColor .col_ul li{list-style:none;float:left;line-height:20px;}
	#showColor .col_ul .clo_li{width:30px;height:14px;margin:2px 2px 4px 10px;}
    #listboxBody  tr:nth-of-type(odd) { background: #eee;}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadTimeChart();  
});

function checkTimeMatrixDownPercent() {

	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
    if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
    if(startTime==""){
    	alertMsg.info("请选择开始时间");
        return false;
    }
    var repairPercent = $("#repairPercent", navTab.getCurrentPanel()).val();
    if(repairPercent!=null && repairPercent!=""){
    	var reg = new RegExp("^-?\\d+$");
    	if(repairPercent.match(reg)==null){
    		alertMsg.info("最大维修率请输入整率值!");
            return false;
        }
    }
	var curFormDom = $("#timeMatrixForPercent", navTab.getCurrentPanel());
	var statisData = $("input[name='statisData']:checked", navTab.getCurrentPanel()).val();
	//维修率跳转至维修数统计界面
	if(statisData == "repairCount"){
		curFormDom.attr("action","timeMatrixTable/sigleDownMonthReCount.do");
	}
	curFormDom.submit();
	return true;
}	
function loadPercentCondition(loadType)
{
	
	var url = "<c:url value='qms/common/partTypeLineOps.do' />";
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
	var factory = $('#factory', navTab.getCurrentPanel()).val();
	var partTypeListTxt = $('#partTypeListTxt', navTab.getCurrentPanel()).val();
	var plineListTxt = $('#plineListTxt', navTab.getCurrentPanel()).val();
	var regionListTxt = $('#regionListTxt', navTab.getCurrentPanel()).val();
	var jsonVar = {productType:productType,factory:factory,partTypeListTxt:partTypeListTxt,plineListTxt:plineListTxt,regionListTxt:regionListTxt,
			partTypeDocId:"downPartTypeMtpList",plineDocId:"downPlineMtpList"};
	if(loadType == 0){
		delete jsonVar["partTypeListTxt"];
	}
	if(loadType == 1){
		delete jsonVar["plineListTxt"];
	}
	$("#timeMatrixPercentTd").load(url,jsonVar);
}
function export_sigleDownPercet(url){    
	
	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var objs = $("#timeMatrixForPercent input",navTab.getCurrentPanel());
	var objs_select = $("#timeMatrixForPercent select",navTab.getCurrentPanel());	
	var myInput;
	for(var i = 0 ; i< objs.length+objs_select.length ; i++){
		var $obj = null;
		if(i>=objs.length){
			$obj = $(objs_select[i-objs.length]);	
		}else{
			$obj = $(objs[i]);			
		}
		var v = $obj.val();
		if(v==null || v==""){
			v="";
		}
		if($obj.attr("type")=="checkbox"){
			if(!$obj.attr("checked")){
				v="";
			}
		}
		myInput = document.createElement("input");
		myInput.setAttribute("name", $obj.attr("name"));
		myInput.setAttribute("value", v);
		myForm.appendChild(myInput);
	}
	if(checkTimeMatrixDownPercent())
	{
		document.body.appendChild(myForm);
		myForm.submit();
	}	
}
function clearAll(){
	$("#SDMRCP_TYPE_name").val("");
	$("#SDMRCP_TYPE_id").val("");
	$("#SDMRCP_TYPE_code").val("");
}

function clearAllReason(){
	$("#SDMRCP_name").val("");
	$("#SDMRCP_id").val("");
	$("#SDMRCP_code").val("");
}
function clearMeshReason(){
	$("#SDMRCMP_id",navTab.getCurrentPanel()).val("");
	$("#SDMRCMP_meshname",navTab.getCurrentPanel()).val("");
	$("#SDMRCMP_meshFaultCode",navTab.getCurrentPanel()).val("");
}
</script>
	<form onsubmit="return navTabSearch(this);" id="timeMatrixForPercent" rel="pagerForm" action="timeMatrixTable/sigleDownMonthRePercent.do" method="post">
	<div class="searchBar dropdownSearchBar" >
		<table class="searchContent">
			<tr style="line-height: 28px">
                <th>机型类别：</th>
                <td >
					<select name="productType" onchange="loadPercentCondition(0);">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 
    			<th>型号：</th>                      
                <td>
                	<div id="downPartTypeMtpList" class="dropdownlist"></div>
                </td> 
                <th>故障小类：</th>
    			<td>
    				<input type="hidden" name="faultReasonID" id="SDMRCP_id" value="${vo.faultReasonID}"/>  
    				<input type="hidden" name="faultReasonCode" id="SDMRCP_code" value="${vo.faultReasonCode}"/>  
    				<input type="text" name="faultReasonTxt" id="SDMRCP_name" size="15" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="smrcSel(this);" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?groupName=SDMRCP" width=950 height=500 lookupGroup="SDMRCP">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearAllReason()" ></a> 
    			</td>
                <th>统计方式：</th>                       
                <td>
                    <select name="statisType">
                    	<option value="month" <c:if test="${'month' eq vo.statisType}">selected="selected"</c:if> >月份</option> 	
                    	<option value="quarter" <c:if test="${'quarter' eq vo.statisType}">selected="selected"</c:if> >季度</option> 	
                    	<option value="year" <c:if test="${'year' eq vo.statisType}">selected="selected"</c:if> >年份</option> 	
					</select>
                </td>	
                <th>统计数据：</th>   
                <td>
                	<label style="display:inline-block;width:50px;">
                		<input type="radio" name="statisData" value="repairCount" <c:if test="${vo.statisData=='repairRate'}">checked="checked"</c:if>/>
                		维修数
                	</label>
                	<label style="display:inline-block;width:50px;">
                		<input type="radio" name="statisData" value="repairRate" checked="checked"/>
                		维修率
                	</label>
                </td>            
              </tr>
             <tr style="line-height: 28px">	
    			<th >工厂：</th>
                <td>
					<select name="factory" id="factory" onchange="loadPercentCondition(1);">
							<option value="">请选择</option>
							<c:forEach items="${factorys}" var="o">
							<option value="${o.factory }" <c:if test="${vo.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
							</c:forEach>
					</select>
    			</td>
    			<th >产线：</th>                       
                <td>
                	<div id="downPlineMtpList" class="dropdownlist"></div>
                </td>
                 <th >故障大类：</th>
                <td>
                    <input type="hidden" name="faultTypeID" id="SDMRCP_TYPE_id" size="15" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="SDMRCP_TYPE_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="SDMRCP_TYPE_name" size="15" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a onclick="monRepecentftSel(this);" id="btn" class="btnLook btn" href="qms/commonSelect/faultTypeSelect.do?groupName=SDMRCP_TYPE" width=950 height=500 lookupGroup="SDMRCP_TYPE">故障大类选择</a>  
                    <a class="btnClear" href="javascript:void(0);" onclick="clearAll()" ></a> 
                </td>  
                 <th>百台内：</th>
				<td>
                	<select name="isOver" id="isOver">
						<option value="">请选择</option>
						<option <c:if test="${vo.isOver=='否'}">selected</c:if> value="否">是</option>
						<option <c:if test="${vo.isOver=='是'}">selected</c:if> value="是">否</option>
					</select>
				</td>     	
                <th>最大维修率：</th> 
				<td>
					<input type="text" id="repairPercent" name="repairPercent"  size="8"  value='<c:if test="${vo.repairPercent!=0}">${vo.repairPercent}</c:if>'/>  
				</td>   
			</tr>
			<tr style="line-height: 28px">
                <th >区域：</th>                       
                <td>
                    <div id="downRegionMtpList" class="dropdownlist"></div>
                </td>           
              	 <th>生产年月：</th>
                <td>
                    <input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime}" readonly="readonly" size="8"/> 
                    	至 
                    <input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime }" readonly="readonly" size="8"/>                  
                </td>   
                 <th>合并故障小类：</th>
    			<td>
    				<input type="hidden" name="meshFaultReasonID" id="SDMRCMP_id" value="${vo.meshFaultReasonID}">
    				<input type="hidden" name="meshFaultReasonCode" id="SDMRCMP_code" value="${vo.meshFaultReasonCode}">
    				<input type="text" name="meshFaultName" id="SDMRCMP_meshname" size="15" readonly="true" value="${vo.meshFaultName}" style="float: left;"/>    				
    				<a id="btn" onclick="smrcMeshSel(this)" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?groupName=SDMRCMP" width=950 height=500 lookupGroup="SDMRCMP">合并故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearMeshReason()"  title="清空"></a> 
    			</td>        
    			<td colspan="2">
					故障小类是否有效：
					<select name="faultReasonValid" id="faultReasonValid">
						<option value="">请选择</option>
						<option <c:if test="${vo.faultReasonValid=='是'}">selected</c:if> value="是">是</option>
						<option <c:if test="${vo.faultReasonValid=='否'}">selected</c:if> value="否">否</option>
					</select>
				</td>         			
				<td colspan="2">
    				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkTimeMatrixDownPercent()">查找</button></div></div>
    				<a style="margin-left:20px;" class="button"  onclick="export_sigleDownPercet('timeMatrixTable/excelOutput_sigleMonthDownRePercent.do');"    title="确定导出信息？"><span>导出EXCEL</span></a>   				
    			</td>	
    			
			</tr>
		</table>
	</div>
	</form>
	
                <span id="timeMatrixPercentTd">
                    <script type="text/javascript">
                        $(function(){
                            $('#downPartTypeMtpList').dropdownlist({ 
                                id:'partTypeListTxt',
                                columns:3,
                                selectedtext:'',
                                listboxwidth:450,//下拉框宽
                                maxchecked:100,
                                checkbox:true,
                                listboxmaxheight:400,
                                width:120,
                                requiredvalue:[],
                                selected:[${vo.partTypeListTxt}],
                                data:${jsonParts},//数据，格式：{value:name}
                                onchange:function(text,value){
                                }
                            });

                            $('#downPlineMtpList').dropdownlist({
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

                            $('#downRegionMtpList').dropdownlist({
                                id:'regionListTxt',
                                columns:3,
                                selectedtext:'',
                                listboxwidth:700,//下拉框宽
                                maxchecked:100,
                                checkbox:true,
                                listboxmaxheight:400,
                                width:100,
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
	<table id="fixTable" class="fixTable">
		<c:if test="${column!= null || fn:length(column) != 0}">
		<thead>
			<tr>
				<th rowspan="2" style="min-width:70px;">生产年月</th>
				<th rowspan="2" style="min-width:70px;">生产台数</th>
				<th colspan="${fn:length(column)}">维修月(单月维修率)</th>
				<tr>
					<c:forEach items="${column}" var="m">
						<th style="min-width:70px;">${m}</th>
					</c:forEach>
				</tr>
			</tr>
		</thead>
		</c:if>
		 <tbody id="fixTableBody">
			<c:forEach items="${list}" var="o" begin="0" varStatus="status">
				<tr>
					<%-- <td>${status.index+1}</td> --%>
					<td>${o.baseMonth}</td>
					<td>${o.baseCount}</td>
					<c:forEach var="i" begin="1" end="${o.preDiff}">
				    	<td></td>
				    </c:forEach>
					<c:forEach items="${o.repairPercent}" var="num" varStatus="subStatus">
						<c:if test="${o.preDiff+subStatus.index < fn:length(column)}">
							<c:choose>
								<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
									<td style="background: #f8696b">${num}</td>
								</c:when>
							    <c:when test="${rangePercentList[0] < num and rangePercentList[1] >= num}">
									<td style="background: #63be7b">${num}</td>
							    </c:when>
							    <c:when test="${rangePercentList[1] < num and rangePercentList[2] >= num}">
									<td style="background: #85c87d">${num}</td>
							    </c:when>
							    <c:when test="${rangePercentList[2] < num and rangePercentList[3] >= num}">
									<td style="background: #a8d27f">${num}</td>
							    </c:when>
							    <c:when test="${rangePercentList[3] < num and rangePercentList[4] >= num}">
									<td style="background: #cbdc81">${num}</td>
							    </c:when>
							    <c:when test="${rangePercentList[4] < num and rangePercentList[5] >= num}">
									<td style="background: #ede683">${num}</td>
							    </c:when>
							    <c:when test="${rangePercentList[5] < num and rangePercentList[6] >= num}">
									<td style="background: #ffdd82">${num}</td>
							    </c:when>
							    <c:when test="${rangePercentList[6] < num and rangePercentList[7] >= num}">
									<td style="background: #fdc07c">${num}</td>
							    </c:when>
							    <c:when test="${rangePercentList[7] < num and rangePercentList[8] >= num}">
									<td style="background: #fca377">${num}</td>
							    </c:when>
							    <c:when test="${rangePercentList[8] < num and rangePercentList[9] >= num}">
									<td style="background: #fa8671">${num}</td>
							    </c:when>
							    <c:when test="${rangePercentList[9] < num and rangePercentList[10] >= num}">
									<td style="background: #f8696b">${num}</td>
							    </c:when>
							    <c:otherwise>
									<td>${num}</td>
							    </c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<div id="showColor">
		<ul class="col_ul">
			<c:if test="${not empty rangePercentList}">
			<li class="clo_li" style="background:#63be7b"></li>
			<li>${rangePercentList[0]}~${rangePercentList[1]}</li>
			<li class="clo_li" style="background:#85c87d"></li>
			<li>${rangePercentList[1]}~${rangePercentList[2]}</li>
			<li class="clo_li" style="background:#a8d27f"></li>
			<li>${rangePercentList[2]}~${rangePercentList[3]}</li>
			<li class="clo_li" style="background:#cbdc81"></li>
			<li>${rangePercentList[3]}~${rangePercentList[4]}</li>
			<li class="clo_li" style="background:#ede683"></li>
			<li>${rangePercentList[4]}~${rangePercentList[5]}</li>
			
			<li class="clo_li" style="background:#ffdd82"></li>
			<li>${rangePercentList[5]}~${rangePercentList[6]}</li>
			<li class="clo_li" style="background:#fdc07c"></li>
			<li>${rangePercentList[6]}~${rangePercentList[7]}</li>
			<li class="clo_li" style="background:#fca377"></li>
			<li>${rangePercentList[7]}~${rangePercentList[8]}</li>
			<li class="clo_li" style="background:#fa8671"></li>
			<li>${rangePercentList[8]}~${rangePercentList[9]}</li>
			<li class="clo_li" style="background:#f8696b"></li>
			<li>${rangePercentList[9]}~${rangePercentList[10]}</li>
			</c:if>
		</ul>
	</div>
</div>	

<script>
	var sdmrFixwidth = $(".pageContent", navTab.getCurrentPanel()).width()-16;
	var sdmrFixHeigh = $("#navTab").height() - $(".searchBar", navTab.getCurrentPanel()).height() -76;
	$("#fixTable",navTab.getCurrentPanel()).fixTable({
		fixRow:2,//固定行数
        fixColumn: 2,//固定列数
        width:sdmrFixwidth,//显示宽度
        height:sdmrFixHeigh//显示高度
    });
	
	//查询带回
	function smrcSel(obj){
		var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
		var href = "qms/commonSelect/faultReasonSelect.do?groupName=SDMRCP";
		$(obj).attr("href",href+"&productType="+productType);
		
	}
	function smrcMeshSel(obj){
		var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
		var href = "qms/commonSelect/faultReasonSelect.do?groupName=SDMRCMP";
		$(obj).attr("href",href+"&productType="+productType);
	}
	//故障大类选择
	function monRepecentftSel(obj){
		var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
		var href = "qms/commonSelect/faultTypeSelect.do?groupName=SDMRCP_TYPE";
		$(obj).attr("href",href+"&productType="+productType);
	}
	
</script>