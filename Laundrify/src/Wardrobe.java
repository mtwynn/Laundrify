import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Wardrobe {
	private List<Article> wardrobe;
	
	public Wardrobe() {
		wardrobe = new ArrayList<Article>();
	}
	
	public void addArticle(Article a) {
		wardrobe.add(a);
	}
	
	public void print() {
		System.out.println("===YOUR CURRENT WARDROBE===");
		System.out.println("===========================");
		System.out.println();
		for (Article article : wardrobe) {
			System.out.println(String.format("%s %s: Worn %d times.", article.color, article.type, article.wearCount));
			//System.out.println(article.color + " " + article.type + ": " + " Worn " + article.wearCount + " times.");
		}
		System.out.println();
		System.out.println("+++++++END WARD ROBE+++++++");
		System.out.println();
	}
	
	public void putLaundry(String id, Basket b) {
		for (Iterator<Article> iterator = wardrobe.iterator(); iterator.hasNext(); ) {
			Article article = iterator.next();
			if (article.getId().equals(id)) {
				b.addArticle(article);
				iterator.remove();
			}
			
		}
	}
	
	public void wearArticle(String id) {
		for (Article article : wardrobe) {
			if (article.getId().equals(id)) {
				article.wear();
			}
		}
	}
	
}
