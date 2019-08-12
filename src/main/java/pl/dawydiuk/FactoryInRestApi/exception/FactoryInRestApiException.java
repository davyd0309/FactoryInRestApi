package pl.dawydiuk.FactoryInRestApi.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FactoryInRestApiException extends Exception {

    private String code;
    private String message;
    private String additionalInformation;

}
