package com.improuv.walle.decorators;

import com.improuv.walle.interfaces.IWheel;
import com.improuv.walle.sensorstates.WheelsMovement;

/**
 * Motor-independent version of a wheel.
 */
public class WheelStub implements IWheel {
	private WheelsMovement direction = WheelsMovement.STANDING;
		
	@Override
	public void forward(int degreesPerSecond) {
		setDirection(degreesPerSecond);
	}
	
	@Override
	public void backward(int degreesPerSecond) {
		setDirection(-degreesPerSecond);
	}

	@Override
	public void stop() {
		setDirection(0);
	}
	
	@Override
	public void rotate(int degrees, int degreesPerSecond, boolean immediateReturn) {
		if(degrees != 0)
			setDirection(degreesPerSecond);
	}

	private void setDirection(int degreesPerSecond) {
		if(degreesPerSecond > 0)
			direction = WheelsMovement.FORWARD;
		else if(degreesPerSecond < 0)
			direction = WheelsMovement.BACKWARD;
		else
			direction = WheelsMovement.STANDING;
	}
		
	@Override
	public boolean isMoving() {
		return direction != WheelsMovement.STANDING;
	}

	@Override
	public WheelsMovement getDirection() {
		return direction;
	}
}
