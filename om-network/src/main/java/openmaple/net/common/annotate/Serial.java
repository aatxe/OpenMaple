package openmaple.net.common.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A field annotation that marks the order for the fields to be serialized in.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/10/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Serial {
	public int value();
}
