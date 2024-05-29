package de.szut.msp_backend.models.tradesystem;

import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.parser.ItemParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TraderTest
{
    @Test
    void testPlayerBuysItem()
    {
    }

    @Test
    void testPlayerSellsItem()
    {
        final GenericItem item = assertDoesNotThrow(() -> ItemParser.getGenericItemById(2));
        final Trader trader = new Trader(3, "TestTrader", 250, new Inventory(1));
        trader.getInventory().addItem(assertDoesNotThrow(() -> ItemParser.getGenericItemById(3)), 1);
        trader.playerSellsItem(item, 2);
        trader.playerSellsItem(item, 2);
    }

    @Test
    void testGetAllTradeItems()
    {
    }

    @Test
    void testToString()
    {
    }

    @Test
    void testGetTraderID()
    {
    }

    @Test
    void testGetName()
    {
    }

    @Test
    void testGetMoney()
    {
    }

    @Test
    void testGetInventory()
    {
    }

    @Test
    void testSetMoney()
    {
    }

    @Test
    void testSetInventory()
    {
    }

    @Test
    void testEquals()
    {
    }

    @Test
    void testCanEqual()
    {
    }

    @Test
    void testHashCode()
    {
    }
}
