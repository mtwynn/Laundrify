//
//  ViewController.swift
//  Laundrify
//
//  Created by Tam Nguyen on 1/10/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var usernameField: UITextField!
    
    @IBOutlet weak var passwordField: UITextField!
    
    
    @IBAction func onTap(_ sender: AnyObject) {
        view.endEditing(true)
    }
    
    @IBAction func loginButton(_ sender: Any) {
        print("Username: " + usernameField.text!)
        print("Password: " + passwordField.text!)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }


}

