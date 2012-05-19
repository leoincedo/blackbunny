package org.blackbunny.protocol;

import org.blackbunny.core.NetMessage;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public class DefaultMessageEncoder extends OneToOneEncoder {

    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object o) throws Exception {
        return ((NetMessage)o).getBuffer();
    }
}
