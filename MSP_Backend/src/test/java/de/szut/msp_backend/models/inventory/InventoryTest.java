package de.szut.msp_backend.models.inventory;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.ItemType;
import de.szut.msp_backend.parser.ItemParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest
{
    @Test
    void testAddItem()
    {
        final Inventory inventory = new Inventory(2);
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(2), 1));
        assertEquals(inventory.getItems().size(), 1);
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(2), 1));
        assertEquals(inventory.getItems().size(), 1);
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(3), 1));
        assertEquals(inventory.getItems().size(), 2);
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(4), 1));
        assertEquals(inventory.getItems().size(), 2);
    }

    @Test
    void testIsNotFull()
    {
        final Inventory inventory = new Inventory(2);
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(2), 1));
        assertTrue(inventory.isNotFull());
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(3), 1));
        assertFalse(inventory.isNotFull());
    }

    @Test
    void testRemoveItem()
    {
        final Inventory inventory = new Inventory(2);
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(2), 3));
        assertEquals(inventory.getItems().get(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2))), 3);
        assertDoesNotThrow(() -> inventory.removeItem(ItemParser.getGenericItemById(2), 2));
        assertEquals(inventory.getItems().get(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2))), 1);
        assertDoesNotThrow(() -> inventory.removeItem(ItemParser.getGenericItemById(2), 2));
        assertFalse(inventory.getItems().containsKey(assertDoesNotThrow(() -> ItemParser.getGenericItemById(2))));
        assertThrowsExactly(ItemNotFoundException.class, () -> inventory.removeItem(ItemParser.getGenericItemById(2), 2));
    }

    @Test
    void testGetItemsOfType()
    {
        final Inventory inventory = new Inventory(10);

        assertEquals(0, inventory.getItemsOfType(ItemType.CONSUMABLE).size());

        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(35), 1));  // fish
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(42), 1));  // fish
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(43), 1));  // fish
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(2), 1));   // item
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(7), 1));   // item

        assertEquals(0, inventory.getItemsOfType(ItemType.CONSUMABLE).size());
        assertEquals(3, inventory.getItemsOfType(ItemType.FISH).size());
    }

    @Test
    void testGetEmptySlots()
    {
        final Inventory inventory = new Inventory(10);

        assertEquals(10, inventory.getEmptySlots());

        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(2), 1));   // item
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(7), 1));   // item

        assertEquals(8, inventory.getEmptySlots());
    }

    @Test
    void testRemoveRandomItem()
    {
        final Inventory inventory = new Inventory(10);

        inventory.removeRandomItem();

        assertEquals(10, inventory.getEmptySlots());

        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(2), 1));   // item
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(7), 1));   // item

        assertEquals(8, inventory.getEmptySlots());

        inventory.removeRandomItem();

        assertEquals(9, inventory.getEmptySlots());

    }

    @Test
    void testIsItemPresent()
    {
        final Inventory inventory = new Inventory(10);
        final GenericItem item = assertDoesNotThrow(() -> ItemParser.getGenericItemById(5));

        assertFalse(inventory.isItemPresent(item));

        inventory.addItem(item, 1);

        assertTrue(inventory.isItemPresent(item));
    }

    @Test
    void testClearInventory()
    {
        final Inventory inventory = new Inventory(10);

        assertEquals(10, inventory.getEmptySlots());

        inventory.clearInventory();

        assertEquals(10, inventory.getEmptySlots());

        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(2), 1));   // item
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(7), 1));   // item

        assertEquals(8, inventory.getEmptySlots());

        inventory.clearInventory();

        assertEquals(10, inventory.getEmptySlots());
    }

    @Test
    void testGetAllTradeItems()
    {
        final Inventory inventory = new Inventory(10);

        assertEquals(0, inventory.getAllTradeItems().size());

        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(2), 0));   // item
        assertDoesNotThrow(() -> inventory.addItem(ItemParser.getGenericItemById(7), 5));   // item

        assertEquals(5, inventory.getAllTradeItems().size());
    }

    /*
    @Test
    void testGetMaxSize()
    {
    }

    @Test
    void testSetMaxSize()
    {
    }


    @Test
    void testToString()
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
    } */
}
