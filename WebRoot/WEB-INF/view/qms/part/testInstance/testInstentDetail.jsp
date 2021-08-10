<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css" media="screen">
	${demo.css}
	
.my-uploadify-button {
    background:none;
    border: none;
    text-shadow: none;
    border-radius:0;
}

.uploadify:hover .my-uploadify-button {
    background:none;
    border: none;
}

.fileQueue {
    width: 400px;
    height: 150px;
    overflow: auto;
    border: 1px solid #E5E5E5;
    margin-bottom: 10px;
}
/** 多行表头固定列宽样式**/
.grid .gridHeader .fixColRow
{
    height: 1px;
}
.grid .gridHeader .fixColRow th
{
    line-height: 0px;
    background-color: #CAE8EA;
    border-color: #CAE8EA;
    border-top-width: 0;
    padding-top: 0px;
    padding-bottom: 0px;
}
</style>
<script type="text/javascript">
    $(function(){	
    	
    	$('#partTypeList', navTab.getCurrentPanel()).dropdownlist({
    	    id:'partType',
    	    columns:2,
    	    selectedtext:'',
    	    listboxwidth:300,//下拉框宽
    	    maxchecked:10,
    	    checkbox:true,
    	    listboxmaxheight:400,
    	    width:120,
    	    requiredvalue:[],
    	    selected:[],
    	    data:${partMap},//数据，格式：{value:name}
    	    onchange:function(text,value){
    	    }
    	});	
    	setTimeout("setTableWidth()",5);
     });

    function setTableWidth(){
    	var $testInstentThead = $("#testInstentThead",navTab.getCurrentPanel());
    	var $testInstentTbody = $("#testInstentTbody",navTab.getCurrentPanel());
    	var ttlength = $testInstentThead.parent().width();
    	var toldThs = $testInstentThead.find("tr:first-child").find("th");
    	var toldTds = $testInstentTbody.find("tr:first-child").find("td");
    	var taStyles =[];
    	for(var i = 0, l = toldThs.size(); i < l; i++) {
			var $th = $(toldThs[i]);
			var  width = $th.innerWidth() - (100 * $th.innerWidth() / ttlength);
			//var  width = $th.width() ;
			taStyles[taStyles.length] = width;
		}
    	for(var i = 0, l = toldTds.size(); i < l; i++) {
			var $td = $(toldTds[i]);
			$td.width(taStyles[i]-1+"px");
		}
    }
    function testExportData(){
    	var url = "quality/testInstance/exportTestExcel.do";
    	var form = document.createElement("form");
    	form.action = url;
    	form.method = "post";
    	form.target="noexistForm"; 
    	document.body.appendChild(form);
    	form.submit();
    }
</script>

	<form id="testInstanceForm" onsubmit="return navTabSearch(this);" action="quality/testInstance/testInstentDetail.do" rel="pagerForm" method="post">
	<div id="searchContentDiv" class="searchBar dropdownSearchBar" >
		<table class="searchContent">
		     <jsp:include page="../select/part_supplier.jsp" flush="true">
					<jsp:param value="threeLine" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="productType"/>
					<jsp:param value="1" name="supplier"/>
					<jsp:param value="1" name="partType"/>
					<jsp:param value="1" name="partClass"/>
					<jsp:param value="1" name="partName"/>
					<jsp:param value="1" name="partVersion"/>
					<jsp:param value="1" name="isNew"/>
					<jsp:param value="1" name="source"/>
					<jsp:param value="0" name="selectDate"/>
					<jsp:param value="1" name="period"/>
					<jsp:param value="1" name="lotTime"/>
					<jsp:param value="1" name="smallBatch"/> 
					<jsp:param value="0" name="columnNum"/>
					<jsp:param value="1" name="result"/>
					<jsp:param value="0" name="analysisType"/>
					<jsp:param value="1" name="submits"/>
					<jsp:param value="0" name="isLastTr"/> 
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
			</jsp:include>	
			<td>
			    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="testExportData()">导出</button></div></div>
			    </td>  
			 </tr>   
		</table>
	</div>
	</form>

