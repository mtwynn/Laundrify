import java.awt.Image;

public class Article {
	Image img;
	String type; 
	String color;
	String id;
	int wearCount;
	
	Article(Image img, String type, String color, String id, int wearCount) {
		this.img = img;
		this.type = type;
		this.color = color;
		this.id = id;
		this.wearCount = wearCount;
	}
	
	void resetWear() {
		this.wearCount = 0;
	}
	
	void wear() {
		++wearCount;
		
		switch (type) {
		case "T-Shirt":
			if (wearCount > 1) {
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
		}
	}
	
	String getId() {
		return this.id;
	}
}
