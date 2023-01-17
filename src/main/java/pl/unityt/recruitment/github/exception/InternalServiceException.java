package pl.unityt.recruitment.github.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalServiceException extends ResponseStatusException {

    public InternalServiceException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
