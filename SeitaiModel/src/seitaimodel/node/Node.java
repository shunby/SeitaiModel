package seitaimodel.node;


import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

public class Node {
	/**量*/
	private double amount;
	/**エネルギー*/
	private double energy;
	/**水*/
	private double water;
	/**酸素*/
	private double oxygen;
	/**窒素*/
	private double nitrogen;
	/**炭素*/
	private double carbon;

	/**画面表示用の図形*/
	private Shape shape;
	/**画面表示用のText*/
	private Text text;
	/**表示座標*/
	private double x, y;
	/**名前*/
	private String name;

	public Node(String name, double x, double y) {
		this.x = x;
		this.y = y;
		this.name = name;

		shape = new Rectangle(0, 0, 130, 130);
		Rectangle rect = (Rectangle)shape;
		rect.setArcWidth(20);
		rect.setArcHeight(20);

		shape.getTransforms().add(new Translate(x, y));

		text = new Text(toString());
		text.setFill(Color.WHITE);
		text.getTransforms().add(new Translate(x + 10, y + 20));

	}

	public void update(){
		text.setText(toString());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String reline = System.getProperty("line.separator");
		sb.append("      ").append(name).append(reline);
		sb.append("amount: ").append(amount).append(reline)
			.append("N: ").append(nitrogen).append(reline)
			.append("C: ").append(carbon).append(reline)
			.append("O: ").append(oxygen).append(reline)
			.append("Energy: ").append(energy).append(reline)
			.append("water: ").append(water).append(reline);
		return sb.toString();
	}
















	//get/set----------------------------------------------------------
	public double takeCarbon(double take) {
		if (take < carbon) {
			carbon -= take;
			return take;
		} else {
			double tmp = carbon;
			carbon = 0;
			return tmp;
		}
	}

	public double takeNitrogen(double take) {
		if (take < nitrogen) {
			nitrogen -= take;
			return take;
		} else {
			double tmp = nitrogen;
			nitrogen = 0;
			return tmp;
		}
	}

	public double takeEnergy(double take) {
		if (take < energy) {
			energy -= take;
			return take;
		} else {
			double tmp = energy;
			energy = 0;
			return tmp;
		}
	}

	public double takeWater(double take) {
		if (take < water) {
			water -= take;
			return take;
		} else {
			double tmp = water;
			water = 0;
			return tmp;
		}
	}

	public double takeOxygen(double take) {
		if (take < oxygen) {
			oxygen -= take;
			return take;
		} else {
			double tmp = oxygen;
			oxygen = 0;
			return tmp;
		}
	}

	public double takeAmount(double take) {
		if (take < amount) {
			amount -= take;
			return take;
		} else {
			double tmp = amount;
			amount = 0;
			return tmp;
		}
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getWater() {
		return water;
	}

	public void setWater(double water) {
		this.water = water;
	}

	public double getOxygen() {
		return oxygen;
	}

	public void setOxygen(double oxygen) {
		this.oxygen = oxygen;
	}

	public double getNitrogen() {
		return nitrogen;
	}

	public void setNitrogen(double nitrogen) {
		this.nitrogen = nitrogen;
	}

	public double getCarbon() {
		return carbon;
	}

	public void setCarbon(double carbon) {
		this.carbon = carbon;
	}

	public double getAmount() {
		return amount;
	}

	public Shape getShape() {
		return shape;
	}

	public void setPolygon(Polygon polygon) {
		this.shape = polygon;
	}

	public Text getText() {
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

}
