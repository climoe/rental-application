package com.klimczak.rentalapplication.domain.apartment;

import com.google.common.collect.ImmutableMap;
import com.klimczak.rentalapplication.domain.eventchannel.EventChannel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Map;

class ApartmentTest {
    private static final String OWNER_ID = "1234";
    private static final String STREET = "Florianska";
    private static final String POSTAL_CODE = "12-345";
    private static final String HOUSE_NUMBER = "1";
    private static final String APARTMENT_NUMBER = "13";
    private static final String CITY = "Cracow";
    private static final String COUNTRY = "Poland";
    private static final String DESCRIPTION = "Nice place to stay";
    private static final Map<String, Double> ROOMS_DEFINITION = ImmutableMap.of("Toilet", 10.0, "Bedroom", 30.0);
    private static final String TENANT_IT = "137";
    private static final LocalDate START = LocalDate.of(2020, 3, 4);
    private static final LocalDate MIDDLE = LocalDate.of(2020, 3, 5);
    private static final LocalDate END = LocalDate.of(2020, 3, 6);
    private static final Period PERIOD = new Period(START, END);

    private final ApartmentFactory apartmentFactory = new ApartmentFactory();
    private final EventChannel eventChannel = Mockito.mock(EventChannel.class);

    @Test
    void shouldCreateApartmentWithAllRequiredFields() {
        Apartment actual = createApartment();

        ApartmentAssertion.assertThat(actual)
                .hasOwnerIdEqualsTo(OWNER_ID)
                .hasDescriptionEqualsTo(DESCRIPTION)
                .hasAddressEqualsTo(STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY)
                .hasRoomsEqualsTo(ROOMS_DEFINITION);
    }

    @Test
    void shouldCreateBookingOnceBooked() {
        Apartment apartment = createApartment();

        Booking actual = apartment.book(TENANT_IT, PERIOD, eventChannel);

        BookingAssertion.assertThat(actual)
                .isApartment()
                .hasTenantIdEqualTo(TENANT_IT)
                .containsAllDays(START, MIDDLE, END);
    }

    @Test
    void shouldPublishApartmentBooked() {
        ArgumentCaptor<ApartmentBooked> captor = ArgumentCaptor.forClass(ApartmentBooked.class);
        Apartment apartment = createApartment();

        apartment.book(TENANT_IT, PERIOD, eventChannel);

        BDDMockito.then(eventChannel).should().publish(captor.capture());
        ApartmentBooked actual = captor.getValue();
        Assertions.assertThat(actual.getTenantId()).isEqualTo(TENANT_IT);
        Assertions.assertThat(actual.getOwnerId()).isEqualTo(OWNER_ID);
        Assertions.assertThat(actual.getPeriodStart()).isEqualTo(START);
        Assertions.assertThat(actual.getPeriodEnd()).isEqualTo(END);
    }

    private Apartment createApartment() {
        return apartmentFactory.create(
                OWNER_ID, STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY,
                DESCRIPTION, ROOMS_DEFINITION);
    }
}