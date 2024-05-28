package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.events.FightGameAction;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.BuyItemResponse;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.combatsystem.CombatMoves;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.dtos.CharacterTradeRequestDto;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.TradeItem;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.parser.ItemParser;
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
    public static Character player = Game.getInstance().getPlayer();

    @GetMapping
    public ResponseEntity<Character> getCharacter()
    {
        return ResponseEntity.status(HttpStatus.OK).body(GAME.getPlayer());
    }

    @GetMapping("/inventory")
    public ResponseEntity<Inventory> getInventory()
    {
        Inventory inventory = GAME.getPlayer().getInventory();
        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }

    @GetMapping("/trade_inventory")
    public ResponseEntity<List<TradeItem>> getAllTradeItems()
    {
        List<TradeItem> items = GAME.getPlayer().getInventory().getAllTradeItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("/money")
    public ResponseEntity<Integer> getMoney()
    {
        return ResponseEntity.status(HttpStatus.OK).body(GAME.getPlayer().getMoney());
    }

    @PostMapping("/money/add")
    public ResponseEntity<Integer> addMoney(@RequestParam int money)
    {
        GAME.getPlayer().addMoney(money);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(GAME.getPlayer().getMoney());
    }

    @DeleteMapping("/money/remove")
    public ResponseEntity<Integer> removeMoney(@RequestParam int money)
    {
        if (GAME.getPlayer().getMoney() < money)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        GAME.getPlayer().removeMoney(money);
        return ResponseEntity.status(HttpStatus.OK).body(GAME.getPlayer().getMoney());
    }

    @PostMapping("/inventory/add")
    public ResponseEntity<?> addItem(GenericItem item)
    {
        GAME.getPlayer().getInventory().addItem(item, 1);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/eat")
    public ResponseEntity<Integer> consume(@RequestParam Consumable consumable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(GAME.getPlayer().eat(consumable));
    }

    @DeleteMapping("/inventory/remove")
    public ResponseEntity<?> removeItem(GenericItem item) throws ItemNotFoundException
    {
        GAME.getPlayer().getInventory().removeItem(item, 1);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/fight/attack")
    public ResponseEntity<int[]> attackEnemy(final String enemyID)
    {
        final GenericEnemy enemy = GAME.getMap().getEnemyByID(enemyID);
        final FightGameAction playerAttackGameAction = new FightGameAction(enemy, CombatMoves.ATTACK, null);
        GAME.parseGameAction(playerAttackGameAction);
        return ResponseEntity.ok(new int[] {enemy.getHealthPoints(), GAME.getPlayer().getHealthPoints()});
    }

    @GetMapping("/fight/eat")
    public ResponseEntity<int[]> eatInFight(final String enemyID, final int itemID)
    {
        try
        {
            final GenericEnemy enemy = Game.getInstance().getMap().getEnemyByID(enemyID);
            final Consumable consumable = (Consumable) ItemParser.getGenericItemById(itemID);
            final FightGameAction playerEatsGameAction = new FightGameAction(enemy, CombatMoves.EAT, consumable);
            GAME.parseGameAction(playerEatsGameAction);
            return ResponseEntity.ok(new int[] {enemy.getHealthPoints(), GAME.getPlayer().getHealthPoints()});
        }
        catch (ItemNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/fight/flee")
    public ResponseEntity<int[]> fleeFight(final String enemyID)
    {
        final GenericEnemy enemy = GAME.getMap().getEnemyByID(enemyID);
        final FightGameAction fleeFightGameAction = new FightGameAction(enemy, CombatMoves.FLEE, null);
        GAME.parseGameAction(fleeFightGameAction);
        return ResponseEntity.ok(new int[] {GAME.getPlayer().getHealthPoints(), GAME.getPlayer().getMaxHealthPoints()});
    }
    
    @PostMapping(value = "/buy_item_from_trader", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> buyItemFromTrader(@RequestBody CharacterTradeRequestDto requestDto) throws ItemNotFoundException
    {
        GenericItem item = getGenericItemById(requestDto.getItemID());
        Enum<BuyItemResponse> buyItemResponse = GAME.getPlayer().buyItemFromTrader(item, requestDto.getPrice());
        Trader trader = Game.getTraderById(requestDto.getTraderID());
        if (buyItemResponse == BuyItemResponse.NOT_ENOUGH_SPACE)
        {
            return ResponseEntity.status(405).build();
        }
        else if (buyItemResponse == BuyItemResponse.NOT_ENOUGH_MONEY)
        {
            return ResponseEntity.status(402).build();
        }
        else
        {
            trader.playerBuysItem(item, requestDto.getPrice());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @PostMapping(value = "/sell_item_to_trader", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> sellItemToTrader(@RequestBody CharacterTradeRequestDto requestDto) throws ItemNotFoundException
    {
        GenericItem item = getGenericItemById(requestDto.getItemID());
        Trader trader = Game.getTraderById(requestDto.getTraderID());
        boolean sellPossible = trader.playerSellsItem(item, requestDto.getPrice());
        if (!sellPossible)
        {
            return ResponseEntity.status(406).build();
        }
        GAME.getPlayer().sellItemToTrader(item, requestDto.getPrice());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
