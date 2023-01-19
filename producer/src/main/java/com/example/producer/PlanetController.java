package com.example.producer;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController()
@RequestMapping("/api")
public class PlanetController {

    @GetMapping("/planets")
    public Flux<Planet> getPlanets() {
        return Flux.just(new Planet(1L, "Saturn"),
                new Planet(2L, "Jupiter"));
    }

    @PostMapping("/planets")
    public Mono<Planet> createPlanet(@RequestBody String name) {
        return Mono.just(new Planet(new Random().nextLong(), name));
    }
}
