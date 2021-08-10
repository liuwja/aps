<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">

	function assembly_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		var factory = $("#factoryS", navTab.getCurrentPanel()).val();
		var group = $("#oqcGroup", navTab.getCurrentPanel()).val();
/**		if(factory !=null && factory !=""){
			if(group ==""){
				alert("班组不能为空");
				return false;
			}
		}
**/		
		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("")
			return false;
		}
		
		$("#oqcForm").submit();
		
	}
/**	
	function getOqcGroup() {
	var url = "<c:url value='commonSelected/getShiftGroupByFactory.do' />";
	$("#oqcGroup", navTab.getCurrentPanel()).load(url,{factory: $("#factoryS",navTab.getCurrentPanel()).val()});   
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
		
		var objs = $("#oqcForm input",navTab.getCurrentPanel());
		var objs2 = $("#oqcForm select",navTab.getCurrentPanel());
		
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
	<form id="oqcForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/oqcCheck.do" method="post">
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
					<input type="text" name="startTime" id="startTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>"/>
					至 <input type="text" name="endTime" id="endTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>"/>
				</td>

				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="assembly_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/oqcCheckExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
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
				<th>工厂</th>  
				<th>产线</th>
				<th>产品线</th>
				<th>生产班组</th>
				<th>工单号</th>	
				<th>主机条码</th>
				<th>检验单号</th>
				<th>抽检内容</th>							
				<th >抽检结果</th>
				<th >不良现象</th>
				<th >责任班组1</th>
				<th >责任班组2</th>
				<th >责任班组3</th>
				<th >抽检人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td width="6%"><fmt:formatDate value="${o.creationTime}" type="date" pattern="yyyy-MM-dd "/></td>
					<td>${o.factoryS}</td>   
					<td>${o.plineNames}</td> 
					<td>${o.productoinLine}</td> 
					<td>${o.ruda1}</td>
					<td>${o.orderNumber}</td> 
					<td>${o.objectName}</td>		
					<td >${o.uda9}</td>
					<td>${o.opName}</td>
					<td>${o.testPassed == 0 ? "不合格" : "合格"}</td>
					<td >${o.defectComment} </td>
					<td>${o.group1[0]}</td>
					<td>${o.group1[1]}</td>
					<td>${o.group1[2]}</td>
					<td>${o.uda0}</td>

				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

