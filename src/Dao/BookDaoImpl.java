package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Exception.BookException;
import Exception.GuestException;
import Model.Book;
import Model.Guest;
import Utility.DataBaseConnectvity;

public class BookDaoImpl  implements BookDao{

	@Override
	public void bookRoom(Book book) throws BookException {
		String query = "insert into book (GuestId, RoomId , BookingDate ,CheckIN , CheckOut) values (?,?,?,?,?)";
		 String updateRoom = "UPDATE room SET roomStatus = ? WHERE roomId = ?";

		try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, book.getGuestId());
			ps.setInt(2, book.getRoomId());
			Timestamp BookingDate = new Timestamp(System.currentTimeMillis());
		    ps.setTimestamp(3, BookingDate);
			ps.setString(4, book.getCheckIN());
			ps.setString(5, book.getCheckOut());
			  
			int result = ps.executeUpdate();
			if (result <= 0) {
				throw new BookException("Failed to add the Booking.");
			}
			
			// this code work for the you can book and update the  room column Booked
	        PreparedStatement Update = conn.prepareStatement(updateRoom);
	        Update.setString(1, "Booked");  
	        Update.setInt(2, book.getRoomId());
	        int updateResult = Update.executeUpdate(); 
	        if (updateResult <= 0) {
	            throw new BookException("Failed to update the room status.");
	        }
	        // end it
			
		} catch (SQLException e) {
			throw new BookException("Error while adding the Booking: " + e.getMessage());
		}
	}

		
	

	@Override
	public List<Book> getBookingByGuestId(int guestId) throws BookException {
		
		List<Book> book = new ArrayList<>();
        String query = "SELECT * FROM book where GuestId = ?";
	    
	    try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, guestId);
	      
	        try (ResultSet rs = ps.executeQuery()) {
	            System.out.println("+----------+--------------+-------------------+---------------------+------------+-----------+");
	            System.out.println("| BookId   | GuestID      | RoomID            | BookingDate         | CheckIn    | CheckOut  |");
	            System.out.println("+----------+--------------+-------------------+---------------------+------------+-----------+");

	            if(rs.next()) {
	                int bId = rs.getInt("BookingId"); 
	                int gId = rs.getInt("GuestId");
	                int rId = rs.getInt("RoomId");
	                String bDate = rs.getString("BookingDate");
	                String cIn = rs.getString("checkIn");
	                String cOut = rs.getString("checkOut");

	                Book g1 = new Book(bId, gId, rId, bDate, cIn,cOut);
	                book.add(g1); 
	             
	                System.out.printf("| %-8d | %-12s | %-17s | %-8s | %-10s |%-11s|\n", bId, gId, rId, bDate, cIn,cOut);
	                System.out.println("+----------+--------------+-------------------+---------------------+------------+-----------+");
	            } 
	        }

	    } catch (SQLException e) {
	        throw new GuestException("Error fetching All guest: " + e.getMessage());
	    }
	
		return book;
	}

	@Override
	public void cancelBooking(int bookingId) throws BookException {
			String delete = "DELETE FROM book WHERE BookingId = ?";
			String updateRoom = "UPDATE room SET roomStatus = ? WHERE roomId = ?";
		    Book book = null;

		    try (Connection conn = DataBaseConnectvity.getInstance().getConnection();
		         PreparedStatement canbook = conn.prepareStatement(delete)) {

		    	canbook.setInt(1,bookingId);
		        int rowsAffected = canbook.executeUpdate();
		        
		        if (rowsAffected > 0) {
		            book = new Book();  
		            book.setBookingId(bookingId); 
		            System.out.println("Booking with ID " + bookingId + " Cancel Successfully.");
		        } else {
		            System.out.println("No found  Booking  with ID " + bookingId+ " Cancel failed.");
		        }
		        
		        // Update the room status back to 'Available'
		        PreparedStatement psUpdate = conn.prepareStatement(updateRoom);
		        psUpdate.setString(1, "Available");  
		        psUpdate.setInt(2, bookingId);

		        int updateResult = psUpdate.executeUpdate();
		        if (updateResult <= 0) {
		            throw new BookException("Failed to update room status.");
		        }

		    } catch (SQLException e) {
		        throw new GuestException("Error deleting car: " + e.getMessage());
		    } 
		}
			
		
	}


