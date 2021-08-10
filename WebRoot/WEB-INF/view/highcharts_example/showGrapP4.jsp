<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
</style>
<script type="text/javascript">
	var option = "";
	var arryclass = new Array();
	var arryx = new Array();
	var arryy = new Array();
	var url = "<c:url value='care/outlay/getInfo.do'/>";
	$('input[type="checkbox"][name="classes"]').each(function() {
		arryx.push($(this).val());

		/*
		$.post(url,{arryx:arryx},function(data,status){
			if(data.result==0){
				$("#partName").val(data.partDescription);
				$("#partNumber").val(data.partNumber);
				
			}else{
				
				alertMsg.error(result.message);
				
			}
		});*/

	});
	$('input[name="remark"]').each(function() {
		arryy.push($(this).val());
	});
	
	﻿$(function () {
	
	    $('#highchartsP4').highcharts({
	        chart: {
	            type: 'column',
	            margin: [ 50, 50, 100, 80]
	        },
	        title: {
	            text: '班组test'
	        },
	        xAxis: {
	            labels: {
	                rotation: -45,
	                align: 'right',
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                },
	
	            }
	
	        },
	        
	        yAxis: {
	            min: 0,
	            title: {
	                text: '绩效'
	            }
	        },
	        legend: {
	            enabled: false
	        },
	        tooltip: {
	            pointFormat: '绩效: <b>{point.y:.1f} </b>',
	        },
	        series: [{
	            name: 'Population',
	            data: [],
	            dataLabels: {
	                enabled: true,
	                rotation: -90,
	                color: '#FFFFFF',
	                align: 'right',
	                x: 4,
	                y: 10,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif',
	                    textShadow: '0 0 3px black'
	                }
	            }
	        }]

	    });
			var chart = $('#highchartsP4').highcharts();
	       	chart.xAxis[0].setCategories(arryx);	       	
	       	//chart.series[0].setData(arryy);
	    $('#button').click(function () {
        var chart = $('#highchartsP4').highcharts();
        chart.series[0].setData(arryya);
		  });
	});
	function addarry(obj){
			if(obj.checked==true){
				if(arryclass.indexOf(obj.value)>=0){
				
				}else{
					arryclass.push(obj.value);
				}			
			}else{
				arryclass.remove(obj.value);
			}
		    var chart = $('#highchartsP4').highcharts();
	       	chart.xAxis[0].setCategories(arryclass);
	       	
	}	
	
	Array.prototype.indexOf = function(val) {              
	    for (var i = 0; i < this.length; i++) {  
	        if (this[i] == val) return i;  
	    }  
	    return -1;  
	};  
	
	Array.prototype.remove = function(val) {  
	    var index = this.indexOf(val);  
	    if (index > -1) {  
	        this.splice(index, 1);  
	    }  
	};  
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="care/outlay/showGrapP1.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
                <th>班组：</th>
                <td>
                	<c:forEach items="${list}" var="o" varStatus="s">
						<input type="checkbox" id="classes" name="classes" value="${o.name }" onclick="addarry(this)"/>${o.name }	
						<input  type="hidden" id="remark" name="remark" value="${o.remark}"/>
					</c:forEach>

    			</td> 			
				<th>期间：</th>
                <td>
					<input type="text" id="type" name="type" value=""/>
    			</td> 			
				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>				
			</tr>
		</table>
	</div>
	</form>
</div>

<button id="button" class="autocompare">Set new data</button>
<div class="pageContent">
		<div id="highchartsP4" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</div>
