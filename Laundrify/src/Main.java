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
			
			System.out.println("Please select a command: ");
			System.out.println("ADD:   Add to wardrobe");
			System.out.println("LDY:   Add to laundry basket");
			System.out.println("WEAR:  Wear an article");
			System.out.println("WASH:  Wash all clothes");
			System.out.println("FIT:   Make me a clothing combination!");
			System.out.println("E: Exit");
			String command = s.nextLine();
			
			switch (command) {
			case "ADD":
				System.out.println("Adding to wardrobe...");
				System.out.println("What kind of clothing is it? (T-Shirt, Jacket, or Jeans): ");
				String type = s.nextLine();
				System.out.println("What brand is it? ");
				String brand = s.nextLine();
				System.out.println("What color is it?: ");
				String color = s.nextLine();
				System.out.println("Give it a unique ID!: (ex. A1)");
				String id = s.nextLine();
				wardrobe.addArticle(new Article(null, type, brand, color, id, false, 0));
				break;
			case "LDY":
				System.out.println("Adding to laundry basket");
				System.out.println("What is the ID of the item you would like to throw in the laundry?: ");
				String ldyId = s.nextLine();
				wardrobe.putLaundry(ldyId, basket);
				break;
			case "WEAR":
				System.out.println("Wearing article");
				System.out.println("What is the ID of the article you want to wear?: ");
				String wearId = s.nextLine();
				wardrobe.wearArticle(wearId);
				break;
			case "WASH":
				System.out.println("Washing all clothes...");
				basket.wash(wardrobe);
				break;
			case "FIT":
				break;
			case "E":
				System.out.println("Exiting");
				running = false;
				break;
			}
		}
		s.close();
	}
}
