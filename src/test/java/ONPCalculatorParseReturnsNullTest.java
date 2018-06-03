import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
public class ONPCalculatorParseReturnsNullTest {

    private ONPCalculator onpCalculator;
    private String input;

    public ONPCalculatorParseReturnsNullTest(String input) {
        this.input = input;
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[]{
                "", null, "2+2", "(2+2=", "foobar"
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
        assertNull(onpCalculator.parseEquation(input));
    }

}