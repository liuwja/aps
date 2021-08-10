<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){
	  $("#baseFactory_${id_end}",navTab.getCurrentPanel()).change(function(){
		  getFormerProcessGroup();
	  });
});
	function formerProcess_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("")
			return false;
		}
		
		$("#formerProcess").submit();
		
	}
/*	
	function getFormerProcessArea() {
	var url = "<c:url value='commonSelected/getArea.do' />";
	$("#fparea", navTab.getCurrentPanel()).load(url,{factory: $("#fpfactoryS",navTab.getCurrentPanel()).val()});   
	}
	function getFormerProcessGroup() {
	var url = "<c:url value='base/commonselect/getFormerProcessGroup.do' />";
	$("#fpgroup").load(url,{factoryS: $("#fpfactoryS").val()});  
	}
*/	
	function getFormerProcessGroup() {
	    var factory= $("#baseFactory_${id_end}",navTab.getCurrentPanel()).val();
		var url = "<c:url value='commonSelected/getShiftGroupByFactory.do' />";
		$("#fpgroup", navTab.getCurrentPanel()).load(url,{factory: factory});
		$("#fqcgroupS", navTab.getCurrentPanel()).load(url,{factory: factory}); 
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
	
	var objs = $("#formerProcess input",navTab.getCurrentPanel());
	var objs2 = $("#formerProcess select",navTab.getCurrentPanel());
	
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
	<form id="formerProcess" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/formerProcessList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="0" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>			
				<td>
					发生班组： 
				</td>
				<td >
					<select name="groupS" id="fqcgroupS">
					
						<option value="">请选择</option>
						<c:forEach items="${group}" var="groupS">
						    <option value="${groupS.name}">${groupS.name}</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
						$("#fqcgroupS",navTab.getCurrentPanel()).val("${vo.groupS}"); 
				    </script>
				</td>
				<td>
					责任班组： 
				</td>
				<td >
					<select name="group" id="fpgroup">
					
						<option value="">请选择</option>
						<c:forEach items="${group}" var="group">
						    <option value="${group.name}">${group.name}</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
						$("#fpgroup",navTab.getCurrentPanel()).val("${vo.group}"); 
				    </script>
				</td>
				
			</tr>
			<tr>
				
				<td>
					产品型号： 
				</td>
				<td>
					<input type="text" name="typeS" value="${param.typeS}" />
				</td>
				<td>
					产品名称： 
				</td>
				<td>
					<input type="text" name="itemNameS" value="${param.itemNameS}" />
				</td>
			    <td>
					不良现象： 
				</td>
				<td>
					<input type="text" name="defectS"  value="${param.defectS}"/>
				</td>
				<td>
					判断结果： 
				</td>
				<td>
					<select name="checkResultS" id="checkResultS">
					   <option value="">请选择</option>
					   <option value="OK">OK</option>
					   <option value="NG">NG</option>
					</select>
					<script type="text/javascript">
						$("#checkResultS",navTab.getCurrentPanel()).val("${param.checkResultS}"); 
				    </script>
				</td>
				
			</tr>
			<tr>
			   <td>
					处置： 
				</td>
				<td>
					<select name="methodS" id="methodS">
						<option value="">所有</option>
						<option value="返工">返工</option>
						<option value="报废">报废</option>
						<option value="让步">让步</option>
					</select>
					<script type="text/javascript">
						$("#methodS", navTab.getCurrentPanel()).val("${param.methodS}"); 
				    </script>
				</td>			
				<td>
					发生日期： 
				</td>
				<td>
					<input type="text" size="12" id="startTime" name="startTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>"/>
					至 <input type="text" size="12" id="endTime" name="endTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>"/>
				</td>

				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="formerProcess_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/fqcCheckExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
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
				<th width="6%">工厂</th>
				<th width="6%">发生车间</th>
				<th width="6%">发生班组</th>
				<th width="6%">追溯号</th>
				<th width="6%">产品型号</th>
				<th width="6%">产品名称</th>
				<th width="5%">抽检频次</th>
				<th width="5%">不良频次</th>
				<th width="5%">抽检数量</th>
				<th width="5%">不良数量</th>
				<th width="8%">不良现象</th>
				<th width="5%">处置</th>
				<th width="8%">责任单位1</th>
				<th width="5%">责任单位2</th>
				<th width="5%">责任单位3</th>
				<th width="5%">记录人</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td ><fmt:formatDate value="${o.dateT}" type="date" pattern="yyyy-MM-dd "/></td>
					<td>${o.factoryS}</td>
					<td>${o.areaS}</td>
					<td>${o.groupS}</td>
					<td>${o.trackingNumberS}</td>
					<td>${o.typeS}</td>
					<td >${o.itemNameS}</td>				
					<td >${o.totalQtyI}</td>
					<td>${o.simpleQtyI}</td>
					<td>${o.checkResultS}</td>
					<td >${o.defectQtyI}</td>
					<td>${o.defectS}</td>
					<td >${o.methodS }</td>
					<td >${o.group1S}</td>
					<td >${o.group2S}</td>
					<td >${o.group3S}</td>
					<td >${o.recordManS}</td>

				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

