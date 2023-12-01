package com.demos.Controller.ex;

public class FileTypexception extends FileUploadException{
    public FileTypexception() {
        super();
    }

    public FileTypexception(String message) {
        super(message);
    }

    public FileTypexception(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypexception(Throwable cause) {
        super(cause);
    }

    protected FileTypexception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
