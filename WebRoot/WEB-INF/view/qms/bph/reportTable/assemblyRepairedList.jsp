<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">

	function assembly_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		var factory = $("#factoryS", navTab.getCurrentPanel()).val();
		var group = $("#AsReGroup", navTab.getCurrentPanel()).val();
		if(factory !=null && factory !=""){
			
			if(endTime == ""){
				alert("期间不能为空");
				return false;
			}
		}

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("")
			return false;
		}
		
		$("#assemblyRepariedForm").submit();
		
	}
/**	
	function getAsReGroup() {
	var url = "<c:url value='commonSelected/getShiftGroupByFactory.do' />";
	$("#AsReGroup", navTab.getCurrentPanel()).load(url,{factory: $("#factoryS",navTab.getCurrentPanel()).val()});   
	}
**/	
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
		
		var objs = $("#assemblyRepariedForm input",navTab.getCurrentPanel());
		var objs2 = $("#assemblyRepariedForm select",navTab.getCurrentPanel());
		
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
	<form id="assemblyRepariedForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/assemblyRepaired.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="0" name="area"/>
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
					发生日期： 
				</td>
				<td>
					<input type="text" name="dutyGroup1S" id="startTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="${vo.dutyGroup1S }"/>
					至 <input type="text" name="dutyGroup2S" id="endTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="${vo.dutyGroup2S }"/>
				</td>

				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="assembly_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/assemblyRepairedExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
				</td>
				</tr>	
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th width="3%">选择</th>
				<th width="6%">发生日期</th>
				<th width="5%">发生工厂</th> 
				<th width="5%">产线</th>  
				<th width="5%">责任班组</th>
				<th width="5%">班组长</th>
				<th width="5%">工单号</th>
				<th width="5%">工序</th>
				<th width="6%">主机条码</th>
				<th width="5%">产品名称</th>
				<th width="4%">国内外</th>
				<th width="6%">不良现象</th>
				<th width="5%">不良部件</th>
				<th width="6%">不良原因</th>
				<th width="5%">维修工</th>
				<th width="5%">维修时间</th>
				<th width="5%">维修方式</th>
				<th width="5%">审核结论</th>
				<th width="5%">审核人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td width="6%">${o.creationTimeZ}</td>

					<td>${o.factoryS}</td>		
					<td >${o.lineS}</td>
					<td>${o.groupS}</td>
					<td>${o.groupLeader}</td>
		            <td>${o.orderNumbers}</td>
					<td>${o.checkStep}</td>
					<td>${o.itemNumberS}</td>
					<td>${o.productTypeS}</td>
					<td>${o.atrName}</td>
					<td>${o.defectS}</td>
					<td>${o.itemNameS}</td>
					<td>${o.defectSource}</td>
					<td>${o.repariredMan}</td>
					<td>${o.dateZ}</td>
					<td>${o.repairedMethod}</td>
					<td>${o.checkResult}</td>
					<td>${o.recordManS}</td>
			     </tr>		
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
     <c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
    </div>
	
</div>

