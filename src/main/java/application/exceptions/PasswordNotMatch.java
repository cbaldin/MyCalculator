package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED )
public class PasswordNotMatch extends RuntimeException {

    public PasswordNotMatch() {
        super();
    }

}
