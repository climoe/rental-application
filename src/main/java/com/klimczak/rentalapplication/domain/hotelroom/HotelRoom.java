package com.klimczak.rentalapplication.domain.hotelroom;

import com.klimczak.rentalapplication.domain.apartment.Booking;
import com.klimczak.rentalapplication.domain.eventchannel.EventChannel;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoom {
    @Id
    @GeneratedValue
    private UUID id;
    private String hotelId;
    private int number;

    @ElementCollection
    private List<Space> spaces;

    private String description;

    private HotelRoom() {}

    HotelRoom(String hotelId, int number, List<Space> spaces, String description) {
        this.hotelId = hotelId;
        this.number = number;
        this.spaces = spaces;
        this.description = description;
    }

    public Booking book(String tenantId, List<LocalDate> days, EventChannel eventChannel) {
        HotelRoomBooked hotelRoomBooked = HotelRoomBooked.create(id(), hotelId, tenantId, days);
        eventChannel.publish(hotelRoomBooked);

        return Booking.hotelRoom(id(), tenantId, days);
    }

    public String id() {
        return id == null ? null : id.toString();
    }
}
