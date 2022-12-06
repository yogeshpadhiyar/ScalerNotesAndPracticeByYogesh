package sortStrategy;

import model.Hotel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriceStrategy implements HotelSortingStrategy{
    @Override
    public  List<Hotel> findHotels(List<Hotel> allHotels) {
        Collections.sort(allHotels, Comparator.comparingInt(Hotel::getPrice));
        return allHotels;
    }
}
