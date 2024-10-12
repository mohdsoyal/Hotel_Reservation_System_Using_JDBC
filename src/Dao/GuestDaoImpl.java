package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Exception.GuestException;
import Model.Guest;
import Utility.DataBaseConnectvity;


public class GuestDaoImpl  implements GuestDao{

	@Override
	public void addGuest(Guest guest) {
		String Query = "Insert into Guest (gName , gEmail , gPhone , gAddress) values (?,?,?,?)";
		
		try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setString(1, guest.getgName());
            ps.setString(2, guest.getgEmail());
            ps.setString(3, guest.getgPhone());
            ps.setString(4, guest.getgAddress());
           
            int result = ps.executeUpdate();
            if (result <= 0) {
                throw new GuestException("Failed to add the bus.");
            }
        } catch (SQLException e) {
            throw new GuestException("Error while adding the bus: " + e.getMessage());
        }	
	}

	@Override
	public void updateGuest(Guest guest) {
		 String query = "UPDATE Guest SET gName=?, gEmail=? , gPhone=? , gAddress=? WHERE gId=?";
	        
	        try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
	            PreparedStatement ps = conn.prepareStatement(query);
	            
	            ps.setString(1, guest.getgName());
	            ps.setString(2, guest.getgEmail());
	            ps.setString(3, guest.getgPhone());
	            ps.setString(4, guest.getgAddress());
	            ps.setInt(5, guest.getgId());  
	            
	            int result = ps.executeUpdate();
	            
	            if (result > 0) {
	                System.out.println("Guest updated successfully!");
	            } else {
	                throw new GuestException("Bus not found with ID: " + guest.getgId());
	            }
	            
	        } catch (SQLException e) {
	            throw new GuestException("Error updating bus times: " + e.getMessage());
	        }
		
	}

	@Override
	public Guest getGuestById(int guestId) {
	    Guest guest = null; 
	    String query = "SELECT * FROM Guest WHERE gId = ?";
	    
	    try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, guestId);

	        try (ResultSet rs = ps.executeQuery()) {
	            System.out.println("+-------+--------------+-------------------+------------+------------+");
	            System.out.println("| gID   | gName        | gEmail            | gPhone     | gAddress   |");
	            System.out.println("+-------+--------------+-------------------+------------+------------+");

	            
	            if (rs.next()) {
	                int gId = rs.getInt("gId"); 
	                String gName = rs.getString("gName");
	                String gEmail = rs.getString("gEmail");
	                String gPhone = rs.getString("gPhone");
	                String gAddress = rs.getString("gAddress");

	                guest = new Guest(gId, gName, gEmail, gPhone, gAddress);
	                
	                System.out.printf("| %-5d | %-12s | %-8s | %-8s | %-10s |\n", gId, gName, gEmail, gPhone, gAddress);
	                System.out.println("+-------+--------------+-------------------+------------+------------+");
	            } else {
	                throw new GuestException("No guest found with ID: " + guestId);
	            }
	        }

	    } catch (SQLException e) {
	        throw new GuestException("Error fetching guest: " + e.getMessage());
	    }

	    return guest; 
	}


	@Override
	public List<Guest> getAllGuest() {
		List<Guest> guest = new ArrayList<>();
        String query = "SELECT * FROM Guest";
	    
	    try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(query);
	      
	        try (ResultSet rs = ps.executeQuery()) {
	            System.out.println("+-------+--------------+-------------------+------------+------------+");
	            System.out.println("| gID   | gName        | gEmail            | gPhone     | gAddress   |");
	            System.out.println("+-------+--------------+-------------------+------------+------------+");

	            while(rs.next()) {
	                int gId = rs.getInt("gId"); 
	                String gName = rs.getString("gName");
	                String gEmail = rs.getString("gEmail");
	                String gPhone = rs.getString("gPhone");
	                String gAddress = rs.getString("gAddress");

	                Guest g1 = new Guest(gId, gName, gEmail, gPhone, gAddress);
	                guest.add(g1); 
	                
	                System.out.printf("| %-5d | %-12s | %-8s | %-8s | %-10s |\n", gId, gName, gEmail, gPhone, gAddress);
	                System.out.println("+-------+--------------+-------------------+------------+------------+");
	            } 
	        }

	    } catch (SQLException e) {
	        throw new GuestException("Error fetching All guest: " + e.getMessage());
	    }
	
		return guest;
	}

	@Override
	public void deleteGuest(int guestId) {
		String delete = "DELETE FROM Guest WHERE gId = ?";
	    Guest guest = null;

	    try (Connection conn = DataBaseConnectvity.getInstance().getConnection();
	         PreparedStatement delGuest = conn.prepareStatement(delete)) {

	        delGuest.setInt(1,guestId);
	        int rowsAffected = delGuest.executeUpdate();

	        if (rowsAffected > 0) {
	            guest = new Guest();  
	            guest.setgId(guestId); 
	            System.out.println("Guest with ID " + guestId + " Deleted Successfully.");
	        } else {
	            System.out.println("No found  Guest  with ID " + guestId + " Deletion failed.");
	        }

	    } catch (SQLException e) {
	        throw new GuestException("Error deleting car: " + e.getMessage());
	    } 
	}
		
	}


