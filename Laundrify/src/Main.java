import java.util.Scanner;

public class Main {
	
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		Outfit outfit = new Outfit();
		Wardrobe wardrobe = new Wardrobe(outfit);
		Basket basket = new Basket();
		
		System.out.println("Welcome to Laundrify!");
		System.out.println();
		System.out.println();
		
		
		boolean running = true;
		while (running) {
			wardrobe.print();
			basket.print();
			outfit.print();
			
			System.out.println("PLEASE TYPE A COMMAND: ");
			System.out.println("ADD  :   Add to wardrobe");
			System.out.println("LDY  :   Add to laundry basket");
			System.out.println("WASH :   Wash all clothes");
			System.out.println("WEAR :   Wear an article");
			System.out.println("REM  :   Remove an article");
			System.out.println("STRIP:   Take off all clothes");
			System.out.println("FIT  :   Make me an outfit!");
			System.out.println("SAVE :   Save this wardrobe");
			System.out.println("LOAD :   Load a wardrobe");
			System.out.println("E    :   Exit");
			String command = s.nextLine().toLowerCase().trim();
			
			switch (command) {
			
			case "add":
				System.out.println("Adding to wardrobe...");
				System.out.println("What color is it?: ");
				String color = s.nextLine();
				System.out.println("What brand is it? ");
				String brand = s.nextLine();
				System.out.println("What kind of clothing is it? (T-Shirt, Jacket, Jeans, Underwear, or Socks): ");
				String type = s.nextLine();
				System.out.println("Give it a unique ID!: (ex. A1)");
				String id = s.nextLine();
				while (wardrobe.containsId(id)) {
					System.out.println("You already have that ID in your wardrobe. Please try another ID.");
					id = s.nextLine();
				}
				wardrobe.addArticle(new Article(null, type, color, brand, id, false, 0));
				break;
				
			case "ldy":
				System.out.println("Adding to laundry basket...");
				System.out.println("What are the IDs of the item you would like to throw in the laundry?: ");
				String[] ldyId = s.nextLine().split(" ");
				
				try {
					for (String temp : ldyId) {
						System.out.println(temp + " d ");
						wardrobe.putLaundry(temp, basket);
					}
					
				} catch (NoClothesException e) {
					e.print();
				}
				break;
				
			case "wash":
				System.out.println("Washing all clothes...");
				basket.wash(wardrobe);
				break;
				
			case "wear":
				System.out.println("Wearing article...");
				System.out.println("What are the IDs of the articles you want to wear?: ");
				String[] wearId = s.nextLine().split(" ");
				try {
					for (String temp : wearId) {
						wardrobe.wearArticle(temp);
					}
					
				} catch (DirtyClothesException e) {
					e.print();
				}
				break;
			
			case "rem":
				System.out.println("Removing article...");
				System.out.println("What are the IDs of the article you want to remove?");
				String[] remId = s.nextLine().split(" ");
				try {
					for (String temp : remId) {
						wardrobe.removeArticle(temp);
					}
					
				} catch (NoClothesException e) {
					e.print();
				}
				break;
				
			case "strip":
				System.out.println("Removing all clothes...");
				wardrobe.removeAll();
				break;
				
			case "fit":
				Outfit o = wardrobe.generateFit();
				System.out.println("YOUR SUGGESTED OUTFIT: ");
				o.print();
				break;
			
			case "save":
				wardrobe.save();
				break;
				
			case "load":
				System.out.println("Please type the file path: ");
				String path = s.nextLine();
				wardrobe.load(path);
				break;
				
			case "e":
				System.out.println("Exiting");
				running = false;
				break;
			}
		}
		s.close();
	}
}
