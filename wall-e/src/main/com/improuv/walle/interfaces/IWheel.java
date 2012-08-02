package com.improuv.walle.interfaces;

import com.improuv.walle.sensorstates.WheelsMovement;

public interface IWheel {
    
    public abstract void forward(int degreesPerSecond);
    
    public abstract void backward(int degreesPerSecond);
    
    public abstract void rotate(int degrees, int degreesPerSecond, boolean immediateReturn);
    
    public abstract boolean isMoving();
    
    public abstract WheelsMovement getDirection();
    
    public abstract void stop();
    
}