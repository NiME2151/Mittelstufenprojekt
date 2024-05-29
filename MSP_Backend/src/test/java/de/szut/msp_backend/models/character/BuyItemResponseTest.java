package de.szut.msp_backend.models.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BuyItemResponseTest
{
    @Test
    void testValues()
    {
        assertEquals(3, BuyItemResponse.values().length);
    }

    @Test
    void testValueOf()
    {
        assertEquals(BuyItemResponse.valueOf("OK"), BuyItemResponse.valueOf("OK"));
        assertNotEquals(BuyItemResponse.valueOf("NOT_ENOUGH_MONEY"), BuyItemResponse.valueOf("OK"));
    }
}
