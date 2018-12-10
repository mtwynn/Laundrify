import java.awt.Image;

public class Article {
	Image img;
	String type; 
	String id;
	int wearCount;
	
	Article(Image img, String type, String id, int wearCount) {
		this.img = this.img;
		this.type = type;
		this.id = id;
		this.wearCount = wearCount;
	}
	
	void wear() {
		++wearCount;
	}
}
