package com.peg.model.part;

public class MarkPoint {
		String name;
		Double value;
		Integer xAxis;
		Double yAxis;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getValue() {
			return value;
		}
		public void setValue(Double value) {
			this.value = value;
		}
		
		public Integer getXAxis() {
			return xAxis;
		}
		public void setXAxis(Integer xAxis) {
			this.xAxis = xAxis;
		}
		public Double getYAxis() {
			return yAxis;
		}
		public void setYAxis(Double yAxis) {
			this.yAxis = yAxis;
		}
		public MarkPoint(){
			
		}
		public MarkPoint(String name, Double value, Integer xAxis, Double yAxis) {
			super();
			this.name = name;
			this.value = value;
			this.xAxis = xAxis;
			this.yAxis = yAxis;
		}
		
		
		
}
