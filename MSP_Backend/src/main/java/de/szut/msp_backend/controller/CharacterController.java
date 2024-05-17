package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.dtos.CharacterTradeRequestDto;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.BuyItemResponse;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.TradeItem;
import de.szut.msp_backend.models.tradesystem.Trader;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.szut.msp_backend.MspBackendApplication.GAME;
import static de.szut.msp_backend.parser.ItemParser.getGenericItemById;

@AllArgsConstructor
@RestController
@RequestMapping("/api/character")
@CrossOrigin("*")
public class CharacterController
{ 
    @GetMapping
    public ResponseEntity<Character> getCharacter() {
        return ResponseEntity.status(HttpStatus.OK).body(GAME.getPlayer());
    }

    @GetMapping("/inventory")
    public ResponseEntity<Inventory> getInventory() {
        Inventory inventory = GAME.getPlayer().getInventory();
        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }

    @GetMapping("/trade_inventory")
    public ResponseEntity<List<TradeItem>> getAllTradeItems() {
        List<TradeItem> items = GAME.getPlayer().getInventory().getAllTradeItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("/money")
    public ResponseEntity<Integer> getMoney() {
        return ResponseEntity.status(HttpStatus.OK).body(GAME.getPlayer().getMoney());
    }
    
    @PostMapping("/money/add")
    public ResponseEntity<Integer> addMoney(@RequestParam int money){
        GAME.getPlayer().addMoney(money);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(GAME.getPlayer().getMoney());
    }
    
    @DeleteMapping("/money/remove")
    public ResponseEntity<Integer> removeMoney(@RequestParam int money) {
        if (GAME.getPlayer().getMoney() < money) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        GAME.getPlayer().removeMoney(money);
            return ResponseEntity.status(HttpStatus.OK).body(GAME.getPlayer().getMoney());
        
    }
    
    @PostMapping("/consume")
    public ResponseEntity<Integer> consume(@RequestParam Consumable consumable) {
        return ResponseEntity.status(HttpStatus.OK).body(GAME.getPlayer().eat(consumable));
    }
    
    @PostMapping("/inventory/add")
    public ResponseEntity<?> addItem(GenericItem item)
    {
        GAME.getPlayer().getInventory().addItem(item, 1);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        
    }

    @DeleteMapping("/inventory/remove")
    public ResponseEntity<?> removeItem(GenericItem item) throws ItemNotFoundException
    {
        GAME.getPlayer().getInventory().removeItem(item, 1);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    
    @PostMapping(value = "/buy_item_from_trader", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> buyItemFromTrader(@RequestBody CharacterTradeRequestDto requestDto) throws ItemNotFoundException
    {
        GenericItem item = getGenericItemById(requestDto.getItemID());
        Enum<BuyItemResponse> buyItemResponse = GAME.getPlayer().buyItemFromTrader(item, requestDto.getPrice());
        Trader trader = GAME.getTraderById(requestDto.getTraderID());
        trader.playerBuysItem(item, requestDto.getPrice());
        System.out.println("2222: " + GAME);
        if (buyItemResponse == BuyItemResponse.NOTENOUGHSPACE){
            return ResponseEntity.status(405).build();
        }
        else if (buyItemResponse == BuyItemResponse.NOTENOUGHMONEY){
            return ResponseEntity.status(402).build();
        }
        else return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/sell_item_to_trader", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> sellItemToTrader(@RequestBody CharacterTradeRequestDto requestDto) throws ItemNotFoundException
    {
        GenericItem item = getGenericItemById(requestDto.getItemID());
        Trader trader = GAME.getTraderById(requestDto.getTraderID());
        boolean sellPossible = trader.playerSellsItem(item, requestDto.getPrice());
        if (sellPossible){
            return ResponseEntity.status(406).build();
        }
        GAME.getPlayer().sellItemToTrader(item, requestDto.getPrice());
        return ResponseEntity.status(HttpStatus.OK).build();
    };
}
