<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/include.inc.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<script type="text/javascript">
    $(function() {
        $("#type", navTab.getCurrentPanel()).val("${smtLog.type}");
    });
</script>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/smtLog/list.do" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        类型：
                    </td>
                    <td>
                        <select id="type" name="type">
                            <option value="">所有</option>
                            <option value="上料记录">上料记录</option>
                            <option value="物料追溯">物料追溯</option>
                            <option value="抛料率">抛料率</option>
                        </select>
                    </td>
                    <td>创建时间：</td>
                    <td>
                        <input name="createStartTime" type="text" placeholder="请选择开始时间" size="15" readonly="readonly"
                               onclick="laydate({istime:true,format:'YYYY/MM/DD hh:mm:ss'})" value="${smtLog.createStartTime }"/>
                        <span>至</span>
                        <input name="createEndTime" type="text" placeholder="请选择结束时间" size="15" readonly="readonly"
                               onclick="laydate({istime:true,format:'YYYY/MM/DD hh:mm:ss'})" value="${smtLog.createEndTime }"/>
                    </td>
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
<div class="pageContent">
    <table class="table" width="100%" layoutH="93">
        <thead>
            <tr>
                <th>类型</th>
                <th>对象</th>
                <th width="60%">失败原因</th>
                <th>创建时间</th>
            </tr>
        </thead>
        <tbody align="center">
        <c:forEach items="${list}" var="o">
            <tr>
                <td>${o.type}</td>
                <td>${o.objectName}</td>
                <td>${o.failureReason}</td>
                <td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>