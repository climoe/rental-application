package com.klimczak.rentalapplication.infrastructure.persistence.jpa.hotelroom;

import com.klimczak.rentalapplication.domain.hotelroom.HotelRoom;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaHotelRoomRepository implements HotelRoomRepository {
    private final SpringJpaHotelRoomRepository hotelRoomRepository;

    JpaHotelRoomRepository(SpringJpaHotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    @Override
    public String save(HotelRoom hotelRoom) {
        return hotelRoomRepository.save(hotelRoom).id();
    }

    @Override
    public HotelRoom findById(String id) {
        return hotelRoomRepository.findById(UUID.fromString(id)).orElseThrow(() -> new HotelRoomDoesNotExistException(id));
    }
}
