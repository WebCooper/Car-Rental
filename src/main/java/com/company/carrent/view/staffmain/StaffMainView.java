package com.company.carrent.view.staffmain;


import com.company.carrent.entity.Booking;
import com.company.carrent.entity.User;
import com.company.carrent.entity.Vehicle;
import com.company.carrent.view.booking.BookingListView;
import com.company.carrent.view.main.MainView;
import com.company.carrent.view.user.UserListView;
import com.company.carrent.view.vehicle.VehicleListView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@Route("staff")
@ViewController(id = "StaffMainView")
@ViewDescriptor(path = "StaffMainView.xml")
public class StaffMainView extends MainView {

    @Autowired
    private ViewNavigators viewNavigators;

    @Subscribe(id = "customersButton", subject = "clickListener")
    public void onCustomersButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.listView(this, User.class)
                .withViewClass(UserListView.class)
                .navigate();
    }

    @Subscribe(id = "vehiclesButton", subject = "clickListener")
    public void onVehiclesButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.listView(this, Vehicle.class)
                .withViewClass(VehicleListView.class)
                .navigate();
    }

    @Subscribe(id = "bookingsButton", subject = "clickListener")
    public void onBookingsButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.listView(this, Booking.class)
                .withViewClass(BookingListView.class)
                .navigate();
    }


}