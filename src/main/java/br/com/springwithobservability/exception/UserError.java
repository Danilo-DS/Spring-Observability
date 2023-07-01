package br.com.springwithobservability.exception;

public class UserError extends RuntimeException{

	private static final long serialVersionUID = -3761966884315176406L;

	public UserError(String msg) {
		super(msg);
	}
	
}
