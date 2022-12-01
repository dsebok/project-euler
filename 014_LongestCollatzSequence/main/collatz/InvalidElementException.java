package main.collatz;

public class InvalidElementException extends RuntimeException {

	public InvalidElementException(String message) {
		super(message);
	}
}