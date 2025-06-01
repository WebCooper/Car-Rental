package com.company.carrent.view.staffmain;

import com.company.carrent.view.booking.BookingListView;
import com.company.carrent.view.user.UserListView;
import com.company.carrent.view.vehicle.FindVehicles;
import com.company.carrent.view.vehicle.VehicleListView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.ViewNavigators;
import org.springframework.beans.factory.annotation.Autowired;

@Route("staff")
@ViewController(id = "StaffMainView")
@ViewDescriptor(path = "StaffMainView.xml")
public class StaffMainView extends StandardMainView {

    @Autowired
    private ViewNavigators viewNavigators;

    @Subscribe(id = "customersButton", subject = "clickListener")
    public void onCustomersButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.view(this, UserListView.class)
                .withBackwardNavigation(true)
                .navigate();
    }

    @Subscribe(id = "vehiclesButton", subject = "clickListener")
    public void onVehiclesButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.view(this, VehicleListView.class)
                .withBackwardNavigation(true)
                .navigate();
    }

    @Subscribe(id = "bookingsButton", subject = "clickListener")
    public void onBookingsButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.view(this, BookingListView.class)
                .withBackwardNavigation(true)
                .navigate();
    }

    @Subscribe(id = "searchVehiclesButton", subject = "clickListener")
    public void onSearchVehiclesButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.view(this, FindVehicles.class)
                .withBackwardNavigation(true)
                .navigate();
    }


}
