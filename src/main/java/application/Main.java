package application;

import application.entities.Operation;
import application.entities.OperationType;
import application.entities.User;
import application.repository.OperationRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Main {

    public static final String MARIO_USERNAME = "mario@teste.com";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationRepository operationRepository;

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
        final User user = new User(MARIO_USERNAME,"Mr. Test");
        userRepository.save(user);

        userRepository.save(new User("john@teste.com","Mr. Test"));

        Operation addition = new Operation(OperationType.ADDITION, 1.00);
        operationRepository.save(addition);

        Operation subtraction = new Operation(OperationType.SUBTRACTION, 1.50);
        operationRepository.save(subtraction);

        Operation division = new Operation(OperationType.DIVISION, 2.00);
        operationRepository.save(division);

        Operation multiplication = new Operation(OperationType.MULTIPLICATION, 3.00);
        operationRepository.save(multiplication);

        Operation randomString = new Operation(OperationType.RANDOM_STRING, 10.00);
        operationRepository.save(randomString);

        Operation squareRoot = new Operation(OperationType.SQUARE_ROOT, 5.00);
        operationRepository.save(squareRoot);
    }

}

