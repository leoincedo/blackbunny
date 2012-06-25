//
//  NetController.h
//  test4
//
//  Created by SeongWoo Kim on 6/22/12.
//  Copyright (c) 2012 none. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Packet.h"

@protocol NetworkControllerDelegate;

@interface NetController : NSObject< NSStreamDelegate > {
    
    NetBuf netbuf;
    
    NSInputStream * iStream;
    NSOutputStream * oStream;
    
    id<NetworkControllerDelegate> delegate;
}

@property (nonatomic) NetBuf netbuf;
@property (nonatomic) id <NetworkControllerDelegate> delegate;



- (BOOL)open:(NSString*)hostname hostport:(short)port;
- (void)stream:(NSStream *)theStream handleEvent:(NSStreamEvent)streamEvent;
- (void)send:(Packet*) packet;
- (Packet*)createPacket;
@end

@protocol NetworkControllerDelegate <NSObject>
- (void)onReceived:(NetController *)netController receviedPacket:(Packet *)packet;
@end