<div >
	<table class="table" width="190%" layoutH="190">
		<thead id="testInstentThead">
 		    <tr class="fixColRow">
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="2%"></th>
              
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="1%"></th>
              <th width="1%"></th>
	              
              <th width="11%"></th>
              <th width="9%"></th>
              <th width="6%"></th> 
              <th width="3%"></th>
              <th width="4%"></th>
              <th width="4%"></th> 
              <th width="3%"></th>
              <th width="5%"></th>
              <th width="5%"></th>
              <th width="3%"></th>
              <th width="3%"></th>
              <th width="3%"></th>             
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="2%"></th>
              <th width="2%"></th>

            </tr> 
			<tr>
			    <th  rowspan="2" >小批量</th>
			    <th  rowspan="2" >ROHS标识</th>
			    <th  rowspan="2" >国内/外</th>
			    
			    <th  rowspan="2" >产品线</th>
			    <th  rowspan="2" >仓库</th>
			    <th  rowspan="2" >缺陷分类</th>
			    <th  rowspan="2" >一级分类</th>
			    <th  rowspan="2" >二级分类</th>
			    
				<th  rowspan="2" >物料编号(新编号)</th>
			    <th  rowspan="2" >物料批次</th>
				<th  rowspan="2" >物料名称</th>
				<th  rowspan="2" >仓库</th>
				<th  rowspan="2" >检验时间</th>
				<th  rowspan="2" >合格数量</th>
				<th  rowspan="2" >检验结果</th>
				<th  rowspan="2" >供应商编号(新编号)</th>
				<th  rowspan="2" >供应商名称(简称)</th>
				<th  colspan="3" >性能</th>
				<th  colspan="3" >尺寸</th>
				<th  colspan="3" >外观</th>
				<th  colspan="3" >其他</th>
		    <!--<th  rowspan="2" >检验人</th>
				<th  rowspan="2" >检验时间</th> 
			    <th  rowspan="2" >检验方案</th> -->
			</tr>
			<tr >
	            
	            <th>结果</th><th>抽检不良</th> <th>抽检总数</th>
	            <th>结果</th><th>抽检不良</th> <th>抽检总数</th>
	            <th>结果</th><th>抽检不良</th> <th>抽检总数</th>
	            <th>结果</th><th>抽检不良</th> <th>抽检总数</th>
            </tr>
		</thead>
		<tbody id="testInstentTbody" >
		   <c:forEach items="${list }" var="o">
		       <tr >
		          <td>${o.smallBatch }</td>
		          <td>${o.rohs}</td>
		          <td>${o.source}</td>	
		          
		          <td>${o.productionLineName}</td>	
		          <td>${o.location}</td>	
		          <td>${o.defect}</td>	
		          <td>${o.class1}</td>	
		          <td>${o.class2}</td>	
		          	   
		          <td>${o.partNumber }(${o.newPartNumber })</td>
		          <td>${o.lotName }</td>
		          <td>${o.partName }</td>
		          <td>${o.wareHouse }</td>
		          <td>${o.dateT }</td>
		          <td>${o.totalQty }</td>
		          <td>${o.resultS }</td>
		          <td>${o.supplierCode }(${o.newSupplierNumber })</td>
		          <td>${o.supplier}(${o.supplierBrief })</td>
		          <td>${o.propertyType }</td>
	              <td>${o.propertyDnum }</td>
	              <td>${o.propertyTnum }</td>
		          <td>${o.sizeType }</td>
	              <td>${o.sizeDnum }</td>
	              <td>${o.sizeTnum }</td>
		          <td>${o.aspectType }</td>
	              <td>${o.aspectDnum }</td>
	              <td>${o.aspectTnum }</td>
		          <td>${o.otherType }</td>
	              <td>${o.otherDnum }</td>
	              <td>${o.otherTnum }</td>
		       </tr>
		   </c:forEach>
		</tbody>
   </table>
   <c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

