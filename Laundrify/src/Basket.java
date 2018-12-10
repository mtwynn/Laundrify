import java.util.ArrayList;
import java.util.List;

public class Basket {
	List<Article> wardrobe;
	
	Basket() {
		wardrobe = new ArrayList<Article>();
	}
	
	void addArticle(Article a) {
		wardrobe.add(a);
	}
	
	void wash(Wardrobe w) {
		for (Article article : wardrobe) {
			w.addArticle(article);
			wardrobe.remove(article);
		}
	}
}
