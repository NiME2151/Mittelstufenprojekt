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

    /**
     * This function creates a Node
     * @param nodeDisplayName String
     * @param description String
     * @param itemLootTableName String
     * @param entityLootTableName String
     * @param nodeID String
     */
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

    /**
     * This function removes an item from the node's findableItems list
     * @param item GenericItem
     */
    public void pickupItem(GenericItem item)
    {
        findableItems.remove(item);
    }

    /**
     *This functions adds a trader to the node's traders list
     * @param trader Trader
     */
    public void AddTrader(final Trader trader)
    {
        traders.add(trader);
    }

    /**
     * This function returns the trader belonging to the ID if he is in the node's traders list
     * @param traderID int
     * @return Trader or Null
     */
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

    /**
     * This function adds an enemy to the node's enemies list
     * @param enemy GenericEnemy
     */
    public void AddEnemy(final GenericEnemy enemy)
    {
        enemies.add(enemy);
    }

    /**
     * This function returns the enemy belonging to the ID if he is in the node's enemies list
     * @param enemyID int
     * @return GenericEnemy or Null
     */
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
