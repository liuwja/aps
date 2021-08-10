<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div  class="pageHeader">
待选列表
</div>
<div class="pageHeader">
    <form onsubmit="return  divSearch(this, 'toSelectFaultReasonList')" id="pagerForm" action="qms/commonSelect/moreMeshFaultSelectResult.do" method="post">

    <div class="searchBar">
        <table class="searchContent">

	        <tr>
                <td class="dateRange">合并故障名称:</td>
				<td><input type="text" name="meshFaultName"
					id="meshFaultName" size="10" value="${vo.meshFaultName}" /> <input
					type="hidden" name="pageNum" value="1" /> <input type="hidden"
					name="numPerPage" value="${page.numPerPage}" /> <input
					type="hidden" name="direction" value="" /></td>
				<td>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">查找</button>
						</div>
					</div>
				</td>
	        </tr>
        </table>
    </div>
    </form>
</div>

        <table class="table" width="100%" layoutH="150">
            <thead>
                <tr>
                   <th></th>
				   <th>合并故障名称</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach items="${list}" var="o" varStatus="status">
					 <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.meshFaultName}')">
                        <td style="text-align: center;">
                            <input type="checkbox" name="chkbox">
                        </td>
                        <td>${o.meshFaultName}</td>
                    </tr>       
				</c:forEach>
            </tbody>
        </table>
        
    <c:import url="../../_frag/pager/panelBar.jsp">
       <%-- 对话框分页时需要传此参数 --%>
       <c:param name="targetName" value="dialog"/>
       <c:param name="relDivId" value="toSelectFaultReasonList"/>    
       
    </c:import>   
   