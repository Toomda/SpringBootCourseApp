package com.tomlucksted.springbootcourse.engineer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SoftwareEngineerServiceTest {

    private SoftwareEngineerRepository repository;
    private SoftwareEngineerService service;

    @BeforeEach
    void setup() {
        repository = mock(SoftwareEngineerRepository.class);
        service = new SoftwareEngineerService(repository);
    }

    @Test
    void shouldReturnEngineerById() {

        SoftwareEngineer engineer =
                new SoftwareEngineer("Tom", "Java");

        when(repository.findById(1)).thenReturn(Optional.of(engineer));

        var result = service.getById(1);

        assertThat(result.name()).isEqualTo("Tom");
        verify(repository, times(1)).findById(1);
    }
}