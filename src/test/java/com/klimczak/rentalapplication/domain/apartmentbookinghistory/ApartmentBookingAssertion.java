package com.klimczak.rentalapplication.domain.apartmentbookinghistory;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApartmentBookingAssertion extends AbstractAssert<ApartmentBookingAssertion, ApartmentBooking> {


    private ApartmentBookingAssertion(ApartmentBooking actual) {
        super(actual, ApartmentBookingAssertion.class);
    }

    public static ApartmentBookingAssertion assertThat(ApartmentBooking actual) {
        return new ApartmentBookingAssertion(actual);
    }

    ApartmentBookingAssertion hasBookingDateTimeEqualTo(LocalDateTime expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingDateTime", expected);
        return this;
    }

    public ApartmentBookingAssertion hasOwnerIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("ownerId", expected);
        return this;
    }

    public ApartmentBookingAssertion hasTenantIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("tenantId", expected);
        return this;
    }

    public ApartmentBookingAssertion hasBookingPeriodThatHas(LocalDate expectedStart, LocalDate expectedEnd) {
        Assertions.assertThat(actual)
                .hasFieldOrPropertyWithValue("bookingPeriod.periodStart", expectedStart)
                .hasFieldOrPropertyWithValue("bookingPeriod.periodEnd", expectedEnd);
        return this;
    }

    public ApartmentBookingAssertion isStart() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStep", BookingStep.START);
        return this;
    }
}
