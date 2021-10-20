package cn.com.belle.bdc.openapi.common.exception;

import java.io.IOException;

public class SignMissException extends IOException {
    public SignMissException(String message){
        super(message);
    }
}
