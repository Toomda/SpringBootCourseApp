package com.tomlucksted.springbootcourse.engineer;

import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerCreateRequest;
import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerResponse;
import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerUpdateRequest;

public final class SoftwareEngineerMapper {

    private SoftwareEngineerMapper() {}

    public static SoftwareEngineer toEntity(SoftwareEngineerCreateRequest req) {
        return new SoftwareEngineer(req.name(), req.techStack());
    }

    public static SoftwareEngineerResponse toResponse(SoftwareEngineer e) {
        return new SoftwareEngineerResponse(e.getId(), e.getName(), e.getTechStack());
    }

    public static void applyUpdate(SoftwareEngineer e, SoftwareEngineerUpdateRequest req) {
        e.setName(req.name());
        e.setTechStack(req.techStack());
    }
}