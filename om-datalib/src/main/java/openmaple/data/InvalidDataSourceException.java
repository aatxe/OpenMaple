package openmaple.data;

/**
 * An exception to be thrown when a data source is invalid, inaccessible, or non-existent.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 7/26/13
 */
public class InvalidDataSourceException extends RuntimeException {
	/**
	 * {@inheritDoc}
	 */
	public InvalidDataSourceException(String message) {
		super(message);
	}

	/**
	 * {@inheritDoc}
	 */
	public InvalidDataSourceException(String message, Throwable cause) {
		super(message, cause);
	}
}
