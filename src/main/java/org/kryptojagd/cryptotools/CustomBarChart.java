package org.kryptojagd.cryptotools;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;

public class CustomBarChart<X, Y> extends BarChart<X, Y> {

	public CustomBarChart(Axis<X> xAxis, Axis<Y> yAxis) {
		super(xAxis, yAxis);
		// TODO Auto-generated constructor stub
		// this.getPlotChildren();
		
	}
	
	@Override
	public ObservableList<Node> getChartChildren() {
		return super.getChartChildren();
	}
	
	@Override
	public ObservableList<Node> getChildren() {
		return super.getChildren();
	}

}
