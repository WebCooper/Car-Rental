package com.company.carrent.security;

import io.jmix.security.model.SecurityScope;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.UiMinimalPolicies;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "CustomerRole", code = CustomerRole.CODE, scope = SecurityScope.UI)
public interface CustomerRole extends UiMinimalPolicies {
    String CODE = "customer-role";

    @ViewPolicy(viewIds = {"MainView", "Customermainview"})
    @MenuPolicy(menuIds = {"CustomerMainView"})
    void customerAccess();

    @ViewPolicy(viewIds = "LoginView")
    void login();
}