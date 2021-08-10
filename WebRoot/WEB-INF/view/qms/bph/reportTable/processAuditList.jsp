<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	function processAudit_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("");
			return false;
		}
		
		$("#processAudit").submit();
		
	}
	function getProcessAuditArea() {
	var url = "<c:url value='commonSelected/getArea.do' />";
	$("#paarea", navTab.getCurrentPanel()).load(url,{factory: $("#pafactoryS", navTab.getCurrentPanel()).val()});   
	}
	
	function getProcessAuditGroup() {
	var url = "<c:url value='commonSelected/getShiftGroupByFactory.do' />";
	$("#pagroup", navTab.getCurrentPanel()).load(url,{factory: $("#pafactoryS", navTab.getCurrentPanel()).val()});   
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
		
		var objs = $("#processAudit input",navTab.getCurrentPanel());
		var objs2 = $("#processAudit select",navTab.getCurrentPanel());
		
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
	<form id="processAudit" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/processAuditList.do" method="post">
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
					审核发现不符合项： 
				</td>
				<td>
					<input type="text" name="defectS" value="${param.defectS}"/>
				</td>
				<td>
					责任人： 
				</td>
				<td>
					<input type="text" name="dutyS" size="15" value="${param.dutyS}" />
				</td>
				<td>
					跟进人： 
				</td>
				<td>
					<input type="text" name="followManS" size="15" value="${param.followManS}" />
				</td>
				<td>
					是否闭环： 
				</td>
				<td>
					<input type="text" name="isCloseS" size="15" value="${param.isCloseS}" />
				</td>			
			</tr>
			<tr>
				<td>
					发生日期： 
				</td>
				<td>
					<input type="text" id="startTime" name="startTime" size="10" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>"/>
					至 <input type="text" id="endTime" name="endTime" size="10" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>"/>
				</td>

				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="processAudit_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/processAuditExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
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
				<th width="6%">发生日期</th>
				<th  width="5%">工厂</th>
				<th  width="5%">发生车间</th>
				<th  width="8%">班组</th>
				<th  width="5%">班长</th>
				<th  width="6%">审核类型</th>
				<th  width="7%">审核依据</th>
				<th  width="13%">审核发现</th>
				<th width="7%">不符合项</th>
				<th  width="6%">审核员</th>
				<th  width="6%">责任人</th>
				<th  width="6%">跟进人</th>
				<th width="4%">是否闭环</th>
				<th  width="6%">记录人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td width="6%"><fmt:formatDate value="${o.checkDateT}" type="date" pattern="yyyy-MM-dd"/></td>
					<td>${o.factoryS}</td>
					<td>${o.areaS}</td>
					<td>${o.groupS}</td>
					<td width="8%">${o.groupLeaderS}</td>	
					<td>${o.checkTypeS}</td>		
					<td>${o.auditBasisS}</td>
					<td>${o.auditFindS}</td>	
					<td>${o.defectS}</td>
					<td>${o.auditManS}</td>			
					<td width="8%">${o.dutyS}</td>
					<td>${o.followManS}</td>
					<td width="8%">${o.isCloseS }</td>
					<td width="5%">${o.recordManS}</td>

				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

