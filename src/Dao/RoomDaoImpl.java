package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Exception.GuestException;
import Exception.RoomException;
import Model.Guest;
import Model.Room;
import Utility.DataBaseConnectvity;

public class RoomDaoImpl implements RoomDao {

	@Override
	public void addRoom(Room room) throws RoomException {
		String query = "insert into room (roomType , roomPrice , roomStatus ,hotelId) values (?,?,'Available',?)";

		try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, room.getRoomType());
			ps.setString(2, room.getRoomPrice());
			ps.setInt(3, room.getHotelId());
		
			
			int result = ps.executeUpdate();
			if (result <= 0) {
				throw new GuestException("Failed to add the Room.");
			}
		} catch (SQLException e) {
			throw new GuestException("Error while adding the bus: " + e.getMessage());
		}
	}

	@Override
	public void updateRoomStatus(int roomid, String status) throws RoomException {
		
	}
	@Override
	public Room getRoomByHotelId(int hotelId) throws RoomException {
		Room room = null;
		String query = "SELECT * FROM room WHERE hotelId = ?";

		try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, hotelId);

			try (ResultSet rs = ps.executeQuery()) {
				System.out.println("+----------+-----------------+-------------------+----------------+");
				System.out.println("| RoomID   | RoomType        | RoomPrice         | RoomStatus     |");
				System.out.println("+----------+-----------------+-------------------+----------------+");

				if (rs.next()) {
					int romId = rs.getInt("roomId");
					String roomType = rs.getString("roomType");
					String roomPrice = rs.getString("roomPrice");
					String roomStatus = rs.getString("roomStatus");
					int hId = rs.getInt("hotelId");

					room = new Room(romId, roomType, roomPrice, roomStatus,hId);

					System.out.printf("| %-8d | %-15s | %-17s | %-14s |\n", romId, roomType, roomPrice, roomStatus ,hId);
					System.out.println("+----------+-----------------+-------------------+----------------+");
				} else {
					throw new RoomException("No room found with ID: " + hotelId);
				}
			}
		} catch (SQLException e) {
			throw new RoomException("Error fetching room with ID " + hotelId + ": " + e.getMessage());
		}

		return room;
	}

	@Override
	public List<Room> getAllRoom() throws RoomException {
		List<Room> room = new LinkedList<Room>();
		String query = "SELECT * FROM room";

		try (Connection conn = DataBaseConnectvity.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(query);

			try (ResultSet rs = ps.executeQuery()) {
				System.out.println("+----------+-----------------+-------------------+----------------+");
				System.out.println("| RoomID   | RoomType        | RoomPrice         | RoomStatus     |");
				System.out.println("+----------+-----------------+-------------------+----------------+");

				while (rs.next()) {
					int romId = rs.getInt("roomId");
					String roomType = rs.getString("roomType");
					String roomPrice = rs.getString("roomPrice");
					String roomStatus = rs.getString("roomStatus");
					int hId = rs.getInt("hotelId");

					Room g1 = new Room(romId, roomType, roomPrice, roomStatus , hId);
					room.add(g1);

					System.out.printf("| %-8d | %-15s | %-17s | %-14s |\n", romId, roomType, roomPrice, roomStatus,hId);
					System.out.println("+----------+-----------------+-------------------+----------------+");
				}
			}
		} catch (SQLException e) {
			throw new RoomException("Error fetching room with ID " + e.getMessage());
		}

		return room;
	}

}
