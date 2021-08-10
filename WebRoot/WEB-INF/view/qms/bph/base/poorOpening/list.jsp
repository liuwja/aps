<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	jQuery(document).ready(function(){
		$('#baseFactory_${id_end}').change(function(){
	
			//doOnlyLineSelectBase('${id_end}');
			doshiftGroupSelect('${id_end}');
			
		});
		loadShiftGroupMap('${id_end}');
	});
	
	function mkrcheckTime(){
		var starTime = $('#startTime',navTab.getCurrentPanel()).val();
		var endTime = $('#endTime',navTab.getCurrentPanel()).val();
		if(starTime>endTime){
			alert("开始时间不能大于结束时间");
			return false;
		}
		$('#market').submit();
	}
	
	
	function loadShiftGroupMap(idEnd){
		var txt = "";
		<c:forEach items="${shiftGroup_mapLines}" var="ao">
			var akey = '${ao.key}';
			txt = txt+akey+"@$";
			<c:forEach items="${ao.value}" var="shiftGroup">
				txt = txt+"<option value='${shiftGroup[1]}'>${shiftGroup[1]}</option>";
			</c:forEach>
			txt = txt+"#&";
		</c:forEach>
		$("#shiftGroupMap_"+idEnd).val(txt);
	}
	
	function doshiftGroupSelect(idEnd){
		//var idEnd = '${id_end}';
		var favalue = $("#baseFactory_"+idEnd).find("option:selected").text();
		var select = $("#shiftGroup_"+idEnd);
		select.empty();
		select.append("<option value=''>请选择</option>"); 
		if(favalue.indexOf("请选择")>-1){
	   		return ;
		}
		var map = $("#shiftGroupMap_"+idEnd).val();
		//alert(map);
		var arr1 = map.split("#&");
		//alert(arr1);
		for(i=0; i<arr1.length; i++){
			var arr2 = arr1[i].split("@$");
	
			if(arr2[0] == favalue){
				select.append(arr2[1]);
			}
		}
	}

	function editSelect(type){
		
		var $selItem =  $("input[name=selectBox]:checked", navTab.getCurrentPanel());	
		if($selItem.length>1){
		    alertMsg.error("只能选择一条信息！");
		    return false;
		}
		if($selItem.length == 0){
	        alertMsg.error("请选择信息！");
	        return false;
		}
		//获取选中的行中某一字段的信息,用于判断二级选择时候一级选择时候已经确定
		var $par = $selItem.parent().parent();
		if(type == "2"){
			if($par.children().eq(11).text()==""){
				alertMsg.error("请先选择一级责任方！");
				return false;	
			}
			//alert($par.children().eq(11).text().substring(3,4));
			if($par.children().eq(11).text().substring(3,4)!="厂"){
				alertMsg.error("只能对工厂的责任进行二级判定!");
				return false;	
				
			}
		}
		
	    var url = "poorOpen/determine.do?backNum="+ $selItem.val()+"&type="+type;
	    var opt = {width:800,height:450, mask:true};
	    $.pdialog.open(url, "editPoorOpen", "判定-市场开箱不良责任", opt);
	}

