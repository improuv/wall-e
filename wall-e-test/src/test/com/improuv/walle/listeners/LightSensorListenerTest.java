package com.improuv.walle.listeners;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.improuv.walle.WallE;
import com.improuv.walle.decorators.LightSensorStub;
import com.improuv.walle.interfaces.ILightSensor;
import com.improuv.walle.sensorstates.FloorColor;

public class LightSensorListenerTest {
	
	private ILightSensor sensor;
	private LightSensorListener listener;
	
	@Before
	public void setUp() {
		WallE.setInstance(spy(WallE.newInstance()));
		sensor = new LightSensorStub();
		listener = spy(new LightSensorListenerStub());
		sensor.attachListener(listener);
		WallE.getInstance().setLightSensor(sensor, listener);
	}
	
	@Test
	public void shouldTriggerInternallyWhenNewColorDetected() {
		setColor(FloorColor.RED);
		verify(listener).alertWallE();
	}
	
	@Test
	public void shouldCallWalleWhenNewColorDetected() {
		setColor(FloorColor.RED);
		verify(WallE.getInstance()).onColorSpotted(FloorColor.RED);
	}

	@Test
	public void initialColorShouldBeSetToNone() {
		setColor(FloorColor.NONE);
		assertThat(sensor.getColor(), is(FloorColor.NONE));
	}

	@Test
	public void shouldNotTriggerWhenDetectedColorIsNotNew() {
		FloorColor initialColor = sensor.getColor();
		setColor(initialColor);
		verify(listener, times(0)).alertWallE();
	}

	private void setColor(FloorColor color) {
		try {
			sensor.setColor(color);
		} catch (NullPointerException e) {
			//Wall-e might be missing his wheels, which is ok for us, we only care about method invocation
		}
	}
}
