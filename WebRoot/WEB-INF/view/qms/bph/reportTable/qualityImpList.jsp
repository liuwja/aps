<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	function qualityImp_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("");
			return false;
		}
		
		$("#qualityImp").submit();
		
	}
/**	
	function getQualityImpArea() {
	var url = "<c:url value='commonSelected/getArea.do' />";
	$("#qiarea", navTab.getCurrentPanel()).load(url,{factory: $("#qifactoryS", navTab.getCurrentPanel()).val()});   
	}
	
	function getQualityImpGroup() {
	var url = "<c:url value='commonSelected/getShiftGroupByFactory.do' />";
	$("#qigroup", navTab.getCurrentPanel()).load(url,{factory: $("#qifactoryS", navTab.getCurrentPanel()).val()});   
	}
**/	
	function exportExcel(url){    

		var myForm = document.createElement("form");
		myForm.action= url;
		myForm.method="post"; 
		myForm.target="noexistForm"; 
		var myInput;
		
		var objs = $("#qualityImp input",navTab.getCurrentPanel());
		var objs2 = $("#qualityImp select",navTab.getCurrentPanel());
		
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
	<form id="qualityImp" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/qualityImpList.do" method="post">
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
					提报课题： 
				</td>
				<td>
					<input type="text" name="rfpNameS" value="${param.rfpNameS}"/>
				</td>
				<td>
					提报人： 
				</td>
				<td>
					<input type="text" name="finderS" value="${param.finderS}"/>
				</td>
				<td>
					最总评分： 
				</td>
				<td>
					<input type="text" name="totalScoreI" size="15" value="${param.totalScoreI}"/>
				</td>			
			</tr>
			<tr>
				<td>
					发生日期： 
				</td>
				<td>
					<input type="text" name="startTime" placeholder="请输入日期" size="15" onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>"/>
					至 <input type="text" name="endTime" placeholder="请输入日期" size="15" onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>"/>
				</td>

				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="qualityImp_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/qualityImpExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
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
				<th width="10%">发生日期</th>
				<th>工厂</th>
				<th>发生车间</th>
				<th>班组</th>
				<th>班长</th>
				<th width="8%">提报人</th>
				<th >提报课题</th>
				<th>改善课题评分</th>
				<th width="8%">最总评分</th>
				<th>记录人</th>			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td width="10%"><fmt:formatDate value="${o.recordDateT}" type="date" pattern="yyyy-MM-dd"/></td>
					<td>${o.factoryS}</td>
					<td>${o.areaS}</td>
					<td>${o.dutyGroupS}</td>
					<td width="8%">${o.groupLeaderS}</td>				
					<td width="8%">${o.finderS}</td>
					<td>${o.rfpNameS}</td>
					<td>${o.rfpScoreI}</td>
					<td width="8%">${o.totalScoreI}</td>
					<td width="5%">${o.recordManS}</td>

				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

