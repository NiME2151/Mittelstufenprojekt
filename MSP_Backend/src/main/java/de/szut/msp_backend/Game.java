package de.szut.msp_backend;

import de.szut.msp_backend.events.GameAction;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.tradesystem.Trader;
import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;

@Data
public class Game
{
  private static ArrayList<Trader> trader;
  private static Game instance;
  private final Map map;
  private final Character player;
  private int clicks;

  public Game()
  {
    map = new Map();
    trader = new ArrayList<>();
    // Testdaten nur für diesen Branch! Beim merge gerne herauswerfen
      
    player = new Character(1,1,1,1,1,1, new Inventory(1));
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

  public static Trader getTraderById(String traderID)
  {
    return trader.stream().filter(t -> Objects.equals(t.getTraderID().toString(), traderID)).findAny().orElseThrow();
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
    return this.clicks;
  }

  public void parseGameAction(GameAction gameAction)
  {
    clicks += gameAction.doAction(clicks);
  }
}
