package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.map.Direction;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.map.Node;

public class ChangeRoomGameAction implements GameAction
{
  private final Direction direction;

  public ChangeRoomGameAction(final Direction direction)
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

    map.changePLayerLocation(targetLocation);
    return 1;
  }
}
