package com.company.carrent.security;

import com.company.carrent.entity.Booking;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.UiMinimalPolicies;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "CustomerRole", code = CustomerRole.CODE)
public interface CustomerRole extends UiMinimalPolicies {
    String CODE = "customer-role";

    @ViewPolicy(viewIds = {"MainView", "CustomerMainView", "Booking.list","Vehicle.list", "Vehicle.detail", "Booking.detail", "FindVehicles.list"})
    @MenuPolicy(menuIds = {"CustomerMainView", "Booking.list"})
    @SpecificPolicy(resources = {
            "ui.loginToUi",
            "ui.genericfilter.modifyGlobalConfiguration",
            "ui.genericfilter.modifyConfiguration"
    })
    void customerAccess();

    @ViewPolicy(viewIds = "LoginView")
    void login();

    @EntityPolicy(entityClass = Booking.class, actions = {EntityPolicyAction.CREATE, EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    @EntityAttributePolicy(entityClass = Booking.class, attributes = {"vehicle", "startDate", "endDate", "totalPrice","bookingStatus", "customer", "createdBy"}, action = EntityAttributePolicyAction.MODIFY)
    void booking();

    @EntityPolicy(entityName = "Vehicle", actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    @EntityAttributePolicy(entityName = "Vehicle", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    void vehicleEntityAccess();

    @EntityPolicy(entityName = "User", actions = {EntityPolicyAction.READ})
    @EntityAttributePolicy(entityName = "User", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    void userEntityAccess();

    @EntityPolicy(entityName = "flowui_FilterConfiguration", actions = {
            EntityPolicyAction.CREATE, EntityPolicyAction.READ,
            EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE
    })
    void filterConfigAccess();
}