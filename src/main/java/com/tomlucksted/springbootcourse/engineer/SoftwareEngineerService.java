package com.tomlucksted.springbootcourse.engineer;

import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerCreateRequest;
import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerResponse;
import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerUpdateRequest;
import com.tomlucksted.springbootcourse.shared.error.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository repo;

    public SoftwareEngineerService(SoftwareEngineerRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<SoftwareEngineerResponse> getAll() {
        return repo.findAll().stream()
                .map(SoftwareEngineerMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public SoftwareEngineerResponse getById(Integer id) {
        SoftwareEngineer e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("SoftwareEngineer", id));
        return SoftwareEngineerMapper.toResponse(e);
    }

    @Transactional
    public SoftwareEngineerResponse create(SoftwareEngineerCreateRequest req) {
        SoftwareEngineer saved = repo.save(SoftwareEngineerMapper.toEntity(req));
        return SoftwareEngineerMapper.toResponse(saved);
    }

    @Transactional
    public SoftwareEngineerResponse update(Integer id, SoftwareEngineerUpdateRequest req) {
        SoftwareEngineer e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("SoftwareEngineer", id));

        SoftwareEngineerMapper.applyUpdate(e, req);
        return SoftwareEngineerMapper.toResponse(e);
    }

    @Transactional
    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("SoftwareEngineer", id);
        }
        repo.deleteById(id);
    }
}