package de.szut.msp_backend.models.combatsystem;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.Lootable;
import de.szut.msp_backend.models.map.Node;
import de.szut.msp_backend.parser.ItemParser;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static de.szut.msp_backend.models.map.Map.*;
import static org.junit.jupiter.api.Assertions.*;

public class CombatsystemTest
{
    @Test
    void testCharacterAttack()
    {
        final GenericEnemy enemy = new GenericEnemy("Testenemy", 20, 1);
        Combatsystem.characterAttack(Game.getInstance().getPlayer(), enemy);
        assertEquals(20-5, enemy.getHealthPoints());
        assertDoesNotThrow(() -> Game.getInstance().getPlayer().addItemToInventory(ItemParser.getGenericItemById(22), 3));
        Combatsystem.characterAttack(Game.getInstance().getPlayer(), enemy);
        assertEquals(0, enemy.getHealthPoints());

        Game.getInstance().getPlayer().setHealthPoints(Game.getInstance().getPlayer().getMaxHealthPoints());
        Game.getInstance().getPlayer().clearInventory();
    }

    @Test
    void testEnemyAttack()
    {
        Game.getInstance().getPlayer().setMaxHealthPoints(100);
        Game.getInstance().getPlayer().setHealthPoints(Game.getInstance().getPlayer().getMaxHealthPoints());
        final GenericEnemy enemy = new GenericEnemy("Testenemy", 20, 50);
        Combatsystem.enemyAttack(enemy, Game.getInstance().getPlayer());
        assertEquals(100 - 50, Game.getInstance().getPlayer().getHealthPoints());
        Combatsystem.enemyAttack(enemy, Game.getInstance().getPlayer());


        assertEquals(0, Game.getInstance().getPlayer().getHealthPoints());

        Game.getInstance().getPlayer().setHealthPoints(Game.getInstance().getPlayer().getMaxHealthPoints());
    }

    @Test
    void testCharacterFlee()
    {
        final Character player = Game.getInstance().getPlayer();
        player.setMaxHealthPoints(100);
        player.setHealthPoints(50);

        Combatsystem.characterFlee(player);

        assertEquals(25, Game.getInstance().getPlayer().getHealthPoints());
        assertEquals(80, Game.getInstance().getPlayer().getMaxHealthPoints());

        player.setMaxHealthPoints(100);
        player.setHealthPoints(100);
    }

    @Test
    void testIsCharacterDead()
    {
        final Character player = Game.getInstance().getPlayer();
        player.setMaxHealthPoints(100);
        player.setHealthPoints(50);

        assertFalse(Combatsystem.isCharacterDead(player));

        player.setHealthPoints(0);

        assertTrue(Combatsystem.isCharacterDead(player));

        player.setMaxHealthPoints(100);
        player.setHealthPoints(100);
    }

    @Test
    void testIsEnemyDead()
    {
        final GenericEnemy enemy = new GenericEnemy("Testenemy", 20, 50);

        assertFalse(Combatsystem.isEnemyDead(enemy));

        enemy.setHealthPoints(0);

        assertTrue(Combatsystem.isEnemyDead(enemy));
    }

    @Test
    void testCharacterTurn()
    {
        final Character player = Game.getInstance().getPlayer();
        final GenericEnemy enemy = new GenericEnemy("TestEnemy", 20, 50);
        final Consumable consumable = (Consumable) assertDoesNotThrow(() -> ItemParser.getGenericItemById(12)); // meat, 10 healthgain

        Combatsystem.characterTurn(player, null, null, CombatMoves.ATTACK);

        Combatsystem.characterTurn(player, enemy, null, CombatMoves.ATTACK);

        assertEquals(20-5, enemy.getHealthPoints());

        player.setMaxHealthPoints(100);
        player.setHealthPoints(50);

        Combatsystem.characterTurn(player, null, null, CombatMoves.EAT);

        Combatsystem.characterTurn(player, null, consumable, CombatMoves.EAT);

        assertEquals(60, player.getHealthPoints());

        Combatsystem.characterTurn(player, null, null, CombatMoves.FLEE);

        assertEquals(30, player.getHealthPoints());
        assertEquals(80, player.getMaxHealthPoints());

        player.setMaxHealthPoints(100);
        player.setHealthPoints(100);
    }

