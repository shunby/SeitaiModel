package seitaimodel.node;


import java.math.BigDecimal;
import java.util.Random;

import seitaimodel.Main;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

public class Node implements Updatable {
	/**エネルギー*/
	protected BigDecimal energy;
	/**水*/
	protected BigDecimal water;
	/**酸素*/
	protected BigDecimal oxygen;
	/**窒素*/
	protected BigDecimal nitrogen;
	/**炭素*/
	protected BigDecimal carbon;


	/**画面表示用の図形*/
	protected Shape shape;
	/**画面表示用のText*/
	protected Label text;
	/**表示座標*/
	protected double x, y;
	/**名前*/
	protected String name;

	public Node(Pane pane, String name, double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.name = name;
		initVar();

		shape = new Rectangle(0, 0, w, h);
		Rectangle rect = (Rectangle)shape;
		rect.setArcWidth(20);
		rect.setArcHeight(20);

		shape.setTranslateX(x);
		shape.setTranslateY(y);
		Random r = new Random();
		shape.setFill(new Color(r.nextDouble()/2, r.nextDouble()/2, r.nextDouble()/2, 1));

		shape.setOnDragDetected((e)->{
			Dragboard db = shape.startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putString(name);//ドラッグされたとき、クリップボードにnameをコピー
			db.setContent(content);
			e.consume();
		});


		text = new Label("");
		text.setTextFill(Color.WHITE);
		text.setTranslateX(x + 10);
		text.setTranslateY(y + 20);
		text.setMouseTransparent(true);

		pane.getChildren().addAll(shape, text);

	}

	private void initVar(){
		carbon	= new BigDecimal(0);
		oxygen 	= new BigDecimal(0);
		nitrogen= new BigDecimal(0);
		energy= new BigDecimal(0);
		water = new BigDecimal(0);
	}

	public void update(){

		Platform.runLater(()->text.setText(toString()));

		text.setTranslateX(x + 10);
		text.setTranslateY(y + 20);
		shape.setTranslateX(x);
		shape.setTranslateY(y);

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String reline = System.getProperty("line.separator");
		sb.append("      ").append(name).append(reline);
		sb.append("N: ").append(nitrogen).append(reline)
			.append("C: ").append(carbon).append(reline)
			.append("O: ").append(oxygen).append(reline)
			.append("Energy: ").append(energy).append(reline)
			.append("water: ").append(water).append(reline);
		return sb.toString();
	}
















	//get/set----------------------------------------------------------
	public BigDecimal takeCarbon(BigDecimal take) {
		if (take.doubleValue() <= carbon.doubleValue()) {
			carbon = carbon.subtract(take);
			return take;
		} else {
			BigDecimal tmp = new BigDecimal(carbon.toString());
			carbon = new BigDecimal("0");
			return tmp;
		}
	}


	public BigDecimal takeEnergy(BigDecimal take) {
		if (take.doubleValue() <= energy.doubleValue()) {
			energy = energy.subtract(take);
			return take;
		} else {
			BigDecimal tmp = new BigDecimal(energy.toString());
			energy = new BigDecimal("0");
			return tmp;
		}
	}

	public BigDecimal takeWater(BigDecimal take) {
		if (take.doubleValue() <= water.doubleValue()) {
			water = water.subtract(take);
			return take;
		} else {
			BigDecimal tmp = new BigDecimal(water.toString());
			water = new BigDecimal("0");
			return tmp;
		}
	}

	public BigDecimal takeOxygen(BigDecimal take) {
		if (take.doubleValue() <= oxygen.doubleValue()) {
			oxygen = oxygen.subtract(take);
			return take;
		} else {
			BigDecimal tmp = new BigDecimal(oxygen.toString());
			oxygen = new BigDecimal("0");
			return tmp;
		}
	}

	public BigDecimal takeNitrogen(BigDecimal take) {
		if (take.doubleValue() <= nitrogen.doubleValue()) {
			nitrogen = nitrogen.subtract(take);
			return take;
		} else {
			BigDecimal tmp = new BigDecimal(nitrogen.toString());
			nitrogen = new BigDecimal("0");
			return tmp;
		}
	}


	public BigDecimal getEnergy() {
		return energy;
	}

	public void setEnergy(BigDecimal energy) {
		this.energy = energy;
	}

	public BigDecimal getWater() {
		return water;
	}

	public void setWater(BigDecimal water) {
		this.water = water;
	}

	public BigDecimal getOxygen() {
		return oxygen;
	}

	public void setOxygen(BigDecimal oxygen) {
		this.oxygen = oxygen;
	}

	public BigDecimal getNitrogen() {
		return nitrogen;
	}

	public void setNitrogen(BigDecimal nitrogen) {
		this.nitrogen = nitrogen;
	}

	public BigDecimal getCarbon() {
		return carbon;
	}

	public void setCarbon(BigDecimal carbon) {
		this.carbon = carbon;
	}

	public Shape getShape() {
		return shape;
	}

	public void setPolygon(Polygon polygon) {
		this.shape = polygon;
	}

	public Label getText() {
		return text;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
