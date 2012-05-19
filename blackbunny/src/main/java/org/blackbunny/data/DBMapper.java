package org.blackbunny.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/16/12
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
public @interface DBMapper {
}
