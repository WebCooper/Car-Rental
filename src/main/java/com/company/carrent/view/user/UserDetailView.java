package com.company.carrent.view.user;

import com.company.carrent.entity.User;
import com.company.carrent.entity.UserRole;
import com.company.carrent.security.AdminRole;
import com.company.carrent.security.CustomerRole;
import com.company.carrent.security.StaffRole;
import com.company.carrent.view.main.MainView;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import io.jmix.core.EntityStates;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jmix.security.role.assignment.RoleAssignmentRepository;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import io.jmix.security.role.ResourceRoleRepository;


import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

@Route(value = "users/:id", layout = MainView.class)
@ViewController(id = "User.detail")
@ViewDescriptor(path = "user-detail-view.xml")
@EditedEntityContainer("userDc")
public class UserDetailView extends StandardDetailView<User> {

    @ViewComponent
    private TypedTextField<String> usernameField;
    @ViewComponent
    private PasswordField passwordField;
    @ViewComponent
    private PasswordField confirmPasswordField;
    @ViewComponent
    private ComboBox<String> timeZoneField;
    @ViewComponent
    private MessageBundle messageBundle;
    @Autowired
    private Notifications notifications;

    @Autowired
    private EntityStates entityStates;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @ViewComponent("timeZoneField")
    private JmixComboBox<String> timeZoneField1;

    @Autowired
    private RoleAssignmentRepository roleAssignmentRepository;

    @Autowired
    private ResourceRoleRepository resourceRoleRepository;


    @Subscribe
    public void onInit(final InitEvent event) {
        timeZoneField.setItems(List.of(TimeZone.getAvailableIDs()));

    }

    @Subscribe
    public void onInitEntity(final InitEntityEvent<User> event) {
        usernameField.setReadOnly(false);
        passwordField.setVisible(true);
        confirmPasswordField.setVisible(true);
        event.getEntity().setTimeZoneId("Asia/Colombo");
        event.getEntity().setRole(UserRole.CUSTOMER);
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            usernameField.focus();
        }
    }

    @Subscribe
    public void onValidation(final ValidationEvent event) {
        if (entityStates.isNew(getEditedEntity())
                && !Objects.equals(passwordField.getValue(), confirmPasswordField.getValue())) {
            event.getErrors().add(messageBundle.getMessage("passwordsDoNotMatch"));
        }
    }

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            getEditedEntity().setPassword(passwordEncoder.encode(passwordField.getValue()));

            // Assign role based on user's role enum
            String roleCode = switch (getEditedEntity().getRole()) {
                case CUSTOMER -> CustomerRole.CODE;
                case STAFF -> StaffRole.CODE;
                case ADMIN -> AdminRole.CODE;
            };

            // Assign the appropriate role to the new user
            RoleAssignmentEntity roleAssignment = getViewData().getDataContext().create(RoleAssignmentEntity.class);
            roleAssignment.setUsername(getEditedEntity().getUsername());
            roleAssignment.setRoleCode(roleCode);
            roleAssignment.setRoleType("resource");

            getViewData().getDataContext().save();

            notifications.create(messageBundle.getMessage("userCreatedWithRole"))
                    .withType(Notifications.Type.SUCCESS)
                    .withPosition(Notification.Position.TOP_END)
                    .show();
        }
    }

}