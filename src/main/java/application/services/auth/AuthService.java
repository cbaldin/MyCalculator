package application.services.auth;

import application.controller.UserLogin;
import application.controller.UserLoginResponse;
import application.entities.User;
import application.exceptions.PasswordNotMatch;
import application.exceptions.UserNotFoundException;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AuthService {

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    @Autowired
    private TokenFactory tokenFactory;

    @Autowired
    private UserRepository userRepository;

    public UserLoginResponse registryToken(UserLogin userLogin) {
        final User user = userRepository.findOneByUsername(userLogin.getUsername()).orElseThrow(UserNotFoundException::new);
        if (!user.getPassword().equals(userLogin.getPassword())) {
            throw new PasswordNotMatch();
        }
        return new UserLoginResponse(tokenFactory.addTokens(generateNewToken(), user));
    }

    public String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
