package Service;

import java.util.List;

import Dao.GuestDao;
import Dao.GuestDaoImpl;
import Model.Guest;

public class GuestServiceImpl  implements GuestService{

	GuestDao guestDao = new GuestDaoImpl();
	@Override
	public void addGuest(Guest guest) {
		guestDao.addGuest(guest);
		
	}

	@Override
	public void updateGuest(Guest guest) {
		guestDao.updateGuest(guest);
		
	}

	@Override
	public Guest getGuestById(int guestId) {
		return guestDao.getGuestById(guestId);
	}

	@Override
	public List<Guest> getAllGuest() {
		return guestDao.getAllGuest();
	}

	@Override
	public void deleteGuest(int guestId) {
		guestDao.deleteGuest(guestId);
		
	}

}
