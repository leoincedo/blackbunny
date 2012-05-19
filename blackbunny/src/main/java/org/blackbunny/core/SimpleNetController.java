package org.blackbunny.core;

import org.blackbunny.protocol.DefaultMessageDecoder;
import org.blackbunny.protocol.DefaultMessageEncoder;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.ServerSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public class SimpleNetController implements NetController {

    public static final Logger logger = LoggerFactory.getLogger(SimpleNetController.class);

    final NetHandler netHandler;

    public SimpleNetController( NetHandler handler )
    {
        this.netHandler = handler;
    }

    public boolean bind( int port )
    {
       return bind( new InetSocketAddress( port ) );
    }

    public boolean  bind( String ip, int port )
    {
        return bind( new InetSocketAddress( ip, port ) );

    }

    public boolean bind( InetSocketAddress inetSocketAddress ) {


        ServerSocketChannelFactory factory = new NioServerSocketChannelFactory(
                Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() + 1 ),
                Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() + 1 ) );

        ServerBootstrap bootstrap = new ServerBootstrap( factory );

        class BlackBunnyChannelHandler extends SimpleChannelHandler
        {
            NetHandler netHandler;

            public BlackBunnyChannelHandler(NetHandler handler) {
                this.netHandler = handler;
            }

            @Override
            public void channelConnected( ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
            {
                Session session = new Session( ctx.getChannel() );
                netHandler.onConnected( session );
                ctx.setAttachment( session );
            }
            @Override
            public void channelClosed( ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
                netHandler.onClosed( (Session)ctx.getAttachment() );
            }

            @Override
            public void messageReceived(ChannelHandlerContext ctx, MessageEvent e ) throws Exception {
                NetMessage message = (NetMessage)e.getMessage();
                message.setSession( (Session)ctx.getAttachment() );
                netHandler.messageReceived( message );
            }

            @Override
            public void exceptionCaught( ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
                netHandler.exceptionCaught( (Session)ctx.getAttachment(), e.getCause() );
            }

        }

        bootstrap.setPipelineFactory( new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline( new LengthFieldBasedFrameDecoder( 8192, 0, 2, -2, 2 ),
                        new DefaultMessageDecoder(),
                        new LengthFieldPrepender( 2, true ),
                        new DefaultMessageEncoder(),
                        new BlackBunnyChannelHandler( netHandler )
                );
            }
        });

        bootstrap.setOption( "child.bufferFactory", new HeapChannelBufferFactory() );
        bootstrap.setOption( "child.tcpNoDelay", true );
        bootstrap.setOption( "child.keepAlive", true );

        bootstrap.bind( inetSocketAddress );

        logger.info("bind : {}", inetSocketAddress );

        return true;

    }

}
