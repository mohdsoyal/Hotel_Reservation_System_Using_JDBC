package Dao;

import java.util.List;

import Exception.BookException;
import Model.Book;

public interface BookDao {
	
	void bookRoom(Book book) throws BookException;
	
	List<Book> getBookingByGuestId(int guestId) throws BookException;
	
	void cancelBooking(int bookingId) throws BookException;
	
}

