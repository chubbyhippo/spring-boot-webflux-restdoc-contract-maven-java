package com.example.consumer;

import org.codehaus.groovy.reflection.ParameterTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.example:producer:+:8080"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class PlanetStubTests {


    private WebTestClient webTestClient;

    WebClient webClient = WebClient.builder().build();

    @Test
    void shouldGetPlanets() {
//        Flux<Planet> planetFlux = webClient
//                .get()
//                .uri("http://localhost:8080/api/planets")
//                .retrieve()
//                .bodyToFlux(Planet.class);
//
//        StepVerifier
//                .create(planetFlux)
//                .expectNext(new Planet(1L, "Saturn"))
//                .expectNext(new Planet(2L, "Jupiter"))
//                .verifyComplete();
        webTestClient = WebTestClient.bindToServer()
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
