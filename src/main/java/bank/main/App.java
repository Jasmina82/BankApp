package bank.main;



public class App {

	public static void main(String[] args) {
		
		while (true) {

			startApp();
		}
	}

	public static void startApp() {

		System.out.println("   ******************************");
		System.out.println("   *        B A N K             *");
		System.out.println("   ******************************\n");

		
	 GuestMenu guest=new GuestMenu();
	 guest.guestMenu();
	}



}
