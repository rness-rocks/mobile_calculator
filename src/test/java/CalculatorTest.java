import activities.Calculator;
import activities.IOSCalculator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CalculatorTest extends BaseTest {

    @Test
    public void AdditionTest() {
        Calculator calculator = new IOSCalculator(driver);

        calculator.locateButton3().click();
        calculator.locateButtonMultiply().click();
        calculator.locateButton2().click();
        calculator.locateButton3().click();
        calculator.locateButtonEquls().click();
        assertThat(calculator.locateResultsField().getText(), equalTo("69.0"));
    }
}
