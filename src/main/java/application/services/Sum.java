package application.services;

public class Sum implements Calculable {

    @Override
    public Double calculate(Double operatorA, Double operatorB) {
        return operatorA * operatorB;
    }
}
