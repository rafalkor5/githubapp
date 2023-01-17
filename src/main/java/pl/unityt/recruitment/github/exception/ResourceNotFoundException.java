package pl.unityt.recruitment.github.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {
    public ResourceNotFoundException(final String resource) {
        super(HttpStatus.NOT_FOUND, formatReason(resource));
    }

    private static String formatReason(String resource) {
        return String.format("Resource '%s' not found", resource);
    }

}
