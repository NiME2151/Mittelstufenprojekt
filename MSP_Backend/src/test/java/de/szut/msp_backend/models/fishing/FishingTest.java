package de.szut.msp_backend.models.fishing;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.item.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FishingTest
{
    @Test
    void testFishing()
    {
        final Character player = Game.getInstance().getPlayer();

        // may take a while but could be almost guaranteed that each fish will be present at least once.
        for (int i = 0; i < 150; i++)
        {
            player.clearInventory();

            assertDoesNotThrow(() -> Fishing.fishing(player), String.format("attempt %d", i));

            assertFalse(player.getInventory().getItemsOfType(ItemType.FISH).isEmpty(), String.format("attempt %d", i));

            player.clearInventory();
        }
    }
}
