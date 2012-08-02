package com.improuv.walle.decorators;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

import com.improuv.walle.interfaces.IEyes;
import com.improuv.walle.interfaces.IEyesListener;
import com.improuv.walle.listeners.EyesListener;

public class Eyes implements IEyes {

	private UltrasonicSensor sensor;
	private boolean isOn;
	
	/**
	 * on by default.
	 */
	public Eyes(SensorPort port) {
		sensor = new UltrasonicSensor(port);
		on();
	}

	@Override
	public void attachListener(IEyesListener listener) {
		new Thread(listener).start();
	}
	
	@Override
    public void on() {
		if(sensor != null)
			sensor.continuous();
		isOn = true;
	}
	
	@Override
    public void off() {
		if(sensor != null)
			sensor.off();
		isOn = false;
	}
	
	@Override
    public int getDistance() {
		return sensor.getDistance();
	}

	@Override
    public void setDistance(int distance) {
		//unimplemented by purpose. TestableEyes overwrites this.
	}

	@Override
    public boolean isOn() {
		return isOn;
	}

}
