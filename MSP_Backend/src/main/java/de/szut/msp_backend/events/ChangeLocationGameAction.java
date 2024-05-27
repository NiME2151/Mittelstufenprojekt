package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.map.Direction;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.map.Node;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static de.szut.msp_backend.MspBackendApplication.GAME;

public class ChangeLocationGameAction implements GameAction
{
  private final String nodeId;

  public ChangeLocationGameAction(final String nodeId)
  {
    this.nodeId = nodeId;
  }

  @Override
  public int doAction(final int clicks)
  {
      Game game = Game.getInstance();
      Map map = game.getMap();
      Node targetLocation = Map.getNodeById(nodeId);

      if (targetLocation == null)
      {
          Logger.getAnonymousLogger().log(Level.WARNING, "The Location that you tried to get does not exist.");
          return 0;
      }

      map.changePlayerLocation(targetLocation);
      return 1;
  }
}
