package az.neotech.neoeats.layout.controller;

import az.neotech.neoeats.layout.domain.dto.request.TableRequest;
import az.neotech.neoeats.layout.domain.dto.responce.TableResponse;
import az.neotech.neoeats.layout.domain.enums.TableStatus;
import az.neotech.neoeats.layout.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;

    @PostMapping
    public ResponseEntity<TableResponse> create(@RequestBody TableRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tableService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<TableResponse>> getAll() {
        return ResponseEntity.ok(tableService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getById(id));
    }
    @GetMapping("/by-code/{code}")
    public ResponseEntity<TableResponse> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(tableService.getByCode(code));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TableResponse> update(@PathVariable Long id, @RequestBody TableRequest request) {
        return ResponseEntity.ok(tableService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tableService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TableResponse> changeStatus(@PathVariable Long id, @RequestParam TableStatus status) {
        return ResponseEntity.ok(tableService.changeStatus(id, status));
    }

    @GetMapping("/{tableId}/qrcode")
    public ResponseEntity<String> generateQrCode(@PathVariable Long tableId) {
        String base64Qr = tableService.generateQrCodeForTable(tableId);
        return ResponseEntity.ok(base64Qr);
    }
}
