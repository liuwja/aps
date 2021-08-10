<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<script>
//用户值回传方法
function comfirmSelect()
{
	var selVal = $('#userListTable input:radio[name="userLine"]:checked').val();
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
    <form onsubmit="return dialogSearch(this, 'dialog');" rel="pagerForm" action="system/claimsSheet/userList.do" method="post">
    <div class="searchBar">
        <table class="searchContent">
        <tr>	
           		<td>
                                            用户编号： 
                </td>
                <td>
                    <input type="text" name="userName" value="${vo.userName}"/>
                </td> 
                <td>
                                            用户名称： 
                </td>
                <td>
                    <input type="text" name="description" value="${vo.description}"/>
                </td>               
                <td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
               
                </td>   
            </tr>
        </table>
    </div>
    </form>
</div>
<div class="pageContent">
        <table class="list" width="100%" layoutH="120" id="userListTable">
        <thead>
            <tr>
                <th width="50">选择</th>
                <th width="120">用户工号</th>
                <th width="120">用户名称</th>
                <th width="120">所属工厂</th>
            </tr>
        </thead>
        <tbody> 
            <c:forEach items="${list}" var="o">
                <tr target="dlg" rel="${o.userKey}" >
                    <td   style="text-align: center;">
                        <input type="radio" group="dlguser" name="userLine" value="{userId:'${o.userKey }', userName:'${o.userName}', userDescription:'${o.description}'}">
                    </td>
                    <td>${o.userName}</td>
                    <td>${o.description}</td>
                    <td>${o.uda1}</td>
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