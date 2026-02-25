package com.tomlucksted.springbootcourse.engineer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SoftwareEngineerRepositoryTest {

    @Autowired
    private SoftwareEngineerRepository repository;

    @Test
    void shouldSaveEngineer() {

        SoftwareEngineer engineer =
                new SoftwareEngineer("Tom", "Java");

        SoftwareEngineer saved = repository.save(engineer);

        assertThat(saved.getId()).isNotNull();
        assertThat(repository.findAll()).hasSize(1);
    }
}