package az.neotech.neoeats.inventory.service;

import az.neotech.neoeats.inventory.domain.request.InventoryItemTransactionRequest;
import az.neotech.neoeats.inventory.domain.response.InventoryItemTransactionResponse;

import java.util.List;

public interface InventoryItemTransactionService {
    List<InventoryItemTransactionResponse> getAllInventoryItemTransactions();

    InventoryItemTransactionResponse getInventoryItemTransactionById(Long id);

    InventoryItemTransactionResponse createInventoryItemTransaction(InventoryItemTransactionRequest request);
}
