package com.company.carrent.view.user;

import com.company.carrent.entity.User;

import com.company.carrent.view.adminmain.AdminMainView;
import com.company.carrent.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "users", layout = AdminMainView.class)
@ViewController(id = "User.list")
@ViewDescriptor(path = "user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {
}