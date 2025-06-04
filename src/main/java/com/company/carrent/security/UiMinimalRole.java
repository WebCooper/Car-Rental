package com.company.carrent.security;

import com.company.carrent.entity.Vehicle;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.model.SecurityScope;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.UiMinimalPolicies;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "UI: minimal access", code = UiMinimalRole.CODE, scope = SecurityScope.UI)
public interface UiMinimalRole extends UiMinimalPolicies {

    String CODE = "ui-minimal";


    @ViewPolicy(viewIds = "LoginView")
    @SpecificPolicy(resources = "ui.loginToUi")
    void login();

    @ViewPolicy(viewIds = "FindVehicles.list")
    void findVehiclesView();


    @EntityPolicy(entityName = "Vehicle", actions = {EntityPolicyAction.READ})
    @EntityAttributePolicy(entityName = "Vehicle", attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void vehicleEntityAccess();

    @EntityPolicy(entityName = "User", actions = {EntityPolicyAction.READ})
    @EntityAttributePolicy(entityName = "User", attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void userEntityAccess();

    @EntityPolicy(entityName = "flowui_FilterConfiguration", actions = {EntityPolicyAction.READ,

    })
    void filterConfigAccess();
}
