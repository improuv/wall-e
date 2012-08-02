package com.improuv.walle;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.improuv.walle.decorators.BumperStub;
import com.improuv.walle.decorators.WallEsWheels;
import com.improuv.walle.interfaces.IWallE;
import com.improuv.walle.interfaces.IWheel;

/**
 * Walle and the wall...
 */
public class UserStory2Test {

	@Test
	@Ignore("Implementation got lost")
	public void shouldStopWhenHitsWall() throws Exception {
		IWallE wallE = WallE.newInstance();
		
		IWheel oneWheel = mock(IWheel.class);
		wallE.setWheels(oneWheel, oneWheel);
		
		wallE.setBumper(new BumperStub());

		wallE.getWheels().forward(360);
		
		wallE.getBumper().setBumped(true);
		sleep(50);
		
		verify(oneWheel, times(2)).stop();
	}
	
	
	@Test
	//TODO needs refactoring
	public void wallETurnsRightWhenHitsWall() throws Exception {
		IWallE wallE = WallE.newInstance();
		
		IWheel oneWheel = mock(IWheel.class);
		wallE.setWheels(oneWheel, oneWheel);
		
		wallE.setBumper(new BumperStub());

		wallE.getWheels().forward(360);
		
		wallE.getBumper().setBumped(true);
		sleep(50);
		
		WallEsWheels wheels = mock(WallEsWheels.class);
		wheels.turn(1, 0, true, false);
		
		ArgumentCaptor<Double> noOfCircles = ArgumentCaptor.forClass(Double.class);
		ArgumentCaptor<Boolean> clockwise = ArgumentCaptor.forClass(Boolean.class);
		verify(wheels).turn(noOfCircles.capture(), anyInt(), clockwise.capture(), anyBoolean());
		assertEquals(1, noOfCircles.getValue().intValue());
		assertEquals(true, clockwise.getValue().booleanValue());
	}

	private void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			//do nothing
		}
	}
	
	
}
