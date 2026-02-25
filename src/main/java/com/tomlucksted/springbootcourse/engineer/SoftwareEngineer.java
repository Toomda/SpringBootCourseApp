package com.tomlucksted.springbootcourse.engineer;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class SoftwareEngineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 500)
    private String techStack;

    protected SoftwareEngineer() {}

    public SoftwareEngineer(String name, String techStack) {
        this.name = name;
        this.techStack = techStack;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getTechStack() { return techStack; }

    public void setName(String name) { this.name = name; }
    public void setTechStack(String techStack) { this.techStack = techStack; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SoftwareEngineer)) return false;
        SoftwareEngineer other = (SoftwareEngineer) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}