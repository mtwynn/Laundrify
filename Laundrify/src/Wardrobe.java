import java.util.HashMap;


public class Wardrobe {
	private HashMap<String, Article> wardrobe;
	private Outfit outfit;
	
	public Wardrobe(Outfit outfit) {
		this.outfit = outfit;
		wardrobe = new HashMap<String, Article>();
	}
	
	// Add an article to this Wardrobe
	public void addArticle(Article a) {
		wardrobe.put(a.getId(), a);
	}
	
	
	// Puts an article of clothing into the Laundry
	public void putLaundry(String id, Basket b) throws NoClothesException {
		if (wardrobe.get(id) == null) throw new NoClothesException();
		
		try {
			outfit.takeOff(wardrobe.get(id));
		} catch (NoWearException e) {}
		
		b.addArticle(wardrobe.remove(id));
	}
	
	// Wears an article onto this Wardrobe's Outfit
	public void wearArticle(String id) throws DirtyClothesException {
		if (wardrobe.get(id) == null) throw new DirtyClothesException();
		
		if (!wardrobe.get(id).currentlyWorn) {
			wardrobe.get(id).wear();
			outfit.wear(wardrobe.get(id));
		}	
		else
			System.out.println(String.format("You're already wearing (a) %s!", wardrobe.get(id).type));
	}
	
	// Removes an article off this Wardrobe's Outfit
	public void removeArticle(String id) throws NoClothesException {
		if (wardrobe.get(id) == null) throw new NoClothesException();
		
		try {
			outfit.takeOff(wardrobe.get(id));
		} catch (NoWearException e) {
			e.print();
		}
	}
	
	// Removes all articles off this Wardrobe's Outfit
	public void removeAll() {
		outfit.strip();
	}
	
	public boolean containsId(String id) {
		if (wardrobe.containsKey(id)) {
			return true;
		}
		return false;
	}
	
	
	// Prints this Wardrobe
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
}
