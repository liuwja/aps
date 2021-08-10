<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
                <c:forEach items="${list}" var="o">
                    <tr target="id" id="${o.number}" >
                        <td style="text-align: center;">
                            <input onclick="delSelectedUser(this)" type="checkbox" checked="checked" name="id" value="{id:'${o.number}',name: '${o.name}',code:'${o.paterName}'}"></td>                        
	                        <td>${o.paterName}</td>
	                        <td>${o.name}</td>

                        <td style="text-align: center;">
                        <a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value="resources/img/delete.png"/>' /></a>
                        </td>
                    </tr>       
                </c:forEach>                  
