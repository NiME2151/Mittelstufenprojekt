package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.MspBackendApplication;
import de.szut.msp_backend.dtos.CharacterTradeRequestDto;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.TradeItem;
import de.szut.msp_backend.models.map.Node;
import de.szut.msp_backend.parser.ItemParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterControllerTest
{
    @Test
    void getCharacter()
    {
        final ResponseEntity<Character> response = new CharacterController().getCharacter();
        assertEquals(Game.getInstance().getPlayer(), response.getBody());
    }

    @Test
    void getInventory()
    {
        final ResponseEntity<Inventory> response = new CharacterController().getInventory();
        assertEquals(Game.getInstance().getPlayer().getInventory(), response.getBody());
    }

    @Test
    void getAllTradeItems()
    {
        final ResponseEntity<List<TradeItem>> response = new CharacterController().getAllTradeItems();
        assertEquals(Game.getInstance().getPlayer().getInventory().getAllTradeItems(), response.getBody());
    }

    @Test
    void getMoney()
    {
        final ResponseEntity<Integer> response = new CharacterController().getMoney();
        assertEquals(Game.getInstance().getPlayer().getMoney(), response.getBody());
    }

    @Test
    void addMoney()
    {
        final int money = Game.getInstance().getPlayer().getMoney();
        final ResponseEntity<Integer> response = new CharacterController().addMoney(20);
        assertEquals(Game.getInstance().getPlayer().getMoney(), response.getBody());
        assertEquals(money+20, Game.getInstance().getPlayer().getMoney());
    }

    @Test
    void removeMoney()
    {
        int money = Game.getInstance().getPlayer().getMoney();
        ResponseEntity<Integer> response = new CharacterController().removeMoney(20);
        assertEquals(Game.getInstance().getPlayer().getMoney(), response.getBody());
        assertEquals(money-20, Game.getInstance().getPlayer().getMoney());

        money = Game.getInstance().getPlayer().getMoney();
        response = new CharacterController().removeMoney(Integer.MAX_VALUE);
        assertNull(response.getBody());
        assertEquals(money, Game.getInstance().getPlayer().getMoney());
    }

    @Test
    void addItem()
    {
        try
        {
            final GenericItem item = ItemParser.getGenericItemById(5);
            ResponseEntity<?> response = new CharacterController().addItem(item);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());

            response = new CharacterController().addItem(null);

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
        catch (ItemNotFoundException e)
        {
            fail();
        }
    }

    @Test
    void consume()
    {
        try
        {
            final Consumable consumable = (Consumable) ItemParser.getGenericItemById(13);
            final ResponseEntity<Integer> response = new CharacterController().consume(consumable);
            assertEquals(Game.getInstance().getPlayer().getHealthPoints(), response.getBody());
        }
        catch (final ItemNotFoundException ex)
        {
            fail();
        }
    }

    @Test
    void removeItem()
    {
        try
        {
            final GenericItem item = ItemParser.getGenericItemById(2);
            ResponseEntity<?> response = new CharacterController().removeItem(item);
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

            Game.getInstance().getPlayer().addItemToInventory(item, 1);

            response = new CharacterController().removeItem(item);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }
            catch (final ItemNotFoundException ex)
        {
            fail();
        }
    }

    @Test
    void attackEnemy()
    {
        final GenericEnemy enemy = new GenericEnemy("alf", 20, 2);
        final String id = enemy.getID();
        final Node node = Game.getInstance().getMap().lake;
        Game.getInstance().getMap().changePlayerLocation(node);

        node.AddEnemy(enemy);
        Game.getInstance().getPlayer().setMaxHealthPoints(100);
        Game.getInstance().getPlayer().setHealthPoints(100);

        final ResponseEntity<int[]> response = new CharacterController().attackEnemy(id);

        final int[] expected = {20-5, 100-2};
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
        assertEquals(expected[0], response.getBody()[0]);
        assertEquals(expected[1], response.getBody()[1]);
    }

    @Test
    void eatInFight()
    {
        final GenericEnemy enemy = new GenericEnemy("alf", 20, 2);
        final String id = enemy.getID();
        final Node node = Game.getInstance().getMap().lake;
        Game.getInstance().getMap().changePlayerLocation(node);

        node.AddEnemy(enemy);

        Game.getInstance().getPlayer().setMaxHealthPoints(100);
        Game.getInstance().getPlayer().setHealthPoints(50);

        ResponseEntity<int[]> response = new CharacterController().eatInFight(id, 14);

        final int[] expected = {20, 100-2};
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
        assertEquals(expected[0], response.getBody()[0]);
        assertEquals(expected[1], response.getBody()[1]);

        response = new CharacterController().eatInFight(id, 99);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }

    @Test
    void fleeFight()
    {
        final GenericEnemy enemy = new GenericEnemy("alf", 20, 2);
        final String id = enemy.getID();
        final Node node = Game.getInstance().getMap().lake;
        Game.getInstance().getMap().changePlayerLocation(node);

        node.AddEnemy(enemy);
        Game.getInstance().getPlayer().setMaxHealthPoints(100);
        Game.getInstance().getPlayer().setHealthPoints(100);

        final ResponseEntity<int[]> response = new CharacterController().fleeFight(id);

        final int[] expected = {(100)/2-2, (int)(100*0.8)};
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
        assertEquals(expected[0], response.getBody()[0]);
        assertEquals(expected[1], response.getBody()[1]);
    }

    @Test
    void buyItemFromTrader()
    {
        final CharacterTradeRequestDto dto = new CharacterTradeRequestDto(1, 2, "0");
        try
        {
            ResponseEntity<?> response = new CharacterController().buyItemFromTrader(dto);

            assertEquals(HttpStatus.OK, response.getStatusCode());

            Game.getInstance().getPlayer().clearInventory();
            Game.getInstance().getPlayer().getInventory().setMaxSize(0);

            response = new CharacterController().buyItemFromTrader(dto);
                        // 405
            assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());


            Game.getInstance().getPlayer().getInventory().setMaxSize(20);
            Game.getInstance().getPlayer().setMoney(0);

            response = new CharacterController().buyItemFromTrader(dto);
                        // 402
            assertEquals(HttpStatus.PAYMENT_REQUIRED, response.getStatusCode());

            Game.getInstance().getPlayer().setMoney(50);
        }
        catch (final ItemNotFoundException ex)
        {
            fail();
        }

        dto.setItemID(99);

        assertThrows(ItemNotFoundException.class, () -> {new CharacterController().buyItemFromTrader(dto);});
    }

    @Test
    void sellItemToTrader()
    {
    }
}
