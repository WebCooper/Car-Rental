package com.company.carrent.security;

import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.model.SecurityScope;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.UiMinimalPolicies;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "StaffRole", code = StaffRole.CODE, scope = SecurityScope.UI)
public interface StaffRole extends UiMinimalPolicies {
    String CODE = "staff-role";

    @ViewPolicy(viewIds = {
            "MainView", "StaffMainView",
            "Vehicle.list", "Vehicle.detail",
            "Booking.list", "Booking.detail",
            "User.list", "User.detail",
            "FindVehicles.list"
    })
    @MenuPolicy(menuIds = {"StaffMainView", "Vehicle.list", "Booking.list"})
    @SpecificPolicy(resources = {
            "ui.loginToUi",
            "ui.genericfilter.modifyGlobalConfiguration",
            "ui.genericfilter.modifyConfiguration"
    })
    void staffAccess();

    @ViewPolicy(viewIds = "LoginView")
    void login();

    // Vehicle CRUD
    @EntityPolicy(entityName = "Vehicle", actions = {
            EntityPolicyAction.CREATE, EntityPolicyAction.READ,
            EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE
    })
    @EntityAttributePolicy(entityName = "Vehicle", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    void vehicleEntityAccess();

    // Booking CRUD
    @EntityPolicy(entityName = "Booking", actions = {
            EntityPolicyAction.CREATE, EntityPolicyAction.READ,
            EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE
    })
    @EntityAttributePolicy(entityName = "Booking", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    void bookingEntityAccess();

    @EntityPolicy(entityName = "flowui_FilterConfiguration", actions = {
            EntityPolicyAction.CREATE, EntityPolicyAction.READ,
            EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE
    })
    void filterConfigAccess();

    @EntityPolicy(entityName = "User", actions = { EntityPolicyAction.ALL})
    @EntityAttributePolicy(entityName = "User", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    void userEntityAccess();

    @EntityPolicy(entityName = "sec_UserSubstitutionEntity", actions = {
            EntityPolicyAction.CREATE, EntityPolicyAction.READ,
            EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE
    })
    @EntityAttributePolicy(entityName = "sec_UserSubstitutionEntity", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    void userSubstitutionAccess();

    @EntityPolicy(entityName = "sec_RoleAssignmentEntity", actions = {
            EntityPolicyAction.CREATE, EntityPolicyAction.READ,
            EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE
    })
    @EntityAttributePolicy(entityName = "sec_RoleAssignmentEntity", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    void roleAssignmentAccess();

//    // Customer CRUD
//    @EntityPolicy(entityName = "Customer", actions = {
//            EntityPolicyAction.CREATE, EntityPolicyAction.READ,
//            EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE
//    })
//    @EntityAttributePolicy(entityName = "Customer", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
//    void customerAccess();
}
