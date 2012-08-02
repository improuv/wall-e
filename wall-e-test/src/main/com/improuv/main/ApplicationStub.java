package com.improuv.main;

import static org.mockito.Mockito.spy;

import com.improuv.walle.Application;
import com.improuv.walle.WallE;
import com.improuv.walle.decorators.BumperStub;
import com.improuv.walle.decorators.EyesStub;
import com.improuv.walle.decorators.LightSensorStub;
import com.improuv.walle.decorators.WheelStub;
import com.improuv.walle.listeners.EyesListenerStub;
import com.improuv.walle.listeners.LightSensorListenerStub;

/**
 * Sensor- and Motor-independent extension of the Application.
 */
public class ApplicationStub extends Application {
	
	/**
	 * Initializes Wall-e. Wall-e itself is a spy.
	 */
	protected void init() {
		wallE = WallE.newInstance();
		wallE.setWheels(new WheelStub(), new WheelStub());
		wallE.setLightSensor(new LightSensorStub(), new LightSensorListenerStub());
		wallE.setEyes(new EyesStub(), new EyesListenerStub());
		wallE.setBumper(new BumperStub());
		
		WallE.setInstance(spy(wallE));
	}
}
