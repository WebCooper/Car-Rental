package com.company.carrent.view.booking;

import com.company.carrent.entity.Booking;
import com.company.carrent.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "bookings", layout = MainView.class)
@ViewController(id = "Booking.list")
@ViewDescriptor(path = "booking-list-view.xml")
@LookupComponent("bookingsDataGrid")
@DialogMode(width = "64em")
public class BookingListView extends StandardListView<Booking> {
}