package dk.fitfit.power;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

// Stolen from...
// https://blog.codecentric.de/en/2011/11/testing-and-mocking-of-static-methods-in-java/

@RunWith(PowerMockRunner.class)
@PrepareForTest(MathUtil.class)
public class CalculatorTest {
	private Calculator calc;

	@Before
	public void setUp() {
		calc = new Calculator();

		PowerMockito.mockStatic(MathUtil.class);
		PowerMockito.when(MathUtil.addInteger(1, 1)).thenReturn(0);
		PowerMockito.when(MathUtil.addInteger(2, 2)).thenReturn(1);
	}

	@Test
	public void shouldCalculateInAStrangeWay() {
		assertEquals(0, calc.add(1, 1));
		assertEquals(1, calc.add(2, 2));

		PowerMockito.verifyStatic(times(2));
		MathUtil.addInteger(anyInt(),anyInt());
	}
}
