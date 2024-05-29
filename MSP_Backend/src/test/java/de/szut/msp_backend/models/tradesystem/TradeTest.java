package de.szut.msp_backend.models.tradesystem;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.parser.ItemParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

public class TradeTest
{
    @Test
    void testGetTrade()
    {
    }

    @Test
    void testBuyItemFromTrader()
    {
        final Trader trader = new Trader(4, "asdasdasdasd", 170, new Inventory(5));
        final Character mockPlayer = new Character(100, 100, 5, 5, 0, 0, new Inventory(1));
        trader.getInventory().addItem(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2)), 1);
        trader.getInventory().addItem(assertDoesNotThrow(() -> ItemParser.getGenericItemById(3)), 1);

        trader.getInventory().addItem(assertDoesNotThrow(() -> ItemParser.getGenericItemById(4)), 1);
        trader.getInventory().addItem(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2)), 1);

        try
        {
            Trade.buyItemFromTrader(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2)), mockPlayer, trader);
            Trade.buyItemFromTrader(assertDoesNotThrow(() -> ItemParser.getGenericItemById(3)), mockPlayer, trader);
            Trade.buyItemFromTrader(assertDoesNotThrow(() -> ItemParser.getGenericItemById(4)), mockPlayer, trader);
            Trade.buyItemFromTrader(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2)), mockPlayer, trader);
        }
        catch (final ItemNotFoundException ex)
        {
            fail(ex.getMessage());
        }
    }

    @Test
    void testSellItemToTrader()
    {
        final Trader trader = new Trader(4, "asdasdasdasd", 20, new Inventory(5));
        final Character mockPlayer = new Character(100, 100, 5, 5, 0, 0, new Inventory(1));
        mockPlayer.addItemToInventory(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2)), 1);
        mockPlayer.addItemToInventory(assertDoesNotThrow(() -> ItemParser.getGenericItemById(3)), 1);

        mockPlayer.addItemToInventory(assertDoesNotThrow(() -> ItemParser.getGenericItemById(4)), 1);
        mockPlayer.addItemToInventory(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2)), 1);

        try
        {
            Trade.sellItemToTrader(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2)), mockPlayer, trader);
            Trade.sellItemToTrader(assertDoesNotThrow(() -> ItemParser.getGenericItemById(3)), mockPlayer, trader);
            Trade.sellItemToTrader(assertDoesNotThrow(() -> ItemParser.getGenericItemById(4)), mockPlayer, trader);
            Trade.sellItemToTrader(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2)), mockPlayer, trader);
        }
        catch (final ItemNotFoundException ex)
        {
            if (!ex.getMessage().equals("Du kannst nichts verkaufen was du nicht hast"))
            {
                fail(ex.getMessage());
            }
        }
    }

    @Test
    void testGetBuyValue()
    {
    }

    @Test
    void testGetSellValue()
    {
    }
}
