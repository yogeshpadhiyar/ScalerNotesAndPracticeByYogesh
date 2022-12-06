package model;

import java.util.List;

public class Hotel {
    String title;
    String city;
    int rating;
    List<String> rooms;
    int price;
    String owner;
    int room_available;

    public Hotel(String title, String city, int rating, List<String> rooms, String owner) {
        this.title = title;
        this.city = city;
        this.rating = rating;
        this.rooms = rooms;
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoom_available() {
        return room_available;
    }

    public void setRoom_available(int room_available) {
        this.room_available = room_available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "title='" + title + '\'' +
                ", city='" + city + '\'' +
                ", rating=" + rating +
                ", rooms=" + rooms +
                ", price=" + price +
                ", owner='" + owner + '\'' +
                ", room_available=" + room_available +
                '}';
    }
}
