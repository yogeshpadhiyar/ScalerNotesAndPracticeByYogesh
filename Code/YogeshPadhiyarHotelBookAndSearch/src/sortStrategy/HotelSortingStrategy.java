package sortStrategy;

import model.Hotel;

import java.util.List;

public interface HotelSortingStrategy {
    public List<Hotel> findHotels(List<Hotel> allHotels);
}
