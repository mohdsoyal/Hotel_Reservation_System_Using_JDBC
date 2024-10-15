package UseCases;

import java.sql.SQLException;
import java.util.Scanner;

import Exception.RoomException;
import Model.Room;
import Service.RoomServiceImpl;
import Service.RoomServices;

public class RoomUseCases {
	
	
	private static Scanner sc = new Scanner(System.in);
	private static RoomServices roomService = new RoomServiceImpl();
	
	public static void RoomAllCases(){
		
		
		while(true) {
			System.out.println("1. :Add The New Room : ! ");
			System.out.println("2. :Get Room By Hotel Id : !");
			System.out.println("3. :Get All Room");
			
			
			
			System.out.println("Enter Your Choice..........");
			int choice = sc.nextInt();
			
			switch (choice) {
			
			case 1:
				
				Room r1 = new Room();
				
				System.out.println("Enter Room Type AC or Non-Ac ");
				r1.setRoomType(sc.next());
				 
				System.out.println("Enter Room Price ! ");
				r1.setRoomPrice(sc.next());
				
				System.out.println("Enter Hotel Id !");
				r1.setHotelId(sc.nextInt());
				
				roomService.addRoom(r1);
				System.out.println("Room Add Successfully ! ");
				break;
				
			case 2:
				
				Room r2 = new Room();
				System.out.println("Enter Hotel Id !");
				r2.setHotelId(sc.nextInt());
				roomService.getRoomByHotelId(r2.getHotelId());
				break;
				
			case 3:
				
				roomService.getAllRoom();
				break;
			
			}
			
			
			
		}
	}

}
