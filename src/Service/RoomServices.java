package Service;

import java.util.List;

import Exception.RoomException;
import Model.Room;

public interface RoomServices {
	
    void addRoom(Room room) throws RoomException;
	
	void updateRoomStatus(int roomid , String status) throws RoomException;
	
	Room getRoomByHotelId(int hotelId) throws RoomException;
	
	List<Room> getAllRoom() throws RoomException ;

}
