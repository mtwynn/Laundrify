//
//  ProfileViewController.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/24/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit
import Parse

class ProfileViewController: UIViewController {

    @IBOutlet weak var profilePicView: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        profilePicView.layer.cornerRadius = self.profilePicView.frame.size.width/2
        profilePicView.clipsToBounds = true
        profilePicView.layer.borderWidth = 4;
        
        let color : UIColor = UIColor(red: 188/255, green: 219/255, blue: 244/255, alpha: 1.0)
        profilePicView.layer.borderColor = color.cgColor
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
