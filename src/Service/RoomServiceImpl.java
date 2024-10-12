package Service;

import java.util.List;

import Dao.RoomDao;
import Dao.RoomDaoImpl;
import Exception.RoomException;
import Model.Room;

public class RoomServiceImpl implements RoomServices{

	RoomDao  roomDao = new RoomDaoImpl();
	@Override
	public void addRoom(Room room) throws RoomException {
		roomDao.addRoom(room);
		
	}

	@Override
	public void updateRoomStatus(int roomid, String status) throws RoomException {
		roomDao.updateRoomStatus(roomid, status);
		
	}

	@Override
	public Room getRoomById(int roomId) throws RoomException  {
		
		return roomDao.getRoomById(roomId);
	}

	@Override
	public List<Room> getAllRoom() throws RoomException {
		
		return roomDao.getAllRoom();
	}

}
