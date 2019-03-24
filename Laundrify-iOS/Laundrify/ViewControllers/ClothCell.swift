//
//  ClothCell.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/17/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit

class ClothCell: UITableViewCell, UITextFieldDelegate {

    @IBOutlet weak var washBut: UIButton!
    @IBOutlet weak var clothImage: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    
    @IBOutlet weak var wornField: UITextField!
    
    @IBOutlet weak var totalField: UITextField!
    
    @IBOutlet weak var dirtyLabel: UILabel!
    
    @IBOutlet weak var cleanLabel: UILabel!
    
    @IBAction func incButton(_ sender: Any) {
        var value = (wornField.text! as NSString).integerValue
        value += 1
        if (value > 5) {
            cleanLabel.isHidden = true
            dirtyLabel.isHidden = false
        }
        wornField.text = String(value)
    }
    @IBAction func decButton(_ sender: Any) {
        var value = (wornField.text! as NSString).integerValue
        value -= 1
        if (value < 5) {
            cleanLabel.isHidden = false
            dirtyLabel.isHidden = true
        }
        if !(value < 0) {
            wornField.text = String(value)
        }
        
    }
    
    @IBAction func incTotalButton(_ sender: Any) {
        var value = (totalField.text! as NSString).integerValue
        value += 1
        totalField.text = String(value)
    }
    
    @IBAction func decTotalButton(_ sender: Any) {
        var value = (totalField.text! as NSString).integerValue
        value -= 1
        totalField.text = String(value)
    }
    
    @IBAction func wearButton(_ sender: Any) {
        var wearValue = (wornField.text! as NSString).integerValue
        wearValue += 1
        wornField.text = String(wearValue)
        if (wearValue < 5) {
            cleanLabel.isHidden = false
            dirtyLabel.isHidden = true
        }
        else if (wearValue > 5) {
            cleanLabel.isHidden = true
            dirtyLabel.isHidden = false
        }
    }
    @IBAction func washButton(_ sender: Any) {
        let wearValue = (wornField.text! as NSString).integerValue
        let totalValue = (totalField.text! as NSString).integerValue
        let sum = wearValue + totalValue
        totalField.text = String(sum)
        
        wornField.text = "0"
        cleanLabel.isHidden = false
        dirtyLabel.isHidden = true
        
        
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        clothImage.layer.cornerRadius = 10
        clothImage.clipsToBounds = true
        clothImage.layer.borderWidth = 2;
        
        let color : UIColor = UIColor(red: 188/255, green: 219/255, blue: 244/255, alpha: 1.0)
        clothImage.layer.borderColor = color.cgColor
        
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        self.wornField.delegate = self
        self.wornField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        self.totalField.addTarget(self, action: #selector(totalFieldDidChange(_:)), for: .editingChanged)
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool
        {
        let allowedCharacters = CharacterSet.decimalDigits
        let characterSet = CharacterSet(charactersIn: string)
        return allowedCharacters.isSuperset(of: characterSet)
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        
        return true
    }
    
    @objc func textFieldDidChange(_ wornField: UITextField) {
        print("Updating...")
        let wearValue = (wornField.text! as NSString).integerValue
        let totalValue = (totalField.text! as NSString).integerValue
        let sum = wearValue + totalValue
        totalField.text = String(sum)
        
        if (wearValue < 5) {
            cleanLabel.isHidden = false
            dirtyLabel.isHidden = true
        }
        else if (wearValue > 5) {
            cleanLabel.isHidden = true
            dirtyLabel.isHidden = false
        }
    }
    
    @objc func totalFieldDidChange(_ wornField: UITextField) {
        print("Updating...")
    }
}
