package UseCases;
import java.sql.SQLException;
import java.util.Scanner;

import Dao.UserDaoImpl;
import Model.User;

public class MainMethod {

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();  // DAO instance for user operations
        Scanner scanner = new Scanner(System.in);  // Input scanner
        
        while (true) {  // Main loop for login and signup
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left after nextInt()
            
            switch (choice) {
                case 1: // Login option
                    System.out.print("Enter Username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPassword = scanner.nextLine();
                    
                    // Call the login method from the DAO
                    boolean loggedIn = false;
                    try {
                        loggedIn = userDao.login(loginUsername, loginPassword);
                    } catch (SQLException e) {
                        System.out.println("Error during login: " + e.getMessage());
                    }
                    
                    if (loggedIn) {
                        System.out.println("Login Successful!");
                        showMainMenu();  // Show main menu after login
                    } else {
                        System.out.println("Invalid Credentials. Please try again.");
                    }
                    break;

                case 2: // Signup option
                    User newUser = new User();
                    System.out.print("Enter Username: ");
                    newUser.setUsername(scanner.nextLine());
                    System.out.print("Enter Password: ");
                    newUser.setPassword(scanner.nextLine()); // In real apps, hash the password
                    System.out.print("Enter Email: ");
                    newUser.setEmail(scanner.nextLine());  // Capture email as well
                    
                    try {
                        userDao.signup(newUser);  // Call the signup method from the DAO
                        System.out.println("Signup Successful!");
                    } catch (SQLException e) {
                        System.out.println("Error during signup: " + e.getMessage());
                    }
                    break;

                case 3: // Exit option
                    System.out.println("Exiting...");
                    scanner.close();
                    return;  // Exit the program

                default:  // Handle invalid inputs
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // This method shows the main menu after a successful login
    private static void showMainMenu() {
        // Objects for various use cases
        BookUseCases booking = new BookUseCases();
        GuestUseCases guest = new GuestUseCases();
        RoomUseCases room = new RoomUseCases();
        HotelUseCases hotel = new HotelUseCases();
        Scanner scanner = new Scanner(System.in);

        while (true) {  // Loop for the main menu
            System.out.println("1. All Cases For Hotel!");
            System.out.println("2. All Cases For Adding Room!");
            System.out.println("3. All Cases For Guest!");
            System.out.println("4. All Cases For Booking!");
            System.out.println("5. Exit!");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    hotel.HotelCases();  // Call hotel-related use cases
                    break;
                case 2:
                    room.RoomAllCases();  // Call room-related use cases
                    break;
                case 3:
                    guest.GuestallCases();  // Call guest-related use cases
                    break;
                case 4:
                    booking.AllBookCases();  // Call booking-related use cases
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;  // Exit the main menu, return to login/signup
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