    @Test
    void testEnemyTurn()
    {
        final Character player = Game.getInstance().getPlayer();
        final GenericEnemy enemy = new GenericEnemy("TestEnemy", 20, 20);

        player.setMaxHealthPoints(100);
        player.setHealthPoints(100);

        Combatsystem.enemyTurn(enemy, player);

        assertEquals(80, player.getHealthPoints());

        player.setMaxHealthPoints(100);
        player.setHealthPoints(100);
    }

    @Test
    void testCheckForFightEnd()
    {
        final de.szut.msp_backend.models.map.Map map = Game.getInstance().getMap();
        changeToNode(arena);
        final Character player = Game.getInstance().getPlayer();
        final GenericEnemy enemy = new GenericEnemy("TestEnemy", 50, 3);

        final Map<GenericItem, Lootable> findableItems = arena.getFindableItems();

        arena.setFindableItems(new HashMap<GenericItem, Lootable>());

        // player dead with items in inventory

        player.setMaxHealthPoints(100);
        player.setHealthPoints(0);
        player.setMoney(50);
        player.clearInventory();

        player.addItemToInventory(ItemParser.getItemList().get(2), 1);

        assertTrue(Combatsystem.checkForFightEnd(player, enemy));

        assertTrue(arena.getFindableItems().containsKey(ItemParser.getItemList().get(2)));
        assertFalse(player.getInventory().isItemPresent(ItemParser.getItemList().get(2)));
        assertEquals(tavern, Game.getInstance().getMap().getPlayerLocation());
        assertEquals(50, player.getHealthPoints());
        assertEquals(0, player.getMoney());

        Game.getInstance().getMap().changePlayerLocation(arena);

        // player dead without items in inventory

        player.setMaxHealthPoints(100);
        player.setHealthPoints(0);
        player.setMoney(50);
        player.clearInventory();

        assertTrue(Combatsystem.checkForFightEnd(player, enemy));

        assertEquals(tavern, Game.getInstance().getMap().getPlayerLocation());
        assertEquals(50, player.getHealthPoints());
        assertEquals(0, player.getMoney());

        Game.getInstance().getMap().changePlayerLocation(arena);

        // enemy dead with items in inventory, player healthpoints + (player maxhealthpoints / 2) exceeds max healthppoints

        player.setMaxHealthPoints(100);
        player.setHealthPoints(100);
        player.setMoney(50);
        enemy.setHealthPoints(0);
        player.clearInventory();

        assertDoesNotThrow(() -> enemy.getLootItems().put(ItemParser.getGenericItemById(35), 1));

        assertTrue(Combatsystem.checkForFightEnd(player, enemy));

        assertEquals(player.getMaxHealthPoints(), player.getHealthPoints());
        assertEquals(105, player.getMaxHealthPoints());
        assertEquals(60, player.getMoney());
        assertTrue(player.getInventory().isItemPresent(ItemParser.getItemList().get(35)));

        // enemy dead without items in inventory

        player.setMaxHealthPoints(100);
        player.setHealthPoints(30);
        player.setMoney(50);
        enemy.setHealthPoints(0);
        player.clearInventory();

        assertTrue(Combatsystem.checkForFightEnd(player, enemy));

        assertEquals(30 + (int)(105/2), player.getHealthPoints());
        assertEquals(105, player.getMaxHealthPoints());
        assertEquals(60, player.getMoney());

        // noone dead

        player.setMaxHealthPoints(100);
        player.setHealthPoints(100);
        player.setMoney(50);
        enemy.setHealthPoints(50);
        player.clearInventory();

        assertFalse(Combatsystem.checkForFightEnd(player, enemy));

        player.setMaxHealthPoints(100);
        player.setHealthPoints(100);
        player.setMoney(50);
        player.clearInventory();
        Game.getInstance().getMap().changePlayerLocation(market);
        arena.setFindableItems(findableItems);
    }

    private void changeToNode(final Node node)
    {
        Class<?> klasse = Game.getInstance().getMap().getClass();
        try
        {
            Field field = klasse.getDeclaredField("playerLocation");
            field.setAccessible(true);
            field.set(Game.getInstance().getMap(), node);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
