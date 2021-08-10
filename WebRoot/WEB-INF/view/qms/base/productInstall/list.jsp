<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script>
function loadPdPartType() {
	var url = "<c:url value='qms/common/partTypeLineOps.do' />";
	var productType = $('select[name="nProductType"]', navTab.getCurrentPanel()).val();
	var partTypeListTxt = $('#partTypeListTxt', navTab.getCurrentPanel()).val();
	var jsonVar = {productType:productType,partTypeListTxt:partTypeListTxt,partTypeDocId:"partTypePdList"};
	delete jsonVar["partTypeListTxt"];
	$("#productInstallTd", navTab.getCurrentPanel()).load(url,jsonVar);
}
</script>
<div class="pageHeader" style="position:static">
	<form id="productinsForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/productInstall/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>主机条码： </td>
				<td><input type="text" id="partCode" name="partCode" value="${vo.partCode}" size="20"/></td>
				<td>产品类型： </td>
				<td>
					<select id="nProductType" name="productType" onchange="loadPdPartType()">
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
						</c:forEach>
					</select>
				</td>
				<td>产品型号： </td>
				<td><div id="partTypePdList" class="dropdownlist"></div></td>
			</tr>
			<tr>
				<td>服务中心：</td>
				<td>
                    <div id="productInstallInfoRegionList" class="dropdownlist"></div>
                </td>
				<td>安装日期：</td>
				<td colspan="3">
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({istime: true,format: 'YYYY-MM'})" size="20"  readonly="true" value="${vo.startTime}"/>
					&nbsp;至&nbsp;
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({istime: true,format: 'YYYY-MM'})" size="20" readonly="true" value="${vo.endTime}"/>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div></td>	
			</tr>
		</table>
	</div>
	</form>
</div>
<span id="productInstallTd">
	<script type="text/javascript">
	$(function(){
		$("#partTypePdList").dropdownlist({  
			id:'partTypeListTxt',
	        columns:3,
	        selectedtext:'',
	        listboxwidth:450,//下拉框宽
	        maxchecked:100,
	        checkbox:true,
	        listboxmaxheight:400,
	        width:120,
	        requiredvalue:[],
	        selected:[${vo.partType}],
	        data:${jsonParts},//数据，格式：{value:name}
	        onchange:function(text,value){
	        }
	    });
		
		$("#productInstallInfoRegionList").dropdownlist({
            id:'regionListTxt',
            columns:3,
            selectedtext:'',
            listboxwidth:700,//下拉框宽
            maxchecked:100,
            checkbox:true,
            listboxmaxheight:400,
            width:100,
            requiredvalue:[],
            selected:[${vo.regionListTxt}],
            data:${jsonRegions},//数据，格式：{value:name}
            onchange:function(text,value){
            }
        });
	});
	</script>
</span>
<div class="pageContent">
	
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th width="12%">主机条码 </th>
				<th width="12%">安装工单</th>
				<th width="12%">机型类别</th>
				<th width="12%">产品编码</th>
				<th width="12%">产品系列</th>
				<th width="12%">产品型号</th>
				<th width="12%">产品描述</th>
				<th width="12%">安装日期</th>
				<th width="12%">服务中心</th>
				<!-- <th width="15%">维修日期</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.partCode}">
					<td>${o.partCode}</td>
					<td>${o.serivceOrder}</td>
					<td>${o.productType}</td>
					<td>${o.partNumber}</td>
					<td>${o.partFamily}</td>
					<td>${o.partType}</td>
					<td>${o.decription}</td>
					<td>${o.intallDate}</td>
					<td>${o.region}</td>
					<%-- <td>${o.repairDate}</td> --%>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>