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
	$(function(){
		var v=${result};
		if(v=="0"){
			showlinecolumn("onlinechar", ${info1},"1");
			showlinecolumn("onlinechar2", ${info2},"2");
			showlinecolumn("onlinechar3", ${info3},"3");
			showlinecolumn("onlinechar4", ${info4},"4");
			showlinecolumn("onlinechar5", ${info5},"5");
			showlinecolumn2("onlinechar6", ${info6});
			showlinecolumn2("onlinechar7", ${info7});
			showlinecolumn2("onlinechar8", ${info8});
		}else{
			alert("查询出错，请联系管理员！");
		}
	})
	function eqSelctCallBack(obj){
		var time=obj.idend;
		$("#numberstxt_"+time).val(obj.s);
		$("#numberstxtstr_"+time).val(obj.v);
	}
	function eqSelctCallBack2(obj){
		var time=obj.idend;
		$("#partnumbertxt_"+time).val(obj.s);
	}
</script>
<div class="pageHeader" style="position:static">
	<form id="onlineone" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/quality/onlineHomePage.do" method="post">
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
					<jsp:param value="1" name="lotTime"/>
					<jsp:param value="1" name="columnNum"/>
					<jsp:param value="0" name="result"/>
					<jsp:param value="1" name="analysisType"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
				</jsp:include>	
				<tr>
					<td colspan="9">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
					</td>
				</tr>
			</table>
		</div>
		<input id="hidbs" type="hidden" value="0"/><!-- 用于处理dialog --> 
		<input id="hiddenId" name="hiddenId" type="hidden"/><!-- 图序号 -->
		<input id="strone" name="strone" type="hidden"/><!-- 隐藏值 -->
		<input id="strtwo" name="strtwo" type="hidden"/><!-- 显示值 -->
		<input id="spareparts" name="spareparts" type="hidden"/><!-- 不良零部件编号 -->
		<input id="badphenomenon" name="badphenomenon" type="hidden"/><!-- 不良现象 -->
	</form>
</div>
<div style="height:83%; overflow:auto;">
	<div>
		<div class="pageContent" style="width: 50%;float: left;">
			<div id="onlinechar" class="singleChartDiv" style="height: 300px;"></div>	
		</div>
		<div class="pageContent" style="width: 50%;float: left;">
			<div id="onlinechar2" class="singleChartDiv" style="height: 300px;"></div>	
		</div>
	</div>
	<div>
		<div class="pageContent" style="width: 50%;float: left;">
			<div id="onlinechar3" class="singleChartDiv" style="height: 300px;"></div>	
		</div>
		<div class="pageContent" style="width: 50%;float: left;">
			<div id="onlinechar4" class="singleChartDiv" style="height: 300px;"></div>	
		</div>
	</div>
	<div>
		<div class="pageContent" style="width: 50%;float: left;">
			<div id="onlinechar5" class="singleChartDiv" style="height: 300px;"></div>	
		</div>
		<div class="pageContent" style="width: 50%;float: left;">
			<div id="onlinechar6" class="singleChartDiv" style="height: 300px;"></div>	
		</div>
	</div>
	<div>
		<div class="pageContent" style="width: 50%;float: left;">
			<div id="onlinechar7" class="singleChartDiv" style="height: 300px;"></div>	
		</div>
		<div class="pageContent" style="width: 50%;float: left;">
			<div id="onlinechar8" class="singleChartDiv" style="height: 300px;"></div>	
		</div>
	</div>
</div>
