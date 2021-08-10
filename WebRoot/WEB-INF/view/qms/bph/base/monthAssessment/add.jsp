<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    	 $('#baseCategory_${id_end}',$.pdialog.getCurrent()).change(function(){
    		 getIndex();
    	 })
    });

</script>
<script type="text/javascript">
    function getIndex() {
    	 var baseFactory = $('#baseFactory_${id_end}',$.pdialog.getCurrent()).val();
    	 var baseArea = $('#baseArea_${id_end}',$.pdialog.getCurrent()).val();
		 var baseCategory =  $('#baseCategory_${id_end}',$.pdialog.getCurrent()).val(); 
	    var url = "<c:url value='base/monthAssesment/getindex.do' />";
	    $("#indexTable",$.pdialog.getCurrent()).load(url,{baseFactory: baseFactory,baseArea:baseArea,baseCategory:baseCategory});   
	} 
    
</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/monthAssesment/save.do?navTabId=newMonthAssesmentNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		 <jsp:include page="../../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="doubleLine" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="1" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="1" name="fagroup"/>
					<jsp:param value="true" name="isRequired"/>
					<jsp:param value="1" name="thClass"/>
		</jsp:include>	
		<tr>
		    <td style="background: #F7F9FC;">月份：</td>
		    <td colspan="3"><input type="text" class="required" name="monthly" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true"/></td>
		</tr>
		
		</table>
		<div class="pageFormContent"  layoutH="210" id="content">
            <table  class="tableFormContent nowrap">
                <thead>
			    <tr>
				<th style='text-align: center;width:8%'>考核项目</th>
				<th style='text-align: center;width:8%'>项目代码</th>
				<th style='text-align: center;width:8%'>项目比例</th>
				<th style='text-align: center;width:20%'>考核指标    </th>
				<th style='text-align: center;width:8%'>指标代码</th>
				<th style='text-align: center;width:8%'>指标比例</th>
				<th style='text-align: center;width:8%'>是否关键指标</th>
				<th style='text-align: center;width:8%'>基准值</th>
				<th style='text-align: center;width:8%'>目标值</th>
			   </tr>
		     </thead>
		       <tbody id="indexTable"></tbody>
            </table>        
            
        </div>		
		
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
