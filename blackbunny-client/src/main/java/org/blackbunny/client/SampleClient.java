package org.blackbunny.client;

import org.blackbunny.protocol.DefaultMessageDecoder;
import org.blackbunny.protocol.DefaultMessageEncoder;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/16/12
 */
public class SampleClient
{
    String id;
    String host;
    int port;


    public SampleClient( String host, int port, String id )
    {
        this.host = host;
        this.port = port;
        this.id = id;
    }

    public void run()
    {
        // Configure the client.
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Set up the pipeline factory.
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(
                        new LengthFieldBasedFrameDecoder( 8192, 0, 2, -2, 2 ),
                        new DefaultMessageDecoder(),
                        new LengthFieldPrepender( 2, true ),
                        new DefaultMessageEncoder(),
                        new SampleClientHandler( id ));
            }
        });

        // Start the connection attempt.
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));

        // Wait until the connection is closed or the connection attempt fails.
        future.getChannel().getCloseFuture().awaitUninterruptibly();

        // Shut down thread pools to exit.
        bootstrap.releaseExternalResources();
    }


    public static void main( String[] args ) throws Exception {
        // Print usage if no argument is specified.
        if (args.length < 3 || args.length > 3) {
            System.err.println(
                    "Usage: " + SampleClient.class.getSimpleName() +
                            " <host> <port> [id]");
            return;
        }

        // Parse options.
        final String host = args[0];
        final int port = Integer.parseInt(args[1]);
        final String id = args[2];

        try {
            new SampleClient( host, port, id ).run();
        } catch( Exception ex ) {
            ex.printStackTrace();
        }

        Thread.sleep( 10000 );
    }
}
