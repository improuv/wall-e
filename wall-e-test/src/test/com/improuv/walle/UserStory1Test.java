package com.improuv.walle;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Ignore;
import org.junit.Test;

import com.improuv.main.ApplicationStub;
import com.improuv.walle.decorators.LightSensorStub;
import com.improuv.walle.interfaces.ILightSensor;
import com.improuv.walle.interfaces.IWallE;
import com.improuv.walle.interfaces.IWheel;
import com.improuv.walle.listeners.LightSensorListener;
import com.improuv.walle.sensorstates.FloorColor;

public class UserStory1Test {

    private final static int SOME_EXTRA_MILLISECONDS = 5;

    /*
    @Test
    public void sensorShouldCallWallesMethodWhenBlueSpotted() { // only sensor

        IWallE wallE = mock(IWallE.class);
        WallE.setInstance(wallE);

        ILightSensor lightSensor = new LightSensorStub();
        doReturn(lightSensor).when(wallE).getLightSensor();
        lightSensor.attachListener();

        lightSensor.setColor(FloorColor.BLUE);

        sleep(LightSensorListener.WAIT_BETWEEN_FRAMES_IN_MS + SOME_EXTRA_MILLISECONDS);

        verify(wallE, atLeastOnce()).onColorSpotted(FloorColor.BLUE);
    }
    */

    @Test
    @Ignore("Implementation got lost")
    public void walleShouldStopWheelsWhenBlueSpotted() throws Exception { // only
                                                                          // Wall-E
        IWallE wallE = WallE.getInstance();

        IWheel left = mock(IWheel.class);
        IWheel right = mock(IWheel.class);

        wallE.setWheels(left, right);

        wallE.onColorSpotted(FloorColor.BLUE);

        verify(left, times(1)).stop();
        verify(right, times(1)).stop();
    }

    @Ignore
    @Test
    public void afterStartingApplicationWalleShouldMove() throws Exception { // Application
        startApp();
        assertThat(WallE.getInstance().getWheels().isMoving(), is(true));
    }

    @Test
    @Ignore("Implementation got lost")
    public void applicationShouldStopWalleWhenBlueSpotted2() throws Exception { // Application
                                                                                // 2
        startApp();
        sleep(1000);
        WallE.getInstance().getLightSensor().setColor(FloorColor.BLUE);
        sleep(LightSensorListener.WAIT_BETWEEN_FRAMES_IN_MS + SOME_EXTRA_MILLISECONDS);
        assertThat(WallE.getInstance().getWheels().isMoving(), is(false));
    }

    private void startApp() {
        Application app = new ApplicationStub();
        app.run();
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}
