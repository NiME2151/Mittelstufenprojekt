package de.szut.msp_backend;

import de.szut.msp_backend.events.GameAction;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.tradesystem.Trader;
import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;

@Data 
public class Game
{
    private final Map map;
    private final Character player;
    private int clicks;
    private static ArrayList<Trader> traders;
    private static ArrayList<GenericEnemy> enemies;
    private static Game instance;
  
    public Game()
    {
        map = new Map();
        traders = new ArrayList<>();
        enemies = new ArrayList<GenericEnemy>();
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
        return this.clicks;
    }
  
    public static Trader getTraderById(String traderID)
    {
        return traders.stream().filter(trader -> Objects.equals(trader.getTraderID().toString(), traderID)).findAny().orElseThrow();
    }

    public static GenericEnemy getEnemyByID(String enemyID)
    {
        return enemies.stream().filter(enemy -> Objects.equals(enemy.getID(), enemyID)).findAny().orElseThrow();
    }
  
    public void parseGameAction(GameAction gameAction)
    {
        clicks += gameAction.doAction(clicks);
    }
}
