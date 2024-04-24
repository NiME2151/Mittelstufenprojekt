package de.szut.msp_backend.events;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.services.TradeService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TradeItemGameAction implements GameAction
{
  private final Character player;
  private final GenericItem item;
  private final Trader trader;
  private final int trademodifier;

  public TradeItemGameAction(final Character player, final GenericItem item, final Trader trader)
  {
    this.player = player;
    this.item = item;
    this.trader = trader;
    this.trademodifier = 0;
  }

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
        TradeService.sellItemToTrader(item, player, trader);

        return 1;
      }
      catch (final ItemNotFoundException exc)
      {
        Logger.getAnonymousLogger().log(Level.WARNING, exc.getMessage());
      }
    }
    //trader sells to player (player buys from trader)
    else if (trademodifier == 1)
    {
      try
      {
        TradeService.buyItemFromTrader(item, player, trader);

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
