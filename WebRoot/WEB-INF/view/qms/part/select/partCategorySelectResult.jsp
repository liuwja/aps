<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
	var firstLoad = "${firstLoad}";
    loadSelectedSupplier(firstLoad);
});
</script>
		<div  class="pageHeader">
		待选列表
		</div>
		<div  class="pageHeader">
    	<form onsubmit="return  divSearch(this, 'toSelectCategory')" id="pagerForm" action="quality/testInstance/partCategorySelect.do" method="post">

			    <div class="searchBar">
			        <table class="searchContent">
			 		
			        <tr>
			                <td class="dateRange">
								一级分类:
			                </td>
			                <td >
								<select name="categoryName" required="required">
									<option value=" ">所有</option>
									<c:forEach items="${partCategoryList}" var="o">
										<option value="${o.categoryName}" ${categoryName eq o.categoryName ? 'selected':''}>${o.categoryName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="pageNum" value="1" />
    							<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
    							<input type="hidden" name="direction" value="" />	 
    							<input type="hidden" name="flag" value="1" />	 
			                </td>   
<%-- 			            <td class="dateRange">
								二级分类:
			                </td>                                      
			                <td>
								<input type="text" name="supplierName" id="supplierName" size="10" value="${supplierName}"/>
			                </td>  --%>
			                <td>
			                	<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>               
			                </td>  
			        </tr>
			        </table>
			    </div>
		</form>
		</div>
	        <table class="table" width="100%" layoutH="150">
	            <thead>
	                <tr>
	                    <th width="10%"><input id="checkBoxAll" type="checkbox" onclick="selectAllRow()" class="checkboxCtrl" group="id" ></th>
	                    <th width="20%">供应商编号</th>
	                    <th width="30%">供应商名称</th>
	                </tr>
	            </thead>
	            <tbody> 
	                <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.categoryName}','${o.categoryTwoName}')">
	                        <td style="text-align: center;">
	                            <input id="${o.id}" type="checkbox" name="chkbox" value="${o.idNameNumber}">
	                        </td>
	                        <td>${o.categoryName}</td>
	                        <td>${o.categoryTwoName}</td>
	                    </tr>       
	                </c:forEach>        
	            </tbody>
	        </table>
	        
		   <c:import url="../../../_frag/pager/panelBar.jsp">
		       <%-- 对话框分页时需要传此参数 --%>
		       <c:param name="targetName" value="dialog"/>
		       <c:param name="relDivId" value="toSelectCategory"/>    
	       
	       </c:import>   
    
