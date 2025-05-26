package com.company.carrent.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum BookingStatus implements EnumClass<String> {

    PENDING("A"),
    BOOKED("B"),
    CANCELLED("C");

    private final String id;

    BookingStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static BookingStatus fromId(String id) {
        for (BookingStatus at : BookingStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}