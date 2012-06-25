//
//  AppDelegate.h
//  blackbunny-client-xcode
//
//  Created by SeongWoo Kim on 6/25/12.
//  Copyright (c) 2012 none. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NetController.h"
#import "Packet.h"
#import "MasterViewController.h"

@interface AppDelegate : UIResponder <UIApplicationDelegate, NetworkControllerDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (readonly, strong, nonatomic) NSManagedObjectContext *managedObjectContext;
@property (readonly, strong, nonatomic) NSManagedObjectModel *managedObjectModel;
@property (readonly, strong, nonatomic) NSPersistentStoreCoordinator *persistentStoreCoordinator;
@property (readonly, strong, nonatomic) NetController *netController;
@property (nonatomic) MasterViewController* masterViewController;

- (void)saveContext;
- (NSURL *)applicationDocumentsDirectory;

@end
