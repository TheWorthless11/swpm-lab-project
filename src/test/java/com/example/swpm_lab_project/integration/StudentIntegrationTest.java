package com.example.swpm_lab_project.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driver-class-name=org.h2.Driver"
})
class StudentIntegrationTest {

    @LocalServerPort
    private int port;


    @Test
    void testGetAllStudentsEndpoint() {
        // Create a RestTemplate to make HTTP calls
        RestTemplate restTemplate = new RestTemplate();

        // This simulates a browser or API call to your controller
        String url = "http://localhost:" + port + "/students";
        String response = restTemplate.getForObject(url, String.class);

        // Verifies the whole system responds with a non-null response
        assertThat(response).isNotNull();
    }
}