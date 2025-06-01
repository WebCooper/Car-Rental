package com.company.carrent.view.booking;

import com.company.carrent.entity.Booking;
import com.company.carrent.entity.User;
import com.company.carrent.entity.UserRole;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "bookings")
@ViewController(id = "Booking.list")
@ViewDescriptor(path = "booking-list-view.xml")
@LookupComponent("bookingsDataGrid")
@DialogMode(width = "64em")
public class BookingListView extends StandardListView<Booking> {

    private static final Logger log = LoggerFactory.getLogger(BookingListView.class);

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @ViewComponent("bookingsDl")
    private CollectionLoader<Booking> bookingsDl;

    @ViewComponent("bookingsDataGrid")
    private DataGrid<Booking> bookingsDataGrid;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        User currentUser = (User) currentAuthentication.getUser();

        // Debug logging
        log.info("Current user: {}", currentUser.getUsername());
        log.info("User role: {}", currentUser.getRole());

        // Check if user is customer using the correct method
        if (currentUser.getRole() == UserRole.CUSTOMER) {
            log.info("User is a customer, filtering bookings");
            // Filter bookings to show only those belonging to the current customer
            bookingsDl.setQuery("select e from Booking e where e.customer.id = :currentUserId");
            bookingsDl.setParameter("currentUserId", currentUser.getId());
        } else {
            log.info("User is not a customer, showing all bookings");
        }

        bookingsDl.load();

        // Debug: log the number of loaded bookings
        log.info("Loaded {} bookings", bookingsDl.getContainer().getItems().size());
    }
}
