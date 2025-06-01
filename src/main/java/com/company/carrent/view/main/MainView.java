package com.company.carrent.view.main;

import com.company.carrent.entity.User;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
public class MainView extends StandardMainView implements BeforeEnterObserver {

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Get the current authenticated user
        User currentUser = (User) currentAuthentication.getUser();

        if (currentUser != null && currentUser.getRole() != null) {
            String targetRoute = determineRouteByRole(currentUser.getRole());

            // Only redirect if we're not already on the target route
            if (!event.getLocation().getPath().equals(targetRoute)) {
                event.forwardTo(targetRoute);
            }
        }
    }

    private String determineRouteByRole(Object role) {
        // Assuming you have an enum or string for roles
        String roleString = role.toString();

        return switch (roleString.toUpperCase()) {
            case "ADMIN" -> "admin";
            case "STAFF" -> "staff";
            case "CUSTOMER" -> "customer";
            default -> ""; // Stay on main view if role is unknown
        };
    }
}
