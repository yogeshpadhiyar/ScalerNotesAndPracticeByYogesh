package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    List<Hotel> hotels;
    List<Booking> bookingList;

    public User(String name) {
        this.name = name;
        this.hotels = new ArrayList<>();
        this.bookingList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
