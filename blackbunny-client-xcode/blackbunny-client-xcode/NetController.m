//
//  NetController.m
//  test4
//
//  Created by SeongWoo Kim on 6/22/12.
//  Copyright (c) 2012 none. All rights reserved.
//

#import "NetController.h"
#import "Packet.h"

@implementation NetController

@synthesize netbuf;
@synthesize delegate;

- (Packet*)createPacket
{
    return[[Packet alloc]init];
}

- (BOOL)open:(NSString*)hostname hostport:(short)port;
{
    
    CFReadStreamRef     readStream;
    CFWriteStreamRef    writeStream;
    
    CFStreamCreatePairWithSocketToHost(
                                       NULL,
                                       (__bridge CFStringRef) hostname,
                                       port,
                                       &readStream,
                                       &writeStream 
                                       );
    
    iStream = (__bridge NSInputStream *)readStream;
    oStream = (__bridge NSOutputStream *)writeStream;
    
    [iStream setDelegate:self];
    [oStream setDelegate:self];
    
    [iStream scheduleInRunLoop:[NSRunLoop currentRunLoop] forMode:NSDefaultRunLoopMode];
    [oStream scheduleInRunLoop:[NSRunLoop currentRunLoop] forMode:NSDefaultRunLoopMode];
    
    [iStream open];
    [oStream open];
    
    netbuf.length = 0;
    
    NSLog(@"init");
    
    return TRUE;
}

- (void)send:(Packet*) packet
{
    short length = [packet point] + 2;
    memcpy( [packet getbuf], &length, sizeof( short ) );
    [oStream write:[packet getbuf] maxLength:length]; 
}

//------------------------------ *.m 이벤트 함수 부분 --------------------------------------------
- (void)stream:(NSStream *)theStream handleEvent:(NSStreamEvent)streamEvent
{
    switch (streamEvent) {
        case NSStreamEventHasSpaceAvailable:
        {
            NSLog(@"stream:NSStreamEventHasSpaceAvailable");
            break;
        }
        case NSStreamEventHasBytesAvailable:
            NSLog(@"stream:NSStreamEventHasBytesAvailable");
            if (theStream == iStream)
            {
                
                while ([iStream hasBytesAvailable])
                {
                    int len = [iStream read:netbuf.buf + netbuf.length maxLength:8192];
                    netbuf.length += len;
                    
                    uint8_t* cptr = netbuf.buf;
                    int length = netbuf.length;
                    
                    while( length > sizeof( short) ) {
                        short packet_size = 0;
                        
                        memcpy( &packet_size, cptr, sizeof( short ) );
                        
                        
                        if( length >= packet_size ) {
                            Packet * packet = [[Packet alloc] init];
                            memcpy( [packet getbuf], netbuf.buf, packet_size );
                            length -= packet_size;
                            cptr += packet_size;
                            
                            if ([delegate respondsToSelector:@selector(onReceived:receviedPacket:)])
                                [delegate onReceived:self receviedPacket:packet];
                            
                            
                        } else {
                            memcpy( netbuf.buf, cptr, netbuf.length - length );
                            netbuf.length -= length;
                            
                            break;
                        }
                        
                    }
                }
                
            }
            
            break;
        default:
            NSLog(@"stream:default");
            break;
    }
}

@end
