package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.MspBackendApplication;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.szut.msp_backend.MspBackendApplication.player;

@AllArgsConstructor
@RestController
@RequestMapping("/api/character")
@CrossOrigin("*")
public class CharacterController
{
  @GetMapping
  public ResponseEntity<Character> getCharacter()
  {
    return ResponseEntity.status(HttpStatus.OK).body(player);
  }

    @GetMapping("/inventory")
    public ResponseEntity<Inventory> getInventory() {
        Inventory inventory = player.getInventory();
        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }

    @GetMapping("/trade_inventory")
    public ResponseEntity<List<TradeItem>> getAllTradeItems() {
        List<TradeItem> items = player.getInventory().getAllTradeItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("/money")
    public ResponseEntity<Integer> getMoney() {
        return ResponseEntity.status(HttpStatus.OK).body(player.getMoney());
    }
    
    @PostMapping("/money/add")
    public ResponseEntity<Integer> addMoney(@RequestParam int money){
        player.addMoney(money);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(player.getMoney());
    }
    
    @DeleteMapping("/money/remove")
    public ResponseEntity<Integer> removeMoney(@RequestParam int money) {
        if (player.getMoney() < money) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
            player.removeMoney(money);
            return ResponseEntity.status(HttpStatus.OK).body(player.getMoney());
        
    }
    
    @PostMapping("/consume")
    public ResponseEntity<Integer> consume(@RequestParam Consumable consumable) {
        return ResponseEntity.status(HttpStatus.OK).body(player.eat(consumable));
    }
    
    @PostMapping("/inventory/add")
    public ResponseEntity<?> addItem(GenericItem item)
    {
        player.getInventory().addItem(item, 1);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        
    }

    @DeleteMapping("/inventory/remove")
    public ResponseEntity<?> removeItem(GenericItem item) throws ItemNotFoundException
    {
        player.getInventory().removeItem(item, 1);
        return ResponseEntity.status(HttpStatus.CREATED).build();

  }

  @PostMapping("/buy_item_from_trader")
  @Transactional
  public ResponseEntity<?> buyItemFromTrader(GenericItem item, Integer price, String traderId) throws ItemNotFoundException
  {
    Enum<BuyItemResponse> buyItemResponse = player.buyItemFromTrader(item, price);
    Trader trader = Game.getTraderById(traderId);
    trader.playerBuysItem(item, price);
    if (buyItemResponse == BuyItemResponse.NOTENOUGHSPACE)
    {
      return ResponseEntity.status(405).build();
    } else if (buyItemResponse == BuyItemResponse.NOTENOUGHMONEY)
    {
      return ResponseEntity.status(402).build();
    } else
    {
      return ResponseEntity.status(HttpStatus.OK).build();
    }
  }

  @PostMapping("/sell_item_to_trader")
  @Transactional
  public ResponseEntity<?> sellItemToTrader(GenericItem item, Integer price, String traderId) throws ItemNotFoundException
  {
    Trader trader = Game.getTraderById(traderId);
    boolean sellPossible = trader.playerSellsItem(item, price);
    if (sellPossible)
    {
      return ResponseEntity.status(406).build();
    }
    player.sellItemToTrader(item, price);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  ;
}
