<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

             
               
          
		 <c:forEach items="${list}" var="o" >
			<tr id="newArray" target="code_carrier" rel="${o.gcKey}" >
				     <c:forEach items="${o.item}" var="vo" begin="0" end="0" >
				       <td rowspan="${vo.indexNum}">${vo.itemName}</td>
					   <td rowspan="${vo.indexNum}">${vo.itemCode}</td>
		 			    <c:forEach items="${vo.uiindexs }" var="in" begin="0" end="0" varStatus="s1">
		 			      <td ><input type="hidden" name="monList[${in.mainKey }].indexKey" value="${in.indexKey }">
		 			      <input size="5" type="text" name="monList[${in.mainKey }].itemScale">  </td>
					      <td  >${in.indexName }</td>
					      <td  >${in.indexCode }</td>
					      <td  ><input size="5" type="text" name="monList[${in.mainKey }].indexScale"></td>
					      <td  ><input size="5" type="text" name="monList[${in.mainKey }].indexMainkey"></td>
					      <td  ><input size="5" type="text" name="monList[${in.mainKey }].baseValue"></td>
					      <td  ><input size="5" type="text" name="monList[${in.mainKey }].targetValue"></td>
					    </c:forEach>
					    <c:if test="${vo.indexNum == 0}">
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    </c:if>
			 		   
					    <c:forEach items="${vo.uiindexs }" var="in2" begin="1" varStatus="s2">
					    <tr id="newArray"> 
					      <td ><input type="hidden" name="monList[${in2.mainKey }].indexKey" value="${in2.indexKey }">
					      <input size="5" type="text" name="monList[${in2.mainKey }].itemScale">  </td>
					      <td  >${in2.indexName }</td>
					      <td  >${in2.indexCode }</td>
					      <td  ><input size="5" type="text" name="monList[${in2.mainKey }].indexScale"></td>
					      <td  ><input size="5" type="text" name="monList[${in2.mainKey }].indexMainkey"></td>
					      <td  ><input size="5" type="text" name="monList[${in2.mainKey }].baseValue"></td>
					      <td  ><input size="5" type="text" name="monList[${in2.mainKey }].targetValue"></td>
					    </tr> 
					    </c:forEach>
					    
					</c:forEach>	
					<c:if test="${o.indexNum == 0}">
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
			
				     <c:forEach items="${o.item}" var="it" begin="1" >
				        <tr id="newArray"> 
				           <td rowspan="${it.indexNum}">${it.itemName}</td>
					       <td rowspan="${it.indexNum}">${it.itemCode}</td>
						<c:forEach items="${it.uiindexs }" var="ind" begin="0" end="0" varStatus="s3">
						  <td > <input type="hidden" name="monList[${ind.mainKey }].indexKey" value="${ind.indexKey }">
						  <input size="5" type="text" name="monList[${ind.mainKey }].itemScale"></td>
					      <td  >${ind.indexName }</td>
					      <td  >${ind.indexCode }</td>
					      <td  ><input size="5" type="text" name="monList[${ind.mainKey }].indexScale"></td>
					      <td  ><input size="5" type="text" name="monList[${ind.mainKey }].indexMainkey"></td>
					      <td  ><input size="5" type="text" name="monList[${ind.mainKey }].baseValue"></td>
					      <td  ><input size="5" type="text" name="monList[${ind.mainKey }].targetValue"></td>
					    </c:forEach>
					    <c:if test="${it.indexNum == 0}">
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    </c:if>	
					     <c:forEach items="${it.uiindexs }" var="ind2" begin="1" varStatus="s4">
					     <tr id="newArray">
					      <td ><input type="hidden" name="monList[${ind2.mainKey }].indexKey" value="${ind2.indexKey }">
					      <input size="5" type="text" name="monList[${ind2.mainKey }].itemScale"></td>
					      <td  >${ind2.indexName }</td>
					      <td  >${ind2.indexCode }</td>
					      <td  ><input size="5" type="text" name="monList[${ind2.mainKey }].indexScale"></td>
					      <td  ><input size="5" type="text" name="monList[${ind2.mainKey }].indexMainkey"></td>
					      <td  ><input size="5" type="text" name="monList[${ind2.mainKey }].baseValue"></td>
					      <td  ><input size="5" type="text" name="monList[${ind2.mainKey }].targetValue"></td>
					      </tr>
					    </c:forEach>
					    <c:if test="${o.indexNum == 0}">
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
	
		 	