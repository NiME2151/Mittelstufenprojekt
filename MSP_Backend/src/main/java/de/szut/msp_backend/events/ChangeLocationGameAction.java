package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.map.Direction;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.map.Node;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeLocationGameAction implements GameAction
{
  private final Direction direction;

  public ChangeLocationGameAction(final Direction direction)
  {
    this.direction = direction;
  }

  @Override
  public int doAction(final int clicks)
  {
    final Game game = Game.getInstance();
    final Map map = game.getMap();
    final Node playerLocation = map.getPlayerLocation();
    final Node targetLocation = playerLocation.getNeighbour(direction);

    if (targetLocation == null)
    {
      Logger.getAnonymousLogger().log(Level.WARNING, "The Location that you tried to get does not exist.");
      return 0;
    }

    map.changePlayerLocation(targetLocation);
    return 1;
  }
}
