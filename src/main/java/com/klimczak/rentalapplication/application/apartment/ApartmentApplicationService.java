package com.klimczak.rentalapplication.application.apartment;

import com.klimczak.rentalapplication.domain.apartment.Apartment;
import com.klimczak.rentalapplication.domain.apartment.ApartmentFactory;
import com.klimczak.rentalapplication.domain.apartment.ApartmentRepository;
import com.klimczak.rentalapplication.domain.apartment.Booking;
import com.klimczak.rentalapplication.domain.apartment.BookingRepository;
import com.klimczak.rentalapplication.domain.apartment.Period;
import com.klimczak.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class ApartmentApplicationService {

    private final ApartmentRepository apartmentRepository;
    private final EventChannel eventChannel;
    private final BookingRepository bookingRepository;

    public ApartmentApplicationService(ApartmentRepository apartmentRepository,
                                       EventChannel eventChannel,
                                       BookingRepository bookingRepository) {
        this.apartmentRepository = apartmentRepository;
        this.eventChannel = eventChannel;
        this.bookingRepository = bookingRepository;
    }

    public String add(
            String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
            String city, String country, String description, Map<String, Double> roomsDefinition) {

        Apartment apartment = ApartmentFactory.create(
                ownerId, street, postalCode, houseNumber, apartmentNumber, city, country, description, roomsDefinition);

        return apartmentRepository.save(apartment);
    }

    public void book(String id, String tenantId, LocalDate start, LocalDate end) {
        Apartment apartment = apartmentRepository.findById(id);
        Period period = new Period(start, end);

        Booking booking = apartment.book(tenantId, period, eventChannel);

        bookingRepository.save(booking);
    }
}
