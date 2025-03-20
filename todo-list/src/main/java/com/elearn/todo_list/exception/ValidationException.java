package com.elearn.todo_list.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ValidationException extends Throwable{

    private String message;
    private HttpStatus errorCode;
    private ErrorType type;


    public enum ErrorType {
        CLIENT_ERROR,
        SERVER_ERROR,
        UNKNOWN
    }
}
