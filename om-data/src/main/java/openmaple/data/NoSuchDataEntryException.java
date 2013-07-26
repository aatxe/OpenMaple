package openmaple.data;

/**
 * An exception that may be thrown when a data access attempt is made but the entry failed to exist.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 7/25/13
 */
public class NoSuchDataEntryException extends RuntimeException {
	private final DataSource source;
	private final String path;

	/**
	 * Constructs an exception representing a missing data entry.
	 *
	 * @param source the {@code DataSource} with the missing entry
	 * @param path   the path to the entry
	 */
	public NoSuchDataEntryException(DataSource source, String path) {
		this("No such data entry (" + path + ") exists in " + source.getName(), source, path);
	}

	/**
	 * Constructs an exception representing a missing data entry with a specific {@code message}.
	 *
	 * @param message the message
	 * @param source  the {@code DataSource} with the missing entry
	 * @param path    the path to the entry
	 */
	public NoSuchDataEntryException(String message, DataSource source, String path) {
		super(message);
		this.source = source;
		this.path = path;
	}

	/**
	 * Gets the path to the missing entry.
	 *
	 * @return the entry path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Gets the data source without the desired entry.
	 *
	 * @return the data source
	 */
	public DataSource getDataSource() {
		return source;
	}
}
