<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

             
              
                 <option value="">请选择</option>
                 <c:forEach items="${category}" var="o">
		           <option value="${o.shiftGroupCategory}">${o.shiftGroupCategory}</option>
		         </c:forEach>  
		     
		 	   