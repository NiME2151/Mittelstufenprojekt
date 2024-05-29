package de.szut.msp_backend.models.character;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.events.GenericItemImpl;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.parser.ItemParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest
{
    @Test
    void testAddMoney()
    {
        final int previousMoney = Game.getInstance().getPlayer().getMoney();
        Game.getInstance().getPlayer().addMoney(10);
        assertEquals(previousMoney + 10, Game.getInstance().getPlayer().getMoney());
    }

    @Test
    void testRemoveMoney()
    {
        final int previousMoney = Game.getInstance().getPlayer().getMoney();
        Game.getInstance().getPlayer().removeMoney(10);
        assertEquals(previousMoney - 10, Game.getInstance().getPlayer().getMoney());
    }

    @Test
    void testEat()
    {
        Game.getInstance().getPlayer().setHealthPoints(60);
        Game.getInstance().getPlayer().setMaxHealthPoints(100);

        final Consumable consumable = (Consumable) assertDoesNotThrow(() -> {return ItemParser.getGenericItemById(15);}); //healthpotion, 25 healthgain

        assertEquals(60+25, Game.getInstance().getPlayer().eat(consumable));

        assertEquals(Math.min(60+25+25, 100), Game.getInstance().getPlayer().eat(consumable));

        Game.getInstance().getPlayer().setHealthPoints(Game.getInstance().getPlayer().getMaxHealthPoints());
    }

    @Test
    void testSellItemToTrader()
    {
        final GenericItem item = assertDoesNotThrow(() -> {return ItemParser.getGenericItemById(12);});
        final int previousMoney = Game.getInstance().getPlayer().getMoney();

        Game.getInstance().getPlayer().addItemToInventory(item, 1);

        assertDoesNotThrow(() -> {Game.getInstance().getPlayer().sellItemToTrader(item, 12);});
        assertEquals(previousMoney + 12, Game.getInstance().getPlayer().getMoney());

        assertThrowsExactly(ItemNotFoundException.class, () -> {Game.getInstance().getPlayer().sellItemToTrader(item, 12);});
        assertEquals(previousMoney + 12, Game.getInstance().getPlayer().getMoney());

        Game.getInstance().getPlayer().setMoney(previousMoney);
    }

    @Test
    void testBuyItemFromTrader()
    {
        final GenericItem item = assertDoesNotThrow(() -> {return ItemParser.getGenericItemById(12);});
        assertEquals(BuyItemResponse.NOT_ENOUGH_MONEY, Game.getInstance().getPlayer().buyItemFromTrader(item, 10000));
        Game.getInstance().getPlayer().getInventory().setMaxSize(0);
        assertEquals(BuyItemResponse.NOT_ENOUGH_SPACE, Game.getInstance().getPlayer().buyItemFromTrader(item, 0));
        Game.getInstance().getPlayer().getInventory().setMaxSize(10);
        assertEquals(BuyItemResponse.OK, Game.getInstance().getPlayer().buyItemFromTrader(item, 0));
        Game.getInstance().getPlayer().getInventory().clearInventory();
    }

    @Test
    void testAddItemToInventory()
    {
    }

    @Test
    void testRemoveItemFromInventory()
    {
    }

    @Test
    void testGetInventory()
    {
    }

    @Test
    void testClearInventory()
    {
    }

    @Test
    void testGetHealthPoints()
    {
    }

    @Test
    void testGetMaxHealthPoints()
    {
    }

    @Test
    void testGetStrength()
    {
    }

    @Test
    void testGetLuck()
    {
    }

    @Test
    void testGetCharisma()
    {
    }

    @Test
    void testGetMoney()
    {
    }

    @Test
    void testSetHealthPoints()
    {
    }

    @Test
    void testSetMaxHealthPoints()
    {
    }

    @Test
    void testSetStrength()
    {
    }

    @Test
    void testSetLuck()
    {
    }

    @Test
    void testSetCharisma()
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

    @Test
    void testToString()
    {
    }
}
