package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.item.TradeItem;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/trader/inventory")
public class TraderController
{
    @GetMapping()
    public ResponseEntity<List<TradeItem>> getAllTradeItems(@RequestParam String traderID) {
        List<TradeItem> items = Game.getTraderById(traderID).getAllTradeItems(); 
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }
}
