package com.klimczak.rentalapplication.infrastructure.rest.api.booking;

import com.klimczak.rentalapplication.application.booking.BookingAccept;
import com.klimczak.rentalapplication.application.booking.BookingReject;
import com.klimczak.rentalapplication.application.commandregistry.CommandRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingRestController {
    private final CommandRegistry commandRegistry;

    public BookingRestController(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @PutMapping("/reject/{id}")
    public void reject(@PathVariable String id) {
        commandRegistry.register(new BookingReject(id));
    }

    @PutMapping("/accept/{id}")
    public void accept(@PathVariable String id) {
        commandRegistry.register(new BookingAccept(id));
    }
}
