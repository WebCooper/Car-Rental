package com.company.carrent.view.customermain;


import com.company.carrent.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route("customer")
@ViewController(id = "Customermainview")
@ViewDescriptor(path = "CustomerMainView.xml")
public class Customermainview extends MainView {
}