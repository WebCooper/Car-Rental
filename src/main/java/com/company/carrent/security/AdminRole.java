package com.company.carrent.security;

import io.jmix.security.model.SecurityScope;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.UiMinimalPolicies;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Admin Role", code = AdminRole.CODE, scope = SecurityScope.UI)
public interface AdminRole extends UiMinimalPolicies {

    String CODE = "admin-role";

    @ViewPolicy(viewIds = {"MainView", "AdminMainView", "User.list", "Vehicle.list", "Booking.list"})
    @MenuPolicy(menuIds = "*")
    void adminAccess();

    @ViewPolicy(viewIds = "LoginView")
    void login();
}