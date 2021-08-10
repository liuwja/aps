function showVoice(id,data){
	var option = {
		    color: ['#3398DB'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {// 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        height:600,
		        width:1100,
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            name:'区域',
		            data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		        }
		    ],
		    series : [
		        {
		            name:'未知',
		            type:'bar',
		            barWidth: '60%',
		            data:[10, 52, 200, 334, 400, 330, 220]
		        }
		    ]
		};
}