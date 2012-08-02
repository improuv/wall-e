package com.improuv.walle.decorators;

import com.improuv.walle.WallE;
import com.improuv.walle.interfaces.IBumper;
import com.improuv.walle.sensorstates.BumperSide;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;

/**
 * Decorator for one or two touch sensors (depends on the version of the real physical wall-e)
 * at the front end of Wall-e. They should protect him from hurting himself when hitting a wall.
 */
public class Bumper implements IBumper {

	private TouchSensor left, right;
	
	public Bumper(SensorPort leftPort, SensorPort rightPort) {
		addListener(this.left, leftPort, BumperSide.LEFT);
		addListener(this.right, rightPort, BumperSide.RIGHT);
	}
	
	private void addListener(TouchSensor sensor, SensorPort port, final BumperSide side) {
		port.addSensorPortListener(new SensorPortListener() {
			public void stateChanged(SensorPort aSource, int oldValue, int newValue) {
				if(oldValue > 500 && newValue < 500) { //the values differ..usually, the "touched" value is in the 100s, the "not touched" value at 1023
					WallE.getInstance().onBumped(side);
				}
			}
		});
		sensor = new TouchSensor(port);
	}
	
	public Bumper(SensorPort port) {
		addListener(this.left, port, BumperSide.ONLY_ONE_BUMPER);
	}

	/**
	 * Empty Constructor for test-subclasses which have no idea what a real
	 * TouchSensor is (it's Lejos specific)
	 */
	protected Bumper() {
		//do nothing by purpose
	}

	@Override
    public void setBumped(boolean bumped) {
		//do nothing by purpose - is overwritten in test-subclasses
	}

	@Override
    public boolean bumped() {
		return left.isPressed() || (right != null ? right.isPressed() : false);
	}
}
