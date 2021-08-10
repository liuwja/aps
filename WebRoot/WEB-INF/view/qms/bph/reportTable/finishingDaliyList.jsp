<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	function stampingDaliy_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("");
			return false;
		}
		
		$("#finishingDaily").submit();
		
	}

		function exportExcel(url){    

			var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		    var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		    if(startTime==''){
		    	alert("请选择开始时间！");
		    	return;
		    }
		    if(endTime==''){
		    	alert("请选择结束时间！");
		    	return;
		    }
		    
			var myForm = document.createElement("form");
			myForm.action= url;
			myForm.method="post"; 
			myForm.target="noexistForm"; 
			var myInput;
			
			var objs = $("#finishingDaily input",navTab.getCurrentPanel());
			var objs2 = $("#finishingDaily select",navTab.getCurrentPanel());
			
			for(var i = 0 ; i< objs.length+objs2.length ; i++){
				var $obj = null;
				if(i>=objs.length){
					$obj = $(objs2[i-objs.length]);	
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
			document.body.appendChild(myForm);
			myForm.submit();
		}
</script>
<div class="pageHeader">
	<form id="finishingDaily" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/finishingDaliyList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="1" name="fgroup"/>
					<jsp:param value="0" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>			
			<tr>
				<td>
					产品型号： 
				</td>
				<td>
					<input type="text" name="typeS" value="${param.typeS}"/>
				</td>
				<td>
					产品名称： 
				</td>
				<td>
					<input type="text" name="productNameS" value="${param.productNameS}"/>
				</td>
				<td>
					不良现象： 
				</td>
				<td>
					<select name="defectS" id="defectS">
					
						<option value="">请选择</option>
						<option value=""></option>
					</select>
				</td>				
			</tr>
			<tr>
				<td>
					发生日期： 
				</td>
				<td>
					<input type="text" id="startTime" size="10" name="startTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>"/>
					至 <input type="text" id="endTime" size="10" name="endTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>"/>
				</td>

				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="stampingDaliy_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/stampingDaliyExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
				</td>
				</tr>	
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr>
			    <th width="3%">选择</th>
				<th width="8%">发生日期</th>
				<th width="5%">工厂</th>
				<th width="5%">发生车间</th>
				<th width="6%">班组</th>
				<th width="5%">班长</th>
				<th width="5%">检查员</th>
			<!-- 	<th>产品型号</th> -->
				<th width="6%">产品名称</th>
				<th width="6%">发现工序</th>
				<th width="5%">责任工序</th>
				<th width="6%">责任人</th>
				<th width="4%">总数</th>
				<th width="4%">检查数</th>
				<th width="4%">不良数量</th>
				<th width="4%">不良率</th>
				<th width="6%">不良现象</th>
				<th width="6%">不良原因</th>
				<th width="5%">处理方式</th>
				<th width="6%">记录人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td ><fmt:formatDate value="${o.dateT}" type="date" pattern="yyyy-MM-dd "/></td>
					<td>${o.factoryS}</td>
					<td>${o.areaS}</td>
					<td>${o.groupS}</td>
					<td >${o.groupLeaderS}</td>				
					<td >${o.checkManS}</td>
			<!-- 		<td>${o.typeS}</td> -->
					<td>${o.productNameS}</td>
					<td >${o.stepS}</td>
					<td >${o.dutyStepS}</td>
					<td >${o.dutyManS}</td>
					<td>${o.totalQtyI}</td>
					<td>${o.checkQtyI}</td>
					<td>${o.defectQtyI }</td>
					<td >${o.rateS}</td>
					<td >${o.defectNameS}</td>
					<td >${o.defectS}</td>
					<td >${o.methodS}</td>
					<td >${o.recordManS}</td>

				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

