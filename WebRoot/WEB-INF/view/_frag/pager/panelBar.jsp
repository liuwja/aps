<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:set var="targetType" value="${empty param.targetName ? 'navTab' : param.targetName}"/>
<div class="panelBar">
    <div class="pages">
        <span>每页</span>
        <select name="pageSize" onchange="dwzPageBreak({targetType:'${targetType}',rel:'${empty param.relDivId? '' : param.relDivId}',data:{numPerPage:this.value}})">
            <c:forEach begin="100" end="500" step="100" varStatus="s">
                <option value="${s.index}" ${page.numPerPage eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
            </c:forEach>
        </select>
        <span>条，共${page.totalCount}条 </span>
    </div>
    
    <div class="pagination" rel="${empty param.relDivId? '' : param.relDivId}" targetType="${targetType}" totalCount="${page.totalCount}" numPerPage="${page.numPerPage}" pageNumShown="0" currentPage="${page.pageNum}"></div>
</div>