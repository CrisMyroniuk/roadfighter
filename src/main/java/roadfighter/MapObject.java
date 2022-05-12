package roadfighter;

import java.awt.Image;

public abstract class MapObject {

	private Image model;
	private Coordinate coordinate;
	private double width;
	private double height;
	
	public Image getModel() {
		return model;
	}

	public void setModel(Image model) {
		this.model = model;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
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
