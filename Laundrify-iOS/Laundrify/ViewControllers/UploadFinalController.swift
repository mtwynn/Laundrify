//
//  UploadFinalController.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/18/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit
import Parse

class UploadFinalController: UIViewController, UITextFieldDelegate {
    
    var newImage: UIImage!
    
    @IBOutlet weak var pic: UIImageView!
    
    @IBOutlet weak var nameField: UITextField!
    
    @IBOutlet weak var typeField: UITextField!
    @IBOutlet weak var wearField: UITextField!
    
    @IBOutlet weak var finalUploadButtonView: UIButton!
    
    @IBAction func finalUploadButton(_ sender: Any) {
        print("Uploading \(nameField.text!) of type \(typeField.text!) with wear count \(wearField.text!)")
        
        let upload = PFObject(className: "Picture")
        
        upload["name"] = nameField.text
        upload["type"] = typeField.text
        upload["wearLimit"] = (wearField.text as! NSString).integerValue
        upload["wearCount"] = 0
        upload["totalWears"] = 0
        upload["state"] = "closet"
        upload["owner"] = PFUser.current()
        
        let imageData = pic.image!.pngData()
        let file = PFFileObject(data: imageData!)
        upload["image"] = file
        
        upload.saveInBackground { (success, error) in
            if success {
                self.dismiss(animated: true, completion: nil)
                print("Saved!")
            } else {
                print("Error saving post, \(error?.localizedDescription)")
            }
        }
        
        self.tabBarController!.selectedIndex = 0
        /*
        let homeView = self.storyboard?.instantiateViewController(withIdentifier: "SearchViewController") as! SearchViewController
        self.navigationController?.pushViewController(homeView, animated: true)*/
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        finalUploadButtonView.layer.cornerRadius = 22
        pic.image = newImage
        self.wearField.delegate = self
        
        
        
        
        var tapGesture = UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:)))
        
        self.view.addGestureRecognizer(tapGesture)
        
        var swipeDown = UISwipeGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:)))
        
        swipeDown.direction = UISwipeGestureRecognizer.Direction.down
        self.view.addGestureRecognizer(swipeDown)
        
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow), name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillHide), name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    func textField(_ wearField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool
    {
        let allowedCharacters = CharacterSet.decimalDigits
        let characterSet = CharacterSet(charactersIn: string)
        return allowedCharacters.isSuperset(of: characterSet)
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

    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
}
