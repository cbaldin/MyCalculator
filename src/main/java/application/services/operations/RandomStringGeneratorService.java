package application.services.operations;

import application.entities.OperationType;
import application.entities.Record;
import application.entities.Token;
import application.entities.User;
import application.exceptions.InvalidTokenException;
import application.exceptions.UserNotFoundException;
import application.repository.OperationRepository;
import application.repository.RecordRepository;
import application.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class RandomStringGeneratorService extends Chargeable {

    private static final String COMPLETE_URL = "https://www.random.org/strings/?num=1&len=10&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new";

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public String getRandomString(String token) throws IOException, InterruptedException {
        token = normalizeAuthorizationToken(token);
        final Token tokenEntity = tokenRepository.findOneByToken(token).orElseThrow(UserNotFoundException::new);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(
                        URI.create(COMPLETE_URL))
                .header("accept", "application/json")
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        Record record = charge(tokenEntity.getUser(), OperationType.RANDOM_STRING, body);

        return body;
    }

    private String normalizeAuthorizationToken(String token) { //TODO create filter to check and normalize token and remove duplicated code
        if (token.length() < 8 ) {
            throw new InvalidTokenException();
        }
        token = token.substring(7);
        return token;
    }

}
