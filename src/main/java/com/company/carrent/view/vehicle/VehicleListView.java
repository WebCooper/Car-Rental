package com.company.carrent.view.vehicle;

import com.company.carrent.entity.Vehicle;
import com.company.carrent.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "vehicles", layout = MainView.class)
@ViewController(id = "Vehicle.list")
@ViewDescriptor(path = "vehicle-list-view.xml")
@LookupComponent("vehiclesDataGrid")
@DialogMode(width = "64em")
public class VehicleListView extends StandardListView<Vehicle> {
}