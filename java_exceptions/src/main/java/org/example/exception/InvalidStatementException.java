package org.example.exception;

import java.io.IOException;

public class InvalidStatementException extends Exception {
    public InvalidStatementException(String message) {
        super(message);
    }

    public InvalidStatementException(String message, Exception exception) {
        super(message, exception);
    }
}
