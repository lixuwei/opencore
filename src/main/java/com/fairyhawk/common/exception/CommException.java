package com.fairyhawk.common.exception;

public class CommException extends RuntimeException{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -6640748383833519734L;
	public static String ERROR_CODE_KEY = "errCode";//错误信息存储key值
    public static String ERROR_RAND_CODE = "err.randCode";//验证码错误

    public CommException() {
        super();
    }

    public CommException(String message) {
        super(message);
    }

    public CommException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommException(Throwable cause) {
        super(cause);
    }
}
