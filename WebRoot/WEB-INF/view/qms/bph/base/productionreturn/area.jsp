<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

             
          
                 <option value="">请选择</option>
                 <c:forEach items="${list}" var="o">
		           <option value="${o.area}">${o.area}</option>
		         </c:forEach>  

		 	