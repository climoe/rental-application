package com.klimczak.rentalapplication.application.commandregistry;

import com.klimczak.rentalapplication.application.booking.BookingAccept;
import com.klimczak.rentalapplication.application.booking.BookingReject;

public interface CommandRegistry {
    void register(BookingReject bookingReject);

    void register(BookingAccept bookingAccept);
}
