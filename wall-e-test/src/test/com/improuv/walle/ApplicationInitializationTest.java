package com.improuv.walle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.improuv.main.ApplicationStub;
import com.improuv.walle.interfaces.IWallE;
import com.improuv.walle.sensorstates.BumperSide;
import com.improuv.walle.sensorstates.FloorColor;

/**
 * Tests application initialization of sensors, motors and wiring of listeners.
 */
public class ApplicationInitializationTest {
    
    private IWallE wallE;
    
    @Before
    public void setup() {
        new ApplicationStub(); //initializes Wall-e
        wallE = WallE.getInstance();
    }
    
    @Test
    public void shouldHaveAllMotorsAndSensors() {
        assertThat(wallE, is(notNullValue()));
        assertThat(wallE.getWheels(), is(notNullValue()));
        assertThat(wallE.getWheels().leftWheel(), is(notNullValue()));
        assertThat(wallE.getWheels().rightWheel(), is(notNullValue()));
        assertThat(wallE.getEyes(), is(notNullValue()));
        assertThat(wallE.getLightSensor(), is(notNullValue()));
        assertThat(wallE.getBumper(), is(notNullValue()));
    }

    @Test
    public void shouldHaveLightSensorListenerAttached() {
    	wallE.getLightSensor().setColor(FloorColor.RED);
        verify(wallE).onColorSpotted(FloorColor.RED);
    }
    
    @Test
    public void shouldHaveUltrasonicSensorListenerAttached() {
    	wallE.getEyes().setDistance(20);
        verify(wallE).onObstacleDetected(20);
    }

    @Test
    public void shouldHaveBumperListenerAttached() {
    	wallE.getBumper().setBumped(true);
        verify(wallE).onBumped(any(BumperSide.class));
    }
}
