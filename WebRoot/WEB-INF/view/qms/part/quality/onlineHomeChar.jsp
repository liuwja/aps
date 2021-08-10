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
		var url = "<c:url value='base/quality/onlinesetvo.do'/>";
		var reqData = $("#onlinechardata").serializeArray()
		$.ajax({
	    	type: 'POST',
	        url: url,
	        data:reqData,
	        dataType:"json",
	        cache: false,
	        global:true,
	        success: function(data){
	        	var printUrl = "<c:url value='base/quality/onlinebatch.do'/>?jsonStr="+ encodeURIComponent(JSON.stringify(data.obj));
	    		//$.pdialog.open(printUrl, "dialogId", "详情", {width:1200,height:600,mask:true});
	        	navTab.openTab("onlinepoorcharNav", printUrl, { title:"明细-在线批次", fresh:true});
	        },
	    });
	}
	function botonclick(bs){
		var value = $("input[name=analysisType]:checked",navTab.getCurrentPanel()).val();//0：不良批次数，1：不良数/率
		/*if(value == null || value == undefined || value == ''){
			alertMsg.info("请选择统计方式！");
			return;
		}*/
		var isvis = $("input[name=partVersion]:checked",navTab.getCurrentPanel()).val();
		var partname=$("#supPartLookup${id_end}_partName",navTab.getCurrentPanel()).val();
		if(isvis =='否' && (partname==null || partname == undefined || partname=='')){
			alertMsg.info("物料不带版本时,需选择物料！")
			return;
		}
		var char_id="";
		if(value=="0"){//不良批次数
			if(bs=="1"){//供应商不良排列图
				$("#bs",navTab.getCurrentPanel()).val("1");
				char_id="onlinechert1";
			}
			if(bs=="2"){//零部件不良排列图
				$("#bs",navTab.getCurrentPanel()).val("2");
				char_id="onlinechert2";
			}
			if(bs=="3"){//不良现象不良排列图
				$("#bs",navTab.getCurrentPanel()).val("3");
				char_id="onlinechert3";
			}
			if(bs=="4"){//供应商不良趋势图
				$("#bs",navTab.getCurrentPanel()).val("4");
				char_id="onlinechert4";
			}
			if(bs=="5"){//零部件不良趋势图
				$("#bs",navTab.getCurrentPanel()).val("5");
				char_id="onlinechert5";
			}
		}
		if(value=="1"){//不良数/率
			var iscrux = $("#iscrux_${id_end}","#onlinechardata").val();//0:为关键件，1：为非关键件
			if(iscrux==0){
				if(bs=="1"){//关键件供应商不良数/率排列图
					$("#bs",navTab.getCurrentPanel()).val("6");
					char_id="onlinechert6";
				}
				if(bs=="2"){//关键件零部件不良数/率排列图
					$("#bs",navTab.getCurrentPanel()).val("7");
					char_id="onlinechert7";
				}
				if(bs=="3"){//关键件不良现象不良数/率排列图
					$("#bs",navTab.getCurrentPanel()).val("8");
					char_id="onlinechert8";
				}
				if(bs=="4"){//关键件供应商不良数/率趋势图
					$("#bs",navTab.getCurrentPanel()).val("9");
					char_id="onlinechert9";
				}
				if(bs=="5"){//关键件零部件不良数/率趋势图
					$("#bs",navTab.getCurrentPanel()).val("10");
					char_id="onlinechert10";
				}	
			}
			if(iscrux==1){
				if(bs=="1"){//非关键件供应商不良数/率排列图
					$("#bs",navTab.getCurrentPanel()).val("11");
					char_id="onlinechert11";
				}
				if(bs=="2"){//非关键件零部件不良数/率排列图
					$("#bs",navTab.getCurrentPanel()).val("12");
					char_id="onlinechert12";
				}
				if(bs=="3"){//非关键件不良现象不良数/率排列图
					$("#bs",navTab.getCurrentPanel()).val("13");
					char_id="onlinechert13";
				}
				if(bs=="4"){//非关键件供应商不良数/率趋势图
					$("#bs",navTab.getCurrentPanel()).val("14");
					char_id="onlinechert14";
				}
				if(bs=="5"){//非关键件零部件不良数/率趋势图
					$("#bs",navTab.getCurrentPanel()).val("15");
					char_id="onlinechert15";
				}	
			}
		}
		var v=$("#bs",navTab.getCurrentPanel()).val();
		showOrhidden(v);
		if(char_id!=""){
			findchar(char_id,v);
		}
	}
	function findchar(char_id,v){
		var url = "<c:url value='base/quality/poor.do'/>";
		var reqData = $("#onlinechardata").serialize();
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
	        		if(v<6){
	        			showlinecolumn(char_id, data.info,v,1);
	        		}else{
	        			if(v==9 || v==10 || v==14 || v==15){
	        				showlinecolumn3(char_id, data.info,v);
	        			}else{
	        				showlinecolumn2(char_id, data.info,v);
	        			}
	        		}
	        	}else{
	        		alert("查询出错！");
	        	}
	        	
	        },
	    });
		empty();
	}
	function showOrhidden(obj){
		var arr=new Array("char1","char2","char3","char4","char5","char6","char7","char8","char9","char10","char11","char12","char13","char14","char15")
		$.each(arr,function(i,v) {
            if(obj==(i+1)){
            	$("#"+v).css("display", "block")
            }else{
            	$("#"+v).css("display", "none")
            }
        });
	}
	
	function onlineERPnum(){
		var url = "<c:url value='base/quality/onlinesetvo.do'/>";
		var reqData = $("#onlinechardata").serializeArray()
		$.ajax({
	    	type: 'POST',
	        url: url,
	        data:reqData,
	        dataType:"json",
	        cache: false,
	        global:true,
	        success: function(data){
	        	var printUrl = "<c:url value='base/quality/onlineERPnum.do'/>?jsonStr="+ encodeURIComponent(JSON.stringify(data.obj));
	        	navTab.openTab("onlineERPnumcharNav", printUrl, { title:"明细-ERP组装退次", fresh:true});　
	        },
	    });
		
	}
	
	function onlinenum(){
		var url = "<c:url value='base/quality/onlinesetvo.do'/>";
		var reqData = $("#onlinechardata").serializeArray()
		$.ajax({
	    	type: 'POST',
	        url: url,
	        data:reqData,
	        dataType:"json",
	        cache: false,
	        global:true,
	        success: function(data){
	        	var printUrl = "<c:url value='base/quality/onlinenum.do'/>?jsonStr="+ encodeURIComponent(JSON.stringify(data.obj));
	    		navTab.openTab("onlinenumcharNav", printUrl, { title:"明细-在线退次及更换", fresh:true} );　
	        },
	    });
	}
	function empty(){
		$("#onlinechardata tr td[id='yc'] input[type='hidden']").not("#bs").val("")
	}
