package com.klimczak.rentalapplication.application.hotelroom;

import com.klimczak.rentalapplication.domain.apartment.Booking;
import com.klimczak.rentalapplication.domain.apartment.BookingRepository;
import com.klimczak.rentalapplication.domain.eventchannel.EventChannel;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoom;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoomFactory;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class HotelRoomApplicationService {

    private final HotelRoomRepository hotelRoomRepository;
    private final BookingRepository bookingRepository;
    private final EventChannel eventChannel;

    public HotelRoomApplicationService(HotelRoomRepository hotelRoomRepository,
                                       BookingRepository bookingRepository,
                                       EventChannel eventChannel) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.bookingRepository = bookingRepository;
        this.eventChannel = eventChannel;
    }

    public void add(String hotelId, int number, Map<String, Double> spacesDefinition, String description) {
        HotelRoom hotelRoom = new HotelRoomFactory().create(hotelId, number, spacesDefinition, description);

        hotelRoomRepository.save(hotelRoom);
    }

    public void book(String id, String tenantId, List<LocalDate> days) {
        HotelRoom hotelRoom = hotelRoomRepository.findById(id);

        Booking booking = hotelRoom.book(tenantId, days, eventChannel);

        bookingRepository.save(booking);
    }
}
