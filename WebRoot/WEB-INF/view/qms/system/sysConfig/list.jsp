<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<%--
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" rel="pagerForm" action="im/shiftGroup.sp?method=list" method="post">
    <div class="searchBar">
        <table class="searchContent">
            <tr>
                <td>
                    班组编号： 
                </td>
                <td>
                    <input type="text" name="groupName" value="${param.groupName }"/>
                </td>               
                <td>
                    班组名称：
                </td>
                <td>
                    <input type="text" name="groupDescription" value="${param.groupDescription}"/> 
                </td>
                <td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
                </td>           

        </table>
    </div>
    </form>
</div>
 --%>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <shiro:hasPermission name="sys:system:config:ADD">
                <li><a class="add" href="system/sysConfig.sp?method=newConfig"  target="dialog" height="250" width="600" mask="true" rel="newSysConfig" title="新增-系统配置"><span>新增</span></a></li>
            </shiro:hasPermission>  
            <shiro:hasPermission name="sys:system:config:EDIT">
                <li><a class="edit" href="system/sysConfig.sp?method=editConfig&key={key}"  target="dialog" height="250" width="600" mask="true" rel="editSysconfig" title="修改-系统配置"><span>修改</span></a></li>
            </shiro:hasPermission>  
        </ul>
    </div>
    <table class="list" width="100%" layoutH="56">
        <thead>
            <tr>
                <th width="5%">选择</th>
                <th width="45%">属性名称</th>
                <th width="50%">属性值</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="o">
                <tr target="key" rel="${o.key }">
                    <td>
                        <input type="radio" group="code" name="key" value="${o.key }">
                    </td>
                    <%--需维护资料--%>
                    <td>${o.propertyName}</td>
                    <td>${o.propertyValue}</td>
                </tr>       
            </c:forEach>
        </tbody>
    </table>
    <c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>
