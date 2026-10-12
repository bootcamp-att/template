package com.bootcamp.template.controller;

import com.bootcamp.template.api.HolaApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController implements HolaApi {

    @Override
    public ResponseEntity<String> obtenerHola() {
        return ResponseEntity.ok("Hola");
    }
}