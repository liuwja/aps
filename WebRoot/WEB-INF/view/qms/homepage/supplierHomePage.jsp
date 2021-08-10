<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<script>
jQuery(document).ready(function() {
	getHomeSupContentHeight('suppierHomePageContent');
	loadSupplierHomePageChar();
});

function loadSupplierHomePageChar() {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val(); //机型类别
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val(); //截止日期
	var selectDate = $("#selectDate", navTab.getCurrentPanel()).val();
	var supplierNumber = $("#SUPPIER_HOME_PAGE_supplierCode", navTab.getCurrentPanel()).val(); //供应商
	if(selectDate ==""){
		alertMsg.info("请选择时间维度！");
		return ;
	}
	if(selectDate !=""){
		if(queryMonth == ''){
    		alertMsg.info("请选择时间！");
    		return ;
    	}
	}
	getMarketSupplierPartDefect(productType, selectDate, queryMonth, supplierNumber);
	getTestInstenceChart(productType,selectDate,queryMonth,supplierNumber);
	getOnlinechar(2,productType,selectDate,queryMonth,supplierNumber,0);
	getOnlinechar(2,productType,selectDate,queryMonth,supplierNumber,1);
}
/**
 * 市场
 */
function getMarketSupplierPartDefect(productType, selectDate, queryMonth, supplierNumber) {
	var url = "<c:url value='quality/marketPart/marketDefect.do'/>";
	var index = 2;
	var isConsumed = 1;
	var hasVersion = 2;
	var xCount = 10;
	var sortType = "不良数降序";
	var jsonData = {productType:productType, selectDate:selectDate, queryMonth:queryMonth, supplierNumber:supplierNumber, 
			title:index, isConsumed:isConsumed, hasVersion:hasVersion, xCount:xCount, sortType:sortType};
	$.post(url, jsonData, function(data) {
		console.log("supplierNumber = " + supplierNumber);
		setMarketDefectData(index,isConsumed,selectDate,data.chartsInfo);
		showMarketDefectCharts("marketSupplierPartDefect", data.chartsInfo, index, isConsumed);
	});
}

function clearAll_SUPPIER_HOME_PAGE() {
	$("#SUPPIER_HOME_PAGE_data", navTab.getCurrentPanel()).val("");
	$("#SUPPIER_HOME_PAGE_supplierCode", navTab.getCurrentPanel()).val("");
	$("#SUPPIER_HOME_PAGE_supplierName", navTab.getCurrentPanel()).val("");
}
/**
 * 进料
 */
function getTestInstenceChart(productType,dateType,dateT,supplierCode){
	jinitHeight("");
	var type = "2";
	var analysisType = '0';
	var sordType = '不良批/数';
	var columnNum = '15';
	if(dateType=='年'){
		dateT = dateT.substring(0,4);
	}
	
	var url = "<c:url value='quality/testInstance/loadFeedDetail.do' />";
	$.post(url,{type:type,productType:productType,dateType:dateType,dateT:dateT,columnNum:columnNum,analysisType:analysisType,supplierCode:supplierCode,sordType:sordType,partVersion:"否"},function(data){
        	if(data.result==0){
        		loadSupTestInstenceChart(data,type);
    		}
    		if(data.result==-1){
    			alert("查询出错，请联系管理员！");
    		}
	});

}
function loadSupTestInstenceChart(resulMap,type){
	    var analysisT= "0"; 
	    var colorList = ['#3399FF','#C1232B','#333366','#333366','#C1232B'];
		var feedechart;
		var feedoption;
		var comList = resulMap.comList;
	    var	totalList = resulMap.totalList;
	    var codeMap = resulMap.codeMap;
		feedoption = resulMap.option;
	
	    var tooltip_r = {
	    		trigger: 'axis',
	    		formatter: function(params) {
			            var sname = params[0].name ;
			            var svalue ="";
			            var lab = "";
			            if(analysisT ==0){
			            	lab ="(批)";
			            }else{
			            	lab ="(个)";
			            }
			            for(var i in params){
			            	  var pvalue = params[i].value;
			            	  if(pvalue==null){
			            		  pvalue = 0;
			            	  }
			            	  if(i == 0){
			            		  svalue += params[i].seriesName + ' : ' + pvalue+lab + '<br/>';
			            		  if(sname != '其他'){
			            			  var tovalue = totalList[sname];
			            			  if(tovalue == null){
			            				  tovalue = 0;
			            			  }
			            		      svalue += "到货总数：" +tovalue + lab +'<br/>';
			            		      if(codeMap[sname] != null){
			            		    	  sname = codeMap[sname] + "-"+ sname;
			            		      }
			            		  }
			            	  }else{
		                          svalue += params[i].seriesName + ' : ' + pvalue+'%' + '<br/>';
			            	  }
			            	      
			            	}
			        
			            return sname +' <br/>' + svalue ;
			        },
	      };
	      feedoption.tooltip = tooltip_r; 
	      feedoption.color = colorList;
	      
	      feedechart = echarts.init(document.getElementById('testInstanceSupplierPartDefect'));
	      feedechart.setOption(feedoption);
	  	
	      feedechart.on('click', function (e) {
				var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val(); //机型类别
				var dateT = $("#queryMonth", navTab.getCurrentPanel()).val(); //截止日期
				var dateType = $("#selectDate", navTab.getCurrentPanel()).val();
				var supplierCode = $("#SUPPIER_HOME_PAGE_supplierCode", navTab.getCurrentPanel()).val(); //供应商
				var partNumber = comList[e.dataIndex];
				navTab.openTab("testInstentDetailTab", "quality/testInstance/testInstentDetail.do", {title:"进料明细数据报表",fresh:true,data:{productType:productType,dateType:dateType,dateT:dateT,supplierCode:supplierCode,partNumber:partNumber,partVersion:"否",distinction:1}});
	      	});
}
/**
 * 调整页面高度
 */
