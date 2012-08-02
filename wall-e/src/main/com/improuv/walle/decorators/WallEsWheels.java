package com.improuv.walle.decorators;

import com.improuv.walle.interfaces.IWheel;
import com.improuv.walle.sensorstates.WheelsMovement;


public class WallEsWheels {

	public final static double DISTANCE_BETWEEN_LEFT_AND_RIGHT_CHAIN_IN_CM = 14;
	private static final double SLIDE_EFFECT_CORRECTIONS_FACTOR = 0.80; //the chains are not made of rubber, poor friction causes them to slide..
	public final static double WHEEL_360_DEG_TURN_IN_CM = 13 * SLIDE_EFFECT_CORRECTIONS_FACTOR;
	
	private IWheel left, right;
	
	public WallEsWheels(IWheel left2, IWheel right2) {
		this.left = left2;
		this.right = right2;
	}
	
    public IWheel leftWheel() {
		return left;
	}

    public IWheel rightWheel() {
		return right;
	}
	
	public void forward(int degreesPerSecond) {
    	left.forward(degreesPerSecond);
    	right.forward(degreesPerSecond);
    }
    
    public void backward(int degreesPerSecond) {
    	left.backward(degreesPerSecond);
    	right.backward(degreesPerSecond);
    }
    
    public void stop() {
    	left.stop();
    	right.stop();
    }

    public boolean isMoving() {
    	return left.isMoving() && right.isMoving();
    }
    
	public WheelsMovement getDirection() {
		WheelsMovement dirLeft = left.getDirection();
		WheelsMovement dirRight = right.getDirection();
		if(dirLeft == dirRight)
			return dirLeft;
		else
			return WheelsMovement.UNDEFINED;
	}
	
	/**
	 * Rotates Wall-E.
	 * 
	 * @param noOfCircles number of circles, e.g. 1 for a full turn, 0.25 for a quarter turn
	 * @param wheelDegreesPerSecond the speed of the left and right main-wheel, in degrees per second
	 * @param clockwise        should Wall-e rotate clockwise or counterclockwise?
	 * @param immediateReturn  whether this turn is blocking (false) or non-blocking (true)
	 */
	public void turn(double noOfCircles, int wheelDegreesPerSecond, boolean clockwise, boolean immediateReturn) {
    	int oneFullCircleInWheelDegrees = calculateWheelTurnsInDegreesForWalleTurns(noOfCircles);

    	if(!clockwise)
    		oneFullCircleInWheelDegrees *= -1;
    	
		left.rotate(oneFullCircleInWheelDegrees, wheelDegreesPerSecond, true);
		right.rotate(-oneFullCircleInWheelDegrees, wheelDegreesPerSecond, immediateReturn);
	}

	/**
	 * Calculates the number of turns (in degrees), that both motors of Wall-e have to do
	 * in order to make Wall-E turn. 
	 * "1" is one full turn, "0.25" is a quarter circle, "3" is three full turns and so on...
	 * 
	 * @param numberOfCirclesWalleShouldDo Number of full turns of Wall-e
	 * @return                             Number of Wheel turns for each motor in degrees
	 */
	private int calculateWheelTurnsInDegreesForWalleTurns(double numberOfCirclesWalleShouldDo) {
		double nrOfCirclesInDegrees = numberOfCirclesWalleShouldDo * 360.0;
		
		double oneFullCircleWithOneChainMovingInCm = 2 * DISTANCE_BETWEEN_LEFT_AND_RIGHT_CHAIN_IN_CM * Math.PI;
    	double oneFullCircleWithBothChainsMovingInCm = oneFullCircleWithOneChainMovingInCm / 2;
    	int oneFullCircleInWheelDegrees = (int) (oneFullCircleWithBothChainsMovingInCm / WHEEL_360_DEG_TURN_IN_CM * nrOfCirclesInDegrees);
		return oneFullCircleInWheelDegrees;
	}
	
}
