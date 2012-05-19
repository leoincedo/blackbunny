package org.blackbunny.core;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/14/12
 */
public class NetMessage
{
    ChannelBuffer buffer = null;
    Session session = null;
    int len = 0;

    public NetMessage()
    {
        buffer = ChannelBuffers.buffer( 1024 );
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setBuffer( ChannelBuffer buf )
    {
        len = buf.readableBytes();
        this.buffer = buf;
    }

    public ChannelBuffer getBuffer() {
        return buffer;
    }

    public int getId()
    {
        if( len == buffer.readableBytes() ) return buffer.readShort();
        return buffer.getShort( 0 );
    }

    public void setId( int id )
    {
        buffer.writeShort(id);
    }

    public void putByte( byte value ) { buffer.writeByte(value); }
    public void putInt( int value ) { buffer.writeInt(value); }
    public void putLong( long value ) { buffer.writeLong(value); }
    public void putFloat( float value ) { buffer.writeFloat(value); }
    public void putDouble( int value ) { buffer.writeDouble(value); }
    public void putString( String value ) {
        byte[] bytes = value.getBytes();
        buffer.writeShort(bytes.length);
        buffer.writeBytes(bytes);
    }

    public byte readByte() { return buffer.readByte(); }
    public int readInt() { return buffer.readInt(); }
    public Long readLong() { return buffer.readLong(); }
    public Float readFloat() { return buffer.readFloat(); }
    public Double readDouble() { return buffer.readDouble(); }
    public String readString() {
        short size = buffer.readShort();
        byte[] bytes = new byte[ size ];
        buffer.readBytes(bytes);
        return new String( bytes );

    }

}
