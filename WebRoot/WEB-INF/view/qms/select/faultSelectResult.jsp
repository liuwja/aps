<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
	var firstLoad = "${firstLoad}";
    loadSelectedUsers(firstLoad);
});
</script>
<div  class="pageHeader">
待选列表
</div>
<div class="pageHeader">
    <form onsubmit="return  divSearch(this, 'toSelectFaultTypeList')" id="pagerForm" action="qms/commonSelect/faultTypeSelectResult.do" method="post">

    <div class="searchBar">
        <table class="searchContent">

	        <tr>
	        		<td class="dateRange">
						故障代码:
	                </td>                                      
	                <td>
						<input type="text" name="code" id="code" size="10" value="${vo.code}"/>
	                </td> 
	                <td class="dateRange">
						故障名称:                     
						                   
	                </td>
	                 <td >
						<input type="text" name="name" id="name" size="10" value="${vo.name}"/>
					    <input type="hidden" name="pageNum" value="1" />
					    <input type="hidden" name="numPerPage" value="${page.numPerPage}" />
					    <input type="hidden" name="direction" value="" />       						
	                </td>  
	                </tr>
	                <tr>
	                <td class="dateRange">
						机型类别:
		            </td>                                      
		            <td>
							<select name="productType" >
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
							</c:forEach>
						</select>
		             </td> 
	                
	                <td>
	                	<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>               
	                </td>  
	        </tr>
        </table>
    </div>
    </form>
</div>

        <table class="table" width="100%" layoutH="170">
            <thead>
                <tr>
                    <th width="3%"><input id="checkBoxAll" type="checkbox" onclick="selectAllRow()" class="checkboxCtrl" group="id" ></th>
                    <th width="30%">机型类别</th>
                    <th width="40%">故障名称</th>
                    <th width="30%">故障代码</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach items="${list}" var="o">
                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.name}','${o.code}')">
                        <td style="text-align: center;">
                            <input id="${o.id}" type="checkbox" name="chkbox" value="${o.id},${o.productType},${o.name},${o.code}" />
                        </td>
                        <td>${o.productType}</td>
                        <td>${o.name}</td>
                        <td>${o.code}</td>
                    </tr>       
                </c:forEach>        
            </tbody>
        </table>
        
    <c:import url="../../_frag/pager/panelBar.jsp">
       <%-- 对话框分页时需要传此参数 --%>
       <c:param name="targetName" value="dialog"/>
       <c:param name="relDivId" value="toSelectFaultTypeList"/>    
       
    </c:import>   
   