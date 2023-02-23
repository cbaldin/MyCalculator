package application.services.auth;

import application.entities.Token;
import application.entities.User;
import application.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TokenFactory {

    @Autowired
    private TokenRepository tokenRepository;

    public Token addTokens(String stringToken, User user) {
        Token token = new Token();
        token.setToken(stringToken);
        token.setDate(LocalDate.now());
        token.setUser(user);
        tokenRepository.save(token);

        user.getTokens().add(token);
        return token;
    }

}
