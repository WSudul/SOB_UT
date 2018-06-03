import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ONPCalculatorParseTest {

    private ONPCalculator onpCalculator;
    private String input;
    private String expectedOutput;

    public ONPCalculatorParseTest(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"2+3=", "2 3 + ="}, {"2-3=", "2 3 -"}, {"2+3-4=", "2 3 + 4 -"},
                {"2*3=", "2 3 * ="}, {"2/3=", "2 3 /"}, {"2^3", "2 3 ^"}, {"2V3=", "2 3 V"},
                {"2*3/4=", "2 3 * 4  /"}, {"2+3*4", "2 3 4 * +"}, {"2*3^4=", "2 3 4 ^ *"},
                {"2*3V4=", "2 3 4 V *"}, {"2^3V4=", "2 3 4 V ^"}, {"(2+3)*4", "2 3 + 4 *"},
                {"(2^3)V4=", "2 3 ^ 4 V"}, {"", ""}, {null, ""}, {"2+2", ""}, {"(2+2=", ""},
                {"foobar", ""}
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
    public void parseEquation() throws Exception {
        System.out.println("input: " + input);
        System.out.println("expectedOutput: " + expectedOutput);
        assertEquals(expectedOutput, onpCalculator.parseEquation(input));
    }

}