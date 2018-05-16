package assignment2.exceptions;

/**
 * Exception for stock-related issues
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
@SuppressWarnings("serial")
public class StockException extends Exception {
	public StockException(String message) {
		super(message);
	}
}