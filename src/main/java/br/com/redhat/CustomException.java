package br.com.redhat;

public class CustomException extends Exception {

	private static final long serialVersionUID = -6997729525968943762L;

	public CustomException(String message) {
		super(message);
	}
}
