package application.services;

import application.exceptions.InvalidTokenException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TokenNormalizationTest {

    protected CalculatorService calculatorService;

    @Test(expected = InvalidTokenException.class)
    public void givenAnotherSystemWhenItSendsTokenWithLowLenghtThenShouldReturnException() {
        calculatorService = new CalculatorService();
        calculatorService.normalizeAuthorizationToken("token");
    }

    @Test
    public void givenAnotherSystemWhenItSendsValidTokenLenghtThenShouldReturnException() {
        calculatorService = new CalculatorService();
        String normalizedToken = calculatorService.normalizeAuthorizationToken("Bearer xRnanO9N3EoeZjQ6nanO9N3E");

        Assert.assertEquals("xRnanO9N3EoeZjQ6nanO9N3E", normalizedToken);
    }

}
