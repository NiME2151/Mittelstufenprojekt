package de.szut.msp_backend.controller;

import de.szut.msp_backend.events.GenericItemImpl;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.TradeItem;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.parser.ItemParser;
import de.szut.msp_backend.parser.TraderParser;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TraderControllerTest
{
    @Test
    void testGetTraderByID()
    {
        final ResponseEntity<Trader> response = new TraderController().getTraderByID("1");
        assertEquals(TraderParser.getTraders().get(1), response.getBody());
    }

    @Test
    void testGetAllTradeItems()
    {
        final ResponseEntity<List<TradeItem>> response = new TraderController().getAllTradeItems("0");
        assertEquals(TraderParser.getTraders().get(0).getAllTradeItems(), response.getBody());
    }

    @Test
    void testAddItem()
    {
        final GenericItem item = assertDoesNotThrow(() -> {return ItemParser.getGenericItemById(20);});
        ResponseEntity<?> response = new TraderController().addItem("1", item, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        final GenericItem clone = GenericItemImpl.clone(item);
        clone.setItemID(99);
        response = new TraderController().addItem("1", clone, 2);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testRemoveItem()
    {
        ResponseEntity<?> response = new TraderController().removeItem("1", 32, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        response = new TraderController().removeItem("1", 99, 1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testSize()
    {
        final ResponseEntity<Integer> response = new TraderController().size("1");
        assertEquals(TraderParser.getTraders().get(1).getInventory().getMaxSize(), response.getBody());
    }

    @Test
    void testSizeSet()
    {
        final ResponseEntity<?> response = new TraderController().sizeSet("1", 50);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(50, TraderParser.getTraders().get(1).getInventory().getMaxSize());
    }

    @Test
    void testIsNotFull()
    {
        final ResponseEntity<Boolean> response = new TraderController().isNotFull("1");
        assertEquals(Boolean.TRUE, response.getBody());
    }

    @Test
    void testIsItemPresent()
    {
        final GenericItem item = (GenericItem) TraderParser.getTraders().get(1).getInventory().getItems().keySet().toArray()[0];
        final ResponseEntity<Boolean> response = new TraderController().isItemPresent("1", item);
        assertEquals(Boolean.TRUE, response.getBody());
    }
}
