<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

             
              
                 <option value="">请选择</option>
                 <c:forEach items="${shiList}" var="o">
		           <option value="${o.shiftGroup}">${o.shiftGroup}</option>
		         </c:forEach>  
		     
		 	   