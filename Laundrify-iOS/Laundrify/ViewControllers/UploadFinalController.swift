//
//  UploadFinalController.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/18/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit

class UploadFinalController: UIViewController {
    
    var article: Article?
    
    @IBOutlet weak var pic: UIImageView!
    
    @IBOutlet weak var nameField: UITextField!
    
    @IBOutlet weak var typeField: UITextField!
    @IBOutlet weak var wearField: UITextField!
    
    @IBOutlet weak var finalUploadButtonView: UIButton!
    
    @IBAction func finalUploadButton(_ sender: Any) {
        print("Uploading \(nameField.text!) of type \(typeField.text!) with wear count \(wearField.text!)")
        self.dismiss(animated: true, completion: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        finalUploadButtonView.layer.cornerRadius = 22
        pic.image = article?.image
        
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
