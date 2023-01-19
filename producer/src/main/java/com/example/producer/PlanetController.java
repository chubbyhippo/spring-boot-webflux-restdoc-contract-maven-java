package com.example.producer;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController()
@RequestMapping("/api")
public class PlanetController {

    @GetMapping("/planets")
    public List<Planet> getPlanets() {
        return Arrays.asList(new Planet(1L, "Saturn"),
                new Planet(2L, "Jupiter"));
    }

    @PostMapping("/planets")
    public Planet createPlanet(@RequestBody String name) {
        return new Planet(new Random().nextLong(), name);
    }
}
