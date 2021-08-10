<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<legend>待选用户列表</legend>
<div class="pageHeader">
    <div class="searchBar">
    <form onsubmit="return  divSearch(this, 'usersListDiv')" id="pagerForm" action="system/user/queryUsers.do" method="post">
        <table class="searchContent">
            <tr>
                <td>
                                            用户名： 
                </td>
                <td>
                    <input type="hidden" name="pageNum" value="1" />
                    <input type="hidden" name="numPerPage" value="${page.numPerPage}" />
                    <input type="hidden" name="direction" value="" />
                    <input type="text" name="userName" value="${param.userName}" size="10"/>
                </td>               
                <td>
                                            姓名： 
                </td>
                <td>
                    <input type="text" name="name" value="${param.name}" size="10"/>
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
                    <th width="120">用户号</th>
                    <th width="120">用户姓名</th>
                    <th width="120">工厂</th>
                    <th width="120">车间</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach items="${allUsers}" var="o">
                    <tr target="userId" rel="${o.userKey}" onclick="selectUser(this,'${o.userKey}')">
                        <td style="text-align: center;">
                            <input type="checkbox" name="chkbox" >
                        </td>
                        <td>${o.userName}</td>
                        <td>${o.description}</td>
                        <td>${o.uda1}</td>
                        <td>${o.uda2}</td>                        
                    </tr>       
                </c:forEach>        
            </tbody>
        </table>
        
    <c:import url="../../../_frag/pager/panelBar.jsp">
       <%-- 对话框分页时需要传此参数 --%>
       <c:param name="targetName" value="dialog"/>
       <c:param name="relDivId" value="usersListDiv"/>    
    </c:import> 
