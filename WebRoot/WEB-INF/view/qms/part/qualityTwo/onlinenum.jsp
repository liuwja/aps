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
	function chenk(){
		var factory=$("#factory_${id_end}",navTab.getCurrentPanel()).val();
		/* if(factory==null || factory==undefined ||factory==""){
			alertMsg.info("请选择工厂！");
			return;
		} */
		var productType=$("#productType_${id_end}",navTab.getCurrentPanel()).val();
		/* if(productType==null || productType==undefined ||productType==""){
			alertMsg.info("请选择机型！");
			return;
		} */
		$("#onlinesl",navTab.getCurrentPanel()).submit();
	}
	function downloadExeclNum(){
		var url = "base/online/downloadExeclNum.do";
    	var form = document.createElement("form");
    	form.action = url;
    	form.method = "post";
    	form.target="noexistForm"; 
    	document.body.appendChild(form);
    	form.submit();
    }
</script>
<div class="pageHeader" style="position:static">
	<form id="onlinesl" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/online/onlinenum.do" method="post">
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
					<jsp:param value="1" name="badphenomenon"/>
					<jsp:param value="1" name="selectDate"/>
					<jsp:param value="0" name="period"/>
					<jsp:param value="0" name="lotTime"/>
					<jsp:param value="0" name="columnNum"/>
					<jsp:param value="0" name="result"/>
					<jsp:param value="10" name="analysisType"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="1" name="iscrux"/>
					<jsp:param value="1" name="iscrux"/>
				</jsp:include>
				<td>
				维修方式:</td>
				<td>
				<select name="maintenanceMode" id="maintenanceMode">
					<option value="">所有</option>
					<option value="维修">维修</option>
					<option value="更换">更换</option>
				</select></td>
				<script type="text/javascript">				
					$("#maintenanceMode").val("${vo.maintenanceMode}")
				</script>
					<td colspan="5">
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" >查找</button>
							</div>
						</div>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="downloadExeclNum()">导出</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<input id="bs2" type="hidden" name="bs"/> 
	</form>
</div>
<div class="pageContent" id="tab2" hid="1" style="display: ">
	<table class="table" width="120%" layoutH="188">
		<thead>
			<tr>
				<th width="7%">发生日期</th>
				<th width="7%">维修方式</th>
				<th width="5%">产品线</th>
				<th width="12%">主机条码</th>
				<th width="7%">更换前物料条码</th>
				<th width="7%">更换后物料条码</th>
				<th width="7%">物料编号</th>
				<th width="12%">物料名称</th>
				<th width="7%">供应商编号</th>
				<th width="12%">供应商名称</th>
				<th width="7%">不良现象</th>
				<th width="4%">不良数</th>
				<th width="4%">国内/外</th>
				<th width="4%">新品/老品</th>
				<th width="4%">气源</th>
				<th width="4%">不良现象代码</th>
				<th width="4%">不良原因</th>
				<th width="4%">产线</th>
				<th width="4%">不良部件</th>
				<th width="4%">验证结论</th>
				<th width="4%">备注</th>
				<th width="4%">班组名称</th>
				<th width="4%">新品结束时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td><fmt:formatDate value="${list.date_TT}" type="date" pattern="yyyy-MM-dd "/></td>
					<td>${list.maintenanceMode}</td>
					<td>${list.moldtype}</td>
					<td>${list.serialNumber}</td>
					<td>${list.materialOld}</td>
					<td>${list.materialNew}</td>
					<td>${list.part_number}</td>
					<td>${list.description}</td>
					<td>${list.supcode}</td>
					<td>${list.supname}</td>
					<td>${list.defect_s}</td>
					<td>${list.badnum }</td>
					<td>${list.abroad}</td>
					<td>${list.productNO}</td>
					<td>${list.gas}</td>
					<td>${list.defect_d}</td>
					<td>${list.defect_res}</td>
					<td>${list.pl_name}</td>
					<td>${list.defect_b}</td>
					<td>${list.verifyingCon}</td>
					<td>${list.text}</td>
					<td>${list.group_name}</td>
					<td>${list.isnew_end_time_s}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
