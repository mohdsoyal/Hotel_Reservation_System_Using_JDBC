package Model;

public class Book {
	
	private int    BookingId ;
	private int    GuestId;
	private int    RoomId;
	private String BookingDate;
	private String CheckIN;
	private String CheckOut;
	private int hotelId;
	
	public int getBookingId() {
		return BookingId;
	}
	public void setBookingId(int bookingId) {
		BookingId = bookingId;
	}
	public int getGuestId() {
		return GuestId;
	}
	public void setGuestId(int guestId) {
		GuestId = guestId;
	}
	public int getRoomId() {
		return RoomId;
	}
	public void setRoomId(int roomId) {
		RoomId = roomId;
	}
	public String getBookingDate() {
		return BookingDate;
	}
	public void setBookingDate(String bookingDate) {
		BookingDate = bookingDate;
	}
	public String getCheckIN() {
		return CheckIN;
	}
	public void setCheckIN(String checkIN) {
		CheckIN = checkIN;
	}
	public String getCheckOut() {
		return CheckOut;
	}
	public void setCheckOut(String checkOut) {
		CheckOut = checkOut;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public Book(int bookingId, int guestId, int roomId, String bookingDate, String checkIN, String checkOut,
			int hotelId) {
		super();
		BookingId = bookingId;
		GuestId = guestId;
		RoomId = roomId;
		BookingDate = bookingDate;
		CheckIN = checkIN;
		CheckOut = checkOut;
		this.hotelId = hotelId;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Book [BookingId=" + BookingId + ", GuestId=" + GuestId + ", RoomId=" + RoomId + ", BookingDate="
				+ BookingDate + ", CheckIN=" + CheckIN + ", CheckOut=" + CheckOut + ", hotelId=" + hotelId + "]";
	}  
	
	
	
}
