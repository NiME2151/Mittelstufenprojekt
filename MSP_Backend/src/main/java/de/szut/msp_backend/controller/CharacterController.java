package de.szut.msp_backend.controller;

import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.item.TradeItem;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/character/inventory")
public class CharacterController
{ 
    public static Character player;
    @GetMapping()
    public ResponseEntity<List<TradeItem>> getAllTradeItems() {
        List<TradeItem> items = player.getInventory().getAllTradeItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }
}
