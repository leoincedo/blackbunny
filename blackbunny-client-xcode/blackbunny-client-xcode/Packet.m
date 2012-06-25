//
//  Packet.m
//  test4
//
//  Created by SeongWoo Kim on 6/22/12.
//  Copyright (c) 2012 none. All rights reserved.
//

#import "Packet.h"

@implementation Packet
@synthesize point;
@synthesize netbuf;

- (uint8_t*)getbuf {
    return netbuf.buf;
}

- (short)getCommand {
    
    short command = 0;
    memcpy( &command, netbuf.buf + sizeof( short ) * 2, sizeof( short ) );
    point = sizeof( short ) * 2;
    
    return command;
}

- (short)readShort {
    short data = 0;
    memcpy( &data, netbuf.buf + point, sizeof( short ) );
    point += sizeof( short );
    return data;
}

- (int)readInt {
    int data = 0;
    memcpy( &data, netbuf.buf + point, sizeof( int ) );
    point += sizeof( int );
    return data;    
}

- (float)readFloat {
    float data = 0;
    memcpy( &data, netbuf.buf + point, sizeof( float ) );
    point += sizeof( float );
    return data;
}

- (double)readDouble {
    double data = 0;
    memcpy( &data, netbuf.buf + point, sizeof( double ) );
    point += sizeof( double );
    return data;    
}

- (NSString*)readString 
{
    short size = 0;
    memcpy( &size, netbuf.buf + point, sizeof( short ) );
    point += sizeof( short );
    
    NSString* string = [[NSString alloc] initWithBytes:netbuf.buf + point length:size encoding:NSUTF8StringEncoding];
    
    point += size;
    return string;
    
}

- (void)setCommand : (short) command {
    
    memcpy( netbuf.buf + sizeof( short ), &command, sizeof( short ) );
    point = sizeof( short ) * 2;
    
}
- (void)writeInt : (int) param {
    
    memcpy( netbuf.buf + point, &param, sizeof( int ) );    
    point += sizeof( int );
}
- (void)writeShort : (short) param {
    
    memcpy( netbuf.buf + point, &param, sizeof( short ) );
    point += sizeof( short );
}
- (void)writeFloat : (float) param {
    
    memcpy( netbuf.buf + point, &param, sizeof( float ) );    
    point += sizeof( float );
}
- (void)writeDouble : (double) param {
    
    memcpy( netbuf.buf + point, &param, sizeof( double ) );    
    point += sizeof( double );
}
- (void)writeString : (NSString*) param {
    
    const char* str = [param UTF8String];
    short length = strlen( str );
    memcpy( netbuf.buf + point, &length, sizeof( short ) );
    point += sizeof( short );
    memcpy( netbuf.buf + point, str, length );
    point += length;
    
}


@end
