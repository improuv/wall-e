package com.improuv.walle.listeners;

import java.util.Arrays;

import com.improuv.walle.WallE;
import com.improuv.walle.interfaces.IEyes;
import com.improuv.walle.interfaces.IEyesListener;


/**
 * Alerts Wall-e if an obstacle is detected within the maximum detection range.
 */
public class EyesListener implements IEyesListener {

	private final static int FRAMES_PER_SECOND = 20;
	public final static int WAIT_BETWEEN_FRAMES_IN_MS = 1000 / FRAMES_PER_SECOND;
	
	/* Of the last 5 values, we cut the two highest and the two lowest
	 * values and use the medium one as the current distance. Therefore,
	 * we can eliminate errors. A typical list by the sensor looks like:
	 * 59 58 58 34 34 59 59 60 58 59 58 59 59 ...
	 * See the two 34s? They're errors - we don't want them inside, not even averaged.
	 */
	final int[] slidingWindow = new int[]{0,0,0,0,0};
	private int lastIndex = -1;
	private int lastCalculatedMediumDistance;

	@Override
	public void run() {
		while(true) {
			senseAndAlert();
			sleep(WAIT_BETWEEN_FRAMES_IN_MS);
		}
	}

	/** protected so that it can be called by test listeners run() methods, who want to skip the sleeping. */
	protected void senseAndAlert() {
		readDistanceFromSensor();
		if(obstacleDetected()) {
			System.out.println("obstacle detected");
			alertWallE();
		} else {
			System.out.println("NO obstacle detected");
		}
	}

	/** protected since the ListenerStub doesn't use a sliding window */
	protected void readDistanceFromSensor() {
		addValueToSlidingWindow(WallE.getInstance().getEyes().getDistance());
		System.out.println("added value to sliding window");
	}

	private boolean obstacleDetected() {
		lastCalculatedMediumDistance = calculateMediumValue();
		if(lastCalculatedMediumDistance < MAXIMUM_DETECTION_RANGE_IN_CM)
			return true;
		return false;
	}

	void alertWallE() {
		WallE.getInstance().onObstacleDetected(lastCalculatedMediumDistance);
	}

	/** protected since the ListenerStub doesn't use a sliding window */
	protected int calculateMediumValue() {
		int[] b = slidingWindow.clone();
		Arrays.sort(b);
		return b[2];
	}

	private void addValueToSlidingWindow(int value) {
		slidingWindow[++lastIndex % 5] = value;
	}
	
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
