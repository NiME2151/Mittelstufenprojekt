package de.szut.msp_backend.models.map;

import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.tradesystem.Trader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node
{
    private final String nodeDisplayName;
    private final String description;
    private List<GenericItem> findableItems;
    private Map<Direction, Node> neighbourMap;
    private final String itemLootTableName;
    private final String entityLootTableName;
    private final String nodeID;

    private List<Trader> traders;
    private List<GenericEnemy> enemies;

    public Node(String nodeDisplayName, String description, String itemLootTableName, String entityLootTableName, String nodeID)
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

    public String getNodeDisplayName()
    {
        return this.nodeDisplayName;
    }

    public String getDescription()
    {
        return description;
    }

    public List<GenericItem> getFindableItems()
    {
        return findableItems;
    }

    public void addFindableItem(GenericItem findableItem)
    {
        this.findableItems.add(findableItem);
    }

    public void removeFindableItem(GenericItem findableItem)
    {
        this.findableItems.remove(findableItem);
    }

    public void addNeighbour(Direction direction, Node neighbour)
    {
        this.neighbourMap.put(direction, neighbour);
    }

    public Node getNeighbour(Direction direction)
    {
        return this.neighbourMap.getOrDefault(direction, null);
    }

    public String getItemLootTableName()
    {
        return itemLootTableName;
    }

    public String getEntityLootTableName()
    {
        return entityLootTableName;
    }

    public String getNodeID()
    {
        return this.nodeID;
    }

    public Map<Direction, Node> getNeighbours()
    {
        return this.neighbourMap;
    }

    public void pickupItem(GenericItem item)
    {
        findableItems.remove(item);
    }

    public void AddTrader(final Trader trader)
    {
        traders.add(trader);
    }

    public Trader getTraderByIDOrNull(final String traderID)
    {
        for (final Trader trader : traders)
        {
            if (trader.getTraderID().toString().equals(traderID))
            {
                return trader;
            }
        }
        return null;
    }

    public void AddEnemy(final GenericEnemy enemy)
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
