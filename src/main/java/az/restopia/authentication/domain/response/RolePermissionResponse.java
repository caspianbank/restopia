package az.restopia.authentication.domain.response;

import az.restopia.authentication.domain.enums.Permission;

import java.util.*;

public record RolePermissionResponse(String role, Set<Permission> permissions) {

}
