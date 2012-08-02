package com.improuv.walle.listeners;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.improuv.walle.WallE;
import com.improuv.walle.decorators.EyesStub;
import com.improuv.walle.interfaces.IEyes;
import com.improuv.walle.interfaces.IEyesListener;

public class EyeListenerTest {

	private final int maxRange = IEyesListener.MAXIMUM_DETECTION_RANGE_IN_CM;
	
	private IEyes eyes;
	private EyesListener listener;
	
	@Before
	public void setUp() {
		WallE.setInstance(spy(WallE.newInstance()));
		eyes = new EyesStub();
		listener = spy(new EyesListenerStub());
		eyes.attachListener(listener);
		WallE.getInstance().setEyes(eyes, listener);
	}
	
	@Test
	public void shouldBeActiveWhenStarted() {
		assertThat(eyes.isOn(), is(true));
	}
	
	@Test
	public void shouldTriggerInternallyWhenObstacleDetected() {
		setDistance(1);		
		verify(listener).alertWallE();
	}
	
	@Test
	public void shouldCallWalleWhenObstacleDetected() {
		setDistance(1);
		verify(WallE.getInstance()).onObstacleDetected(1);
	}

	@Test
	public void shouldNotTriggerWhenDetectedObstacleIsTooFarAway() {
		eyes.setDistance(maxRange + 1);
		verify(listener, times(0)).alertWallE();
	}

	private void setDistance(int distance) {
		try {
			eyes.setDistance(distance);
		} catch (NullPointerException e) {
			//Wall-e might be missing his wheels, which is ok for us, we only care about method invocation
		}
	}
}