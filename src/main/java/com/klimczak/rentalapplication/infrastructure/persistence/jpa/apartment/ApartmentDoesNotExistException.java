package com.klimczak.rentalapplication.infrastructure.persistence.jpa.apartment;

class ApartmentDoesNotExistException extends RuntimeException {

    ApartmentDoesNotExistException(String id) {
        super("Apartment with id " + id + " does not exist");
    }
}
