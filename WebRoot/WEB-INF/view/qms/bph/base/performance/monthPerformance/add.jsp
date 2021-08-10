<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
var index = 0;
function addItemA() {
	var arr = new Array();
	arr.push("<tr id='newArray' style='height:50px;'>");
	/* <input type="text" id="myMonth"  name="" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="<fmt:formatDate value='${voo.myMonth}' pattern='yyyy-MM'/>" readonly="true"/> */
	arr.push("<td><input type='text' name='item["+index+"].myMonth' id='myMonth' onclick='changeMonthValue(this)' placeholder='请输入日期' size='10' class='required' readonly='readonly'/></td>");
	/* arr.push("<td width='5%' style='padding-left:10px;text-align:center;'>"); */
	/*arr.push("<input  style='text-align:center;'  name='item["+index+"].checkMethod' id='checkMethod' type='text' size='10' class='required' /></td>"); */
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].monTargetValue' id='monTargetValue'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].monTotalTargetValue' id='monTotalTargetValue'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].monChallengeTargetValue' id='monChallengeTargetValue'  type='text' size='10'/></td>");
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

//检查是否有重复月份
function checkRepeat() {
	var repeatArr = new Array();
	$.each($("#newArray", $.pdialog.getCurrent()), function() {
		var myMonth = $(this).find("#myMonth").val();
		repeatArr.push(myMonth);
	});
	if(repeatArr.length < 1){
		alert("您未新增任何月度绩效考核指标");
		return false;
	}
	if(repeatArr.length > 12){
		alert("您添加的月份数量已经超过12，请删除多余记录！");
		return false;
	}
	for (var t = 0; t < repeatArr.length; t++) {
		for (var j = 0; j < repeatArr.length; j++) {
			if (t != j) {
				if (repeatArr[t] == repeatArr[j]) {
					alert("月份重复，请检查月份");
					return false;
				}
			}
		}
	}
	$("#formID", $.pdialog.getCurrent()).submit();
}

