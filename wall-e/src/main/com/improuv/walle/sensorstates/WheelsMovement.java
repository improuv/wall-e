package com.improuv.walle.sensorstates;

/**
 * Tells you in which direction the Wall-e's wheels are moving.
 */
public enum WheelsMovement {
	FORWARD,
	BACKWARD,
	STANDING,
	UNDEFINED /* used when both wheels use different directions */;
	//TODO: instead of undefined, implement clockwise and counterclockwise, both forwards and backwards
}
