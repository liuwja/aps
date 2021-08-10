<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	#table td{text-align:center;}
</style>
<script type="text/javascript">
	function d_checkData(){
		//$("#instotalForm").submit();
	}
	
	//导出excel
	function installTotalExcel(url){
		var myForm = document.createElement("form");
		myForm.action= url;
		myForm.method="post"; 
		myForm.target="noexistForm"; 
		var objs = $("#instotalForm input",navTab.getCurrentPanel());
		var objs_select = $("#instotalForm select",navTab.getCurrentPanel());	
		var myInput;
		for(var i = 0 ; i< objs.length+objs_select.length ; i++){
			var $obj = null;
			if(i>=objs.length){
				$obj = $(objs_select[i-objs.length]);	
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
	//加载型号查询
	function installTotalCondition()
	{
		var url = "<c:url value='qms/common/partTypeLineOps.do' />";
		var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
		var partTypeListTxt = $('#partTypeListTxt', navTab.getCurrentPanel()).val();
		var jsonVar = {productType:productType,partTypeListTxt:partTypeListTxt,partTypeDocId:"insTotalpartTypeList"};
		delete jsonVar["partTypeListTxt"];
		$("#partTypeMaketTd",navTab.getCurrentPanel()).load(url,jsonVar);
	}
	//清空
	function insTotalfr_clearAll(){
		$("#insTotalfr_name", navTab.getCurrentPanel()).val("");
		$("#insTotalfr_id", navTab.getCurrentPanel()).val("");
		$("#insTotalfr_code", navTab.getCurrentPanel()).val("");
	}
	function insTotalft_clearAll(){
		$("#insTotalft_name", navTab.getCurrentPanel()).val("");
		$("#insTotalft_id", navTab.getCurrentPanel()).val("");
		$("#insTotalft_code", navTab.getCurrentPanel()).val("");
	}
	function clearinstcmReason(){
		$("#instcm_name", navTab.getCurrentPanel()).val("");
		$("#instcm_id", navTab.getCurrentPanel()).val("");
		$("#instcm_code", navTab.getCurrentPanel()).val("");
	}
	//故障小类选择
	function insTotalfrSel(obj){
		var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
		var href = "qms/commonSelect/faultReasonSelect.do?groupName=insTotalfr";
		$(obj).attr("href",href+"&productType="+productType);
	}
	//故障小类选择
	function insTotalftSel(obj){
		var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
		var href = "qms/commonSelect/faultTypeSelect.do?groupName=insTotalft";
		$(obj).attr("href",href+"&productType="+productType);
	}
</script>
	<form id="instotalForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/installTotal/installTotal.do" method="post">
	<div class="searchBar">
		<table class="searchContent dropdownSearchBar">
			<tr>	
				<td style="text-align: center;width: 60px">机型类别：</td>
                <td style="width: 142px">
					<select name="productType" onchange="installTotalCondition();">
							<option value="">-请选择-</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 	
    			<td style="text-align: center; width: 60px">
					产品型号： 
				</td>
				<td style="width: 193px">
					<div id="insTotalpartTypeList" class="dropdownlist"></div>
				</td>
    			<td style="text-align: center; width: 60px">
					安装日期：
				</td>
				<td>
					<input type="text" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" name="insStartTime" id="insStartTime" value="${vo.insStartTime}" size="8"/>
					&nbsp;至&nbsp;
					<input type="text" id="insEndTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" name="insEndTime" value="${vo.insEndTime}" size="8"/>
				</td>
    		</tr>
    		<tr>	
    			<td>
					区域： 
				</td>
				<td>
					<div id="insTotalRegionList" class="dropdownlist"></div>
				</td>
				<td style="padding-right:10px;">
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>
				<td>
					<a class="button"  onclick="installTotalExcel('base/installTotal/excelOutput.do');" title="确定导出信息？"><span>导出EXCEL</span></a>
					<div style="margin-top: 6px;margin-left: 105px">安装量总数：<font color="red">${sum}</font>&nbsp;&nbsp;&nbsp;</div>
				</td>
			</tr>
		</table>
	</div>
</form>
<div class="pageContent">
	<table class="table" width="100%" layoutH="119" id="table">
		<thead>
			<tr>
				<th>主机条码</th>
				<th>服务工单</th>
				<th>机型类别</th>
				<th>安装月份</th>				
				<th>物料编码</th>
				<th>系列</th>
				<th>型号</th>
				<th>区域</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<td align="center" style="width: 13%">${o.serialNumber}</td>
					<td align="center">${o.serviceOrder}</td>
					<td align="center">${o.productType}</td>
					<td align="center">${o.installMonth}</td>
					<td align="center">${o.partNumber}</td>
					<td align="center">${o.partFamily}</td>
					<td align="center">${o.partType}</td>
					<td align="center">${o.region}</td>
				</tr>
			</c:forEach>
		</tbody>
<!-- 
		<tbody>
			<c:forEach items="${list}" var="o">
				<c:set value="${fn:length(o.insList)}" var="len"></c:set>
				<tr>
					<td>${o.serialNumber}</td>
					<td align="center"  rowspan="${len+1}">${o.productType}</td>
					<td align="center" rowspan="${len+1}">${o.installMonth}</td>
					<td align="center" rowspan="${len+1}">${o.installCount}</td>
					<td>${o.repairMonth}</td>
					<td>${o.partNumber}</td>
					<td>${o.partFamily}</td>
					<td>${o.partType}</td>
					<td>${o.region}</td>
					<td>${o.faultTypeCode}</td>
					<td>${o.faultReasonCode}</td>
					<td>${o.repairCount}</td>
				</tr>		
				<c:forEach items="${o.insList}" var="chird">
					<tr>
					<td>${chird.repairMonth}</td>
					<td>${chird.partType}</td>
					<td>${chird.region}</td>
					<td>${chird.faultTypeCode}</td>
					<td>${chird.faultReasonCode}</td>
					<td>${chird.repairCount}</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
-->
	</table>
	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>
<span id="partTypeMaketTd">
      <script type="text/javascript">
          $(function(){
        	  $('#insTotalpartTypeList',navTab.getCurrentPanel()).dropdownlist({  
                  id:'partTypeListTxt',
                  columns:3,
                  selectedtext:'',
                  listboxwidth:450,//下拉框宽
                  maxchecked:100,
                  checkbox:true,
                  listboxmaxheight:400,
                  width:120,
                  requiredvalue:[],
                  selected:[${vo.partTypeListTxt}],
                  data:${jsonParts},//数据，格式：{value:name}
                  onchange:function(text,value){
                  }
              });
        	//加载区域
        	 	$('#insTotalRegionList', navTab.getCurrentPanel()).dropdownlist({
        	        id:'regionListTxt',
        	        columns:3,
        	        selectedtext:'',
        	        listboxwidth:700,//下拉框宽
        	        maxchecked:100,
        	        checkbox:true,
        	        listboxmaxheight:400,
        	        width:120,
        	        requiredvalue:[],
        	        selected:[${vo.regionListTxt}],
        	        data:${jsonRegions},//数据，格式：{value:name}
        	        onchange:function(text,value){
        	        }
        	    });
          });
      </script>
  </span> 	
