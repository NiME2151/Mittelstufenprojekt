package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.ItemType;
import de.szut.msp_backend.models.item.Rarity;
import de.szut.msp_backend.models.map.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class PickUpItemGameActionTest
{
    @Test
    void testDoAction()
    {
        final Game game = Game.getInstance();
        final Map map = game.getMap();
        map.changePlayerLocation(Map.market); //get market and set it as the starting point of the game
        final GenericItem item = new GenericItemImpl();
        final PickUpItemGameAction gameAction = new PickUpItemGameAction(item);


        assertEquals(0, gameAction.doAction(12));
        assertFalse(map.getPlayerLocation().getFindableItems().containsKey(item));
        assertFalse(game.getPlayer().getInventory().isItemPresent(item));

        map.getPlayerLocation().addPlayerItem(item);

        assertTrue(map.getPlayerLocation().getFindableItems().containsKey(item));

        assertEquals(1, gameAction.doAction(12));

        assertFalse(map.getPlayerLocation().getFindableItems().containsKey(item));
        assertTrue(game.getPlayer().getInventory().isItemPresent(item));
    }

    @Test
    void testPickUpItem()
    {
        final Game game = Game.getInstance();
        final Map map = game.getMap();
        map.changePlayerLocation(Map.market); //get market and set it as the starting point of the game
        final GenericItem item = new GenericItemImpl();
        final PickUpItemGameAction gameAction = new PickUpItemGameAction(item);

        assertFalse(gameAction.pickUpItem(map.getPlayerLocation(), game.getPlayer()));
        assertFalse(map.getPlayerLocation().getFindableItems().containsKey(item));
        assertFalse(game.getPlayer().getInventory().isItemPresent(item));

        map.getPlayerLocation().addPlayerItem(item);

        assertTrue(gameAction.pickUpItem(map.getPlayerLocation(), game.getPlayer()));
        assertFalse(map.getPlayerLocation().getFindableItems().containsKey(item));
        assertTrue(game.getPlayer().getInventory().isItemPresent(item));
    }

    /**
     * This method is needed to clean the state of game as game is a singleton and still contains all the values of the previous tests when starting a new test.
     */
    @BeforeEach
    void cleanGame()
    {
        try
        {
            // get the game class
            Class<?> game = Game.getInstance().getClass();
            // get the instance field from the game class (private static Game instance)
            Field instance = game.getDeclaredField("instance");
            // set the instance field as accessible due to it not being accessible normally
            instance.setAccessible(true);
            // set the value of the instance field to null to ensure a completely new game instance for the next test
            instance.set(Game.getInstance(), null);
        }
        catch (final NoSuchFieldException | IllegalAccessException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @AfterAll
    static void clean()
    {
        new PickUpItemGameActionTest().cleanGame();
    }
}

class GenericItemImpl extends GenericItem
{
    public GenericItemImpl()
    {
        super(69, "TestItem", "TestItemDescription", 0, ItemType.ITEM, Rarity.LEGENDARY);
    }
}
