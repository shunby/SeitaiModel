package seitaimodel.node;

public class Node {
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

	public void update(){

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


}
