public class NoClothesException extends Exception {
	
	NoClothesException() {}
	
	public void print() {
		System.out.println("You don't have that in your wardrobe!");
	}

}
