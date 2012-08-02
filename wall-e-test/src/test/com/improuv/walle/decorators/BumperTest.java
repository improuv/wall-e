package com.improuv.walle.decorators;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.improuv.walle.decorators.BumperStub;

public class BumperTest {

	@Test
	public void initiallyShouldReturnFalse() {
		BumperStub cut = new BumperStub();
		assertThat(cut.bumped(), is(equalTo(false)));
	}
}
