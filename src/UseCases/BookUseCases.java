package UseCases;

import java.util.Scanner;

import Model.Book;
import Service.BookService;
import Service.BookServiceImpl;

public class BookUseCases {
	
	private static Scanner sc = new Scanner(System.in);
	private static BookService bookservice= new BookServiceImpl();
	
	
	
	public static void AllBookCases() {
		
		
		while(true) {
			System.out.println("1. Enter TO Book Room");
			System.out.println("2. Get Room By Guest ID");
			System.out.println("3. Cancel Booking By BookingId !");
			System.out.println("4. Exit ! ");
			
			
			System.out.println("Enter Your Choice !");
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				Book book = new Book();
				
				System.out.println("Enter Guest Id");
				book.setGuestId(sc.nextInt());
				
				System.out.println("Enter Room Id");
				book.setRoomId(sc.nextInt());
				
				System.out.println("Enter Your Checked In Data '{YY-MM-DD}' ");
				book.setCheckIN(sc.next());
				
				System.out.println("Enter Your Checked Out Date '{YY-MM-DD}'");
				book.setCheckOut(sc.next());
				
				bookservice.bookRoom(book);
				System.out.println("Room Booking Successfully ! ");
				break;
			case 2:
				Book book2 = new Book();
				
				System.out.println("Enter Guest Id ! ");
				book2.setGuestId(sc.nextInt());
				bookservice.getBookingByGuestId(book2.getGuestId());
				break;
				
			case 3:
				Book book3 = new Book();
				System.out.println("Enter Room Id");
				book3.setRoomId(sc.nextInt());
				bookservice.cancelBooking(book3.getRoomId());
				break;
			case 4:
				System.exit(4);
				break;
			
			default:
				System.out.println("Invalid Choice Plz Try Again..........");
			}
		}
	}

}
