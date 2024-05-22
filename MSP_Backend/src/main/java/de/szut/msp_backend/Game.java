package de.szut.msp_backend;

import de.szut.msp_backend.events.GameAction;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.parser.TraderParser;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data 
public class Game
{
    private final Map map;
    private final Character player;
    private int clicks;
    private static List<Trader> trader;
    private static Game instance;
  
    public Game()
    {
        map = new Map();
        trader = TraderParser.getTraders();
        //TODO: hier einmal Frontendmann abfragen für Name und co erstellen lassen
        player = new Character(1,1,1,1,1,1,new Inventory(2));
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
        return this.clicks;
    }
  
    public static Trader getTraderById(int traderID)
    {
        return trader.stream().filter(t -> Objects.equals(t.getTraderID(), traderID)).findAny().orElseThrow();
    }
  
    public void parseGameAction(GameAction gameAction)
    {
        clicks += gameAction.doAction(clicks);
    }
}
