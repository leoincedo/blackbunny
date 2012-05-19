package org.blackbunny.core;

import org.jboss.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public class Session
{

    Map< String, Object > attribute = new HashMap< String, Object >();
    Channel channel;

    public Session( Channel channel ) {
        this.channel = channel;
    }

    public Object getAttribute( String prop )
    {
        return attribute.get( prop );
    }

    public Object setAttribute( String prop, Object attr )
    {
        return attribute.put( prop, attr );
    }

    public void write( NetMessage message ) {
        channel.write( message );
    }

}
