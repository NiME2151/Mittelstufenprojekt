package de.szut.msp_backend.models.fishing;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.item.ItemType;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.map.Node;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class FishingTest
{
    @Test
    void testFishing()
    {
        changeToNode(Map.apartments);
        final Character player = Game.getInstance().getPlayer();

        // may take a while but could be almost guaranteed that each fish will be present at least once.
        for (int i = 0; i < 150; i++)
        {
            player.clearInventory();

            assertDoesNotThrow(() -> Fishing.fishing(player), String.format("attempt %d", i));

            assertFalse(player.getInventory().getItemsOfType(ItemType.FISH).isEmpty(), String.format("attempt %d", i));

            player.clearInventory();
        }
        changeToNode(Map.market);

        assertDoesNotThrow(() -> Fishing.fishing(player));
        assertTrue(player.getInventory().getItemsOfType(ItemType.FISH).isEmpty());
    }

    private void changeToNode(final Node node)
    {
        Class<?> klasse = Game.getInstance().getMap().getClass();
        try
        {
            Field field = klasse.getDeclaredField("playerLocation");
            field.setAccessible(true);
            field.set(Game.getInstance().getMap(), node);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
