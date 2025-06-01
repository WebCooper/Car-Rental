package com.company.carrent.view.vehicle;

import com.company.carrent.entity.Booking;
import com.company.carrent.entity.Vehicle;
import com.company.carrent.view.booking.BookingDetailView;
import com.company.carrent.view.main.MainView;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.textfield.JmixBigDecimalField;
import io.jmix.flowui.component.textfield.JmixIntegerField;

import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.math.BigDecimal;

@Route(value = "find-vehicles")
@ViewController(id = "FindVehicles.list")
@ViewDescriptor(path = "find-vehicles.xml")
@LookupComponent("vehiclesDataGrid")
@DialogMode(width = "AUTO", height = "AUTO")
public class FindVehicles extends StandardListView<Vehicle> {

    @ViewComponent
    private CollectionLoader<Vehicle> vehiclesDl;

    @ViewComponent
    private JmixIntegerField minSeatsField;

    @ViewComponent
    private JmixIntegerField maxSeatsField;

    @ViewComponent
    private TypedTextField brandField;

    @ViewComponent
    private TypedTextField locationField;

    @ViewComponent
    private JmixBigDecimalField minPriceField;

    @ViewComponent
    private JmixBigDecimalField maxPriceField;

    @ViewComponent
    private DataGrid<Vehicle> vehiclesDataGrid;

    @ViewComponent
    private CollectionContainer<Vehicle> vehiclesDc;

    @Autowired
    private DialogWindows dialogWindows;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Metadata metadata;


    @Subscribe
    public void onInit(InitEvent event) {
        // Load data initially with default parameters
        loadVehicles();
        vehiclesDataGrid.addSelectionListener(selectionEvent -> {
            Vehicle selectedVehicle = vehiclesDataGrid.getSingleSelectedItem();

            // Enable/disable the book vehicle action based on selection
            getViewActions().getAction("bookVehicleAction")
                    .setEnabled(selectedVehicle != null);
        });


    }

    @Subscribe("searchAction")
    public void onSearchAction(ActionPerformedEvent event) {
        loadVehicles();
    }

    @Subscribe("clearAction")
    public void onClearAction(ActionPerformedEvent event) {
        clearFilters();
        loadVehicles();
    }

    private void loadVehicles() {
        // Set parameters for the query
        vehiclesDl.setParameter("minSeats", minSeatsField.getValue());
        vehiclesDl.setParameter("maxSeats", maxSeatsField.getValue());
        vehiclesDl.setParameter("brand", getFilterValue(brandField.getValue()));
        vehiclesDl.setParameter("location", getFilterValue(locationField.getValue()));
        vehiclesDl.setParameter("minPrice", minPriceField.getValue());
        vehiclesDl.setParameter("maxPrice", maxPriceField.getValue());

        // Load the data
        vehiclesDl.load();
    }

    private String getFilterValue(String value) {
        // Return null for empty strings so the query condition works properly
        return (value != null && !value.trim().isEmpty()) ? value.trim() : null;
    }

    private void clearFilters() {
        minSeatsField.clear();
        maxSeatsField.clear();
        brandField.clear();
        locationField.clear();
        minPriceField.clear();
        maxPriceField.clear();
    }

    // Optional: Add real-time filtering as user types
    @Subscribe("minSeatsField")
    public void onMinSeatsFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        if (event.isFromClient()) {
            loadVehicles();
        }
    }

    @Subscribe("maxSeatsField")
    public void onMaxSeatsFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        if (event.isFromClient()) {
            loadVehicles();
        }
    }

    @Subscribe("brandField")
    public void onBrandFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        if (event.isFromClient()) {
            loadVehicles();
        }
    }

    @Subscribe("locationField")
    public void onLocationFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        if (event.isFromClient()) {
            loadVehicles();
        }
    }

    @Subscribe("minPriceField")
    public void onMinPriceFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isFromClient()) {
            loadVehicles();
        }
    }

    @Subscribe("maxPriceField")
    public void onMaxPriceFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (event.isFromClient()) {
            loadVehicles();
        }
    }

    @Subscribe("bookVehicleAction")
    public void onBookVehicleAction(final ActionPerformedEvent event) {
        Vehicle selectedVehicle = vehiclesDataGrid.getSingleSelectedItem();
        if (selectedVehicle != null) {
            // Create a new booking entity
            Booking newBooking = dataManager.create(Booking.class);
            newBooking.setVehicle(selectedVehicle);

            // Open BookingDetailView as a dialog - CORRECTED VERSION
            dialogWindows.detail(this, Booking.class)  // Added 'this' as first parameter
                    .withViewClass(BookingDetailView.class)
                    .editEntity(newBooking)
                    .withAfterCloseListener(closeEvent -> {
                        if (closeEvent.closedWith(StandardOutcome.SAVE)) {
                            // Refresh the vehicles list after booking is saved
                            loadVehicles();
                        }
                    })
                    .open();
        }
    }
}