function getHomeSupContentHeight(obj){
	 var contentHeight = $("div.navTab-panel ").innerHeight();
    var searchBarHeight = $("#supplierHomePageForm",navTab.getCurrentPanel()).innerHeight();
    var height = parseInt(contentHeight)- parseInt(searchBarHeight);
    if(obj == 'childContent'){
   	 var contentWidth = ($("div.navTab-panel ").innerWidth())/2;
   	 if(contentWidth < 400){
   		 contentWidth = 400;
   		 $("#"+obj,navTab.getCurrentPanel()).css({"height":height,"width" : contentWidth});
   	 }
   	 $("#"+obj,navTab.getCurrentPanel()).css({"height":height});
    }else{
       $("#"+obj,navTab.getCurrentPanel()).css({"height":height});
    }
}
/**
 * 在线
 */
function getOnlinechar(buttonId,productType,dateType,dateT,supplierCode,replaceType){
	$("#buttonId",navTab.getCurrentPanel()).val(buttonId);
	var isvis = "否";
	if(dateType=='年'){
		dateT = dateT.substring(0,4);
	}
	var dateT_T= dateT;
	var chartId ;
	//更换
	if(replaceType == 0){
		chartId ="onlineSupplierPartDefectByReplace";
	}else{
		chartId ="onlineSupplierPartDefect";
	}
	var url = "<c:url value='base/online/findChartonclick.do'/>";
	$.ajax({
    	type: 'POST',
        url: url,
        data:{analysisType:"1",buttonId:buttonId,productType:productType,dateType:dateType,dateT:dateT,dateT_T:dateT_T,supplierCode:supplierCode,isvis:isvis,partVersion:"否",replaceType:replaceType,columnNum:"15"},
        dataType:"json",
        cache: false,
        global:true,
        success: function(data){
        	jinitHeight("");
        	if(data.result!=-1){
        		var v=data.result;
        		if(v<=5){
        			showlinecolumn(chartId, data.info,data.result,1);
        		}else {
        			if(v==9 || v==10 || v==14 || v==15){
        				showlinecolumn5(chartId, data.info,data.result);
        			}else{
        				showlinecolumn4(chartId, data.info,data.result);
        			}
        		}
        	}else{
        		alert("查询出错！");
        	}
        	
        },
    });
	supHomeEmpty();
}
//MES退次
function onlineMESnum(){
	var url = "<c:url value='base/online/onlinesetvo.do'/>";
	var dateType = $("#selectDate", navTab.getCurrentPanel()).val();
	var dateT = $("#queryMonth", navTab.getCurrentPanel()).val(); //截止日期
	if(dateType=='年'){
		dateT = dateT.substring(0,4);
	}
	var reqData = $("#supplierHomePageForm",navTab.getCurrentPanel()).serializeArray();
	for(var i in reqData){
		if(reqData[i].name == "dateType" && reqData[i].value =="年"){
			for(var k in reqData){
				if(reqData[k].name=="dateT"){
					reqData[k].value = reqData[k].value.substring(0,4);
				}
			}
		}
	}
	reqData.push({name:"partVersion",value:"否"});
	reqData.push({name:"replaceType",value:"0"});
	reqData.push({name:"dateT_T",value:dateT});
	$.ajax({
    	type: 'POST',
        url: url,
        data:reqData,
        dataType:"json",
        cache: false,
        global:true,
        success: function(data){
        	var printUrl = "<c:url value='base/online/onlinenumReturn.do'/>?jsonStr="+ encodeURIComponent(JSON.stringify(data.obj));
        	navTab.openTab("onlinenumReturncharNav", printUrl, { title:"明细-MES在线退次", fresh:true});
        },
    });
	
}
//mes在线更换
function onlinenum(){
	var url = "<c:url value='base/online/onlinesetvo.do'/>";
	var dateType = $("#selectDate", navTab.getCurrentPanel()).val();
	var dateT = $("#queryMonth", navTab.getCurrentPanel()).val(); //截止日期
	if(dateType=='年'){
		dateT = dateT.substring(0,4);
	}
	var reqData = $("#supplierHomePageForm",navTab.getCurrentPanel()).serializeArray();
	for(var i in reqData){
		if(reqData[i].name == "dateType" && reqData[i].value =="年"){
			for(var k in reqData){
				if(reqData[k].name=="dateT"){
					reqData[k].value = reqData[k].value.substring(0,4);
				}
			}
		}
	}
	reqData.push({name:"partVersion",value:"否"});
	reqData.push({name:"replaceType",value:"0"});
	reqData.push({name:"dateT_T",value:dateT});
	$.ajax({
    	type: 'POST',
        url: url,
        data:reqData,
        dataType:"json",
        cache: false,
        global:true,
        success: function(data){
        	var printUrl = "<c:url value='base/online/onlinenum.do'/>?jsonStr="+ encodeURIComponent(JSON.stringify(data.obj));
    		navTab.openTab("onlinenumcharNav", printUrl, { title:"明细-MES在线更换", fresh:true} );
        },
    });
}
function supHomeEmpty(){
	$("#strone",navTab.getCurrentPanel()).val("");
	$("#strtwo",navTab.getCurrentPanel()).val("");
	$("#spareparts",navTab.getCurrentPanel()).val("");
	$("#spareparts2",navTab.getCurrentPanel()).val("");
	$("#badphenomenon",navTab.getCurrentPanel()).val("");
	$("#distinction",navTab.getCurrentPanel()).val("");//自动跳转标记
}
</script>
<div class="pageHeader" style="position:static">
	<form id="supplierHomePageForm" onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<th>机型类别：
					    <input id="buttonId" type="hidden" name="buttonId"/>
					    <input type="hidden" name="analysisType" value="1"/>
						<input id="distinction" name="distinction" type="hidden" value="1"/><!-- 区分跳转查询（1）和手动查询（0） -->
						<input id="strone" name="strone" type="hidden"/><!-- 供应商-隐藏值 -->
						<input id="strtwo" name="strtwo" type="hidden"/><!-- 供应商-显示值 -->
						<input id="spareparts" name="spareparts" type="hidden"/><!-- 零部件-不良零部件编号 -->
						<input id="spareparts2" name="spareparts2" type="hidden"/><!-- 零部件-不良零部件信息 隐藏值 -->
						<input id="badphenomenon" name="badphenomenon" type="hidden"/><!-- 不良现象 -->
						<input id="trendTime" name="trendTime" type="hidden"/><!-- 趋势图时间 -->
						<a onclick="onlineMESnum()" id="onlineMES" rel="" title="详情"></a>
						<a onclick="onlinenum()" id="onlinenum" rel="" title="详情"></a>
					</th>
					<td>
						<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
						</select>
					</td>
					<th>供应商：</th>
					<td>
						<input type="hidden" id="SUPPIER_HOME_PAGE_data" name="supplierData" size="10" readonly="true" style="float: left;" value="${vo.supplierId}"/>
						<input type="hidden" id="SUPPIER_HOME_PAGE_supplierCode" name="supplierCode" size="10" readonly="true" style="float: left;" value="${vo.supplierNumber}"/>
	                    <input type="text" id="SUPPIER_HOME_PAGE_supplierName" name="supplier" size="10" readonly="true" style="float: left;" value="${vo.supplierListTxt}"/>
	                    <a id="btn" class="btnLook btn" href="quality/testInstance/supplierSelect.do?data=SUPPIER_HOME_PAGE" width=950 height=500 lookupGroup="SUPPIER_HOME_PAGE">供应商选择</a>  
						<a class="btnClear" href="javascript:void(0);" onclick="clearAll_SUPPIER_HOME_PAGE()" title="清空"></a>
					</td>
					<th>时间维度：</th>
					<td>
						<select id="selectDate" name="dateType">
	                       <option value="年" ${vo.selectDate eq '年' ? 'selected':''}>年</option>
	                       <option value="月" ${vo.selectDate eq '月' ? 'selected':''}>月</option>
	                     </select>
	                     <input type="text" id="queryMonth" name="dateT" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth}" readonly="readonly" size="8"/>
					</td>
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadSupplierHomePageChar()">查询</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div  class="pageContent"  style="overflow: auto; ">
    <div id="suppierHomePageContent" style="overflow: auto; ">
		 <div id="testInstanceSupplierPartDefect" style="width: 95%;height: 350px;"></div>
		 <div style="width: 100%;height: 10px;background: #F0F8FF"></div>
		 <div id="onlineSupplierPartDefectByReplace" style="width: 95%;height: 350px;"></div>
		 <div style="width: 100%;height: 10px;background: #F0F8FF"></div>
		 <div id="onlineSupplierPartDefect" style="width: 95%;height: 350px;"></div>
		 <div style="width: 100%;height: 10px;background: #F0F8FF"></div>
		 <div id="marketSupplierPartDefect"  style="width: 95%;height: 350px;"></div>
		 <div  style="width: 100%;height: 50px;"></div>
	 </div>
</div>