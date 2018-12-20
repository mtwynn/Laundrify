import java.awt.Image;

public class Article {
	Image img;
	String type; 
	String color;
	String brand;
	private String id;
	boolean currentlyWorn;
	int wearCount;
	int totalWearCount;
	
	Article(Image img, String type, String color, String brand, String id, boolean currentlyWorn, int wearCount) {
		this.img = img;
		this.type = type;
		this.color = color;
		this.brand = brand;
		this.id = id;
		this.currentlyWorn = currentlyWorn;
		this.wearCount = wearCount;
	}
	
	public void resetWear() {
		this.wearCount = 0;
		takeOff();
	}
	
	public void wear() {
		++wearCount;
		currentlyWorn = true;
		
		switch (type) {
		
		case "T-Shirt":
			if (wearCount == 1) {
				System.out.println("It's time to wash your " + color + " t-shirt!");
			}
			break;
			
		case "Jacket":
			if (wearCount > 10) {
				System.out.println("It's time to wash your " + color + " jacket!");
			}
			break;
			
		case "Jeans":
			if (wearCount > 5) {
				System.out.println("It's time to wash your " + color + " jeans!");
			}
			break;
			
		case "Underwear":
			if (wearCount == 1) {
				System.out.println("It's time to wash your " + color + " underwear!");
			}
		
		case "Socks":
			if (wearCount == 1) {
				System.out.println("It's time to wash your " + color + " socks!");
			}
		}
	}
	
	public void takeOff() {
		currentlyWorn = false;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void print() {
		System.out.println(String.format("%s: %s %s %s - WORN %d TIME(S).", id, color, brand, type, wearCount));
	}
}
