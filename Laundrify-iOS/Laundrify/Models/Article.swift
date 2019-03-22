//
//  Article.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/18/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit
import Foundation
import Parse

struct Article {
    let name: String
    let type: String
    let wearCount: String
    var image: UIImage
    let owner: PFUser
}
