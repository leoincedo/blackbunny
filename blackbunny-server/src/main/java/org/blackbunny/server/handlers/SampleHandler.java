package org.blackbunny.server.handlers;

import org.blackbunny.core.NetMessage;
import org.blackbunny.core.NetMessageHandler;
import org.blackbunny.script.JavaScript;
import org.blackbunny.server.data.SampleDAO;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/16/12
 */


public class SampleHandler implements NetMessageHandler
{
    protected SampleDAO sampleDAO;
    protected JavaScript javaScript;

    public void setSampleDAO(SampleDAO sampleDAO) {
        this.sampleDAO = sampleDAO;
    }

    public void setJavaScript(JavaScript javaScript) {
        this.javaScript = javaScript;
    }

    @Override
    public boolean execute(NetMessage message) {
        return false;
    }
}
