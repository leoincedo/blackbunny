//
//  DetailViewController.h
//  test3
//
//  Created by SeongWoo Kim on 6/4/12.
//  Copyright (c) 2012 none. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController <UISplitViewControllerDelegate>

@property (strong, nonatomic) id detailItem;

@property (strong, nonatomic) IBOutlet UILabel *detailDescriptionLabel;

@end
