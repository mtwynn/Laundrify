public class DirtyClothesException extends Exception {
	Article a;
	
	DirtyClothesException() {}
	
	public void print() {
		System.out.println("You can't wear dirty clothes! Wash your clothes first.");
	}
}
