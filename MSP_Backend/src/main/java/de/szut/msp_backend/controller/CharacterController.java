package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.events.FightGameAction;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.BuyItemResponse;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.combatsystem.CombatMoves;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.TradeItem;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.parser.ItemParser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/character")
public class CharacterController
{ 
    public static  Character player;
    @GetMapping
    public ResponseEntity<Character> getCharacter() {
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @GetMapping("/inventory")
    public ResponseEntity<Map<GenericItem, Integer>> getAllItems() {
        Map<GenericItem, Integer> items = player.getInventory().getItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("/tradeInventory")
    public ResponseEntity<List<TradeItem>> getAllTradeItems() {
        List<TradeItem> items = player.getInventory().getAllTradeItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("/money")
    public ResponseEntity<Integer> getMoney() {
        return ResponseEntity.status(HttpStatus.OK).body(player.getMoney());
    }
    
    @PostMapping("/money")
    public ResponseEntity<Integer> addMoney(@RequestParam int money){
        player.addMoney(money);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(player.getMoney());
    }
    
    @DeleteMapping("/money")
    public ResponseEntity<Integer> removeMoney(@RequestParam int money) {
        if (player.getMoney() < money) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
            player.removeMoney(money);
            return ResponseEntity.status(HttpStatus.OK).body(player.getMoney());
        
    }
    
    @PostMapping
    public ResponseEntity<Integer> consume(@RequestParam Consumable consumable) {
        return ResponseEntity.status(HttpStatus.OK).body(player.eat(consumable));
    }
    
    @PostMapping
    public ResponseEntity<?> addItem(GenericItem item)
    {
        player.getInventory().addItem(item, 1);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        
    }

    @DeleteMapping
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
        Trader trader = Game.getInstance().getMap().getTraderById(traderId);
        trader.playerBuysItem(item, price);
        if (buyItemResponse == BuyItemResponse.NOTENOUGHSPACE){
            return ResponseEntity.status(405).build();
        }
        else if (buyItemResponse == BuyItemResponse.NOTENOUGHMONEY){
            return ResponseEntity.status(402).build();
        }
        else return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @PostMapping("/sell_item_to_trader")
    @Transactional
    public ResponseEntity<?> sellItemToTrader(GenericItem item, Integer price, String traderId) throws ItemNotFoundException
    {
        Trader trader = Game.getInstance().getMap().getTraderById(traderId);
        boolean sellPossible = trader.playerSellsItem(item, price);
        if (sellPossible){
            return ResponseEntity.status(406).build();
        }
        player.sellItemToTrader(item, price);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/fight/attack")
    public ResponseEntity<int[]> attackEnemy(final String enemyID)
    {
        final GenericEnemy enemy = Game.getInstance().getMap().getEnemyByID(enemyID);
        final FightGameAction playerAttackGameAction = new FightGameAction(enemy, CombatMoves.ATTACK, null);
        Game.getInstance().parseGameAction(playerAttackGameAction);
        return ResponseEntity.ok(new int[] {enemy.getHealthPoints(), Game.getInstance().getPlayer().getHealthPoints()});
    }

    @GetMapping("/fight/eat")
    public ResponseEntity<int[]> eatInFight(final String enemyID, final int itemID)
    {
        try
        {
            final GenericEnemy enemy = Game.getInstance().getMap().getEnemyByID(enemyID);
            final Consumable consumable = (Consumable) ItemParser.getGenericItemFromID(itemID);
            final FightGameAction playerEatsGameAction = new FightGameAction(enemy, CombatMoves.EAT, consumable);
            Game.getInstance().parseGameAction(playerEatsGameAction);
            return ResponseEntity.ok(new int[] {enemy.getHealthPoints(), Game.getInstance().getPlayer().getHealthPoints()});
        }
        catch (ItemNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/fight/flee")
    public ResponseEntity<int[]> fleeFight(final String enemyID)
    {
        final GenericEnemy enemy = Game.getInstance().getMap().getEnemyByID(enemyID);
        final FightGameAction fleeFightGameAction = new FightGameAction(enemy, CombatMoves.FLEE, null);
        Game.getInstance().parseGameAction(fleeFightGameAction);
        return ResponseEntity.ok(new int[] {Game.getInstance().getPlayer().getHealthPoints(), Game.getInstance().getPlayer().getMaxHealthPoints()});
    }
}