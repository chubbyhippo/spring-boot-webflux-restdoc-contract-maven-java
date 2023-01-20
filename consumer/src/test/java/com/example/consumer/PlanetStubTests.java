package com.example.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.example:producer:+:8080"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class PlanetStubTests {

    @Test
    void shouldGetPlanets() {

        WebTestClient webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
        webTestClient
                .get()
                .uri("/api/planets")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Planet.class)
                .hasSize(2);

    }

}
