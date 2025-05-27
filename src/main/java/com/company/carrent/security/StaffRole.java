package com.company.carrent.security;

import io.jmix.security.model.SecurityScope;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.UiMinimalPolicies;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "StaffRole", code = StaffRole.CODE, scope = SecurityScope.UI)
public interface StaffRole extends UiMinimalPolicies {
    String CODE = "staff-role";

    @ViewPolicy(viewIds = {"MainView", "Staffmainview", "Vehicle.list", "Booking.list", })
    @MenuPolicy(menuIds = {"Staffmainview", "Vehicle.list", "Booking.list"})
    void staffAccess();

    @ViewPolicy(viewIds = "LoginView")
    void login();
}