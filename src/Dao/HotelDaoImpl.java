package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exception.GuestException;
import Exception.HotelException;
import Model.Guest;
import Model.Hotel;
import Utility.DataBaseConnectvity;

public class HotelDaoImpl  implements HotelDao{

	@Override
	public void addHotel(Hotel hotel) throws HotelException {
		
		String query = "insert into hotel (hotelName,city,totalRooms,availableRooms ) values (?,?,?,?)";

		try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, hotel.getHotelName());
			ps.setString(2, hotel.getCity());
			ps.setInt(3, hotel.getTotalRooms());
			ps.setInt(4, hotel.getAvailableRooms());
		
			
			int result = ps.executeUpdate();
			if (result <= 0) {
				throw new HotelException("Failed to add the Hotel.");
			}
		} catch (SQLException e) {
			throw new HotelException("Error while adding the Hotel: " + e.getMessage());
		}
	}

	
	@Override
	public List<Hotel> getHotelsByCity(String city) throws HotelException {
		
		List<Hotel> hotel = new ArrayList<>();
        String query = "SELECT * FROM hotel where city = ? ";
	    
	    try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(query);
	        
	        ps.setString(1, city);
	      
	        try (ResultSet rs = ps.executeQuery()) {
	            System.out.println("+-------+----------------+-------------+--------------+-----------------+");
	            System.out.println("| HID   | HotelName      | City        | TotalRooms   | AvailableRooms  |");
	            System.out.println("+-------+----------------+-------------+--------------+-----------------+");

	            while(rs.next()) {
	                int hId = rs.getInt("hotelId"); 
	                String hName = rs.getString("hotelName");
	                String hcity = rs.getString("city");
	                int trooms = rs.getInt("totalRooms");
	                int arooms = rs.getInt("availableRooms");

	                Hotel g1 = new Hotel(hId,hName,hcity,trooms,arooms);
	                hotel.add(g1); 
	                
	                System.out.printf("| %-5d | %-14s | %-11s | %-12s | %-15s |\n", hId,hName,city,trooms,arooms);
	                System.out.println("+-------+----------------+-------------+--------------+-----------------+");
	            } 
	        }

	    } catch (SQLException e) {
	        throw new HotelException("Error fetching All hotel: " + e.getMessage());
	    }
	
		return hotel;
	}
	

	@Override
	public List<Hotel> getAllHotels() throws HotelException {
		List<Hotel> hotel = new ArrayList<>();
        String query = "SELECT * FROM hotel";
	    
	    try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(query);
	      
	        try (ResultSet rs = ps.executeQuery()) {
	            System.out.println("+-------+----------------+-------------+--------------+-----------------+");
	            System.out.println("| HID   | HotelName      | City        | TotalRooms   | AvailableRooms  |");
	            System.out.println("+-------+----------------+-------------+--------------+-----------------+");

	            while(rs.next()) {
	                int hId = rs.getInt("hotelId"); 
	                String hName = rs.getString("hotelName");
	                String city = rs.getString("city");
	                int trooms = rs.getInt("totalRooms");
	                int arooms = rs.getInt("availableRooms");

	                Hotel g1 = new Hotel(hId,hName,city,trooms,arooms);
	                hotel.add(g1); 
	                
	                System.out.printf("| %-5d | %-14s | %-11s | %-12s | %-15s |\n", hId,hName,city,trooms,arooms);
	                System.out.println("+-------+----------------+-------------+--------------+-----------------+");
	            } 
	        }

	    } catch (SQLException e) {
	        throw new HotelException("Error fetching All hotel: " + e.getMessage());
	    }
	
		return hotel;
	}

	

	@Override
	public int countHotelsByCity(String city) throws HotelException {
	    int count = 0;
	    String query = "SELECT COUNT(*) FROM hotel WHERE city = ?";

	    try (Connection conn = DataBaseConnectvity.getInstance().getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        
	        ps.setString(1, city);
	        
	        try (ResultSet resultSet = ps.executeQuery()) {
	            if (resultSet.next()) {
	                count = resultSet.getInt(1);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return count;
	}


}
