package de.szut.msp_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trader/inventory")
public class TraderController
{
    @GetMapping()
    public ResponseEntity<String> getAllTradeItems() {
        return ResponseEntity.status(HttpStatus.OK).body("WasGeht?");
    }
}
