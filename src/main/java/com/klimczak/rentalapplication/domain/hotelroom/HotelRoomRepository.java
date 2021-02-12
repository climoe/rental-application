package com.klimczak.rentalapplication.domain.hotelroom;

public interface HotelRoomRepository {
    String save(HotelRoom hotelRoom);

    HotelRoom findById(String id);
}
