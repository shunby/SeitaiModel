package seitaimodel.node;

import java.math.BigDecimal;
import java.util.Random;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

public class Pipe implements Updatable {
	/**パイプの始点*/
	private Node from;
	/**パイプの終点*/
	private Node to;

	//導通率
	/**エネルギー導通率*/
	public BigDecimal energy;
	/**水導通率*/
	public BigDecimal water;
	/**酸素導通率*/
	public BigDecimal oxygen;
	/**窒素導通率*/
	public BigDecimal nitrogen;
	/**炭素導通率*/
	public BigDecimal carbon;
	/**矢印*/
	public Shape shape;

	public Pipe(Pane pane, Node from, Node to) {
		this.from = from;
		this.to = to;
		initVar();

		shape = new Polygon(0d, 1d,   0.5d, 0d,    1d, 1d,    0.75d, 1d,    0.75d, 2d,    0.25d, 2d,    0.25d, 1d);

		//ここから矢印の見た目の定義
		Bounds fbounds = from.getShape().getBoundsInParent();
		Bounds tbounds = to.getShape().getBoundsInParent();
		double fx = from.getX() + fbounds.getWidth() / 2;
		double fy = from.getY() + fbounds.getHeight() / 2;
		double tx = to.getX() + tbounds.getWidth()/2;
		double ty = to.getY() + tbounds.getHeight()/2;
		double dist = Math.sqrt(Math.pow(fx - tx, 2) + Math.pow(ty - fy, 2));

		shape.setScaleY(dist/2);shape.setScaleX(30);shape.setScaleZ(dist/2);
		shape.setTranslateX((fx + tx)/2);shape.setTranslateY((fy + ty)/2);
		shape.setRotate(90 + Math.toDegrees(Math.atan2(ty - fy, tx - fx)) );

		Random r = new Random();
		shape.setFill(new Color(r.nextDouble()/2 + 0.5, r.nextDouble()/2 + 0.5, r.nextDouble()/2 + 0.5, 1));

		pane.getChildren().add(0,shape);
	}
	private void initVar(){
		carbon	= new BigDecimal(0);
		oxygen 	= new BigDecimal(0);
		nitrogen= new BigDecimal(0);
		energy= new BigDecimal(0);
		water = new BigDecimal(0);
	}

	public void update() {

			Bounds fbounds = from.getShape().getBoundsInParent();
			Bounds tbounds = to.getShape().getBoundsInParent();
			double fx = from.getX() + fbounds.getWidth() / 2;
			double fy = from.getY() + fbounds.getHeight() / 2;
			double tx = to.getX() + tbounds.getWidth()/2;
			double ty = to.getY() + tbounds.getHeight()/2;
			double dist = Math.sqrt(Math.pow(fx - tx, 2) + Math.pow(fy - ty, 2));

			shape.setScaleY(dist/2);//shape.setScaleX(dist/2);shape.setScaleZ(dist/2);
			shape.setTranslateX((fx + tx)/2);shape.setTranslateY((fy + ty)/2);
			shape.setRotate(90 + Math.toDegrees(Math.atan2(ty - fy, tx - fx)) );

			to.takeEnergy(from.takeEnergy(energy).multiply(new BigDecimal("-1")));
			to.takeWater(from.takeWater(water).multiply(new BigDecimal("-1")));
			to.takeOxygen(from.takeOxygen(oxygen).multiply(new BigDecimal("-1")));
			to.takeNitrogen(from.takeNitrogen(nitrogen).multiply(new BigDecimal("-1")));
			to.takeCarbon(from.takeCarbon(carbon).multiply(new BigDecimal("-1")));


	}

	/**
	 * @return shape
	 */
	public Shape getShape() {
		return shape;
	}

	/**
	 * @param shape セットする shape
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}

}
