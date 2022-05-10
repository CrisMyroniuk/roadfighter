package roadfighter;

import java.awt.Image;

public abstract class MapObject {

	private Image model;
	private double x;
	private double y;
	private double width;
	private double height;
	
	public Image getModel() {
		return model;
	}

	public void setModel(Image model) {
		this.model = model;
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

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}


}
