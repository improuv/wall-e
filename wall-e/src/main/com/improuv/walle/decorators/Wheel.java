package com.improuv.walle.decorators;

import com.improuv.walle.interfaces.IWheel;
import com.improuv.walle.sensorstates.WheelsMovement;

import lejos.nxt.NXTRegulatedMotor;

/**
 * A decorator for Motor, used for the motors which let Wall-E move.
 */
public class Wheel implements IWheel {

	private NXTRegulatedMotor wheel;
	protected WheelsMovement direction = WheelsMovement.STANDING;
	
	/** 
	 * The new Wall-e has its motors facing the opposite direction - a forward effectively
	 * moves wall-E backwards. This constant is used to switch the sign of the degrees handed
	 * over as arguments for the rotatE() method - this does the trick.
	 */
	private final static int MOTORS_ATTACHED_IN_OPPOSITE_DIRECTION_FIXER = -1;
	

	public Wheel(NXTRegulatedMotor wheel) {
		this.wheel = wheel;
	}

	/**
	 * Empty Constructor for test-subclasses which have no idea what a real
	 * Motor is (it's Lejos specific)
	 */
	protected Wheel() {
		//do nothing by purpose
	}

	@Override
	public void forward(int degreesPerSecond) {
		wheel.setSpeed(degreesPerSecond);
		wheel.backward(); //motors are attached in the wrong direction
		this.direction = WheelsMovement.FORWARD;
	}
	
	@Override
	public void backward(int degreesPerSecond) {
		wheel.setSpeed(MOTORS_ATTACHED_IN_OPPOSITE_DIRECTION_FIXER *degreesPerSecond);
		wheel.forward(); //motors are attached in the wrong direction
		this.direction = WheelsMovement.BACKWARD;
	}
	
	@Override
	public void rotate(int degrees, int degreesPerSecond, boolean immediateReturn) {
		wheel.setSpeed(degreesPerSecond);
		wheel.rotate(MOTORS_ATTACHED_IN_OPPOSITE_DIRECTION_FIXER * degrees, immediateReturn);
		if(degrees > 0)
			direction = WheelsMovement.FORWARD;
		else if(degrees < 0)
			direction = WheelsMovement.BACKWARD;
		else
			direction = WheelsMovement.STANDING;
	}

	@Override
	public boolean isMoving() {
		return wheel.isMoving();
	}

	@Override
	public WheelsMovement getDirection() {
		return direction;
	}
	
	@Override
	public void stop() {
		wheel.stop();
		this.direction = WheelsMovement.STANDING;
	}
}
