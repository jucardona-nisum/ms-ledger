package com.nisum.hack.ms.ledger.controller;

import com.nisum.hack.ms.ledger.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sum")
public class OperationController {

    private final OperationService service;

    @Autowired
    public OperationController(OperationService service) {
        this.service = service;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getResultOperation(@Valid @RequestBody List<Integer> values) {
        return ResponseEntity.ok(service.sumValues(values.stream()
                .mapToInt(Integer::intValue)
                .toArray()));
    }
}
