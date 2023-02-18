package application.services;

import application.entities.Operation;
import application.entities.Record;
import application.entities.User;
import application.repository.RecordRepository;
import application.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sum")
    public String sum(@RequestParam(value = "x") String x,
                      @RequestParam(value = "y", defaultValue = "0") String y) {
        User user = new User();
        user.setPassword("teste");
        user.setStatus("teste");
        user.setStatus("statys teste");

        userRepository.save(user);

        return "Result of the sum:" + calculatorService.sum(x, y);
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

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
