package application.services;

import application.entities.OperationType;
import application.entities.Token;
import application.entities.User;
import application.exceptions.InvalidTokenException;
import application.exceptions.UserNotFoundException;
import application.repository.TokenRepository;
import application.services.operations.Chargeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService extends Chargeable {

    @Autowired
    private TokenRepository tokenRepository;

    public Double sum(String operatorA, String operatorB, String token) {
        token = normalizeAuthorizationToken(token);
        final Token tokenEntity = tokenRepository.findOneByToken(token).orElseThrow(UserNotFoundException::new);
        charge(tokenEntity.getUser(), OperationType.ADDITION);
        return Double.parseDouble(operatorA) + Double.parseDouble(operatorB);
    }

    public Double sub(String operatorA, String operatorB, String token) {
        token = normalizeAuthorizationToken(token);
        final Token tokenEntity = tokenRepository.findOneByToken(token).orElseThrow(UserNotFoundException::new);
        charge(tokenEntity.getUser(), OperationType.SUBTRACTION);
        return Double.parseDouble(operatorA) - Double.parseDouble(operatorB);
    }

    public Double mult(String operatorA, String operatorB, String token) {
        token = normalizeAuthorizationToken(token);
        final Token tokenEntity = tokenRepository.findOneByToken(token).orElseThrow(UserNotFoundException::new);
        charge(tokenEntity.getUser(), OperationType.MULTIPLICATION);
        return Double.parseDouble(operatorA) * Double.parseDouble(operatorB);
    }

    public Double div(String operatorA, String operatorB, String token) {
        token = normalizeAuthorizationToken(token);
        final Token tokenEntity = tokenRepository.findOneByToken(token).orElseThrow(UserNotFoundException::new);
        charge(tokenEntity.getUser(), OperationType.MULTIPLICATION);
        return Double.parseDouble(operatorA) / Double.parseDouble(operatorB);
    }

    public Double squareRoot(String operator,  String token) {
        token = normalizeAuthorizationToken(token);
        final Token tokenEntity = tokenRepository.findOneByToken(token).orElseThrow(UserNotFoundException::new);
        charge(tokenEntity.getUser(), OperationType.SQUARE_ROOT);
        return Math.sqrt(Double.parseDouble(operator));
    }

    protected String normalizeAuthorizationToken(String token) { //TODO create filter to check and normalize token
        if (token.length() < 8 ) {
            throw new InvalidTokenException();
        }
        token = token.substring(7);
        return token;
    }

}
