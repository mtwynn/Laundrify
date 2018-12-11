import java.util.HashMap;
import java.util.Set;

public class Outfit {
	HashMap<String, Article> outfit = new HashMap<String, Article>();
	
	Outfit() {
		outfit.put("T-shirt", null);
		outfit.put("Jacket", null);
		outfit.put("Jeans", null);
		outfit.put("Underwear", null);
		outfit.put("Socks", null);
	}
	
	
	// Wear an article
	public void wear(Article a) {
		if (outfit.get(a.type) == null) {
			outfit.put(a.type, a);
		} else {
			System.out.println(String.format("You're already wearing (a) %s!", a.type));
		}
	}
	
	// Take off an article from this Outfit
	public void takeOff(Article a) throws NoWearException {
		if (outfit.get(a.type) == null || outfit.get(a.type).getId() != a.getId()) throw new NoWearException();
		else {
			a.takeOff();
			outfit.put(a.type, null);
		}
		
	}
	
	// Take off all articles from this Outfit
	public void strip() {
		for (String key : outfit.keySet()) {
			if (outfit.get(key) != null) {
				outfit.get(key).takeOff();
				outfit.put(key, null);
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	// Prints this Outfit
	public void print() {
		System.out.println("====YOUR CURRENT OUTFIT====");
		System.out.println("===========================");
		System.out.println();
		if (outfit.size() == 0) {
			System.out.println();
		} else {
			Set<String> keys = outfit.keySet();
			for (String key : keys) {
				if (outfit.get(key) != null)
					outfit.get(key).print();
			}
		}
		System.out.println();
		System.out.println("++++++++END OUTFIT+++++++++");
		System.out.println();
		System.out.println();
	}
}
