package com.company.carrent.view.adminmain;



import com.company.carrent.entity.User;
import com.company.carrent.entity.Vehicle;
import com.company.carrent.view.main.MainView;


import com.company.carrent.view.user.UserListView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;

import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.flowui.view.navigation.ViewNavigator;
import org.springframework.beans.factory.annotation.Autowired;



@Route("admin")
@ViewController(id = "AdminMainView")
@ViewDescriptor(path = "AdminMainView.xml")
public class AdminMainView extends MainView {


    @Autowired
    private ViewNavigators viewNavigators;

    @Subscribe(id = "usersButton", subject = "clickListener")
    public void onUsersButtonClick(final ClickEvent<JmixButton> event) {
        viewNavigators.listView(this, User.class)
                .withViewClass(UserListView.class)
                .navigate();
    }

//    @Subscribe(id = "usersButton", subject = "clickListener")
//    public void onUsersButtonClick(final ClickEvent<JmixButton> event) {
//        getUI().ifPresent(ui -> ui.navigate("users")); // direct route
//    }






}