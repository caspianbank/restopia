package az.restopia.inventory.controller;

import az.restopia.inventory.domain.request.InventoryItemTransactionRequest;
import az.restopia.inventory.domain.response.InventoryItemTransactionResponse;
import az.restopia.inventory.service.InventoryItemTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: validation, swagger
@RestController
@RequestMapping("/api/v1/inventory-item-transactions")
@RequiredArgsConstructor
public class InventoryItemTransactionController {

    private final InventoryItemTransactionService inventoryItemTransactionService;

    @GetMapping
    public List<InventoryItemTransactionResponse> getAllInventoryItemTransactions() {
        return inventoryItemTransactionService.getAllInventoryItemTransactions();
    }

    @GetMapping("/{id}")
    public InventoryItemTransactionResponse getInventoryItemTransactionById(@PathVariable Long id) {
        return inventoryItemTransactionService.getInventoryItemTransactionById(id);
    }

    @PostMapping
    public InventoryItemTransactionResponse createInventoryItemTransaction(
            @RequestBody @Valid InventoryItemTransactionRequest request) {
        return inventoryItemTransactionService.createInventoryItemTransaction(request);
    }
}
