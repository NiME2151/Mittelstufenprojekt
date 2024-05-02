package de.szut.msp_backend.controller;

import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.TradeItem;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/character")
public class CharacterController
{ 
    public static  Character player;

    @GetMapping("/inventory")
    public ResponseEntity<Map<GenericItem, Integer>> getAllItems() {
        Map<GenericItem, Integer> items = player.getInventory().getItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    };

    @GetMapping("/tradeInventory")
    public ResponseEntity<List<TradeItem>> getAllTradeItems() {
        List<TradeItem> items = player.getInventory().getAllTradeItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    };
    
    @GetMapping
    public ResponseEntity<Character> getCharacter() {
        return ResponseEntity.status(HttpStatus.OK).body(player);
    };
    
    @PostMapping("/money")
    public ResponseEntity<Integer> addMoney(@RequestParam int money){
        player.addMoney(money);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(player.getMoney());
    };
    
    @DeleteMapping("/money")
    public ResponseEntity<Integer> removeMoney(@RequestParam int money) {
        if (player.getMoney() < money) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
            player.removeMoney(money);
            return ResponseEntity.status(HttpStatus.OK).body(player.getMoney());
        
    };
    
    public ResponseEntity<Integer> consume(@RequestParam Consumable consumable) {
        return ResponseEntity.status(HttpStatus.OK).body(player.eat(consumable));
    }
    
    // buyItem
   // sellItem
    // getInventory
    // addItem
    //removeItem
    
}
