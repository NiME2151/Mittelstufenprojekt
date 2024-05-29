package de.szut.msp_backend.models.map;

import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.parser.ItemParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;

public class NodeTest
{
    @Test
    void testGetNodeDisplayName()
    {
    }

    @Test
    void testGetDescription()
    {
    }

    @Test
    void testGetFindableItems()
    {
    }

    @Test
    void testAddPlayerItem()
    {
        final GenericItem item = assertDoesNotThrow(() -> ItemParser.getGenericItemById(2));
        final Node node = Map.market;
        node.addPlayerItem(item);
        node.addPlayerItem(item);

        Map.arena.addPlayerItem(assertDoesNotThrow(() -> ItemParser.getGenericItemById(10)));
    }

    @Test
    void testRemoveFindableItem()
    {
    }

    @Test
    void testAddNeighbour()
    {
    }

    @Test
    void testGetNeighbour()
    {
    }

    @Test
    void testGetItemLootTableName()
    {
    }

    @Test
    void testGetEntityLootTableName()
    {
    }

    @Test
    void testGetNodeID()
    {
    }

    @Test
    void testGetNeighbours()
    {
    }

    @Test
    void testPickupItem()
    {
        final GenericItem item = assertDoesNotThrow(() -> ItemParser.getGenericItemById(2));
        final Node node = Map.market;
        node.pickupItem(item);
        node.addPlayerItem(item);
        node.addPlayerItem(item);

        Map.arena.pickupItem(assertDoesNotThrow(() -> ItemParser.getGenericItemById(10)));
        Map.arena.pickupItem(assertDoesNotThrow(() -> ItemParser.getGenericItemById(10)));
    }

    @Test
    void testAddTrader()
    {
    }

    @Test
    void testGetTraderByIDOrNull()
    {
        assertNull(Map.market.getTraderByIDOrNull(9));
    }

    @Test
    void testAddEnemy()
    {
    }

    @Test
    void testGetEnemyByIDOrNull()
    {
    }

    @Test
    void testUpdate()
    {
        final GenericItem item = assertDoesNotThrow(() -> ItemParser.getGenericItemById(2));
        final Node node = Map.market;
        node.addPlayerItem(item);
        node.update(2);
    }
}
