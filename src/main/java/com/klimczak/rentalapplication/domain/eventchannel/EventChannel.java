package com.klimczak.rentalapplication.domain.eventchannel;

import com.klimczak.rentalapplication.domain.apartment.ApartmentBooked;
import com.klimczak.rentalapplication.domain.apartment.BookingAccepted;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoomBooked;

public interface EventChannel {
    void publish(ApartmentBooked apartmentBooked);

    void publish(HotelRoomBooked hotelRoomBooked);

    void publish(BookingAccepted bookingAccepted);
}
