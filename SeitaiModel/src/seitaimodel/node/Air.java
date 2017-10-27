package seitaimodel.node;

import java.math.BigDecimal;

import javafx.scene.layout.Pane;

public class Air extends Node {

	public Air(Pane pane, double x, double y) {
		super(pane, "Air", x, y, 260, 130);
		/*
		 * 大気圧: 1013hPa = 101300N/m^2
		 * g = 10とする
		 * 大気の重さ(kg) = 10130kg;
		 * 面積を1m^2とする
		 * 10130 * 0.78 = 7900:N2
		 * 10130 * 0.21 = 2100:O2
		 * 10130 * 0.00032 = 3.2:CO2
		 */
		nitrogen= new BigDecimal("7900");
		oxygen	= new BigDecimal("2100");
		carbon	= new BigDecimal("3.2");

	}

}
