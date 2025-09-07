package az.restopia.authentication.controller;

import az.restopia.authentication.domain.enums.Role;
import az.restopia.authentication.domain.model.RolePermission;
import az.restopia.authentication.domain.response.RolePermissionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @GetMapping
    public List<Role> getAllRoles() {
        return Arrays.stream(Role.values()).toList();
    }

    @GetMapping("with-permissions")
    public List<RolePermissionResponse> getAllRolesWithPermissions() {
        return RolePermission.getAll().entrySet().stream()
                .map(entry -> new RolePermissionResponse(entry.getKey().name(), entry.getValue()))
                .toList();
    }
}