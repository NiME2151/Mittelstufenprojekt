package de.szut.msp_backend.models.item;

import de.szut.msp_backend.models.tradesystem.Trade;
import de.szut.msp_backend.parser.ItemParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeItemTest
{
    @Test
    void testFillSellValue()
    {
        final GenericItem item = assertDoesNotThrow(() -> ItemParser.getGenericItemById(12));
        final TradeItem tradeItem = new TradeItem(item);

        assertEquals(Trade.getSellValue(item), tradeItem.fillSellValue(item));
    }

    @Test
    void testFillBuyValue()
    {
        final GenericItem item = assertDoesNotThrow(() -> ItemParser.getGenericItemById(12));
        final TradeItem tradeItem = new TradeItem(item);

        assertEquals(Trade.getBuyValue(item), tradeItem.fillBuyValue(item));
    }

    @Test
    void testGetSellValue()
    {
    }

    @Test
    void testGetBuyValue()
    {
    }

    @Test
    void testGetItemID()
    {
    }

    @Test
    void testGetDisplayName()
    {
    }

    @Test
    void testSetSellValue()
    {
    }

    @Test
    void testSetBuyValue()
    {
    }

    @Test
    void testSetItemID()
    {
    }

    @Test
    void testSetDisplayName()
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

    @Test
    void testToString()
    {
    }
}
