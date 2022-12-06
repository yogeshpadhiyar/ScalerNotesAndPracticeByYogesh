package service;

import dao.BookingDao;
import dao.HotelDao;
import dao.UserDao;
import model.*;
import sortStrategy.HotelSortingStrategy;
import sortStrategy.PriceStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserServiceImpl implements UserService{
    private int lastBookingId = 1;
    HotelDao hotelDao;
    BookingDao bookingDao;
    UserDao userDao;

    public UserServiceImpl() {
        this.hotelDao = HotelDao.getInstance();
        this.userDao = UserDao.getInstance();
        this.bookingDao = BookingDao.getInstance();
    }

    @Override
    public void onBoardHotel(String title, String city, int rating, List<String> roomType, String owner) {
        Hotel hotel = new Hotel(title,city,rating,roomType,owner);
        hotelDao.addHotel(hotel);
        User user = userDao.getUser(owner);
        user.getHotels().add(hotel);
    }

    @Override
    public void addInventory(String title, String room, int price, int rooms_available, String owner) {
        List<Hotel> ownerHotels = hotelDao.getHotel(owner);
        if(!ownerHotels.isEmpty()){
            for (Hotel h : ownerHotels) {
                if(h.getTitle().equals(title)){
                    h.setPrice(price);
                    h.setRoom_available(rooms_available);
                    break;
                }
            }
        }
    }

    @Override
    public void updateInventory(String title, int updateRooms) {
        List<Hotel> allHotels = hotelDao.getAllHotel();
        for (Hotel hotel : allHotels){
            if(hotel.getTitle().equals(title)){
                if(updateRooms>0){
                    hotel.setRoom_available(hotel.getRoom_available()-updateRooms);
                }else hotel.setRoom_available(hotel.getRoom_available() + updateRooms);
                break;
            }
        }
    }

    @Override
    public int bookingHotel(String title, String roomType, Date checkIn, Date checkOut, String person) {
        int bookingId = lastBookingId;
        lastBookingId++;
        Booking book  = new Booking(bookingId, person,title,roomType,new TimeSlot(checkIn,checkOut));
        bookingDao.bookRoom(book);
        updateInventory(title, 1);
        User user = userDao.getUser(person);
        user.getBookingList().add(book);
        return bookingId;
    }

    @Override
    public void cancelBooking(int bookingId) {
        Booking booking = bookingDao.cancelBooking(bookingId);
        User user = userDao.getUser(booking.getPerson());
        Iterator<Booking> userBookingList = user.getBookingList().iterator();
        while (userBookingList.hasNext()){
            Booking b = userBookingList.next();
            if(b.getBookingId()==bookingId){
                userBookingList.remove();
                break;
            }
        }
        updateInventory(booking.getHotelName(), -1);
    }

    @Override
    public List<Booking> viewBooking(String username) {
        User user = userDao.getUser(username);
        return user.getBookingList();
    }

    @Override
    public List<Hotel> findHotels(String sort) {
        HotelSortingStrategy strategy = null;
        if(sort.equals("price")){
            strategy = new PriceStrategy();
        }
        return strategy.findHotels(hotelDao.getAllHotel());
    }
}
