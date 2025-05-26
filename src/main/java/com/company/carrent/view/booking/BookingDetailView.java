package com.company.carrent.view.booking;

import com.company.carrent.entity.Booking;
import com.company.carrent.entity.Vehicle;
import com.company.carrent.entity.VehicleStatus;
import com.company.carrent.view.main.MainView;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Route(value = "bookings/:id", layout = MainView.class)
@ViewController(id = "Booking.detail")
@ViewDescriptor(path = "booking-detail-view.xml")
@EditedEntityContainer("bookingDc")
public class BookingDetailView extends StandardDetailView<Booking> {

    @ViewComponent
    private InstanceContainer<Booking> bookingDc;

    @ViewComponent
    private EntityPicker<Vehicle> vehicleField;

    @ViewComponent
    private DateTimePicker startDateField;

    @ViewComponent
    private DateTimePicker endDateField;

    @Autowired
    private DataManager dataManager;

    private Vehicle previousVehicle;

    @ViewComponent
    private TextField totalPriceField;
    @ViewComponent("totalPriceField")
    private TypedTextField<BigDecimal> totalPriceField1;

    @Subscribe
    public void onInit(final InitEvent event) {
        vehicleField.addValueChangeListener(e -> {
            Vehicle newVehicle = e.getValue();
            previousVehicle = e.getOldValue(); // Track previous vehicle
            calculateTotal();
        });
        startDateField.addValueChangeListener(e -> calculateTotal());
        endDateField.addValueChangeListener(e -> calculateTotal());
    }

    private void calculateTotal() {
        Booking booking = bookingDc.getItem();
        Vehicle vehicle = booking.getVehicle();
        LocalDateTime start = booking.getStartDate();
        LocalDateTime end = booking.getEndDate();

        if(vehicle != null && start != null && end != null ){
            long days = Duration.between(start, end).toDays();
            if (days <1){
                days = 1;
            }
            BigDecimal total = vehicle.getPricePerDay().multiply(BigDecimal.valueOf(days));
            booking.setTotalPrice(total);
            totalPriceField.setValue(total.toString());

        }
    }

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        Booking booking = bookingDc.getItem();
        Vehicle currentVehicle = booking.getVehicle();

        if (currentVehicle != null) {
            currentVehicle.setStatus(VehicleStatus.BOOKED);
            dataManager.save(currentVehicle);
        }

        if (previousVehicle != null && !previousVehicle.equals(currentVehicle)) {
            previousVehicle.setStatus(VehicleStatus.AVAILABLE);
            dataManager.save(previousVehicle);
        }
    }

}