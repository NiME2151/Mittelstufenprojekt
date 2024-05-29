package de.szut.msp_backend.models.map;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.parser.TraderParser;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest
{
    @Test
    void testGetAllNodes()
    {
        assertEquals(5, Map.getAllNodes().size());
    }

    @Test
    void testChangePlayerLocation()
    {
        changeToNode(null);
        Game.getInstance().getMap().changePlayerLocation(Map.market);
        assertEquals(1, Game.getInstance().getMap().changePlayerLocation(Map.forest));
        assertEquals(0, Game.getInstance().getMap().changePlayerLocation(Map.forest));

        Game.getInstance().getMap().changePlayerLocation(Map.market);
    }

    @Test
    void testGetTraderById()
    {
        assertNull(Game.getInstance().getMap().getTraderById(0));
        final List<Trader> traders = new ArrayList<>();
        traders.add(TraderParser.getTraders().get(0));
        Map.market.setTraders(traders);
        assertNotNull(Game.getInstance().getMap().getTraderById(0));

        Map.market.setTraders(new ArrayList<>());
    }

    @Test
    void testGetEnemyByID()
    {
        final GenericEnemy enemy = new GenericEnemy("TestEnemy", 20, 20);
        assertNull(Game.getInstance().getMap().getEnemyByID(enemy.getID()));
        final List<GenericEnemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        Map.market.setEnemies(enemies);
        assertNotNull(Game.getInstance().getMap().getEnemyByID(enemy.getID()));

        Map.market.setEnemies(new ArrayList<>());
    }

    @Test
    void testUpdate()
    {
        assertDoesNotThrow(() -> Game.getInstance().getMap().update(12));
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
