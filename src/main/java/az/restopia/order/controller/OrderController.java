package az.restopia.order.controller;

import az.restopia.order.domain.enums.OrderStatus;
import az.restopia.order.domain.request.OrderRequest;
import az.restopia.order.domain.response.OrderCountResponse;
import az.restopia.order.domain.response.OrderResponse;
import az.restopia.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getOrders(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @RequestParam(required = false) OrderStatus status,
            Pageable pageable) {
        Page<OrderResponse> orders = orderService.getOrders(tenantCode, status, pageable);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(tenantCode, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable String id,
            @Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.updateOrder(tenantCode, id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable String id) {
        orderService.cancelOrder(tenantCode, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable String id) {
        OrderResponse response = orderService.getOrderById(tenantCode, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<OrderResponse>> searchOrders(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @RequestParam String q,
            Pageable pageable) {
        Page<OrderResponse> orders = orderService.searchOrders(tenantCode, q, pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/count")
    public List<OrderCountResponse> getOrderCounts(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @RequestParam(defaultValue = "daily") String period) {
        return orderService.getOrderCounts(tenantCode, period);
    }
}