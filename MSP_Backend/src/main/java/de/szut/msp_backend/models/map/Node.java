package de.szut.msp_backend.models.map;

import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.Lootable;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.parser.NodeLootParser;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Data
@Getter
public class Node
{

    private String nodeDisplayName;
    private String description;
    private java.util.Map<GenericItem, Lootable> findableItems;
    private HashMap<Direction, String> neighbourMap;
    private String itemLootTableName;
    private String entityLootTableName;
    private String nodeID;

    private List<Trader> traders;
    private List<GenericEnemy> enemies;

    public Node(String nodeID, String nodeDisplayName, String description, String itemLootTableName, String entityLootTableName)
    {
        this.nodeDisplayName = nodeDisplayName;
        this.description = description;
        this.findableItems = NodeLootParser.getFindableItems(itemLootTableName);
        this.neighbourMap = new HashMap<>();
        this.entityLootTableName = entityLootTableName;
        this.nodeID = nodeID;
        this.traders = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public String getNodeDisplayName()
    {
        return this.nodeDisplayName;
    }

    public String getDescription()
    {
        return description;
    }

    public java.util.Map<GenericItem, Lootable> getFindableItems()
    {
        return findableItems;
    }
    
    public void addPlayerItem(GenericItem findableItem)
    {
        if (!this.findableItems.containsKey(findableItem))
        {
            this.findableItems.put(findableItem, null);
        }
        else
        {
            this.findableItems.get(findableItem).addPlayerItem();
        }
    }

    public void removePlayerItem(GenericItem findableItem)
    {
        this.findableItems.remove(findableItem);
    }

    public void addNeighbour(Direction direction, String neighbourID)
    {
        this.neighbourMap.put(direction, neighbourID);
    }

    public String getEntityLootTableName()
    {
        return entityLootTableName;
    }

    public String getNodeID()
    {
        return this.nodeID;
    }

    public java.util.Map<Direction, String> getNeighbours()
    {
        return this.neighbourMap;
    }

    public boolean pickupItem(GenericItem item)
    {
        if (!findableItems.containsKey(item))
        {
            return false;
        }
        final Lootable lootable = findableItems.get(item);
        if (lootable == null)
        {
            removePlayerItem(item);
            return true;
        }
        if (lootable.isAvailable())
        {
            lootable.lootItem();
            return true;
        }
        return false;
    }

    public void addTrader(final Trader trader)
    {
        traders.add(trader);
    }

    public Trader getTraderByIDOrNull(final int traderID)
    {
        for (final Trader trader : traders)
        {
            if (trader.getTraderID() == (traderID))
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


    public void update(final int clicks)
    {
        for (Lootable loot : findableItems.values())
        {
            loot.update(clicks);
        }
    }

    @Override
    public String toString()
    {
        return String.format("ID:\t\t%s\nName:\t\t%s\nDescription:\t%s\nFindableItems:\t%s\nNeighbours:\tDue to fear or recursive assault on toString not printing\nlootTableString:\t%s\n", nodeID, nodeDisplayName, description, findableItems, entityLootTableName);
    }
  
    public String getNodeId()
    { 
        return this.nodeID;

    }
}
