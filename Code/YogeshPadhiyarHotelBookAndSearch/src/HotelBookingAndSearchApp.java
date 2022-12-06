import model.Booking;
import model.Hotel;
import service.UserService;
import service.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HotelBookingAndSearchApp {
    public static void main(String[] args) throws ParseException {

        UserService userService = new UserServiceImpl();

        List<String> roomType = new ArrayList<>(Arrays.asList("Deluxe", "luxury"));
        userService.onBoardHotel("My Place", "Bangalore", 3,roomType,"tej" );
        System.out.println("OnBoarding Successfully");

        List<String> roomType2 = new ArrayList<>(Arrays.asList("Basic", "Suite", "Villa"));
        userService.onBoardHotel("My Hotel", "Delhi", 5,roomType,"tej" );
        System.out.println("OnBoarding Successfully");

        userService.addInventory("My Place", "Deluxe",3000,3, "tej");
        System.out.println("Price and Inventory added Successfully");

        userService.addInventory("My Hotel", "Basic",2500,2, "tej");
        System.out.println("Price and Inventory added Successfully");


        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        int bookingId = userService.bookingHotel("My Place", "Deluxe", sd.parse("08/11/2022"), sd.parse("09/11/2022"), "raj");
        System.out.println("Congratulations your booking is confirmed. Id - "+bookingId);

        List<Booking> allBooking = userService.viewBooking("raj");
        for (Booking b : allBooking){
            System.out.println(b.toString());
        }

        userService.cancelBooking(bookingId);
        System.out.println(" Canceled successfully Id - "+ bookingId);

        allBooking = userService.viewBooking("raj");
        for (Booking b : allBooking){
            System.out.println(b.toString());
        }

        List<Hotel> allHotels = userService.findHotels("price");
        for (Hotel h : allHotels){
            System.out.println(h);
        }
    }
}
