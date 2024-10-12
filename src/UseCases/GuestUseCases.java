package UseCases;

import java.util.Scanner;

import Model.Guest;
import Service.GuestService;
import Service.GuestServiceImpl;

public class GuestUseCases {
	
	private static Scanner sc  = new Scanner(System.in);
    private static GuestService guestService = new GuestServiceImpl();
    
    public static void GuestallCases() {
    	
        while (true) {
            System.out.println("\nHotel Reservation System:");
            System.out.println("1. Add  Guest ! ");
            System.out.println("2. Update Guest ");
            System.out.println("3. Get Guest By Id !");
            System.out.println("4. Get all Guest List !");
            System.out.println("5. Delete Guest By ID !");
            System.out.println("6. Exit");
            
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            
            switch (choice) {
                
                case 1:
                    Guest d  = new Guest();
                    
                    System.out.println("Enter Guest  Name ! :");
                    d.setgName(sc.next());
                    
                    System.out.println("Enter Guest Email ! :");
                    d.setgEmail(sc.next());
                    
                    System.out.println("Enter Guest Phone ! :");
                    d.setgPhone(sc.next());
                    
                    System.out.println("Enter Guest Address ! :");
                    d.setgAddress(sc.next());
                      
                    guestService.addGuest(d);
                    System.out.println("Guest Added successfully!");
                    break;
                case 2:
                	Guest d1 = new Guest();
                	System.out.println("Enter Guest Id");
                	d1.setgId(sc.nextInt());
                	
                	System.out.println("Enter New Name  ! ");
                	d1.setgName(sc.next());
                	
                	System.out.println("Enter New Email   ! ");
                	d1.setgEmail(sc.next());
                	
                	System.out.println("Enter New Phone Number  ! ");
                	d1.setgPhone(sc.next());
                	
                	System.out.println("Enter New Address  !");
                	d1.setgAddress(sc.next());
                	guestService.updateGuest(d1);
                	break;
                	
                case 3:
                	Guest d2 = new Guest();
                	System.out.println("Enter Guest Id !");
                	 d2.setgId(sc.nextInt());
                	 guestService.getGuestById(d2.getgId());  
                	break;
                	
                case 4:
                	guestService.getAllGuest();
                	break;
                case 5:
                	Guest d3 = new Guest();
                	System.out.println("Enter Guest Id");
                	d3.setgId(sc.nextInt());
                	guestService.deleteGuest(d3.getgId());
                	break;
                case 6:
                	System.exit(6);
                	break;
                	default:
                		System.out.println("Invalid Choice Plz Try Again ! ");
                    }
                    

 }
        }
    }
