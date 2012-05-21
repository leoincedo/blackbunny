package org.blackbunny.server;

import org.blackbunny.core.*;
import org.blackbunny.inject.Injector;
import org.blackbunny.script.JavaScript;
import org.blackbunny.server.data.SampleDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public class SampleServer implements NetHandler
{
    public static final Logger logger = LoggerFactory.getLogger( SampleServer.class );

    NetMessageDistributor distributor;
    NetController netController;

    public void onConnected(Session session) {
        logger.info( "onConnected : {}", session );
    }

    public void onClosed(Session session) {
        logger.info( "onClosed : {}", session );
    }

    public void messageReceived(NetMessage message) {
        logger.info( "onMessage : {}", message.getSession() );
        distributor.execute( message );
    }

    public void exceptionCaught(Session session, Throwable cause) {
        logger.info( "exceptionCaught : {}", session, cause );
    }

    public void start() throws Exception
    {
        logger.info( "starting.." );

        //! Dependency Injection
        Injector.createComponent( JavaScript.class );
        Injector.createComponent( SampleDAO.class ).initDB();

        distributor = new NetMessageDistributor();
        //! Registration handlers
        distributor.scanPackages( "org.blackbunny.server.handlers" );

        netController = new SimpleNetController( this );
        netController.bind( 27932 );

        Thread.sleep( Integer.MAX_VALUE );

    }

    public static void main( String[] args ) throws Exception {

        SampleServer server = new SampleServer();
        server.start();

    }

}
