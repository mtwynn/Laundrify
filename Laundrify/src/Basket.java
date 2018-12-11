import java.util.HashMap;

public class Basket {
	private HashMap<String, Article> basket;
	
	Basket() {
		basket = new HashMap<String, Article>();
	}
	
	void addArticle(Article a) {
		basket.put(a.getId(), a);
	}
	
	void wash(Wardrobe w) {
		for (String key : basket.keySet()) {
			basket.get(key).resetWear();
			w.addArticle(basket.remove(key));
		}
	}
	
	void print() {
		System.out.println("====YOUR CURRENT BASKET====");
		System.out.println("===========================");
		System.out.println();
		for (String key : basket.keySet()) {
			System.out.println(String.format("%s: %s %s %s - WORN %d TIME(S).", basket.get(key).getId(), basket.get(key).color, basket.get(key).brand, basket.get(key).type, basket.get(key).wearCount));
		}
		System.out.println();
		System.out.println("++++++++END BASKET+++++++++");
		System.out.println();
		System.out.println();
	}
}
