package com.klimczak.rentalapplication.application.hotelbookinghistory;

import com.klimczak.rentalapplication.domain.hotelbookinghistory.HotelBookingHistory;
import com.klimczak.rentalapplication.domain.hotelbookinghistory.HotelBookingHistoryRepository;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoomBooked;
import org.springframework.context.event.EventListener;

public class HotelBookingHistoryEventListener {
    private final HotelBookingHistoryRepository hotelBookingHistoryRepository;

    public HotelBookingHistoryEventListener(HotelBookingHistoryRepository hotelBookingHistoryRepository) {
        this.hotelBookingHistoryRepository = hotelBookingHistoryRepository;
    }

    @EventListener
    public void consume(HotelRoomBooked hotelRoomBooked) {
        HotelBookingHistory hotelBookingHistory = getHotelBookingHistory(hotelRoomBooked.getHotelId());

        hotelBookingHistory.add(
                hotelRoomBooked.getHotelRoomId(), hotelRoomBooked.getEventCreationDateTime(), hotelRoomBooked.getTenantId(),
                hotelRoomBooked.getDays());

        hotelBookingHistoryRepository.save(hotelBookingHistory);
    }

    private HotelBookingHistory getHotelBookingHistory(String hotelId) {
        if (hotelBookingHistoryRepository.existsFor(hotelId)) {
            return hotelBookingHistoryRepository.findFor(hotelId);
        } else {
            return new HotelBookingHistory(hotelId);
        }
    }
}
