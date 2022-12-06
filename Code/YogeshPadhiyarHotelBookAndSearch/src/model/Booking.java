package model;

public class Booking {
    int bookingId;
    String person;
    String hotelName;
    String roomType;
    TimeSlot timeSlot;

    public Booking(int bookingId,String person, String hotelName,String roomType, TimeSlot timeSlot) {
        this.bookingId = bookingId;
        this.person = person;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.timeSlot = timeSlot;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", person='" + person + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", timeSlot=" + timeSlot +
                '}';
    }
}
