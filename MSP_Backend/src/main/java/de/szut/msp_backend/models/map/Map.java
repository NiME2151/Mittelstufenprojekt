package de.szut.msp_backend.models.map;

import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.tradesystem.Trader;

import java.util.*;

public class Map
{
  public static Node lake;
  public static Node tavern;
  public static Node forest;
  public static Node market;
  public static Node arena;

  private Node playerLocation;

  public Map()
  {
    lake = new Node("1","Lake", "This small Lake seems to be soothing your mind and make you feel at peace. With its constant Water flowing it makes you forget all the hardships of living.", "nodeItemsLake", "nodeEnemiesLake");
    tavern = new Node("2","Tavern", "The local Tavern seems to have seen livelier days, but its still comforting, + you can get a good beer here :)", "nodeItemsTavern", "nodeEnemiesTavern");
    forest = new Node("3", "Forest", "The Forest is dense and full of life. There is some dark places you might want to avoid. A good source of Mushrooms, too.", "nodeItemsForest", "nodeEnemiesForest");
    market = new Node("4","Market", "The Market is tidy and smells really nice. All the fresh picked goods, the fresh bakery goods, all the smells you'd need to keep your mouth watering like the famous Falls of Offler.", "nodeItemsMarket", "nodeEnemiesMarket");
    arena = new Node("5", "Arena", "The floor is still stained by blood. You are unsure if you should ever be here. This place makes you feel uneasy.", "nodeItemsArena", "nodeEnemiesArena");

    playerLocation = tavern;
    setNeighbours();
  }

  private void setNeighbours()
  {
    lake.addNeighbour(Direction.EAST, "3");

    forest.addNeighbour(Direction.WEST, "1");
    forest.addNeighbour(Direction.SOUTH, "4");

    market.addNeighbour(Direction.NORTH, "3");
    market.addNeighbour(Direction.SOUTH, "5");
    market.addNeighbour(Direction.WEST, "2");

    tavern.addNeighbour(Direction.EAST, "4");
    tavern.addNeighbour(Direction.SOUTH, "5");

    arena.addNeighbour(Direction.NORTH, "4");
  }

  public static List<Node> getAllNodes()
  {
    final List<Node> nodes = new ArrayList<>();
    nodes.add(lake);
    nodes.add(arena);
    nodes.add(forest);
    nodes.add(market);
    nodes.add(tavern);
    return nodes;
  }
  
  public static Node getNodeById(String nodeId){
      List<Node> nodes = getAllNodes();
      System.out.println("nodes");
      System.out.println(nodes);
      Optional<Node> node = nodes.stream().filter(n -> n.getNodeId().equals(nodeId)).findFirst();
      
      return node.orElse(null);
  }

  public Node getPlayerLocation()
  {
    return this.playerLocation;
  }
  
    public int changePlayerLocation(Node location)
    {
        if (playerLocation == null || playerLocation.getNeighbourMap().containsValue(location.getNodeId()))
        {
            this.playerLocation = location;
            return 1;
        }
        return 0;
    }
    
    public Trader getTraderById(final int traderID)
    {
        for (final Node node : getAllNodes())
        {
            final Trader trader = node.getTraderByIDOrNull(traderID);
            if (trader != null)
            {
                return trader;
            }
        }
        return null;
    }

    public GenericEnemy getEnemyByID(final String enemyID)
    {
        for (final Node node : getAllNodes())
        {
            final GenericEnemy enemy = node.getEnemyByIDOrNull(enemyID);
            if (enemy != null)
            {
                return enemy;
            }
        }
        return null;
    }

    public void update(final int clicks)
    {
      for (Node node : getAllNodes())
      {
        node.update(clicks);
      }
    }

    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      for (Node node : getAllNodes())
      {
        builder.append(node.toString());
      }
      builder.append("\n\nCurrent Node:\n\n");
      if (playerLocation != null)
      {
        builder.append(playerLocation.toString());
      }
      else
      {
        builder.append("null");
      }
      return builder.toString();
    }
}
