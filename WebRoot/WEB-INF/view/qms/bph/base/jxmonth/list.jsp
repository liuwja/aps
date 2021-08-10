<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
#monthAssesmontListTbody a{
color:blue;
}
</style>
<script type="text/javascript">
jQuery(document).ready(function() {
	loadDepartMent("navTab", "${vo.department}");
});
function exportExcel(url){    

	var myForm=document.createElement("form");
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
<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="per/monthly/list.do" method="post">
<div class="searchBar">
	<table class="searchContent">
		<tr>
				<td>工厂：</td>
			<td>
				<select name="factoryNumber" id="factoryNumber" onchange="loadDepartMent()">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factoryNumber }" <c:if test="${vo.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
					</c:forEach>
				</select>
			</td>
			<td>部门：</td>
			<td>
				<select id="department" name="department">
				</select>
			</td>
			<td>
					年度：
				</td>
				<td>
					 <input type="text"  id="chekyear" name="year" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})" value="${vo.year}" readonly="true"/>
				</td>					
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>

<div class="pageContent">
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th></th>
			    <th>工厂</th>
			    <th>部门</th>
				<th>绩效大类</th>
				<th>衡量指标内容</th>
				<th>绩效类型</th>
				
				<th>本年基准值</th>
				<th>本年目标值</th>
				
				<th>月度基准值</th>
				<th>月份目标值</th>
				<th>月份</th>
				<th>操作</th>
				
				<th>当月实际值</th>
				<!-- <th>月份累计目标值</th> -->
				<th>当月累计实际值</th>				
				<th>操作</th>
			</tr>	
		</thead>
		<tbody id="monthAssesmontListTbody">
		 <c:forEach items="${list}" var="o" varStatus="s">
		 <c:set var = "len" value="${o.monthNum == 0 ? '1' : o.monthNum}"></c:set>   <!-- BUG #5879-->
			<tr  target="code_carrier" rel="${o.id}" >
				<td rowspan="${len}">
					<input type="radio" group="code" name="id" >
				</td>
				<td  rowspan="${len}">${o.factoryName}</td>
				<td  rowspan="${len}">${o.departmentName}</td>
				<td  rowspan="${len}">${o.targetclass}</td>
				<td  rowspan="${len}">${o.indexcontent}</td>
				<td  rowspan="${len}">${o.performancecontent}</td>
				     <c:forEach items="${o.item}" var="vo" begin="0" end="0">
				     	<c:set var = "len" value = "${vo.monthNum == 0 ? '1' :vo.monthNum}" ></c:set>
					   <td rowspan="${len}">${vo.referencevalue}</td>
				       <td rowspan="${len}">${vo.yeartargetvalue}</td>
				       
		 			    <c:forEach items="${vo.uiindexs }" var="in" begin="0" end="0">
		 			      <c:set var = "len" value = "${in.monthNum == 0 ? '1' : in.monthNum}"></c:set>
					      <td  rowspan="${len}">${in.basevalue }</td>
					      <td  rowspan="${len}">${in.targetvalue }</td>
					      
					      <td  rowspan="${len}">${in.month}</td>
					      <td  rowspan="${len}">
					       <shiro:hasPermission name="per:monthly:ADD"> 
					            <a  href="per/monthly/add.do?id=${vo.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="新增"><span>新增</span></a>
					       </shiro:hasPermission>
					   	  </td>
					       <c:forEach items="${in.uigroupCategory }" var="mon" begin="0" end="0">
					            <td>${mon.monthreality } </td>
					            <%-- <td>${mon.targetvaluemonth }</td> --%>
					            <td>${mon.accumumonth } </td>					           
					          <td>
					          <shiro:hasPermission name="per:monthly:EDIT"> 
					            <a class="edit" href="per/monthly/edit.do?monthKey=${mon.monthKey}&id=${o.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          	</shiro:hasPermission>  
					          	<shiro:hasPermission name="per:monthly:DEL"> 
					            <a class="delete" href="per/monthly/delete.do?monthKey=${mon.monthKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          	</shiro:hasPermission>
					         </td>
					       </c:forEach>
					       <c:if test="${in.monthNum ==0}">					           
							    <td> </td>
							    <td> </td>
							   	<td> </td>

					       </c:if>
					       <c:forEach items="${in.uigroupCategory }" var="mon1" begin="1">
					          <tr > 
					            <td>${mon1.monthreality } </td>
					            <td>${mon1.accumumonth } </td>
					            <td>
						         <shiro:hasPermission name="per:monthly:EDIT"> 
					            <a class="edit" href="per/monthly/edit.do?monthKey=${mon1.monthKey}&id=${o.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          	</shiro:hasPermission>  
					          	<shiro:hasPermission name="per:monthly:DEL"> 
					            <a class="delete" href="per/monthly/delete.do?monthKey=${mon1.monthKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
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
						<td> </td>
						<td> </td>
						
					    </c:if>
			 		   
					    <c:forEach items="${vo.uiindexs }" var="in2" begin="1">
					    <tr > 
					      <c:set var = "len" value = "${in2.monthNum == 0 ? '1' : in2.monthNum }"></c:set>
					      <td  rowspan="${len }">${in2.basevalue }</td>
					      <td  rowspan="${len}">${in2.targetvalue }</td>
					      <td  rowspan="${len}">${in2.month}</td>
					      <td  rowspan="${len}">
					       <shiro:hasPermission name="per:monthly:ADD"> 
					            <a  href="per/monthly/add.do?id=${vo.id}&itemKey=${vo.itemKey}&indexKey=${in2.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="新增"><span>新增</span></a>
					       </shiro:hasPermission>
					      </td>
					      <c:forEach items="${in2.uigroupCategory }" var="mon" begin="0" end="0">
					            <td>${mon.monthreality } </td>					     
					            <td>${mon.accumumonth } </td>					     
					            <td>
						          <shiro:hasPermission name="per:monthly:EDIT"> 
					            <a class="edit" href="per/monthly/edit.do?monthKey=${mon.monthKey}&id=${o.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          	</shiro:hasPermission>  
					          	<shiro:hasPermission name="per:monthly:DEL"> 
					            <a class="delete" href="per/monthly/delete.do?monthKey=${mon.monthKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          	</shiro:hasPermission>
						         </td>
					      </c:forEach>
					      <c:if test="${in2.monthNum ==0}">
					            <td> </td>
							    <td> </td>
							    <td> </td>
							   
					       </c:if>
					       <c:forEach items="${in2.uigroupCategory }" var="mon1" begin="1">
					           <tr >
					            <td>${mon1.monthreality } </td>
					            <td>${mon1.accumumonth } </td>
					            <td>
						          <shiro:hasPermission name="per:monthly:EDIT"> 
					            <a class="edit" href="per/monthly/edit.do?monthKey=${mon1.monthKey}&id=${o.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          	</shiro:hasPermission>  
					          	<shiro:hasPermission name="per:monthly:DEL"> 
					            <a class="delete" href="per/monthly/delete.do?monthKey=${mon1.monthKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          	</shiro:hasPermission>
						         </td>
					           </tr>
					       </c:forEach>
					    </tr> 
					    </c:forEach>
					    
					</c:forEach>	
					<c:if test="${o.monthNum == 0}">
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
			
				     <c:forEach items="${o.item}" var="it" begin="1" >
				        <tr > 
				           <c:set var = "len" value = "${it.monthNum == 0 ? '1' : it.monthNum}"></c:set>
					       <td rowspan="${len}">${it.referencevalue}</td>
				           <td rowspan="${len}">${it.yeartargetvalue}</td>
					       
						<c:forEach items="${it.uiindexs }" var="ind" begin="0" end="0">
						  <c:set var = "len" value = "${ind.monthNum == 0 ? '1': ind.monthNum}"></c:set>
					      <td  rowspan="${len}">${ind.basevalue }</td>
					      <td  rowspan="${len}">${ind.targetvalue }</td>
					       <td rowspan="${len}">${ind.month}</td>
					      <td rowspan="${len}">
					       <shiro:hasPermission name="per:monthly:ADD"> 
					            <a  href="per/monthly/add.do?id=${vo.id}&itemKey=${vo.itemKey}&indexKey=${ind.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="新增"><span>新增</span></a>
					       </shiro:hasPermission>
					   </td>
					          <c:forEach items="${ind.uigroupCategory }" var="mon" begin="0" end="0">
					            <td>${mon.monthreality } </td>
					            <td>${mon.accumumonth } </td>
					            <td>
						          <shiro:hasPermission name="per:monthly:EDIT"> 
					            <a class="edit" href="per/monthly/edit.do?monthKey=${mon.monthKey}&id=${o.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          	</shiro:hasPermission>  
					          	<shiro:hasPermission name="per:monthly:DEL"> 
					            <a class="delete" href="per/monthly/delete.do?monthKey=${mon.monthKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
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
							    <td> </td>
					       </c:if>
					       <c:forEach items="${ind.uigroupCategory }" var="mon1" begin="1">
					           <tr >
					            <td>${mon1.monthreality } </td>
					            <td>${mon1.accumumonth } </td>
					            <td>
					          	<shiro:hasPermission name="per:monthly:EDIT"> 
					            <a class="edit" href="per/monthly/edit.do?monthKey=${mon1.monthKey}&id=${o.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          	</shiro:hasPermission>  
					          	<shiro:hasPermission name="per:monthly:DEL"> 
					            <a class="delete" href="per/monthly/delete.do?monthKey=${mon1.monthKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
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
						<td> </td>
						<td> </td>
						<td> </td>
					    </c:if>	
					    
					     <c:forEach items="${it.uiindexs }" var="ind2" begin="1">
					     <tr >
					      <c:set var = "len" value = "${ind2.monthNum == 0 ? '1' : ind2.monthNum }"></c:set>
					      <td  rowspan="${len }">${ind2.basevalue }</td>
					      <td  rowspan="${len}">${ind2.targetvalue }</td>
					       <td rowspan="${len}">${ind2.month}</td>
					       <td rowspan="${len}">
					       <shiro:hasPermission name="per:monthly:ADD"> 
					            <a  href="per/monthly/add.do?id=${vo.id}&itemKey=${vo.itemKey}&indexKey=${ind2.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="新增"><span>新增</span></a>
					       </shiro:hasPermission>
					   </td>
					      <c:forEach items="${ind2.uigroupCategory }" var="mon" begin="0" end="0">					            
					            <td>${mon.monthreality } </td>
					            <td>${mon.accumumonth } </td>
					            <td>
						          <shiro:hasPermission name="per:monthly:EDIT"> 
					            <a class="edit" href="per/monthly/edit.do?monthKey=${mon.monthKey}&id=${o.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          	</shiro:hasPermission>  
					          	<shiro:hasPermission name="per:monthly:DEL"> 
					            <a class="delete" href="per/monthly/delete.do?monthKey=${mon.monthKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          	</shiro:hasPermission>
						         </td>
					      </c:forEach>
					      <c:if test="${ind2.monthNum ==0}">
					            <td> </td>
							    <td> </td>
							    <td> </td>
					       </c:if>
					       <c:forEach items="${ind2.uigroupCategory }" var="mon1" begin="1">
					           <tr >
					            <td>${mon1.monthreality } </td>
					            
					            <td>${mon1.accumumonth } </td>
					            <td>
						          <shiro:hasPermission name="per:monthly:EDIT"> 
					            <a class="edit" href="per/monthly/edit.do?monthKey=${mon1.monthKey}&id=${o.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          	</shiro:hasPermission>  
					          	<shiro:hasPermission name="per:monthly:DEL"> 
					            <a class="delete" href="per/monthly/delete.do?monthKey=${mon1.monthKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          	</shiro:hasPermission>
						         </td>
					           </tr>
					       </c:forEach>
					     </tr>
					    </c:forEach>
					    <c:if test="${o.monthNum == 0}">
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
