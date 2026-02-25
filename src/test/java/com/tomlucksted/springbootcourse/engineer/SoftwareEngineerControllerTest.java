package com.tomlucksted.springbootcourse.engineer;

import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerCreateRequest;
import com.tomlucksted.springbootcourse.engineer.dto.SoftwareEngineerResponse;
import com.tomlucksted.springbootcourse.engineer.SoftwareEngineerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SoftwareEngineerController.class)
class SoftwareEngineerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SoftwareEngineerService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllEngineers() throws Exception {

        when(service.getAll()).thenReturn(
                List.of(new SoftwareEngineerResponse(1, "Tom", "Java"))
        );

        mockMvc.perform(get("/api/v1/software-engineers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Tom"));
    }

    @Test
    void shouldCreateEngineer() throws Exception {

        SoftwareEngineerCreateRequest request =
                new SoftwareEngineerCreateRequest("Tom", "Java");

        SoftwareEngineerResponse response =
                new SoftwareEngineerResponse(1, "Tom", "Java");

        when(service.create(request)).thenReturn(response);

        mockMvc.perform(post("/api/v1/software-engineers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
}