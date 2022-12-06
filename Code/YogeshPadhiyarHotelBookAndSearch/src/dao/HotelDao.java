package dao;

import model.Hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelDao {
    private static HotelDao hotelDao = null;
    private HashMap<String, List<Hotel>> listOfAllHotel;

    public HotelDao() {
        this.listOfAllHotel = new HashMap<>();
    }

    public static HotelDao getInstance(){
        if(hotelDao==null){
            hotelDao = new HotelDao();
        }
        return hotelDao;
    }

    public void addHotel(Hotel hotel) {
        List<Hotel> ownersHotel;
        if(listOfAllHotel.containsKey(hotel.getOwner())){
            ownersHotel = listOfAllHotel.get(hotel.getOwner());
        }else ownersHotel = new ArrayList<>();
        ownersHotel.add(hotel);
        listOfAllHotel.put(hotel.getOwner(), ownersHotel);
    }

    public List<Hotel> getHotel(String owner) {
        return listOfAllHotel.getOrDefault(owner, new ArrayList<>());
    }

    public List<Hotel> getAllHotel() {
        List<Hotel> allHotels = new ArrayList<>();
        for (List<Hotel> hotels : listOfAllHotel.values()){
            allHotels.addAll(hotels);
        }
        return allHotels;
    }
}
