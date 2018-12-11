import java.util.HashMap;

public class Wardrobe {
	private HashMap<String, Article> wardrobe;
	private Outfit outfit;
	
	public Wardrobe(Outfit outfit) {
		this.outfit = outfit;
		wardrobe = new HashMap<String, Article>();
	}
	
	public void addArticle(Article a) {
		wardrobe.put(a.getId(), a);
	}
	
	public void print() {
		System.out.println("===YOUR CURRENT WARDROBE===");
		System.out.println("===========================");
		System.out.println();
		for (String key : wardrobe.keySet()) {
			System.out.println(String.format("%s: %s %s %s - WORN %d TIME(S).", wardrobe.get(key).getId(), wardrobe.get(key).color, wardrobe.get(key).brand, wardrobe.get(key).type, wardrobe.get(key).wearCount));
		}
		System.out.println();
		System.out.println("+++++++END WARD ROBE+++++++");
		System.out.println();
		System.out.println();
	}
	
	public void putLaundry(String id, Basket b) {
		outfit.takeOff(wardrobe.get(id));
		b.addArticle(wardrobe.remove(id));
	}
	
	public void wearArticle(String id) {
		if (!wardrobe.get(id).currentlyWorn) {
			wardrobe.get(id).wear();
			outfit.wear(wardrobe.get(id));
		}	
		else
			System.out.println(String.format("You're already wearing (a) %s", wardrobe.get(id).type));
	}
	
}
