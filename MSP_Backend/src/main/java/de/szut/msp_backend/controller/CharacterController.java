package de.szut.msp_backend.controller;

import de.szut.msp_backend.models.item.TradeItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/character/inventory")
public class CharacterController
{
    @GetMapping()
    public ResponseEntity<TradeItem> getAllTradeItems() {
        return ResponseEntity.status(HttpStatus.OK).body();
    }
}
