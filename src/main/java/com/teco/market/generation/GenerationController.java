package com.teco.market.generation;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.support.annotation.Permission;
import lombok.RequiredArgsConstructor;

@Permission(target = "USER")
@RequestMapping("/api/generations")
@RequiredArgsConstructor
@RestController
public class GenerationController {
    private final GenerationRepository generationRepository;

    @Permission(target = "ADMIN")
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody GenerationCreateRequest request) {
        Generation generation = generationRepository.save(request.toGeneration());

        return ResponseEntity.created(URI.create("/api/generations/" + generation.getId())).build();
    }
}
