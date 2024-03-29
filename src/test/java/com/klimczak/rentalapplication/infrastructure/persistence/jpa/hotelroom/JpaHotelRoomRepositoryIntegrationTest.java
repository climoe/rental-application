package com.klimczak.rentalapplication.infrastructure.persistence.jpa.hotelroom;

import com.google.common.collect.ImmutableMap;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoom;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoomAssertion;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoomFactory;
import com.klimczak.rentalapplication.domain.hotelroom.HotelRoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JpaHotelRoomRepositoryIntegrationTest {
    private static final String HOTEL_ID = "5678";
    private static final int ROOM_NUMBER = 42;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION = ImmutableMap.of("Room1", 30.0);
    private static final String DESCRIPTION = "This is very nice place";

    @Autowired private HotelRoomRepository hotelRoomRepository;

    @Test
    void shouldThrowExceptionWhenNoHotelRoomFound() {
        String id = UUID.randomUUID().toString();

        HotelRoomDoesNotExistException actual = assertThrows(HotelRoomDoesNotExistException.class, () -> hotelRoomRepository.findById(id));

        assertThat(actual).hasMessage("Hotel Room with id " + id + " does not exist.");
    }

    @Test
    @Transactional
    void shouldFindExistingHotelRoom() {
        HotelRoom hotelRoom = createHotelRoom();
        String hotelRoomId = hotelRoomRepository.save(hotelRoom);

        HotelRoom actual = hotelRoomRepository.findById(hotelRoomId);

        HotelRoomAssertion.assertThat(actual)
                .hasHotelIdEqualTo(HOTEL_ID)
                .hasRoomNumberEqualTo(ROOM_NUMBER)
                .hasSpacesDefinitionEqualTo(SPACES_DEFINITION)
                .hasDescriptionEqualTo(DESCRIPTION);
    }

    private HotelRoom createHotelRoom() {
        return new HotelRoomFactory().create(HOTEL_ID, ROOM_NUMBER, SPACES_DEFINITION, DESCRIPTION);
    }
}