<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="direction" value="" />
</form>