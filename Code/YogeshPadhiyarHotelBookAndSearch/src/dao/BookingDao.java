package dao;

import model.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingDao {
    private static BookingDao bookingDao = null;
    private HashMap<Integer, Booking> listOfAllBooking;

    public BookingDao() {
        this.listOfAllBooking =new HashMap<>();
    }

    public static BookingDao getInstance(){
        if(bookingDao==null){
            bookingDao = new BookingDao();
        }
        return bookingDao;
    }

    public void bookRoom(Booking book) {
        listOfAllBooking.put(book.getBookingId(), book);
    }

    public Booking cancelBooking(int bookingId) {
        Booking booking = listOfAllBooking.get(bookingId);
        listOfAllBooking.remove(bookingId);
        return booking;
    }

}
