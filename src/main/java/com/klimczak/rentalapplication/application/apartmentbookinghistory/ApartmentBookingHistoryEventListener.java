package com.klimczak.rentalapplication.application.apartmentbookinghistory;

import com.klimczak.rentalapplication.domain.apartment.ApartmentBooked;
import com.klimczak.rentalapplication.domain.apartmentbookinghistory.ApartmentBooking;
import com.klimczak.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.klimczak.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import com.klimczak.rentalapplication.domain.apartmentbookinghistory.BookingPeriod;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApartmentBookingHistoryEventListener {
    private final ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

    public ApartmentBookingHistoryEventListener(ApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
        this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
    }

    @EventListener
    public void consume(ApartmentBooked apartmentBooked) {
        ApartmentBookingHistory apartmentBookingHistory = getApartmentBookingHistoryFor(apartmentBooked.getApartmentId());
        BookingPeriod bookingPeriod = new BookingPeriod(apartmentBooked.getPeriodStart(), apartmentBooked.getPeriodEnd());

        apartmentBookingHistory.add(ApartmentBooking.start(
                apartmentBooked.getEventCreationDateTime(), apartmentBooked.getOwnerId(), apartmentBooked.getTenantId(), bookingPeriod));

        apartmentBookingHistoryRepository.save(apartmentBookingHistory);
    }

    private ApartmentBookingHistory getApartmentBookingHistoryFor(String apartmentId) {
        if (apartmentBookingHistoryRepository.existsFor(apartmentId)) {
            return apartmentBookingHistoryRepository.findFor(apartmentId);
        } else {
            return new ApartmentBookingHistory(apartmentId);
        }
    }
}
