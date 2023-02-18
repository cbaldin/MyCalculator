package application.services;


import org.springframework.stereotype.Service;
@Service
public class CalculatorService {

    public Double sum(String x, String y) {
        return  Double.parseDouble(x) * Double.parseDouble(y);
    }

}
