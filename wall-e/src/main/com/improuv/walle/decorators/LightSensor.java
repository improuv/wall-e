package com.improuv.walle.decorators;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;

import com.improuv.walle.interfaces.ILightSensor;
import com.improuv.walle.interfaces.ILightSensorListener;
import com.improuv.walle.listeners.LightSensorListener;
import com.improuv.walle.sensorstates.FloorColor;

/**
 * A decorator for ColorSensor.
 * 
 * This decorator allows to be subclassed in the Wall-E JUnit Testing Project.
 * 
 * Why subclassing and not mocking? Mocking is not an option. Although Mockito
 * does not call the default constructor, it still tries to instantiate 
 * static ColorSensor.S1 and his buddies, which DO call the constructor, which
 * in turn queries the hardware.
 * 
 * Therefore, subclassing a decorator is the only way to separate the
 * TestCode from the actual hardware, allowing TDD.
 */
public class LightSensor implements ILightSensor {
	
	private ColorSensor sensor;

	public LightSensor(SensorPort port) {
		this.sensor = new ColorSensor(port);
		sensor.getColor();
	}
	
	@Override
	public void setColor(FloorColor color) {
		//do nothing by purpose - is overwritten in test-subclasses
	}

	@Override
	public FloorColor getColor() {
		return FloorColor.getColor(sensor.getColorID());
	}

	@Override
	public void attachListener(ILightSensorListener listener) {
		new Thread(listener).start();
	}

}
