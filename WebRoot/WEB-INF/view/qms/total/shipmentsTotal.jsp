<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	function s_checkData(){
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		if(endTime<startTime){
			alertMsg.info("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("");
			return false;
		}
		$("#shipments").submit();
	}
	//导出excel
	function shipTotalExcel(url){
		var myForm = document.createElement("form");
		myForm.action= url;
		myForm.method="post"; 
		myForm.target="noexistForm"; 
		var objs = $("#shipments input",navTab.getCurrentPanel());
		var objs_select = $("#shipments select",navTab.getCurrentPanel());	
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
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		if(endTime<startTime){
			alertMsg.info("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("");
			return false;
		}
		document.body.appendChild(myForm);
		myForm.submit();
	}
</script>
<div class="pageHeader">
	<form id="shipments" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/downShipTotal/shipmentsTotal.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					机型类别： 
				</td>
				<td>
					<select name="productType" id="s_productType">
					
						<option value="">所有</option>
						<c:forEach items="${typelist}" var="t">
							<option value="${t.machineType}">${t.machineType}</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
						$("#s_productType").val("${vo.productType}");
					</script>
				</td>				
				<td>
					发货月份：
				</td>
				<td>
				<input type="text" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" id="startTime" readonly="true" name="startTime" value="${vo.startTime}" size="8"/>&nbsp;至&nbsp;<input type="text" placeholder="请输入日期" id="endTime" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" name="endTime" value="${vo.endTime}" size="8"/>
					
				</td>
				<td>
					型号：
				</td>
				<td>
				<input type="text" name="partType" value="${vo.partType}"/>
					
				</td>
				<td>
					区域：
				</td>
				<td>
				<input type="text" name="region" value="${vo.region}"/>
					
				</td>
			</tr>
			<tr>	
				<td>
					产线编号：
				</td>
				<td>
				<input type="text" name="productlineNumber" value="${vo.productlineNumber}"/>
					
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="s_checkData();">查找</button></div></div>
				</td>
				<td>
					<a class="button"  onclick="shipTotalExcel('base/downShipTotal/shipTotalExcelOutput.do');" title="确定导出信息？"><span>导出EXCEL</span></a>					
				</td>	
				<td>
					发货数量总数：<font color="red">${sum}</font>	
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th>机型类别</th>
				<th>发货月份</th>	
				<th>产品系列</th>					
				<th>型号</th>
				<th>区域</th>
				<th>产线编号</th>
				<th>发货数量</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>					
					<td>${o.productType}</td>
					<td>${o.statisticsMonth}</td>
					<td>${o.productFamily}</td>
					<td>${o.partType}</td>
					<td>${o.region}</td>
					<td>${o.productlineNumber}</td>
					<td>${o.shipCount}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>