</script>
<div class="pageHeader" style="position:static">
	<form id="onlinechardata" onsubmit="return navTabSearch(this);" rel="pagerForm" action="" method="post">
		<div class="searchBar pageFormContent">
			<table class="searchContent">		  
				<jsp:include page="../select/part_supplier.jsp" flush="true">
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
				</jsp:include>	
				<tr>
					<td colspan="9" id="yc">
						<input id="bs" type="hidden" name="bs"/><!-- 图序号 -->
						<input id="hidbs" type="hidden" value="0"/><!-- 用于处理dialog --> 
						<input id="hiddenId" name="hiddenId" type="hidden"/><!-- 图序号 -->
						<input id="strone" name="strone" type="hidden"/><!-- 隐藏值 -->
						<input id="strtwo" name="strtwo" type="hidden"/><!-- 显示值 -->
						<input id="spareparts" name="spareparts" type="hidden"/><!-- 不良零部件编号 -->
						<input id="spareparts2" name="spareparts2" type="hidden"/><!-- 不良零部件信息 隐藏值 -->
						<input id="badphenomenon" name="badphenomenon" type="hidden"/><!-- 不良现象 -->
						<input id="trendTime" name="trendTime" type="hidden"/><!-- 趋势图时间 -->
						<input type="hidden"><a onclick="getOnline()" id="batchshow" rel="" title="详情"></a></input>
						<a onclick="onlineERPnum()" id="onlineERP" rel="" title="详情"></a>
						<a onclick="onlinenum()" id="onlinenum" rel="" title="详情"></a>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="botonclick(1)">供应商不良排列图</button></div></div>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="botonclick(2)">零部件不良排列图</button></div></div>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="botonclick(3)">不良现象排列图</button></div></div>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="botonclick(4)">供应商不良趋势控制图</button></div></div>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="botonclick(5)">零部件不良趋势控制图</button></div></div>
					</td>
				</tr>
			</table>
		</div>
		<div style="display: none"><input id="onlinedata" type="submit"/></div>
	</form>
</div>
<div class="pageContent">
	<!-- 供应商不良批次数排列图 -->
	<div id="char1" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert1" class="singleChartDiv"></div>	
	</div>
	<!-- 零部件不良批次数排列图 -->
	<div id="char2" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert2" class="singleChartDiv""></div>	
	</div>
	<!-- 不良现象不良批次数排列图 -->
	<div id="char3" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert3" class="singleChartDiv"></div>	
	</div>
	<!-- 供应商不良批次数趋势图 -->
	<div id="char4" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert4" class="singleChartDiv""></div>	
	</div>
	<!-- 零部件不良批次数趋势图 -->
	<div id="char5" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert5" class="singleChartDiv""></div>	
	</div>
	<div id="char6" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert6" class="singleChartDiv"></div>	
	</div>
	
	<div id="char7" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert7" class="singleChartDiv"></div>	
	</div>
	<div id="char8" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert8" class="singleChartDiv"></div>	
	</div>
	<div id="char9" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert9" class="singleChartDiv"></div>	
	</div>
	<div id="char10" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert10" class="singleChartDiv"></div>	
	</div>
	<div id="char11" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert11" class="singleChartDiv"></div>	
	</div>
	
	<div id="char12" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert12" class="singleChartDiv"></div>	
	</div>
	<div id="char13" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert13" class="singleChartDiv"></div>	
	</div>
	<div id="char14" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert14" class="singleChartDiv"></div>	
	</div>
	<div id="char15" class="pageContent" style="width: 100%;text-align: center;display: none;">
		<div id="onlinechert15" class="singleChartDiv"></div>	
	</div>
</div>
