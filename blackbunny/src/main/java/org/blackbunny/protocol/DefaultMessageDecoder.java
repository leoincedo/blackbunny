package org.blackbunny.protocol;

import org.blackbunny.core.NetMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public class DefaultMessageDecoder extends OneToOneDecoder {

    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, Object o) throws Exception {

        NetMessage msg = new NetMessage();
        msg.setBuffer( (ChannelBuffer)o );
        return msg;

    }
}
