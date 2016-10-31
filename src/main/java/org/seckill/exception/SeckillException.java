package org.seckill.exception;

public class SeckillException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SeckillException() {
	}

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(Throwable cause) {
		super(cause);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
