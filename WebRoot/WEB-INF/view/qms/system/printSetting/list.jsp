<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/printSetting.sp?method=list" method="post">
    <div class="searchBar">
        <table class="searchContent">
            <tr>
                <td>
                                        产品代码： 
                </td>
                <td>
                    <input type="text" name="partNumber" value="${param.partNumber}"/>
                </td>               
                <th>模板类型：</th>
                <td>
                   <select name="templateType" id="templateType">
                        <option value="">--请选择--</option>
                        <option value="主机条码" <c:if test="${param.templateType == '主机条码'}">selected="selected"</c:if> >主机条码</option>
                        <option value="包装铭牌" <c:if test="${param.templateType == '包装铭牌'}">selected="selected"</c:if>>包装铭牌</option>
                   </select> 
                   <script type="text/javascript">
                   </script>               
                </td>  
                <td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
                </td>           

        </table>
    </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <shiro:hasPermission name="sys:system:printConfig:ADD">
                <li><a class="add" href="system/printSetting.sp?method=newConfig"  target="dialog" height="450" width="600" mask="true" rel="newPrtConfig" title="新增-打印配置"><span>新增</span></a></li>
            </shiro:hasPermission>  
            <shiro:hasPermission name="sys:system:printConfig:EDIT">
                <li><a class="edit" href="system/printSetting.sp?method=editConfig&key={key}"  target="dialog" height="450" width="600" mask="true" rel="editPrtconfig" title="修改-打印配置"><span>修改</span></a></li>
            </shiro:hasPermission>  
            <shiro:hasPermission name="sys:system:printConfig:DEL">
               <li><a class="delete" href="system/printSetting.sp?method=delete&key={key}"  target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a></li>            
             </shiro:hasPermission>  
        </ul>
    </div>
    <table class="list" width="100%" layoutH="97">
        <thead>
            <tr>
                <th width="5%">选择</th>
                <th width="25%">条码模板</th>
                <th width="25%">产品名称</th>
                <th width="25%">产品代码</th>
                <th width="20%">模板类型</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="o">
                <tr target="key" rel="${o.key }">
                    <td>
                        <input type="radio" group="code" name="key" value="${o.key }">
                    </td>
                    <%--需维护资料--%>
                    <td>${o.barcodeTemplate}</td>
                    <td>${o.partDescription}</td>
                    <td>${o.partNumber}</td>
                    <td>${o.templateType}</td>
                </tr>       
            </c:forEach>
        </tbody>
    </table>
    <c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>
