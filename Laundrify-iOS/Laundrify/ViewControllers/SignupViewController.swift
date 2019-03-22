//
//  SignupViewController.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/17/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit
import Parse

class SignupViewController: UIViewController {

    @IBOutlet weak var emailField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var confirmField: UITextField!
    
    @IBAction func signupButton(_ sender: Any) {
        let user = PFUser()
        user.username = emailField.text
        user.password = passwordField.text
        
        user.signUpInBackground { (success, error) in
            if success {
                print("Successfully signed up")
                self.performSegue(withIdentifier: "signupToMain", sender: nil)
                //self.dismiss(animated: true, completion: nil)
            } else {
                print("Failed to sign up")
            }
        }
    }
    
    @IBAction func backToLoginButton(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.self.view.addGestureRecognizer(UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:))))
        
        
        
        
        var tapGesture = UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:)))
        
        self.view.addGestureRecognizer(tapGesture)
        var swipeDown = UISwipeGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:)))
        
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
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
