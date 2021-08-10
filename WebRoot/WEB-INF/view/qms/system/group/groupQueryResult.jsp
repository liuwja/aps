<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
        <legend>待选用户列表</legend>
<div class="pageHeader">
    <div class="searchBar">
    <form onsubmit="return  divSearch(this, 'groupListDiv')" id="pagerForm" action="system/group/groupList.do" method="post">
        <table class="searchContent">
            <tr>
                <td>
                                            编号： 
                </td>
                <td>
                    <input type="hidden" name="pageNum" value="1" />
                    <input type="hidden" name="numPerPage" value="${page.numPerPage}" />
                    <input type="hidden" name="direction" value="" />                
                    <input type="text" name="groupCode" value="${param.groupCode }" size="10"/>
                </td>               
                <td>
                                            名称： 
                </td>
                <td>
                    <input type="text" name="groupName" value="${param.groupName}" size="10"/>
                </td>               
                <td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
               
                </td>   
            </tr>
        </table>
    </form>
    </div>
</div>

        <table class="table" width="100%" layoutH="225">
            <thead>
                <tr>
                    <th width="50"><input type="checkbox" class="checkboxCtrl" group="userId" ></th>
                    <th width="120">编号</th>
                    <th width="120">名称</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach items="${list}" var="o">
                    <tr target="userId" rel="${o.groupKey}" onclick="selectGroup(this,'${o.groupKey}')">
                        <td style="text-align: center;">
                            <input type="checkbox" name="chkbox" >
                        </td>
                        <td>${o.groupCode}</td>
                        <td>${o.groupName}</td>
                    </tr>       
                </c:forEach>        
            </tbody>
        </table>
        
    <c:import url="../../../_frag/pager/panelBar.jsp">
       <%-- 对话框分页时需要传此参数 --%>
       <c:param name="targetName" value="dialog"/>
       <c:param name="relDivId" value="groupListDiv"/>    
    </c:import> 