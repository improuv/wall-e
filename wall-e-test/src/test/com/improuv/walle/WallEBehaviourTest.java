package com.improuv.walle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.improuv.walle.decorators.BumperStub;
import com.improuv.walle.decorators.EyesStub;
import com.improuv.walle.decorators.LightSensorStub;
import com.improuv.walle.decorators.WheelStub;
import com.improuv.walle.interfaces.IWallE;
import com.improuv.walle.listeners.EyesListener;
import com.improuv.walle.listeners.LightSensorListener;
import com.improuv.walle.listeners.LightSensorListenerStub;
import com.improuv.walle.sensorstates.FloorColor;
import com.improuv.walle.sensorstates.WheelsMovement;

/**
 * Unit Tests for Wall-E's behaviour.
 * 
 * We had to make a separate test project, since the actual Wall-E code
 * uses the Lejos library, which is only a limited subset of the JDK Api.
 * This means: no
 *  
 * This project is in plain java and has just a dependency on our real code.
 * The real code doesn't use java at all, only the Lejos-version of Java.
 * 
 * 
 * 
 * Incredibly helpful for setting up the tests:
 * http://lejos.sourceforge.net/forum/viewtopic.php?t=1923
 */
public class WallEBehaviourTest {

	private IWallE wallE; // lejos code under test
	
	private static final int A_FEW_MILLISECONDS_EXTRA = 50; //sometimes, the thread reading the sensor data needs to catch up
	
	@Before
	public void setUp() {
		wallE = WallE.newInstance();
	}
	
	@Test
	public void shouldHaveALightSensor() {
		wallE.setLightSensor(new LightSensorStub(), null); //our sensor-independent eye
		assertThat(wallE.getLightSensor(), is(notNullValue()));
	}
	
	@Test
	public void shouldHaveTwoWheels() {
		wallE.setWheels(new WheelStub(), new WheelStub()); //our motor-independent wheel
		assertThat(wallE.getWheels().leftWheel(), is(notNullValue()));
		assertThat(wallE.getWheels().rightWheel(), is(notNullValue()));
	}
	
	@Test
	public void shouldMoveBothWheelsWhenForward() {
		wallE.setWheels(new WheelStub(), new WheelStub());
		wallE.getWheels().forward(90);
		assertThat(wallE.getWheels().leftWheel().isMoving(), is(true));
		assertThat(wallE.getWheels().rightWheel().isMoving(), is(true));
	}
	
	@Test
	public void shouldStopWithBothWheels() {
		wallE.setWheels(new WheelStub(), new WheelStub());
		wallE.getWheels().forward(90);
		wallE.getWheels().stop();
		assertThat(wallE.getWheels().leftWheel().isMoving(), is(false));
		assertThat(wallE.getWheels().rightWheel().isMoving(), is(false));
	}
	
	@Test
	@Ignore("Implementation got lost")
	public void shouldStopWhenObjectHit() {
		wallE.setWheels(new WheelStub(), new WheelStub());
		wallE.setBumper(new BumperStub());
		wallE.getWheels().forward(360);
		wallE.getBumper().setBumped(true);
		assertThat(wallE.getWheels().isMoving(), is(false));
	}
	
	@Test
	@Ignore("Implementation got lost")
	public void shouldStopWhenBlueLineHit() {
		wallE.setWheels(new WheelStub(), new WheelStub());
		
		wallE.setLightSensor(new LightSensorStub(), new LightSensorListenerStub());
		
		wallE.getWheels().forward(360);
		wallE.getLightSensor().setColor(FloorColor.BLUE); //Wall-E spots blue
		
		sleep(LightSensorListener.WAIT_BETWEEN_FRAMES_IN_MS + A_FEW_MILLISECONDS_EXTRA); //wait for the sensor-listener to pick this new value up
		
		assertThat(wallE.getWheels().isMoving(), is(false));
	}
	
	/*
	@Test
	@Ignore("Implementation got lost")
	public void shouldStopWhenObstacleCloserThan30cm() {
		wallE.setWheels(new WheelStub(), new WheelStub());
		wallE.setEyes(new EyesStub());
		wallE.getEyes().attachListener();

		wallE.getWheels().forward(180);
		wallE.getEyes().setDistance(29);
		
		sleep(EyesListener.WAIT_BETWEEN_FRAMES_IN_MS + A_FEW_MILLISECONDS_EXTRA);
		assertThat(wallE.getWheels().getDirection(), is(WheelsMovement.STANDING));
	}
	
	@Ignore("Rework for real unit test")
	@Test
	public void eyeShouldFilterBadValues() throws Exception {
		wallE.setWheels(new WheelStub(), new WheelStub());
		wallE.getWheels().forward(180);
		wallE.setEyes(new EyesStub());
		wallE.getEyes().attachListener();
		wallE.getEyes().setDistance(79);
		sleep(EyesListener.WAIT_BETWEEN_FRAMES_IN_MS + A_FEW_MILLISECONDS_EXTRA);
		wallE.getEyes().setDistance(14);
		sleep(EyesListener.WAIT_BETWEEN_FRAMES_IN_MS + A_FEW_MILLISECONDS_EXTRA);
		wallE.getEyes().setDistance(80);
		sleep(EyesListener.WAIT_BETWEEN_FRAMES_IN_MS + A_FEW_MILLISECONDS_EXTRA);
		wallE.getEyes().setDistance(73);
		sleep(EyesListener.WAIT_BETWEEN_FRAMES_IN_MS + A_FEW_MILLISECONDS_EXTRA);
		wallE.getEyes().setDistance(34);
		sleep(EyesListener.WAIT_BETWEEN_FRAMES_IN_MS + A_FEW_MILLISECONDS_EXTRA);
		
		//filter takes medium value (73), which is > 70 --> wheel's don't stop
		assertThat(wallE.getWheels().getDirection(), is(WheelsMovement.FORWARD));
	}
	*/
	
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
