package org.blackbunny.core;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public interface NetMessageHandler {

    public boolean execute( NetMessage message );
}
