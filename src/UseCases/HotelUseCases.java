package UseCases;

import java.util.Scanner;

import Model.Hotel;
import Service.HotelService;
import Service.HotelServiceImpl;

public class HotelUseCases {

	private static Scanner sc = new Scanner(System.in);
	
	private static HotelService hotelservice = new HotelServiceImpl();
	
	
	public static void HotelCases() {
		
		while(true) {
			System.out.println("+-----------All Functionality For The Hotel-----------+");
			System.out.println(" ");
			System.out.println("1. Add New Hotel !");
			System.out.println("2. Get All Hotel !");
			System.out.println("3. Get Hotel By City Name ! ");
			System.out.println("4. Count Hotel By City !");
			System.out.println("3. Exit.....");
			
			
			System.out.println("Enter Your Choice !");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				Hotel h1 = new Hotel();
				
				System.out.println("Enter Hotel Name");
				h1.setHotelName(sc.next());
				
				System.out.println("Enter Hotel City");
				h1.setCity(sc.next());
				
				System.out.println("Enter Total Rooms ");
				h1.setTotalRooms(sc.nextInt());
				
				System.out.println("Enter Available Rooms ");
				h1.setAvailableRooms(sc.nextInt());
				
				hotelservice.addHotel(h1);
				System.out.println("Room Add Successfully");
				break;
				
			case 2:
				hotelservice.getAllHotels();
				break;
			case 3:
				Hotel h3 = new Hotel();
				
				System.out.println("Enter City and Get Hotel ! ");
				h3.setCity(sc.next());
				hotelservice.getHotelsByCity(h3.getCity());
				break;
			case 4:
			    Hotel h4 = new Hotel();
			    System.out.println("Enter City: ");
			    h4.setCity(sc.next());
			    int hotelCount = hotelservice.countHotelsByCity(h4.getCity());
			    System.out.println("Number of hotels in " + h4.getCity() + ": " + hotelCount);
			    break;

			case 5:
				System.exit(5);
				break;
				default:
					System.out.println("Invalid Choice Plz Try Again..........");
				
			}
		}
	}
}
