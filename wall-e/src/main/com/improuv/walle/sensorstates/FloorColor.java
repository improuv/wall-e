package com.improuv.walle.sensorstates;

/**
 * The colors which Wall-e can detect independent from its environment.
 */
public enum FloorColor {
	
	NONE(-1),
	RED(0),
	GREEN(1),
	BLUE(2),
	YELLOW(3),
	WHITE(6),
	BLACK(7);
	
	private final int id;
	
	private FloorColor(int id) {
		this.id = id;
	}
	
	public static FloorColor getColor(int value) {
		for(FloorColor color : values()) {
			if(value == color.id)
				return color;
		}
		return NONE;
	}
}