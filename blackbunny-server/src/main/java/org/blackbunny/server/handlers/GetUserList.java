package org.blackbunny.server.handlers;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/16/12
 */

import org.blackbunny.core.NetMessage;
import org.blackbunny.core.NetMessageHandlerBind;
import org.blackbunny.core.Session;
import org.blackbunny.server.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@NetMessageHandlerBind( id=200 )
public class GetUserList extends SampleHandler
{
    public static final Logger logger = LoggerFactory.getLogger(GetUserList.class);

    public boolean execute( NetMessage message ) {

        logger.info("GetUserList : execute");

        Session session = message.getSession();

        List<User> userList = sampleDAO.getUserList();

        NetMessage response = new NetMessage();

        response.setId( 201 );
        response.putInt( userList.size() );

        for( User user : userList ) {
            response.putString( user.getId() );
            response.putString( user.getNickname() );
        }

        session.write( response );

        return true;
    }
}
