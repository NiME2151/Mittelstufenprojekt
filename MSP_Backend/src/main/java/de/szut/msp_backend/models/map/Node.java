package de.szut.msp_backend.models.map;

import de.szut.msp_backend.models.item.GenericItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node
{
  private final String nodeDisplayName;
  private final String description;
  private final String itemLootTableName;
  private final String entityLootTableName;
  private final String nodeID;
  private List<GenericItem> findableItems;
  private Map<Direction, Node> neighbourMap;

  public Node(String nodeID, String nodeDisplayName, String description, String itemLootTableName, String entityLootTableName)
  {
    this.nodeDisplayName = nodeDisplayName;
    this.description = description;
    this.findableItems = new ArrayList<>();
    this.neighbourMap = new HashMap<>();
    this.itemLootTableName = itemLootTableName;
    this.entityLootTableName = entityLootTableName;
    this.nodeID = nodeID;
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
}
