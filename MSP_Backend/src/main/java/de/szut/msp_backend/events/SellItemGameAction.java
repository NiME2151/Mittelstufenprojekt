package de.szut.msp_backend.events;

import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.tradesystem.Trader;

public class SellItemGameAction extends TradeItemGameAction
{
  /**
   *
   * @param player the player that sells the item to the trader
   * @param item the item the player sells to the trader
   * @param trader the trader that gets the item from the player
   */
  public SellItemGameAction(Character player, GenericItem item, Trader trader)
  {
    super(player, item, trader);
  }
}
