<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.tablestyle{
		 display:block; 
		 overflow:hidden; 
	}
	.tablestyle td{
		padding-right:20px; 
		white-space:nowrap; 
		height:25px
	}
</style>
<script type="text/javascript">
	function getOnline(){
		var url = "<c:url value='base/online/onlinesetvo.do'/>";
		var reqData = $("#onlinechardata",navTab.getCurrentPanel()).serializeArray()
		$.ajax({
	    	type: 'POST',
	        url: url,
	        data:reqData,
	        dataType:"json",
	        cache: false,
	        global:true,
	        success: function(data){
	        	var printUrl = "<c:url value='base/online/onlinebatch.do'/>?jsonStr="+ encodeURIComponent(JSON.stringify(data.obj));
	        	navTab.openTab("onlinepoorcharNav", printUrl, { title:"明细-在线批次", fresh:true});
	        },
	    });
	}
	function findchar(buttonId){
		var factory=$("#factory_${id_end}",navTab.getCurrentPanel()).val();
		/* if(factory==null || factory==undefined ||factory==""){
			alertMsg.info("请选择工厂！");
			return;
		} */
		var productType=$("#productType_${id_end}",navTab.getCurrentPanel()).val();
		/* if(productType==null || productType==undefined ||productType==""){
			alertMsg.info("请选择机型！");
			return;
		} */
		var dateT=$("#dateT_${id_end}",navTab.getCurrentPanel()).val();
		if(dateT==null || dateT==undefined ||dateT==""){
			alertMsg.info("请输入第一个日期！");
			//$("#dateT_${id_end}",navTab.getCurrentPanel()).click();
			return;
		}
		var dateT_T=$("#dateT_T_${id_end}",navTab.getCurrentPanel()).val();
		if((dateT_T==null || dateT_T==undefined ||dateT_T=="") && dateT!=""){
			alertMsg.info("请输入第二个日期！");
			//$("#dateT_${id_end}",navTab.getCurrentPanel()).click();
			return;
		}
		var partname=$("#supPartLookup${id_end}_partName",navTab.getCurrentPanel()).val();
		var accountname=$("#supplierLookup${id_end}_supplierName",navTab.getCurrentPanel()).val();
		/*if(partname!="" && accountname!=""){
			alertMsg.info("供应商与物料不能同时指定！")
			return;
		}*/
		/* if(buttonId==4 && partname!=""){
			alertMsg.info("供应商不良趋势控制图物料只能为空！")
			return;
		}
		if(buttonId==5 && accountname!=""){
			alertMsg.info("零部件不良趋势控制图物料只能为空！")
			return;
		} */
		var isvis = $("input[name=partVersion]:checked",navTab.getCurrentPanel()).val();
	/* 	if(isvis =='否' && (partname==null || partname == undefined || partname=='')){
			alertMsg.info("物料不带版本时,需选择物料！")
			return;
		} */
		var url = "<c:url value='base/online/findChartonclick.do'/>";
		$("#buttonId",navTab.getCurrentPanel()).val(buttonId);
		var reqData = $("#onlinechardata",navTab.getCurrentPanel()).serialize();
		$.ajax({
	    	type: 'POST',
	        url: url,
	        data:reqData,
	        dataType:"json",
	        cache: false,
	        global:true,
	        success: function(data){
	        	jinitHeight("");
	        	if(data.result!=-1){
	        		var v=data.result;
	        		if(v<=5){
	        			showlinecolumn("onlinechert", data.info,data.result,1);
	        		}else {
	        			if(v==9 || v==10 || v==14 || v==15){
	        				showlinecolumn5("onlinechert", data.info,data.result);
	        			}else{
	        				showlinecolumn4("onlinechert", data.info,data.result);
	        			}
	        		}
	        		var buttonName;
        			if(v==1|| v==2|| v==3 || v==6 || v==7|| v==8 || v==11 || v==12 || v==13){
        				buttonName ="排列图";
        			}else if(v==4|| v==5 || v==9 || 10|| v==14 || v==15){
        				buttonName ="趋势图";
        			}else{
        				buttonName ="";
        			}
        			$("<div style='z-index:900;position: absolute;top:5px;left:85%;'><button id='mytest' type='button' style='background: transparent;color: #A0A0A0;border: 0px;cursor:pointer;' onclick='getDataSourceByMenuName(&quot;物料质量统计分析&quot;, &quot;在线部分&quot;, &quot;物料在线统计分析&quot;, &quot;"+buttonName+"&quot;);'>数据来源</button></div>").appendTo($("#onlinechert",navTab.getCurrentPanel()));
	        	}else{
	        		alert("查询出错！");
	        	}
	        	
	        },
	    });
		empty();
	}
	//ERP退次
	function onlineERPnum(){
		var url = "<c:url value='base/online/onlinesetvo.do'/>";
		var reqData = $("#onlinechardata",navTab.getCurrentPanel()).serializeArray()
		$.ajax({
	    	type: 'POST',
	        url: url,
	        data:reqData,
	        dataType:"json",
	        cache: false,
	        global:true,
	        success: function(data){
	        	var printUrl = "<c:url value='base/online/onlineERPnum.do'/>?jsonStr="+ encodeURIComponent(JSON.stringify(data.obj));
	        	navTab.openTab("onlineERPnumcharNav", printUrl, { title:"明细-ERP组装退次", fresh:true});
	        },
	    });
		
	}
	//MES退次
	function onlineMESnum(){
		var url = "<c:url value='base/online/onlinesetvo.do'/>";
		var reqData = $("#onlinechardata",navTab.getCurrentPanel()).serializeArray()
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
		var reqData = $("#onlinechardata",navTab.getCurrentPanel()).serializeArray();
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
	function empty(){
		//$("#onlinechardata tr td[id='yc'] input[type='hidden']",navTab.getCurrentPanel()).not("#bs").val("")
		//$("#buttonId",navTab.getCurrentPanel()).val("");
		$("#strone",navTab.getCurrentPanel()).val("");
		$("#strtwo",navTab.getCurrentPanel()).val("");
		$("#spareparts",navTab.getCurrentPanel()).val("");
		$("#spareparts2",navTab.getCurrentPanel()).val("");
		$("#badphenomenon",navTab.getCurrentPanel()).val("");
		$("#trendTime",navTab.getCurrentPanel()).val("");
		$("#distinction",navTab.getCurrentPanel()).val("");//自动跳转标记
	}
</script>
<div class="pageHeader" style="position:static">
	<form id="onlinechardata" onsubmit="return navTabSearch(this);" rel="pagerForm" action="" method="post">
		<div class="searchBar pageFormContent">
			<table class="searchContent">		  
				<jsp:include page="../select/part_supplier2.jsp" flush="true">
					<jsp:param value="doubleLine" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="productType"/>
					<jsp:param value="1" name="supplier"/>
					<jsp:param value="0" name="partType"/>
					<jsp:param value="1" name="partClass"/>
					<jsp:param value="1" name="partName"/>
					<jsp:param value="1" name="partVersion"/>
					<jsp:param value="1" name="isNew"/>
					<jsp:param value="1" name="selectDate"/>
					<jsp:param value="0" name="period"/>
					<jsp:param value="0" name="lotTime"/>
					<jsp:param value="1" name="columnNum"/>
					<jsp:param value="0" name="result"/>
					<jsp:param value="1" name="analysisType"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="1" name="iscrux"/>
					<jsp:param value="1" name="replaceType"/>
					<jsp:param value="1" name="sordType"/>
				</jsp:include>	
				<tr>
					<td colspan="9" id="yc">
						<input id="buttonId" type="hidden" name="buttonId"/>
						<input id="distinction" name="distinction" type="hidden"/><!-- 区分跳转查询（1）和手动查询（0） -->
						<input id="strone" name="strone" type="hidden"/><!-- 供应商-隐藏值 -->
						<input id="strtwo" name="strtwo" type="hidden"/><!-- 供应商-显示值 -->
						<input id="spareparts" name="spareparts" type="hidden"/><!-- 零部件-不良零部件编号 -->
						<input id="spareparts2" name="spareparts2" type="hidden"/><!-- 零部件-不良零部件信息 隐藏值 -->
						<input id="badphenomenon" name="badphenomenon" type="hidden"/><!-- 不良现象 -->
						<input id="trendTime" name="trendTime" type="hidden"/><!-- 趋势图时间 -->
						<a onclick="getOnline()" id="batchshow" rel="" title="详情"></a>
						<a onclick="onlineERPnum()" id="onlineERP" rel="" title="详情"></a>
						<a onclick="onlineMESnum()" id="onlineMES" rel="" title="详情"></a>
						<a onclick="onlinenum()" id="onlinenum" rel="" title="详情"></a>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="findchar(1)">供应商不良排列图</button></div></div>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="findchar(2)">零部件不良排列图</button></div></div>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="findchar(3)">不良现象排列图</button></div></div>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="findchar(4)">供应商不良趋势控制图</button></div></div>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="findchar(5)">零部件不良趋势控制图</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div id="char" class="pageContent" style="width: 100%;text-align: center;">
		<div id="onlinechert" style="width: 90%" class="singleChartDiv"></div>	
	</div>
</div>
