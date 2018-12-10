import java.util.Scanner;

public class Main {
	
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		Wardrobe wardrobe;
		Basket basket;
		
		
		System.out.println("Welcome to Laundrify!");
		System.out.println();
		System.out.println("===YOUR CURRENT WARDROBE===");
		System.out.println("===========================");
		System.out.println();
		System.out.println();
		System.out.println("====YOUR CURRENT BASKET====");
		System.out.println("===========================");
		System.out.println();
		System.out.println("Please select a command: ");
		System.out.println("A: Add to wardrobe");
		System.out.println("L: Add to laundry basket");
		System.out.println("W: Wear an article");
		
		boolean running = true;
		while (running) {
			String command = s.nextLine();
			
			switch (command) {
			case "A":
				System.out.println("Adding to wardrobe");
				break;
			case "L":
				System.out.println("Adding to laundry basket");
				break;
			case "W":
				System.out.println("Wearing article");
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
