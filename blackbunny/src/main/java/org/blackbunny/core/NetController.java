package org.blackbunny.core;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public interface NetController {
    public boolean bind( int port );
    public boolean bind( String ip, int port );
}
