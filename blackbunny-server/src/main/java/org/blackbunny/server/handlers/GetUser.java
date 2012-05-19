package org.blackbunny.server.handlers;

import org.blackbunny.core.NetMessageHandlerBind;
import org.blackbunny.core.NetMessage;
import org.blackbunny.core.Session;
import org.blackbunny.server.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
@NetMessageHandlerBind( id=100 )
public class GetUser extends SampleHandler
{

    public static final Logger logger = LoggerFactory.getLogger(GetUser.class);

    public boolean execute( NetMessage message ) {

        Session session = message.getSession();

        logger.info("GetUser : execute");

        String id = message.readString();

        User user = sampleDAO.getUser(id);

        Map< String, Object > properties = new HashMap< String, Object >();

        properties.put("sampleDAO", sampleDAO );

        try {
            String ret = (String)javaScript.functionCall( "./sample.js", properties, "test", new Object[] { user } );

            logger.info("script ret = " + ret );

        } catch( Exception ex ) {
            logger.error( "script call", ex );
        }

        NetMessage response = new NetMessage();
        response.setId( 101 );
        response.putString( user.getId() );
        response.putString( user.getNickname() );

        session.write( response );

        return true;
    }
}
