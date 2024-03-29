package com.klimczak.rentalapplication.application.hotel;

import com.klimczak.rentalapplication.domain.hotel.Hotel;
import com.klimczak.rentalapplication.domain.hotel.HotelFactory;
import com.klimczak.rentalapplication.domain.hotel.HotelRepository;
import org.springframework.stereotype.Service;

@Service
public class HotelApplicationService {

    private final HotelRepository hotelRepository;

    public HotelApplicationService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void add(String name, String street, String postalCode, String buildingNumber, String city, String country) {
        Hotel hotel = new HotelFactory().create(name, street, postalCode, buildingNumber, city, country);

        hotelRepository.save(hotel);
    }
}
