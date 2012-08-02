package com.improuv.walle;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Timer;

import org.hamcrest.text.pattern.internal.ast.OneOrMore;
import org.junit.Test;
import org.mockito.Mockito;

import com.improuv.main.ApplicationStub;
import com.improuv.walle.decorators.LightSensorStub;
import com.improuv.walle.interfaces.ILightSensor;
import com.improuv.walle.interfaces.IWallE;
import com.improuv.walle.sensorstates.FloorColor;

public class WallEDiscoversTheHillTest {

	@Test
	public void whenApplicationRunShouldBeMovingInitially() throws Exception {
		Application app = new ApplicationStub();
		app.run();
		assertThat(WallE.getInstance().getWheels().isMoving(), is(true));
	}
	
	/*
	@Test
	public void shouldDetectGreenColor() throws Exception {
        IWallE wallE = mock(IWallE.class);
        WallE.setInstance(wallE);

        ILightSensor lightSensor = new LightSensorStub();
        doReturn(lightSensor).when(wallE).getLightSensor();
        lightSensor.attachListener();

		lightSensor.setColor(FloorColor.GREEN);
		waitForListener(); // wait for the listener
		
		verify(wallE, atLeastOnce()).onColorSpotted(FloorColor.GREEN);
	}
*/
	
	private void waitForListener() throws InterruptedException {
		Thread.sleep(2 * (1000/30));
	}
	
	@Test
	public void whenDiscoveredGreenColorShouldStopMoving() throws InterruptedException {
		Application app = new ApplicationStub();
		app.run();
		app.wallE.getLightSensor().setColor(FloorColor.GREEN);
		waitForListener();
		assertThat(WallE.getInstance().getWheels().isMoving(), is(false));
	}
	
	
	@Test
	public void whenDiscoveredBlueColorShouldKeepOnMoving() throws InterruptedException {
		Application app = new ApplicationStub();
		app.run();
		app.wallE.getLightSensor().setColor(FloorColor.BLUE);
		waitForListener();
		assertThat(WallE.getInstance().getWheels().isMoving(), is(true));
	}
	
	@Test
	public void wallEStopsAtTheWall() throws Exception {
		Application app = new ApplicationStub();
		app.run();
		app.wallE.getBumper().setBumped(true);
		
		assertThat(WallE.getInstance().getWheels().isMoving(), is(false));
	}
}
