//
//  MainViewController.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/17/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit
import Parse

class MainViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UISearchBarDelegate {
    
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var closetView: UITableView!
    
    var articles = [PFObject]()
    var filteredArticles = [PFObject]()
    
    

    @IBAction func logoutButton(_ sender: Any) {
        PFUser.logOut()
        
        let main = UIStoryboard(name: "Main", bundle: nil)
        
        let loginViewController = main.instantiateViewController(withIdentifier: "LoginViewController")
        
        let delegate = UIApplication.shared.delegate as! AppDelegate
        
        delegate.window?.rootViewController = loginViewController
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 50
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ClothCell", for: indexPath) as! ClothCell
        cell.totalLabel!.text = "3"
        cell.dirtyLabel.isHidden = true
        
        return cell
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        closetView.delegate = self
        closetView.dataSource = self
        searchBar.delegate = self
    }

}
