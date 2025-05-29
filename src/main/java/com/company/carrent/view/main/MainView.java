package com.company.carrent.view.main;

//import com.company.carrent.entity.User;
//import com.company.carrent.entity.UserRole;
//import com.vaadin.flow.router.BeforeEnterEvent;
//import com.vaadin.flow.router.BeforeEnterObserver;
//import org.springframework.beans.factory.annotation.Autowired;
//import io.jmix.core.security.CurrentAuthentication;

import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import com.vaadin.flow.router.Route;


@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
public class MainView extends StandardMainView {

}
//public class MainView extends StandardMainView implements BeforeEnterObserver {
//
//    @Autowired
//    private CurrentAuthentication currentAuthentication;
//
//    @Override
//    public void beforeEnter(BeforeEnterEvent event) {
//        User currentUser = (User)currentAuthentication.getUser();
//        UserRole userRole = currentUser.getRole();
//
//        String targetRoute = switch (userRole) {
//            case ADMIN -> "admin";
//            case STAFF -> "staff";
//            case CUSTOMER -> "customer";
//        };
//
//        if (!event.getLocation().getPath().equals(targetRoute)){
//            event.forwardTo(targetRoute);
//        }
//    }
//
//
//}
