package com.company.carrent.view.customermain;



import com.company.carrent.entity.Vehicle;
import com.company.carrent.view.booking.BookingListView;
import com.company.carrent.view.vehicle.FindVehicles;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@Route("customer")
@ViewController(id = "CustomerMainView")
@ViewDescriptor(path = "CustomerMainView.xml")
public class CustomerMainView extends StandardMainView {

    @Autowired
    private ViewNavigators viewNavigators;

    @Subscribe(id = "myBookingsButton", subject = "clickListener")
    public void onMyBookingsButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.view(this, BookingListView.class)
                .withViewClass(BookingListView.class)
                .withBackwardNavigation(true)
                .navigate();
    }

    @Subscribe(id = "searchVehiclesButton", subject = "clickListener")
    public void onSearchVehiclesButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.listView(this, Vehicle.class)
                .withViewClass(FindVehicles.class)
                .navigate();
    }


}