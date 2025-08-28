package az.neotech.neoeats.layout.controller;

import az.neotech.neoeats.layout.domain.dto.request.DiningTableRequest;
import az.neotech.neoeats.layout.domain.dto.responce.DiningTableResponse;
import az.neotech.neoeats.layout.domain.enums.TableStatus;
import az.neotech.neoeats.layout.service.DiningTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dining-tables")
@RequiredArgsConstructor
public class DiningTableController {
    private final DiningTableService diningTableService;

    @PostMapping
    public ResponseEntity<DiningTableResponse> create(@RequestBody DiningTableRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diningTableService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<DiningTableResponse>> getAll() {
        return ResponseEntity.ok(diningTableService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiningTableResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(diningTableService.getById(id));
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<DiningTableResponse> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(diningTableService.getByCode(code));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiningTableResponse> update(@PathVariable Long id, @RequestBody DiningTableRequest request) {
        return ResponseEntity.ok(diningTableService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        diningTableService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DiningTableResponse> changeStatus(@PathVariable Long id, @RequestParam TableStatus status) {
        return ResponseEntity.ok(diningTableService.changeStatus(id, status));
    }

    @GetMapping("/{tableId}/qrcode")
    public ResponseEntity<String> generateQrCode(@PathVariable Long tableId) {
        String base64Qr = diningTableService.generateQrCodeForTable(tableId);
        return ResponseEntity.ok(base64Qr);
    }
}
