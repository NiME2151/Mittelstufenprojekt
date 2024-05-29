package de.szut.msp_backend.models.item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LootableTest
{
    @Test
    void testIsAvailable()
    {
        final Lootable lootable = new Lootable(21, 2);
        assertTrue(lootable.isAvailable());
    }

    @Test
    void testUpdate()
    {
        final Lootable lootable = new Lootable(21, 2);
        assertTrue(lootable.isAvailable());
        lootable.update(3);
        lootable.lootItem();
        assertFalse(lootable.isAvailable());
        lootable.update(10);
        assertFalse(lootable.isAvailable());
        lootable.update(32);
        assertTrue(lootable.isAvailable());
    }

    @Test
    void testLootItem()
    {
        final Lootable lootable = new Lootable(21, 2);
        assertTrue(lootable.isAvailable());
        lootable.lootItem();
        assertFalse(lootable.isAvailable());
    }

    @Test
    void testAddPlayerItem()
    {
        final Lootable lootable = new Lootable(21, 2);
        assertTrue(lootable.isAvailable());
        lootable.lootItem();
        assertFalse(lootable.isAvailable());
        lootable.addPlayerItem();
        assertTrue(lootable.isAvailable());
    }

    @Test
    void testToString()
    {
    }
}