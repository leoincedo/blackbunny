package org.blackbunny.core;

import org.blackbunny.inject.Injector;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public class NetMessageDistributor
{
    public static final Logger logger = LoggerFactory.getLogger(NetMessageDistributor.class);

    Map< Integer, NetMessageHandler> map = new HashMap< Integer, NetMessageHandler>();


    public void scanPackages( String scanPackages )
    {
        Reflections reflections = new Reflections( scanPackages );

        Set<Class<?>> annotated =
                reflections.getTypesAnnotatedWith( NetMessageHandlerBind.class );

        for( Class<?> cls : annotated ) {

            NetMessageHandlerBind prop = cls.getAnnotation( NetMessageHandlerBind.class );

            try {

                NetMessageHandler handler = (NetMessageHandler) Injector.createComponent( cls );
                map.put( prop.id(), handler );

                logger.info( "id : {}, name : {}", prop.id(), cls.getName() );
            } catch (InstantiationException e) {
                logger.error( "InstantiationException : ", e );
            } catch (IllegalAccessException e) {
                logger.error( "IllegalAccessException : ", e );
            } catch (InvocationTargetException e) {
                logger.error( "InvocationTargetException : ", e );
            }
            ;

        }
    }

    public void put( int id, NetMessageHandler handler )
    {
        map.put( id, handler );
    }

    public NetMessageHandler get( Integer id )
    {
        return map.get( id );
    }

    public void execute( NetMessage message )
    {
        NetMessageHandler handler = get( message.getId() );
        if( handler == null ) {
            logger.warn("Not Registered Handler : {}", message.getId() );
            return;
        }
        handler.execute( message );
    }
}
