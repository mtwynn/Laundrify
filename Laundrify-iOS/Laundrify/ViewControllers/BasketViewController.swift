//
//  BasketViewController.swift
//  Laundrify
//
//  Created by Tam Nguyen on 3/23/19.
//  Copyright © 2019 Tam Nguyen. All rights reserved.
//

import UIKit
import Parse

class BasketViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var tableView: UITableView!
    
    
    var articles = [Article]()
    let myRefreshControl = UIRefreshControl()
    
    
    @IBAction func doneButton(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func washButton(_ sender: Any) {
        let query = PFQuery(className: "Picture")
        query.whereKey("state", equalTo: "basket")
        query.includeKeys(["owner"])
        query.findObjectsInBackground { (queryDict, error) in
            if let queryArticles = queryDict {
                for articleDict in queryArticles {
                    articleDict["state"] = "closet"
                    let sum = (articleDict["totalWears"] as! Int) + (articleDict["wearCount"] as! Int)
                    articleDict["totalWears"] = sum
                    
                    articleDict["wearCount"] = 0
                    articleDict.saveInBackground()
                    self.loadBasket()
                }
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.delegate = self
        tableView.dataSource = self
        tableView.allowsSelection = false
        
        loadBasket()
        myRefreshControl.addTarget(self, action: #selector(loadBasket), for: .valueChanged)
        tableView.refreshControl = myRefreshControl
    
    }
    

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return articles.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "BasketCell", for: indexPath) as! BasketCell
        
        let article = articles[indexPath.item]
        cell.basketCellImage.image = article.image
        
        return cell
        
    }
    
    @objc func loadBasket() {
        let query = PFQuery(className: "Picture")
        query.whereKey("state", equalTo: "basket")
        query.includeKeys(["owner"])
        query.findObjectsInBackground { (queryDict, error) in
            if let queryArticles = queryDict {
                self.articles.removeAll()
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
                    self.tableView.reloadData()
                    self.myRefreshControl.endRefreshing()
                }
            }
        }
    }

}
