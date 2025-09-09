package az.restopia.menu.controller;

import az.restopia.menu.domain.request.MenuRequest;
import az.restopia.menu.domain.response.MenuResponse;
import az.restopia.menu.service.MenuService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> getAllMenus() {
        List<MenuResponse> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/active")
    public ResponseEntity<MenuResponse> getActiveMenu(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode
    ) {
        MenuResponse menu = menuService.getActiveMenu(tenantCode);
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long id) {
        MenuResponse menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest menuRequest) {
        MenuResponse createdMenu = menuService.createMenu(menuRequest);
        return ResponseEntity.status(201).body(createdMenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long id, @RequestBody MenuRequest menuRequest) {
        MenuResponse updatedMenu = menuService.updateMenu(id, menuRequest);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuResponse>> searchMenus(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String category) {
        List<MenuResponse> menus = menuService.searchMenus(name, category);
        return ResponseEntity.ok(menus);
    }
}