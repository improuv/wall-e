package com.improuv.walle;

import com.improuv.walle.decorators.WallEsWheels;
import com.improuv.walle.interfaces.IBumper;
import com.improuv.walle.interfaces.IEyes;
import com.improuv.walle.interfaces.IEyesListener;
import com.improuv.walle.interfaces.ILightSensor;
import com.improuv.walle.interfaces.ILightSensorListener;
import com.improuv.walle.interfaces.IWallE;
import com.improuv.walle.interfaces.IWheel;
import com.improuv.walle.sensorstates.BumperSide;
import com.improuv.walle.sensorstates.FloorColor;
import com.improuv.walle.utils.Log;

/**
 * This is Wall-E, our robot. The Application tells Wall-E what to do.
 * 
 * Assure that the hardware related stuff (sensors, motors) is hidden inside the
 * decorators (LightSensor, Wheel), since the tests use this class and have no
 * dependencies on lejos.*
 */
public class WallE implements IWallE {

	/* Wall-E's attached Sensors */
	private ILightSensor lightSensor;
	private WallEsWheels wheels;
	private IBumper bumper;
	private IEyes eyes;

	/* The one and only Wall-E */
	private static IWallE wallE;

	/**
	 * The class WallE is a singleton so that all listener-threads know who to
	 * talk to.
	 * 
	 * @return the good old, one and only Wall-e!
	 */
	public static IWallE getInstance() {
		if (wallE == null)
			return newInstance();
		return wallE;
	}

	/**
	 * For tests, it is important to get a new, fresh instance of Wall-e.
	 * 
	 * @return a fresh Wall-E
	 */
	public static IWallE newInstance() {
		wallE = new WallE();
		return wallE;
	}

	/**
	 * Some tests want to mock Wall-e.
	 * 
	 * @param iWallE
	 *            a Wall-e (maybe a mock)
	 */
	public static void setInstance(IWallE iWallE) {
		wallE = iWallE;
	}

	private WallE() {
		Log.print("Hi, I am Wall-E ..");
	}

	@Override
	public void onColorSpotted(FloorColor color) {
		Log.print(color.name());
		if (color.equals(FloorColor.GREEN)) {
			wallE.getWheels().stop();
		} else if(color.equals(FloorColor.RED)) {
			wallE.getWheels().turn(1, 500, true, false);
		}
	}

	@Override
	public void onObstacleDetected(int distance) {
	}

	@Override
	public void onBumped(BumperSide side) {
		wallE.getWheels().stop();
	}

	@Override
	public IEyes getEyes() {
		return eyes;
	}

	@Override
	public void setEyes(IEyes eyes, IEyesListener listener) {
		this.eyes = eyes;
		eyes.attachListener(listener);
	}

	@Override
	public IBumper getBumper() {
		return bumper;
	}

	@Override
	public void setBumper(IBumper bumper) {
		this.bumper = bumper;
	}

	@Override
	public WallEsWheels getWheels() {
		return wheels;
	}

	@Override
	public void setWheels(IWheel left, IWheel right) {
		this.wheels = new WallEsWheels(left, right);
	}

	@Override
	public ILightSensor getLightSensor() {
		return lightSensor;
	}

	@Override
	public void setLightSensor(ILightSensor lightSensor, ILightSensorListener listener) {
		this.lightSensor = lightSensor;
		lightSensor.attachListener(listener);
	}
}
