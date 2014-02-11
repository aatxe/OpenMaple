package openmaple.net.common.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that denotes a parameter as being unsigned.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/10/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Unsigned {
}
