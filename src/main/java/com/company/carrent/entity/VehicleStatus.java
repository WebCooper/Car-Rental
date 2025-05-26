package com.company.carrent.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum VehicleStatus implements EnumClass<String> {

    AVAILABLE("A"),
    BOOKED("B"),
    MAINTENANCE("C");

    private final String id;

    VehicleStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VehicleStatus fromId(String id) {
        for (VehicleStatus at : VehicleStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}