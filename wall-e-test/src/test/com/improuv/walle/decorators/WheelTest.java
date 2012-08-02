package com.improuv.walle.decorators;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.improuv.walle.decorators.WheelStub;
import com.improuv.walle.interfaces.IWheel;
import com.improuv.walle.sensorstates.WheelsMovement;

public class WheelTest {

	private IWheel wheel;
	
	@Before
	public void setUp() {
		wheel = new WheelStub();
	}
	
	@Test
	public void wheelShouldBeStoppedInitially() {
		assertThat(wheel.isMoving(), is(false));
	}

	@Test
	public void wheelShouldBeStandingInitially() {
		assertThat(wheel.getDirection(), is(WheelsMovement.STANDING));
	}

	@Test
	public void wheelShouldBeMovingWhenSetToForward() {
		wheel.forward(100);
		assertThat(wheel.isMoving(), is(true));
	}
	
	@Test
	public void wheelShouldNotBeTurningAfterStopped() {
		wheel.forward(100);
		wheel.stop();
		assertThat(wheel.isMoving(), is(false));
	}

	@Test
	public void wheelShouldBeMovingWhenSetToBackward() {
		wheel.forward(-100);
		assertThat(wheel.isMoving(), is(true));
	}
	
	@Test
	public void wheelShouldNotBeTurningAfterStoppedAgain() {
		wheel.forward(-100);
		wheel.stop();
		assertThat(wheel.isMoving(), is(false));
	}

	@Test
	public void wheelShouldMoveForwardWhenForwardDegreesPerSecondOverZero() {
		wheel.forward(1);
		assertThat(wheel.getDirection(), is(WheelsMovement.FORWARD));
	}

	@Test
	public void wheelShouldMoveForwardWhenBackwardDegreesPerSecondLessThanZero() {
		wheel.backward(-1);
		assertThat(wheel.getDirection(), is(WheelsMovement.FORWARD));
	}

	@Test
	public void wheelShouldMoveForwardWhenRotationDegreesPerSecondMoreThanZero() {
		wheel.rotate(360,100,true);
		assertThat(wheel.getDirection(), is(WheelsMovement.FORWARD));
	}

	@Test
	public void wheelShouldBeStandingWhenForwardDegreesPerSecondIsZero() {
		wheel.forward(0);
		assertThat(wheel.getDirection(), is(WheelsMovement.STANDING));
	}

	@Test
	public void wheelShouldBeStandingWhenBackwardDegreesPerSecondIsZero() {
		wheel.backward(0);
		assertThat(wheel.getDirection(), is(WheelsMovement.STANDING));
	}

	@Test
	public void wheelShouldBeStandingWhenRotationDegreesPerSecondIsZero() {
		wheel.rotate(360,0,true);
		assertThat(wheel.getDirection(), is(WheelsMovement.STANDING));
	}

	@Test
	public void wheelShouldBeStandingWhenRotationAngleIsZero() {
		wheel.rotate(0,100,true);
		assertThat(wheel.getDirection(), is(WheelsMovement.STANDING));
	}

	@Test
	public void wheelShouldMoveBackwardWhenForwardDegreesPerSecondLessThanZero() {
		wheel.forward(-1);
		assertThat(wheel.getDirection(), is(WheelsMovement.BACKWARD));
	}

	@Test
	public void wheelShouldMoveBackwardWhenBackwardDegreesPerSecondOverZero() {
		wheel.backward(1);
		assertThat(wheel.getDirection(), is(WheelsMovement.BACKWARD));
	}

	@Test
	public void wheelShouldMoveBackwardWhenRotationDegreesPerSecondLessThanZero() {
		wheel.rotate(360,-100,true);
		assertThat(wheel.getDirection(), is(WheelsMovement.BACKWARD));
	}
}
