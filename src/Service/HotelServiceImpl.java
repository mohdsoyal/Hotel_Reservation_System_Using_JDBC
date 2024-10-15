package Service;

import java.util.List;

import Dao.HotelDao;
import Dao.HotelDaoImpl;
import Exception.HotelException;
import Model.Hotel;

public class HotelServiceImpl  implements HotelService{
	
	HotelDao hotelDao = new HotelDaoImpl();

	@Override
	public void addHotel(Hotel hotel) throws HotelException {
		hotelDao.addHotel(hotel);
		
		
	}

	@Override
	public List<Hotel> getHotelsByCity(String city) throws HotelException {
		// TODO Auto-generated method stub
		return hotelDao.getHotelsByCity(city);
	}

	@Override
	public List<Hotel> getAllHotels() throws HotelException {
		// TODO Auto-generated method stub
		return hotelDao.getAllHotels();
	}

	@Override
	public int countHotelsByCity(String city) throws HotelException {
		
		return hotelDao.countHotelsByCity(city);
	}

}
