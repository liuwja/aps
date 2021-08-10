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
function wareHousrExportData(){
	var url = "quality/wareHouse/exportExcel.do";
	var form = document.createElement("form");
	form.action = url;
	form.method = "post";
	form.target="noexistForm"; 
	var wareNumber = document.createElement("input");
	wareNumber.name = "wareNumber";
	wareNumber.value = $("input[name=wareNumber]",navTab.getCurrentPanel()).val();
	form.appendChild(wareNumber);
	var productName = document.createElement("input");
	productName.name ="productName";
	productName.value =$("input[name=productName]",navTab.getCurrentPanel()).val();
	form.appendChild(productName);
	form.submit();
}
</script>

	<form  onsubmit="return navTabSearch(this);" action="quality/wareHouse/list.do" rel="pagerForm" method="post">
	<div  class="searchBar dropdownSearchBar" >
		<table class="searchContent">
		     <td>
		         仓库编号：
		     </td>
		     <td>
		        <input type="text" size="12" name="wareNumber" value="${param.wareNumber }">
		     </td>
		     <td>
		         机型：
		     </td>
		     <td>
		        <input type="text" size="12" name="productName" value="${param.productName }">
		     </td>
		      <td >
			    <div class="buttonActive"><div class="buttonContent"><button type="submit" ">查找</button></div></div>
			 </td> 
             <td >
			    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="wareHousrExportData()">导出</button></div></div>
			 </td>  
		</tr> 
		</table>
	</div>
	</form>

<div >
    <div class="panelBar">
		<ul class="toolBar">
		<shiro:hasPermission name="part:wareHouse:ADD">
				    <li><a class="add" href="quality/wareHouse/add.do"  mask="true" target="dialog" height="300" width="500"  title="新增-仓库"><span>新增</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="part:wareHouse:EDIT">
					<li><a class="edit" href="quality/wareHouse/edit.do?id={code_carrier}"  mask="true" target="dialog" height="300" width="500"   title="修改-仓库"><span>修改</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="part:wareHouse:DEL">
					<li><a class="delete" href="quality/wareHouse/delete.do?id={code_carrier}"  target="ajaxTodo"   title="确定删除该仓库吗？"><span>删除</span></a></li>
		</shiro:hasPermission>	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="117">
		<thead >
		    
			<tr>
			    <th  >选择</th>
			    <th   >仓库编号</th>
			    <th   >仓库名称</th>
				<th   >机型</th>
			</tr>
			
		</thead>
		<tbody >
		   <c:forEach items="${list }" var="o">
		       <tr target="code_carrier" rel="${o.wareRefKey}" >
		          <td ><input type="radio" name="radio"></td>
		          <td >${o.wareNumber }</td>
		          <td >${o.wareName}</td>
		          <td >${o.productName }</td>
		       </tr>
		   </c:forEach>
		</tbody>
   </table>
   <c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

