package com.improuv.walle.listeners;

import com.improuv.walle.WallE;
import com.improuv.walle.interfaces.ILightSensorListener;
import com.improuv.walle.sensorstates.FloorColor;


/**
 * A Listener for the Light Sensor. Alerts Wall-e whenever a new color is spotted.
 */
public class LightSensorListener implements ILightSensorListener {

	private final static int FRAMES_PER_SECOND = 30;
	public final static int WAIT_BETWEEN_FRAMES_IN_MS = 1000 / FRAMES_PER_SECOND;
	
	private FloorColor lastColor = FloorColor.NONE, currentColor = FloorColor.NONE;

	@Override
	public void run() {
		while (true) {
			senseAndAlert();
			sleep(WAIT_BETWEEN_FRAMES_IN_MS);
		}
	}

	/** senseAndAlert is protected so that it can be called by test listeners run() methods, who want to skip the sleeping. */
	protected void senseAndAlert() {
		readColorFromSensor();		
		if(newColorSpotted()) {
			alertWallE();
			preserveLatestColor();
		}
	}

	private void readColorFromSensor() {
		currentColor = WallE.getInstance().getLightSensor().getColor();
	}

	private boolean newColorSpotted() {
		if(currentColor != FloorColor.NONE && currentColor != lastColor)
			return true;		
		return false;
	}

	void alertWallE() {
		WallE.getInstance().onColorSpotted(currentColor);
	}
	
	private void preserveLatestColor() {
		lastColor = currentColor;
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
