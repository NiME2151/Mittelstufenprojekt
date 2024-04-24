package de.szut.msp_backend;

import de.szut.msp_backend.events.GameAction;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.map.Map;

public class Game
{
  private final Map map;
  private final Character player;
  private int clicks;
  private static Game instance;

  public Game()
  {
    map = new Map();
    //TODO: hier einmal Frontendmann abfragen für Name und co erstellen lassen
    player = new Character();
    clicks = 0;
  }

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
