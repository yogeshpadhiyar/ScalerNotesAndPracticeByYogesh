package model;

public class Room {
    int price;
    String roomType;
    int room_available;

    public Room(String roomType) {
        //Default price of room if inventory not added
        this.price = 1000;
        this.roomType = roomType;
        this.room_available=0;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoom_available() {
        return room_available;
    }

    public void setRoom_available(int room_available) {
        this.room_available = room_available;
    }
}
