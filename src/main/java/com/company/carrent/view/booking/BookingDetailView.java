package com.company.carrent.view.booking;

import com.company.carrent.entity.Booking;
import com.company.carrent.entity.Vehicle;
import com.company.carrent.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;

@Route(value = "bookings/:id", layout = MainView.class)
@ViewController(id = "Booking.detail")
@ViewDescriptor(path = "booking-detail-view.xml")
@EditedEntityContainer("bookingDc")
public class BookingDetailView extends StandardDetailView<Booking> {

    @ViewComponent
    private InstanceContainer<Booking> bookingDc;

    @ViewComponent
    private EntityPicker<Vehicle> vehicleField;




}