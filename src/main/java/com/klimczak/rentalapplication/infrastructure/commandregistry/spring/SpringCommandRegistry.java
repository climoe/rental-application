package com.klimczak.rentalapplication.infrastructure.commandregistry.spring;

import com.klimczak.rentalapplication.application.booking.BookingAccept;
import com.klimczak.rentalapplication.application.booking.BookingReject;
import com.klimczak.rentalapplication.application.commandregistry.CommandRegistry;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class SpringCommandRegistry implements CommandRegistry {

    private final ApplicationEventPublisher publisher;

    SpringCommandRegistry(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void register(BookingReject bookingReject) {
        publisher.publishEvent(bookingReject);
    }

    @Override
    public void register(BookingAccept bookingAccept) {
        publisher.publishEvent(bookingAccept);
    }
}
