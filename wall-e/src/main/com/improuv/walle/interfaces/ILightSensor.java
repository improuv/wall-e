package com.improuv.walle.interfaces;

import com.improuv.walle.sensorstates.FloorColor;

public interface ILightSensor {

	public abstract FloorColor getColor();

	public abstract void setColor(FloorColor color);

	public abstract void attachListener(ILightSensorListener listener);
}