package openmaple.net.common.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for the OpenMaple serialization and serial layers.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/10/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface Opcode {
	public int value();
}
