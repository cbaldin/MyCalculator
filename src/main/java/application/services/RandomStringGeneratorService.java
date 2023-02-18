package application.services;

import application.entities.Operation;
import application.entities.OperationType;
import application.entities.Record;
import application.entities.User;
import application.exceptions.InsufficientFundsException;
import application.repository.OperationRepository;
import application.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class RandomStringGeneratorService {

    private static final String COMPLETE_URL = "https://www.random.org/strings/?num=1&len=10&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new";

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private RecordRepository recordRepository;

    public String getRandomString(User user) throws IOException, InterruptedException {
        charge(user);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(
                        URI.create(COMPLETE_URL))
                .header("accept", "application/json")
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private void charge(User user) {
        var userBalance = 0.0;

        if (user.getRecords().isEmpty()) {
            userBalance = User.INITIAL_BALANCE;
        } else {
//            Record record = recordRepository.findFirstByUser(user, Sort.by(Sort.Direction.DESC, "amount")).get();
            Record record = user.getRecords().stream().min((o1, o2) -> o1.getUserBalance().compareTo(o2.getUserBalance())).get();
            userBalance = record.getUserBalance();
        }

        var operation = operationRepository.findOneByType(OperationType.RANDOM_STRING).get();
        if((userBalance - operation.getCost()) < 0.0) {
            throw new InsufficientFundsException();
        }

        var record = new Record();
        record.setOperation(operation);
        record.setAmount(operation.getCost());
        record.setOperationResponse(0.0); //vai preencher depois
        record.setDate(LocalDate.now());
        record.setUser(user);
        record.setUserBalance(userBalance - operation.getCost());

        recordRepository.save(record);
    }
}
