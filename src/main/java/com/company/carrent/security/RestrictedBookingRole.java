package com.company.carrent.security;

import com.company.carrent.entity.Booking;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "RestrictedBookingRole", code = RestrictedBookingRole.CODE)
public interface RestrictedBookingRole {
    String CODE = "restricted-booking-role";

    @JpqlRowLevelPolicy(entityClass = Booking.class, where = "{E}.customer.username=:current_user_username")
    void booking();
}