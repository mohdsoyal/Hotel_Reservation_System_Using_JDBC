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
			System.out.println("2. :Get Room By Room Id : !");
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
				
				
				roomService.addRoom(r1);
				System.out.println("Room Add Successfully ! ");
				break;
				
			case 2:
				
				Room r2 = new Room();
				System.out.println("Enter Room Id !");
				r2.setRoomId(sc.nextInt());
				
				roomService.getRoomById(r2.getRoomId());
				break;
				
			case 3:
				
				roomService.getAllRoom();
				break;
			
			}
			
			
			
		}
	}

}
