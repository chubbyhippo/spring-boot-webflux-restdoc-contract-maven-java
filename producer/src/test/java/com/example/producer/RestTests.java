package com.example.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.cloud.contract.wiremock.restdocs.SpringCloudContractRestDocs;
import org.springframework.http.MediaType;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@AutoConfigureRestDocs
class RestTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldGetPlanets() {
        webTestClient
                .get()
                .uri("/api/planets")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.[0].name").hasJsonPath()
                .jsonPath("$.[1].name").hasJsonPath()
                .consumeWith(WebTestClientRestDocumentation
                        .document("get",
                                SpringCloudContractRestDocs.dslContract()));
    }

    @Test
    void shouldAddPlanet() {
        webTestClient
                .post()
                .uri("/api/planets")
                .body(Mono.just("Jupiter"), String.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isEqualTo("Jupiter")
                .consumeWith(WebTestClientRestDocumentation
                        .document("post",
                                SpringCloudContractRestDocs.dslContract()));
    }
}
