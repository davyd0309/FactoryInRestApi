package pl.dawydiuk.FactoryInRestApi.exception;

import org.springframework.http.HttpStatus;

public class FactoryInRestApiException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Error[code='%s',message='%s']";
    private HttpStatus httpStatus;

    public FactoryInRestApiException(Error error,HttpStatus httpStatus) {
        super(createErrorMessage(error));
        this.httpStatus = httpStatus;
    }

    private static String createErrorMessage(Error error) {
        return String.format(ERROR_MESSAGE,error.getCode(),error.getMessage());
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
