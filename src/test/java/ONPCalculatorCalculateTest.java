import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ONPCalculatorCalculateTest {

    private final double kEpsilon = 0.01;
    private ONPCalculator onpCalculator;
    private String input;
    private Double expectedOutput;

    public ONPCalculatorCalculateTest(String input, Double expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"2 3 +", 5d}, {"2 3 -", -1d}, {"2 3 + 4 -", 1d},
                {"2 3 *", 6d}, {"2 3 /", 0.66d}, {"2 3 ^", 8d}, {"2 3 V", 1.2599d},
                {"2 3 * 4  /", 1.5}, {"2 3 4 * + ", 16d}, {"2 3 4 ^ *", 162d},
                {"2 3 4 V *", 2.632d}, {"2 3 4 V ^", 2.489}, {"2 3 + 4 *", 20d},
                {"2 3 ^ 4 V", 1.681d}, {"2 3 4 +", 0d}, {"", 0d}, {null, 0d}, {"2+2=", 0d}, {"(2+2", 0d},
                {"foobar", 0d}
        });
    }

    @Before
    public void setUp() throws Exception {
        onpCalculator = new ONPCalculator();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calculateONPEquation() throws Exception {
        System.out.println("input: " + input);
        System.out.println("expectedOutput: " + expectedOutput);
        assertEquals(expectedOutput, onpCalculator.calculateONPEquation(input), kEpsilon);
    }

}