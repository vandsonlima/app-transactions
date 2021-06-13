package com.example.apptransactions.commons.exception;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 **/
public class FieldErrorOutput {
    private String field;
    private String message;

    public FieldErrorOutput(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
