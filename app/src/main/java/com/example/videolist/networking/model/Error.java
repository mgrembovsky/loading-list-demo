package com.example.videolist.networking.model;

public class Error {

    public Integer code;
    public String message;

    public Error(int code, String strMessage)
    {
        this.code = code;
        this.message = strMessage;
    }
}
