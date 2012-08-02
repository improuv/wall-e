package com.improuv.walle;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.improuv.walle.WallE;
import com.improuv.walle.decorators.LightSensorStub;
import com.improuv.walle.decorators.WheelStub;
import com.improuv.walle.interfaces.IWallE;
import com.improuv.walle.listeners.LightSensorListener;
import com.improuv.walle.listeners.LightSensorListenerStub;
import com.improuv.walle.sensorstates.FloorColor;

public class WallEAdventureTest {

	private static final int LATENCY = 20;
	private IWallE walle;

	@Before
	public void setup() {
		walle = WallE.newInstance();
		walle.setWheels(new WheelStub(), new WheelStub());
		walle.setLightSensor(new LightSensorStub(), new LightSensorListenerStub());
	}

	@Test
	@Ignore("Implementation got lost")
	public void whenReachingBlueLineWalleShouldStop() {
		walle.getWheels().forward(1000);
		walle.getLightSensor().setColor(FloorColor.BLUE);
		sleep();
		assertThat(walle.getWheels().isMoving(), CoreMatchers.is(false));
	}

	private void sleep() {
		try {
			Thread.sleep(LightSensorListener.WAIT_BETWEEN_FRAMES_IN_MS + LATENCY);
		} catch (InterruptedException e) {
			// do nothing
		}
	}
}
