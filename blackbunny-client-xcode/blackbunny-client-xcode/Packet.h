//
//  Packet.h
//  test4
//
//  Created by SeongWoo Kim on 6/22/12.
//  Copyright (c) 2012 none. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef struct {
    uint8_t buf[1024];
    int length;
} NetBuf;

@interface Packet : NSObject {
    int point;
}


@property( readonly, assign ) int point;
@property( readonly, assign ) NetBuf netbuf;

- (uint8_t*) getbuf;

- (short)getCommand;
- (short)readShort;
- (int)readInt;
- (float)readFloat;
- (double)readDouble;
- (NSString*)readString;

- (void)setCommand : (short) command;
- (void)writeInt : (int) param;
- (void)writeShort : (short) param;
- (void)writeFloat : (float) param;
- (void)writeDouble : (double) param;
- (void)writeString : (NSString*) param;


@end
