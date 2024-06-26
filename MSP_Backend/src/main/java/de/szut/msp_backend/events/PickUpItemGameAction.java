package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.map.Node;
import org.springframework.transaction.annotation.Transactional;

public class PickUpItemGameAction implements GameAction
{
    private final GenericItem item;

    public PickUpItemGameAction(final GenericItem item)
    {
        this.item = item;
    }

    @Override
    public int doAction(final int clicks)
    {
        final Game game = Game.getInstance();
        final Map map = game.getMap();
        final Node playerLocation = map.getPlayerLocation();
        final Character player = game.getPlayer();

        return pickUpItem(playerLocation, player) ? 1 : 0;
    }

    public boolean pickUpItem(Node playerLocation, Character player)
    {
        if (playerLocation.getFindableItems().containsKey(item))
        {
            playerLocation.pickupItem(item);
            player.addItemToInventory(item, 1);
            return true;
        }
        return false;
    }
}
