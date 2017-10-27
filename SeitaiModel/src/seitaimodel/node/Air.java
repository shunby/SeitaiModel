package seitaimodel.node;

import java.math.BigDecimal;

import javafx.scene.layout.Pane;

public class Air extends Node {

	public Air(Pane pane, double x, double y) {
		super(pane, "Air", x, y, 260, 130);
		/*
		 * 大気圧: 1013hPa = 10130000N/m^2
		 * 面積を1m^2とする
		 * 10130000 * 0.78 = 7900000:N2
		 * 10130000 * 0.21 = 2100000:O2
		 * 10130000 * 0.00032 = 3200:CO2
		 */
		nitrogen= new BigDecimal("7900000");
		oxygen	= new BigDecimal("2100000");
		carbon	= new BigDecimal("3200");

	}

}
