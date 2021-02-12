package com.klimczak.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.klimczak.rentalapplication.domain.hotel.Hotel;
import com.klimczak.rentalapplication.domain.hotel.HotelRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaHotelRepository implements HotelRepository {
    private final SpringJpaHotelRepository hotelRepository;

    JpaHotelRepository(SpringJpaHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
    }
}
