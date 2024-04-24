package de.szut.msp_backend;

import de.szut.msp_backend.events.GameAction;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.map.Map;

public class Game
{
  private Map map;
  private Character player;
  private int clicks;
  private static Game instance;

  public static Game getInstance()
  {
    if (instance == null)
    {
      instance = new Game();
    }
    return instance;
  }

  public Map getMap()
  {
    return map;
  }

  public Character getPlayer()
  {
    return this.player;
  }

  public int getClicks()
  {
    return clicks;
  }

  public void parseGameAction(GameAction gameAction)
  {
    clicks += gameAction.doAction(clicks);
  }
}
