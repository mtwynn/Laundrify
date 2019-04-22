//
//  MainViewController.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/17/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit
import Parse

class SearchViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UISearchBarDelegate {
    
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var closetView: UITableView!
    
    var articles = [Article]()
    var filteredArticles = [Article]()
    let myRefreshControl = UIRefreshControl()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        closetView.delegate = self
        closetView.dataSource = self
        closetView.allowsSelection = false
        searchBar.delegate = self
        
        self.view.addGestureRecognizer(UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:))))
        myRefreshControl.addTarget(self, action: #selector(loadPics), for: .valueChanged)
        closetView.refreshControl = myRefreshControl
        loadPics()
        
    }
    
    @IBAction func logoutButton(_ sender: Any) {
        PFUser.logOut()
        
        let main = UIStoryboard(name: "Main", bundle: nil)
        
        let loginViewController = main.instantiateViewController(withIdentifier: "LoginViewController")
        
        let delegate = UIApplication.shared.delegate as! AppDelegate
        
        delegate.window?.rootViewController = loginViewController
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return filteredArticles.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = closetView.dequeueReusableCell(withIdentifier: "ClothCell", for: indexPath) as! ClothCell
        
        let article = filteredArticles[indexPath.item]
        cell.clothImage.image = article.image
        cell.wornField.text = article.wearCount.stringValue
        cell.nameLabel.text = article.name
        cell.totalField.text = article.total.stringValue
        cell.dirtyLabel.isHidden = true
        cell.washBut.tag = indexPath.row
        cell.washBut.addTarget(self, action: #selector(washTapped(_:)), for: .touchUpInside)
        
        return cell
    }
    
    
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        // When there is no text, filteredData is the same as the original data
        // When user has entered text into the search box
        // Use the filter method to iterate over all items in the data array
        // For each item, return true if the item should be included and false if the
        // item should NOT be included
        filteredArticles = searchText.isEmpty ? articles : articles.filter { (article: Article) -> Bool in
            // If dataItem matches the searchText, return true to include it
            let name = article.name
            return name.lowercased().contains(searchText.lowercased())
        }
        
        
        closetView.reloadData()
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder() // hides the keyboard.
    }
    
    @objc func loadPics() {
        print("called")
        let query = PFQuery(className: "Picture")
        query.whereKey("state", equalTo: "closet")
        query.whereKey("owner", equalTo: PFUser.current())
        query.includeKeys(["owner"])
        query.findObjectsInBackground { (queryDict, error) in
            if let queryArticles = queryDict {
                self.articles.removeAll()
                self.filteredArticles.removeAll()
                for articleDict in queryArticles {
                    
                    let imageFile = articleDict["image"] as! PFFileObject
                    let url = URL(string: imageFile.url!)!
                    let data = try? Data(contentsOf: url)
                    let pic = UIImage(data: data!)
                    let article = Article(id: articleDict.objectId as! String,
                        name: articleDict["name"] as! String,
                                      type: articleDict["type"] as! String,
                                      wearCount: articleDict["wearCount"] as! NSNumber,
                                      total: articleDict["totalWears"] as! NSNumber,
                                      wearLimit: articleDict["wearLimit"] as! NSNumber,
                                      image: pic!,
                                      owner: articleDict["owner"] as! PFUser)
                    
                    self.articles.append(article)
                    self.filteredArticles = self.articles
                }
                    if self.articles.count == 0 {
                        let rect = CGRect(origin: CGPoint(x: 0,y :0), size: CGSize(width: self.view.bounds.size.width, height: self.view.bounds.size.height))
                        let messageLabel = UILabel(frame: rect)
                        messageLabel.text = "You don't have any projects yet.\nYou can create up to 10."
                        messageLabel.textColor = UIColor.black
                        messageLabel.numberOfLines = 0;
                        messageLabel.textAlignment = .center;
                        messageLabel.font = UIFont(name: "TrebuchetMS", size: 15)
                        messageLabel.sizeToFit()
                        
                        self.closetView.backgroundView = messageLabel;
                        self.closetView.separatorStyle = .none;
                    } else {
                        self.closetView.backgroundView = nil;
                    }
                    
                    self.closetView.reloadData()
                    self.myRefreshControl.endRefreshing()
                    }
                }
        }
    
    @objc func washTapped(_ sender: UIButton) {
        let article = articles[sender.tag]
        let name = article.name
        
        let query = PFQuery(className: "Picture")
        query.getObjectInBackground(withId: article.id) {
            (article: PFObject?, error: Error?) in
            if let error = error {
                print(error.localizedDescription)
            } else if let article = article {
                article["state"] = "basket"
                article.saveInBackground()
                self.loadPics()
            }
        }
        
        let alert = UIAlertController(title: "Washed!", message: "Washing \(article.name)", preferredStyle: .alert)
        let okAction = UIAlertAction(title: "OK", style: .default, handler: nil)
        alert.addAction(okAction)
        
        self.present(alert, animated: true, completion: nil)
    }
}
