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

</style>
<script type="text/javascript">
function newPartRefExportData(){
	var url = "quality/newPartRef/exportExcel.do";
	var form = document.createElement("form");
	form.action = url;
	form.method = "post";
	form.target="noexistForm"; 
	var wareNumber = document.createElement("input");
	wareNumber.name = "oldPartNumber";
	wareNumber.value = $("input[name=oldPartNumber]",navTab.getCurrentPanel()).val();
	form.appendChild(wareNumber);
	var productName = document.createElement("input");
	productName.name ="newPartNumber";
	productName.value =$("input[name=newPartNumber]",navTab.getCurrentPanel()).val();
	form.appendChild(productName);
	document.body.appendChild(form);
	form.submit();
}
</script>

	<form  onsubmit="return navTabSearch(this);" action="quality/newPartRef/list.do" rel="pagerForm" method="post">
	<div  class="searchBar dropdownSearchBar" >
		<table class="searchContent">
		     <td>
		         旧物料编码：
		     </td>
		     <td>
		        <input type="text" size="12" name="oldPartNumber" value="${param.oldPartNumber }">
		     </td>
		     <td>
		         新物料编码：
		     </td>
		     <td>
		        <input type="text" size="12" name="newPartNumber" value="${param.newPartNumber }">
		     </td>
		      <td >
			    <div class="buttonActive"><div class="buttonContent"><button type="submit" ">查找</button></div></div>
			 </td> 
             <td >
			    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="newPartRefExportData()">导出</button></div></div>
			 </td>  
		</tr> 
		</table>
	</div>
	</form>

<div >
    <div class="panelBar">
		<ul class="toolBar">
		<shiro:hasPermission name="part:wareHouse:ADD">
				    <li><a class="add" href="quality/newPartRef/add.do"  mask="true" target="dialog" height="300" width="500"  title="新增-新旧物料对应"><span>新增</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="part:wareHouse:EDIT">
					<li><a class="edit" href="quality/newPartRef/edit.do?id={code_carrier}"  mask="true" target="dialog" height="300" width="500"   title="修改-新旧物料对应"><span>修改</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="part:wareHouse:DEL">
					<li><a class="delete" href="quality/newPartRef/delete.do?id={code_carrier}"  target="ajaxTodo"   title="确定该新旧物料对应关系吗？"><span>删除</span></a></li>
		</shiro:hasPermission>	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="117">
		<thead >
		    
			<tr>
			    <th  >选择</th>
			    <th   >旧物料编码</th>
			    <th   >新物料编码</th>
				<th   >物料名称</th>
			</tr>
			
		</thead>
		<tbody >
		   <c:forEach items="${list }" var="o">
		       <tr target="code_carrier" rel="${o.partRefKey}" >
		          <td ><input type="radio" name="radio"></td>
		          <td >${o.oldPartNumber }</td>
		          <td >${o.newPartNumber}</td>
		          <td >${o.partName }</td>
		       </tr>
		   </c:forEach>
		</tbody>
   </table>
   <c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

