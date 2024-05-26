package de.szut.msp_backend.events;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.tradesystem.Trade;
import de.szut.msp_backend.models.tradesystem.Trader;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class TradeItemGameAction implements GameAction
{
    private final Character player;
    private final GenericItem item;
    private final Trader trader;
    private final int trademodifier;

    /**
     * @param player the player that sells the item to the trader
     * @param item   the item the player sells to the trader
     * @param trader the trader that gets the item from the player
     */
    public TradeItemGameAction(final Character player, final GenericItem item, final Trader trader)
    {
        this.player = player;
        this.item = item;
        this.trader = trader;
        this.trademodifier = 0;
    }

    /**
     * @param trader the trader that sells the item to the player
     * @param item   the item the player buys from the trader
     * @param player the player that buys the item from the trader
     */
    public TradeItemGameAction(final Trader trader, final GenericItem item, final Character player)
    {
        this.trader = trader;
        this.item = item;
        this.player = player;
        this.trademodifier = 1;
    }

    @Override
    public int doAction(final int clicks)
    {
        //player sells to trader
        if (trademodifier == 0)
        {
            try
            {
                Trade.sellItemToTrader(item, player, trader);

                return 1;
            }
            catch (final ItemNotFoundException exc)
            {
                Logger.getAnonymousLogger().log(Level.WARNING, exc.getMessage());
            }
        }
        //trader sells to player (player buys from trader)
        else //if (trademodifier == 1)
        {
            try
            {
                Trade.buyItemFromTrader(item, player, trader);

                return 1;
            }
            catch (final ItemNotFoundException exc)
            {
                Logger.getAnonymousLogger().log(Level.WARNING, exc.getMessage());
            }
        }
        return 0;
    }
}