/* 该函数用来加载输入月份的控件 */
function changeMonthValue(){
	laydate({isym:true, format: 'YYYY-MM'});
}
</script>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='ptm/monthPerformanceSet/save.do?navTabId=monthPerformancelistNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${vo.id}">
		<table class="tableFormContent nowrap">	
		<tr>
			<th>工厂名称</th>
		    <td >
		    	<input type="text" id="factoryName" name="factoryName" value="${ vo.factoryName }" size="20" class="required" readonly="readonly"/>		         
		    </td>
			<th>部门名称</th>
		    <td >
		         <input type="text" id="departmentName"  name="departmentName" value="${ vo.departmentName }" size="20" class="required" readonly="readonly"/>
		    </td>
		    <th>年度</th>
		    <td >
		         <input type="text" id="checkYear"  name="" value="<fmt:formatDate value='${vo.checkYear}' pattern='yyyy'/>" size="20" class="required" readonly="readonly"/>
		    </td>
		</tr>
		<tr>   
			<th>绩效目标大类</th>
		    <td >
		         <input type="text" id="performanceTargetClass"  name="performanceTargetClass" value="${ vo.performanceTargetClass }" size="20" class="required" readonly="readonly"/>
		    </td>
		   	<th>衡量指标内容</th>
		    <td>
		       <input type="text" id="indexContent" name="indexContent" value="${ vo.indexContent }" readonly="readonly" size="20"  class="required"/>
		    </td>
		    <th>绩效类型</th>
		    <td>
		       <input type="text" id="performanceType" name="performanceType" value="${ vo.performanceType }" readonly="readonly" size="20"  class="required"/>
		    </td>	
		</tr>
		<tr>   
			<th>指标类型</th>
		    <td >
		         <input type="text" id="indexType"  name="indexType" value="${ vo.indexType }" readonly="readonly" size="20" class="required" />
		    </td>		    
		    <th>权重</th>
		    <td >
		         <input type="text" id="weight"  name="weight" value="${ vo.weight }" readonly="readonly" size="20" class="required number" style="width:50px;display:inline;"/><span style="font-weight:bold;">%</span>
		    </td>
		    <!-- 新增一个单位字段 -->
		    <th>单位</th>
		    <td >
		         <input type="text" id="company"  name="company" value="${ vo.company }" readonly="readonly" size="20" class="required" />
		    </td>
		</tr>					
		<tr> 
			<th>计算公式</th>
		    <td >
		         <input type="text" id="formula"  name="formula" value="${ vo.formula }" readonly="readonly" size="20" class="required" />
		    </td>  	    
		</tr>
		<tr>
			<th>上年度实际值</th>
		    <td >
		         <input type="text" id="lastYearRealityValue"  name="lastYearRealityValue" value="${vo.yearPerformance.lastYearRealityValue }" size="20" class="required"  readonly="readonly"/>
		    </td>
		    <th>上半年基准值</th>
		    <td >
		         <input type="text" id="firstYearReferenceValue"  name="firstYearReferenceValue" value="${vo.yearPerformance.firstYearReferenceValue }" size="20" class="required" readonly="readonly"/>
		    </td>
		    <th>本年度基准值</th>
		    <td >
		         <input type="text" id="referenceValue"  name="referenceValue" value="${vo.yearPerformance.referenceValue }" size="20" class="required" readonly="readonly"/>
		    </td>
		    
		</tr>
		<tr>		
			<th>上半年目标值</th>
		    <td >
		         <input type="text" id="firstYearTargetValue"  name="firstYearTargetValue" value="${vo.yearPerformance.firstYearTargetValue }" size="20" class="required" readonly="readonly"/>
		    </td>
		    <th>下半年目标值</th>
		    <td >
		         <input type="text" id="secondYearTargetValue"  name="secondYearTargetValue" value="${vo.yearPerformance.secondYearTargetValue }" size="20" class="required" readonly="readonly"/>
		    </td>
		    <th>本年度目标值</th>
		    <td >
		         <input type="text" id="targetValue"  name="targetValue" value="${ vo.yearPerformance.targetValue}" size="20" class="required" readonly="readonly"/>
		    </td>
		</tr>
		</table>  
<div class="panelBar">
	<ul class="toolBar">
		<shiro:hasPermission name="ptm:monthPerformancelist:ADD">
		</shiro:hasPermission>
		<li><a class="add" href="###" onclick="addItemA()"><span>新增</span></a></li>
	</ul>
</div>
<div class="pageFormContent" layoutH="250" id="content">
<table class="tableFormContent nowrap" >
<tr>
	<th width="10%" style="width:15%;text-align:center;">月份</th>       
    <!-- <th width="10%" style="width:15%;text-align:center;">考核方法</th> -->
    <th width="10%" style="width:15%;text-align:center;">当月目标值</th>       
    <th width="10%" style="width:15%;text-align:center;">当月累计目标值</th>
    <th width="10%" style="width:15%;text-align:center;">当月挑战目标值</th>
    <th width="5%" style="width:5%;text-align:center;">操作</th>
</tr>
<tbody id="printConfigsList">
  <c:forEach items="${vo.monthList }" var="voo">
    <tr id='newArray'>
       <td width="10%" style="text-align:center;">
       	<%-- <fmt:formatDate value='${voo.myMonth}' pattern='yyyy-MM'/> --%>
       	<input type="text" id="myMonth"  name="" placeholder="请输入日期" value="<fmt:formatDate value='${voo.myMonth}' pattern='yyyy-MM'/>" readonly="true"/>
       </td>
       <%-- <td width="10%" style='text-align:center;'>${voo.checkMethod }</td> --%>
       <td width="10%" style="text-align:center;">${voo.monTargetValue }</td>
       <td width="10%" style='text-align:center;'>${voo.monTotalTargetValue }</td>
       <td width="10%" style="text-align:center;">${voo.monChallengeTargetValue }</td>	       
       <td style="width:10%;text-align:center;"></td>
    </tr>
  </c:forEach>
</tbody>
     </table>
</div>			
	       	       	        
	                						
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkRepeat()">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
