package application.services;

import application.entities.Operation;
import application.entities.Record;
import application.entities.User;
import application.entities.UserStatus;
import application.exceptions.UserNotFoundException;
import application.repository.RecordRepository;
import application.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static application.Main.MARIO_USERNAME;

@RestController
@RequestMapping("api/v1")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RandomStringGeneratorService randomStringGeneratorService;

    @GetMapping("/sum")
    public String sum(@RequestParam(value = "x") String x,
                      @RequestParam(value = "y", defaultValue = "0") String y) {
        return "Result of the sum:" + calculatorService.sum(x, y); //TODO encontrar usuário.
    }

    @GetMapping("/sub")
    public String sub(@RequestParam(value = "x", defaultValue = "0") String x,
                      @RequestParam(value = "y", defaultValue = "0") String y) {
        final Double result =  Double.parseDouble(x) - Double.parseDouble(y);
        return "Result of the sub:" + result;
    }

    @GetMapping("/mult")
    public String mult(@RequestParam(value = "x", defaultValue = "0") String x,
                       @RequestParam(value = "y", defaultValue = "0") String y) {
        final Double result =  Double.parseDouble(x) * Double.parseDouble(y);

        return "Result of the mult:" + result;
    }

    @GetMapping("/div")
    public String div(@RequestParam(value = "x", defaultValue = "0") String x,
                      @RequestParam(value = "y", defaultValue = "0") String y) {
        final Double result =  Double.parseDouble(x) / Double.parseDouble(y);
        return "Result of the div:" + result;
    }

    @GetMapping("/square-root")
    public String squareRoot(@RequestParam(value = "x") String x) {
        return "Result of the squareRoot:" + Math.sqrt(Double.parseDouble(x));
    }

    @GetMapping("/random-string")
    public String randomString() throws IOException, InterruptedException {
        final User firstByUsername = userRepository.findOneByUsername(MARIO_USERNAME).orElseThrow(UserNotFoundException::new);
        return "Result of the random String: " + randomStringGeneratorService.getRandomString(firstByUsername);
    }

}
