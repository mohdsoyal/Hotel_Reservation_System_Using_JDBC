package Service;

import java.util.List;

import Exception.BookException;
import Model.Book;

public interface BookService {

    void bookRoom(Book book) throws BookException;
	
	List<Book> getBookingByGuestId(int guestId) throws BookException;
	
	void cancelBooking(int roomId) throws BookException;
}
