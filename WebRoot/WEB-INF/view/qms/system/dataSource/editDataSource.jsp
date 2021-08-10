<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" id="addDataSourceForm" class="pageForm required-validate" action="<c:url value='system/dataSource/update.do?navTabId=dataSourceListNav&callbackType=closeCurrent'/>" onsubmit="return validateCallback(this, dialogAjaxDone);">	<div class="pageFormContent" layoutH="56">
		<div class="pageFormContent">
			<input type="hidden" name="id" id="id" value="${vo.id}">
			<table class="tableFormContent nowrap">
				<tr>
					<th>第一级菜单：</th>
					<td>
						<select name="accordion" onchange="selectFolder()">
							<option value="主页">主页</option>
							<c:forEach items="${accordionNameList}" var="o">
								<option value="${o}" ${vo.accordion eq o ? 'selected':''}>${o}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>第二级菜单：</th>
					<td>
						<select name="folder" id="dataSourceFolder" onchange="selectMenu()">
							<c:forEach items="${folderNameList}" var="o">
								<option value="${o}" ${vo.folder eq o ? 'selected':''}>${o}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>第三级菜单：</th>
					<td>
						<select name="menu" id="dataSourceMenu">
							<c:forEach items="${menuNameList}" var="o">
								<option value="${o}" ${vo.menu eq o ? 'selected':''}>${o}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>图表类型：</th>
					<td>
						<select name="chartType" id="dataSourceChartType">
							<option value=""></option>
							<option value="排列图" ${vo.chartType eq '排列图' ? 'selected':''}>排列图</option>
							<option value="象限图" ${vo.chartType eq '象限图' ? 'selected':''}>象限图</option>
							<option value="三角阵" ${vo.chartType eq '三角阵' ? 'selected':''}>三角阵</option>
							<option value="对比图" ${vo.chartType eq '对比图' ? 'selected':''}>对比图</option>
							<option value="趋势图" ${vo.chartType eq '趋势图' ? 'selected':''}>趋势图</option>
							<option value="时序图" ${vo.chartType eq '时序图' ? 'selected':''}>时序图</option>
							<option value="P控图" ${vo.chartType eq 'P控图' ? 'selected':''}>P控图</option>
							<option value="地图" ${vo.chartType eq '地图' ? 'selected':''}>地图</option>
							<option value="数据明细" ${vo.chartType eq '数据明细' ? 'selected':''}>数据明细</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>说明：</th>
					<td>
						<textarea rows="8" cols="100" name="description" overflow:auto>${vo.description}</textarea>
					</td>
				</tr>
			</table>
		</div>		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
<script>
jQuery(document).ready(function() {
	selectFolder();
});

function selectFolder() {
	var accordion = $("select[name='accordion']").val();
	var map = "${map}";
	var folder = "${vo.folder}";
	var select = $("#dataSourceFolder");
	select.empty();
	<c:forEach items="${map}" var="o">
		var key = '${o.key}';
		if(key == accordion) {
			<c:forEach items="${o.value}" var="folderNameList">
				<c:forEach items="${folderNameList}" var="folderName">
					if (folder != null && folder != undefined && folder == "${folderName.key}") {
						select.append("<option value='${folderName.key}' selected>${folderName.key}</option>");
					} else {
						select.append("<option value='${folderName.key}'>${folderName.key}</option>");
					}
				</c:forEach>
			</c:forEach>
		}
	</c:forEach>
	selectMenu();
}

function selectMenu() {
	var folder = $("select[name='folder']").val();
	var map = "${map}";
	var menu = "${vo.menu}";
	var select = $("#dataSourceMenu");
	select.empty();
	<c:forEach items="${map}" var="o">
		<c:forEach items="${o.value}" var="folderNameList">
			<c:forEach items="${folderNameList}" var="folderNameMap">
				var key = '${folderNameMap.key}';
				if(key == folder) {
					<c:forEach items="${folderNameMap.value}" var="menuName">
						if (menu != null && menu != undefined && menu == "${menuName}") {
							select.append("<option value='${menuName}' selected>${menuName}</option>");
						} else {
							select.append("<option value='${menuName}'>${menuName}</option>");
						}
					</c:forEach>
				}
			</c:forEach>
		</c:forEach>
	</c:forEach>
}
</script>