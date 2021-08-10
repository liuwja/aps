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
    <form onsubmit="return  divSearch(this, 'toSelectPartList')" id="pagerForm" action="quality/testInstance/partSelect.do" method="post">

    <div class="searchBar">
        <table class="searchContent">

	        <tr>
	                <td class="dateRange">
											物料号:                     
						<input type="hidden" name="pageNum" value="1" />
					    <input type="hidden" name="numPerPage" value="${page.numPerPage}" />
					    <input type="hidden" name="direction" value="" /> 
					    <input type="hidden" name="flag" value="1" />  	              
	                </td>
	                <td >
						<input type="text" name="partNumber" id="partNumber" size="10" value="${partNumber}"/>
					
	                </td>   
	                <td class="dateRange">
						物料名称:
	                </td>                                      
	                <td>
						<input type="text" name="partName" id="partName" size="10" value="${partName}"/>
	                </td> 
	        </tr>
	        <tr>
	        	<td class="dateRange">是否关键件</td>
	        	<td>
	        		<select id="isConsumed" name="isConsumed" style="width : 78px">
	        			<option value="">全选</option>
						<option value="是" ${isConsumed eq '是' ? 'selected':''}>是</option>
						<option value="否" ${isConsumed eq '否' ? 'selected':''}>否</option>
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

        <table id="partSelectTable" class="table" width="100%" layoutH="170">
            <thead>
                <tr>
                    <th width="10%"><input id="checkBoxAll" type="checkbox" onclick="selectAllRow()" class="checkboxCtrl" group="id" ></th>
                    <th width="20%">物料号</th>
                    <th width="30%">物料名称</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach items="${list}" var="o">
                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.partNumber}','${o.partName}')">
                        <td style="text-align: center;">
                            <input id="${o.id}" type="checkbox" name="chkbox" value="${o.idNameNumber}">
                        </td>
	                        <td>${o.partNumber}</td>
	                        <td>${o.partName}</td>
                    </tr>       
                </c:forEach>        
            </tbody>
        </table>
        
    <c:import url="../../../_frag/pager/panelBar.jsp">
       <%-- 对话框分页时需要传此参数 --%>
       <c:param name="targetName" value="dialog"/>
       <c:param name="relDivId" value="toSelectPartList"/>    
       
    </c:import>   
   