//
//  ClothCell.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/17/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit

class ClothCell: UITableViewCell, UITextFieldDelegate {

    
    @IBOutlet weak var wornField: UITextField!
    
    @IBOutlet weak var totalLabel: UILabel!
    
    @IBOutlet weak var dirtyLabel: UILabel!
    
    @IBOutlet weak var cleanLabel: UILabel!
    
    @IBAction func incButton(_ sender: Any) {
        print("Incrementing...")
    }
    @IBAction func decButton(_ sender: Any) {
        print("Decrementing...")
    }
    @IBAction func wearButton(_ sender: Any) {
        print("Wearing...")
    }
    @IBAction func washButton(_ sender: Any) {
        print("Washing...")
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        self.wornField.delegate = self
        // Configure the view for the selected state
    }
    
    func textField(_ wornField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool
        {
        let allowedCharacters = CharacterSet.decimalDigits
        let characterSet = CharacterSet(charactersIn: string)
        return allowedCharacters.isSuperset(of: characterSet)
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        
        return true
    }

}
