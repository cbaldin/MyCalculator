package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
public class InsufficientFundsException  extends RuntimeException {

    public InsufficientFundsException() {
        super();
    }
}
