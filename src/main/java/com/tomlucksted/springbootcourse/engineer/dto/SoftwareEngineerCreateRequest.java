package com.tomlucksted.springbootcourse.engineer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SoftwareEngineerCreateRequest(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Size(max = 500) String techStack
) {}