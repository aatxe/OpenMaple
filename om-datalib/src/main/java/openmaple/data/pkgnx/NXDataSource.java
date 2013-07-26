package openmaple.data.pkgnx;

import openmaple.data.DataEntry;
import openmaple.data.DataSource;
import openmaple.data.InvalidDataSourceException;
import us.aaronweiss.pkgnx.NXFile;
import us.aaronweiss.pkgnx.format.NXNode;

import java.io.IOException;

/**
 * A {@code DataSource} for an {@code NXFile}.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 7/26/13
 */
public class NXDataSource extends DataSource {
	private final NXFile source;
	private DataEntry root;

	/**
	 * Creates an {@code NXFile} data source.
	 *
	 * @param filePath the path to the file
	 * @throws InvalidDataSourceException if the file fails to load
	 */
	public NXDataSource(String filePath) {
		try {
			source = new NXFile(filePath);
		} catch (IOException e) {
			throw new InvalidDataSourceException("Failed to load file: " + filePath, e);
		}
	}

	/**
	 * Creates an {@code NXFile} data source.
	 *
	 * @param filePath         the path to the file
	 * @param parseImmediately whether or not to parse it immediately
	 * @throws InvalidDataSourceException if the file fails to load
	 */
	public NXDataSource(String filePath, boolean parseImmediately) {
		try {
			source = new NXFile(filePath, parseImmediately);
		} catch (IOException e) {
			throw new InvalidDataSourceException("Failed to load file: " + filePath, e);
		}
	}

	@Override
	public String getName() {
		return source.getFilePath();
	}

	@Override
	public DataEntry getRoot() {
		if (root == null) {
			root = new NXDataEntry(source.getRoot(), "/");
		}
		return root;
	}

	@Override
	protected void internalPopulateEntryMap() {
		recursePopulate(source.getRoot(), "");
	}

	/**
	 * Adds the specific node to the entry map.
	 *
	 * @param node      the node to add
	 * @param pathAccum the path above this node
	 */
	private void recursePopulate(NXNode node, String pathAccum) {
		pathAccum += "/" + node.getName();
		dataEntries.put(pathAccum, new NXDataEntry(node, pathAccum));
		for (NXNode child : node) {
			recursePopulate(child, pathAccum);
		}
	}

	@Override
	protected DataEntry internalLookUp(String path) {
		String pathProper = (path.startsWith("//")) ? path : (path.startsWith("/")) ? "/" + path : "//" + path;
		return new NXDataEntry(source.resolve(path), pathProper);
	}
}
