package pl.dawydiuk.FactoryInRestApi.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static pl.dawydiuk.FactoryInRestApi.exception.Error.buildError;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FactoryInRestApiExceptionFactory {

    public static FactoryInRestApiException serviceNotAvailableException(HttpStatus httpStatus) {
        String code = "1001";
        String message = "Service is not available";
        return new FactoryInRestApiException(buildError(code, message),httpStatus);
    }

    public static FactoryInRestApiException clientException(HttpStatus httpStatus) {
        String code = "1002";
        String message = "Client exception";
        return new FactoryInRestApiException(buildError(code, message),httpStatus);
    }

    public static FactoryInRestApiException unknownException(HttpStatus httpStatus) {
        String code = "1003";
        String message = "Unknown exception";
        return new FactoryInRestApiException(buildError(code, message),httpStatus);
    }


}
