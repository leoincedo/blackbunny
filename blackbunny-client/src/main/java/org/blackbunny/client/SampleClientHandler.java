package org.blackbunny.client;

import org.blackbunny.core.NetMessage;
import org.jboss.netty.channel.*;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/16/12
 */
public class SampleClientHandler extends SimpleChannelHandler
{

    String id;

    public SampleClientHandler( String id )
    {
        this.id = id;
    }

    @Override
    public void channelConnected( ChannelHandlerContext ctx, ChannelStateEvent e ) {

        System.out.println("Connected.");
        NetMessage msg = new NetMessage();

        msg.setId(100);
        msg.putString( id );

        ctx.getChannel().write( msg );

        msg = new NetMessage();
        msg.setId(200);

        ctx.getChannel().write( msg );

        System.out.println("sent message.");
    }

    @Override
    public void messageReceived( ChannelHandlerContext ctx, MessageEvent e ) {

        NetMessage msg = (NetMessage) e.getMessage();

        System.out.println("recevied msg.");

        if( msg.getId() == 101 ) {
            System.out.println("ID : " + msg.getString() +", NICK : " + msg.getString() );
        }

        if( msg.getId() == 201 ) {

            int size = msg.getInt();
            System.out.println("USER LIST : " + size );
            for( int i = 0; i < size; ++i ) {
                System.out.println("ID : " + msg.getString() +", NICK : " + msg.getString() );
            }
        }

    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, ExceptionEvent e ) {
        e.getChannel().close();
    }

}
