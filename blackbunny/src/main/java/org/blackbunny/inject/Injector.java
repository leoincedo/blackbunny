package org.blackbunny.inject;

import com.google.common.base.Predicates;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.reflections.ReflectionUtils.withModifier;
import static org.reflections.ReflectionUtils.withPrefix;
import static org.reflections.ReflectionUtils.withParameters;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/18/12
 */
public class Injector {

    static Map< Class<?>, Object > objs = new HashMap< Class<?>, Object >();

    static public <T> T createComponent( Class<T> cls ) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        T obj = cls.newInstance();

        for( Class<?> c : objs.keySet() ) {

            Set<Method> setters =
                    Reflections.getAllMethods( c,
                            Predicates.and(withModifier(Modifier.PUBLIC), withPrefix("set"), withParameters( cls ) ) );

            for( Method m : setters ) {

                Object o = objs.get( c );
                m.invoke( o, new Object[] { obj });
            }

        }


        for( Class<?> c : objs.keySet() ) {

            Set<Method> setters =
                    Reflections.getAllMethods( cls,
                            Predicates.and(withModifier(Modifier.PUBLIC), withPrefix("set"), withParameters( c ) ) );

            for( Method m : setters ) {

                Object o = objs.get( c );
                m.invoke( obj, new Object[] { o });
            }
        }

        objs.put(cls, obj);

        return obj;

    }

    static public void release()
    {
        objs.clear();
    }

}
