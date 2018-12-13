import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;


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
	
	public Outfit generateFit() { // INPROG
		Article[] articles = wardrobe.values().toArray(new Article[wardrobe.size()]);
		Outfit o = new Outfit();
		
		int counter = 0;
		Random rand = new Random();
		while (counter != o.size()) {
			int r = rand.nextInt(articles.length);
		
		    if (o.wear(articles[r])) {
		    	++counter;
		    } else {
		    	continue;
		    }
			
		}
		o.print();
		return o;
	}
	
	
	// Saves this Wardrobe
	public void save() {
		System.out.println("Saving...");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Wardrobe.txt", true));
			for (String key : wardrobe.keySet()) {
				bw.write(String.format("%s: %s %s %s - WORN %d TIME(S).\n", wardrobe.get(key).getId(), wardrobe.get(key).color, wardrobe.get(key).brand, wardrobe.get(key).type, wardrobe.get(key).wearCount));
				bw.flush();
			}
			
			bw.close();
		} catch (IOException e) {
			System.out.println("There was a problem saving your file.");
		}
		System.out.println("Saved!");
		
	}
	
	// Loads this wardrobe ID: Color Brand Type - WORN INT TIME(S).
	public void load (String path) {
		System.out.println("Loading...");
		HashMap<String, Article> w = new HashMap<String, Article>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String in;
			while ((in = br.readLine()) != null) {
				String[] articleStr = in.split(" ");
				
				Article a = new Article(null, articleStr[3], articleStr[1], articleStr[2], articleStr[0].substring(0, 2), false, Integer.parseInt(articleStr[6]));
				w.put(articleStr[3], a);
			}
			
			br.close();
		} catch (IOException e) {
			System.out.println("There was a problem loading your file.");
		}
        this.wardrobe = w;
        System.out.println("Loaded!");
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
