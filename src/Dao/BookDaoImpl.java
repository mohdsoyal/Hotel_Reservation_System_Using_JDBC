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
import Exception.HotelException;
import Model.Book;
import Model.Guest;
import Utility.DataBaseConnectvity;

public class BookDaoImpl implements BookDao {

	@Override
	public void bookRoom(Book book) throws BookException {
		String query = "insert into book (GuestId,RoomId,BookingDate,CheckIN,CheckOut,hotelId) values (?,?,?,?,?,?)";
		String updateRoom = "UPDATE room SET roomStatus = ? WHERE roomId = ?";
		String updateavailable = "update hotel set availableRooms = availableRooms - ? where hotelId=?";

		try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, book.getGuestId());
			ps.setInt(2, book.getRoomId());
			Timestamp BookingDate = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(3, BookingDate);
			ps.setString(4, book.getCheckIN());
			ps.setString(5, book.getCheckOut());
			ps.setInt(6, book.getHotelId());

			int result = ps.executeUpdate();
			if (result <= 0) {
				throw new BookException("Failed to add the Booking.");
			}

			// this code work for the you can book and update the room column Booked
			PreparedStatement Update = conn.prepareStatement(updateRoom);
			Update.setString(1, "Booked");
			Update.setInt(2, book.getRoomId());
			int Res = Update.executeUpdate();
			if (Res <= 0) {
				throw new BookException("Failed to update the room status.");
			}
			// end it
			
			// available seats decrement code after booking 
			PreparedStatement available = conn.prepareStatement(updateavailable);
			available.setInt(1, 1);
			available.setInt(2, book.getHotelId());
			int res = available.executeUpdate();
			if(res <= 0) {
				throw new HotelException("Failed to update seats status");
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
				System.out.println(
						"+----------+--------------+-------------------+---------------------+------------+-----------+");
				System.out.println(
						"| BookId   | GuestID      | RoomID            | BookingDate         | CheckIn    | CheckOut  |");
				System.out.println(
						"+----------+--------------+-------------------+---------------------+------------+-----------+");

				while(rs.next()) {
					int bId = rs.getInt("BookingId");
					int gId = rs.getInt("GuestId");
					int rId = rs.getInt("RoomId");
					String bDate = rs.getString("BookingDate");
					String cIn = rs.getString("checkIn");
					String cOut = rs.getString("checkOut");
					int hId = rs.getInt("hotelId");
					

					Book g1 = new Book(bId, gId, rId, bDate, cIn, cOut , hId);
					book.add(g1);

					System.out.printf("| %-8d | %-12s | %-17s | %-8s | %-10s |%-11s|\n", bId, gId, rId, bDate, cIn,cOut,hId);
					System.out.println(
							"+----------+--------------+-------------------+---------------------+------------+-----------+");
				}
			}

		} catch (SQLException e) {
			throw new GuestException("Error fetching All guest: " + e.getMessage());
		}

		return book;
	}

	@Override
	public void cancelBooking(int roomId, int hotelId) throws BookException {
	    String delete = "DELETE FROM book WHERE roomId = ?";
	    String updateRoom = "UPDATE room SET roomStatus = ? WHERE roomId = ?";
	    String updateAvailable = "UPDATE hotel SET availableRooms = availableRooms + 1 WHERE hotelId = ?";

	    try (Connection conn = DataBaseConnectvity.getInstance().getConnection();
	         PreparedStatement canbook = conn.prepareStatement(delete)) {

	        // Step 1: Delete the booking
	        canbook.setInt(1, roomId);
	        int rowsAffected = canbook.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Booking with ID " + roomId + " canceled successfully.");
	        } else {
	            System.out.println("No booking found with ID " + roomId + ". Cancel failed.");
	            return; 
	        }

	        // Step 2: Update the room status back to 'Available'
	        try (PreparedStatement psUpdate = conn.prepareStatement(updateRoom)) {
	            psUpdate.setString(1, "Available");
	            psUpdate.setInt(2, roomId);
	            int updateResult = psUpdate.executeUpdate();
	            if (updateResult <= 0) {
	                throw new BookException("Failed to update room status.");
	            } else {
	                System.out.println("Room with ID " + roomId + " status updated to 'Available'.");
	            }
	        }

	        // Step 3: Update the available rooms in the hotel
	        try (PreparedStatement available = conn.prepareStatement(updateAvailable)) {
	            available.setInt(1, hotelId); 
	            int res = available.executeUpdate();
	            if (res <= 0) {
	                throw new HotelException("Failed to update available room status.");
	            }
	        }

	    } catch (SQLException e) {
	        throw new BookException("Error canceling booking: " + e.getMessage());
	    }
	}

	}
	
