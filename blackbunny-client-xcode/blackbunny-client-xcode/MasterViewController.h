//
//  MasterViewController.h
//  blackbunny-client-xcode
//
//  Created by SeongWoo Kim on 6/25/12.
//  Copyright (c) 2012 none. All rights reserved.
//

#import <UIKit/UIKit.h>

@class DetailViewController;

#import <CoreData/CoreData.h>

@interface MasterViewController : UITableViewController <NSFetchedResultsControllerDelegate>

@property (strong, nonatomic) DetailViewController *detailViewController;

@property (strong, nonatomic) NSFetchedResultsController *fetchedResultsController;
@property (strong, nonatomic) NSManagedObjectContext *managedObjectContext;

- (void)insertUser:(NSString*)id:(NSString*)nick;

@end
