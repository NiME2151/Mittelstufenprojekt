package de.szut.msp_backend.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
}
