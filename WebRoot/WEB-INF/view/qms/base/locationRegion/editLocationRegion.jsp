<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/locationRegion/updateLocationRegion.do?navTabId=locationRegionListNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" id="id" value="${locationRegion.id}">
		<table class="tableFormContent nowrap">
			<tr >
                <th>仓库编码：</th>
                <td><input name="locationCode" type="text" size="30" class="required" value="${locationRegion.locationCode}" /></td>
            </tr>
            <tr >
                <th>仓库名称：</th>
                <td><input name="location" type="text" size="30" class="required" value="${locationRegion.location}" /></td>
            </tr>
            <tr >
                <th>服务中心名称：</th>
                <td><input name="region" type="text" size="30" class="required" value="${locationRegion.region}" /></td>
            </tr>
           <tr >
                <th>所在省份：</th>
                <td><input name="province" type="text" size="30" class="required" value="${locationRegion.province}" /></td>
            </tr>
            
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
