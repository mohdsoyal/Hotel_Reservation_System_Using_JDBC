package Service;

import java.util.List;

import Dao.BookDao;
import Dao.BookDaoImpl;
import Exception.BookException;
import Model.Book;

public class BookServiceImpl implements BookService{

	
	BookDao bookDao= new BookDaoImpl();
	@Override
	public void bookRoom(Book book) throws BookException {
		bookDao.bookRoom(book);
		
	}

	@Override
	public List<Book> getBookingByGuestId(int guestId) throws BookException {
		
		return bookDao.getBookingByGuestId(guestId);
	}

	@Override
	public void cancelBooking(int roomId  ,int hotelId) throws BookException {
		bookDao.cancelBooking(roomId , hotelId);
		
	}

}
