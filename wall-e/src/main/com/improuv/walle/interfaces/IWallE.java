package com.improuv.walle.interfaces;

import com.improuv.walle.decorators.WallEsWheels;
import com.improuv.walle.sensorstates.BumperSide;
import com.improuv.walle.sensorstates.FloorColor;


public interface IWallE {

	public abstract void setWheels(IWheel left, IWheel right);

	public abstract WallEsWheels getWheels();

	public abstract void setEyes(IEyes eyes, IEyesListener listener);
	
	public abstract IEyes getEyes();

	public abstract void setLightSensor(ILightSensor lightSensor, ILightSensorListener listener);

	public abstract ILightSensor getLightSensor();

	public abstract void setBumper(IBumper bumper);

	public abstract IBumper getBumper();


	public abstract void onColorSpotted(FloorColor color);

	public abstract void onObstacleDetected(int distance);

	public abstract void onBumped(BumperSide side);
}