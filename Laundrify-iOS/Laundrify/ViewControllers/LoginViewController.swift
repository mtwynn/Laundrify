//
//  ViewController.swift
//  Laundrify
//
//  Created by Tam Nguyen on 1/10/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit
import Parse

class LoginViewController: UIViewController {

    @IBOutlet weak var emailField: UITextField!
    
    @IBOutlet weak var passwordField: UITextField!
    
    @IBOutlet weak var loginButtonView: UIButton!
    
    @IBOutlet weak var invalidLabel: UILabel!
    
    @IBAction func onTap(_ sender: AnyObject) {
        view.endEditing(true)
    }
    
    @IBAction func loginButton(_ sender: Any) {
        let email = emailField.text!
        let password = passwordField.text!
        
        PFUser.logInWithUsername(inBackground: email, password: password) { (user, error) in
            if user != nil {
                self.performSegue(withIdentifier: "menuSegue", sender: nil)
            } else {
                print("No user")
                self.invalidLabel.isHidden = false
            }
        }
    }
    
    @IBAction func noAccButton(_ sender: Any) {
        print("Performing segue")
        self.performSegue(withIdentifier: "signupSegue", sender: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loginButtonView.layer.cornerRadius = 22
        invalidLabel.isHidden = true
        
        let backgroundImage = UIImageView(frame: UIScreen.main.bounds)
        backgroundImage.image = #imageLiteral(resourceName: "image-2")
        backgroundImage.contentMode =  UIView.ContentMode.scaleAspectFill
        self.view.insertSubview(backgroundImage, at: 0)
        
        let swipeDown = UISwipeGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:)))
        
        swipeDown.direction = UISwipeGestureRecognizer.Direction.down
        self.view.addGestureRecognizer(swipeDown)
        
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow), name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillHide), name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    @objc func keyboardWillShow(notification: NSNotification) {
        if let keyboardSize = (notification.userInfo?[UIResponder.keyboardFrameBeginUserInfoKey] as? NSValue)?.cgRectValue {
            if self.view.frame.origin.y == 0 {
                self.view.frame.origin.y -= (keyboardSize.height - 100)
            }
        }
    }
    
    @objc func keyboardWillHide(notification: NSNotification) {
        if self.view.frame.origin.y != 0 {
            self.view.frame.origin.y = 0
        }
    }


}

