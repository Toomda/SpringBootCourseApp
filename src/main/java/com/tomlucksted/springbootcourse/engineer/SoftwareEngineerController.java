package com.tomlucksted.springbootcourse.engineer;

import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerCreateRequest;
import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerResponse;
import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService service;

    public SoftwareEngineerController(SoftwareEngineerService service) {
        this.service = service;
    }

    @GetMapping
    public List<SoftwareEngineerResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SoftwareEngineerResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<SoftwareEngineerResponse> create(
            @Valid @RequestBody SoftwareEngineerCreateRequest req,
            UriComponentsBuilder uriBuilder
    ) {
        SoftwareEngineerResponse created = service.create(req);
        URI location = uriBuilder.path("/api/v1/software-engineers/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public SoftwareEngineerResponse update(
            @PathVariable Integer id,
            @Valid @RequestBody SoftwareEngineerUpdateRequest req
    ) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}