package com.company.carrent.view.vehicle;

import com.company.carrent.entity.Vehicle;
import com.company.carrent.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "vehicles/:id", layout = MainView.class)
@ViewController(id = "Vehicle.detail")
@ViewDescriptor(path = "vehicle-detail-view.xml")
@EditedEntityContainer("vehicleDc")
public class VehicleDetailView extends StandardDetailView<Vehicle> {
}