package com.improuv.walle.decorators;

import com.improuv.walle.WallE;
import com.improuv.walle.interfaces.IBumper;
import com.improuv.walle.sensorstates.BumperSide;


/**
 * Sensor-independent version of a touch sensor. Calls Wall-e's onBumped() method when bumped is set to true manually.
 */
public class BumperStub implements IBumper {
	
	private boolean bumped = false;
	
	/**
	 * Sets if bumped or not (since we don't have a sensor which could gather that data).
	 * And calls Wall-E's touch-listener.
	 */
	public void setBumped(boolean touched) {
		this.bumped = touched;
		if(touched)
			WallE.getInstance().onBumped(BumperSide.ONLY_ONE_BUMPER);
	}
	
	/**
	 * Returns the latest set bumped-value.
	 */
	public boolean bumped() {
		return bumped;
	}
}
