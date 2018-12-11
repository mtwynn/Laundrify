public class NoWearException extends Exception {
	
	NoWearException() {}
	
	public void print() {
		System.out.println("You're not wearing that right now!");
	}

}
