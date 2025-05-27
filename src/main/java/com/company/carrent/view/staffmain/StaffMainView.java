package com.company.carrent.view.staffmain;


import com.company.carrent.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route("staff")
@ViewController(id = "Staffmainview")
@ViewDescriptor(path = "StaffMainView.xml")
public class StaffMainView extends MainView {
}