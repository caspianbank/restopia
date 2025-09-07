package az.restopia.authentication.domain.model;

import az.restopia.authentication.domain.enums.Permission;
import az.restopia.authentication.domain.enums.Role;

import java.util.*;

public class RolePermission {
    private static final Map<Role, Set<Permission>> ROLE_PERMISSIONS = new HashMap<>();

    static {
        ROLE_PERMISSIONS.put(Role.ADMIN, EnumSet.allOf(Permission.class));
        ROLE_PERMISSIONS.put(Role.OWNER, EnumSet.of(
                Permission.VIEW_REPORTS,
                Permission.MANAGE_MENU,
                Permission.VIEW_FINANCIALS
        ));
        ROLE_PERMISSIONS.put(Role.MANAGER, EnumSet.of(
                Permission.MANAGE_ORDERS,
                Permission.MANAGE_MENU,
                Permission.VIEW_REPORTS
        ));
        ROLE_PERMISSIONS.put(Role.WAITER, EnumSet.of(
                Permission.MANAGE_ORDERS
        ));
        ROLE_PERMISSIONS.put(Role.KITCHEN_STAFF, EnumSet.of(
                Permission.VIEW_KITCHEN_ORDERS
        ));
    }

    public static Map<Role, Set<Permission>> getAll() {
        return Collections.unmodifiableMap(ROLE_PERMISSIONS);
    }
}
