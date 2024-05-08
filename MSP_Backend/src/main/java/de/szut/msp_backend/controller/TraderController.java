package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.TradeItem;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.parser.ItemParser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/trader")
public class TraderController
{
    @GetMapping()
    public ResponseEntity<Trader> getTraderByID(@RequestParam final String traderID)
    {
        final Trader trader = Game.getTraderById(traderID);
        return ResponseEntity.status(HttpStatus.OK).body(trader);
    }

    @GetMapping("/inventory/items")
    public ResponseEntity<List<TradeItem>> getAllTradeItems(@RequestParam final String traderID) {
        final List<TradeItem> items = Game.getTraderById(traderID).getAllTradeItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @PostMapping("/inventory/items/add")
    public ResponseEntity addItem(@RequestParam final String traderID, final int itemID, final int amount) {
        final Trader trader = Game.getTraderById(traderID);
        try
        {
            final GenericItem item = ItemParser.getGenericItemFromID(itemID);
            trader.getInventory().addItem(item, amount);
            return ResponseEntity.ok().build();
        }
        catch (ItemNotFoundException ex)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/inventory/items/remove")
    public ResponseEntity removeItem(@RequestParam final String traderID, final int itemID, final int amount)
    {
        final Trader trader = Game.getTraderById(traderID);
        try
        {
            trader.getInventory().removeItem(itemID, amount);
            return ResponseEntity.ok().build();
        }
        catch (ItemNotFoundException ex)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/inventory/items/size")
    public ResponseEntity<Integer> size(@RequestParam final String traderID)
    {
        final Trader trader = Game.getTraderById(traderID);
        final int size = trader.getInventory().getMaxSize();
        return ResponseEntity.status(HttpStatus.OK).body(size);
    }

    @PostMapping("/inventory/items/size")
    public ResponseEntity sizeSet(@RequestParam final String traderID, @RequestParam final int newSize)
    {
        final Trader trader = Game.getTraderById(traderID);
        trader.getInventory().setMaxSize(newSize);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/inventory/items/is_not_full")
    public ResponseEntity<Boolean> isNotFull(@RequestParam final String traderID)
    {
        final Trader trader = Game.getTraderById(traderID);
        final boolean isNotFull = trader.getInventory().isNotFull();
        return ResponseEntity.status(HttpStatus.OK).body(isNotFull);
    }

    @GetMapping("/inventory/items/is_item_present")
    public ResponseEntity<Boolean> isItemPresent(@RequestParam final String traderID, @RequestParam final GenericItem item)
    {
        final Trader trader = Game.getTraderById(traderID);
        final boolean isItemPresent = trader.getInventory().isItemPresent(item);
        return ResponseEntity.status(HttpStatus.OK).body(isItemPresent);
    }
}
