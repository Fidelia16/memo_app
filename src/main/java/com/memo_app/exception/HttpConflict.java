package com.memo_app.exception;

public class HttpConflict extends RuntimeException{
    public HttpConflict (String message){
        super(message);
    }
}
