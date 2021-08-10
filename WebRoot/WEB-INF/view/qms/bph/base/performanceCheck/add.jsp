<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/performanceCheck/save.do?navTabId=performanceCheckNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">	
		<tr>
			<th>工厂</th>
			<td>
				<select name="factoryNumber" id="factoryNumber" onchange="loadDepartMent('pdialog')">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factoryNumber }">${o.factory}</option>
					</c:forEach>
				</select>
			</td>
			<th>部门</th>
			<td>
				<select id="department" name="department">
				</select>
			</td>
		 	<th>年度</th>
			<td colspan="3">
				<input type="text" class="required" name="chekyear" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true"/>
			</td>
		</tr>
		<tr>   
			<th>绩效目标大类</th>
		    <td >
		         <input type="text" id="targetclass"  name="targetclass" size="20" class="required"/>
		    </td>
		   	<th>衡量指标内容</th>
		    <td>
		       <input type="text" name="indexcontent" size="20"  class="required">
		    </td>	
		    <th>指标类型</th>
		    <td >
		         <input type="text" id="performancecontent"  name="performancecontent" size="20" class="required" />
		    </td>
		</tr>
		<tr>   		    
		    <th>权重</th>
		    <td >
		         <input type="text" id="weight"  name="weight" size="20" class="required number" />
		    </td>
		    <!-- 新增一个单位字段 -->
		    <th>单位</th>
		    <td >
		         <input type="text" id="company"  name="company" size="20" class="required" />
		    </td>
		    <th>计算公式</th>
		    <td >
		         <input type="text" id="formula"  name="formula" size="20" class="required" />
		    </td>
		</tr>
		</table>
		
		<!--新增考核方法  -->
		<div class="panelBar">
	        <ul class="toolBar">	           	                       
	    <li><a class="add" href="###" ><span>考核方法</span></a></li>	            
	        </ul>
   		</div>
   			           	      	         
	  <table class="tableFormContent nowrap">						
		<tr>   
		 <th>小于基准</th>
		    <td >
		         <input type="text" id="referencevalue"  name="referencevalue" size="20" class="required" />
		    </td>
		    <th>基准与目标之间</th>
		    <td >
		         <input type="text" id="median"  name="median" size="20" class="required" />
		    </td>
		    <th>大于目标</th>
		    <td >
		         <input type="text" id="targetvalue"  name="targetvalue" size="20" class="required" />
		    </td>		    
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
