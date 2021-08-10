<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<script>
//用户值回传方法
function comfirmSelect()
{
	var selVal = $('#reworkListTable input:radio[name="reworkline"]:checked').val();
	if(selVal == undefined)
	{
		alertMsg.warn('请选择用户！');
		return false;
	}
	var jsonObj = eval("("+ selVal +")");
	$.bringBack(jsonObj);
    //if ($.isFunction(perSelectCallBack)) perSelectCallBack(jsonObj);

}
</script>
<div class="pageHeader">
    <form onsubmit="return dialogSearch(this, 'dialog');" rel="pagerForm" action="system/claimsSheet/reworkList.do" method="post">
    <div class="searchBar">
        <table class="searchContent">
        <tr>	
           	<td>工厂：</td>
			<td>
				<select name="factory" id="factory" onchange="">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factory }" <c:if test="${reworkSheet.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
					</c:forEach>
				</select>
			</td>
			<td>返工/停线单号</td>
		    <td >
		         <input type="text" id="rework_number"  name="rework_number" size="20"  value="${reworkSheet.rework_number}"/>
		    </td>
			<td>开单日期：</td>
			<td>
				 <input type="text" id="creation_time"  name="creation_time" placeholder="请输入日期" onclick="laydate()" value="${reworkSheet.creation_time}" readonly="true"/>
			</td>
			<td>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
			</td>
        </tr>
        </table>
    </div>
    </form>
</div>
<div class="pageContent">
        <table class="list" width="100%" layoutH="120" id="reworkListTable">
        <thead>
            <tr>
                <th width="50">选择</th>
		    	<th width="12%">序号</th>
		    	<th width="12%">工厂</th>
		    	<th width="12%">返工/停线单号</th>
				<th width="12%">工时</th>
				<th width="12%">耗材费用</th>
				<th width="12%">金额</th>
				<th width="12%">状态</th>
				<th width="12%">开单日期</th>
            </tr>
        </thead>
        <tbody> 
            <c:forEach items="${list}" var="o" varStatus="i">
                <tr target="dlg" rel="${o.id}" >
                    <td   style="text-align: center;">
                        <input type="radio" group="dlguser" name="reworkline" value="{reworkId:'${o.id }', reworkNumber:'${o.rework_number}'}">
                    </td>
                    <td width="3%">
        				${i.count}
        			</td>
					<td>${o.factory}</td> 
					<td>${o.rework_number}</td> 
					<td>${o.workhour}</td>  
					<td>${o.supplies_expense } </td> 
					<td>${o.money}</td>
					<td>${o.status}</td>
					<td>${o.creation_time}</td>
                </tr>       
            </c:forEach>        
        </tbody>
    </table>
    <c:import url="../../../_frag/pager/panelBar.jsp">
       <%-- 对话框分页时需要传此参数 --%>
       <c:param name="targetName" value="dialog"/>    
    </c:import>
   	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="comfirmSelect()">确定</button></div></div></li>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>
		</ul>
	</div>
</div>