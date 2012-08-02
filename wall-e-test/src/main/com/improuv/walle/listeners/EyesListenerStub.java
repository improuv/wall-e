package com.improuv.walle.listeners;

import com.improuv.walle.WallE;

/**
 * A listener for the ultrasonic sensor - has to be manually triggered.
 * 
 * Apart from skipping the loop and sleep, this class implements the same
 * functionality as EyesListener. Only exception: while the real listener
 * has a sliding window of 5 values, we use only one value here.
 */
public class EyesListenerStub extends EyesListener {

	private int distance;
	
	@Override
	/** Has to be called manually. */
	public void run() {
		senseAndAlert();
	}
	
	@Override
	protected void readDistanceFromSensor() {
		distance = WallE.getInstance().getEyes().getDistance();
	}

	@Override
	protected int calculateMediumValue() {
		return distance;
	}
}
