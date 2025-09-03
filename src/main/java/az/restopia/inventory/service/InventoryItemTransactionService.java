package az.restopia.inventory.service;

import az.restopia.inventory.domain.request.InventoryItemTransactionRequest;
import az.restopia.inventory.domain.response.InventoryItemTransactionResponse;

import java.util.List;

public interface InventoryItemTransactionService {
    List<InventoryItemTransactionResponse> getAllInventoryItemTransactions();

    InventoryItemTransactionResponse getInventoryItemTransactionById(Long id);

    InventoryItemTransactionResponse createInventoryItemTransaction(InventoryItemTransactionRequest request);
}
