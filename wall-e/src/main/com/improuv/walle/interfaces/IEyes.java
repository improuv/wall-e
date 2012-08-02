package com.improuv.walle.interfaces;

public interface IEyes {
    
    public abstract void on();
    
    public abstract boolean isOn();

    public abstract void off();
    
    public abstract int getDistance();
    
    public abstract void setDistance(int distance);

	public abstract void attachListener(IEyesListener listener);
}