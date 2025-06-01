package az.neotech.neoeats.layout.controller;

import az.neotech.neoeats.layout.domain.dto.request.AreaRequest;
import az.neotech.neoeats.layout.domain.dto.responce.AreaResponse;
import az.neotech.neoeats.layout.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: unnecessary response entity
@Controller
@RequestMapping("/api/v1/area")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @PostMapping
    public ResponseEntity<AreaResponse> create(@RequestBody AreaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(areaService.create(request));
    }

    @GetMapping()
    public ResponseEntity<List<AreaResponse>> getAll() {
        return ResponseEntity.ok(areaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(areaService.getById(id));
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<AreaResponse> getAreaByCode(@PathVariable String code) {
        return ResponseEntity.ok(areaService.getByCode(code));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaResponse> update
            (@PathVariable Long id, @RequestBody AreaRequest request) {
        return ResponseEntity.ok(areaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        areaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
