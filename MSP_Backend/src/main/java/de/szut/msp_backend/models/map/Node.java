package de.szut.msp_backend.models.map;

import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.tradesystem.Trader;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Getter
public class Node
{
    private final String nodeDisplayName;
    private final String description;
    private List<GenericItem> findableItems;
    private HashMap<Direction, String> neighbourMap;
    private final String itemLootTableName;
    private final String entityLootTableName;
    private final String nodeID;

    private List<Trader> traders;
    private List<GenericEnemy> enemies;

    public Node(String nodeID, String nodeDisplayName, String description, String itemLootTableName, String entityLootTableName)
    {
        this.nodeDisplayName = nodeDisplayName;
        this.description = description;
        this.findableItems = new ArrayList<>();
        this.neighbourMap = new HashMap<>();
        this.itemLootTableName = itemLootTableName;
        this.entityLootTableName = entityLootTableName;
        this.nodeID = nodeID;
        this.traders = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }
    
    public void addFindableItem(GenericItem findableItem)
    {
        this.findableItems.add(findableItem);
    }

    public void removeFindableItem(GenericItem findableItem)
    {
        this.findableItems.remove(findableItem);
    }

    public void addNeighbour(Direction direction, String neighbourID)
    {
        this.neighbourMap.put(direction, neighbourID);
    }
    
    public String getNodeId(){
        return this.nodeID;
    }
    
    public void pickupItem(GenericItem item)
    {
        findableItems.remove(item);
    }

    public void addTrader(final Trader trader)
    {
        traders.add(trader);
    }

    public Trader getTraderByIDOrNull(final int traderID)
    {
        for (final Trader trader : traders)
        {
            if (trader.getTraderID() == traderID)
            {
                return trader;
            }
        }
        return null;
    }

    public void addEnemy(final GenericEnemy enemy)
    {
        enemies.add(enemy);
    }

    public GenericEnemy getEnemyByIDOrNull(final String enemyID)
    {
        for (final GenericEnemy enemy : enemies)
        {
            if (enemy.getID().equals(enemyID))
            {
                return enemy;
            }
        }
        return null;
    }
}
