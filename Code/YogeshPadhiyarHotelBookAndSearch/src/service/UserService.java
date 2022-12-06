package service;

import model.Booking;
import model.Hotel;
import model.TimeSlot;

import java.util.Date;
import java.util.List;

public interface UserService {
    void onBoardHotel(String title, String city, int rating, List<String> rootType, String owner);
    void addInventory(String title, String room, int price, int rooms_available, String owner);
    void updateInventory(String title, int updateRooms);
    int bookingHotel(String title, String roomType, Date checkIn, Date checkOut, String person);
    void cancelBooking(int bookingId);
    List<Booking> viewBooking(String username);

    List<Hotel> findHotels(String sort);
}
