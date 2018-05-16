package assignment2.exceptions;

/**
 * Exception for delivery-related issues
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
@SuppressWarnings("serial")
public class DeliveryException extends Exception {
	public DeliveryException(String message) {
		super(message);
	}
}