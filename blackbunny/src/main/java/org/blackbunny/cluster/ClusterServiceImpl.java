package org.blackbunny.cluster;

import com.hazelcast.config.*;
import com.hazelcast.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/11/12
 */
public class ClusterServiceImpl implements ClusterService
{

    public static final Logger logger = LoggerFactory.getLogger( ClusterServiceImpl.class );

    private static String GROUPNAME = "bb.groups";

    HazelcastInstance hzInstance;
    ITopic< ClusterMessage > topic;
    List< String > clusters;

    public ClusterServiceImpl( List< String > clusters )
    {
        this.clusters = clusters;
    }

    public void start()
    {

        Config config = new Config();
        NetworkConfig networkConfig = config.getNetworkConfig();
        Join join = networkConfig.getJoin();
        MulticastConfig multicastConfig = join.getMulticastConfig();
        multicastConfig.setEnabled(false);

        TcpIpConfig tcpIpConfig = join.getTcpIpConfig();
        tcpIpConfig.setEnabled(true);

        tcpIpConfig.setMembers( clusters );
        Interfaces interfaces = config.getNetworkConfig().getInterfaces();
        interfaces.setInterfaces( clusters );

        hzInstance = Hazelcast.newHazelcastInstance( config );


        MembershipListener membershipListener = new MembershipListener () {

            public void memberAdded(MembershipEvent membershipEvent) {
                logger.info("Cluster memberAdded : {}", membershipEvent.getMember().getInetSocketAddress().toString() );
            }

            public void memberRemoved(MembershipEvent membershipEvent) {

                logger.info("Cluster memberRemoved : {}", membershipEvent.getMember().getInetSocketAddress().toString() );
            }
        };

        hzInstance.getCluster().addMembershipListener( membershipListener );

        topic = hzInstance.getTopic( GROUPNAME );

    }


    public void stop()
    {
        topic.destroy();
        hzInstance.getLifecycleService().shutdown();
    }



    public void addClusterMessageListener( ClusterMessageListener listener )
    {
        topic.addMessageListener( listener );
    }

    public void removeClusterMessageListener( ClusterMessageListener listener )
    {
        topic.removeMessageListener( listener );
    }

}
