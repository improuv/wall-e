package com.improuv.walle.listeners;

/**
 * A listener for the light- and color sensor.
 * 
 * Apart from skipping the loop and sleep, this class implements the same
 * functionality as LightSensorListener.
 */
public class LightSensorListenerStub extends LightSensorListener {
	
	@Override
	/** Has to be called manually. */
	public void run() {
		senseAndAlert();
	}
}