</script>
<div class="pageHeader" style="position:static">
	<form id="market" onsubmit="return navTabSearch(this);" rel="pagerForm" action="poorOpen/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<jsp:include page="../../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="fgroup"/>
					<jsp:param value="0" name="area"/>
					<jsp:param value="0" name="pline"/>
					<jsp:param value="0" name="shift"/>
					<jsp:param value="0" name="factory_iskey"/>
					<jsp:param value="0" name="area_iskey"/>
					<jsp:param value="1" name="pline_iskey"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
				</jsp:include>
				
				<td>
				   不良来源
				</td>
				<td>
				   <select name="billType" id="billType">
				      <option value="">所有</option>
				      <option value="开箱不良">开箱不良</option>
				      <option value="质量反馈">质量反馈</option>
				   </select>
				   <script type="text/javascript">
						$("#billType").val("${vo.billType}");
					</script>
				</td>
				<td>
				   故障机型
				</td>
				<td>
				   <input name="partType" type="text"  value="${vo.partType}" size="10">
				</td>
				<th>区域：</th>
				<td>
					<div id="regionOpenPoorList" class="dropdownlist"></div>
				</td>	
				</tr>
				<tr>
				<td>
				   产品编号或范围
				</td>
				<td>
				   <input name="productNum" type="text"  value="${vo.productNum }" size="10">
				</td>
				<td>发生日期：</td>	
				<td colspan="3">
					<input name="startTime" id="startTime" placeholder="请输入日期" onclick="laydate()" type="text" size="10" readonly="true"   value="${vo.startTime}" />&nbsp;至&nbsp;
					<input name="endTime" id="fqcendTime" placeholder="请输入日期" onclick="laydate()" type="text" size="10" readonly="true"   value="${vo.endTime}"/>
				</td>	
				
				<th>供应商：</th>
				<td>
					<input type="hidden" id="OPEN_POOR_SUPPLIER_data" name="supplierId" readonly="true" style="float: left;" value="${vo.supplierId}"/>
					<input type="hidden" id="OPEN_POOR_SUPPLIER_supplierCode" name="supplierNumber" readonly="true" style="float: left;" value="${vo.supplierNumber}"/>
                    <input type="text" id="OPEN_POOR_SUPPLIER_supplierName" name="supplierListTxt" readonly="true" style="float: left;" value="${vo.supplierListTxt}"/>
                    <a id="btn" class="btnLook btn" onclick="supplierSel(this, 'OPEN_POOR_SUPPLIER')" lookupGroup="OPEN_POOR_SUPPLIER">供应商选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearSupplier('OPEN_POOR_SUPPLIER')" title="清空"></a>
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" id="mkrbtn1" onclick="mkrcheckTime()">查找</button></div></div>
				</td>
					
			</tr>
		</table>
	</div>
	</form>

</div>
<div class="pageContent">
			<div class="panelBar">
			<ul class="toolBar">
				<shiro:hasPermission name="base:poorOpen:primary">
				    <li><a class="edit" href="javascript:void(0);" onclick="editSelect(1)"><span>一级责任划分</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:poorOpen:ultimate">
				   <li><a class="edit" href="javascript:void(0);" onclick="editSelect(2)"  ><span>二级责任划分</span></a></li>
				</shiro:hasPermission>
			</ul>
		</div>
	<table class="table" width="100%" layoutH="140" >
		<thead>
			<tr>
			    <th >选择</th>
			    <th width="6%">反馈单号</th>
			    <th width="8%">反馈日期</th>
			    <th width="8%">单据类别</th>
				<th width="6%">服务中心</th>
				<th width="6%">故障大类</th>
				<th width="6%">故障小类</th>
				<th width="7%">产品型号</th>
				<th width="8%">产品编码</th>
				<th width="8%">问题描述</th>
				<th width="6%">故障原因分析</th>	
				<th width="8%">责任方1</th>
				<th width="8%">判定人1</th>
				<th width="8%">责任方2</th>
				<th width="8%">责任方3</th>
				<th width="8%">判定人2</th>
			</tr>
		</thead>
		<tbody align="center" id="tbodyTab">
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.backNum}">
					<td>
						<input id="selectBox" type="checkbox" group="code" name="selectBox" value="${o.backNum }">
					</td>
					<%--需维护资料--%>
					<td width="6%">${o.backNum}</td>
					<td width="8%">${o.bactTime}</td> 
					<td width="8%">${o.billType}</td> 
					<td width="6%">${o.regionCore}</td> 
					<td width="6%">${o.faultType}</td> 	
					<td width="6%">${o.faultReason}</td> 					
					<td width="7%">${o.partType}</td> 					
					<td width="8%">${o.productNum}</td> 
					<td width="8%">${o.describe}</td> 
					<td width="6%">${o.causeAnalysis}</td> 					
					<td width="8%">${o.duty1}</td> 
					<td width="8%">${o.duty1Name}</td> 
					<td width="8%">${o.duty2}</td>
					<td width="8%">${o.duty3}</td>
					<td width="8%">${o.duty2Name}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script type="text/javascript">
		$(function(){
			loadRegionData("#regionOpenPoorList", "regionListTxt", [], ${jsonRegions});
		});
	</script>
