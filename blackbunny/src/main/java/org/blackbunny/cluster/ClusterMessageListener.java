package org.blackbunny.cluster;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/11/12
 */
public interface ClusterMessageListener extends MessageListener< ClusterMessage > {

    public void onMessage( Message< ClusterMessage > message );

}
