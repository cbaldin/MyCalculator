package application;

import application.entities.Operation;
import application.entities.OperationType;
import application.entities.Record;
import application.entities.User;
import application.repository.OperationRepository;
import application.repository.RecordRepository;
import application.repository.TokenRepository;
import application.repository.UserRepository;
import application.services.auth.TokenFactory;
import application.services.operations.RecordFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootApplication
public class Main {

    public static final String MARIO_USERNAME = "test@test.com";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private RecordFactory recordFactory;

    @Autowired
    private TokenFactory tokenFactory;

    public static void main(String[] args) {
        System.out.println("Initializing");
        SpringApplication.run(Main.class, args);

        System.out.println("Initialization finished");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setupDefaultData() {
        createDefaultData();
    }

    private void createDefaultData() {
        final User user = new User(MARIO_USERNAME,"123");
        userRepository.save(user);

        userRepository.save(new User("john@test.com","123"));


        final Operation addition = new Operation(OperationType.ADDITION, 1.00);
        operationRepository.save(addition);

        final Operation subtraction = new Operation(OperationType.SUBTRACTION, 1.50);
        operationRepository.save(subtraction);

        final Operation division = new Operation(OperationType.DIVISION, 2.00);
        operationRepository.save(division);

        final Operation multiplication = new Operation(OperationType.MULTIPLICATION, 3.00);
        operationRepository.save(multiplication);

        final Operation randomString = new Operation(OperationType.RANDOM_STRING, 10.00);
        operationRepository.save(randomString);

        final Operation squareRoot = new Operation(OperationType.SQUARE_ROOT, 5.00);
        operationRepository.save(squareRoot);

        final List operations = List.of(addition, subtraction, division, multiplication, randomString, squareRoot);

        IntStream.rangeClosed(1, 10).forEach(value -> {
            Record record = recordFactory.createRecord(user, 20.0D + value, (Operation) operations.get(new Random().nextInt(operations.size())), "10");
        });

        tokenFactory.addTokens("tokenTeste", user);
    }

}

