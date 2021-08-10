<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
var index = 0;
function addItemA() {
	var arr = new Array();
	arr.push("<tr id='newArray' style='height:50px;'>");
	/* <input type="text" id="myMonth"  name="" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="<fmt:formatDate value='${voo.myMonth}' pattern='yyyy-MM'/>" readonly="true"/> */
	arr.push("<td><input type='text' name='item["+index+"].myMonth' id='myMonth' onclick='changeMonthValue(this)' placeholder='请输入日期' size='10' class='required' readonly='readonly'/></td>");
	arr.push("<td width='5%' style='padding-left:10px;text-align:center;'>");
	arr.push("<input  style='text-align:center;'  name='item["+index+"].checkMethod' id='checkMethod' type='text' size='10' class='required' /></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].monTargetValue' id='monTargetValue'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].monTotalTargetValue' id='monTotalTargetValue'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].monChallengeTargetValue' id='monChallengeTargetValue'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].monRealityTargetValue' id='monRealityTargetValue'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].monRealityTotalTargetValue' id='monRealityTotalTargetValue'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].monRealityChallengeTargetValue' id='monRealityChallengeTargetValue'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].checkResult' id='checkResult'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].column1' id='column1'  type='text' size='10' class='required'/></td>");
	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='item["+index+"].column2' id='column2'  type='text' size='10' class='required'/></td>");
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
	if(repeatArr.length > 12){
		alert("您添加的月份数量已经超过12，请删除多余记录！");
		return false;
	}
	console.log(repeatArr);
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
	<form method="post" id="formID" action="<c:url value='ptm/monthPerformanceResult/delete.do?navTabId=monthPerformanceResultlistNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${vo.id}">
		<input type="hidden" name="factoryNumber" value="${vo.factoryNumber}">
		<input type="hidden" name="departmentNumber" value="${vo.departmentNumber}">
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
		    <th>指标类型</th>
		    <td >
		         <input type="text" id="indexType"  name="indexType" value="${ vo.indexType }" readonly="readonly" size="20" class="required" />
		    </td>
		</tr>
		<tr>   		    
		    <th>权重</th>
		    <td >
		         <input type="text" id="weight"  name="weight" value="${ vo.weight }" readonly="readonly" size="20" class="required number" />
		    </td>
		    <!-- 新增一个单位字段 -->
		    <th>单位</th>
		    <td >
		         <input type="text" id="company"  name="company" value="${ vo.company }" readonly="readonly" size="20" class="required" />
		    </td>
		    <th>计算公式</th>
		    <td >
		         <input type="text" id="formula"  name="formula" value="${ vo.formula }" readonly="readonly" size="20" class="required" />
		    </td>
		</tr>					
		<tr>   
		 	<th>上年度实际值</th>
		    <td >
		         <input type="text" id="lastYearRealityValue"  name="lastYearRealityValue" value="${ vo.yearPerformance.lastYearRealityValue}" readonly="readonly" size="20" class="required" />
		    </td>
		    <th>本年度基准值</th>
		    <td >
		         <input type="text" id="referenceValue"  name="referenceValue" value="${ vo.yearPerformance.referenceValue}" readonly="readonly" size="20" class="required" />
		    </td>
		    <th>本年度目标值</th>
		    <td >
		         <input type="text" id="targetValue"  name="targetValue" value="${ vo.yearPerformance.targetValue}" readonly="readonly" size="20" class="required" />
		    </td>		    
		</tr>
		<tr>
			<th>上半年目标值</th>
		    <td >
		         <input type="text" id="firstYearTargetValue"  name="firstYearTargetValue" value="${ vo.yearPerformance.firstYearTargetValue }" readonly="readonly" size="20" class="required"/>
		    </td>
		    <th>下半年目标值</th>
		    <td >
		         <input type="text" id="secondYearTargetValue"  name="secondYearTargetValue" value="${ vo.yearPerformance.secondYearTargetValue }" readonly="readonly" size="20" class="required"/>
		    </td>
		</tr>
<!-- 		<tr>
			<th>月份</th>
			<td>
				<input type="text" class="required" id="myMonth" name="myMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" />
			</td>
		    <th>考核方法</th>
		    <td >
		         <input type="text" id="checkMethod"  name="checkMethod" value="" size="20" class="required"/>
		    </td>
		</tr>
		<tr>
			<th>当月目标值</th>
		    <td >
		         <input type="text" id="monTargetValue"  name="monTargetValue" value="" size="20" class="required"/>
		    </td>
		    <th>当月累计目标值</th>
		    <td >
		         <input type="text" id="monTotalTargetValue"  name="monTotalTargetValue" value="" size="20" class="required"/>
		    </td>
		    <th>当月挑战目标值</th>
		    <td >
		         <input type="text" id="monChallengeTargetValue"  name="monChallengeTargetValue" value="" size="20" class="required"/>
		    </td>
		</tr> -->
	</table>  
<div class="pageFormContent" layoutH="250" id="content">
<table class="tableFormContent nowrap" >
<tr>
	<th></th>
	<th width="" style="text-align:center;">月份</th>       
    <th width="" style="text-align:center;">考核方法</th>
    <th width="" style="text-align:center;">当月目标值</th>       
    <th width="" style="text-align:center;">当月累计目标值</th>
    <th width="" style="text-align:center;">当月挑战目标值</th>
    <th width="" style="text-align:center;">当月实际目标值</th> 
    <th width="" style="text-align:center;">当月实际累计目标值</th> 
    <th width="" style="text-align:center;">当月实际挑战目标值</th> 
    <th width="" style="text-align:center;">考核结果</th> 
    <th width="" style="text-align:center;">状态</th> 
    <th width="" style="text-align:center;">评级</th> 
</tr>
<tbody id="printConfigsList">
  <c:forEach items="${vo.monthList }" var="voo" varStatus="vs">
    <tr id='newArray'>
       <td>
       		<input width="50" type="checkbox" id="toDel" name="toDel" value="${ voo.id }"/>
       		<input width="50" type="hidden" id="" name="item[${vs.index }].id" value="${ voo.id }"/>
       </td>
       <td width="" style="text-align:center;">
       <input type="text" id="myMonth"  name="" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="<fmt:formatDate value='${voo.myMonth}' pattern='yyyy-MM'/>"/>
       </td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].checkMethod" value="${ voo.checkMethod }" readonly="readonly"/></td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].monTargetValue" value="${ voo.monTargetValue }" readonly="readonly"/></td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].monTotalTargetValue" value="${ voo.monTotalTargetValue }" readonly="readonly"/></td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].monChallengeTargetValue" value="${ voo.monChallengeTargetValue }" readonly="readonly"/></td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].monRealityTargetValue" value="${ voo.monRealityTargetValue }"/></td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].monRealityTotalTargetValue" value="${ voo.monRealityTotalTargetValue }"/></td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].monRealityChallengeTargetValue" value="${ voo.monRealityChallengeTargetValue }"/></td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].checkResult" value="${ voo.checkResult }"/></td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].column1" value="${ voo.column1 }"/></td>
       <td width="" style="width:20px;text-align:center;"><input type="text" id="" name="item[${vs.index }].column2" value="${ voo.column2 }"/></td>       
    </tr>
  </c:forEach>
</tbody>
     </table>
</div>			
	       	       	        
	                						
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkRepeat()">删除</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
