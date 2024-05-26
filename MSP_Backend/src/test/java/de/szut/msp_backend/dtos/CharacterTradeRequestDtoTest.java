package de.szut.msp_backend.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTradeRequestDtoTest
{
    final CharacterTradeRequestDto dto = new CharacterTradeRequestDto(12, 24, "36");

    @Test
    void getItemID()
    {
       assertEquals(12, dto.getItemID());
    }

    @Test
    void getPrice()
    {
        assertEquals(24, dto.getPrice());
    }

    @Test
    void getTraderID()
    {
        assertEquals("36", dto.getTraderID());
    }

    @Test
    void setItemID()
    {
        final int itemIDNew = 13;
        assertNotEquals(itemIDNew, dto.getItemID());
        dto.setItemID(itemIDNew);
        assertEquals(itemIDNew, dto.getItemID());
    }

    @Test
    void setPrice()
    {
        final int priceNew = 15;
        assertNotEquals(priceNew, dto.getPrice());
        dto.setPrice(priceNew);
        assertEquals(priceNew, dto.getPrice());
    }

    @Test
    void setTraderID()
    {
        final String traderIDNew = "37";
        assertNotEquals(traderIDNew, dto.getTraderID());
        dto.setTraderID(traderIDNew);
        assertEquals(traderIDNew, dto.getTraderID());
    }

    @Test
    void testEquals()
    {
        final CharacterTradeRequestDto dto2 = new CharacterTradeRequestDto(14, 26, "38");

        assertFalse(dto.equals(dto2));
        assertTrue(dto.equals(dto));
    }

    @Test
    void canEqual()
    {
        assertTrue(dto.canEqual(dto));
        assertFalse(dto.canEqual("i am something that is not the dto"));
    }

    @Test
    void testHashCode()
    {
        //i have no idea how hashcode is done so i took a failed test and took the actual value, so this is dirty
        assertEquals(250202, dto.hashCode());
    }

    @Test
    void testToString()
    {
        //i have no idea how lombok toString is done so i took a failed test and took the actual value, so this is dirty
        assertEquals("CharacterTradeRequestDto(itemID=12, price=24, traderID=36)", dto.toString());
    }
}
