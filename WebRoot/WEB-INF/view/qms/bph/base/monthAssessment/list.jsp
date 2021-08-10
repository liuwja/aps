<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
#monthAssesmontListTbody a{
color:blue;
}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
    //jQuery("#formID").validationEngine();	
});
function exportExcel(url){    

	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var myInput;
	
	var objs = $("#monthAssessmentForm input",navTab.getCurrentPanel());
	var objs2 = $("#monthAssessmentForm select",navTab.getCurrentPanel());
	
	for(var i = 0 ; i< objs.length+objs2.length ; i++){
		var $obj = null;
		if(i>=objs.length){
			$obj = $(objs2[i-objs.length]);	
		}else{
			$obj = $(objs[i]);			
		}
		var v = $obj.val();
		if(v==null || v==""){
			v="";
		}
		if($obj.attr("type")=="checkbox"){
			if(!$obj.attr("checked")){
				v="";
			};
		}
		
		myInput = document.createElement("input");
		myInput.setAttribute("name", $obj.attr("name"));
		myInput.setAttribute("value", v);
		myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
}
function monthDelBath(){
	  var selKey = $("#monthAssesmontListTbody", navTab.getCurrentPanel()).find("input[type=radio]:checked").val();
	  if(selKey==null || selKey=='')
	  {
		  alertMsg.info("请选择一条记录！");
		  return false;
	  }
	  var selectStr = "";
	  $("#monthAssesmontListTbody ", navTab.getCurrentPanel()).find("#"+selKey).each(function(){
		  selectStr += $(this).find("#maKey").val()+",";
	  });
	 // alert(selectStr);
	  alertMsg.confirm("确定删除吗？", {
          okCall: function(){
			    var url = "base/monthAssesment/deleteBatch.do?selectKey="+selectStr;
			    $.post(url,function(data){
			    	if(data.result==0){
			    		alertMsg.info("删除成功!");
			    		$("#monthAssessmentForm",navTab.getCurrentPanel()).submit();
			    	}else{
			    		alertMsg.info("删除失败！"+data.msg);
			    	}
			    });
          }
      });
}
</script>

<div class="pageHeader">
	<form id="monthAssessmentForm"  onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/monthAssesment/list.do" method="post">
	<div class="searchBar">
	<!--1、dispalyType:doubleLine表示双行，在新增编辑页面用，inline表示单行，在列表用  -->
	<!--2、 1表示显示，0表示不显示 -->
	<!--3、 1表示value为编号，0表示value为 名称-->
	<!--4、 isRequired表示必填与否，true、false，用于新增、编辑的时候-->
		<table class="searchContent">
			<tr>
			    <jsp:include page="../../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="1" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="1" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
				</jsp:include>
				<td>月份</td>	
				<td>
				    <input type="text" id="monthly"  name="monthly" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${param.monthly }" readonly="true"/>
				</td>			   

				<td><div class="buttonActive"><div class="buttonContent"><button type="submit" >查找</button></div></div>
				</td>
							
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
<shiro:hasPermission name="base:monthAssesment:ADD">
		    <li><a class="add" href="base/monthAssesment/add.do"  mask="true" target="dialog" height="600" width="1000"  title="新增月度考核基准"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:monthAssesment:ADD">
		  	<li><a class="add" href="base/monthAssesment/monthAssessmentImportInit.do"  target="navTab"  tabId="monthAssessmentImportTab"  title="导入excel（月度考核基准）"><span>导入excel</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:monthAssesment:ADD">
		  	<li><a class="add" href="#" onclick="exportExcel('base/monthAssesment/excelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a></li>
</shiro:hasPermission>
<!--  
<shiro:hasPermission name="sys:item:EDIT">
			<li><a class="edit" href="base/item/edit.do?gcKey={code_carrier}"  mask="true" target="dialog" height="300" width="800"   title="修改-考核项目"><span>修改</span></a></li>
</shiro:hasPermission>
-->	
<shiro:hasPermission name="base:monthAssesment:DEL">
			<li><a class="delete" href="#" onclick="monthDelBath()"  ><span>删除</span></a></li>
</shiro:hasPermission>	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th width="3%"></th>
   		        <th width="6%">工厂</th>
			    <th width="6%">车间</th>
				<th width="6%">班组类别</th>
                <th width="6%">班组名称</th>	
				<th width="5%">项目代码</th>
				<th width="6%">考核项目</th>
				<th width="5%">指标代码</th>
				<th width="14%">考核指标</th>
                <th width="6%">月份</th>	
				<th width="4%">项目比例</th>
				<th width="4%">指标比例</th>
				<th width="4%">是否关键指标</th>
				<th width="5%">基准值</th>
				<th width="5%">目标值</th>
				<th width="8%">创建时间</th>
				<th width="6%">操作</th>
			</tr>
		</thead>
		<tbody id="monthAssesmontListTbody">
		 <c:forEach items="${list}" var="o" varStatus="s">
			<tr id="${o.groupKey }_${o.uigroupCategory.gcKey}" target="code_carrier" rel="${o.uigroupCategory.gcKey}" >
				<td rowspan="${o.uigroupCategory.monthNum}">
					<input type="radio" group="code" name="gcKey" value="${o.groupKey }_${o.uigroupCategory.gcKey}">
				</td>
				<td  rowspan="${o.uigroupCategory.monthNum}">${o.uigroupCategory.factory}</td>
				<td  rowspan="${o.uigroupCategory.monthNum}">${o.uigroupCategory.area}</td>
				<td  rowspan="${o.uigroupCategory.monthNum}">${o.uigroupCategory.category}</td>
				<td  rowspan="${o.uigroupCategory.monthNum}">${o.groupName}</td>
				     <c:forEach items="${o.uigroupCategory.item}" var="vo" begin="0" end="0">
					   <td rowspan="${vo.monthNum}">${vo.itemCode}</td>
				       <td rowspan="${vo.monthNum}">${vo.itemName}</td>
		 			    <c:forEach items="${vo.uiindexs }" var="in" begin="0" end="0">
					      <td  rowspan="${in.monthNum}">${in.indexCode }</td>
					      <td  rowspan="${in.monthNum}">${in.indexName }</td>
					       <c:forEach items="${in.monthAssessments }" var="mon" begin="0" end="0">
					            <td>${mon.monthly } <input type="hidden" id="maKey" value="${mon.maKey}"></td>
					            <td>${mon.itemScale } </td>
					            <td>${mon.indexScale } </td>
					            <td>${mon.indexMainkey } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					            <td><fmt:formatDate value="${mon.createTime }" type="date" pattern="yyyy-MM-dd"/>  </td>
					          <td>
					          <shiro:hasPermission name="base:monthAssesment:EDIT"> 
					            <a class="edit" href="base/monthAssesment/edit.do?groupKey=${o.groupKey }&itemKey=${vo.itemKey}&indexKey=${in.indexKey}&maKey=${mon.maKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          </shiro:hasPermission>  
					          <shiro:hasPermission name="base:monthAssesment:DEL"> 
					            <a class="delete" href="base/monthAssesment/delete.do?maKey=${mon.maKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          </shiro:hasPermission>
					         </td>
					       </c:forEach>
					       <c:if test="${in.monthNum ==0}">
					            <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    
					       </c:if>
					       <c:forEach items="${in.monthAssessments }" var="mon1" begin="1">
					          <tr id="${o.groupKey }_${o.uigroupCategory.gcKey}"> 
					            <td>${mon1.monthly } <input type="hidden" id="maKey" value="${mon1.maKey}"></td>
					            <td>${mon1.itemScale } </td>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.indexMainkey } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					            <td><fmt:formatDate value="${mon1.createTime }" type="date" pattern="yyyy-MM-dd"/> </td>
					            <td>
						          <shiro:hasPermission name="base:monthAssesment:EDIT"> 
						            <a class="edit" href="base/monthAssesment/edit.do?groupKey=${o.groupKey }&itemKey=${vo.itemKey}&indexKey=${in.indexKey}&maKey=${mon1.maKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
						          </shiro:hasPermission>  
						          <shiro:hasPermission name="base:monthAssesment:DEL"> 
						            <a class="delete" href="base/monthAssesment/delete.do?maKey=${mon1.maKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
						          </shiro:hasPermission>
						         </td>
					          </tr>
					       </c:forEach>
					      
					    </c:forEach>
					    
					    <c:if test="${vo.monthNum == 0}">
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
						<td> </td>
						<td> </td>
					    </c:if>
			 		   
					    <c:forEach items="${vo.uiindexs }" var="in2" begin="1">
					    <tr id="${o.groupKey }_${o.uigroupCategory.gcKey}"> 
					      <td  rowspan="${in2.monthNum}">${in2.indexCode }</td>
					      <td  rowspan="${in2.monthNum}">${in2.indexName }</td>
					      <c:forEach items="${in2.monthAssessments }" var="mon" begin="0" end="0">
					            <td>${mon.monthly } <input type="hidden" id="maKey" value="${mon.maKey}"></td>
					            <td>${mon.itemScale } </td>
					            <td>${mon.indexScale } </td>
					            <td>${mon.indexMainkey } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					            <td><fmt:formatDate value="${mon.createTime }" type="date" pattern="yyyy-MM-dd"/></td>
					            <td>
						          <shiro:hasPermission name="base:monthAssesment:EDIT"> 
						            <a class="edit" href="base/monthAssesment/edit.do?groupKey=${o.groupKey }&itemKey=${vo.itemKey}&indexKey=${in2.indexKey}&maKey=${mon.maKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
						          </shiro:hasPermission>  
						          <shiro:hasPermission name="base:monthAssesment:DEL"> 
						            <a class="delete" href="base/monthAssesment/delete.do?maKey=${mon.maKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
						          </shiro:hasPermission>
						         </td>
					      </c:forEach>
					      <c:if test="${in2.monthNum ==0}">
					            <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    
					       </c:if>
					       <c:forEach items="${in2.monthAssessments }" var="mon1" begin="1">
					           <tr id="${o.groupKey }_${o.uigroupCategory.gcKey}">
					            <td>${mon1.monthly } <input type="hidden" id="maKey" value="${mon1.maKey}"></td>
					            <td>${mon1.itemScale } </td>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.indexMainkey } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					            <td><fmt:formatDate value="${mon1.createTime }" type="date" pattern="yyyy-MM-dd"/> </td>
					            <td>
						          <shiro:hasPermission name="base:monthAssesment:EDIT"> 
						            <a class="edit" href="base/monthAssesment/edit.do?groupKey=${o.groupKey }&itemKey=${vo.itemKey}&indexKey=${in2.indexKey}&maKey=${mon1.maKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
						          </shiro:hasPermission>  
						          <shiro:hasPermission name="base:monthAssesment:DEL"> 
						            <a class="delete" href="base/monthAssesment/delete.do?maKey=${mon1.maKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
						          </shiro:hasPermission>
						         </td>
					           </tr>
					       </c:forEach>
					    </tr> 
					    </c:forEach>
					    
					</c:forEach>	
					<c:if test="${o.uigroupCategory.monthNum == 0}">
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    </c:if>	 
			     </tr>		
			
				     <c:forEach items="${o.uigroupCategory.item}" var="it" begin="1" >
				        <tr id="${o.groupKey }_${o.uigroupCategory.gcKey}"> 
					       <td rowspan="${it.monthNum}">${it.itemCode}</td>
				           <td rowspan="${it.monthNum}">${it.itemName}</td>
					       
						<c:forEach items="${it.uiindexs }" var="ind" begin="0" end="0">
					      <td  rowspan="${ind.monthNum}">${ind.indexCode }</td>
					      <td  rowspan="${ind.monthNum}">${ind.indexName }</td>
					          <c:forEach items="${ind.monthAssessments }" var="mon" begin="0" end="0">
					            <td>${mon.monthly } <input type="hidden" id="maKey" value="${mon.maKey}"></td>
					            <td>${mon.itemScale } </td>
					            <td>${mon.indexScale } </td>
					            <td>${mon.indexMainkey } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					            <td><fmt:formatDate value="${mon.createTime }" type="date" pattern="yyyy-MM-dd"/> </td>
					            <td>
						          <shiro:hasPermission name="base:monthAssesment:EDIT"> 
						            <a class="edit" href="base/monthAssesment/edit.do?groupKey=${o.groupKey }&itemKey=${it.itemKey}&indexKey=${ind.indexKey}&maKey=${mon.maKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
						          </shiro:hasPermission>  
						          <shiro:hasPermission name="base:monthAssesment:DEL"> 
						            <a class="delete" href="base/monthAssesment/delete.do?maKey=${mon.maKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
						          </shiro:hasPermission>
						         </td>
					         </c:forEach>
					       <c:if test="${ind.monthNum ==0}">
					            <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    
					       </c:if>
					       <c:forEach items="${ind.monthAssessments }" var="mon1" begin="1">
					           <tr id="${o.groupKey }_${o.uigroupCategory.gcKey}">
					            <td>${mon1.monthly } <input type="hidden" id="maKey" value="${mon1.maKey}"></td>
					            <td>${mon1.itemScale } </td>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.indexMainkey } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					            <td><fmt:formatDate value="${mon1.createTime }" type="date" pattern="yyyy-MM-dd"/> </td>
					            <td>
						          <shiro:hasPermission name="base:monthAssesment:EDIT"> 
						            <a class="edit" href="base/monthAssesment/edit.do?groupKey=${o.groupKey }&itemKey=${it.itemKey}&indexKey=${ind.indexKey}&maKey=${mon1.maKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
						          </shiro:hasPermission>  
						          <shiro:hasPermission name="base:monthAssesment:DEL"> 
						            <a class="delete" href="base/monthAssesment/delete.do?maKey=${mon1.maKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
						          </shiro:hasPermission>
						         </td>
					          </tr>
					       </c:forEach>
					    </c:forEach>
					    <c:if test="${it.monthNum == 0}">
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
						<td> </td>
						<td> </td>
					    </c:if>	
					    
					     <c:forEach items="${it.uiindexs }" var="ind2" begin="1">
					     <tr id="${o.groupKey }_${o.uigroupCategory.gcKey}">
					      <td  rowspan="${ind2.monthNum}">${ind2.indexCode }</td>
					      <td  rowspan="${ind2.monthNum}">${ind2.indexName }</td>
					      <c:forEach items="${ind2.monthAssessments }" var="mon" begin="0" end="0">
					            
					            <td>${mon.monthly } <input type="hidden" id="maKey" value="${mon.maKey}"></td>
					            <td>${mon.itemScale } </td>
					            <td>${mon.indexScale } </td>
					            <td>${mon.indexMainkey } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					            <td><fmt:formatDate value="${mon.createTime }" type="date" pattern="yyyy-MM-dd"/> </td>
					            <td>
						          <shiro:hasPermission name="base:monthAssesment:EDIT"> 
						            <a class="edit" href="base/monthAssesment/edit.do?groupKey=${o.groupKey }&itemKey=${it.itemKey}&indexKey=${ind2.indexKey}&maKey=${mon.maKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
						          </shiro:hasPermission>  
						          <shiro:hasPermission name="base:monthAssesment:DEL"> 
						            <a class="delete" href="base/monthAssesment/delete.do?maKey=${mon.maKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
						          </shiro:hasPermission>
						         </td>
					      </c:forEach>
					      <c:if test="${ind2.monthNum ==0}">
					            <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    
					       </c:if>
					       <c:forEach items="${ind2.monthAssessments }" var="mon1" begin="1">
					           <tr id="${o.groupKey }_${o.uigroupCategory.gcKey}">
					            <td>${mon1.monthly } <input type="hidden" id="maKey" value="${mon1.maKey}"></td>
					            <td>${mon1.itemScale } </td>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.indexMainkey } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					            <td><fmt:formatDate value="${mon1.createTime }" type="date" pattern="yyyy-MM-dd"/> </td>
					            <td>
						          <shiro:hasPermission name="base:monthAssesment:EDIT"> 
						            <a class="edit" href="base/monthAssesment/edit.do?groupKey=${o.groupKey }&itemKey=${it.itemKey}&indexKey=${ind2.indexKey}&maKey=${mon1.maKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
						          </shiro:hasPermission>  
						          <shiro:hasPermission name="base:monthAssesment:DEL"> 
						            <a class="delete" href="base/monthAssesment/delete.do?maKey=${mon1.maKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
						          </shiro:hasPermission>
						         </td>
					           </tr>
					       </c:forEach>
					     </tr>
					    </c:forEach>
					    <c:if test="${o.uigroupCategory.monthNum == 0}">
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    </c:if>					    
					   </tr>
					</c:forEach>	
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
