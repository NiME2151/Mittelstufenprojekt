package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.ItemType;
import de.szut.msp_backend.models.item.Rarity;
import org.junit.jupiter.api.Test;

public class PickUpItemGameActionTest
{
    @Test
    void doAction()
    {
        final Game game = Game.getInstance();
        final GenericItem item = new GenericItemImpl();
        final PickUpItemGameAction gameAction = new PickUpItemGameAction(item);
    }

    @Test
    void pickUpItem()
    {
    }
}

class GenericItemImpl extends GenericItem
{
    public GenericItemImpl()
    {
        super(50, "TestItem", "TestItemDescription", 0, ItemType.ITEM, Rarity.LEGENDARY);
    }
}
