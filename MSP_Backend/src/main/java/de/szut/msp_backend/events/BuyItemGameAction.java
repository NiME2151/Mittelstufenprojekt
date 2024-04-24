package de.szut.msp_backend.events;

import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.tradesystem.Trader;

public class BuyItemGameAction extends TradeItemGameAction
{
  /**
   *
   * @param trader the trader that sells the item to the player
   * @param item the item the player buys from the trader
   * @param player the player that buys the item from the trader
   */
  public BuyItemGameAction(Trader trader, GenericItem item, Character player)
  {
    super(trader, item, player);
  }
}
