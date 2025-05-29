package com.company.carrent.view.vehiclesearch;

import com.company.carrent.entity.Vehicle;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Route(value = "vehicle-search")
@ViewController("VehicleSearchView")
@ViewDescriptor("vehicle-search.xml")
public class VehicleSearchView extends StandardView {

    @ViewComponent
    private TextField brandField;

    @ViewComponent
    private TextField modelField;

    @ViewComponent
    private TextField registerNoField;

    @ViewComponent
    private NumberField seatsField;

    @ViewComponent
    private NumberField minPriceField;

    @ViewComponent
    private NumberField maxPriceField;

    @ViewComponent
    private TextField locationField;

    @ViewComponent
    private JmixButton searchBtn;

    @ViewComponent
    private JmixButton clearBtn;

    @ViewComponent
    private DataGrid<Vehicle> vehiclesDataGrid;

    @ViewComponent
    private CollectionLoader<Vehicle> vehiclesDl;

    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onInit(final InitEvent event) {
        setupFilterOptions();
        loadAllAvailableVehicles();
    }

    private void setupFilterOptions() {
        // Set placeholders
        brandField.setPlaceholder("Enter brand name...");
        modelField.setPlaceholder("Enter model name...");
        registerNoField.setPlaceholder("Enter register number...");
        seatsField.setPlaceholder("Number of seats");
        minPriceField.setPlaceholder("Min price per day");
        maxPriceField.setPlaceholder("Max price per day");
        locationField.setPlaceholder("Enter location...");
    }

    @Subscribe("searchBtn")
    public void onSearchBtnClick(final ClickEvent<JmixButton> event) {
        applyFiltersAndSearch();
    }

    @Subscribe("clearBtn")
    public void onClearBtnClick(final ClickEvent<JmixButton> event) {
        clearAllFilters();
        loadAllAvailableVehicles();
    }

    private void applyFiltersAndSearch() {
        StringBuilder jpql = new StringBuilder("select v from Vehicle v where 1=1");

        // Build dynamic query based on filled filters
        if (hasValue(brandField.getValue())) {
            jpql.append(" and lower(v.brand) like lower(:brand)");
        }

        if (hasValue(modelField.getValue())) {
            jpql.append(" and lower(v.model) like lower(:model)");
        }

        if (hasValue(registerNoField.getValue())) {
            jpql.append(" and lower(v.registerNo) like lower(:registerNo)");
        }

        if (seatsField.getValue() != null) {
            jpql.append(" and v.seats = :seats");
        }

        if (minPriceField.getValue() != null) {
            jpql.append(" and v.pricePerDay >= :minPrice");
        }

        if (maxPriceField.getValue() != null) {
            jpql.append(" and v.pricePerDay <= :maxPrice");
        }

        if (hasValue(locationField.getValue())) {
            jpql.append(" and lower(v.location) like lower(:location)");
        }

        jpql.append(" order by v.brand, v.model");

        // Create and configure query
        var query = dataManager.createQuery(jpql.toString(), Vehicle.class);

        // Set parameters
        if (hasValue(brandField.getValue())) {
            query.setParameter("brand", "%" + brandField.getValue().trim() + "%");
        }

        if (hasValue(modelField.getValue())) {
            query.setParameter("model", "%" + modelField.getValue().trim() + "%");
        }

        if (hasValue(registerNoField.getValue())) {
            query.setParameter("registerNo", "%" + registerNoField.getValue().trim() + "%");
        }

        if (seatsField.getValue() != null) {
            query.setParameter("seats", seatsField.getValue().intValue());
        }

        if (minPriceField.getValue() != null) {
            query.setParameter("minPrice", BigDecimal.valueOf(minPriceField.getValue()));
        }

        if (maxPriceField.getValue() != null) {
            query.setParameter("maxPrice", BigDecimal.valueOf(maxPriceField.getValue()));
        }

        if (hasValue(locationField.getValue())) {
            query.setParameter("location", "%" + locationField.getValue().trim() + "%");
        }

        // Execute query and update grid
        var vehicles = query.getResultList();
        vehiclesDataGrid.getGenericDataView().getDataProvider().refreshAll();

        // Update the data container
        CollectionContainer<Vehicle> container = vehiclesDataGrid.getItems();
        container.getMutableItems().clear();
        container.getMutableItems().addAll(vehicles);
    }

    private void clearAllFilters() {
        brandField.clear();
        modelField.clear();
        registerNoField.clear();
        seatsField.clear();
        minPriceField.clear();
        maxPriceField.clear();
        locationField.clear();
    }

    private void loadAllAvailableVehicles() {
        vehiclesDl.setQuery("select v from Vehicle v order by v.brand, v.model");
        vehiclesDl.load();
    }

    private boolean hasValue(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
