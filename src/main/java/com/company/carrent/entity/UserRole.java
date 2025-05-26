package com.company.carrent.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum UserRole implements EnumClass<String> {

    STAFF("A"),
    CUSTOMER("B"),
    ADMIN("C");

    private final String id;

    UserRole(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static UserRole fromId(String id) {
        for (UserRole at : UserRole.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}