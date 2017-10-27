package seitaimodel.node;

import java.math.BigDecimal;
import java.util.Random;
import java.util.function.BiConsumer;

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
	/**エネルギー導通率関数*/
	public BiConsumer<Node, Node> energy;
	/**水導通率関数*/
	public BiConsumer<Node, Node> water;
	/**酸素導通率関数*/
	public BiConsumer<Node, Node> oxygen;
	/**窒素導通率関数*/
	public BiConsumer<Node, Node> nitrogen;
	/**炭素導通率関数*/
	public BiConsumer<Node, Node> carbon;
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
		carbon	= (from, to)->{
			to.takeCarbon(from.takeCarbon(BigDecimal.ZERO).multiply(new BigDecimal("-1")));
		};
		oxygen 	= (from, to)->{
			to.takeOxygen(from.takeOxygen(BigDecimal.ZERO).multiply(new BigDecimal("-1")));
		};
		nitrogen= (from, to)->{
			to.takeNitrogen(from.takeNitrogen(BigDecimal.ZERO).multiply(new BigDecimal("-1")));
		};
		energy	= (from, to)->{
			to.takeEnergy(from.takeEnergy(BigDecimal.ZERO).multiply(new BigDecimal("-1")));
		};
		water	= (from, to)->{
			to.takeWater(from.takeWater(BigDecimal.ZERO).multiply(new BigDecimal("-1")));
		};
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

			carbon.accept(from, to);
			oxygen.accept(from, to);
			nitrogen.accept(from, to);
			energy.accept(from, to);
			water.accept(from, to);



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
