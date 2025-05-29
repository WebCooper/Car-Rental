package com.company.carrent.view.customermain;


import com.company.carrent.entity.Booking;
import com.company.carrent.view.booking.BookingListView;
import com.company.carrent.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@Route("customer")
@ViewController(id = "CustomerMainView")
@ViewDescriptor(path = "CustomerMainView.xml")
public class CustomerMainView extends MainView {

    @Autowired
    private ViewNavigators viewNavigators;

    @Subscribe(id = "myBookingsButton", subject = "clickListener")
    public void onMyBookingsButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.listView(this, Booking.class)
                .withViewClass(BookingListView.class)
                .navigate();
    }


}