package org.smart.exception;

/**
 * SDK 异常类
 */
public class SDKException extends Exception {
    public SDKException(String message) {
        super(message);
    }

    public SDKException(String message, Throwable cause) {
        super(message, cause);
    }
}