//
//  BasketCell.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/23/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit
import Parse

class BasketCell: UITableViewCell {

    
    @IBOutlet weak var basketCellImage: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
