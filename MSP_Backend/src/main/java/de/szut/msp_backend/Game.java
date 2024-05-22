package de.szut.msp_backend;

import de.szut.msp_backend.events.GameAction;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.tradesystem.Trader;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static de.szut.msp_backend.parser.ItemParser.getGenericItemById;

@Data 
public class Game
{
    private final Map map;
    private final Character player;
    private int clicks;
    private ArrayList<GenericEnemy> enemies;
    private static ArrayList<Trader> trader = new ArrayList<>();
    private static Game instance;

    public static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
  
    public Game()
    {
        map = new Map();
        Inventory lynnInventory = new Inventory(20);
        Inventory inventory = new Inventory(10);
        try
        {
            lynnInventory.addItem(getGenericItemById(22), 10);
            inventory.addItem(getGenericItemById(11), 1);
        }
        catch (ItemNotFoundException e)
        {
            LOGGER.error(e.getMessage());
        }
        Trader lynnTheSmith = new Trader(0, "Lynn the Smith", 1000, lynnInventory);
        trader.add(lynnTheSmith);
        player = new Character(100, 100, 5, 5, 5, 50, inventory);
        enemies = new ArrayList<>();
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
    
    public static Trader getTraderById(int traderID)
    {
        return trader.stream().filter(t -> Objects.equals(t.getTraderID(), traderID)).findAny().orElseThrow();
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
