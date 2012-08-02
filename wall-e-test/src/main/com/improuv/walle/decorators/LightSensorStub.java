package com.improuv.walle.decorators;

import com.improuv.walle.interfaces.ILightSensor;
import com.improuv.walle.interfaces.ILightSensorListener;
import com.improuv.walle.listeners.LightSensorListener;
import com.improuv.walle.sensorstates.FloorColor;

/**
 * Sensor-independent version of the light- and color sensor. Calls attached listener when color is set manually.
 */
public class LightSensorStub implements ILightSensor {

	private FloorColor color = FloorColor.NONE;
	private ILightSensorListener listener;
	
	@Override
	public void setColor(FloorColor color) {
		this.color = color;
		if(listener != null)
			listener.run();
	}

	@Override
	public FloorColor getColor() {
		return color;
	}

	@Override
	public void attachListener(ILightSensorListener listener) {
		this.listener = listener;
	}
}

