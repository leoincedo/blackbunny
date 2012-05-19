package org.blackbunny.core;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public interface NetHandler {

    public void onConnected( Session session );
    public void onClosed( Session session );
    public void messageReceived( NetMessage message );
    public void exceptionCaught( Session session, Throwable cause );

}
