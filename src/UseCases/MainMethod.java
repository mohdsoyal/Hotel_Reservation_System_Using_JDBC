package UseCases;

import java.util.Scanner;

public class MainMethod {

	static Scanner s1 = new Scanner(System.in);

	public static void main(String[] args) {

		BookUseCases booking = new BookUseCases();
		GuestUseCases guest = new GuestUseCases();
		RoomUseCases room = new RoomUseCases();

		while (true) {
			System.out.println("1. All Cases For Adding Room !");
			System.out.println("2. All Cases For Guest !");
			System.out.println("3. All Cases For Booking  !");
			System.out.println("4. Exit !");

			int choice = s1.nextInt();

			switch (choice) {

			case 1:
				room.RoomAllCases();
			case 2:
				guest.GuestallCases();

			case 3:
				booking.AllBookCases();

			case 4:
				System.exit(4);

			}

		}

	}

}
