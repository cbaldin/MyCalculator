package application.services;

import application.entities.Operation;
import application.entities.OperationType;
import application.entities.Record;
import application.entities.Token;
import application.entities.User;
import application.exceptions.InvalidTokenException;
import application.repository.OperationRepository;
import application.repository.RecordRepository;
import application.repository.TokenRepository;
import application.services.CalculatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyCalculatorApplicationTests {

    @InjectMocks
    private CalculatorService calculatorService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private RecordRepository recordRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidTokenException.class)
    public void givenAnotherSystemWhenItSendsTokenWithLowLenghtThenShouldReturnException() {
        calculatorService.div("1.0", "2.0", "token");
        Assert.assertEquals("teste", "teste");
    }

    @Test
    public void givenAnotherSystemWhenItSendsTokenWithRightPatternThenShouldBeCalculatureNormal() {
        Token token = new Token();
        token.setToken("testeToken");
        token.setUser(new User("name", "pass"));
        Optional<Operation> operation = Optional.of(new Operation(OperationType.SUBTRACTION, 1.50));

        when(tokenRepository.findOneByToken(Mockito.anyString())).thenReturn(Optional.of(token));
        when(operationRepository.findOneByType(Mockito.any(OperationType.class))).thenReturn(operation);
        when(recordRepository.save(Mockito.any(Record.class))).thenReturn(new Record());

        Assert.assertEquals(Double.valueOf(2.0),  calculatorService.div("4.0", "2.0", "Bearer: v79vwx4aVHhrXv4U"));
        Assert.assertNotNull(token.getUser().getRecords());
    }

    @Test
    public void givenAnotherSystemWhenMathMethodIsCalledThenChargeShouldCreateRecord() {
        Token token = new Token();
        token.setToken("testeToken");
        User user = new User("name", "pass");
        token.setUser(user);
        Optional<Operation> operation = Optional.of(new Operation(OperationType.SUBTRACTION, 1.50));

        when(operationRepository.findOneByType(Mockito.any(OperationType.class))).thenReturn(operation);
        when(recordRepository.save(Mockito.any(Record.class))).thenReturn(new Record());

        Record charge = calculatorService.charge(user, OperationType.SUBTRACTION, "10");
        Assert.assertEquals( OperationType.SUBTRACTION, charge.getOperation().getType());
        Assert.assertEquals(Double.valueOf(1.50D), charge.getAmount());
        Assert.assertEquals(Double.valueOf(48.50), charge.getUserBalance());
    }

    @Test
    public void givenAnotherSystemWhenItSendsCallAdditionThenTheResultShouldBeReturned() {
        Token token = new Token();
        token.setToken("testeToken");
        token.setUser(new User("name", "pass"));
        Optional<Operation> operation = Optional.of(new Operation(OperationType.ADDITION, 1.50));

        when(tokenRepository.findOneByToken(Mockito.anyString())).thenReturn(Optional.of(token));
        when(operationRepository.findOneByType(Mockito.any(OperationType.class))).thenReturn(operation);
        when(recordRepository.save(Mockito.any(Record.class))).thenReturn(new Record());

        Assert.assertEquals(Double.valueOf(6.0),  calculatorService.sum("4.0", "2.0", "Bearer: v79vwx4aVHhrXv4U"));
    }

    @Test
    public void givenAnotherSystemWhenItSendsCallMultThenTheResultShouldBeReturned() {
        Token token = new Token();
        token.setToken("testeToken");
        token.setUser(new User("name", "pass"));
        Optional<Operation> operation = Optional.of(new Operation(OperationType.ADDITION, 1.50));

        when(tokenRepository.findOneByToken(Mockito.anyString())).thenReturn(Optional.of(token));
        when(operationRepository.findOneByType(Mockito.any(OperationType.class))).thenReturn(operation);
        when(recordRepository.save(Mockito.any(Record.class))).thenReturn(new Record());

        Assert.assertEquals(Double.valueOf(8.0),  calculatorService.mult("4.0", "2.0", "Bearer: v79vwx4aVHhrXv4U"));
    }

    @Test
    public void givenAnotherSystemWhenItSendsCallDivThenTheResultShouldBeReturned() {
        Token token = new Token();
        token.setToken("testeToken");
        token.setUser(new User("name", "pass"));
        Optional<Operation> operation = Optional.of(new Operation(OperationType.ADDITION, 1.50));

        when(tokenRepository.findOneByToken(Mockito.anyString())).thenReturn(Optional.of(token));
        when(operationRepository.findOneByType(Mockito.any(OperationType.class))).thenReturn(operation);
        when(recordRepository.save(Mockito.any(Record.class))).thenReturn(new Record());

        Assert.assertEquals(Double.valueOf(5.0),  calculatorService.div("10.0", "2.0", "Bearer: v79vwx4aVHhrXv4U"));
    }

    @Test
    public void givenAnotherSystemWhenItSendsCallSquareRootThenTheResultShouldBeReturned() {
        Token token = new Token();
        token.setToken("testeToken");
        token.setUser(new User("name", "pass"));
        Optional<Operation> operation = Optional.of(new Operation(OperationType.ADDITION, 1.50));

        when(tokenRepository.findOneByToken(Mockito.anyString())).thenReturn(Optional.of(token));
        when(operationRepository.findOneByType(Mockito.any(OperationType.class))).thenReturn(operation);
        when(recordRepository.save(Mockito.any(Record.class))).thenReturn(new Record());

        Assert.assertEquals(Double.valueOf(7.0),  calculatorService.squareRoot("49.0", "Bearer: v79vwx4aVHhrXv4U"));
    }

}
