package com.improuv.walle.decorators;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.improuv.walle.decorators.LightSensorStub;
import com.improuv.walle.interfaces.ILightSensor;
import com.improuv.walle.listeners.LightSensorListenerStub;
import com.improuv.walle.sensorstates.FloorColor;

public class EyesTest {

	private ILightSensor lightSensor;
	
	@Before
	public void setUp() {
		lightSensor = new LightSensorStub();
	}
	
	@Test
	public void colorIsNoneInitially() {
		assertThat(lightSensor.getColor(), is(FloorColor.NONE));
	}
	
	@Test
	public void colorSensorShouldReturnLatestSetColor() {
		lightSensor.setColor(FloorColor.BLACK); 
		assertThat(lightSensor.getColor(), is(FloorColor.BLACK));
	}
}
