package com.improuv.walle;

import com.improuv.walle.decorators.Bumper;
import com.improuv.walle.decorators.Eyes;
import com.improuv.walle.decorators.LightSensor;
import com.improuv.walle.decorators.Wheel;
import com.improuv.walle.interfaces.IWallE;
import com.improuv.walle.listeners.EyesListener;
import com.improuv.walle.listeners.LightSensorListener;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class Application {

	protected IWallE wallE;

	public static void main(String[] args) {
		
    	Application app = new Application();
    	app.run();

    	Button.waitForAnyPress(); //just to quickly read what's on the display
    }
	public Application() {
		init();
	}

	public void run() {
		wallE.getWheels().forward(300);
	}

	protected void init() {
		wallE = WallE.newInstance();
		
    	wallE.setWheels(new Wheel(Motor.A), new Wheel(Motor.C));

    	wallE.setLightSensor(new LightSensor(SensorPort.S3), new LightSensorListener());
    	wallE.setEyes(new Eyes(SensorPort.S2), new EyesListener());
    	wallE.setBumper(new Bumper(SensorPort.S4, SensorPort.S1));
	}
}
