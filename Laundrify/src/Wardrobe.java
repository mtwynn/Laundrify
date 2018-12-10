import java.util.ArrayList;
import java.util.List;

public class Wardrobe {
	List<Article> wardrobe;
	
	Wardrobe() {
		wardrobe = new ArrayList<Article>();
	}
	
	void addArticle(Article a) {
		wardrobe.add(a);
	}
}
