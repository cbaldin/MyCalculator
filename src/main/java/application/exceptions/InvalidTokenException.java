package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED )
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super();
    }

}
