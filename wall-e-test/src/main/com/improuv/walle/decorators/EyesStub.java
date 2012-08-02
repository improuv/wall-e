package com.improuv.walle.decorators;

import com.improuv.walle.interfaces.IEyes;
import com.improuv.walle.interfaces.IEyesListener;
import com.improuv.walle.listeners.EyesListener;
import com.improuv.walle.listeners.EyesListenerStub;


/**
 * Sensor independent version of the ultrasonic sensor. Calls attached listener when distance set manually.
 */
public class EyesStub implements IEyes {
    
    private int distance;
    private boolean isOn;
    private IEyesListener listener;
    
	public EyesStub() {
    	on();
    }
    
	@Override
	public void attachListener(IEyesListener listener) {
		this.listener = listener;
	}
	
    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public void setDistance(int distance) {
        this.distance = distance;
        if(listener != null)
        	listener.run();
    }
    
    @Override
    public void off() {
        isOn = false;
    }
    
    @Override
    public boolean isOn() {
        return isOn;
    }
    
    @Override
    public void on() {
        isOn = true;
    }
}
