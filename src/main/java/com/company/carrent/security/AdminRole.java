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

@ResourceRole(name = "Admin Role", code = AdminRole.CODE, scope = SecurityScope.UI)
public interface AdminRole extends UiMinimalPolicies {

    String CODE = "admin-role";

    @ViewPolicy(viewIds = "*")
    @MenuPolicy(menuIds = "*")
    @SpecificPolicy(resources = {
            "ui.loginToUi",
            "ui.genericfilter.modifyGlobalConfiguration",
            "ui.genericfilter.modifyConfiguration"
    })
    void adminAccess();

    @ViewPolicy(viewIds = "LoginView")
    void login();

    @EntityPolicy(entityName = "User", actions = {
            EntityPolicyAction.CREATE, EntityPolicyAction.READ,
            EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE
    })
    @EntityAttributePolicy(entityName = "User", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    void userEntityAccess();

    @EntityPolicy(entityName = "Vehicle", actions = {
            EntityPolicyAction.CREATE, EntityPolicyAction.READ,
            EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE
    })
    @EntityAttributePolicy(entityName = "Vehicle", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    void vehicleEntityAccess();

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

}