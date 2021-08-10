<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<script>
	jQuery(document).ready(function() {
		$('#testTipTxt').poshytip({
			className : 'tip-yellowsimple',
			showOn : 'focus',
			alignTo : 'target',
			alignX : 'center',
			alignY : 'top',
			offsetY : 5

		});
		$('#baseCategory_${id_end}', $.pdialog.getCurrent()).change(function() {
			getItem();
		})
	});

	var index = 0;
	function addItem() {
		var arr = new Array();
		arr.push("<tr id='newArray' style='height:50px;'>");
		arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].upperactualvalue' id='upperactualvalue'  type='text' size='10' class='required'/></td>");
		arr.push("<td width='5%' style='padding-left:10px;text-align:center;'>");
		arr.push("<input  style='text-align:center;' id='upperhalftargetvalue' name='item["+index+"].upperhalftargetvalue'  type='text' size='10' class='required' /></td>");
		arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].secondhalftargetvalue' id='secondhalftargetvalue'  type='text' size='10' class='required'/></td>");
		arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].referencevalue' id='referencevalue'  type='text' size='10' class='required'/></td>");
		arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].yeartargetvalue' id='yeartargetvalue'  type='text' size='10' class='required'/></td>");
		arr.push("<td width='5%' style='text-align:center;'><input type='button' id='delbtn'  value='删除' onclick='removeItem(this)' class='delButton' /></td>");
		arr.push("</tr>");
		var html = arr.join("");
		$("#printConfigsList").append(html);
		//$("#printConfigsList tr").last().find(".btnLook").lookup();
		index++;

	}
	function removeItem(obj) {
		$(obj).parent().parent().remove();
	}

	function checkRepeat() {
		var repeatArr = new Array();
		var repeatFArr = new Array();
		$.each($("#newArray", $.pdialog.getCurrent()), function() {
			var upperactualvalue = $(this).find("#upperactualvalue").val();
			var upperhalftargetvalue = $(this).find("#upperhalftargetvalue").val();
			var secondhalftargetvalue = $(this).find("#secondhalftargetvalue").val();
			var referencevalue = $(this).find("#referencevalue").val();
			var yeartargetvalue = $(this).find("#yeartargetvalue").val();
			repeatArr.push(upperactualvalue);
			repeatFArr.push(upperhalftargetvalue);
			repeatFArr.push(secondhalftargetvalue);
			repeatFArr.push(referencevalue);
			repeatFArr.push(yeartargetvalue);
		});
		for (var t = 0; t < repeatArr.length; t++) {
			for (var j = 0; j < repeatArr.length; j++) {
				if (t != j) {
					if (repeatArr[t] == repeatArr[j]) {
						alert("项目名称重复！请检查！");
						return false;
					}
				}
			}
		}
		for (var t = 0; t < repeatFArr.length; t++) {
			for (var j = 0; j < repeatFArr.length; j++) {
				if (t != j) {
					if (repeatFArr[t] == repeatFArr[j]) {
						alert("项目代码！请检查！");
						return false;
					}
				}
			}
		}

		$("#formID", $.pdialog.getCurrent()).submit();
	}
</script>
<div class="pageContent">
	<form method="post" id="formID"
		action="<c:url value='per/item/save.do?navTabId=perItemNav&callbackType=closeCurrent'/>"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<table class="tableFormContent nowrap">
				<tr>
					<th>部门</th>
					<td><input type="text" name="department" value="${vo.departmentName}" readonly="readonly"> 
					<input type="hidden" name="id" value="${vo.id}">
					</td>
					<th>绩效目标大类</th>
					<td><input type="text" name="targetclass" value="${vo.targetclass}" readonly="required"></td>
				</tr>
				<tr><th>绩效指标内容</th>
				<td colspan="3">
				<input type="text" name="indexcontent" value="${vo.indexcontent }" readonly="readonly"></td>
				</tr>
			</table>

			<div class="panelBar">
				<ul class="toolBar">
					<shiro:hasPermission name="per:item:ADD">
					</shiro:hasPermission>
					<li><a class="add" href="###" onclick="addItem()"><span>新增</span></a></li>
					<shiro:hasPermission name="per:item:ADD">
					</shiro:hasPermission>
				</ul>
			</div>
			<div class="pageFormContent" layoutH="250" id="content">
				<table class="tableFormContent nowrap" >
	            <tr>
	            	<th width="10%" style="width:15%;text-align:center;">上年度实际值</th>       
	                <th width="10%" style="width:15%;text-align:center;">上半年目标值</th>
	                <th width="10%" style="width:15%;text-align:center;">下半年目标值</th>       
	                <th width="10%" style="width:15%;text-align:center;">本年基准值</th>
	                <th width="10%" style="width:15%;text-align:center;">本年目标值</th>
	                <th width="5%" style="width:5%;text-align:center;">操作</th>
	            </tr>
	            <tbody id="printConfigsList">
	              <c:forEach items="${item }" var="o">
	                <tr id='newArray'>
	                   <td width="10%" style="text-align:center;">${o.upperactualvalue }</td>
	                   <td width="10%" style='text-align:center;'>${o.upperhalftargetvalue }</td>
	                    <td width="10%" style="text-align:center;">${o.secondhalftargetvalue }</td>
	                   <td width="10%" style='text-align:center;'>${o.referencevalue }</td>
	                    <td width="10%" style="text-align:center;">${o.yeartargetvalue }</td>	       
	                   <td style="width:10%;text-align:center;"></td>
	                </tr>
	              </c:forEach>
				</tbody>

	        </table>
        </div>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
