package pl.dawydiuk.FactoryInRestApi.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static pl.dawydiuk.FactoryInRestApi.exception.Error.buildError;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FactoryInRestApiExceptionFactory {

    public static FactoryInRestApiException serviceNotAvailableException(String serviceName){
        String code = "1001";
        String message = "Service = [%s] is not available";
        return new FactoryInRestApiException(code,message,serviceName);
    }

}
