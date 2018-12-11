import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Basket {
	List<Article> basket;
	
	Basket() {
		basket = new ArrayList<Article>();
	}
	
	void addArticle(Article a) {
		basket.add(a);
	}
	
	void wash(Wardrobe w) {
		for (Iterator<Article> iterator = basket.iterator(); iterator.hasNext(); ) {
			Article article = iterator.next();
			
			article.resetWear();
			w.addArticle(article);
			iterator.remove();
		}
	}
	
	void print() {
		System.out.println("====YOUR CURRENT BASKET====");
		System.out.println("===========================");
		System.out.println();
		for (Article article : basket) {
			System.out.println(String.format("%s %s: Worn %d times.", article.color, article.type, article.wearCount));
			//System.out.println(article.color + " " + article.type + ": " + " Worn " + article.wearCount + " times.");
		}
		System.out.println();
		System.out.println("++++++++END BASKET+++++++++");
		System.out.println();
	}
}
