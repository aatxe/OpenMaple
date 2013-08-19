package openmaple.crypto.legacy;

/**
 * A runtime exception thrown for erroneous behavior throughout the legacy protocol's cryptography code.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 8/19/13
 */
public class LegacyCryptographyException extends RuntimeException {
	/**
	 * {@inheritDoc}
	 */
	public LegacyCryptographyException(String message) {
		super(message);
	}

	/**
	 * {@inheritDoc}
	 */
	public LegacyCryptographyException(String message, Throwable cause) {
		super(message, cause);
	}
}
