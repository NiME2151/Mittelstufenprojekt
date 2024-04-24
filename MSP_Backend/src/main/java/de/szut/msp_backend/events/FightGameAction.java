package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.item.Consumable;

public class FightGameAction implements GameAction
{
  private final GenericEnemy enemy;
  private final CombatMoves move;
  private final Consumable consumable;

  public FightGameAction(GenericEnemy enemy, CombatMoves move, Consumable consumable)
  {
    this.enemy = enemy;
    this.move = move;
    this.consumable = consumable;
  }

  @Override
  public int doAction(final int clicks)
  {
    final Game game = Game.getInstance();
    final Character player = game.getPlayer();

    Combatsystem.characterTurn(player, enemy, consumable, move);

    if (!Combatsystem.checkForFightEnd(player, enemy))
    {
      enemyTurn(enemy, player);
    }

    return 0;
  }
}
