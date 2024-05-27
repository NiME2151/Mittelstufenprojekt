package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.parser.TraderParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TradeItemGameActionTest
{
    @Test
    void doAction()
    {
        final Game game = Game.getInstance();
        final Map map = game.getMap();
        map.changePlayerLocation(map.getAllNodes().get(3)); //get market and set it as the starting point of the game
        final GenericItem item = new GenericItemImpl();
        final Trader trader = TraderParser.getTraders().get(0); // Jenprest

        final TradeItemGameAction gameActionSell = new SellItemGameAction(game.getPlayer(), item, trader);

        assertEquals(0, gameActionSell.doAction(21));
        assertFalse(game.getPlayer().getInventory().isItemPresent(item));
        assertFalse(trader.getInventory().isItemPresent(item));

        final TradeItemGameAction gameActionBuy = new BuyItemGameAction(trader, item, game.getPlayer());

        assertEquals(0, gameActionBuy.doAction(21));
        assertFalse(game.getPlayer().getInventory().isItemPresent(item));
        assertFalse(trader.getInventory().isItemPresent(item));

        game.getPlayer().addItemToInventory(item, 1);

        assertTrue(game.getPlayer().getInventory().isItemPresent(item));
        assertFalse(trader.getInventory().isItemPresent(item));

        assertEquals(1, gameActionSell.doAction(21));
        assertFalse(game.getPlayer().getInventory().isItemPresent(item));
        assertTrue(trader.getInventory().isItemPresent(item));

        assertEquals(1, gameActionBuy.doAction(21));
        assertTrue(game.getPlayer().getInventory().isItemPresent(item));
        assertFalse(trader.getInventory().isItemPresent(item));
    }
}
