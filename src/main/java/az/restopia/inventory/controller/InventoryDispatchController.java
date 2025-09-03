package az.restopia.inventory.controller;

import az.restopia.inventory.domain.request.InventoryDispatchRequest;
import az.restopia.inventory.domain.response.InventoryDispatchResponse;
import az.restopia.inventory.service.InventoryDispatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: validation and swagger
@RestController
@RequestMapping("/api/v1/inventory-dispatches")
@RequiredArgsConstructor
public class InventoryDispatchController {

    private final InventoryDispatchService inventoryDispatchService;

    @GetMapping
    public List<InventoryDispatchResponse> getAllInventoryDispatches() {
        return inventoryDispatchService.getAllInventoryDispatches();
    }

    @GetMapping("/{id}")
    public InventoryDispatchResponse getInventoryDispatchById(@PathVariable Long id) {
        return inventoryDispatchService.getInventoryDispatchById(id);
    }

    @PostMapping
    public InventoryDispatchResponse createInventoryDispatch(@RequestBody @Valid InventoryDispatchRequest request) {
        return inventoryDispatchService.createInventoryDispatch(request);
    }

    @PutMapping("/{id}")
    public InventoryDispatchResponse updateInventoryDispatch(@PathVariable Long id,
                                                             @RequestBody @Valid InventoryDispatchRequest request) {
        return inventoryDispatchService.updateInventoryDispatch(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteInventoryDispatch(@PathVariable Long id) {
        inventoryDispatchService.deleteInventoryDispatch(id);
    }
}
