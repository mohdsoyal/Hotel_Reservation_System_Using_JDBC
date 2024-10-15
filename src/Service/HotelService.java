package Service;

import java.util.List;

import Exception.HotelException;
import Model.Hotel;

public interface HotelService {
	
   public void addHotel(Hotel hotel) throws HotelException;
	
	public List<Hotel> getHotelsByCity(String city) throws HotelException;
	
	public List<Hotel> getAllHotels() throws HotelException;
	
	public int countHotelsByCity(String city) throws HotelException;

}
