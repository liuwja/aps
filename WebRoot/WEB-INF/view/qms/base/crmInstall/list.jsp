<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader" style="position:static">
	<form id="crminsForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/crmInstall/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>主机条码： </td>
				<td><input type="text" id="serialNumber" name="serialNumber" value="${vo.serialNumber}" size="20"/></td>
				<td>安装工单： </td>
				<td><input type="text" id="serviceOrder" name="serviceOrder" value="${vo.serviceOrder}" size="20"/></td>
			</tr>
			<tr>
				<td>服务中心：</td>
				<td>
                    <div id="crmInstallInfoRegionList" class="dropdownlist"></div>
                </td>
				<td>创建日期：</td>
				<td colspan="3">
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({istime: true,format: 'YYYY-MM-DD'})" size="20"  readonly="true" value="${vo.startTime}"/>
					&nbsp;至&nbsp;
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({istime: true,format: 'YYYY-MM-DD'})" size="20" readonly="true" value="${vo.endTime}"/>
				</td>
				<td>安装日期：</td>
				<td colspan="3">
					<input type="text" id="installStartTime" name="installStartTime" placeholder="请输入日期" onclick="laydate({istime: true,format: 'YYYY-MM-DD'})" size="20"  readonly="true" value="${vo.installStartTime}"/>
					&nbsp;至&nbsp;
					<input type="text" id="installEndTime" name="installEndTime" placeholder="请输入日期" onclick="laydate({istime: true,format: 'YYYY-MM-DD'})" size="20" readonly="true" value="${vo.installEndTime}"/>
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
		$("#crmInstallInfoRegionList").dropdownlist({
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
				<th width="12%">安装日期</th>
				<th width="12%">安装工单</th>
				<th width="12%">机型类别</th>
				<th width="12%">产品名称</th>
				<th width="12%">服务中心</th>
				<th width="12%">创建日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.serialNumber}">
					<td>${o.serialNumber}</td>
					<td>${o.intallDate}</td>
					<td>${o.serviceOrder}</td>
					<td>${o.productType}</td>
					<td>${o.description}</td>
					<td>${o.region}</td>
					<td>${o.creationTime}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>