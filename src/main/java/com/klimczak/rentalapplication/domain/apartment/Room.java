package com.klimczak.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
class Room {
    private String name;

    @Embedded
    private SquareMeter squareMeter;

    private Room() {}

    Room(String name, SquareMeter squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }
}
