package openmaple.net.common.utils;

import io.netty.util.AttributeKey;
import openmaple.crypto.CryptoKit;

/**
 * A set of constants and static utilities functions for networking.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 1/26/14
 */
public class NetworkUtils {
	public static final AttributeKey<CryptoKit> CRYPTO_KIT_ATTRIBUTE_KEY = AttributeKey.valueOf(CryptoKit.class.getName());
}
